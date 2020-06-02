package seedu.fractal.component.menu.button;

import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class HelpButton extends CustomButton {

    private GridPane menuPane;
    private VBox helpPopupParentBox;

    /**
     * Constructor of the help button.
     *
     * @param menuPane
     *  The grid pane of the menu screen
     * @param helpPopupParentBox
     *  The parent VBox of the help popup
     */
    public HelpButton(GridPane menuPane, VBox helpPopupParentBox) {
        super();
        this.menuPane = menuPane;
        this.helpPopupParentBox = helpPopupParentBox;

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(this, FilePath.HELP_BUTTON_IMAGE_PATH);
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {
        reset();
        helpPopupParentBox.setVisible(true);
        menuPane.setEffect(new BoxBlur(5, 5, 3));
    }
}
