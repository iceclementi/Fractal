package seedu.fractal.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.fractal.component.game.GameBackButton;
import seedu.fractal.component.game.CancelButton;
import seedu.fractal.component.game.CardButton;
import seedu.fractal.component.game.GameBoard;
import seedu.fractal.component.game.MatchButton;
import seedu.fractal.logic.Card;
import seedu.fractal.logic.CardGenerator;
import seedu.fractal.storage.FilePath;
import seedu.fractal.storage.Storage;
import seedu.fractal.util.SceneUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private GridPane gamePane;

    @FXML
    private HBox controlBox;

    @FXML
    private VBox cardBox;

    @FXML
    private HBox selectionBox;

    @FXML
    private Label matchCounter;

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

        GameBackButton gameBackButton = new GameBackButton();
        controlBox.getChildren().addAll(gameBackButton);

        MatchButton matchButton = new MatchButton();
        CancelButton cancelButton = new CancelButton();

        if (!gameBoard.isOngoing()) {
            gameBoard.resetGame();
            gameBoard.setCardButtons(generateCards());
        }

        gameBoard.initialise(matchButton, cancelButton, matchCounter);

        arrangeCards();

        selectionBox.getChildren().addAll(matchButton, cancelButton);

        Storage.saveGame();
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

    private ArrayList<CardButton> generateCards() {
        ArrayList<Card> cards = new CardGenerator(gameBoard.getDifficulty(), gameBoard.getNumberOfMatches(),
                gameBoard.getAdvancedOptions()).generateCards();

        ArrayList<CardButton> cardButtons = new ArrayList<>();
        for (int i = 0; i < cards.size(); ++i) {
            cardButtons.add(new CardButton(i, cards.get(i)));
        }

        return cardButtons;
    }
}
