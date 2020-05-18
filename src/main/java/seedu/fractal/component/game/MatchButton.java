package seedu.fractal.component.game;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import seedu.fractal.logic.Card;

public class MatchButton extends Button {

    private boolean canMatch = false;

    /**
     * Constructor for the match button.
     */
    public MatchButton() {
        super();

        initialiseStyle();
        initialiseEvents();
    }

    public void setCanMatch() {
        canMatch = true;
        getStyleClass().clear();
        getStyleClass().add("match-button");
    }

    private void initialiseStyle() {
        setText("MATCH!");
        getStylesheets().add(getClass().getResource("/styles/gameStyle.css").toExternalForm());
        getStyleClass().add("unactivated-button");
        reset();
    }

    private void initialiseEvents() {
        setOnMousePressed(this::onClick);
        setOnMouseReleased(this::onRelease);
    }



    private void onClick(MouseEvent mouseEvent) {
        if (canMatch) {
            
        }
    }

    private void onRelease(MouseEvent mouseEvent) {
        if (canMatch) {
            reset();
        }
    }

    private void reset() {
        canMatch = false;
        getStyleClass().clear();
        getStyleClass().add("unactivated-button");
    }
}
