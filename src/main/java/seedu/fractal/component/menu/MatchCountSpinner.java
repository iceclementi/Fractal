package seedu.fractal.component.menu;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import seedu.fractal.component.game.GameBoard;
import seedu.fractal.storage.FilePath;

public class MatchCountSpinner extends Spinner<Integer> {

    private static final int EASY_MAX = 11;
    private static final int INTERMEDIATE_MAX = 17;
    private static final int ADVANCED_MAX = 20;
    private static final int GENIUS_MAX = 20;
    private static final int DEFAULT_MAX = 11;

    private DifficultySlider difficultySlider;

    /**
     * Constructor for the match count spinner.
     *
     * @param difficultySlider
     *  The difficulty slider in the new game popup
     */
    public MatchCountSpinner(DifficultySlider difficultySlider) {
        super(2, 11, 4);
        this.difficultySlider = difficultySlider;

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        getStylesheets().add(getClass().getResource(FilePath.MENU_STYLE_PATH).toExternalForm());
        getStyleClass().add("match-count-spinner");
        getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
    }

    private void initialiseEvents() {
        SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory =
                (SpinnerValueFactory.IntegerSpinnerValueFactory) getValueFactory();

        setMaxValue(spinnerValueFactory);
        spinnerValueFactory.setValue(GameBoard.getInstance().getNumberOfMatches());

        /* Changes maximum value according to difficulty level set */
        difficultySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            setMaxValue(spinnerValueFactory);
        });
    }

    private void setMaxValue(SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory) {
        switch (difficultySlider.getDifficulty()) {
        case EASY:
            spinnerValueFactory.setMax(EASY_MAX);
            break;
        case INTERMEDIATE:
            spinnerValueFactory.setMax(INTERMEDIATE_MAX);
            break;
        case ADVANCED:
            spinnerValueFactory.setMax(ADVANCED_MAX);
            break;
        case GENIUS:
            spinnerValueFactory.setMax(GENIUS_MAX);
            break;
        default:
            spinnerValueFactory.setMax(DEFAULT_MAX);
        }
    }

}
