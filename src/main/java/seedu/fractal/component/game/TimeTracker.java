package seedu.fractal.component.game;

public class TimeTracker {

    private long startTime;

    private int returnTime;

    /**
     * Constructor for the time tracker.
     *
     * @param returnTime
     *  The time to continue from
     */
    public TimeTracker(int returnTime) {
        this.returnTime = returnTime;
    }

    /**
     * Gets the elapsed time recorded by the time tracker.
     *
     * @return
     *  The elapsed time recorded
     */
    public int getElapsedTime() {
        long endTime = System.currentTimeMillis();
        return computeElapsedTime(returnTime, endTime - startTime);
    }

    /**
     * Gets the string representation of the elapsed time recorded by the time tracker.
     *
     * @return
     *  The elapsed time recorded as a string
     */
    public String getTimeString() {
        int elapsedTime = getElapsedTime();

        if (elapsedTime == 0) {
            return "-";
        }

        int minutes = elapsedTime / 60;
        int seconds = elapsedTime % 60;

        return minutes == 0 ? String.format("%ss", seconds) : String.format("%sm %ss", minutes, seconds);
    }

    /**
     * Starts the time tracker.
     */
    public void start() {
        startTime = System.currentTimeMillis();
    }

    private int computeElapsedTime(int returnTime, long currentElapsedTime) {
        try {
            // Round to nearest second
            int elapsedTimeInSeconds = Math.toIntExact((currentElapsedTime + 500) / 1000);
            return returnTime + elapsedTimeInSeconds;
        } catch (ArithmeticException e) {
            return 0;
        }
    }
}
