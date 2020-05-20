package seedu.fractal.component.menu.button;

import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import seedu.fractal.component.game.GameBoard;
import seedu.fractal.component.menu.DifficultySlider;
import seedu.fractal.controller.GameController;
import seedu.fractal.storage.FilePath;
import seedu.fractal.storage.Storage;
import seedu.fractal.util.SceneUtil;

public class PlayButton extends MenuButton {

    private GridPane menuPane;
    private GridPane popupPane;
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
        setButtonBackground(FilePath.PLAY_BUTTON_IMAGE_PATH);
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {
        /* To be changed to appropriate file retrieval process */
        GameController.setDifficulty(difficultySlider.getDifficulty());
        GameController.setNumberOfMatches(spinner.getValue());

        GameBoard.getInstance().setDetails(difficultySlider.getDifficulty(), spinner.getValue());
        Storage.saveGameDetails();

        SceneUtil.changeScene(this, FilePath.GAME_SCENE_PATH);
    }


}
