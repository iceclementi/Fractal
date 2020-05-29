package seedu.fractal.logic;

import seedu.fractal.storage.FilePath;
import seedu.fractal.util.CardUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Pattern;

public class CardGenerator {

    private static final String[] EASY_CARD_VALUES = {
        "1-2", "1-4", "3-4", "1-5", "2-5", "3-5", "4-5", "1-10", "3-10", "7-10", "9-10"};
    private static final String[] INTERMEDIATE_CARD_VALUES = {
        "1-3", "2-3", "1-8", "3-8", "5-8", "7-8"};
    private static final String[] ADVANCED_CARD_VALUES = {
        "1-6", "5-6", "1-9", "2-9", "4-9", "5-9", "7-9", "8-9"};
    private static final String[] GENIUS_CARD_VALUES = {
        "1-7", "2-7", "3-7", "4-7", "5-7", "6-7"};

    private static final String[] INTERMEDIATE_BIG_CARD_VALUES = {
        "1-20", "3-20", "7-20", "9-20", "11-20", "13-20", "17-20", "19-20"};
    private static final String[] ADVANCED_BIG_CARD_VALUES = {
        "1-50", "3-50", "7-50", "9-50", "11-50", "13-50", "17-50", "19-50", "21-50", "23-50", "27-50", "29-50",
        "31-50", "33-50", "37-50", "39-50", "41-50", "43-50", "47-50", "49-50"};
    private static final String[] GENIUS_BIG_CARD_VALUES = {
        "1-25", "2-25", "3-25", "4-25", "6-25", "7-25", "8-25", "9-25", "11-25", "12-25", "13-25", "14-25",
        "16-25", "17-25", "18-25", "19-25",   "21-25", "22-25", "23-25", "24-25",
        "1-40", "3-40", "7-40", "9-40", "11-40", "13-40", "17-40", "19-40", "21-40", "23-40", "27-40", "29-40",
        "31-40", "33-40", "37-40", "39-40"};

    private ArrayList<String> cardTypes = new ArrayList<>();
    private ArrayList<String> easyCardValues = new ArrayList<>();
    private ArrayList<String> intermediateCardValues = new ArrayList<>();
    private ArrayList<String> advancedCardValues = new ArrayList<>();
    private ArrayList<String> geniusCardValues = new ArrayList<>();

    private Difficulty difficulty;
    private int numberOfMatches;
    private HashMap<CardType, Boolean> advancedOptions;

    /**
     * Constructor for the card generator.
     *
     * @param difficulty
     *  The difficulty of the game
     * @param numberOfMatches
     *  The number of card matches
     * @param advancedOptions
     *  The advanced options to determine the type of cards to generate
     */
    public CardGenerator(Difficulty difficulty, int numberOfMatches, HashMap<CardType, Boolean> advancedOptions) {
        this.difficulty = difficulty;
        this.numberOfMatches = numberOfMatches;
        this.advancedOptions = advancedOptions;

        initialiseCardTypes();
        initialiseCardValues();
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
            cards = generateEasyCards(numberOfMatches);
            break;
        case INTERMEDIATE:
            cards = generateIntermediateCards(numberOfMatches, true);
            break;
        case ADVANCED:
            cards = generateAdvancedCards(numberOfMatches, true);
            break;
        case GENIUS:
            cards = generateGeniusCards(numberOfMatches);
            break;
        default:
            System.out.println("CardGenerator: Unable to generate cards of specified difficulty.\n"
                    + "Generating easy cards instead...");
            cards = generateEasyCards(numberOfMatches);
        }

        Collections.shuffle(cards);

        return cards;
    }

    /**
     * Initialises the list of card types to choose from for the game.
     */
    private void initialiseCardTypes() {
        for (CardType cardType : CardUtil.NORMAL_CARD_TYPES) {
            if (advancedOptions.get(cardType)) {
                cardTypes.add(cardType.name().toLowerCase());
            }
        }

        assert cardTypes.size() >= 2 : "CardGenerator: Insufficient types of cards.";
    }

    /**
     * Initialises the list of card values to choose from for the game.
     */
    private void initialiseCardValues() {
        easyCardValues.addAll(Arrays.asList(EASY_CARD_VALUES));
        intermediateCardValues.addAll(Arrays.asList(INTERMEDIATE_CARD_VALUES));
        advancedCardValues.addAll(Arrays.asList(ADVANCED_CARD_VALUES));
        geniusCardValues.addAll(Arrays.asList(GENIUS_CARD_VALUES));

        if (isBigNumberPossible()) {
            intermediateCardValues.addAll(Arrays.asList(INTERMEDIATE_BIG_CARD_VALUES));
            advancedCardValues.addAll(Arrays.asList(ADVANCED_BIG_CARD_VALUES));
            geniusCardValues.addAll(Arrays.asList(GENIUS_BIG_CARD_VALUES));
        }
    }

    /**
     * Checks if a card value with denominator greater than 10 is possible.
     *
     * @return
     *  True if a card value with denominator greater than 10 is possible, or false otherwise
     */
    private boolean isBigNumberPossible() {
        // No cards with part card type for big card values
        return !(cardTypes.size() == 2 && cardTypes.contains(CardType.PART.name().toLowerCase()));
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
        return generatePreferredCardList(easyCardValues, numberOfMatches, numberOfMatches, FilePath.CARD_EASY_PATH);
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

        ArrayList<Card> selectedIntermediateCards = generatePreferredCardList(intermediateCardValues,
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

        ArrayList<Card> selectedAdvancedCards = generatePreferredCardList(advancedCardValues,
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

        ArrayList<Card> selectedGeniusCards = generatePreferredCardList(geniusCardValues,
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
            ArrayList<String> cardValues, int preferredLowerLimit, int upperLimit, String difficultyPath) {
        int cardCount = selectBestRandomNumber(preferredLowerLimit, upperLimit, cardValues.size());
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
            ArrayList<String> supportedCardTypes = generateSupportedCardTypeList(cardValue);
            for (Integer index : selectRandomNumberList(supportedCardTypes.size(), 2)) {
                if (advancedOptions.get(CardType.SIMPLIFIED)) {
                    String cardName = String.format("%s_%s", cardValue, supportedCardTypes.get(index));
                    String imagePath = String.format("%s/%s/%s.png",
                            difficultyPath, cardValue, supportedCardTypes.get(index));

                    cardList.add(new Card(cardName, cardValue, imagePath));
                } else {
                    String unsimplifiedCardValue = selectUnsimplified(cardValue, supportedCardTypes.get(index));
                    String cardName = String.format("%s_%s", cardValue, unsimplifiedCardValue);
                    String imagePath = String.format("%s/%s/%s.png", difficultyPath, cardValue, unsimplifiedCardValue);

                    cardList.add(new Card(cardName, cardValue, imagePath));
                }
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
    private ArrayList<String> selectRandomCardValues(ArrayList<String> cardValues, int count) {
        ArrayList<String> selectedCardValues = new ArrayList<>();

        for (Integer index : selectRandomNumberList(cardValues.size(), count)) {
            selectedCardValues.add(cardValues.get(index));
        }

        return selectedCardValues;
    }

    /**
     * Generates the list of card types supported based on the card value.
     *
     * @param cardValue
     *  The value of the card
     * @return
     *  The correct list of card types that the card value supports
     */
    private ArrayList<String> generateSupportedCardTypeList(String cardValue) {
        if (isBigNumber(cardValue)) {
            ArrayList<String> correctCardTypeList = cardTypes;
            correctCardTypeList.remove(CardType.PART.name().toLowerCase());

            return correctCardTypeList;
        } else {
            return cardTypes;
        }
    }

    /**
     * Checks if a given card value has a denominator greater than 10.
     *
     * @param cardValue
     *  The value of the card to be checked
     * @return
     *  True if its denominator is greater than 10, or false otherwise
     */
    private boolean isBigNumber(String cardValue) {
        String[] splitValue = cardValue.split(Pattern.quote("-"));
        assert splitValue.length == 2 : "CardGenerator: Length of split card value must be 2.";

        int denominator = Integer.parseInt(splitValue[1]);

        return denominator > 10;
    }

    /**
     * Selects the unsimplified version of the card value if possible.
     *
     * @param cardValue
     *  The value of the card to be selected
     * @param cardType
     *  The type of card to be selected
     * @return
     *  The unsimplified version of the card value if possible, otherwise the simplified card value
     */
    private String selectUnsimplified(String cardValue, String cardType) {
        switch (CardType.valueOf(cardType.toUpperCase())) {
        case FRACTION:
        case RATIO:
        case PART:
            return selectRandomUnsimplified(cardValue, cardType);

        case DECIMAL:
        case PERCENTAGE:
            return cardType;

        default:
            System.out.println("CardGenerator: Unknown card type... Using original card type.");
            return cardType;
        }
    }

    /**
     * Selects a random unsimplified version of the card value at 80% probability.
     *
     * @param cardValue
     *  The value of the card to be selected
     * @param cardType
     *  The type of card to be selected
     * @return
     *  A random unsimplified version of the card value, otherwise the simplified card value
     */
    private String selectRandomUnsimplified(String cardValue, String cardType) {
        /* 25% chance of being simplified */
        if (selectRandomNumber(0, 4) == 0) {
            return cardType;
        }

        String[] splitValue = cardValue.split(Pattern.quote("-"));
        assert splitValue.length == 2 : "CardGenerator: Length of split card value must be 2.";

        int numerator = Integer.parseInt(splitValue[0]);
        int denominator = Integer.parseInt(splitValue[1]);

        if (denominator > 5) {
            return cardType;
        } else {
            int multiplier = selectRandomNumber(1, 10 / denominator);

            if (multiplier == 1) {
                return cardType;
            } else {
                return String.format("%s_%d-%d", cardType, numerator * multiplier, denominator * multiplier);
            }
        }
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
    private ArrayList<Integer> selectRandomNumberList(int upperLimit, int n) {
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
