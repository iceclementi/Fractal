package seedu.fractal.component.menu;

import javafx.scene.control.CheckBox;
import seedu.fractal.storage.FilePath;

public class OptionCheckBox extends CheckBox {

    private OptionCheckBoxGroup checkBoxGroup;

    /**
     * Constructor for the advanced options checkbox.
     *
     * @param label
     *  The label for the checkbox
     * @param checkBoxGroup
     *  The group in which the checkbox belongs to
     */
    public OptionCheckBox(String label, OptionCheckBoxGroup checkBoxGroup) {
        super(label);
        this.checkBoxGroup = checkBoxGroup;

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        getStylesheets().add(getClass().getResource(FilePath.MENU_STYLE_PATH).toExternalForm());
        getStyleClass().add("option-checkbox");
        setIndeterminate(false);

        selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (checkBoxGroup.getNumberOfSelectedOptions() < 2) {
                setSelected(true);
            }
        });
    }

    private void initialiseEvents() {

    }

}
