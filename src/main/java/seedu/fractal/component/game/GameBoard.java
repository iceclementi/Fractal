package seedu.fractal.component.game;

import javafx.scene.control.Label;
import seedu.fractal.component.game.button.CancelButton;
import seedu.fractal.component.game.button.CardButton;
import seedu.fractal.component.game.button.MatchButton;
import seedu.fractal.logic.CardType;
import seedu.fractal.logic.Difficulty;
import seedu.fractal.storage.Storage;

import java.util.ArrayList;
import java.util.HashMap;

public class GameBoard {

    /* Game details */
    private Difficulty difficulty = Difficulty.EASY;
    private int numberOfMatches = 4;
    private HashMap<CardType, Boolean> advancedOptions = new HashMap<>();

    /* Current game information */
    private int matchedCardCount = 0;
    private int selectedCardCount = 0;
    private CardButton[] selectedCards = new CardButton[2];
    private int matchCounter = 0;

    private boolean isOngoing = false;

    private ArrayList<CardButton> cardButtons = new ArrayList<>();
    private MatchButton matchButton;
    private CancelButton cancelButton;
    private Label matchCounterLabel;

    private static GameBoard gameBoard = null;

    private GameBoard() {
    }

    public static GameBoard getInstance() {
        if (gameBoard == null) {
            gameBoard = new GameBoard();
        }

        return gameBoard;
    }

    public void initialise(MatchButton matchButton, CancelButton cancelButton, Label matchCounterLabel) {
        this.matchButton = matchButton;
        this.cancelButton = cancelButton;
        this.matchCounterLabel = matchCounterLabel;

        activateSelectionButtons();
        matchCounterLabel.setText(String.valueOf(matchCounter));
        isOngoing = true;
        Storage.saveGameDetails(difficulty, numberOfMatches, advancedOptions, true);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public HashMap<CardType, Boolean> getAdvancedOptions() {
        return advancedOptions;
    }

    public void setDetails(Difficulty difficulty, int numberOfMatches, HashMap<CardType, Boolean> advancedOptions) {
        this.difficulty = difficulty;
        this.numberOfMatches = numberOfMatches;
        this.advancedOptions = advancedOptions;
    }

    /**
     * Set default details if there is an error.
     */
    public void setDefaultDetails() {
        difficulty = Difficulty.EASY;
        numberOfMatches = 4;

        for (CardType cardType : CardType.values()) {
            advancedOptions.put(cardType, true);
        }
    }

    public int getMatchedCardCount() {
        return matchedCardCount;
    }

    public int getSelectedCardCount() {
        return selectedCardCount;
    }

    public void setCountInformation(int matchedCardCount, int selectedCardCount) {
        this.matchedCardCount = matchedCardCount;
        this.selectedCardCount = selectedCardCount;
    }

    public void setSelectedCards(CardButton firstSelectedCard, CardButton secondSelectedCard) {
        selectedCards[0] = firstSelectedCard;
        selectedCards[1] = secondSelectedCard;
    }

    public int getMatchCounter() {
        return matchCounter;
    }

    public void setMatchCounter(int counter) {
        matchCounter = counter;
    }

    public ArrayList<CardButton> getCardButtons() {
        return cardButtons;
    }

    public void setCardButtons(ArrayList<CardButton> cardButtons) {
        this.cardButtons = cardButtons;
    }

    public boolean isOngoing() {
        return isOngoing;
    }

    public void setOngoing(boolean ongoing) {
        isOngoing = ongoing;
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

        matchedCardCount += 2;
        selectedCardCount = 0;
        matchButton.reset();
        cancelButton.reset();

        Storage.saveGame();

        if (matchedCardCount == numberOfMatches * 2) {
            resetGame();
        }
    }

    public void selectCard(CardButton card) {
        if (selectedCardCount >= 2) {
            return;
        }

        selectedCards[selectedCardCount++] = card;
        card.select();

        if (selectedCardCount == 2) {
            activateSelectionButtons();
            matchCounterLabel.setText(String.valueOf(++matchCounter));
        }

        Storage.saveGame();
    }

    public boolean isMatched() {
        return selectedCards[0].getCard().isSameValue(selectedCards[1].getCard());
    }

    /**
     * Resets the game board.
     */
    public void resetGame() {
        matchedCardCount = 0;
        selectedCardCount = 0;
        matchCounter = 0;

        isOngoing = false;
        Storage.saveGameDetails(difficulty, numberOfMatches, advancedOptions, false);
        Storage.saveGame();

        // Show some ending screen
    }

    private void activateSelectionButtons() {
        if (selectedCardCount == 2) {
            matchButton.activate();
            cancelButton.activate();
        }
    }
}
