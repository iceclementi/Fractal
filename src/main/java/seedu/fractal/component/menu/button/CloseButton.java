package seedu.fractal.component.menu.button;

import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class CloseButton extends Button {

    private GridPane menuPane;
    private GridPane popupPane;

    public CloseButton(GridPane menuPane, GridPane popupPane) {
        super();
        this.menuPane = menuPane;
        this.popupPane = popupPane;

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        getStylesheets().add(getClass().getResource(FilePath.MENU_STYLE_PATH).toExternalForm());
        getStyleClass().add("close-button");
        ComponentUtil.setBackground(this, FilePath.CLOSE_BUTTON_IMAGE_PATH);

        prefWidthProperty().bind(heightProperty());
    }

    private void initialiseEvents() {
        setOnMouseEntered(this::onHover);
        setOnMouseExited(this::onUnhover);
        setOnMousePressed(this::onClick);
        setOnMouseReleased(this::onRelease);
    }

    private void onHover(MouseEvent mouseEvent) {
        setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(99, 133, 171), 5, 0, 0, 0));
    }

    private void onUnhover(MouseEvent mouseEvent) {
        reset();
    }

    private void onClick(MouseEvent mouseEvent) {
        setEffect(new ColorAdjust(0, 0, -0.1, 0));
    }

    private void onRelease(MouseEvent mouseEvent) {
        reset();
        menuPane.setEffect(null);
        popupPane.setVisible(false);
    }

    private void reset() {
        setEffect(null);
    }
}
