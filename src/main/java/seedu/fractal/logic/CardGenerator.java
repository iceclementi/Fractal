package seedu.fractal.logic;

import seedu.fractal.storage.FilePath;

import java.util.ArrayList;
import java.util.Collections;

public class CardGenerator {

    private static final String[] CARDS = {"fraction.png", "percentage.png", "decimal.png", "part.png", "ratio.png"};
    private static final String[] EASY_DIRECTORIES = {
            "1-2", "1-4", "3-4", "1-5", "2-5", "3-5", "4-5", "1-10", "3-10", "7-10", "9-10"};

    private int matchCount;
    private Difficulty difficulty;
    private boolean hasFraction = true;
    private boolean hasPercentage = true;
    private boolean hasDecimal = true;
    private boolean hasPart = true;
    private boolean hasRatio = true;

    /**
     * Constructor for the card generator.
     *
     * @param matchCount
     *  The number of card matches
     */
    public CardGenerator(int matchCount) {
        this.matchCount = matchCount;
    }

    /**
     * Generates the required number of cards as a list.
     *
     * @return
     *  A list of cards to be used for the game
     */
    public ArrayList<Card> generateCards() {
        ArrayList<Card> selectedCards = new ArrayList<>();

        for (String directory : selectRandomDirectories()) {
            for (Integer index : selectRandom(CARDS.length, 2)) {
                String cardName = String.format("%s_%s", directory, CARDS[index]);
                String cardValue = directory;
                String imagePath = String.format("%s/%s/%s", FilePath.CARD_EASY_PATH, directory, CARDS[index]);

                selectedCards.add(new Card(cardName, cardValue, imagePath));
            }
        }

        Collections.shuffle(selectedCards);

        return selectedCards;
    }

    /*
    public ArrayList<Card> generateCards() {
        ArrayList<Card> selectedCards = new ArrayList<>();

        // Choose 2 random cards for a card value
        for (File directory : selectRandomDirectories()) {
            File[] cards = new File(directory.getPath()).listFiles();
            assert cards != null;

            for (Integer index : selectRandom(cards.length, 2)) {
                String cardName = String.format("%s_%s", directory.getName(), cards[index].getName());
                String cardValue = directory.getName();
                String imagePath = String.format("%s/%s", FilePath.CARD_EASY_PATH, cards[index].getName());

                selectedCards.add(new Card(cardName, cardValue, imagePath));
            }
        }

        Collections.shuffle(selectedCards);

        return selectedCards;
    }
     */

    private ArrayList<String> selectRandomDirectories() {
        ArrayList<String> selectedDirectories = new ArrayList<>();

        for (Integer index : selectRandom(EASY_DIRECTORIES.length, matchCount)) {
            selectedDirectories.add(EASY_DIRECTORIES[index]);
        }

        return selectedDirectories;
    }

    /*
    private ArrayList<File> selectRandomDirectories() {
        File[] cardDirectories = new File(getClass().getResource(FilePath.CARD_EASY_PATH).getFile()).listFiles();
        assert cardDirectories != null : "No cards found.";
        System.out.println(Arrays.toString(cardDirectories));

        ArrayList<File> selectedDirectories = new ArrayList<>();
        for (Integer index : selectRandom(cardDirectories.length, matchCount)) {
            selectedDirectories.add(cardDirectories[index]);
        }

        return selectedDirectories;
    }
     */

    /**
     * Selects n random numbers from the range 0 inclusive to the upper limit exclusive.
     *
     * @param upperLimit
     *  The upper limit of the number range
     * @param n
     *  The number of random numbers to be selected
     * @return
     *  A list of n random numbers
     */
    private ArrayList<Integer> selectRandom(int upperLimit, int n) {
        assert n <= upperLimit : "Cannot select sufficient numbers.";

        ArrayList<Integer> randomNumbers = new ArrayList<>();
        for (int index = 0; index < upperLimit; ++index) {
            randomNumbers.add(index);
        }

        Collections.shuffle(randomNumbers);
        randomNumbers.subList(n, upperLimit).clear();

        return randomNumbers;
    }
}
