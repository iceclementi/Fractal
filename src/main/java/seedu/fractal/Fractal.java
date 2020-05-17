package seedu.fractal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Fractal extends Application {

    @Override
    public void init() {
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("FRACTAL");
        // stage.getIcons().add(new Image("images/venus_icon.png"));
        stage.setMinWidth(800);
        stage.setMinHeight(600);

        stage.setOnCloseRequest(event -> {
            // Close event
            stage.close();
        });

        FXMLLoader sceneLoader = new FXMLLoader(getClass().getResource("/scenes/menu.fxml"));
        Parent root = sceneLoader.load();
        Scene menuScene = new Scene(root);

        stage.setScene(menuScene);
        stage.show();
    }
}
