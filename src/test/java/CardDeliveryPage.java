import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryPage {
    private  static final SelenideElement cityInput = $x("//*[@data-test-id = 'city']//input");
    private static final SelenideElement dateInput = $x("//*[@data-test-id = 'date']//input");
    String strDateInput = "//*[@data-test-id = 'date']//input";
    private static final SelenideElement nameInput = $x("//*[@data-test-id = 'name']//input");
    private static final SelenideElement phoneInput = $x("//*[@data-test-id = 'phone']//input");
    private static final SelenideElement acceptCheckBox = $x("//*[@data-test-id = 'agreement']");
    private static final SelenideElement button = $(".button__text");

    private LocalDate date = LocalDate.now();
    private DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String dateString = date.plusDays(7).format(formatters).toString();




    public CardDeliveryPage(String url) {
        Selenide.open(url);
    }

    private void setValueToObject(SelenideElement element, String str){
        element.setValue(str);
    }

    private void clickOnObject(SelenideElement element){
        element.click();
    }


    public void setCity(String city){
        setValueToObject(cityInput,city);
    }
    public void addDaysToTheCurrentDate(int daysAfter){
        String dateString = date.plusDays(daysAfter).format(formatters).toString();
        Selenide.$x(strDateInput)
                .doubleClick()
                .sendKeys(Keys.BACK_SPACE);
        Selenide.$x(strDateInput).setValue(dateString);

    }
    public void setName(String name){
        setValueToObject(nameInput,name);
    }
    public void setPhone(String phone){
        setValueToObject(phoneInput,phone);
    }

    public void clickOnCheckbox(){
        clickOnObject(acceptCheckBox);
    }
    public void clickOnAcceptButton(){
        clickOnObject(button);
    }

}

