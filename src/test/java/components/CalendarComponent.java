package components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {
    SelenideElement
            yearSelect = $("select[class*='year']"),
            monthSelect = $("select[class*='month']");

    ElementsCollection
            weeks = $$(".react-datepicker__week");

    public void setDate(String year, String month, Integer week, String day) {
        yearSelect.selectOption(year);
        monthSelect.selectOption(month);
        weeks
            .get(week)
            .$$("[role='option']")
            .find(exactText(day))
            .click();
    }
}
