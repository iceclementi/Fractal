package seedu.fractal.component.menu;

import javafx.scene.control.CheckBox;
import seedu.fractal.storage.FilePath;

public class OptionCheckBox extends CheckBox {

    public OptionCheckBox(String label) {
        super(label);

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        getStylesheets().add(getClass().getResource(FilePath.MENU_STYLE_PATH).toExternalForm());
        getStyleClass().add("option-checkbox");
        setIndeterminate(false);
    }

    private void initialiseEvents() {

    }

}
