package seedu.fractal.storage;

import seedu.fractal.component.game.CardButton;
import seedu.fractal.component.game.CardStatus;
import seedu.fractal.component.game.GameBoard;
import seedu.fractal.logic.Card;
import seedu.fractal.logic.Difficulty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Storage {

    private static final String DIVIDER = " ||| ";
    private static final String NEWLINE = "\n";
    private static final String END = "--- END ---";

    private static final GameBoard gameBoard = GameBoard.getInstance();

    public static void saveGameDetails(Difficulty difficulty, int numberOfMatches, boolean isOngoing) {
        saveToFile(FilePath.GAME_DETAILS_STORAGE_PATH,
                generateGameDetailsContent(difficulty, numberOfMatches, isOngoing));
    }

    public static void saveGame() {
        saveToFile(FilePath.GAME_STORAGE_PATH, generateGameBoardContent());
        // Save time?
    }


    public static void loadGameDetails() {
        try {
            String content = loadContentFromFile(FilePath.GAME_DETAILS_STORAGE_PATH);
            String[] contentLines = splitString(content, NEWLINE, 3, true);

            String[] gameDetails = splitString(contentLines[0], DIVIDER, 2);
            Difficulty difficulty = Difficulty.valueOf(gameDetails[0]);
            int numberOfMatches = Integer.parseInt(gameDetails[1]);

            gameBoard.setDetails(difficulty, numberOfMatches);

            boolean isOngoing = Boolean.parseBoolean(contentLines[1]);
            gameBoard.setOngoing(isOngoing);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            loadDefaultGameDetails();
        }
    }

    public static void loadGame() {

        // No game is ongoing
        if (!gameBoard.isOngoing()) {
            return;
        }

        try {
            String content = loadContentFromFile(FilePath.GAME_STORAGE_PATH);

            int numberOfMatches = gameBoard.getNumberOfMatches();
            int numberOfLines = (numberOfMatches * 2) + 2;

            String[] contentLines = splitString(content, NEWLINE, numberOfLines, true);

            String[] countInformation = splitString(contentLines[0], DIVIDER, 3);
            int matchedCardCount = Integer.parseInt(countInformation[0]);
            int selectedCardCount = Integer.parseInt(countInformation[1]);
            int matchCounter = Integer.parseInt(countInformation[2]);

            gameBoard.setCountInformation(matchedCardCount, selectedCardCount);
            gameBoard.setMatchCounter(matchCounter);

            String[] cardButtonsContent = Arrays.copyOfRange(contentLines, 1, numberOfLines - 1);
            loadCardButtons(cardButtonsContent, matchedCardCount, selectedCardCount);

            gameBoard.setOngoing(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // loadDefaultGameDetails();
        }
    }

    private static void saveToFile(String filePath, String content) {
        try {
            File saveFile = new File(filePath);
            saveFile.getParentFile().mkdirs();
            FileWriter fileWriter = new FileWriter(saveFile);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            // Error saving file
        }
    }

    private static String generateGameDetailsContent(Difficulty difficulty, int numberOfMatches, boolean isOngoing) {
        return String.format("%s%s%s\n%s\n%s", difficulty.name(), DIVIDER, numberOfMatches, isOngoing, END);
    }

    private static String generateGameBoardContent() {
        StringBuilder gameBoardContent = new StringBuilder();

        gameBoardContent.append(String.format("%s%s%s%s%s\n",
                gameBoard.getMatchedCardCount(), DIVIDER, gameBoard.getSelectedCardCount(), DIVIDER,
                gameBoard.getMatchCounter()));

        ArrayList<CardButton> cardButtons = gameBoard.getCardButtons();
        for (CardButton cardButton : cardButtons) {
            Card card = cardButton.getCard();
            gameBoardContent.append(String.format("%s%s%s%s%s%s%s%s%s\n",
                    cardButton.getButtonId(), DIVIDER, card.getName(), DIVIDER,
                    card.getValue(), DIVIDER, card.getImagePath(), DIVIDER, card.getStatus()));
        }

        gameBoardContent.append(String.format("%s", END));

        return gameBoardContent.toString();
    }

    private static String loadContentFromFile(String filePath) throws Exception {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String content = bufferedReader.lines().collect(Collectors.joining(NEWLINE));

        bufferedReader.close();
        fileReader.close();

        return content;
    }

    private static void loadDefaultGameDetails() {
        gameBoard.setDetails(Difficulty.EASY, 4);
        gameBoard.setOngoing(false);
    }

    private static void loadCardButtons(String[] contentLines, int matchedCardCount, int selectedCardCount)
            throws Exception {
        ArrayList<CardButton> allCardButtons = new ArrayList<>();
        ArrayList<CardButton> selectedCardButtons = new ArrayList<>();

        int currentMatchedCardCount = 0;
        int currentSelectedCardCount = 0;

        for (String line : contentLines) {
            String[] cardButtonData = splitString(line, DIVIDER, 5);
            int buttonId = Integer.parseInt(cardButtonData[0]);
            String name = cardButtonData[1];
            String value = cardButtonData[2];
            String imagePath = cardButtonData[3];
            CardStatus status = CardStatus.valueOf(cardButtonData[4]);

            Card card = new Card(name, value, imagePath, status);
            CardButton cardButton = new CardButton(buttonId, card);

            if (status == CardStatus.SELECTED) {
                cardButton.select();
                selectedCardButtons.add(cardButton);
                ++currentSelectedCardCount;
            } else if (status == CardStatus.MATCHED) {
                // Select and match for card to be faced-up
                cardButton.select();
                cardButton.match();
                ++currentMatchedCardCount;
            }

            allCardButtons.add(cardButton);
        }

        if (currentMatchedCardCount != matchedCardCount || currentSelectedCardCount != selectedCardCount) {
            throw new Exception("Card counts are different.");
        }

        if (selectedCardButtons.size() == 0) {
            gameBoard.setSelectedCards(null, null);
        } else if (selectedCardButtons.size() == 1) {
            gameBoard.setSelectedCards(selectedCardButtons.get(0), null);
        } else if (selectedCardButtons.size() == 2) {
            gameBoard.setSelectedCards(selectedCardButtons.get(0), selectedCardButtons.get(1));
        } else {
            throw new Exception("Excess selected cards.");
        }

        // Sort card buttons by ID
        allCardButtons.sort(Comparator.comparing(CardButton::getButtonId));

        gameBoard.setCardButtons(allCardButtons);
    }

    private static String[] splitString(String string, String delimiter, int expectedLength, boolean isCheckForEnd)
            throws Exception {
        String[] lines = string.split(Pattern.quote(delimiter));

        if (lines.length != expectedLength) {
            throw new Exception("Unexpected number of lines in load file.");
        }

        if (isCheckForEnd) {
            if (!lines[lines.length - 1].equals(END)) {
                throw new Exception("Last line is not END line.");
            }
        }

        return lines;
    }

    private static String[] splitString(String string, String delimiter, int expectedLength) throws Exception {
        return splitString(string, delimiter, expectedLength, false);
    }
}
