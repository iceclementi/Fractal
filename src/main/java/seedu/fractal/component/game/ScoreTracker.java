package seedu.fractal.component.game;

public class ScoreTracker {

    private int score = 0;
    private int streak = 1;
    private boolean isStop = true;

    private static final int STREAK_INCREMENT = 100;

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

        isStop = false;
    }

    /**
     * Stops tracking the score of the game.
     */
    public void stop() {
        isStop = true;
    }

    /**
     * Increases the current score of the game.
     */
    public void addScore() {
        if (isStop) {
            return;
        }

        score += streak * STREAK_INCREMENT;
        ++streak;
    }

    /**
     * Resets the current streak.
     */
    public void breakStreak() {
        if (isStop) {
            return;
        }

        streak = 1;
    }
}
