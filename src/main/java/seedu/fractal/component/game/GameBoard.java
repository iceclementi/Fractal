package seedu.fractal.component.game;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameBoard {

    private int selectedCardCount = 0;
    private CardButton[] selectedCards = new CardButton[2];

    private ArrayList<CardButton> cards = new ArrayList<>();
    private MatchButton matchButton;
    private CancelButton cancelButton;
    private Label matchCounter;

    private static GameBoard gameBoard = null;

    private GameBoard() {
    }

    public static GameBoard getInstance() {
        if (gameBoard == null) {
            gameBoard = new GameBoard();
        }

        return gameBoard;
    }

    public void initialise(ArrayList<CardButton> cards, MatchButton matchButton,
               CancelButton cancelButton, Label matchCounter) {
        this.cards = cards;
        this.matchButton = matchButton;
        this.cancelButton = cancelButton;
        this.matchCounter = matchCounter;
    }

    /**
     * Resets all faced-up cards back to face-down.
     */
    public void reset() {
        for (CardButton card : selectedCards) {
            card.reset();
        }

        selectedCardCount = 0;
        matchButton.reset();
        cancelButton.reset();
    }

    /**
     * Match the cards and fade image.
     */
    public void match() {
        for (CardButton card : selectedCards) {
            card.match();
        }

        selectedCardCount = 0;
        matchButton.reset();
        cancelButton.reset();
    }

    public void selectCard(CardButton card) {
        selectedCards[selectedCardCount++] = card;

        if (selectedCardCount == 2) {
            matchButton.activate();
            cancelButton.activate();

            int currentCount = Integer.parseInt(matchCounter.getText());
            matchCounter.setText(String.valueOf(currentCount+1));
        }
    }

    public boolean canSelect() {
        return selectedCardCount < 2;
    }

    public boolean isMatched() {
        return selectedCards[0].getCard().isSameValue(selectedCards[1].getCard());
    }
}
