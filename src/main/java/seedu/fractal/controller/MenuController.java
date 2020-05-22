package seedu.fractal.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import seedu.fractal.component.menu.button.*;
import seedu.fractal.component.menu.popup.HelpPopupFiller;
import seedu.fractal.component.menu.popup.NewGamePopupFiller;
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
    private GridPane newGamePopupPane;
    @FXML
    private HBox closeNewGameBox;
    @FXML
    private VBox difficultyBox;
    @FXML
    private Label difficultyHeader;
    @FXML
    private VBox difficultySliderBox;
    @FXML
    private VBox matchCountBox;
    @FXML
    private Label matchCountHeader;
    @FXML
    private VBox spinnerBox;
    @FXML
    private VBox advancedOptionsBox;
    @FXML
    private Label advancedOptionsHeader;
    @FXML
    private VBox playBox;


    @FXML
    private GridPane helpPopupPane;
    @FXML
    private HBox closeHelpBox;
    @FXML
    private Label helpHeader;
    @FXML
    private TextFlow helpText;
    @FXML
    private Label goalHeader;
    @FXML
    private TextFlow goalText;
    @FXML
    private Label ruleHeader;
    @FXML
    private TextFlow ruleText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* Load game */
        Storage.loadGameDetails();

        menuPane.setBackground(SceneUtil.generateBackground(FilePath.BACKGROUND_IMAGE_PATH));

        MenuButton newGameButton = new NewGameButton(menuPane, newGamePopupPane);
        MenuButton continueButton = new ContinueButton();
        MenuButton helpButton = new HelpButton(menuPane, helpPopupPane);
        MenuButton aboutButton = new AboutButton();

        fillPopupBoxes();

        aboutButton.setDisable(true);

        menuButtonBox.getChildren().addAll(newGameButton, continueButton, helpButton, aboutButton);
    }

    private void fillPopupBoxes() {
        new NewGamePopupFiller(menuPane, newGamePopupPane,
                closeNewGameBox, difficultySliderBox, difficultyHeader, spinnerBox, matchCountHeader,
                advancedOptionsBox, advancedOptionsHeader, playBox).fillPopup();
        new HelpPopupFiller(menuPane, helpPopupPane, closeHelpBox, helpHeader, helpText, goalHeader, goalText,
                ruleHeader, ruleText).fillPopup();
    }


}
