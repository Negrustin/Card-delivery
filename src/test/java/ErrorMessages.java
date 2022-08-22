import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ErrorMessages {

    private  final SelenideElement cityInput = $x("//*[@data-test-id = 'city' and  //span[contains(@class, 'invalid')]]");
    private  final SelenideElement nameInput = $x("//*[@data-test-id = 'name' and  //span[contains(@class, 'invalid')]]");

    private final SelenideElement phoneInputSpan = $x("//span[@data-test-id = 'phone']/span/span[@class = 'input__sub']");

    public SelenideElement getNameInputError() {
        return nameInput;
    }
    public SelenideElement getCityInputError() {
        return cityInput;
    }
    public SelenideElement getPhoneInputError(){
        return phoneInputSpan;
    }

}
