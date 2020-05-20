package seedu.fractal.component.menu.button;

import seedu.fractal.storage.FilePath;

public class HelpButton extends MenuButton {

    public HelpButton() {
        super();

        initialiseStyle();
    }

    private void initialiseStyle() {
        setButtonBackground(FilePath.HELP_BUTTON_IMAGE_PATH);
    }

}
