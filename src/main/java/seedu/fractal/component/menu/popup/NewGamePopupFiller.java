package seedu.fractal.component.menu.popup;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.fractal.component.menu.DifficultySlider;
import seedu.fractal.component.menu.MatchCountSpinner;
import seedu.fractal.component.menu.OptionCheckBoxGroup;
import seedu.fractal.component.menu.button.PlayButton;

public class NewGamePopupFiller extends PopupFiller {

    private HBox closeNewGameBox;
    private Label difficultyHeader;
    private VBox difficultySliderBox;
    private Label matchCountHeader;
    private VBox spinnerBox;
    private Label advancedOptionsHeader;
    private HBox advancedOptionsBox;
    private VBox playBox;

    private DifficultySlider difficultySlider;
    private MatchCountSpinner matchCountSpinner;
    private OptionCheckBoxGroup optionCheckBoxGroup;

    public NewGamePopupFiller(GridPane menuPane, GridPane newGamePopupPane,
          HBox closeNewGameBox, Label difficultyHeader, VBox difficultySliderBox,
          Label matchCountHeader, VBox spinnerBox, Label advancedOptionsHeader, HBox advancedOptionsBox, VBox playBox) {
        super(menuPane, newGamePopupPane);
        this.closeNewGameBox = closeNewGameBox;
        this.difficultyHeader = difficultyHeader;
        this.difficultySliderBox = difficultySliderBox;
        this.matchCountHeader = matchCountHeader;
        this.spinnerBox = spinnerBox;
        this.advancedOptionsHeader = advancedOptionsHeader;
        this.advancedOptionsBox = advancedOptionsBox;
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
        optionCheckBoxGroup = new OptionCheckBoxGroup(advancedOptionsBox);
        optionCheckBoxGroup.fillGroup();
    }

    private void fillPlaySection() {
        PlayButton playButton = new PlayButton(difficultySlider, matchCountSpinner);
        playBox.getChildren().add(playButton);
    }
}
