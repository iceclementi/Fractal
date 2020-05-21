package seedu.fractal.component.menu.button;

import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import seedu.fractal.component.game.GameBoard;
import seedu.fractal.component.menu.DifficultySlider;
import seedu.fractal.storage.FilePath;
import seedu.fractal.storage.Storage;
import seedu.fractal.util.ComponentUtil;
import seedu.fractal.util.SceneUtil;

public class PlayButton extends MenuButton {

    private DifficultySlider difficultySlider;
    private Spinner<Integer> spinner;

    public PlayButton(DifficultySlider difficultySlider, Spinner<Integer> spinner) {
        super();
        this.difficultySlider = difficultySlider;
        this.spinner = spinner;

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
        GameBoard.getInstance().setDetails(difficultySlider.getDifficulty(), spinner.getValue());
        Storage.saveGameDetails(difficultySlider.getDifficulty(), spinner.getValue());

        SceneUtil.changeScene(this, FilePath.GAME_SCENE_PATH);
    }


}
