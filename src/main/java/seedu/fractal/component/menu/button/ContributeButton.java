package seedu.fractal.component.menu.button;

import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class ContributeButton extends CustomButton {

    private GridPane menuPane;
    private VBox contributePopupParentBox;

    /**
     * Constructor for the contribute button.
     *
     * @param menuPane
     *  The grid pane of the menu screen
     * @param contributePopupParentBox
     *  The parent VBox of the contribute popup
     */
    public ContributeButton(GridPane menuPane, VBox contributePopupParentBox) {
        super();
        this.menuPane = menuPane;
        this.contributePopupParentBox = contributePopupParentBox;

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(this, FilePath.CONTRIBUTE_BUTTON_IMAGE_PATH);
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {
        reset();
        contributePopupParentBox.setVisible(true);
        menuPane.setEffect(new BoxBlur(5, 5, 3));
    }
}
