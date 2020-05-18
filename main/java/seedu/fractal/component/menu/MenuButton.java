package seedu.fractal.component.menu;

import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;

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
        getStylesheets().add(getClass().getResource("/styles/menuStyle.css").toExternalForm());
        getStyleClass().add("menu-button");
        setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.rgb(171, 171, 171), 5, 0, 0, 0));
    }

}
