package seedu.fractal.component.game;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

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
        getStyleClass().clear();
        getStyleClass().add("cancel-button");
    }

    private void initialiseStyle() {
        setText("CANCEL");
        getStylesheets().add(getClass().getResource("/styles/gameStyle.css").toExternalForm());
        getStyleClass().add("unactivated-button");
        reset();
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
        getStyleClass().clear();
        getStyleClass().add("unactivated-button");
    }
}
