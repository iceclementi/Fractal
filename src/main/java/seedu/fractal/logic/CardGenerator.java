package seedu.fractal.logic;

import seedu.fractal.storage.FilePath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CardGenerator {

    private static final String[] CARD_TYPES = {"fraction.png", "percentage.png", "decimal.png", "part.png", "ratio.png"};
    private static final String[] EASY_CARD_VALUES = {
            "1-2", "1-4", "3-4", "1-5", "2-5", "3-5", "4-5", "1-10", "3-10", "7-10", "9-10"};
    private static final String[] INTERMEDIATE_CARD_VALUES = {
            "1-3", "2-3", "1-8", "3-8", "5-8", "7-8"};

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
     * @param difficulty
     *  The difficulty of the game
     */
    public CardGenerator(int matchCount, Difficulty difficulty) {
        this.matchCount = matchCount;
        this.difficulty = difficulty;
    }

    /**
     * Generates the required number of cards as a list.
     *
     * @return
     *  A list of cards to be used for the game
     */
    public ArrayList<Card> generateCards() {
        switch (difficulty) {
        case EASY:
            return generateEasyCards();
        case INTERMEDIATE:
            return generateIntermediateCards();
        default:
            System.out.println("CardGenerator: Unable to generate cards of specified difficulty.\n"
                    + "Generating easy cards instead...");
            return generateEasyCards();
        }
    }

    /**
     * Generates the required number of cards as a list for Easy difficulty.
     *
     * @return
     *  A list of cards to be used for the game
     */
    private ArrayList<Card> generateEasyCards() {
        ArrayList<String> easyCardValues = selectRandomCardValues(EASY_CARD_VALUES, matchCount);
        ArrayList<Card> selectedCards = generateCardList(easyCardValues, FilePath.CARD_EASY_PATH);

        Collections.shuffle(selectedCards);

        return selectedCards;
    }

    /**
     * Generates the required number of cards as a list for Intermediate difficulty.
     *
     * @return
     *  A list of cards to be used for the game
     */
    private ArrayList<Card> generateIntermediateCards() {
        // At least one-third of cards should be of intermediate level if possible and greater than 0
        int intermediateCardsLowerLimit =
                Math.min((int) Math.ceil(matchCount / 3.0), INTERMEDIATE_CARD_VALUES.length);
        int intermediateCardsUpperLimit = Math.min(matchCount, INTERMEDIATE_CARD_VALUES.length);

        int intermediateCardsCount = selectRandomNumber(intermediateCardsLowerLimit, intermediateCardsUpperLimit);
        int easyCardsCount = matchCount - intermediateCardsCount;

        ArrayList<Card> selectedCards = new ArrayList<>();
        ArrayList<String> intermediateCardValues =
                selectRandomCardValues(INTERMEDIATE_CARD_VALUES, intermediateCardsCount);
        ArrayList<String> easyCardValues = selectRandomCardValues(EASY_CARD_VALUES, easyCardsCount);
        selectedCards.addAll(generateCardList(intermediateCardValues, FilePath.CARD_INTERMEDIATE_PATH));
        selectedCards.addAll(generateCardList(easyCardValues, FilePath.CARD_EASY_PATH));

        Collections.shuffle(selectedCards);

        return selectedCards;
    }

    /**
     * Generates a list of cards from the card values given.
     *
     * @param cardValues
     *  The value of the card
     * @param difficultyPath
     *  The directory path to the difficulty level of the card
     * @return
     *  A list of constructed cards
     */
    private ArrayList<Card> generateCardList(ArrayList<String> cardValues, String difficultyPath) {
        ArrayList<Card> cardList = new ArrayList<>();

        for (String cardValue : cardValues) {
            for (Integer index : selectRandom(CARD_TYPES.length, 2)) {
                String cardName = String.format("%s_%s", cardValue, CARD_TYPES[index]);
                String imagePath = String.format("%s/%s/%s", difficultyPath, cardValue, CARD_TYPES[index]);

                cardList.add(new Card(cardName, cardValue, imagePath));
            }
        }

        return cardList;
    }

    /**
     * Selects a given number of random card values from a list.
     *
     * @param cardValues
     *  The list of card values to be selected randomly
     * @param count
     *  The number of card values to be selected
     * @return
     *  A list of the given number of random card values
     */
    private ArrayList<String> selectRandomCardValues(String[] cardValues, int count) {
        ArrayList<String> selectedCardValues = new ArrayList<>();

        for (Integer index : selectRandom(cardValues.length, count)) {
            selectedCardValues.add(cardValues[index]);
        }

        return selectedCardValues;
    }

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

    /**
     * Selects a random integer between the range of the lower and upper limits inclusive.
     *
     * @param lowerLimit
     *  The lower limit of the range
     * @param upperLimit
     *  The upper limit of the range
     * @return
     *  A random integer within the range
     */
    private int selectRandomNumber(int lowerLimit, int upperLimit) {
        assert lowerLimit >= 0 : "Lower limit needs to be greater than 0";
        assert lowerLimit <= upperLimit : "Lower limit is not less than upper limit";

        int range = upperLimit - lowerLimit + 1;

        return lowerLimit +  new Random().nextInt(range);
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
}
