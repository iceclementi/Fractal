package seedu.fractal.component.game;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import seedu.fractal.storage.FilePath;

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

    /**
     * Activates the match button.
     */
    public void activate() {
        canMatch = true;
        getStyleClass().clear();
        getStyleClass().add("match-button");
    }

    private void initialiseStyle() {
        setText("MATCH!");
        getStylesheets().add(getClass().getResource(FilePath.GAME_STYLE_PATH).toExternalForm());
        getStyleClass().add("unactivated-button");
        reset();
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {
        if (canMatch) {
            CardButton[] cards = CardButton.getSelectedCards();
            if (cards[0].getCard().isSameValue(cards[1].getCard())) {
                CardButton.match();
            } else {
                CardButton.reset();
            }
        }
    }

    /**
     * Resets the match button to unactivated mode.
     */
    public void reset() {
        canMatch = false;
        getStyleClass().clear();
        getStyleClass().add("unactivated-button");
    }
}
