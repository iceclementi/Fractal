package seedu.fractal.component.menu.button;

import javafx.scene.input.MouseEvent;
import seedu.fractal.component.game.GameBoard;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;
import seedu.fractal.util.SceneUtil;

public class ContinueButton extends MenuButton {

    public ContinueButton() {
        super();

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        ComponentUtil.setButtonBackground(this, FilePath.CONTINUE_BUTTON_IMAGE_PATH);

        if (!GameBoard.getInstance().isOngoing()) {
            setDisable(true);
        }
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {

        SceneUtil.changeScene(this, FilePath.GAME_SCENE_PATH);
    }
}