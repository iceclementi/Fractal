package seedu.fractal.component.menu.popup;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.fractal.component.menu.DifficultySlider;
import seedu.fractal.component.menu.GameModeToggle;
import seedu.fractal.component.menu.LifeCountSpinner;
import seedu.fractal.component.menu.MatchCountSpinner;
import seedu.fractal.component.menu.OptionCheckBoxGroup;
import seedu.fractal.component.menu.button.PlayButton;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.ComponentUtil;

public class NewGamePopupFiller extends PopupFiller {

    private VBox newGamePopupBox;
    private HBox closeNewGameBox;
    private VBox difficultySliderBox;
    private VBox spinnerBox;
    private VBox gameModeToggleBox;
    private Label normalGameMode;
    private Label practiceGameMode;
    private HBox cardTypeOptionBox;
    private VBox lifeCountSpinnerBox;
    private VBox withLifeBox;
    private VBox withoutLifeBox;
    private VBox playBox;

    private DifficultySlider difficultySlider;
    private MatchCountSpinner matchCountSpinner;
    private OptionCheckBoxGroup optionCheckBoxGroup;

    public NewGamePopupFiller(GridPane menuPane, VBox newGamePopupParentBox, VBox newGamePopupBox,
            HBox closeNewGameBox, VBox difficultySliderBox, VBox spinnerBox, VBox gameModeToggleBox,
            Label normalGameMode, Label practiceGameMode, HBox cardTypeOptionBox,
            VBox lifeCountSpinnerBox, VBox withLifeBox, VBox withoutLifeBox, VBox playBox) {
        super(menuPane, newGamePopupParentBox);
        this.newGamePopupBox = newGamePopupBox;
        this.closeNewGameBox = closeNewGameBox;
        this.difficultySliderBox = difficultySliderBox;
        this.spinnerBox = spinnerBox;
        this.gameModeToggleBox = gameModeToggleBox;
        this.normalGameMode = normalGameMode;
        this.practiceGameMode = practiceGameMode;
        this.cardTypeOptionBox = cardTypeOptionBox;
        this.lifeCountSpinnerBox = lifeCountSpinnerBox;
        this.withLifeBox = withLifeBox;
        this.withoutLifeBox = withoutLifeBox;
        this.playBox = playBox;

        initialiseStyle();
    }

    /**
     * Fills the new game popup.
     */
    public void fillPopup() {
        fillCloseSection();
        fillDifficultySection();
        fillMatchCountSection();
        fillAdvancedOptionsSection();
        fillPlaySection();
    }

    private void initialiseStyle() {
        ComponentUtil.setBackground(newGamePopupBox, FilePath.NEW_GAME_FRAME_IMAGE_PATH);
    }

    private void fillCloseSection() {
        closeNewGameBox.getChildren().add(generateCloseButton());
    }

    private void fillDifficultySection() {
        difficultySlider = new DifficultySlider();
        difficultySliderBox.getChildren().add(difficultySlider);
    }

    private void fillMatchCountSection() {
        matchCountSpinner = new MatchCountSpinner(difficultySlider);
        spinnerBox.getChildren().add(matchCountSpinner);
    }

    private void fillAdvancedOptionsSection() {
        GameModeToggle gameModeToggle = new GameModeToggle(normalGameMode, practiceGameMode);
        gameModeToggleBox.getChildren().add(gameModeToggle);

        optionCheckBoxGroup = new OptionCheckBoxGroup(cardTypeOptionBox);
        optionCheckBoxGroup.fillGroup();

        LifeCountSpinner lifeCountSpinner = new LifeCountSpinner(withLifeBox, withoutLifeBox);
        lifeCountSpinnerBox.getChildren().add(lifeCountSpinner);
    }

    private void fillPlaySection() {
        PlayButton playButton = new PlayButton(difficultySlider, matchCountSpinner, optionCheckBoxGroup);
        playBox.getChildren().add(playButton);
    }
}
