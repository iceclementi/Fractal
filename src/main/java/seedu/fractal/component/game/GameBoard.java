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
    private int numberOfLives = 3;

    /* Current game information */
    private int matchedCardCount = 0;
    private int selectedCardCount = 0;
    private CardButton[] selectedCards = new CardButton[2];
    private int matchCounter = 0;
    private int currentNumberOfLives = 3;
    private ScoreTracker scoreTracker = ScoreTracker.getInstance();

    private boolean isOngoing = false;

    private ArrayList<CardButton> cardButtons = new ArrayList<>();
    private LifeManager lifeManager;
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

    public void initialise(MatchButton matchButton, CancelButton cancelButton, Label matchCounterLabel,
           LifeManager lifeManager) {
        this.matchButton = matchButton;
        this.cancelButton = cancelButton;
        this.matchCounterLabel = matchCounterLabel;
        this.lifeManager = lifeManager;

        activateSelectionButtons();
        matchCounterLabel.setText(String.valueOf(matchCounter));
        lifeManager.initialise(numberOfLives, currentNumberOfLives);
        scoreTracker.start(0, 1);
        isOngoing = true;

        Storage.saveGameDetails(difficulty, numberOfMatches, advancedOptions, numberOfLives, true);
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

    public int getNumberOfLives() {
        return numberOfLives;
    }

    /**
     * Sets the details of the game.
     *
     * @param difficulty
     *  The difficulty of the game
     * @param numberOfMatches
     *  The number of matches in the game
     * @param advancedOptions
     *  The advanced options selected in the game
     * @param numberOfLives
     *  The number of lives in the game
     */
    public void setDetails(Difficulty difficulty, int numberOfMatches,
            HashMap<CardType, Boolean> advancedOptions, int numberOfLives) {
        this.difficulty = difficulty;
        this.numberOfMatches = numberOfMatches;
        this.advancedOptions = advancedOptions;
        this.numberOfLives = numberOfLives;
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

        numberOfLives = 3;
        // currentNumberOfLives = 3;
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

    public int getCurrentNumberOfLives() {
        return currentNumberOfLives;
    }

    public void setCurrentNumberOfLives(int currentNumberOfLives) {
        this.currentNumberOfLives = currentNumberOfLives;
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
     * Selects and flip the card face-up.
     *
     * @param card
     *  The card to be selected
     */
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

        scoreTracker.breakStreak();

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

        scoreTracker.addScore();

        Storage.saveGame();

        if (matchedCardCount == numberOfMatches * 2) {
            endGame();
            resetGame();
        }
    }

    public boolean isMatched() {
        return selectedCards[0].getCard().isSameValue(selectedCards[1].getCard());
    }

    public void loseLife() {
        lifeManager.loseLife();
        --currentNumberOfLives;

        assert currentNumberOfLives >= 0 : "GameBoard: Current number of lives must be at least 0.";
    }

    public void gameOver() {
        scoreTracker.stop();

        String matchedPercent = String.format("%.1f%%", 100.0 * matchedCardCount /  numberOfMatches / 2);
        String score = String.valueOf(scoreTracker.getScore());

        GameOverPopup.getInstance().show(matchedPercent, score);
    }

    /**
     * Resets the game board.
     */
    public void resetGame() {
        matchedCardCount = 0;
        selectedCardCount = 0;
        matchCounter = 0;
        currentNumberOfLives = numberOfLives;

        isOngoing = false;
        Storage.saveGameDetails(difficulty, numberOfMatches, advancedOptions, numberOfLives, false);
        Storage.saveGame();
    }

    private void activateSelectionButtons() {
        if (selectedCardCount == 2) {
            matchButton.activate();
            cancelButton.activate();
        }
    }

    private void endGame() {
        String matchedPercent = String.format("%.1f%%", 100.0 * matchedCardCount /  numberOfMatches / 2);
        String time = "-";
        String score = String.valueOf(scoreTracker.getScore());

        if (currentNumberOfLives > 0) {
            WinPopup.getInstance().show(matchedPercent, time, score);
        } else {
            GameOverPopup.getInstance().showClear(matchedPercent, score);
        }
    }
}
