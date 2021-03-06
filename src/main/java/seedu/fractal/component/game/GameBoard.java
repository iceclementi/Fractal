package seedu.fractal.component.game;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.fractal.component.game.button.CancelButton;
import seedu.fractal.component.game.button.CardButton;
import seedu.fractal.component.game.button.MatchButton;
import seedu.fractal.logic.CardType;
import seedu.fractal.logic.Difficulty;
import seedu.fractal.logic.GameMode;
import seedu.fractal.storage.Storage;

import java.util.ArrayList;
import java.util.HashMap;

public class GameBoard {

    /* Game details */
    private Difficulty difficulty = Difficulty.EASY;
    private int numberOfMatches = 4;
    private HashMap<CardType, Boolean> cardTypeOptions = new HashMap<>();
    private GameMode gameMode = GameMode.NORMAL;

    private boolean isOngoing = false;

    /* Current game information */
    private int matchedCardCount = 0;
    private int selectedCardCount = 0;
    private CardButton[] selectedCards = new CardButton[2];
    private int numberOfMoves = 0;

    private boolean isGameEnd = false;

    private ArrayList<CardButton> cardButtons = new ArrayList<>();
    private MatchButton matchButton;
    private CancelButton cancelButton;
    private Label gameScore;

    private LifeManager lifeManager = new LifeManager();
    private ScoreTracker scoreTracker = new ScoreTracker();
    private TimeTracker timeTracker = new TimeTracker(0);

    private static GameBoard gameBoard = null;

    private GameBoard() {
    }

    public static GameBoard getInstance() {
        if (gameBoard == null) {
            gameBoard = new GameBoard();
        }

        return gameBoard;
    }

    public void initialise(MatchButton matchButton, CancelButton cancelButton, Label gameScore, HBox lifeBox) {
        this.matchButton = matchButton;
        this.cancelButton = cancelButton;
        this.gameScore = gameScore;

        activateSelectionButtons();
        gameScore.setText(String.valueOf(getScore()));

        lifeManager.initialise(lifeBox);

        if (isGameEnd) {
            scoreTracker.stop();
        } else {
            scoreTracker.start();
        }

        timeTracker.start();

        isOngoing = true;

        Storage.saveGameDetails(difficulty, numberOfMatches, cardTypeOptions, lifeManager.getNumberOfLives(),
                gameMode, true);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public HashMap<CardType, Boolean> getCardTypeOptions() {
        return cardTypeOptions;
    }

    public int getNumberOfLives() {
        return lifeManager.getNumberOfLives();
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    /**
     * Sets the details of the game.
     *
     * @param difficulty
     *  The difficulty of the game
     * @param numberOfMatches
     *  The number of matches in the game
     * @param cardTypeOptions
     *  The card type options selected in the game
     * @param numberOfLives
     *  The number of lives in the game
     * @param gameMode
     *  The mode of the game
     */
    public void setDetails(Difficulty difficulty, int numberOfMatches,
            HashMap<CardType, Boolean> cardTypeOptions, int numberOfLives, GameMode gameMode) {
        this.difficulty = difficulty;
        this.numberOfMatches = numberOfMatches;
        this.cardTypeOptions = cardTypeOptions;
        lifeManager.setNumberOfLives(numberOfLives);
        this.gameMode = gameMode;
    }

    /**
     * Set default details if there is an error.
     */
    public void setDefaultDetails() {
        difficulty = Difficulty.EASY;
        numberOfMatches = 4;

        cardTypeOptions.put(CardType.FRACTION, true);
        cardTypeOptions.put(CardType.DECIMAL, true);
        cardTypeOptions.put(CardType.PERCENTAGE, true);
        cardTypeOptions.put(CardType.RATIO, true);
        cardTypeOptions.put(CardType.PART, true);
        cardTypeOptions.put(CardType.SIMPLIFIED, false);
        cardTypeOptions.put(CardType.PROPER, true);

        lifeManager.setNumberOfLives(3);

        gameMode = GameMode.NORMAL;
    }

    public boolean isOngoing() {
        return isOngoing;
    }

    public void setOngoing(boolean ongoing) {
        isOngoing = ongoing;
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

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    public int getCurrentNumberOfLives() {
        return lifeManager.getCurrentNumberOfLives();
    }

    public void setCurrentNumberOfLives(int currentNumberOfLives) {
        lifeManager.setCurrentNumberOfLives(currentNumberOfLives);
    }

    public int getScore() {
        return scoreTracker.getScore();
    }

    public void setScore(int score) {
        scoreTracker.setScore(score);
    }

    public int getStreak() {
        return scoreTracker.getStreak();
    }

    public void setStreak(int streak) {
        scoreTracker.setStreak(streak);
    }

    public int getElapsedTime() {
        return timeTracker.getElapsedTime();
    }

    public void setReturnTime(int returnTime) {
        timeTracker = new TimeTracker(returnTime);
    }

    public ArrayList<CardButton> getCardButtons() {
        return cardButtons;
    }

    public void setCardButtons(ArrayList<CardButton> cardButtons) {
        this.cardButtons = cardButtons;
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }

    public void setGameEnd(boolean gameEnd) {
        isGameEnd = gameEnd;
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
            ++numberOfMoves;
            activateSelectionButtons();
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
        gameScore.setText(String.valueOf(getScore()));

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
    }

    public void gameOver() {
        String matchedPercent = String.format("%.1f%%", 100.0 * matchedCardCount /  numberOfMatches / 2);
        String score = String.valueOf(getScore());

        GameOverPopup.getInstance().show(matchedPercent, score);

        isGameEnd = true;
        scoreTracker.stop();
    }

    /**
     * Resets the game board.
     */
    public void resetGame() {
        matchedCardCount = 0;
        selectedCardCount = 0;
        numberOfMoves = 0;

        lifeManager.reset();
        scoreTracker.reset();

        timeTracker = new TimeTracker(0);

        isOngoing = false;
        isGameEnd = false;
        Storage.saveGameDetails(difficulty, numberOfMatches, cardTypeOptions, lifeManager.getNumberOfLives(),
                gameMode, false);
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
        String time;
        String score;
        String bonusScore;

        if (lifeManager.getCurrentNumberOfLives() > 0 && gameMode == GameMode.NORMAL) {
            scoreTracker.addBonus();

            time = timeTracker.getTimeString();
            score = String.valueOf(getScore());
            bonusScore = String.format("MOVE BONUS: %s",
                    scoreTracker.getBonusScore() == 0 ? "-" : scoreTracker.getBonusScore());
            WinPopup.getInstance().show(matchedPercent, time, score, bonusScore);
        } else {
            score = String.valueOf(getScore());
            GameOverPopup.getInstance().showClear(matchedPercent, score);
        }

        isGameEnd = true;
        scoreTracker.stop();
    }
}
