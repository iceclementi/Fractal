package seedu.fractal.storage;

import seedu.fractal.component.game.CardButton;
import seedu.fractal.component.game.GameBoard;
import seedu.fractal.logic.Card;
import seedu.fractal.logic.Difficulty;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Storage {

    private final static String DIVIDER = " ||| ";
    private final static String NEWLINE = "\n";
    private final static String END = "--- END ---";

    private final static GameBoard gameBoard = GameBoard.getInstance();

    /*
    private static int difficulty;
    private static int numberOfMatches;


    public static int getDifficulty() {
        return difficulty;
    }

    public static int getNumberOfMatches() {
        return numberOfMatches;
    }
     */

    public static void saveGameDetails(Difficulty difficulty, int numberOfMatches) {
        saveToFile(FilePath.GAME_DETAILS_STORAGE_PATH, generateGameDetailsContent(difficulty, numberOfMatches));
    }

    public static void saveGame() {
        saveToFile(FilePath.GAME_STORAGE_PATH, generateGameCardsContent());
        // Save time?
    }

    // public static void loadGameDetails() {
    //
    // }

    public static void loadGame() {

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

    private static String generateGameDetailsContent(Difficulty difficulty, int numberOfMatches) {
        return String.format("%s%s%s\n%s", difficulty.name(), DIVIDER, numberOfMatches, END);
    }

    private static String generateGameCardsContent() {
        StringBuilder gameCardsContent = new StringBuilder();

        gameCardsContent.append(String.format("%s%s%s\n",
                gameBoard.getMatchCount(), DIVIDER, gameBoard.getSelectedCardCount()));

        ArrayList<CardButton> cardButtons = gameBoard.getCardButtons();
        for (CardButton cardButton : cardButtons) {
            Card card = cardButton.getCard();
            gameCardsContent.append(String.format("%s%s%s%s%s%s%s%s%s\n",
                    cardButton.getButtonId(), DIVIDER, card.getName(), DIVIDER,
                    card.getValue(), DIVIDER, card.getImagePath(), DIVIDER, card.getStatus()));
        }

        gameCardsContent.append(String.format("%s", END));

        return gameCardsContent.toString();
    }

    private static String loadContentFromFile(String filePath) throws Exception {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String content = bufferedReader.lines().collect(Collectors.joining(NEWLINE));

        bufferedReader.close();
        fileReader.close();

        return content;
    }

    public static void loadGameDetails() {
        try {
            String content = loadContentFromFile(FilePath.GAME_DETAILS_STORAGE_PATH);
            String[] contentLines = splitString(content, NEWLINE, 2, true);

            String[] gameDetails = splitString(contentLines[0], DIVIDER, 2);
            Difficulty difficulty = Difficulty.valueOf(gameDetails[0]);
            int numberOfMatches = Integer.parseInt(gameDetails[1]);

            gameBoard.setDetails(difficulty, numberOfMatches);

        } catch (Exception e) {
            loadDefaultGameDetails();
        }

    }

    private static void loadDefaultGameDetails() {
        gameBoard.setDetails(Difficulty.EASY, 4);
    }

    private static String[] splitString(String string, String delimiter, int expectedLength, boolean isCheckForEnd)
            throws Exception {
        String[] lines = string.split(Pattern.quote(delimiter));

        if (lines.length != expectedLength) {
            throw new Exception("Unexpected number of lines in load file.");
        }

        if (isCheckForEnd) {
            if (!lines[lines.length-1].equals(END)) {
                throw new Exception("Last line is not END line.");
            }
        }

        return lines;
    }

    private static String[] splitString(String string, String delimiter, int expectedLength) throws Exception {
        return splitString(string, delimiter, expectedLength, false);
    }
}
