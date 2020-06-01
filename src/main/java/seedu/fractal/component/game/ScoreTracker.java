package seedu.fractal.component.game;

import seedu.fractal.logic.Difficulty;

import java.util.HashMap;
import java.util.stream.IntStream;

public class ScoreTracker {

    private int score = 0;
    private int bonusScore = 0;
    private int streak = 1;
    private boolean isStart = false;

    private static final int STREAK_INCREMENT = 100;
    private static final HashMap<Difficulty, Integer> DIFFICULTY_BONUS = new HashMap<>() {
            {
                put(Difficulty.EASY, 50);
                put(Difficulty.INTERMEDIATE, 100);
                put(Difficulty.ADVANCED, 200);
                put(Difficulty.GENIUS, 300);
            }};

    private static ScoreTracker scoreTracker = null;

    private ScoreTracker() {
    }

    /**
     * Gets a singleton instance of the score tracker.
     *
     * @return
     *  The point tracker
     */
    public static ScoreTracker getInstance() {
        if (scoreTracker == null) {
            scoreTracker = new ScoreTracker();
        }

        return scoreTracker;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getBonusScore() {
        return bonusScore;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    /**
     * Initialises the score tracker.
     *
     * @param score
     *  The current score of the game
     * @param streak
     *  The current streak of the game
     */
    public void start(int score, int streak) {
        this.score = score;
        this.streak = streak;

        bonusScore = 0;
        isStart = true;
    }

    /**
     * Stops tracking the score of the game.
     */
    public void stop() {
        isStart = false;
    }

    /**
     * Increases the current score of the game.
     */
    public void addScore() {
        if (isStart) {
            score += streak * STREAK_INCREMENT;
            ++streak;
        }
    }

    /**
     * Adds bonus score depending on difficulty level and number of extra moves.
     */
    public void addBonus() {
        if (!isStart) {
            return;
        }

        GameBoard gameBoard = GameBoard.getInstance();

        int extraMoves = gameBoard.getNumberOfMoves() - gameBoard.getNumberOfMatches();
        assert extraMoves >= 0 : "ScoreTracker: Extra moves must not be negative.";

        int extraMovesLimit = findExtraMovesLimit(gameBoard.getNumberOfMatches());

        if (extraMoves > extraMovesLimit) {
            return;
        }

        int bonusMultiplier = extraMovesLimit - extraMoves + 1;

        bonusScore = DIFFICULTY_BONUS.get(gameBoard.getDifficulty()) * bonusMultiplier;

        score += bonusScore;
    }

    /**
     * Resets the current streak.
     */
    public void breakStreak() {
        if (isStart) {
            streak = 1;
        }
    }

    private int findExtraMovesLimit(int numberOfMatches) {
        int sum = IntStream.range(2, numberOfMatches + 1).sum();
        return sum / 2;
    }
}
