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

    private static final String PART_OF_THE_CITY = "мо";
    private static final String NAME = "Иванов Иван";
    private static final String PHONE = "+71234567890";


    @Test
    public void SetDataWithDynamicgiElements(){
        CardDeliveryPage deliveryPage = new CardDeliveryPage(BASE_URL);
        CalendarInterface calendarInterface = new CalendarInterface();

        deliveryPage.setCity(CITY);
        calendarInterface.daysAdd(7);
        deliveryPage.setName(NAME);
        deliveryPage.setPhone(PHONE);
        deliveryPage.clickOnCheckbox();
        deliveryPage.clickOnAcceptButton();


        deliveryPage.isASuccess("Встреча успешно забронирована на " + dateString);
    }
}

