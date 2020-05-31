package seedu.fractal.component.game;

import javafx.scene.control.Label;
import seedu.fractal.storage.FilePath;

public abstract class EndPopup {

    public EndPopup() {
    }

    protected void setCommonLabels(Label matchedText, Label matchedPercent, Label score, Label scoreText) {
        setLabelText(matchedText, "MATCHED:");
        matchedText.getStyleClass().add("statistics-text");

        setLabelText(matchedPercent, "");
        matchedPercent.getStyleClass().add("statistics");

        setLabelText(score, "");
        score.getStyleClass().add("score");

        setLabelText(scoreText, "YOUR SCORE");
        scoreText.getStyleClass().add("score-text");
    }

    protected void setLabelText(Label label, String text) {
         label.getStylesheets().add(getClass().getResource(FilePath.GAME_STYLE_PATH).toExternalForm());
         label.setText(text);
    }
}
