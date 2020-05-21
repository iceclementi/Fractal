package seedu.fractal.component.menu;

import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.fractal.component.menu.button.PlayButton;
import seedu.fractal.storage.FilePath;

public class NewGamePopupBoxFiller extends PopupBoxFiller  {

    private HBox closeNewGameBox;
    private VBox difficultyBox;
    private Label difficultyHeader;
    private VBox matchCountBox;
    private Label matchCountHeader;
    private VBox advancedOptionsBox;
    private Label advancedOptionsHeader;
    private VBox playBox;

    private DifficultySlider difficultySlider;
    private Spinner<Integer> spinner;

    public NewGamePopupBoxFiller(GridPane menuPane, GridPane newGamePopupPane,
             HBox closeNewGameBox, VBox difficultyBox, Label difficultyHeader, VBox matchCountBox,
             Label matchCountHeader, VBox advancedOptionsBox, Label advancedOptionsHeader, VBox playBox) {
        super(menuPane, newGamePopupPane);
        this.closeNewGameBox = closeNewGameBox;
        this.difficultyBox = difficultyBox;
        this.difficultyHeader = difficultyHeader;
        this.matchCountBox = matchCountBox;
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
        setHeaderStyle(difficultyHeader, "DIFFICULTY");
        difficultySlider = new DifficultySlider();
        difficultyBox.getChildren().add(difficultySlider);
    }

    private void fillMatchCountSection() {
        setHeaderStyle(matchCountHeader, "NUMBER OF MATCHES");
        spinner = new Spinner<>(2, 11, 4);
        matchCountBox.getChildren().add(spinner);
    }

    private void fillAdvancedOptionsSection() {
        setHeaderStyle(advancedOptionsHeader, "ADVANCED OPTIONS");
        advancedOptionsBox.setDisable(true);
    }

    private void fillPlaySection() {
        PlayButton playButton = new PlayButton(difficultySlider, spinner);
        playBox.getChildren().add(playButton);
    }

    private void setHeaderStyle(Label header, String name) {
        header.getStylesheets().add(getClass().getResource(FilePath.MENU_STYLE_PATH).toExternalForm());
        header.getStyleClass().add("popup-header");
        header.setText(name);
    }

}
