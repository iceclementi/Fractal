package seedu.fractal.component.game;

import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import seedu.fractal.component.game.button.BackToMainButton;
import seedu.fractal.component.game.button.ContinueGameButton;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class GameOverPopup extends EndGamePopup {

    private GridPane gamePane;
    private VBox gameOverParentBox;

    private VBox gameOverBox;
    private Label matchedText;
    private Label matchedPercent;
    private Label score;
    private Label scoreText;
    private VBox buttonBox;

    private static GameOverPopup gameOverPopup = null;

    private GameOverPopup() {
    }

    /**
     * Gets the singleton instance of the game over popup.
     *
     * @return
     *  The game over popup
     */
    public static GameOverPopup getInstance() {
        if (gameOverPopup == null) {
            gameOverPopup = new GameOverPopup();
        }

        return gameOverPopup;
    }

    /**
     * Initialises the game over popup with its various components.
     *
     * @param gamePane
     *  The grid pane of the game
     * @param gameOverParentBox
     *  The parent VBox of the game over popup
     * @param gameOverBox
     *  The main VBox of the game over popup
     * @param matchedText
     *  The label for the matched text
     * @param matchedPercent
     *  The label for the percentage of cards matched in the game
     * @param score
     *  The label for the score of the game
     * @param scoreText
     *  The label for the score text
     * @param buttonBox
     *  The HBox for the continue and back to main buttons
     */
    public void initialise(GridPane gamePane, VBox gameOverParentBox, VBox gameOverBox,
            Label matchedText, Label matchedPercent, Label score, Label scoreText, VBox buttonBox) {
        this.gamePane = gamePane;
        this.gameOverParentBox = gameOverParentBox;

        this.gameOverBox = gameOverBox;
        this.matchedText = matchedText;
        this.matchedPercent = matchedPercent;
        this.score = score;
        this.scoreText = scoreText;
        this.buttonBox = buttonBox;

        initialiseStyle();
    }

    /**
     * Shows the game over popup.
     *
     * @param matchedPercent
     *  The percentage of the cards that are matched in the game
     * @param score
     *  The score of the game
     */
    public void show(String matchedPercent, String score) {
        this.matchedPercent.setText(matchedPercent);
        this.score.setText(score);

        gameOverParentBox.setVisible(true);
        gamePane.setEffect(new BoxBlur(5, 5, 3));
    }

    /**
     * Shows the game cleared popup (for a lost game or a practice game).
     *
     * @param matchedPercent
     *  The percentage of the cards that are matched in the game
     * @param score
     *  The score of the game
     */
    public void showClear(String matchedPercent, String score) {
        ComponentUtil.setBackground(gameOverBox, FilePath.CLEARED_FRAME_IMAGE_PATH);
        buttonBox.getChildren().clear();
        buttonBox.getChildren().add(new BackToMainButton());

        show(matchedPercent, score);
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(gameOverBox, FilePath.GAME_OVER_FRAME_IMAGE_PATH);
        gameOverBox.setPrefSize(400, 500);

        setCommonLabels(matchedText, matchedPercent, score, scoreText);

        buttonBox.getChildren().addAll(new ContinueGameButton(gamePane, gameOverParentBox), new BackToMainButton());
    }
}
