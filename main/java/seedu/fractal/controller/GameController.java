package seedu.fractal.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.fractal.component.game.CardButton;
import seedu.fractal.logic.Card;

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

        for (int i = 0; i < 1; ++i) {
            cardRows.add(new HBox());
        }

        cardRows.get(0).setPadding(new Insets(5));
        cardRows.get(0).setSpacing(10);
        cardRows.get(0).setAlignment(Pos.CENTER);

        CardButton one = new CardButton(new Card("", "", ""));
        CardButton two = new CardButton(new Card("", "", ""));

        cardRows.get(0).getChildren().addAll(one, two);

        cardBox.getChildren().addAll(cardRows.get(0));
    }
}
