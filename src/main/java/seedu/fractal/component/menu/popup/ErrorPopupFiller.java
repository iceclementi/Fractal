package seedu.fractal.component.menu.popup;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class ErrorPopupFiller extends PopupFiller {

    private VBox errorPopupBox;

    /**
     * Constructor for the error popup filler.
     *
     * @param menuPane
     *  The main menu grid pane
     * @param errorPopupParentBox
     *  The parent VBox of the error popup
     * @param errorPopupBox
     *  The VBox of the error popup
     */
    public ErrorPopupFiller(GridPane menuPane, VBox errorPopupParentBox, VBox errorPopupBox) {
        super(menuPane, errorPopupParentBox);
        this.errorPopupBox = errorPopupBox;
        initialiseStyle();
    }

    /**
     * Fills the error popup.
     */
    public void fillPopup() {
        fillCloseSection();
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(errorPopupBox, FilePath.ERROR_FRAME_IMAGE_PATH);
    }

    private void fillCloseSection() {
        errorPopupBox.getChildren().add(generateCloseButton());
    }
}
