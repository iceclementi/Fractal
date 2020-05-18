package seedu.fractal.component.game;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import seedu.fractal.logic.Card;
import seedu.fractal.storage.FilePath;

public class CardButton extends Button {

    private Card card;

    public CardButton(Card card) {
        super();
        this.card = card;

        initialiseStyle();
    }


    private void initialiseStyle() {
        /* Set card back image */
        Image image = new Image(FilePath.CARD_BACK_IMAGE_PATH, getWidth(), getHeight(), true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(
                image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(getWidth(), getHeight(), true, true, true, false));

        setBackground(new Background(backgroundImage));

        /* Set dimensions */
        setPrefSize(120, 160);
    }
}
