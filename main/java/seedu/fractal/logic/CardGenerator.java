package seedu.fractal.logic;

import java.util.HashSet;

public class CardGenerator {

    private int matchCount;
    private String difficulty;
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

    public HashSet<Card> generateCards() {
        HashSet<Card> cardSet = new HashSet<>();



        return cardSet;
    }



}
