package pages;

import com.codeborne.selenide.SelenideElement;

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

    public TextBoxPage openTextBoxPage() {
        open(textBoxPageRoute);
        return this;
    }

    public TextBoxPage setUserFirstName(String userFirstName) {
        firstNameInput.setValue(userFirstName);
        return this;
    }

    public TextBoxPage setUserEmail(String userEmail) {
        emailInput.setValue(userEmail);
        return this;
    }

    public TextBoxPage setUserCurrentAddress(String userCurrentAddress) {
        currentAddressInput.setValue(userCurrentAddress);
        return this;
    }

    public TextBoxPage setUserPermanentAddress(String userPermanentAddress) {
        permanentAddressInput.setValue(userPermanentAddress);
        return this;
    }

    public TextBoxPage sendForm() {
        submitButton.click();
        return this;
    }

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
