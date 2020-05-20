package seedu.fractal.storage;

import seedu.fractal.component.game.CardButton;
import seedu.fractal.component.game.GameBoard;
import seedu.fractal.logic.Card;
import seedu.fractal.logic.Difficulty;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Storage {

    private final static String DIVIDER = " ||| ";
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

    public static void saveGameDetails() {
        saveToFile(FilePath.GAME_DETAILS_STORAGE_PATH, generateGameDetailsContent());
    }

    public static void saveGame() {
        saveToFile(FilePath.GAME_STORAGE_PATH, generateGameCardsContent());
        // Save time?
    }

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

    private static String generateGameDetailsContent() {
        Difficulty difficulty = gameBoard.getDifficulty();
        int numberOfMatches = gameBoard.getNumberOfMatches();
        return String.format("%s%s%s\n", difficulty.name(), DIVIDER, numberOfMatches);
    }

    private static String generateGameCardsContent() {
        StringBuilder gameCardsContent = new StringBuilder();

        ArrayList<CardButton> cardButtons = gameBoard.getCardButtons();
        for (CardButton cardButton : cardButtons) {
            Card card = cardButton.getCard();
            gameCardsContent.append(String.format("%s%s%s%s%s%s%s%s%s\n",
                    cardButton.getButtonId(), DIVIDER, card.getName(), DIVIDER,
                    card.getValue(), DIVIDER, card.getImagePath(), DIVIDER, card.getStatus()));
        }

        gameCardsContent.append(String.format("%s%s%s\n",
                gameBoard.getSelectedCardCount(), DIVIDER, gameBoard.getMatchCount()));

        return gameCardsContent.toString();
    }

}
