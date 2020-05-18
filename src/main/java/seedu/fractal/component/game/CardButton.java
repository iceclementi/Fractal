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
    private MatchButton matchButton;
    private CancelButton cancelButton;

    private boolean isSelected = false;
    private boolean isMatched = false;
    private BackgroundImage cardBack;
    private BackgroundImage cardFace;

    private static int selectedCardCount = 0;
    private static CardButton[] selectedCards = new CardButton[2];

    public CardButton(Card card, MatchButton matchButton, CancelButton cancelButton) {
        super();
        this.card = card;
        this.matchButton = matchButton;
        this.cancelButton = cancelButton;
        this.cardBack = generateCardBack();
        this.cardFace = generateCardFace();

        initialiseStyle();
        initialiseEvents();
    }

    public static CardButton[] getSelectedCards() {
        return selectedCards;
    }

    /**
     * Resets all faced-up cards back to face-down.
     */
    public void reset() {
        setBackground(new Background(cardBack));
        setCursor(Cursor.HAND);
        isSelected = false;
        selectedCardCount = 0;
    }

    private BackgroundImage generateCardBack() {
        Image image = new Image(FilePath.CARD_BACK_IMAGE_PATH, getWidth(), getHeight(), true, true, true);
        return new BackgroundImage(
                image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(getWidth(), getHeight(), true, true, true, false));
    }

    private BackgroundImage generateCardFace() {
        Image image = new Image(card.getImagePath(), getWidth(), getHeight(), true, false, true);
        return new BackgroundImage(
                image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(getWidth(), getHeight(), true, true, true, true));
    }

    private void initialiseStyle() {
        // getStylesheets().add(getClass().getResource("/styles/gameStyle.css").toExternalForm());
        // getStyleClass().add("card-button");

        /* Set dimensions */
        setMinSize(120, 160);

        /* Set card back image */
        setBackground(new Background(cardBack));

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
            setBackground(new Background(cardFace));

            isSelected = true;
            setCursor(Cursor.DEFAULT);
            selectedCards[selectedCardCount++] = this;

            if (selectedCardCount == 2) {
                matchButton.setCanMatch();
            }
        }
    }
}
