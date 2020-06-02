package seedu.fractal.component.menu.button;

import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class NewGameButton extends CustomButton {

    private GridPane menuPane;
    private VBox newGamePopupParentBox;

    /**
     * Constructor of the new game button.
     *
     * @param menuPane
     *  The grid pane of the menu screen
     * @param newGamePopupParentBox
     *  The grid pane of the new game popup
     */
    public NewGameButton(GridPane menuPane, VBox newGamePopupParentBox) {
        super();
        this.menuPane = menuPane;
        this.newGamePopupParentBox = newGamePopupParentBox;

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
        newGamePopupParentBox.setVisible(true);
        menuPane.setEffect(new BoxBlur(5, 5, 3));
    }
}
