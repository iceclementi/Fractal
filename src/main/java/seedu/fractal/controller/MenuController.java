package seedu.fractal.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import seedu.fractal.component.menu.button.ContinueButton;
import seedu.fractal.component.menu.button.ContributeButton;
import seedu.fractal.component.menu.button.HelpButton;
import seedu.fractal.component.menu.button.LogoButton;
import seedu.fractal.component.menu.button.CustomButton;
import seedu.fractal.component.menu.button.NewGameButton;
import seedu.fractal.component.menu.popup.ContributePopupFiller;
import seedu.fractal.component.menu.popup.ErrorPopupFiller;
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
    private VBox logoBox;
    @FXML
    private VBox menuButtonBox;

    @FXML
    private VBox newGamePopupParentBox;
    @FXML
    private VBox newGamePopupBox;
    @FXML
    private HBox closeNewGameBox;
    @FXML
    private VBox difficultySliderBox;
    @FXML
    private VBox spinnerBox;
    @FXML
    private VBox gameModeToggleBox;
    @FXML
    private Label normalGameMode;
    @FXML
    private Label practiceGameMode;
    @FXML
    private HBox cardTypeOptionBox;
    @FXML
    private VBox lifeCountSpinnerBox;
    @FXML
    private VBox withLifeBox;
    @FXML
    private VBox withoutLifeBox;
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

    @FXML
    private GridPane contributePopupPane;
    @FXML
    private HBox closeContributeBox;
    @FXML
    private Label contributeHeader;
    @FXML
    private TextFlow contributeText;

    @FXML
    private GridPane errorPopupPane;
    @FXML
    private HBox closeErrorBox;
    @FXML
    private Label errorHeader;
    @FXML
    private VBox errorBox;
    @FXML
    private TextFlow errorText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* Load game */
        try {
            Storage.loadGameDetails();
        } catch (Exception e) {
            Storage.loadDefaultGameDetails();
        }

        menuPane.setBackground(SceneUtil.generateBackground(FilePath.BACKGROUND_IMAGE_PATH));
        logoBox.getChildren().add(new LogoButton());

        CustomButton newGameButton = new NewGameButton(menuPane, newGamePopupParentBox);
        CustomButton continueButton = new ContinueButton(menuPane, errorPopupPane);
        CustomButton helpButton = new HelpButton(menuPane, helpPopupPane);
        CustomButton aboutButton = new ContributeButton(menuPane, contributePopupPane);

        preparePopupBoxes();

        menuButtonBox.getChildren().addAll(newGameButton, continueButton, helpButton, aboutButton);
    }

    private void preparePopupBoxes() {
        new NewGamePopupFiller(menuPane, newGamePopupParentBox, newGamePopupBox,
                closeNewGameBox, difficultySliderBox, spinnerBox,
                gameModeToggleBox, normalGameMode, practiceGameMode, cardTypeOptionBox,
                lifeCountSpinnerBox, withLifeBox, withoutLifeBox, playBox).fillPopup();
        new HelpPopupFiller(menuPane, helpPopupPane, closeHelpBox, helpHeader, helpText, goalHeader, goalText,
                ruleHeader, ruleText).fillPopup();
        new ContributePopupFiller(menuPane, contributePopupPane, closeContributeBox,
                contributeHeader, contributeText).fillPopup();
        new ErrorPopupFiller(menuPane, errorPopupPane, closeErrorBox, errorHeader, errorText).fillPopup();
    }


}
