package seedu.fractal.component.menu.button;

import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class AboutButton extends MenuButton{

    public AboutButton() {
        super();

        initialiseStyle();
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(this, FilePath.ABOUT_BUTTON_IMAGE_PATH);
    }
}
