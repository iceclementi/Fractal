package seedu.fractal.component.menu;

import javafx.scene.control.CheckBox;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

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
        ComponentUtil.setStyleClass(this, FilePath.MENU_STYLE_PATH, "option-checkbox");
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
