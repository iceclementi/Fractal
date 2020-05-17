package seedu.fractal.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import seedu.fractal.component.menu.MenuButton;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private VBox menuButtonBox;

    private MenuButton startButton;
    private MenuButton helpButton;
    private MenuButton aboutButton;
    private MenuButton settingsButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startButton = new MenuButton("PLAY!!");
        helpButton = new MenuButton("HOW TO PLAY");
        aboutButton = new MenuButton("ABOUT");
        settingsButton = new MenuButton("SETTINGS");
        menuButtonBox.getChildren().addAll(startButton, helpButton, aboutButton, settingsButton);
    }

}
