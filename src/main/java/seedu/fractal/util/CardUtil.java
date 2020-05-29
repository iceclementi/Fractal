package seedu.fractal.util;

import seedu.fractal.logic.CardType;

import java.util.HashMap;

public class CardUtil {
    public static final String FRACTION = "fraction";
    public static final String DECIMAL = "decimal";
    public static final String PERCENTAGE = "percentage";
    public static final String RATIO = "ratio";
    public static final String PARTS = "parts";
    public static final String SIMPLIFIED = "simplified";
    public static final String PROPER = "proper";

    public static final CardType[] NORMAL_CARD_TYPES = {CardType.FRACTION, CardType.DECIMAL,
        CardType.PERCENTAGE, CardType.RATIO, CardType.PART};
    public static final CardType[] EXTRA_CARD_TYPES = {CardType.SIMPLIFIED, CardType.PROPER};

    public static final HashMap<String, Integer> FROM_CARD_TYPE = new HashMap<>() {
        {
            put(FRACTION, 0);
            put(DECIMAL, 1);
            put(PERCENTAGE, 2);
            put(RATIO, 3);
            put(PARTS, 4);
            put(SIMPLIFIED, 5);
            put(PROPER, 6);
        }
    };

    public static final HashMap<Integer, String> TO_CARD_TYPE = new HashMap<>() {
        {
            put(0, FRACTION);
            put(1, DECIMAL);
            put(2, PERCENTAGE);
            put(3, RATIO);
            put(4, PARTS);
            put(5, SIMPLIFIED);
            put(6, PROPER);
        }
    };
}
