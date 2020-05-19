package seedu.fractal.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import seedu.fractal.component.menu.ContinueButton;
import seedu.fractal.component.menu.MenuButton;
import seedu.fractal.component.menu.NewGameButton;
import seedu.fractal.component.menu.newGamePopupBoxFiller;
import seedu.fractal.storage.FilePath;
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
    private VBox popupBox;

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
        menuPane.setBackground(SceneUtil.generateBackground(FilePath.BACKGROUND_IMAGE_PATH));

        // Do NOT display pop up on start up
        popupPane.setVisible(false);
        fillPopupBoxes();

        MenuButton newGameButton = new NewGameButton("NEW GAME", menuPane, popupPane);
        MenuButton continueButton = new ContinueButton("CONTINUE");
        MenuButton helpButton = new MenuButton("HOW TO PLAY");
        MenuButton aboutButton = new MenuButton("ABOUT");

        helpButton.setDisable(true);
        aboutButton.setDisable(true);

        menuButtonBox.getChildren().addAll(newGameButton, continueButton, helpButton, aboutButton);
    }

    private void fillPopupBoxes() {
        new newGamePopupBoxFiller(difficultyBox, difficultyHeader, matchCountBox, matchCountHeader,
                advancedOptionsBox, advancedOptionsHeader, playBox).fillNewGamePopup();
    }


}
