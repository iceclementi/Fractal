package seedu.fractal.component.menu.button;

import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class LogoButton extends Button {

    /**
     * Constructor for the logo "button".
     */
    public LogoButton() {
        super();

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(this, FilePath.LOGO_IMAGE_PATH);

        setPrefWidth(600);
        setPrefHeight(200);

        prefWidthProperty().bind(heightProperty().multiply(3));
    }

    private void initialiseEvents() {
        setOnMouseEntered(this::onHover);
        setOnMouseExited(this::onUnhover);
    }

    private void onHover(MouseEvent mouseEvent) {
        setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(99, 133, 171), 10, 0, 0, 0));
    }

    private void onUnhover(MouseEvent mouseEvent) {
        setEffect(null);
    }
}
