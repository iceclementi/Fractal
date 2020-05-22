package seedu.fractal.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.fractal.component.game.GameBoard;
import seedu.fractal.component.menu.button.*;
import seedu.fractal.component.menu.NewGamePopupBoxFiller;
import seedu.fractal.storage.FilePath;
import seedu.fractal.storage.Storage;
import seedu.fractal.util.SceneUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private GridPane menuPane;
    @FXML
    private VBox menuButtonBox;

    @FXML
    private GridPane popupPane;

    @FXML
    private HBox closeNewGameBox;
    @FXML
    private VBox difficultyBox;
    @FXML
    private Label difficultyHeader;
    @FXML
    private VBox matchCountBox;
    @FXML
    private Label matchCountHeader;
    @FXML
    private VBox advancedOptionsBox;
    @FXML
    private Label advancedOptionsHeader;
    @FXML
    private VBox playBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* Load game */
        Storage.loadGameDetails();

        menuPane.setBackground(SceneUtil.generateBackground(FilePath.BACKGROUND_IMAGE_PATH));

        // Do NOT display pop up on start up
        popupPane.setVisible(false);

        MenuButton newGameButton = new NewGameButton(menuPane, popupPane);
        MenuButton continueButton = new ContinueButton();
        MenuButton helpButton = new HelpButton();
        MenuButton aboutButton = new AboutButton();

        fillPopupBoxes();

        helpButton.setDisable(true);
        aboutButton.setDisable(true);

        menuButtonBox.getChildren().addAll(newGameButton, continueButton, helpButton, aboutButton);
    }

    private void fillPopupBoxes() {
        new NewGamePopupBoxFiller(menuPane, popupPane,
                closeNewGameBox, difficultyBox, difficultyHeader, matchCountBox, matchCountHeader,
                advancedOptionsBox, advancedOptionsHeader, playBox).fillPopup();
    }


}
