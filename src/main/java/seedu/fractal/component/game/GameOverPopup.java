package seedu.fractal.component.game;

import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import seedu.fractal.component.game.button.BackToMainButton;
import seedu.fractal.component.game.button.ContinueGameButton;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class GameOverPopup extends EndPopup {

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

    public static GameOverPopup getInstance() {
        if (gameOverPopup == null) {
            gameOverPopup = new GameOverPopup();
        }

        return gameOverPopup;
    }

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

    public void show(String matchedPercent, String score) {
        this.matchedPercent.setText(matchedPercent);
        this.score.setText(score);

        gameOverParentBox.setVisible(true);
        gamePane.setEffect(new BoxBlur(5, 5, 3));
    }

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
