import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NameValidationTest {
    private static final String BASE_URL = "http://localhost:9999";
    private static final String CITY = "Москва";
    private static final String NAME = "Семён Слепаков";
    private static final String PHONE = "+71234567890";
    private static final String EMPTY_NAME = "";
    private static final String NUMERIC_NAME = "12345";

    LocalDate date = LocalDate.now();
    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String dateString = date.plusDays(7).format(formatters);
@Test
public void emptyValueName(){
    CardDeliveryPage deliveryPage = new CardDeliveryPage(BASE_URL);
    ErrorMessages errorMessages = new ErrorMessages();

    deliveryPage.setCity(CITY);
    deliveryPage.addDaysToTheCurrentDate(7);
    deliveryPage.setName(EMPTY_NAME);
    deliveryPage.setPhone(PHONE);
    deliveryPage.clickOnCheckbox();
    deliveryPage.clickOnAcceptButton();

    String expected = "Фамилия и имя\nПоле обязательно для заполнения";
    String actual = errorMessages.getNameInputError()
            .shouldBe(Condition.visible)
            .text();

    Assertions.assertEquals(expected,actual);


}



@Test
public void  setNameUnicodeCyrillicExtension(){
    CardDeliveryPage deliveryPage = new CardDeliveryPage(BASE_URL);
    SuccessPage successPage = new SuccessPage();
    deliveryPage.setCity(CITY);
    deliveryPage.addDaysToTheCurrentDate(7);
    deliveryPage.setName(NAME);
    deliveryPage.setPhone(PHONE);
    deliveryPage.clickOnCheckbox();
    deliveryPage.clickOnAcceptButton();

    String expected = "Успешно!" + "\nВстреча успешно забронирована на " + dateString;
    String  actual =successPage.getSuccessWindow()
            .shouldBe(Condition.visible, Duration.ofSeconds(15))
            .text();

    Assertions.assertEquals(expected, actual);

}

    @Test
    public void  setNumericValue(){
        CardDeliveryPage deliveryPage = new CardDeliveryPage(BASE_URL);
        ErrorMessages errorMessages = new ErrorMessages();

        deliveryPage.setCity(CITY);
        deliveryPage.addDaysToTheCurrentDate(7);
        deliveryPage.setName(NUMERIC_NAME);
        deliveryPage.setPhone(PHONE);
        deliveryPage.clickOnCheckbox();
        deliveryPage.clickOnAcceptButton();

        String expected = "Фамилия и имя\nИмя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = errorMessages.getNameInputError()
                .shouldBe(Condition.visible)
                .text();

        Assertions.assertEquals(expected,actual);

    }
}
