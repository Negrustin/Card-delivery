import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class CalendarDate {

    private static final ElementsCollection monthElement = $$x("//td[text() != '']");
    private static final SelenideElement day = $x("//td[contains(@class, 'today')]");
    private static final SelenideElement dateInput = $x("//*[@data-test-id = 'date']//input");
    private static final SelenideElement nextMonthButton = $x("//div[@data-step = '1']");

    public void choseDayAfter(int dayPlus) {
        int daysAdd = dayPlus;
        dateInput.click();
        int todayDay = Integer.parseInt(day.text());
        int monthDaysCount = monthElement.size();
        int ostatok = monthDaysCount - todayDay;

        if (todayDay  + dayPlus <= monthDaysCount){
        monthElement.get(todayDay + dayPlus -1).click();
    }else {
            while (todayDay  + daysAdd > monthDaysCount){
                nextMonthButton.click();
                daysAdd = daysAdd - ostatok;

            }monthElement.get(daysAdd).click();
        }

//        if (todayDay + dayPlus > monthDaysCount) {
//            while (todayDay + daysAdd > monthDaysCount) {
//                daysAdd = daysAdd - (monthDaysCount - todayDay + 1);
//                nextMonthButton.click();
//            }monthElement.get(daysAdd - 1).click();
//        }else{monthElement.get(todayDay + dayPlus -1).click();
//
//        }

    }
//        }
//
//    if (todayDay  + dayPlus <= monthDaysCount){
//        monthElement.get(todayDay + dayPlus -1).click();
//
//    }
//    }
}

