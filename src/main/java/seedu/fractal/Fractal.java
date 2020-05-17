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
        stage.setTitle("INFI-NUKE");
        // stage.getIcons().add(new Image("images/venus_icon.png"));
        // stage.setMinWidth(1250);
        // stage.setMinHeight(700);

        stage.setOnCloseRequest(event -> {
            // Close event
            stage.close();
        });

        FXMLLoader sceneLoader = new FXMLLoader(getClass().getResource("/menu.fxml"));
        Parent mainRoot = sceneLoader.load();
        Scene main = new Scene(mainRoot);

        stage.setScene(main);
        stage.show();
    }

}
