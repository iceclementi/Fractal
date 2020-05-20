package seedu.fractal.component.game;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorAdjust;
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

    private static int selectedCardCount = 0;
    private static CardButton[] selectedCards = new CardButton[2];

    private static Background cardBack = null;
    private Background cardFace;

    private int id;
    private Card card;

    public CardButton(int id, Card card) {
        super();
        this.id = id;
        this.card = card;

        initialiseStyle();
        initialiseEvents();
    }

    public static CardButton[] getSelectedCards() {
        return selectedCards;
    }

    public Card getCard() {
        return card;
    }

    /**
     * Resets all faced-up cards back to face-down.
     */
    public void reset() {
        setBackground(cardBack);
        setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(171, 171, 171), 5, 0, 0, 0));
        setCursor(Cursor.HAND);
        getCard().setStatus(CardStatus.DEFAULT);
    }

    /**
     * Match the cards and fade image.
     */
    public void match() {
        setEffect(new ColorAdjust(0, -0.5, 0, 0));
        setOpacity(0.5);
        getCard().setStatus(CardStatus.MATCHED);
    }

    private Background generateCardBack() {
        Image image = new Image(FilePath.CARD_BACK_IMAGE_PATH, getWidth(), getHeight(), true, true, true);
        BackgroundImage cardBackImage = new BackgroundImage(
                image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(getWidth(), getHeight(), true, true, true, false));
        return new Background(cardBackImage);
    }

    private Background generateCardFace() {
        Image image = new Image(card.getImagePath(), getWidth(), getHeight(), true, false, true);
        BackgroundImage cardFaceImage = new BackgroundImage(
                image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(getWidth(), getHeight(), true, true, true, false));
        return new Background(cardFaceImage);
    }

    private void initialiseStyle() {
        /* Set dimensions */
        setPrefHeight(160);
        setPrefWidth(120);

        /* Set card back image */
        if (cardBack == null) {
            cardBack = generateCardBack();
        }

        setBackground(cardBack);
        cardFace = generateCardFace();

        setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(171, 171, 171), 5, 0, 0, 0));
        setCursor(Cursor.HAND);
    }

    private void initialiseEvents() {
        setOnMouseEntered(this::onHover);
        setOnMouseExited(this::onUnhover);
        setOnMouseReleased(this::onClick);
    }

    private void onHover(MouseEvent mouseEvent) {
        if (getCard().getStatus() == CardStatus.DEFAULT) {
            setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(99, 133, 171), 10, 0, 0, 0));
        }
    }

    private void onUnhover(MouseEvent mouseEvent) {
        if (getCard().getStatus() == CardStatus.DEFAULT) {
            setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(171, 171, 171), 5, 0, 0, 0));
        }
    }

    private void onClick(MouseEvent mouseEvent) {
        if (getCard().getStatus() == CardStatus.DEFAULT && GameBoard.getInstance().canSelect()) {
            System.out.println(card.getImagePath());
            setBackground(cardFace);

            getCard().setStatus(CardStatus.SELECTED);
            setCursor(Cursor.DEFAULT);
            GameBoard.getInstance().selectCard(this);
        }
    }
}
