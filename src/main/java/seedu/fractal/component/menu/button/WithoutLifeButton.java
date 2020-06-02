package seedu.fractal.component.menu.button;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import seedu.fractal.component.menu.LifeCountSpinner;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class WithoutLifeButton extends Button {

    private LifeCountSpinner lifeCountSpinner;

    /**
     * Constructor for the without life button.
     */
    public WithoutLifeButton(LifeCountSpinner lifeCountSpinner) {
        super();
        this.lifeCountSpinner = lifeCountSpinner;

        initialiseStyle();
        initialiseEvents();
    }

    /**
     * Activates the button.
     */
    public void activate() {
        setOpacity(1);
    }

    /**
     * Deactivates the button
     */
    public void deactivate() {
        setOpacity(0.5);
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(this, FilePath.CROSS_IMAGE_PATH);
        ComponentUtil.setStyleClass(this, FilePath.MENU_STYLE_PATH, "life-count-spinner-cross");
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {
        if (lifeCountSpinner.getValueFactory().getValue() != 0) {
            lifeCountSpinner.getValueFactory().setValue(0);
        }

        activate();
    }
}
