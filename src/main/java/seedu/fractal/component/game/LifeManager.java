package seedu.fractal.component.game;

import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class LifeManager {

    private int numberOfLives = 3;
    private int currentNumberOfLives = 3;
    private ArrayList<Life> lives = new ArrayList<>();

    private HBox lifeBox;

    /**
     * Constructor for the life manager that manages operations with the lives in the game.
     */
    public LifeManager() {
    }

    public int getNumberOfLives() {
        return numberOfLives;
    }

    public void setNumberOfLives(int numberOfLives) {
        this.numberOfLives = numberOfLives;
    }

    public int getCurrentNumberOfLives() {
        return currentNumberOfLives;
    }

    public void setCurrentNumberOfLives(int currentNumberOfLives) {
        this.currentNumberOfLives = currentNumberOfLives;
    }

    /**
     * Initialises the number of lives and current number of lives in the game.
     *
     * @param lifeBox
     *  The HBox containing the lives
     */
    public void initialise(HBox lifeBox) {
        this.lifeBox = lifeBox;

        initialiseLives();
    }

    /**
     * Lose a single life.
     */
    public void loseLife() {
        if (currentNumberOfLives <= 0) {
            return;
        }

        lives.get(--currentNumberOfLives).setDisable(true);

        if (currentNumberOfLives == 0) {
            System.out.println("Game Over - Current health: " + currentNumberOfLives);
            GameBoard.getInstance().gameOver();
        }
    }

    /**
     * Resets the life information.
     */
    public void reset() {
        currentNumberOfLives = numberOfLives;
    }

    private void initialiseLives() {
        lifeBox.getChildren().clear();
        lives.clear();

        for (int i = 0; i < numberOfLives; ++i) {
            Life life = new Life();
            lives.add(life);
            lifeBox.getChildren().add(life);
        }

        for (int i = currentNumberOfLives; i < numberOfLives; ++i) {
            lives.get(i).setDisable(true);
        }
    }
}
