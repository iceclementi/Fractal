package seedu.fractal.component.menu.popup;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;

public class ErrorPopupFiller extends PopupFiller {

    private static final String CORRUPTED_FILE_ERROR_TEXT =
        "There was an error when loading the game. Please start a new game instead.";

    private HBox closeErrorBox;
    private Label errorHeader;
    private TextFlow errorText;

    public ErrorPopupFiller(GridPane menuPane, GridPane errorPopupPane, HBox closeErrorBox,
            Label errorHeader, TextFlow errorText) {
        super(menuPane, errorPopupPane);
        this.closeErrorBox = closeErrorBox;
        this.errorHeader = errorHeader;
        this.errorText = errorText;
    }

    public void fillPopup() {
        fillCloseSection();
        fillContributeSection();
    }

    private void fillCloseSection() {
        closeErrorBox.getChildren().add(generateCloseButton());
    }

    private void fillContributeSection() {
        setHeader(errorHeader, "ERROR!");
        setTextFlow(errorText, CORRUPTED_FILE_ERROR_TEXT);
    }
}
