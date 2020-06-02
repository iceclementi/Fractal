package seedu.fractal.component.menu;

import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import seedu.fractal.component.menu.button.WithLifeButton;
import seedu.fractal.component.menu.button.WithoutLifeButton;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class LifeCountSpinner extends Spinner<Integer> {

    private WithLifeButton withLifeButton;
    private WithoutLifeButton withoutLifeButton;

    private VBox withLifeBox;
    private VBox withoutLifeBox;

    /**
     * Constructor for the life count spinner.
     */
    public LifeCountSpinner(VBox withLifeBox, VBox withoutLifeBox) {
        super(0, 5, 3);
        this.withLifeBox = withLifeBox;
        this.withoutLifeBox = withoutLifeBox;

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        ComponentUtil.setStyleClass(this, FilePath.MENU_STYLE_PATH, "count-spinner");
        getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);

        setPrefWidth(150);

        withLifeButton = new WithLifeButton();
        withLifeBox.getChildren().add(withLifeButton);

        withoutLifeButton = new WithoutLifeButton(this);
        withoutLifeBox.getChildren().add(withoutLifeButton);
    }

    private void initialiseEvents() {
        toggle();
        getValueFactory().valueProperty().addListener(observable -> toggle());
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
