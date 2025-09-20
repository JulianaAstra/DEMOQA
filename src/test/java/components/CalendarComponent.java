package components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {
    SelenideElement yearSelect = $("select[class*='year']");
    SelenideElement monthSelect = $("select[class*='month']");

    SelenideElement daysContainer = $(".react-datepicker__month");

    public void setDate(String year, String month, String day) {
        yearSelect.selectOption(year);
        monthSelect.selectOption(month);

        daysContainer
                .$(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)")
                .click();
    }
}
