package seedu.fractal.component.game.button;

import javafx.scene.input.MouseEvent;
import seedu.fractal.component.menu.button.CustomButton;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;
import seedu.fractal.util.SceneUtil;

public class BackToMainButton extends CustomButton {

    public BackToMainButton() {
        super();

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(this, FilePath.MAIN_BUTTON_IMAGE_PATH);
        setPrefSize(300, 50);
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {
        SceneUtil.changeScene(this, FilePath.MENU_SCENE_PATH);
    }
}
