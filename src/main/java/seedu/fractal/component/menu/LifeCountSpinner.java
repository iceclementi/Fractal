package seedu.fractal.component.menu;

import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import seedu.fractal.component.game.GameBoard;
import seedu.fractal.component.menu.button.WithLifeButton;
import seedu.fractal.component.menu.button.WithoutLifeButton;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class LifeCountSpinner extends Spinner<Integer> {

    private WithLifeButton withLifeButton;
    private WithoutLifeButton withoutLifeButton;
    private static final int DEFAULT_LIFE_COUNT = 3;

    private VBox withLifeBox;
    private VBox withoutLifeBox;
    private GameModeToggle gameModeToggle;

    /**
     * Constructor for the life count spinner.
     */
    public LifeCountSpinner(VBox withLifeBox, VBox withoutLifeBox, GameModeToggle gameModeToggle) {
        super(0, 5, 3);
        this.withLifeBox = withLifeBox;
        this.withoutLifeBox = withoutLifeBox;
        this.gameModeToggle = gameModeToggle;

        initialiseStyle();
        initialiseEvents();
    }

    /**
     * Sets the value of the life count spinner to the default normal mode value.
     */
    public void setNormalMode() {
        getValueFactory().setValue(DEFAULT_LIFE_COUNT);
    }

    private void initialiseStyle() {
        ComponentUtil.setStyleClass(this, FilePath.MENU_STYLE_PATH, "count-spinner");
        getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);

        setPrefWidth(150);

        getValueFactory().setValue(GameBoard.getInstance().getNumberOfLives());

        withLifeButton = new WithLifeButton();
        withLifeBox.getChildren().add(withLifeButton);

        withoutLifeButton = new WithoutLifeButton(this);
        withoutLifeBox.getChildren().add(withoutLifeButton);
    }

    private void initialiseEvents() {
        toggle();
        getValueFactory().valueProperty().addListener((observable, oldValue, newValue) -> {
            toggle();

            if (newValue != DEFAULT_LIFE_COUNT) {
                gameModeToggle.togglePracticeMode();
            }
        });
    }

    private void toggle() {
        if (getValueFactory().getValue() == 0) {
            withLifeButton.deactivate();
            withoutLifeButton.activate();
        } else {
            withLifeButton.activate();
            withoutLifeButton.deactivate();
        }
    }
}
