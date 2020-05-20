package seedu.fractal.component.menu.button;

import seedu.fractal.storage.FilePath;

public class AboutButton extends MenuButton{

    public AboutButton() {
        super();

        initialiseStyle();
    }

    private void initialiseStyle() {
        setButtonBackground(FilePath.ABOUT_BUTTON_IMAGE_PATH);
    }
}
