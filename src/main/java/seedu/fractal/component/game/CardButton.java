package seedu.fractal.component.game;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
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

    private Card card;
    private static MatchButton matchButton;
    private static CancelButton cancelButton;

    private boolean isSelected = false;
    private boolean isMatched = false;
    private static Background cardBack;
    private Background cardFace;

    public CardButton(Card card, MatchButton matchButton, CancelButton cancelButton) {
        super();
        this.card = card;
        CardButton.matchButton = matchButton;
        CardButton.cancelButton = cancelButton;
        cardBack = generateCardBack();
        this.cardFace = generateCardFace();

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
    public static void reset() {
        for (CardButton card : selectedCards) {
            card.setBackground(cardBack);
            card.setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(171, 171, 171), 5, 0, 0, 0));
            card.setCursor(Cursor.HAND);
            card.isSelected = false;
        }

        selectedCardCount = 0;
        matchButton.reset();
        cancelButton.reset();
    }

    /**
     * Match the cards and fade image.
     */
    public static void match() {
        for (CardButton card : selectedCards) {
            card.setEffect(new ColorAdjust(0, -0.5, -0.1, 0));
            card.isMatched = true;
        }

        selectedCardCount = 0;
        matchButton.reset();
        cancelButton.reset();
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
                new BackgroundSize(getWidth(), getHeight(), true, true, true, true));
        return new Background(cardFaceImage);
    }

    private void initialiseStyle() {
        /* Set dimensions */
        setMinSize(120, 160);

        /* Set card back image */
        setBackground(cardBack);

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
        if (!isSelected && !isMatched && selectedCardCount < 2) {
            System.out.println(card.getImagePath());
            setBackground(cardFace);

            isSelected = true;
            setCursor(Cursor.DEFAULT);
            selectedCards[selectedCardCount++] = this;

            if (selectedCardCount == 2) {
                matchButton.activate();
                cancelButton.activate();
            }
        }
    }
}
