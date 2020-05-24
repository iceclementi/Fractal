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
    private static final String[] ADVANCED_CARD_VALUES = {
            "1-6", "5-6", "1-9", "2-9", "4-9", "5-9", "7-9", "8-9"};
    private static final String[] GENIUS_CARD_VALUES = {
            "1-7", "2-7", "3-7", "4-7", "5-7", "6-7"};

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
        ArrayList<Card> cards;

        switch (difficulty) {
        case EASY:
            cards = generateEasyCards(matchCount);
            break;
        case INTERMEDIATE:
            cards = generateIntermediateCards(matchCount, true);
            break;
        case ADVANCED:
            cards = generateAdvancedCards(matchCount, true);
            break;
        case GENIUS:
            cards = generateGeniusCards(matchCount);
            break;
        default:
            System.out.println("CardGenerator: Unable to generate cards of specified difficulty.\n"
                    + "Generating easy cards instead...");
            cards = generateEasyCards(matchCount);
        }

        Collections.shuffle(cards);

        return cards;
    }

    /**
     * Generates the required number of cards as a list for Easy difficulty.
     *
     * @param numberOfMatches
     *  The number of matches for easy cards
     * @return
     *  A list of cards to be used for the game
     */
    private ArrayList<Card> generateEasyCards(int numberOfMatches) {
        return generatePreferredCardList(EASY_CARD_VALUES, numberOfMatches, numberOfMatches, FilePath.CARD_EASY_PATH);
    }

    /**
     * Generates the required number of cards as a list for Intermediate difficulty.
     *
     * @param numberOfMatches
     *  The number of matches for easy and intermediate cards
     * @param isNotZero
     *  Indicates if the number of intermediate cards can be zero
     * @return
     *  A list of cards to be used for the game
     */
    private ArrayList<Card> generateIntermediateCards(int numberOfMatches, boolean isNotZero) {
        // At least one-third of cards should be of intermediate level if possible
        int lowerLimit = (numberOfMatches / 3 == 0 && isNotZero) ? 1 : numberOfMatches / 3;

        ArrayList<Card> selectedIntermediateCards = generatePreferredCardList(INTERMEDIATE_CARD_VALUES,
                lowerLimit, numberOfMatches, FilePath.CARD_INTERMEDIATE_PATH);

        int easyCardsCount = numberOfMatches - selectedIntermediateCards.size() / 2;
        assert easyCardsCount >= 0 : "CardGenerator: Card count has become negative.";

        ArrayList<Card> selectedCards = new ArrayList<>();
        selectedCards.addAll(selectedIntermediateCards);
        selectedCards.addAll(generateEasyCards(easyCardsCount));

        return selectedCards;
    }

    /**
     * Generates the required number of cards as a list for Advanced difficulty.
     *
     * @param numberOfMatches
     *  The number of matches for easy, intermediate and advanced cards
     * @param isNotZero
     *  Indicates if the number of advanced cards can be zero
     * @return
     *  A list of cards to be used for the game
     */
    private ArrayList<Card> generateAdvancedCards(int numberOfMatches, boolean isNotZero) {
        // At least one-fourth of cards should be of advanced level if possible and greater than 0
        int lowerLimit = (numberOfMatches / 4 == 0 && isNotZero) ? 1 : numberOfMatches / 4;

        ArrayList<Card> selectedAdvancedCards = generatePreferredCardList(ADVANCED_CARD_VALUES,
                lowerLimit, numberOfMatches, FilePath.CARD_ADVANCED_PATH);

        int intermediateCardsCount = numberOfMatches - selectedAdvancedCards.size() / 2;
        assert intermediateCardsCount >= 0 : "CardGenerator: Card count has become negative.";

        ArrayList<Card> selectedCards = new ArrayList<>();
        selectedCards.addAll(selectedAdvancedCards);
        selectedCards.addAll(generateIntermediateCards(intermediateCardsCount, false));

        return selectedCards;
    }

    /**
     * Generates the required number of cards as a list for Genius difficulty.
     *
     * @param numberOfMatches
     *  The number of matches for easy, intermediate, advanced and genius cards
     * @return
     *  A list of cards to be used for the game
     */
    private ArrayList<Card> generateGeniusCards(int numberOfMatches) {
        // At least one-fifth of cards should be of advanced level if possible and greater than 0
        int lowerLimit = (numberOfMatches / 5 == 0) ? 1 : numberOfMatches / 5;

        ArrayList<Card> selectedGeniusCards = generatePreferredCardList(GENIUS_CARD_VALUES,
                lowerLimit, numberOfMatches, FilePath.CARD_GENIUS_PATH);

        int advancedCardsCount = numberOfMatches - selectedGeniusCards.size() / 2;
        assert advancedCardsCount >= 0 : "CardGenerator: Card count has become negative.";

        ArrayList<Card> selectedCards = new ArrayList<>();
        selectedCards.addAll(selectedGeniusCards);
        selectedCards.addAll(generateAdvancedCards(advancedCardsCount, false));

        return selectedCards;
    }

    /**
     * Generates a list of a random number of cards within the given range and the card values given.
     *
     * @param cardValues
     *  The list of card values to be randomly selected
     * @param preferredLowerLimit
     *  The preferred lower limit of the range
     * @param upperLimit
     *  The upper limit of the range
     * @param difficultyPath
     *  The directory patht ot the difficulty level of the card
     * @return
     *  A list of constructed cards with a size that is preferably within the range
     */
    private ArrayList<Card> generatePreferredCardList(
            String[] cardValues, int preferredLowerLimit, int upperLimit, String difficultyPath) {
        int cardCount = selectBestRandomNumber(preferredLowerLimit, upperLimit, cardValues.length);
        ArrayList<String> selectedCardValues = selectRandomCardValues(cardValues, cardCount);

        return generateCardList(selectedCardValues, difficultyPath);
    }

    /**
     * Generates a list of cards from the card values given.
     *
     * @param cardValues
     *  The list of card values to be randomly selected
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
     *  The list of card values to be randomly selected
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
     * Selects a random number within the given preferred range if possible, otherwise selects the next best number.
     *
     * @param preferredLowerLimit
     *  The preferred lower limit of the range
     * @param upperLimit
     *  The upper limit of the range
     * @param numberOfCardValues
     *  The number of possible card values available
     * @return
     *  A random integer within the given preferred range if possible, or the next best number otherwise
     */
    private int selectBestRandomNumber(int preferredLowerLimit, int upperLimit, int numberOfCardValues) {
        assert preferredLowerLimit < upperLimit : "CardGenerator: Minimum is greater than upper limit.";

        int cardsLowerLimit = Math.min(preferredLowerLimit, numberOfCardValues);
        int cardsUpperLimit = Math.min(numberOfCardValues, upperLimit);

        return selectRandomNumber(cardsLowerLimit, cardsUpperLimit);
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
