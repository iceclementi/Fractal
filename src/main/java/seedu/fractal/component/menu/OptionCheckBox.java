package seedu.fractal.component.menu;

import javafx.scene.control.CheckBox;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class OptionCheckBox extends CheckBox {

    private OptionCheckBoxGroup checkBoxGroup;
    private GameModeToggle gameModeToggle;
    private boolean isDefaultSelected;

    /**
     * Constructor for the advanced options checkbox.
     *
     * @param checkBoxGroup
     *  The group in which the checkbox belongs to
     * @param isDefaultSelected
     *  The default selection status of the checkbox
     * @param label
     *  The label for the checkbox
     */
    public OptionCheckBox(OptionCheckBoxGroup checkBoxGroup, GameModeToggle gameModeToggle,
            boolean isDefaultSelected, String label) {
        super(label);
        this.checkBoxGroup = checkBoxGroup;
        this.gameModeToggle = gameModeToggle;
        this.isDefaultSelected = isDefaultSelected;

        initialiseStyle();
        initialiseEvents();
    }

    /**
     * Sets the checkbox to the default normal mode option.
     */
    public void setNormalMode() {
        setSelected(isDefaultSelected);
    }

    private void initialiseStyle() {
        ComponentUtil.setStyleClass(this, FilePath.MENU_STYLE_PATH, "option-checkbox");
        setIndeterminate(false);

    }

    private void initialiseEvents() {
        selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (checkBoxGroup.getNumberOfSelectedOptions() < 2) {
                setSelected(true);
            }

            if (newValue != isDefaultSelected) {
                gameModeToggle.togglePracticeMode();
            }
        });
    }

}
