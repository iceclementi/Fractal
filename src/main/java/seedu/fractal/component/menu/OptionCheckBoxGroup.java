package seedu.fractal.component.menu;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.fractal.component.game.GameBoard;
import seedu.fractal.logic.CardType;

import java.util.HashMap;

public class OptionCheckBoxGroup {

    private VBox leftBox;
    private VBox rightBox;
    private OptionCheckBox fractionCheckBox;
    private OptionCheckBox decimalCheckBox;
    private OptionCheckBox percentageCheckBox;
    private OptionCheckBox ratioCheckBox;
    private OptionCheckBox partsCheckBox;
    private OptionCheckBox simplifiedCheckBox;
    private OptionCheckBox properCheckBox;

    private HBox cardTypeOptionBox;
    private GameModeToggle gameModeToggle;

    /**
     * Constructor for the advanced options checkbox group.
     *
     * @param cardTypeOptionBox
     *  The horizontal box of the advanced options section
     * @param gameModeToggle
     *  The game mode toggle on the new game popup
     */
    public OptionCheckBoxGroup(HBox cardTypeOptionBox, GameModeToggle gameModeToggle) {
        this.cardTypeOptionBox = cardTypeOptionBox;
        this.gameModeToggle = gameModeToggle;

        initialiseStyle();
        initialiseCardTypeOptions();
    }

    /**
     * Gets the total number of selected options from the checkboxes.
     *
     * @return
     *  The total number of selected options from the checkboxes
     */
    public int getNumberOfSelectedOptions() {
        int numberOfSelectedOptions = 0;

        if (fractionCheckBox.isSelected()) {
            ++numberOfSelectedOptions;
        }
        if (decimalCheckBox.isSelected()) {
            ++numberOfSelectedOptions;
        }
        if (percentageCheckBox.isSelected()) {
            ++numberOfSelectedOptions;
        }
        if (ratioCheckBox.isSelected()) {
            ++numberOfSelectedOptions;
        }
        if (partsCheckBox.isSelected()) {
            ++numberOfSelectedOptions;
        }

        return numberOfSelectedOptions;
    }

    /**
     * Fills the checkbox group with checkboxes.
     */
    public void fillGroup() {
        cardTypeOptionBox.getChildren().addAll(leftBox, rightBox);
    }

    /**
     * Retrieves the selected options from the checkboxes.
     *
     * @return
     *  A mapping of the box to its corresponding selected value
     */
    public HashMap<CardType, Boolean> getSelectedOptions() {
        HashMap<CardType, Boolean> advancedOptions = new HashMap<>();

        advancedOptions.put(CardType.FRACTION, fractionCheckBox.isSelected());
        advancedOptions.put(CardType.DECIMAL, decimalCheckBox.isSelected());
        advancedOptions.put(CardType.PERCENTAGE, percentageCheckBox.isSelected());
        advancedOptions.put(CardType.RATIO, ratioCheckBox.isSelected());
        advancedOptions.put(CardType.PART, partsCheckBox.isSelected());
        advancedOptions.put(CardType.SIMPLIFIED, simplifiedCheckBox.isSelected());
        advancedOptions.put(CardType.PROPER, properCheckBox.isSelected());

        return advancedOptions;
    }

    /**
     * Sets the checkboxes to the default normal mode options.
     */
    public void setNormalMode() {
        fractionCheckBox.setNormalMode();
        decimalCheckBox.setNormalMode();
        percentageCheckBox.setNormalMode();
        ratioCheckBox.setNormalMode();
        partsCheckBox.setNormalMode();
        simplifiedCheckBox.setNormalMode();
        properCheckBox.setNormalMode();
    }

    private void initialiseStyle() {
        leftBox = new VBox();
        rightBox = new VBox();
        leftBox.setAlignment(Pos.CENTER_LEFT);
        rightBox.setAlignment(Pos.CENTER_LEFT);
        leftBox.setSpacing(5);
        rightBox.setSpacing(5);

        fractionCheckBox = new OptionCheckBox(this, gameModeToggle, true, "Fraction");
        decimalCheckBox = new OptionCheckBox(this, gameModeToggle, true, "Decimal");
        percentageCheckBox = new OptionCheckBox(this, gameModeToggle, true, "Percentage");
        ratioCheckBox = new OptionCheckBox(this, gameModeToggle, true, "Ratio");
        partsCheckBox = new OptionCheckBox(this, gameModeToggle, true, "Parts");
        leftBox.getChildren().addAll(fractionCheckBox, decimalCheckBox, percentageCheckBox,
                ratioCheckBox, partsCheckBox);

        simplifiedCheckBox = new OptionCheckBox(this, gameModeToggle, false, "Simplified");
        properCheckBox = new OptionCheckBox(this, gameModeToggle, true, "Proper");
        properCheckBox.setDisable(true);
        rightBox.getChildren().addAll(simplifiedCheckBox, properCheckBox);
    }

    private void initialiseCardTypeOptions() {
        HashMap<CardType, Boolean> advancedOptions = GameBoard.getInstance().getCardTypeOptions();

        fractionCheckBox.setSelected(advancedOptions.get(CardType.FRACTION));
        decimalCheckBox.setSelected(advancedOptions.get(CardType.DECIMAL));
        percentageCheckBox.setSelected(advancedOptions.get(CardType.PERCENTAGE));
        ratioCheckBox.setSelected(advancedOptions.get(CardType.RATIO));
        partsCheckBox.setSelected(advancedOptions.get(CardType.PART));
        simplifiedCheckBox.setSelected(advancedOptions.get(CardType.SIMPLIFIED));
        // properCheckBox.setSelected(advancedOptions.get(CardType.PROPER));
        properCheckBox.setSelected(true);
    }
}
