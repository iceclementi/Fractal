package seedu.fractal.component.game;

import javafx.scene.control.Label;
import seedu.fractal.logic.Difficulty;
import seedu.fractal.storage.Storage;

import java.util.ArrayList;

public class GameBoard {

    private Difficulty difficulty;
    private int numberOfMatches;
    private int selectedCardCount = 0;
    private CardButton[] selectedCards = new CardButton[2];
    private int matchCount = 0;

    private ArrayList<CardButton> cardButtons = new ArrayList<>();
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

    public void initialise(ArrayList<CardButton> cardButtons, MatchButton matchButton,
               CancelButton cancelButton, Label matchCounter) {
        this.cardButtons = cardButtons;
        this.matchButton = matchButton;
        this.cancelButton = cancelButton;
        this.matchCounter = matchCounter;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public void setDetails(Difficulty difficulty, int numberOfMatches) {
        this.difficulty = difficulty;
        this.numberOfMatches = numberOfMatches;
    }

    public int getSelectedCardCount() {
        return selectedCardCount;
    }

    public void setSelectedCardCount(int selectedCardCount) {
        this.selectedCardCount = selectedCardCount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public void setMatchCount(int matchCount) {
        this.matchCount = matchCount;
    }

    public ArrayList<CardButton> getCardButtons() {
        return cardButtons;
    }

    public void setCardButtons(ArrayList<CardButton> cardButtons) {
        this.cardButtons = cardButtons;
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

        Storage.saveGame();
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

        Storage.saveGame();
    }

    public void selectCard(CardButton card) {
        selectedCards[selectedCardCount++] = card;

        if (selectedCardCount == 2) {
            matchButton.activate();
            cancelButton.activate();

            matchCounter.setText(String.valueOf(++matchCount));
        }

        Storage.saveGame();
    }

    public boolean canSelect() {
        return selectedCardCount < 2;
    }

    public boolean isMatched() {
        return selectedCards[0].getCard().isSameValue(selectedCards[1].getCard());
    }
}
