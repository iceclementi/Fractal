package seedu.fractal.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.fractal.component.game.CancelButton;
import seedu.fractal.component.game.CardButton;
import seedu.fractal.component.game.GameBoard;
import seedu.fractal.component.game.MatchButton;
import seedu.fractal.logic.Card;
import seedu.fractal.logic.CardGenerator;
import seedu.fractal.logic.Difficulty;
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
    private VBox cardBox;

    @FXML
    private HBox selectionBox;

    @FXML
    private Label matchCounter;

    private ArrayList<HBox> cardRows = new ArrayList<>();
    private ArrayList<CardButton> allCards;


    /* To be changed to appropriate file retrieval process */
    private static Difficulty difficulty;
    private static int numberOfMatches;

    /* Template for card arrangement */
    private final int[] rows = {0, 2, 2, 2, 2, 2, 3, 3, 4, 3, 4, 4, 4, 4, 4, 5, 4, 5, 5, 5, 5};
    private final int[] columns = {0, 1, 2, 3, 4, 5, 4, 5, 4, 6, 5, 6, 6, 7, 7, 6, 8, 8, 8, 8, 8};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gamePane.setBackground(SceneUtil.generateBackground(FilePath.BACKGROUND_IMAGE_PATH));

        MatchButton matchButton = new MatchButton();
        CancelButton cancelButton = new CancelButton();
        allCards = generateCards();
        GameBoard.getInstance().initialise(allCards, matchButton, cancelButton, matchCounter);
        Storage.saveGame();

        arrangeCards();

        selectionBox.getChildren().addAll(matchButton, cancelButton);
    }

    private void arrangeCards() {
        int numberOfRows = rows[numberOfMatches];
        int numberOfColumns = columns[numberOfMatches];

        for (int i = 0; i < numberOfRows; ++i) {
            HBox newRow = new HBox();
            newRow.setPadding(new Insets(5));
            newRow.setSpacing(10);
            newRow.setAlignment(Pos.CENTER);

            cardRows.add(newRow);
        }

        for (int i = 0; i < numberOfRows; ++i) {
            for (int j = 0; j < numberOfColumns; ++j) {
                if (i*numberOfColumns + j >= numberOfMatches*2) {
                    break;
                }
                cardRows.get(i).getChildren().add(allCards.get(i*numberOfColumns + j));
            }
            cardBox.getChildren().add(cardRows.get(i));
        }
    }

    private ArrayList<CardButton> generateCards() {
        ArrayList<Card> cards = new CardGenerator(numberOfMatches).generateCards();

        ArrayList<CardButton> cardButtons = new ArrayList<>();
        for (int i = 0; i < cards.size(); ++i) {
            cardButtons.add(new CardButton(i, cards.get(i)));
        }

        return cardButtons;
    }


    /* To be changed to appropriate file retrieval process */
    public static void setDifficulty(Difficulty difficulty) {
        GameController.difficulty = difficulty;
    }

    public static void setNumberOfMatches(int numberOfMatches) {
        GameController.numberOfMatches = numberOfMatches;
    }
}
