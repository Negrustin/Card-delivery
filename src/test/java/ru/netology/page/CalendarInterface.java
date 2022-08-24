package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class CalendarInterface {

    private static ElementsCollection monthElement = $$x("//td[text() != '']");
    private static final SelenideElement day = $x("//td[contains(@class, 'today')]");
    private static final SelenideElement dateInput = $x("//*[@data-test-id = 'date']//input");
    private static final SelenideElement nextMonthButton = $x("//div[@data-step = '1']");
    private LocalDate date = LocalDate.now();

    public void daysAdd(int dayPlus) {
        dateInput.click();
        int monthDaysCount = monthElement.size();
        int todayDay = Integer.parseInt(day.text());
        int daysLast = dayPlus - (monthDaysCount - todayDay);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd");
        int dateInt = Integer.parseInt(date.plusDays(dayPlus).format(formatters));

        if (todayDay + dayPlus <= monthDaysCount) {
            monthElement.get(todayDay + dayPlus - 1).click();
        }else{
            nextMonthButton.click();
            monthDaysCount = monthElement.size();
            while (daysLast > monthDaysCount){
                monthDaysCount = monthElement.size();
                nextMonthButton.click();
                monthDaysCount = monthElement.size();
                daysLast = daysLast - monthDaysCount;
            }
            monthElement.get(dateInt-1).click();
        }
    }
}
