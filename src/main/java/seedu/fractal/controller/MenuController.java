package seedu.fractal.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import seedu.fractal.component.menu.MenuButton;
import seedu.fractal.component.menu.StartButton;
import seedu.fractal.storage.FilePath;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private GridPane menuPane;

    @FXML
    private VBox menuButtonBox;

    private MenuButton startButton;
    private MenuButton helpButton;
    private MenuButton aboutButton;
    private MenuButton settingsButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setBackground();

        startButton = new StartButton("PLAY!!");
        helpButton = new MenuButton("HOW TO PLAY");
        aboutButton = new MenuButton("ABOUT");
        settingsButton = new MenuButton("SETTINGS");
        menuButtonBox.getChildren().addAll(startButton, helpButton, aboutButton, settingsButton);
    }

    private void setBackground() {
        Image image = new Image(FilePath.BACKGROUND_IMAGE_PATH);
        BackgroundImage backgroundImage = new BackgroundImage(
                image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        menuPane.setBackground(new Background(backgroundImage));
    }

}
