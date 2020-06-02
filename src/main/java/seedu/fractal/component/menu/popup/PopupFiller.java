package seedu.fractal.component.menu.popup;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import seedu.fractal.component.menu.button.CloseButton;
import seedu.fractal.storage.FilePath;

public abstract class PopupFiller {

    private GridPane menuPane;
    private Parent popupPane;

    public PopupFiller(GridPane menuPane, Parent popupPane) {
        this.menuPane = menuPane;
        this.popupPane = popupPane;
    }

    public abstract void fillPopup();

    protected CloseButton generateCloseButton() {
        return new CloseButton(menuPane, popupPane);
    }

    protected void setHeader(Label header, String name) {
        header.getStylesheets().add(getClass().getResource(FilePath.MENU_STYLE_PATH).toExternalForm());
        header.getStyleClass().add("popup-header");
        header.setText(name);
    }

    protected void setTextFlow(TextFlow textflow, String content) {
        Text text = new Text(content);
        text.setFill(Color.LIGHTSLATEGREY);
        textflow.getStylesheets().add(getClass().getResource(FilePath.MENU_STYLE_PATH).toExternalForm());
        textflow.getStyleClass().add("text-flow");

        textflow.setTextAlignment(TextAlignment.CENTER);
        textflow.getChildren().add(text);
    }
}
