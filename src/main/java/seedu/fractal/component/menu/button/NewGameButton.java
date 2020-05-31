package seedu.fractal.component.menu.button;

import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class NewGameButton extends MenuButton {

    private GridPane menuPane;
    private GridPane newGamePopupPane;

    /**
     * Constructor of the new game button.
     *
     * @param menuPane
     *  The grid pane of the menu screen
     * @param newGamePopupPane
     *  The grid pane of the new game popup
     */
    public NewGameButton(GridPane menuPane, GridPane newGamePopupPane) {
        super();
        this.menuPane = menuPane;
        this.newGamePopupPane = newGamePopupPane;

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(this, FilePath.NEW_GAME_BUTTON_IMAGE_PATH);
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {
        reset();
        newGamePopupPane.setVisible(true);
        menuPane.setEffect(new BoxBlur(5, 5, 3));
    }
}
