import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class HappyPathTest {
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String dateString = date.plusDays(7).format(formatters);


    private static final String BASE_URL = "http://localhost:9999";
    private static final String CITY = "Москва";
    private static final String NAME = "Иванов Иван";
    private static final String PHONE = "+71234567890";


    @Test
    void HappyPathTest() {
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
                .shouldBe(Condition.visible,Duration.ofSeconds(15))
                .text();

        Assertions.assertEquals(expected, actual);


    }

}






