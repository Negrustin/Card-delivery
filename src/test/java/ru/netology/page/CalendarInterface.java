package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class CalendarInterface {

    private final ElementsCollection days = $$x("//*[@data-day]");
    private static final SelenideElement dateInput = $x("//*[@data-test-id = 'date']//input");
    private static final SelenideElement nextMonthButton = $x("//div[@data-step = '1']");



    public void selectDateDaysAdd(int plusDays) {

        LocalDateTime dateTime = LocalDate.now().plusDays(plusDays).atTime(LocalTime.MIN);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
        String millis = Long.toString(zonedDateTime.toInstant().toEpochMilli());

        List<String> milliSeconds = new ArrayList<>();
        List<String> tmp = new ArrayList<>();

        for (SelenideElement element : days) {
            milliSeconds.add(element.getAttribute("data-day"));
        }
        dateInput.click();
        for(int i = 0; !milliSeconds.contains(millis); i++) {
            nextMonthButton.click();
            for (SelenideElement element : days) {
                tmp.add(element.getAttribute("data-day"));
                milliSeconds = tmp;
            }
        }
            days.findBy(Condition.attribute("data-day", millis)).click();

    }

}


