package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.page.CardDeliveryPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CheckBoxTest {

    LocalDate date = LocalDate.now();
    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String dateString = date.plusDays(7).format(formatters);


    private static final String BASE_URL = "http://localhost:9999";
    private static final String CITY = "Москва";
    private static final String NAME = "Иванов Иван";
    private static final String PHONE = "+71234567890";

    @Test
    void CheckBoxNotSelected() {
        CardDeliveryPage deliveryPage = new CardDeliveryPage(BASE_URL);

        deliveryPage.setCity(CITY);
        deliveryPage.addDaysToTheCurrentDate(7);
        deliveryPage.setName(NAME);
        deliveryPage.setPhone(PHONE);

        deliveryPage.clickOnAcceptButton();


        Assertions.assertTrue(deliveryPage.checkBoxError());


    }


}
