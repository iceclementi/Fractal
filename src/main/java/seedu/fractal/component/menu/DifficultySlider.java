package seedu.fractal.component.menu;

import javafx.scene.control.Slider;
import javafx.util.StringConverter;
import seedu.fractal.component.game.GameBoard;
import seedu.fractal.logic.Difficulty;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class DifficultySlider extends Slider {

    /**
     * Constructor for the difficulty slider.
     */
    public DifficultySlider() {
        super();

        initialiseStyle();
    }

    public Difficulty getDifficulty() {
        switch ((int) getValue()) {
        case 0:
            return Difficulty.EASY;
        case 1:
            return Difficulty.INTERMEDIATE;
        case 2:
            return Difficulty.ADVANCED;
        case 3:
            return Difficulty.GENIUS;
        default:
            System.out.println("DifficultySlider: Unknown difficulty... Selecting EASY instead.");
            return Difficulty.EASY;
        }
    }

    public void setDifficulty(Difficulty difficulty) {
        switch (difficulty) {
        case EASY:
            setValue(0);
            return;
        case INTERMEDIATE:
            setValue(1);
            return;
        case ADVANCED:
            setValue(2);
            return;
        case GENIUS:
            setValue(3);
            return;
        default:
            setValue(0);
        }
    }

    private void initialiseStyle() {
        /* Set CSS */
        ComponentUtil.setStyleClass(this, FilePath.MENU_STYLE_PATH, "difficulty-slider");

        /* Set range */
        setMin(0);
        setMax(3);
        setDifficulty(GameBoard.getInstance().getDifficulty());

        setMinorTickCount(0);
        setMajorTickUnit(1);
        setSnapToTicks(true);
        setShowTickMarks(true);
        setShowTickLabels(true);

        /* Add difficulty labels */
        setLabelFormatter(new StringConverter<>() {
            @Override
            public String toString(Double value) {
                if (value < 0.5) {
                    return "Easy";
                } else if (value < 1.5) {
                    return "Intermediate";
                } else if (value < 2.5) {
                    return "Advanced";
                } else {
                    return "Genius";
                }
            }

            @Override
            public Double fromString(String labelString) {
                switch (labelString) {
                case "Easy":
                    return 0d;
                case "Intermediate":
                    return 1d;
                case "Advanced":
                    return 2d;
                case "Genius":
                    return 3d;
                default:
                    return 0d;
                }
            }
        });

        setPrefWidth(400);
    }
}
