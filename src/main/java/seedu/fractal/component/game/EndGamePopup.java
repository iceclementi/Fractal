package seedu.fractal.component.game;

import javafx.scene.control.Label;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public abstract class EndGamePopup {

    /**
     * An empty constructor for the end game popup.
     */
    public EndGamePopup() {
    }

    /**
     * Sets some of the common labels for the end game popup.
     *
     * @param matchedText
     *  The label for the matched text
     * @param matchedPercent
     *  The label for the percentage of cards matched in the game
     * @param score
     *  The label for the score of the game
     * @param scoreText
     *  The label for the score text
     */
    protected void setCommonLabels(Label matchedText, Label matchedPercent, Label score, Label scoreText) {
        ComponentUtil.setStyleClass(matchedText, FilePath.GAME_STYLE_PATH, "statistics-text");
        matchedText.setText("MATCHED:");

        ComponentUtil.setStyleClass(matchedPercent, FilePath.GAME_STYLE_PATH, "statistics");

        ComponentUtil.setStyleClass(score, FilePath.GAME_STYLE_PATH, "score");

        ComponentUtil.setStyleClass(scoreText, FilePath.GAME_STYLE_PATH, "score-text");
        matchedText.setText("YOUR SCORE");
    }
}
