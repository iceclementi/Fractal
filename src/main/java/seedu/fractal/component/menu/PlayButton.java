package seedu.fractal.component.menu;

import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import seedu.fractal.controller.GameController;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.SceneUtil;

public class PlayButton extends MenuButton {

    private GridPane menuPane;
    private GridPane popupPane;
    private DifficultySlider difficultySlider;
    private Spinner<Integer> spinner;

    public PlayButton(String name, DifficultySlider difficultySlider, Spinner<Integer> spinner) {
        super(name);
        this.difficultySlider = difficultySlider;
        this.spinner = spinner;
        initialiseEvents();
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {
        /* To be changed to appropriate file retrieval process */
        GameController.setDifficulty((int) difficultySlider.getValue());
        GameController.setNumberOfMatches(spinner.getValue());

        SceneUtil.changeScene(this, FilePath.GAME_SCENE_PATH);
    }


}
