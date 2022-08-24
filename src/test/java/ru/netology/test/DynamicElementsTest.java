package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.page.CalendarInterface;
import ru.netology.page.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DynamicElementsTest {

    LocalDate date = LocalDate.now();
    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String dateString = date.plusDays(7).format(formatters);

    private static final String BASE_URL = "http://localhost:9999";
    private static final String CITY = "Москва";
    private static final String NAME = "Иванов Иван";
    private static final String PHONE = "+71234567890";
    private static final String PART_OF_THE_CITY = "мо";

    @Test
    public void selectCityFromDropDownList() {
        CardDeliveryPage deliveryPage = new CardDeliveryPage(BASE_URL);
        CalendarInterface calendarInterface = new CalendarInterface();

        deliveryPage.selectCityFromDropDownList(PART_OF_THE_CITY);
        calendarInterface.daysAdd(7);
        deliveryPage.setName(NAME);
        deliveryPage.setPhone(PHONE);
        deliveryPage.clickOnCheckbox();
        deliveryPage.clickOnAcceptButton();

        boolean expected = true;
        boolean  actual = deliveryPage.isASuccess("Встреча успешно забронирована на " + dateString);

        Assertions.assertEquals(expected,actual);
    }
}

