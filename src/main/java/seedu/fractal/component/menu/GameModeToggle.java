package seedu.fractal.component.menu;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import seedu.fractal.logic.GameMode;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class GameModeToggle extends Slider {

    private GameMode previousSelection;

    private OptionCheckBoxGroup cardTypeOptions;
    private LifeCountSpinner lifeCountSpinner;
    private Label normalGameMode;
    private Label practiceGameMode;

    /**
     * Constructor for the difficulty slider.
     *
     * @param normalGameMode
     *  The label for the normal game mode
     * @param practiceGameMode
     *  The label for the practice mode
     */
    public GameModeToggle(Label normalGameMode, Label practiceGameMode) {
        super();
        this.normalGameMode = normalGameMode;
        this.practiceGameMode = practiceGameMode;

        initialiseStyle();

        previousSelection = getGameMode();
    }

    /**
     * Gets the selected game mode from the toggle.
     *
     * @return
     *  The selected game mode
     */
    public GameMode getGameMode() {
        switch ((int) getValue()) {
        case 0:
            return GameMode.NORMAL;
        case 1:
            return GameMode.PRACTICE;
        default:
            System.out.println("GameModeToggle: Unknown game mode... Setting to NORMAL instead.");
            return GameMode.NORMAL;
        }
    }

    /**
     * Sets the game mode on the toggle.
     *
     * @param gameMode
     *  The game mode of the game
     */
    public void setGameMode(GameMode gameMode) {
        switch (gameMode) {
        case NORMAL:
            setValue(0);
            return;
        case PRACTICE:
            setValue(1);
            return;
        default:
            setValue(0);
        }
    }

    /**
     * Initialises the game mode toggle to link to the other components in the advanced options section.
     *
     * @param cardTypeOptions
     *  The checkbox group about the card type options on the new game popup
     * @param lifeCountSpinner
     *  The life count spinner on the new game popup
     */
    public void initialise(OptionCheckBoxGroup cardTypeOptions, LifeCountSpinner lifeCountSpinner) {
        this.cardTypeOptions = cardTypeOptions;
        this.lifeCountSpinner = lifeCountSpinner;

        initialiseEvents();
    }

    /**
     * Toggles to the practice mode.
     */
    public void togglePracticeMode() {
        setGameMode(GameMode.PRACTICE);

        previousSelection = getGameMode();
    }

    private void initialiseStyle() {
        ComponentUtil.setStyleClass(this, FilePath.MENU_STYLE_PATH, "game-mode-toggle");

        /* Set range */
        setMin(0);
        setMax(1);
        setGameMode(GameMode.NORMAL);

        setMinorTickCount(0);
        setMajorTickUnit(1);
        setSnapToTicks(true);
        setShowTickMarks(false);
        setShowTickLabels(false);

        /* Set Label style */
        ComponentUtil.setStyleClass(normalGameMode, FilePath.MENU_STYLE_PATH, "game-mode-label");
        normalGameMode.setText("NORMAL");

        ComponentUtil.setStyleClass(practiceGameMode, FilePath.MENU_STYLE_PATH, "game-mode-label");
        practiceGameMode.setText("PRACTICE");

    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);

        toggle();
        valueProperty().addListener(observable -> toggle());
    }

    private void onRelease(MouseEvent mouseEvent) {
        if (getGameMode() == previousSelection) {
            setGameMode((getGameMode() == GameMode.NORMAL) ? GameMode.PRACTICE : GameMode.NORMAL);
        }

        previousSelection = getGameMode();

        if (getGameMode() == GameMode.NORMAL) {
            cardTypeOptions.setNormalMode();
            lifeCountSpinner.setNormalMode();
        }
    }

    private void toggle() {
        normalGameMode.setDisable(getGameMode() == GameMode.PRACTICE);
        practiceGameMode.setDisable(getGameMode() == GameMode.NORMAL);
    }
}
