package seedu.fractal.component.menu.button;

import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import seedu.fractal.storage.FilePath;

public class MenuButton extends Button {

    /**
     * Constructor for a custom menu button.
     */
    public MenuButton() {
        super();

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        getStylesheets().add(getClass().getResource(FilePath.MENU_STYLE_PATH).toExternalForm());
        getStyleClass().add("menu-button");

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
