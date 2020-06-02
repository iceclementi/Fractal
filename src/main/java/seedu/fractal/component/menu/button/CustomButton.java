package seedu.fractal.component.menu.button;

import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public abstract class CustomButton extends Button {

    /**
     * Constructor for a custom button.
     */
    public CustomButton() {
        super();

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        ComponentUtil.setStyleClass(this, FilePath.MENU_STYLE_PATH, "custom-button");

        setPrefSize(400, 67);
    }

    private void initialiseEvents() {
        setOnMouseEntered(this::onHover);
        setOnMouseExited(this::onUnhover);
        setOnMousePressed(this::onClick);
    }

    private void onHover(MouseEvent mouseEvent) {
        setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(99, 133, 171), 10, 0, 0, 0));
    }

    private void onUnhover(MouseEvent mouseEvent) {
        reset();
    }

    private void onClick(MouseEvent mouseEvent) {
        setEffect(new ColorAdjust(0, 0, -0.05, 0));
    }

    protected void reset() {
        setEffect(null);
    }
}
