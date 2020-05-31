package seedu.fractal.util;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

public class ComponentUtil {

    public static void setBackground(Button button, String backgroundPath) {
        button.setBackground(generateBackground(button.getWidth(), button.getHeight(), backgroundPath));
    }

    public static void setBackground(VBox box, String backgroundPath) {
        box.setBackground(generateBackground(box.getWidth(), box.getHeight(), backgroundPath));
    }

    private static Background generateBackground(double width, double height, String backgroundPath) {
        Image image = new Image(backgroundPath, width, height, false, true, true);
        BackgroundImage background = new BackgroundImage(
                image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(width, height, true, true, true, false));

        return new Background(background);
    }
}
