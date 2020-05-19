package seedu.fractal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import seedu.fractal.storage.FilePath;
import seedu.fractal.util.SceneUtil;

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

        FXMLLoader sceneLoader = new FXMLLoader(getClass().getResource(FilePath.MENU_SCENE_PATH));
        Parent root = sceneLoader.load();
        Scene menuScene = new Scene(root);

        stage.setScene(menuScene);
        stage.show();

        SceneUtil.centreScene(stage);
    }
}
