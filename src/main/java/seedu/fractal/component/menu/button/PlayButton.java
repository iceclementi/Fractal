package seedu.fractal.component.menu.button;

import javafx.scene.input.MouseEvent;
import seedu.fractal.component.menu.DifficultySlider;
import seedu.fractal.component.menu.GameModeToggle;
import seedu.fractal.component.menu.LifeCountSpinner;
import seedu.fractal.component.menu.MatchCountSpinner;
import seedu.fractal.component.menu.OptionCheckBoxGroup;
import seedu.fractal.storage.FilePath;
import seedu.fractal.storage.Storage;
import seedu.fractal.util.ComponentUtil;
import seedu.fractal.util.SceneUtil;

public class PlayButton extends CustomButton {

    private DifficultySlider difficultySlider;
    private MatchCountSpinner matchCountSpinner;
    private OptionCheckBoxGroup cardTypeOptions;
    private LifeCountSpinner lifeCountSpinner;
    private GameModeToggle gameModeToggle;

    /**
     * Constructor of the play button.
     *
     * @param difficultySlider
     *  The difficulty slider on the new game popup
     * @param matchCountSpinner
     *  The match count spinner on the new game popup
     * @param cardTypeOptions
     *  The group of checkboxes about the card types on the new game popup
     * @param lifeCountSpinner
     *  The life count spinner on the new game popup
     */
    public PlayButton(DifficultySlider difficultySlider, MatchCountSpinner matchCountSpinner,
            OptionCheckBoxGroup cardTypeOptions, LifeCountSpinner lifeCountSpinner, GameModeToggle gameModeToggle) {
        super();
        this.difficultySlider = difficultySlider;
        this.matchCountSpinner = matchCountSpinner;
        this.cardTypeOptions = cardTypeOptions;
        this.lifeCountSpinner = lifeCountSpinner;
        this.gameModeToggle = gameModeToggle;

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(this, FilePath.PLAY_BUTTON_IMAGE_PATH);
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {
        Storage.saveGameDetails(difficultySlider.getDifficulty(), matchCountSpinner.getValue(),
                cardTypeOptions.getSelectedOptions(), lifeCountSpinner.getValue(), gameModeToggle.getGameMode(),  false);

        SceneUtil.changeScene(this, FilePath.GAME_SCENE_PATH);
    }
}
