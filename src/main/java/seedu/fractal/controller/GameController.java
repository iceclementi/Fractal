package seedu.fractal.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.fractal.component.game.GameOverPopup;
import seedu.fractal.component.game.WinPopup;
import seedu.fractal.component.game.button.GameBackButton;
import seedu.fractal.component.game.button.CancelButton;
import seedu.fractal.component.game.button.CardButton;
import seedu.fractal.component.game.GameBoard;
import seedu.fractal.component.game.button.MatchButton;
import seedu.fractal.logic.Card;
import seedu.fractal.logic.CardGenerator;
import seedu.fractal.logic.GameMode;
import seedu.fractal.storage.FilePath;
import seedu.fractal.storage.Storage;
import seedu.fractal.util.ComponentUtil;
import seedu.fractal.util.SceneUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private GridPane gamePane;

    @FXML
    private Label gameMode;

    @FXML
    private HBox lifeBox;

    @FXML
    private VBox cardBox;

    @FXML
    private HBox controlBox;
    @FXML
    private HBox selectionBox;
    @FXML
    private Label gameScoreText;
    @FXML
    private Label gameScore;

    @FXML
    private VBox winParentBox;
    @FXML
    private VBox winBox;
    @FXML
    private Label winMatchedText;
    @FXML
    private Label winMatchedPercent;
    @FXML
    private Label winTimeText;
    @FXML
    private Label winTime;
    @FXML
    private Label winBonusScore;
    @FXML
    private Label winScore;
    @FXML
    private Label winScoreText;
    @FXML
    private VBox winButtonBox;


    @FXML
    private VBox gameOverParentBox;
    @FXML
    private VBox gameOverBox;
    @FXML
    private Label gameOverMatchedText;
    @FXML
    private Label gameOverMatchedPercent;
    @FXML
    private Label gameOverScore;
    @FXML
    private Label gameOverScoreText;
    @FXML
    private VBox gameOverButtonBox;


    private GameBoard gameBoard = GameBoard.getInstance();
    private ArrayList<HBox> cardRows = new ArrayList<>();

    /* Hard-coded grid for card arrangement */
    private static final int[] ROWS = {0, 2, 2, 2, 2, 2, 3, 3, 4, 3, 4, 4, 4, 4, 4, 5, 4, 5, 5, 5, 5};
    private static final int[] COLUMNS = {0, 1, 2, 3, 4, 5, 4, 5, 4, 6, 5, 6, 6, 7, 7, 6, 8, 7, 8, 8, 8};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gamePane.setBackground(SceneUtil.generateBackground(FilePath.BACKGROUND_IMAGE_PATH));

        try {
            Storage.loadGameDetails();
        } catch (Exception e) {
            Storage.loadDefaultGameDetails();
        }

        setComponentStyles();
        preparePopupBoxes();

        if (!gameBoard.isOngoing()) {
            gameBoard.resetGame();
            gameBoard.setCardButtons(generateCards());
        }

        GameBackButton gameBackButton = new GameBackButton();
        controlBox.getChildren().addAll(gameBackButton);

        MatchButton matchButton = new MatchButton();
        CancelButton cancelButton = new CancelButton();

        gameBoard.initialise(matchButton, cancelButton, gameScore, lifeBox);

        arrangeCards();

        selectionBox.getChildren().addAll(matchButton, cancelButton);

        Storage.saveGame();
    }

    // To be changed to image?
    private void setComponentStyles() {
        ComponentUtil.setStyleClass(gameMode, FilePath.GAME_STYLE_PATH, "game-mode");
        String gameModeText = gameBoard.getDifficulty().name()
                + ((gameBoard.getGameMode() == GameMode.NORMAL) ? "" : "  (PRACTICE)");
        gameMode.setText(gameModeText);

        ComponentUtil.setStyleClass(gameScoreText, FilePath.GAME_STYLE_PATH, "game-score");
        gameScoreText.setText("SCORE:");

        ComponentUtil.setStyleClass(gameScore, FilePath.GAME_STYLE_PATH, "game-score");
    }

    private void preparePopupBoxes() {
        GameOverPopup.getInstance().initialise(gamePane, gameOverParentBox, gameOverBox,
                gameOverMatchedText, gameOverMatchedPercent, gameOverScore, gameOverScoreText, gameOverButtonBox);
        WinPopup.getInstance().initialise(gamePane, winParentBox, winBox, winMatchedText, winMatchedPercent,
                winTimeText, winTime, winBonusScore, winScore, winScoreText, winButtonBox);
    }

    private ArrayList<CardButton> generateCards() {
        ArrayList<Card> cards = new CardGenerator(gameBoard.getDifficulty(), gameBoard.getNumberOfMatches(),
                gameBoard.getCardTypeOptions()).generateCards();

        ArrayList<CardButton> cardButtons = new ArrayList<>();
        for (int i = 0; i < cards.size(); ++i) {
            cardButtons.add(new CardButton(i, cards.get(i)));
        }

        return cardButtons;
    }

    private void arrangeCards() {
        int numberOfRows = ROWS[gameBoard.getNumberOfMatches()];
        int numberOfColumns = COLUMNS[gameBoard.getNumberOfMatches()];

        for (int i = 0; i < numberOfRows; ++i) {
            HBox newRow = new HBox();
            newRow.setPadding(new Insets(5));
            newRow.setSpacing(10);
            newRow.setAlignment(Pos.CENTER);

            cardRows.add(newRow);
        }

        int numberOfCards = gameBoard.getNumberOfMatches() * 2;
        ArrayList<CardButton> cards = gameBoard.getCardButtons();

        for (int i = 0; i < numberOfRows; ++i) {
            for (int j = 0; j < numberOfColumns; ++j) {
                if (i * numberOfColumns + j >= numberOfCards) {
                    break;
                }
                cardRows.get(i).getChildren().add(cards.get(i * numberOfColumns + j));
            }
            cardBox.getChildren().add(cardRows.get(i));
        }
    }
}
