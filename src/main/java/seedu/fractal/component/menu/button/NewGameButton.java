package seedu.fractal.component.menu.button;

import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import seedu.fractal.storage.FilePath;

public class NewGameButton extends MenuButton {

    private GridPane menuPane;
    private GridPane popupPane;

    public NewGameButton(GridPane menuPane, GridPane popupPane) {
        super();
        this.menuPane = menuPane;
        this.popupPane = popupPane;

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        setButtonBackground(FilePath.NEW_GAME_BUTTON_IMAGE_PATH);
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {
        reset();
        popupPane.setVisible(true);
        menuPane.setEffect(new BoxBlur(5, 5, 3));
    }
}
