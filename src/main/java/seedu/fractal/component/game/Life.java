package seedu.fractal.component.game;

import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class Life extends Button {

    /**
     * Constructor for a life "button".
     */
    public Life() {
        super();

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        ComponentUtil.setButtonBackground(this, FilePath.LIFE_IMAGE_PATH);
        setPrefSize(57.41, 50);
        prefWidthProperty().bind(heightProperty().multiply(1.148));
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
