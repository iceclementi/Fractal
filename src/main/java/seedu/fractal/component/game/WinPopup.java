package seedu.fractal.component.game;

import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import seedu.fractal.component.game.button.BackToMainButton;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class WinPopup extends EndGamePopup {

    private GridPane gamePane;
    private VBox winParentBox;

    private VBox winBox;
    private Label matchedText;
    private Label matchedPercent;
    private Label timeText;
    private Label time;
    private Label bonusScore;
    private Label score;
    private Label scoreText;
    private VBox buttonBox;

    private static WinPopup winPopup = null;

    private WinPopup() {
    }

    /**
     * Gets the singleton instance of the win popup.
     *
     * @return
     *  The win popup
     */
    public static WinPopup getInstance() {
        if (winPopup == null) {
            winPopup = new WinPopup();
        }

        return winPopup;
    }

    /**
     * Initialises the win popup with its various components.
     *
     * @param gamePane
     *  The grid pane of the game
     * @param winParentBox
     *  The parent VBox of the win popup
     * @param winBox
     *  The main VBox of the win popup
     * @param matchedText
     *  The label for the matched text
     * @param matchedPercent
     *  The label for the percentage of cards matched in the game
     * @param timeText
     *  The label for the time text
     * @param time
     *  The label for the time taken to win the game
     * @param bonusScore
     *  The label for the bonus move score of the game
     * @param score
     *  The label for the score of the game
     * @param scoreText
     *  The label for the score text
     * @param buttonBox
     *  The HBox for the continue and back to main buttons
     */
    public void initialise(GridPane gamePane, VBox winParentBox, VBox winBox, Label matchedText, Label matchedPercent,
           Label timeText, Label time, Label bonusScore, Label score, Label scoreText, VBox buttonBox) {
        this.gamePane = gamePane;
        this.winParentBox = winParentBox;

        this.winBox = winBox;
        this.matchedText = matchedText;
        this.matchedPercent = matchedPercent;
        this.timeText = timeText;
        this.time = time;
        this.bonusScore = bonusScore;
        this.score = score;
        this.scoreText = scoreText;
        this.buttonBox = buttonBox;

        initialiseStyle();
    }

    /**
     * Shows the win popup.
     *
     * @param matchedPercent
     *  The percentage of the cards that are matched in the game
     * @param time
     *  The time taken to win the game
     * @param score
     *  The score of the game
     * @param bonusScore
     *  The bonus score of the game
     */
    public void show(String matchedPercent, String time, String score, String bonusScore) {
        this.matchedPercent.setText(matchedPercent);
        this.time.setText(time);
        this.score.setText(score);
        this.bonusScore.setText(bonusScore);

        winParentBox.setVisible(true);
        gamePane.setEffect(new BoxBlur(5, 5, 3));
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(winBox, FilePath.WIN_FRAME_IMAGE_PATH);
        winBox.setPrefSize(400, 500);

        setCommonLabels(matchedText, matchedPercent, score, scoreText);

        ComponentUtil.setStyleClass(bonusScore, FilePath.GAME_STYLE_PATH, "bonus-score");

        ComponentUtil.setStyleClass(timeText, FilePath.GAME_STYLE_PATH, "statistics");
        timeText.setText("TIME:");

        ComponentUtil.setStyleClass(time, FilePath.GAME_STYLE_PATH, "statistics");

        buttonBox.getChildren().addAll(new BackToMainButton());
    }
}
