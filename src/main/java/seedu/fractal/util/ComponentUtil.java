package seedu.fractal.util;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class ComponentUtil {

    public static void setButtonBackground(Button button, String backgroundPath) {
        Image image = new Image(backgroundPath, button.getWidth(), button.getHeight(), false, true, true);
        BackgroundImage background = new BackgroundImage(
                image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(button.getWidth(), button.getHeight(), true, true, true, false));
        button.setBackground(new Background(background));
    }
}
