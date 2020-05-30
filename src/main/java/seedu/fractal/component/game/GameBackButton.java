package seedu.fractal.component.game;

import javafx.scene.input.MouseEvent;
import seedu.fractal.component.menu.button.MenuButton;
import seedu.fractal.storage.FilePath;
import seedu.fractal.storage.Storage;
import seedu.fractal.util.ComponentUtil;
import seedu.fractal.util.SceneUtil;

public class GameBackButton extends MenuButton {

    /**
     * Constructor for the back button.
     */
    public GameBackButton() {

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        getStylesheets().clear();
        getStyleClass().clear();
        getStylesheets().add(getClass().getResource(FilePath.GAME_STYLE_PATH).toExternalForm());
        getStyleClass().add("back-button");

        ComponentUtil.setButtonBackground(this, FilePath.BACK_BUTTON_IMAGE_PATH);
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
