import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PhoneValidationTest {

    LocalDate date = LocalDate.now();
    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String dateString = date.plusDays(7).format(formatters);


    private static final String BASE_URL = "http://localhost:9999";
    private static final String CITY = "Москва";
    private static final String NAME = "Иванов Иван";
    private static final String PHONE = "";
    private static final String SMALL_PHONE = "+7901234567";
    private static final String LONG_PHONE = "+790123456789";

    private static final String NOT_PLUS_VALUE = "79012345678";


    @Test
    public void setEmptyValue(){

        CardDeliveryPage deliveryPage = new CardDeliveryPage(BASE_URL);
        ErrorMessages errorMessages = new ErrorMessages();

        deliveryPage.setCity(CITY);
        deliveryPage.addDaysToTheCurrentDate(7);
        deliveryPage.setName(NAME);
        deliveryPage.setPhone(PHONE);
        deliveryPage.clickOnCheckbox();
        deliveryPage.clickOnAcceptButton();

        String expected = "Поле обязательно для заполнения";
        String actual = errorMessages.getPhoneInputError()
                .shouldBe(Condition.visible)
                .text();

        Assertions.assertEquals(expected,actual);
    }


    @Test
    public void smallPhone(){


        CardDeliveryPage deliveryPage = new CardDeliveryPage(BASE_URL);
        ErrorMessages errorMessages = new ErrorMessages();

        deliveryPage.setCity(CITY);
        deliveryPage.addDaysToTheCurrentDate(7);
        deliveryPage.setName(NAME);
        deliveryPage.setPhone(SMALL_PHONE);
        deliveryPage.clickOnCheckbox();
        deliveryPage.clickOnAcceptButton();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = errorMessages.getPhoneInputError()
                .shouldBe(Condition.visible)
                .text();

        Assertions.assertEquals(expected,actual);

    }

    @Test
    public void longPhone(){


        CardDeliveryPage deliveryPage = new CardDeliveryPage(BASE_URL);
        ErrorMessages errorMessages = new ErrorMessages();

        deliveryPage.setCity(CITY);
        deliveryPage.addDaysToTheCurrentDate(7);
        deliveryPage.setName(NAME);
        deliveryPage.setPhone(LONG_PHONE);
        deliveryPage.clickOnCheckbox();
        deliveryPage.clickOnAcceptButton();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = errorMessages.getPhoneInputError()
                .shouldBe(Condition.visible)
                .text();

        Assertions.assertEquals(expected,actual);

    }

    @Test
    public void invalidFormat(){


        CardDeliveryPage deliveryPage = new CardDeliveryPage(BASE_URL);
        ErrorMessages errorMessages = new ErrorMessages();

        deliveryPage.setCity(CITY);
        deliveryPage.addDaysToTheCurrentDate(7);
        deliveryPage.setName(NAME);
        deliveryPage.setPhone(NOT_PLUS_VALUE);
        deliveryPage.clickOnCheckbox();
        deliveryPage.clickOnAcceptButton();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = errorMessages.getPhoneInputError()
                .shouldBe(Condition.visible)
                .text();

        Assertions.assertEquals(expected,actual);

    }
}
