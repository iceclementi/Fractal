package seedu.fractal.component.menu.button;

import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class HelpButton extends MenuButton {

    public HelpButton() {
        super();

        initialiseStyle();
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(this, FilePath.HELP_BUTTON_IMAGE_PATH);
    }

}
