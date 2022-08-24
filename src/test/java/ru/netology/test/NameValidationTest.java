package ru.netology.test;

import ru.netology.page.*;
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

    deliveryPage.setCity(CITY);
    deliveryPage.addDaysToTheCurrentDate(7);
    deliveryPage.setName(EMPTY_NAME);
    deliveryPage.setPhone(PHONE);
    deliveryPage.clickOnCheckbox();
    deliveryPage.clickOnAcceptButton();

    String expected = "Поле обязательно для заполнения";
    String actual = deliveryPage.getNameInputError();

    Assertions.assertEquals(expected,actual);


}



@Test
public void  setNameUnicodeCyrillicExtension(){
    CardDeliveryPage deliveryPage = new CardDeliveryPage(BASE_URL);
    deliveryPage.setCity(CITY);
    deliveryPage.addDaysToTheCurrentDate(7);
    deliveryPage.setName(NAME);
    deliveryPage.setPhone(PHONE);
    deliveryPage.clickOnCheckbox();
    deliveryPage.clickOnAcceptButton();

    boolean expected = true;
    boolean actual = deliveryPage.isASuccess("Встреча успешно забронирована на " + dateString);


    Assertions.assertEquals(expected, actual);

}

    @Test
    public void  setNumericValue(){
        CardDeliveryPage deliveryPage = new CardDeliveryPage(BASE_URL);

        deliveryPage.setCity(CITY);
        deliveryPage.addDaysToTheCurrentDate(7);
        deliveryPage.setName(NUMERIC_NAME);
        deliveryPage.setPhone(PHONE);
        deliveryPage.clickOnCheckbox();
        deliveryPage.clickOnAcceptButton();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = deliveryPage.getNameInputError();


        Assertions.assertEquals(expected,actual);

    }
}
