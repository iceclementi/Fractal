package seedu.fractal.component.game;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import seedu.fractal.storage.FilePath;

public class CancelButton extends Button {

    private boolean canMatch = false;

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
        canMatch = true;
        setDisable(false);
    }

    private void initialiseStyle() {
        setText("CANCEL");
        getStylesheets().add(getClass().getResource(FilePath.GAME_STYLE_PATH).toExternalForm());
        getStyleClass().add("cancel-button");
        setDisable(true);
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }


    private void onRelease(MouseEvent mouseEvent) {
        if (canMatch) {
            CardButton.reset();
        }
    }

    /**
     * Resets the cancel button to unactivated mode.
     */
    public void reset() {
        canMatch = false;
        setDisable(true);
    }
}
