package seedu.fractal.component.game;

import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import seedu.fractal.component.game.button.BackToMainButton;
import seedu.fractal.component.game.button.ContinueGameButton;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class GameOverPopup {

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
            Label gameOverMatchedText, Label gameOverMatchedPercent, Label gameOverScore, Label gameOverScoreText,
            VBox buttonBox) {
        this.gamePane = gamePane;
        this.gameOverParentBox = gameOverParentBox;

        this.gameOverBox = gameOverBox;
        this.matchedText = gameOverMatchedText;
        this.matchedPercent = gameOverMatchedPercent;
        this.score = gameOverScore;
        this.scoreText = gameOverScoreText;
        this.buttonBox = buttonBox;

        initialiseStyle();
        initialiseLayout();
        initialiseEvents();
    }

    public void show(String matchedPercent, String score) {
        this.matchedPercent.setText(matchedPercent);
        this.score.setText(score);

        gameOverParentBox.setVisible(true);
        gamePane.setEffect(new BoxBlur(5, 5, 3));
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(gameOverBox, FilePath.GAME_OVER_FRAME_IMAGE_PATH);
        gameOverBox.setPrefSize(400, 500);

        matchedText.getStylesheets().add(getClass().getResource(FilePath.GAME_STYLE_PATH).toExternalForm());
        matchedText.getStyleClass().add("matched-text");
        matchedText.setText("MATCHED:");

        matchedPercent.getStylesheets().add(getClass().getResource(FilePath.GAME_STYLE_PATH).toExternalForm());
        matchedPercent.getStyleClass().add("matched-percent");

        score.getStylesheets().add(getClass().getResource(FilePath.GAME_STYLE_PATH).toExternalForm());
        score.getStyleClass().add("score");

        scoreText.getStylesheets().add(getClass().getResource(FilePath.GAME_STYLE_PATH).toExternalForm());
        scoreText.getStyleClass().add("score-text");
        scoreText.setText("YOUR SCORE");

        buttonBox.getChildren().addAll(new ContinueGameButton(gamePane, gameOverParentBox), new BackToMainButton());
    }

    private void initialiseLayout() {

    }

    private void initialiseEvents() {

    }
}
