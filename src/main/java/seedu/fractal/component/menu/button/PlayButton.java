package seedu.fractal.component.menu.button;

import javafx.scene.input.MouseEvent;
import seedu.fractal.component.game.GameBoard;
import seedu.fractal.component.menu.DifficultySlider;
import seedu.fractal.component.menu.MatchCountSpinner;
import seedu.fractal.component.menu.OptionCheckBoxGroup;
import seedu.fractal.storage.FilePath;
import seedu.fractal.storage.Storage;
import seedu.fractal.util.ComponentUtil;
import seedu.fractal.util.SceneUtil;

public class PlayButton extends MenuButton {

    private DifficultySlider difficultySlider;
    private MatchCountSpinner spinner;
    private OptionCheckBoxGroup advancedOptions;

    /**
     * Constructor of the play button.
     *
     * @param difficultySlider
     *  The difficulty slider on the new game popup
     * @param spinner
     *  The match count spinner on the new game popup
     */
    public PlayButton(DifficultySlider difficultySlider, MatchCountSpinner spinner,
          OptionCheckBoxGroup advancedOptions) {
        super();
        this.difficultySlider = difficultySlider;
        this.spinner = spinner;
        this.advancedOptions = advancedOptions;

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
        GameBoard.getInstance().setDetails(difficultySlider.getDifficulty(), spinner.getValue(),
                advancedOptions.getSelectedOptions());
        Storage.saveGameDetails(difficultySlider.getDifficulty(), spinner.getValue(),
                advancedOptions.getSelectedOptions(), false);

        SceneUtil.changeScene(this, FilePath.GAME_SCENE_PATH);
    }
}
