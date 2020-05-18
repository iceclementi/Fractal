package seedu.fractal.component.game;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import seedu.fractal.logic.Card;
import seedu.fractal.storage.FilePath;

public class CardButton extends Button {

    private Card card;
    private boolean isSelected = false;
    private boolean isMatched = false;

    public CardButton(Card card) {
        super();
        this.card = card;

        initialiseStyle();
        initialiseEvents();
    }

    private void initialiseStyle() {
        // getStylesheets().add(getClass().getResource("/styles/gameStyle.css").toExternalForm());
        // getStyleClass().add("card-button");

        /* Set dimensions */
        setMinSize(120, 160);

        /* Set card back image */
        Image image = new Image(FilePath.CARD_BACK_IMAGE_PATH, getWidth(), getHeight(), true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(
                image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(getWidth(), getHeight(), true, true, true, false));

        setBackground(new Background(backgroundImage));

        setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(171, 171, 171), 5, 0, 0, 0));
        setCursor(Cursor.HAND);


    }

    private void initialiseEvents() {
        setOnMouseEntered(this::onHover);
        setOnMouseExited(this::onUnhover);
        setOnMouseClicked(this::onClick);
    }

    private void onHover(MouseEvent mouseEvent) {
        if (!isSelected && !isMatched) {
            setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(99, 133, 171), 10, 0, 0, 0));
        }
    }

    private void onUnhover(MouseEvent mouseEvent) {
        if (!isSelected && !isMatched) {
            setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(171, 171, 171), 5, 0, 0, 0));
        }
    }

    private void onClick(MouseEvent mouseEvent) {
        if (!isSelected && !isMatched) {
            System.out.println(card.getImagePath());
            Image image = new Image(card.getImagePath(), getWidth(), getHeight(), true, false, true);
            BackgroundImage backgroundImage = new BackgroundImage(
                    image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(getWidth(), getHeight(), true, true, true, true));
            setBackground(new Background(backgroundImage));
        }
    }
}
