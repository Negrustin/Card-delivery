package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryPage {
    private static final SelenideElement cityInput = $x("//*[@data-test-id = 'city']//input");
    private static final SelenideElement dateInput = $x("//*[@data-test-id = 'date']//input");
    private static final SelenideElement nameInput = $x("//*[@data-test-id = 'name']//input");
    private static final SelenideElement phoneInput = $x("//*[@data-test-id = 'phone']//input");
    private static final SelenideElement acceptCheckBox = $x("//*[@data-test-id = 'agreement']");
    private static final SelenideElement button = $(".button__text");

    private final SelenideElement cityInputError = $x("//span[@data-test-id = 'city']/span/span[@class = 'input__sub']");
    private final SelenideElement nameInputError = $x("//span[@data-test-id = 'name']/span/span[@class = 'input__sub']");
    private final SelenideElement PhoneInputError = $x("//span[@data-test-id = 'phone']/span/span[@class = 'input__sub']");
    private final SelenideElement successWindow = $x("//*[@data-test-id = 'notification']");
    private final SelenideElement notCheckBoxError = $x("//div/label[@data-test-id ='agreement' and //div/label[contains(@class, 'input_invalid')]]");

    private LocalDate date = LocalDate.now();
    private DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public CardDeliveryPage(String url) {
        Selenide.open(url);
    }

    private void setValueToObject(SelenideElement element, String str) {
        element.setValue(str);
    }

    private void clickOnObject(SelenideElement element) {
        element.click();
    }


    public void setCity(String city) {
        setValueToObject(cityInput, city);
    }

    public void addDaysToTheCurrentDate(int daysAfter) {
        String dateString = date.plusDays(daysAfter).format(formatters);
        dateInput
                .doubleClick()
                .sendKeys(Keys.BACK_SPACE);
        dateInput.setValue(dateString);

    }

    public void setName(String name) {
        setValueToObject(nameInput, name);
    }

    public void setPhone(String phone) {
        setValueToObject(phoneInput, phone);
    }

    public void clickOnCheckbox() {
        clickOnObject(acceptCheckBox);
    }

    public void clickOnAcceptButton() {
        clickOnObject(button);
    }


    public String getCityInputError() {
        return cityInputError
                .shouldBe(Condition.visible)
                .text();
    }

    public String getNameInputError() {
        return nameInputError
                .shouldBe(Condition.visible)
                .text();
    }

    public String getPhoneInputError() {
        return PhoneInputError
                .shouldBe(Condition.visible)
                .text();
    }

    public void isASuccess(String msg) {
        successWindow
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text(msg));
    }

    public void checkBoxError() {

        notCheckBoxError
                .shouldBe(Condition.visible);
    }

    public void selectCityFromDropDownList(String city) {
        cityInput
                .setValue(city);
        $$x("//span[contains(@class, 'menu-item__control')]").findBy(Condition.matchText("^(?iu)" + city + "+.*")).click();

    }

    public String getValueFromCityInput() {
        return cityInput.getValue();
    }

public void isTheCityEnteredCorrectly(String city){
    Assertions.assertTrue(getValueFromCityInput().contains(city));

}
}

