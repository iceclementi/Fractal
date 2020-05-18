package seedu.fractal.logic;

public class Card {
    private String name;
    private String value;
    private String imagePath;

    /**
     * Constructor for a math memory card.
     *
     * @param name
     *  The name of the card
     * @param value
     *  The value of the card for comparison
     * @param imagePath
     *  The path to the card's image
     */
    public Card(String name, String value, String imagePath) {
        this.name = name;
        this.value = value;
        this.imagePath = imagePath;
    }

    /**
     * Checks if the card is the same value as another card.
     *
     * @param another
     *  The other card
     * @return
     *  TRUE if both cards are the same value, or FALSE otherwise
     */
    public boolean isSameValue(Card another) {
        return value.equals(another.value);
    }
}
