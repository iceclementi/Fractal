package seedu.fractal.component.menu.popup;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;

public class HelpPopupFiller extends PopupFiller {

    private static final String HELP_TEXT =
            "Frac/Tal is an interactive mathematics memory card game that exercises your "
            + "ability and speed to convert between fractions, decimals, percentages and ratios.";
    private static final String GOAL_TEXT =
            "The goal of this game is to match cards that have the same value.\n"
            // + "The game ends when all the cards have been correctly matched, or you ran out of lives.\n";
            + "The game ends when all the cards have been correctly matched.\n";
    private static final String RULE_TEXT =
            "Only two cards may be selected at any one time. After that, you have to decide whether the two cards "
            + "have the same value and match them, or to reset the cards.\n\n"
            // + "Every incorrect match will remove one life.\n\n"
            + "HAVE FUN!! " + new String(Character.toChars(0x1F609));

    private HBox closeHelpBox;
    private Label helpHeader;
    private TextFlow helpText;
    private Label goalHeader;
    private TextFlow goalText;
    private Label ruleHeader;
    private TextFlow ruleText;

    public HelpPopupFiller(GridPane menuPane, GridPane helpPopupPane, HBox closeHelpBox,
           Label helpHeader, TextFlow helpText, Label goalHeader, TextFlow goalText,
           Label ruleHeader, TextFlow ruleText) {
        super(menuPane, helpPopupPane);
        this.closeHelpBox = closeHelpBox;
        this.helpHeader = helpHeader;
        this.helpText = helpText;
        this.goalHeader = goalHeader;
        this.goalText = goalText;
        this.ruleHeader = ruleHeader;
        this.ruleText = ruleText;
    }

    public void fillPopup() {
        fillCloseSection();
        fillHelpSection();
        fillGoalSection();
        fillRuleSection();
    }

    private void fillCloseSection() {
        closeHelpBox.getChildren().add(generateCloseButton());
    }

    private void fillHelpSection() {
        setHeader(helpHeader, "HOW TO PLAY");
        setTextFlow(helpText, HELP_TEXT);
    }

    private void fillGoalSection() {
        setHeader(goalHeader, "GOAL");
        setTextFlow(goalText, GOAL_TEXT);
    }

    private void fillRuleSection() {
        setHeader(ruleHeader, "RULES");
        setTextFlow(ruleText, RULE_TEXT);
    }
}
