package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {
    String textBoxPageRoute = "/text-box";

    SelenideElement
        firstNameInput = $("#userName"),
        emailInput =  $("#userEmail"),
        currentAddressInput = $("#currentAddress"),
        permanentAddressInput = $("#permanentAddress"),
        submitButton = $("#submit"),
        output = $("#output"),
        firsNameOutput =  $("#output #name"),
        emailOutput = $("#output #email"),
        currentAddressOutput = $("#output #currentAddress"),
        permanentAddressOutput = $("#output #permanentAddress");

    @Step("Открыть страницу")
    public TextBoxPage openTextBoxPage() {
        open(textBoxPageRoute);
        return this;
    }

    @Step("Указать имя пользователя {userFirstName}")
    public TextBoxPage setUserFirstName(String userFirstName) {
        firstNameInput.setValue(userFirstName);
        return this;
    }

    @Step("Указать email пользователя {userEmail}")
    public TextBoxPage setUserEmail(String userEmail) {
        emailInput.setValue(userEmail);
        return this;
    }

    @Step("Указать фактический адрес пользователя {userCurrentAddress}")
    public TextBoxPage setUserCurrentAddress(String userCurrentAddress) {
        currentAddressInput.setValue(userCurrentAddress);
        return this;
    }

    @Step("Указать постоянный адрес пользователя {userPermanentAddress}")
    public TextBoxPage setUserPermanentAddress(String userPermanentAddress) {
        permanentAddressInput.setValue(userPermanentAddress);
        return this;
    }

    @Step("Отправить форму")
    public TextBoxPage sendForm() {
        submitButton.scrollTo().click();
        return this;
    }

    @Step("Проверить вывод: имя пользователя - {userFirstName}, емейл пользователя - {userEmail}, фактический адрес - {userCurrentAddress}, постоянный адрес - {userPermanentAddress}")
    public TextBoxPage checkOutput(
            String userFirstName,
            String userEmail,
            String userCurrentAddress,
            String userPermanentAddress) {

        output.shouldBe(visible);

        firsNameOutput.shouldHave(text(userFirstName));
        emailOutput.shouldHave(text(userEmail));
        currentAddressOutput.shouldHave(text(userCurrentAddress));
        permanentAddressOutput.shouldHave(text(userPermanentAddress));

        return this;
    }
}
