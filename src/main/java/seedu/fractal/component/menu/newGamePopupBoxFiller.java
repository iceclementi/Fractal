package seedu.fractal.component.menu;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import seedu.fractal.storage.FilePath;

public class newGamePopupBoxFiller {

    private VBox difficultyBox;
    private Label difficultyHeader;
    private VBox matchCountBox;
    private Label matchCountHeader;
    private VBox advancedOptionsBox;
    private Label advancedOptionsHeader;
    private VBox playBox;

    public newGamePopupBoxFiller(VBox difficultyBox, Label difficultyHeader, VBox matchCountBox,
             Label matchCountHeader, VBox advancedOptionsBox, Label advancedOptionsHeader, VBox playBox) {
        this.difficultyBox = difficultyBox;
        this.difficultyHeader = difficultyHeader;
        this.matchCountBox = matchCountBox;
        this.matchCountHeader = matchCountHeader;
        this.advancedOptionsBox = advancedOptionsBox;
        this.advancedOptionsHeader = advancedOptionsHeader;
        this.playBox = playBox;
    }

    public void fillNewGamePopup() {
        fillDifficultySection();
    }

    private void fillDifficultySection() {
        difficultyHeader.getStylesheets().add(getClass().getResource(FilePath.MENU_STYLE_PATH).toExternalForm());
        difficultyHeader.getStyleClass().add("popup-header");
        difficultyHeader.setText("DIFFICULTY");
        difficultyBox.getChildren().add(new DifficultySlider());
    }

}
