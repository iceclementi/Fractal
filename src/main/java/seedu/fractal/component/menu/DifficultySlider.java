package seedu.fractal.component.menu;

import javafx.scene.control.Slider;
import javafx.util.StringConverter;
import seedu.fractal.storage.FilePath;

public class DifficultySlider extends Slider {

    public DifficultySlider() {
        super();

        initialiseStyle();
        initialiseEvents();
    }


    private void initialiseStyle() {
        /* Set CSS */
        getStylesheets().add(getClass().getResource(FilePath.MENU_STYLE_PATH).toExternalForm());
        getStyleClass().add("difficulty-slider");


        /* Set range */
        setMin(0);
        setMax(3);
        setValue(0);

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

        setPrefWidth(800);
    }


    private void initialiseEvents() {

    }

}