package seedu.fractal.component.game.button;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import seedu.fractal.component.menu.button.CustomButton;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class ContinueGameButton extends CustomButton {

    private GridPane gamePane;
    private VBox gameOverParentBox;

    public ContinueGameButton(GridPane gamePane, VBox gameOverParentBox) {
        super();
        this.gamePane = gamePane;
        this.gameOverParentBox = gameOverParentBox;

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(this, FilePath.CONTINUE_GAME_BUTTON_IMAGE_PATH);
        setPrefSize(300, 50);
    }

    private void initialiseEvents() {
        setOnMouseReleased(this::onRelease);
    }

    private void onRelease(MouseEvent mouseEvent) {
        reset();
        gamePane.setEffect(null);
        gameOverParentBox.setVisible(false);
    }
}
