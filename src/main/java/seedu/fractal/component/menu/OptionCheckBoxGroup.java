package seedu.fractal.component.menu;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

        initialise();
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
    public HashMap<String, Boolean> getSelectedOptions() {
        HashMap<String, Boolean> advancedOptions = initialiseAdvancedOptions();

        if (fractionCheckBox.isSelected()) {
            advancedOptions.replace("fraction", true);
        }
        if (decimalCheckBox.isSelected()) {
           advancedOptions.replace("decimal", true);
        }
        if (percentageCheckBox.isSelected()) {
           advancedOptions.replace("percentage", true);
        }
        if (ratioCheckBox.isSelected()) {
           advancedOptions.replace("ratio", true);
        }
        if (partsCheckBox.isSelected()) {
           advancedOptions.replace("parts", true);
        }
        if (simplifiedCheckBox.isSelected()) {
           advancedOptions.replace("simplified", true);
        }
        if (properCheckBox.isSelected()) {
           advancedOptions.replace("proper", true);
        }

        return advancedOptions;
    }

    private void initialise() {
        leftBox = new VBox();
        rightBox = new VBox();
        leftBox.setAlignment(Pos.CENTER_LEFT);
        rightBox.setAlignment(Pos.CENTER_LEFT);
        leftBox.setSpacing(5);
        rightBox.setSpacing(5);

        fractionCheckBox = new OptionCheckBox("Fraction");
        decimalCheckBox = new OptionCheckBox("Decimal");
        percentageCheckBox = new OptionCheckBox("Percentage");
        ratioCheckBox = new OptionCheckBox("Ratio");
        partsCheckBox = new OptionCheckBox("Parts");
        leftBox.getChildren().addAll(fractionCheckBox, decimalCheckBox, percentageCheckBox,
                ratioCheckBox, partsCheckBox);

        simplifiedCheckBox = new OptionCheckBox("Simplified");
        properCheckBox = new OptionCheckBox("Proper");
        rightBox.getChildren().addAll(simplifiedCheckBox, properCheckBox);
    }

    private HashMap<String, Boolean> initialiseAdvancedOptions() {
        HashMap<String, Boolean> advancedOptions = new HashMap<>();

        advancedOptions.put("fraction", false);
        advancedOptions.put("decimal", false);
        advancedOptions.put("percentage", false);
        advancedOptions.put("ratio", false);
        advancedOptions.put("parts", false);
        advancedOptions.put("simplified", false);
        advancedOptions.put("proper", false);

        return advancedOptions;
    }
}
