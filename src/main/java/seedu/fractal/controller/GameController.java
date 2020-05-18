package seedu.fractal.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.fractal.component.game.CardButton;
import seedu.fractal.logic.Card;
import seedu.fractal.logic.CardGenerator;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private VBox cardBox;

    private ArrayList<HBox> cardRows = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get dimensions from some storage to be implemented in future

        for (int i = 0; i < 2; ++i) {
            HBox newRow = new HBox();
            newRow.setPadding(new Insets(5));
            newRow.setSpacing(10);
            newRow.setAlignment(Pos.CENTER);

            cardRows.add(newRow);
        }

        ArrayList<Card> cards = new CardGenerator(4).generateCards();

        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 4; ++j) {
               CardButton cardButton = new CardButton(cards.get(i*4 + j));
                cardRows.get(i).getChildren().add(cardButton);
            }
            cardBox.getChildren().add(cardRows.get(i));
        }
    }
}
