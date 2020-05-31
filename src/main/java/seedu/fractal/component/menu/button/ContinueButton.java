package seedu.fractal.component.menu.button;

import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import seedu.fractal.component.game.GameBoard;
import seedu.fractal.storage.FilePath;
import seedu.fractal.storage.Storage;
import seedu.fractal.util.ComponentUtil;
import seedu.fractal.util.SceneUtil;

public class ContinueButton extends MenuButton {

    private GridPane menuPane;
    private GridPane errorPopupPane;

    /**
     * Constructor for the continue button.
     *
     * @param menuPane
     *  The grid pane of the menu screen
     * @param errorPopupPane
     *  The grid pane of error popup
     */
    public ContinueButton(GridPane menuPane, GridPane errorPopupPane) {
        super();
        this.menuPane = menuPane;
        this.errorPopupPane = errorPopupPane;

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(this, FilePath.CONTINUE_BUTTON_IMAGE_PATH);

        if (!GameBoard.getInstance().isOngoing()) {
            setDisable(true);
        }
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {
        try {
            Storage.loadGameDetails();
            Storage.loadGame();
            SceneUtil.changeScene(this, FilePath.GAME_SCENE_PATH);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorPopupPane.setVisible(true);
            menuPane.setEffect(new BoxBlur(5, 5, 3));
        }
    }
}
