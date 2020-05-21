package seedu.fractal.component.menu.button;

import javafx.scene.input.MouseEvent;
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
        ComponentUtil.setBackground(this, FilePath.CONTINUE_BUTTON_IMAGE_PATH);
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {
        SceneUtil.changeScene(this, FilePath.GAME_SCENE_PATH);
    }
}
