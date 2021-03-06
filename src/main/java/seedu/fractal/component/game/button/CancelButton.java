package seedu.fractal.component.game.button;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import seedu.fractal.component.game.GameBoard;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class CancelButton extends Button {

    /**
     * Constructor for the cancel button.
     */
    public CancelButton() {
        super();

        initialiseStyle();
        initialiseEvents();
    }

    /**
     * Activates the cancel button.
     */
    public void activate() {
        setDisable(false);
    }

    /**
     * Resets the cancel button to unactivated mode.
     */
    public void reset() {
        setDisable(true);
    }

    private void initialiseStyle() {
        ComponentUtil.setStyleClass(this, FilePath.GAME_STYLE_PATH, "cancel-button");
        setText("CANCEL");

        setDisable(true);
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {
        GameBoard.getInstance().reset();
    }
}
