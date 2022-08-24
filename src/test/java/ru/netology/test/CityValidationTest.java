package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.page.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CityValidationTest {

    LocalDate date = LocalDate.now();
    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String dateString = date.plusDays(7).format(formatters);
    private static final String BASE_URL = "http://localhost:9999";
    private static final String NOT_CYRILLIC_CITY = "Moscow";
    private static final String EMPTY_VALUE = "";
    private static final String NON_EXISTENT_CITY = "Энск";



    @Test
    public void emptyCity(){
        CardDeliveryPage deliveryPage = new CardDeliveryPage(BASE_URL);

        deliveryPage.setCity(EMPTY_VALUE);
        deliveryPage.addDaysToTheCurrentDate(7);
        deliveryPage.setName("Сергей Суриков");
        deliveryPage.setPhone("+79123456789");
        deliveryPage.clickOnCheckbox();
        deliveryPage.clickOnAcceptButton();

        String expected = "Поле обязательно для заполнения";
        String actual = deliveryPage.getCityInputError();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void notCyrillicValue(){
        CardDeliveryPage deliveryPage = new CardDeliveryPage(BASE_URL);

        deliveryPage.setCity(NOT_CYRILLIC_CITY );
        deliveryPage.addDaysToTheCurrentDate(7);
        deliveryPage.setName("Сергей Суриков");
        deliveryPage.setPhone("+79123456789");
        deliveryPage.clickOnCheckbox();
        deliveryPage.clickOnAcceptButton();

        String expected = "Доставка в выбранный город недоступна";
        String actual = deliveryPage.getCityInputError();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void nonExistentCity(){
        CardDeliveryPage deliveryPage = new CardDeliveryPage(BASE_URL);

        deliveryPage.setCity(NON_EXISTENT_CITY);
        deliveryPage.addDaysToTheCurrentDate(7);
        deliveryPage.setName("Сергей Суриков");
        deliveryPage.setPhone("+79123456789");
        deliveryPage.clickOnCheckbox();
        deliveryPage.clickOnAcceptButton();

        String expected = "Доставка в выбранный город недоступна";
        String actual = deliveryPage.getCityInputError();
        Assertions.assertEquals(expected, actual);
    }


}
