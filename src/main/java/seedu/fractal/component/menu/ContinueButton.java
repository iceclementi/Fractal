package seedu.fractal.component.menu;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.SceneUtil;

public class ContinueButton extends MenuButton {

    public ContinueButton(String name) {
        super(name);

        initialiseEvents();
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {
        SceneUtil.changeScene(this, FilePath.GAME_SCENE_PATH);
    }
}
