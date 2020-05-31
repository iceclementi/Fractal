package seedu.fractal.component.game;

import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class LifeManager {

    private int numberOfLives = 3;
    private int currentNumberOfLives = 3;
    private ArrayList<Life> lives = new ArrayList<>();

    private HBox lifeBox;

    public LifeManager(HBox lifeBox) {
        this.lifeBox = lifeBox;
    }

    public void initialise(int numberOfLives, int currentNumberOfLives) {
        this.numberOfLives = numberOfLives;
        this.currentNumberOfLives = currentNumberOfLives;

        initialiseLives();
    }

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

    private void setNumberOfLives(int numberOfLives, int currentNumberOfLives) {
        this.numberOfLives = numberOfLives;
        this.currentNumberOfLives = currentNumberOfLives;
    }
}
