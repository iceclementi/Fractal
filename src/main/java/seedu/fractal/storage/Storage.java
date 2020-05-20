package seedu.fractal.storage;

import seedu.fractal.logic.Difficulty;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private final static String DIVIDER = " ||| ";

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

    public static void saveGame(Difficulty difficulty, int numberOfMatches) {
        saveGameDetails(difficulty, numberOfMatches);

        // Save cards
        // card name, value and image location
        // selected, matched status
        //

        // Save match count

        // Save time?
    }

    public static void loadGame() {

    }

    private static void saveGameDetails(Difficulty difficulty, int numberOfMatches) {
        try {
            File saveFile = new File(FilePath.GAME_DETAILS_STORAGE_PATH);
            saveFile.getParentFile().mkdirs();
            FileWriter fileWriter = new FileWriter(saveFile);
            fileWriter.write(generateGameDetailsContent(difficulty, numberOfMatches));
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            // Error saving file
        }
    }

    private static String generateGameDetailsContent(Difficulty difficulty, int numberOfMatches) {
        return String.format("%s%s%s", difficulty.name(), DIVIDER, String.valueOf(numberOfMatches));
    }

    private static void saveGameCards() {

    }


}
