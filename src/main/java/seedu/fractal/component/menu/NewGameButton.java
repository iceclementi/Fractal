package seedu.fractal.component.menu;

import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import seedu.fractal.storage.FilePath;

public class NewGameButton extends MenuButton {

    private GridPane menuPane;
    private GridPane popupPane;

    private boolean isPopupBoxFilled = false;

    public NewGameButton(String name, GridPane menuPane, GridPane popupPane) {
        super("");
        this.menuPane = menuPane;
        this.popupPane = popupPane;

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        Image image = new Image(FilePath.NEW_GAME_BUTTON_IMAGE_PATH, getWidth(), getHeight(), false, true, true);
        BackgroundImage startButtonBackground = new BackgroundImage(
                image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(getWidth(), getHeight(), true, true, true, false));
        setBackground(new Background(startButtonBackground));

    }

    private void initialiseEvents() {
        setOnMouseEntered(this::onHover);
        setOnMouseExited(this::onUnhover);
        setOnMousePressed(this::onClick);
        setOnMouseReleased(this::onRelease);
    }

    private void onHover(MouseEvent mouseEvent) {
        // setEffect(new ColorAdjust(0, 0, -0.05, 0));
    }

    private void onUnhover(MouseEvent mouseEvent) {
        // setEffect(new ColorAdjust(0, 0, -0.05, 0));
    }

    private void onClick(MouseEvent mouseEvent) {
        setEffect(new ColorAdjust(0, 0, -0.05, 0));
    }

    private void onRelease(MouseEvent mouseEvent) {
        popupPane.setVisible(true);
        menuPane.setEffect(new BoxBlur(5, 5, 3));
    }
}
