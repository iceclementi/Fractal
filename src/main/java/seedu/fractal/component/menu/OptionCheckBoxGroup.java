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

    private HBox advancedOptionsBox;

    /**
     * Constructor for the advanced options checkbox group.
     *
     * @param advancedOptionsBox
     *  The horizontal box of the advanced options section
     */
    public OptionCheckBoxGroup(HBox advancedOptionsBox) {
        this.advancedOptionsBox = advancedOptionsBox;

        initialiseStyle();
        initialiseAdvancedOptions();
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
        advancedOptionsBox.getChildren().addAll(leftBox, rightBox);
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
        advancedOptions.put(CardType.PARTS, partsCheckBox.isSelected());
        advancedOptions.put(CardType.SIMPLIFIED, simplifiedCheckBox.isSelected());
        advancedOptions.put(CardType.PROPER, properCheckBox.isSelected());

        return advancedOptions;
    }

    private void initialiseStyle() {
        leftBox = new VBox();
        rightBox = new VBox();
        leftBox.setAlignment(Pos.CENTER_LEFT);
        rightBox.setAlignment(Pos.CENTER_LEFT);
        leftBox.setSpacing(5);
        rightBox.setSpacing(5);

        fractionCheckBox = new OptionCheckBox("Fraction", this);
        decimalCheckBox = new OptionCheckBox("Decimal", this);
        percentageCheckBox = new OptionCheckBox("Percentage", this);
        ratioCheckBox = new OptionCheckBox("Ratio", this);
        partsCheckBox = new OptionCheckBox("Parts", this);
        leftBox.getChildren().addAll(fractionCheckBox, decimalCheckBox, percentageCheckBox,
                ratioCheckBox, partsCheckBox);

        simplifiedCheckBox = new OptionCheckBox("Simplified", this);
        properCheckBox = new OptionCheckBox("Proper", this);
        simplifiedCheckBox.setDisable(true);
        properCheckBox.setDisable(true);
        rightBox.getChildren().addAll(simplifiedCheckBox, properCheckBox);
    }

    private void initialiseAdvancedOptions() {
        HashMap<CardType, Boolean> advancedOptions = GameBoard.getInstance().getAdvancedOptions();

        fractionCheckBox.setSelected(advancedOptions.get(CardType.FRACTION));
        decimalCheckBox.setSelected(advancedOptions.get(CardType.DECIMAL));
        percentageCheckBox.setSelected(advancedOptions.get(CardType.PERCENTAGE));
        ratioCheckBox.setSelected(advancedOptions.get(CardType.RATIO));
        partsCheckBox.setSelected(advancedOptions.get(CardType.PARTS));
        simplifiedCheckBox.setSelected(advancedOptions.get(CardType.SIMPLIFIED));
        properCheckBox.setSelected(advancedOptions.get(CardType.PROPER));
    }
}
