package seedu.fractal.component.menu.popup;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import seedu.fractal.component.menu.button.CloseButton;

public abstract class PopupFiller {

    private GridPane menuPane;
    private VBox popupParentBox;

    /**
     * Constructor of the abstract popup filler.
     *
     * @param menuPane
     *  The grid pane of the menu screen
     * @param popupParentBox
     *  The parent VBox of the popup box
     */
    public PopupFiller(GridPane menuPane, VBox popupParentBox) {
        this.menuPane = menuPane;
        this.popupParentBox = popupParentBox;
    }

    /**
     * Fills the popup.
     */
    public abstract void fillPopup();

    /**
     * Generates the close button for the popup.
     *
     * @return
     *  The close button
     */
    protected CloseButton generateCloseButton() {
        return new CloseButton(menuPane, popupParentBox);
    }
}
