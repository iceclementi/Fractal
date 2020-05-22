package seedu.fractal.component.menu.button;

import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class HelpButton extends MenuButton {

    private GridPane menuPane;
    private GridPane helpPopupPane;

    public HelpButton(GridPane menuPane, GridPane helpPopupPane) {
        super();
        this.menuPane = menuPane;
        this.helpPopupPane = helpPopupPane;

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
        helpPopupPane.setVisible(true);
        menuPane.setEffect(new BoxBlur(5, 5, 3));
    }

}
