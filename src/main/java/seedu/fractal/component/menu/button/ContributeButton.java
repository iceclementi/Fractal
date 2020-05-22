package seedu.fractal.component.menu.button;

import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class ContributeButton extends MenuButton {

    private GridPane menuPane;
    private GridPane contributePopupPane;

    /**
     * Constructor for the contribute button.
     *
     * @param menuPane
     *  The grid pane of the menu screen
     * @param contributePopupPane
     *  The grid pane of contribute popup
     */
    public ContributeButton(GridPane menuPane, GridPane contributePopupPane) {
        super();
        this.menuPane = menuPane;
        this.contributePopupPane = contributePopupPane;

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        ComponentUtil.setButtonBackground(this, FilePath.CONTRIBUTE_BUTTON_IMAGE_PATH);
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {
        reset();
        contributePopupPane.setVisible(true);
        menuPane.setEffect(new BoxBlur(5, 5, 3));
    }
}
