import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class SuccessPage {

   private final SelenideElement successWindow = $x("//*[@data-test-id = 'notification']");

    public SelenideElement getSuccessWindow() {
        return successWindow;
    }
}
