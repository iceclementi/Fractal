package seedu.fractal.component.menu.button;

import javafx.scene.control.Button;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class WithLifeButton extends Button {

    /**
     * Constructor for the with life "button"
     */
    public WithLifeButton() {
        super();

        initialiseStyle();
    }

    /**
     * Activates the button.
     */
    public void activate() {
        setDisable(false);
    }

    /**
     * Deactivates the button
     */
    public void deactivate() {
        setDisable(true);
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(this, FilePath.HEART_IMAGE_PATH);
        ComponentUtil.setStyleClass(this, FilePath.MENU_STYLE_PATH, "life-count-spinner-heart");
    }

}
