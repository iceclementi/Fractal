package seedu.fractal.component.menu;

import javafx.scene.input.MouseEvent;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.SceneUtil;

public class StartButton extends MenuButton {

    public StartButton(String name) {
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
