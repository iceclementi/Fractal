package seedu.fractal.component.game.button;

import javafx.scene.input.MouseEvent;
import seedu.fractal.component.menu.button.CustomButton;
import seedu.fractal.storage.FilePath;
import seedu.fractal.storage.Storage;
import seedu.fractal.util.ComponentUtil;
import seedu.fractal.util.SceneUtil;

public class GameBackButton extends CustomButton {

    /**
     * Constructor for the back button.
     */
    public GameBackButton() {

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        ComponentUtil.setStyleClass(this, FilePath.GAME_STYLE_PATH, "back-button");
        ComponentUtil.setBackground(this, FilePath.BACK_BUTTON_IMAGE_PATH);

        prefWidthProperty().bind(heightProperty());
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {
        reset();
        Storage.saveGame();
        SceneUtil.changeScene(this, FilePath.MENU_SCENE_PATH);
    }
}
