package seedu.fractal.component.menu;

import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import seedu.fractal.storage.FilePath;

public class NewGameButton extends MenuButton {

    private GridPane menuPane;
    private GridPane popupPane;

    private boolean isPopupBoxFilled = false;

    public NewGameButton(String name, GridPane menuPane, GridPane popupPane) {
        super(name);
        this.menuPane = menuPane;
        this.popupPane = popupPane;

        initialiseEvents();
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {
        popupPane.setVisible(true);
        menuPane.setEffect(new BoxBlur(5, 5, 3));
    }
}
