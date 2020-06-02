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
import seedu.fractal.component.menu.button.NewGameButton;
import seedu.fractal.component.menu.popup.ContributePopupFiller;
import seedu.fractal.component.menu.popup.ErrorPopupFiller;
import seedu.fractal.component.menu.popup.HelpPopupFiller;
import seedu.fractal.component.menu.popup.NewGamePopupFiller;
import seedu.fractal.storage.FilePath;
import seedu.fractal.storage.Storage;
import seedu.fractal.util.ComponentUtil;
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
    private VBox helpPopupParentBox;
    @FXML
    private VBox helpPopupBox;

    @FXML
    private VBox contributePopupParentBox;
    @FXML
    private VBox contributePopupBox;

    @FXML
    private VBox errorPopupParentBox;
    @FXML
    private VBox errorPopupBox;

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

        NewGameButton newGameButton = new NewGameButton(menuPane, newGamePopupParentBox);
        ContinueButton continueButton = new ContinueButton(menuPane, errorPopupParentBox);
        HelpButton helpButton = new HelpButton(menuPane, helpPopupParentBox);
        ContributeButton contributeButton = new ContributeButton(menuPane, contributePopupParentBox);

        preparePopupBoxes();

        menuButtonBox.getChildren().addAll(newGameButton, continueButton, helpButton, contributeButton);
    }

    private void preparePopupBoxes() {
        new NewGamePopupFiller(menuPane, newGamePopupParentBox, newGamePopupBox,
                closeNewGameBox, difficultySliderBox, spinnerBox,
                gameModeToggleBox, normalGameMode, practiceGameMode, cardTypeOptionBox,
                lifeCountSpinnerBox, withLifeBox, withoutLifeBox, playBox).fillPopup();
        new HelpPopupFiller(menuPane, helpPopupParentBox, helpPopupBox).fillPopup();
        new ContributePopupFiller(menuPane, contributePopupParentBox, contributePopupBox).fillPopup();
        new ErrorPopupFiller(menuPane, errorPopupParentBox, errorPopupBox).fillPopup();
    }
}
