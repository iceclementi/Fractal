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
     *
     * @param lifeBox
     *  The HBox containing the lives
     */
    public LifeManager(HBox lifeBox) {
        this.lifeBox = lifeBox;
    }

    /**
     * Initialises the number of lives and current number of lives in the game.
     *
     * @param numberOfLives
     *  The total number of lives in the game
     * @param currentNumberOfLives
     *  The current number of lives
     */
    public void initialise(int numberOfLives, int currentNumberOfLives) {
        this.numberOfLives = numberOfLives;
        this.currentNumberOfLives = currentNumberOfLives;

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

    private void initialiseLives() {
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
