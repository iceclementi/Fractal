package seedu.fractal.component.menu;

import javafx.scene.layout.GridPane;
import seedu.fractal.component.menu.button.CloseButton;

public abstract class PopupBoxFiller {

    private GridPane menuPane;
    private GridPane popupPane;

    public PopupBoxFiller(GridPane menuPane, GridPane popupPane) {
        this.menuPane = menuPane;
        this.popupPane = popupPane;
    }

    protected CloseButton generateCloseButton() {
        return new CloseButton(menuPane, popupPane);
    }

    public abstract void fillPopup();
}
