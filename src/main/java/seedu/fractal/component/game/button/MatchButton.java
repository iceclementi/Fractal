package seedu.fractal.component.game.button;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import seedu.fractal.component.game.GameBoard;
import seedu.fractal.storage.FilePath;

public class MatchButton extends Button {

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
        setDisable(false);
    }

    private void initialiseStyle() {
        setText("MATCH!");
        getStylesheets().add(getClass().getResource(FilePath.GAME_STYLE_PATH).toExternalForm());
        getStyleClass().add("match-button");
        setDisable(true);
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {
        GameBoard gameBoard = GameBoard.getInstance();

        if (gameBoard.isMatched()) {
            gameBoard.match();
        } else {
            gameBoard.loseLife();
            gameBoard.reset();
        }
    }

    /**
     * Resets the match button to unactivated mode.
     */
    public void reset() {
        setDisable(true);
    }
}
