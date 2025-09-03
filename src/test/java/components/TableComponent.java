package components;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class TableComponent {
    ElementsCollection table = $$("tr");

    public void assertTableEntry(String label, String value) {
        table.find(text(label))
            .$$("td").get(1)
            .shouldHave(exactText(value));
    }
}
