package seedu.fractal.component.menu.popup;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class ContributePopupFiller extends PopupFiller {

    private VBox contributePopupBox;

    /**
     * Constructor for the contribute popup filler.
     *
     * @param menuPane
     *  The main menu grid pane
     * @param contributePopupParentBox
     *  The parent VBox of the contribute popup
     * @param contributePopupBox
     *  The VBox of the contribute popup
     */
    public ContributePopupFiller(GridPane menuPane, VBox contributePopupParentBox, VBox contributePopupBox) {
        super(menuPane, contributePopupParentBox);
        this.contributePopupBox = contributePopupBox;

        initialiseStyle();
    }

    /**
     * Fills the contribute popup.
     */
    public void fillPopup() {
        fillCloseSection();
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(contributePopupBox, FilePath.CONTRIBUTE_FRAME_IMAGE_PATH);
    }

    private void fillCloseSection() {
        contributePopupBox.getChildren().add(generateCloseButton());
    }
}
