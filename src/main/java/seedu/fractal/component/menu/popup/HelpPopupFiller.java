package seedu.fractal.component.menu.popup;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class HelpPopupFiller extends PopupFiller {

    private VBox helpPopupBox;

    /**
     * Constructor for the help popup filler.
     *
     * @param menuPane
     *  The main menu grid pane
     * @param helpPopupParentBox
     *  The parent VBox of the help popup
     * @param helpPopupBox
     *  The VBox of the help popup
     */
    public HelpPopupFiller(GridPane menuPane, VBox helpPopupParentBox, VBox helpPopupBox) {
        super(menuPane, helpPopupParentBox);
        this.helpPopupBox = helpPopupBox;

        initialiseStyle();
    }

    /**
     * Fills the help popup.
     */
    public void fillPopup() {
        fillCloseSection();
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(helpPopupBox, FilePath.HELP_FRAME_IMAGE_PATH);
    }

    private void fillCloseSection() {
        helpPopupBox.getChildren().add(generateCloseButton());
    }
}
