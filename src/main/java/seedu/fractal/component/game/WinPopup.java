package seedu.fractal.component.game;

import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import seedu.fractal.component.game.button.BackToMainButton;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class WinPopup extends EndPopup {

    private GridPane gamePane;
    private VBox winParentBox;

    private VBox winBox;
    private Label matchedText;
    private Label matchedPercent;
    private Label timeText;
    private Label time;
    private Label score;
    private Label scoreText;
    private VBox buttonBox;

    private static WinPopup winPopup = null;

    private WinPopup() {
    }

    public static WinPopup getInstance() {
        if (winPopup == null) {
            winPopup = new WinPopup();
        }

        return winPopup;
    }

    public void initialise(GridPane gamePane, VBox winParentBox, VBox winBox, Label matchedText, Label matchedPercent,
           Label timeText, Label time, Label winScore, Label scoreText, VBox buttonBox) {
        this.gamePane = gamePane;
        this.winParentBox = winParentBox;

        this.winBox = winBox;
        this.matchedText = matchedText;
        this.matchedPercent = matchedPercent;
        this.timeText = timeText;
        this.time = time;
        this.score = winScore;
        this.scoreText = scoreText;
        this.buttonBox = buttonBox;

        initialiseStyle();
    }

    public void show(String matchedPercent, String time, String score) {
        this.matchedPercent.setText(matchedPercent);
        this.time.setText(time);
        this.score.setText(score);

        winParentBox.setVisible(true);
        gamePane.setEffect(new BoxBlur(5, 5, 3));
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(winBox, FilePath.WIN_FRAME_IMAGE_PATH);
        winBox.setPrefSize(400, 500);

        setCommonLabels(matchedText, matchedPercent, score, scoreText);

        timeText.getStylesheets().add(getClass().getResource(FilePath.GAME_STYLE_PATH).toExternalForm());
        timeText.getStyleClass().add("statistics-text");
        timeText.setText("TIME:");

        time.getStylesheets().add(getClass().getResource(FilePath.GAME_STYLE_PATH).toExternalForm());
        time.getStyleClass().add("statistics");

        buttonBox.getChildren().addAll(new BackToMainButton());
    }
}
