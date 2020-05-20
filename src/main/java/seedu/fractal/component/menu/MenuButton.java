package seedu.fractal.component.menu;

import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import seedu.fractal.storage.FilePath;

public class MenuButton extends Button {

    /**
     * Constructor for a custom menu button.
     *
     * @param name
     *  The name of the button
     */
    public MenuButton(String name) {
        super();

        initialiseStyle();
        setText(name);
    }

    private void initialiseStyle() {
        getStylesheets().add(getClass().getResource(FilePath.MENU_STYLE_PATH).toExternalForm());
        getStyleClass().add("menu-button");
        // setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.rgb(171, 171, 171), 5, 0, 0, 0));
        Image image = new Image(FilePath.MENU_BUTTON_IMAGE_PATH, getWidth(), getHeight(), false, true, true);
        BackgroundImage cardBackImage = new BackgroundImage(
                image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(getWidth(), getHeight(), false, false, true, true));
        setBackground(new Background(cardBackImage));
    }

}
