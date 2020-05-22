package seedu.fractal.component.menu.popup;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;

public class ContributePopupFiller extends PopupFiller {

    private static final String CONTRIBUTE_TEXT =
            "Frac/Tal is merely at its beta phase and is still under development. "
            + "As such, there are some areas in the application that can be improved even further.\n\n"
            + "You can be a contributor of Frac/Tal too!\n\n"
            + "Send your ideas, thoughts on the application, and even bugs that you found.\n"
            + "Any feedback is most welcomed.\n\n"
            + "You can contact me, the developer, at:\n"
            + "iclemonti@gmail.com\n";

    private HBox closeContributeBox;
    private Label contributeHeader;
    private TextFlow contributeText;

    public ContributePopupFiller(GridPane menuPane, GridPane helpPopupPane, HBox closeContributeBox,
             Label contributeHeader, TextFlow contributeText) {
        super(menuPane, helpPopupPane);
        this.closeContributeBox = closeContributeBox;
        this.contributeHeader = contributeHeader;
        this.contributeText = contributeText;
    }

    public void fillPopup() {
        fillCloseSection();
        fillContributeSection();
    }

    private void fillCloseSection() {
        closeContributeBox.getChildren().add(generateCloseButton());
    }

    private void fillContributeSection() {
        setHeader(contributeHeader, "CONTRIBUTE");
        setTextFlow(contributeText, CONTRIBUTE_TEXT);
    }
}
