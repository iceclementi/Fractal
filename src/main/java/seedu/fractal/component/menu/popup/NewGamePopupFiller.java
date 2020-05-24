package seedu.fractal.component.menu.popup;

import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.fractal.component.menu.DifficultySlider;
import seedu.fractal.component.menu.MatchCountSpinner;
import seedu.fractal.component.menu.button.PlayButton;

public class NewGamePopupFiller extends PopupFiller {

    private HBox closeNewGameBox;
    private VBox difficultySliderBox;
    private Label difficultyHeader;
    private VBox spinnerBox;
    private Label matchCountHeader;
    private VBox advancedOptionsBox;
    private Label advancedOptionsHeader;
    private VBox playBox;

    private DifficultySlider difficultySlider;
    private MatchCountSpinner matchCountSpinner;

    public NewGamePopupFiller(GridPane menuPane, GridPane newGamePopupPane,
          HBox closeNewGameBox, VBox difficultySliderBox, Label difficultyHeader, VBox spinnerBox,
          Label matchCountHeader, VBox advancedOptionsBox, Label advancedOptionsHeader, VBox playBox) {
        super(menuPane, newGamePopupPane);
        this.closeNewGameBox = closeNewGameBox;
        this.difficultySliderBox = difficultySliderBox;
        this.difficultyHeader = difficultyHeader;
        this.spinnerBox = spinnerBox;
        this.matchCountHeader = matchCountHeader;
        this.advancedOptionsBox = advancedOptionsBox;
        this.advancedOptionsHeader = advancedOptionsHeader;
        this.playBox = playBox;
    }

    public void fillPopup() {
        fillCloseSection();
        fillDifficultySection();
        fillMatchCountSection();
        fillAdvancedOptionsSection();
        fillPlaySection();
    }

    private void fillCloseSection() {
        closeNewGameBox.getChildren().add(generateCloseButton());
    }

    private void fillDifficultySection() {
        setHeader(difficultyHeader, "DIFFICULTY");
        difficultySlider = new DifficultySlider();
        difficultySliderBox.getChildren().add(difficultySlider);
    }

    private void fillMatchCountSection() {
        setHeader(matchCountHeader, "NUMBER OF MATCHES");
        matchCountSpinner = new MatchCountSpinner(difficultySlider);
        spinnerBox.getChildren().add(matchCountSpinner);
    }

    private void fillAdvancedOptionsSection() {
        setHeader(advancedOptionsHeader, "ADVANCED OPTIONS");
        advancedOptionsBox.setDisable(true);
    }

    private void fillPlaySection() {
        PlayButton playButton = new PlayButton(difficultySlider, matchCountSpinner);
        playBox.getChildren().add(playButton);
    }
}
