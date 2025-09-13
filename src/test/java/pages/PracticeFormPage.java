package pages;

import components.CalendarComponent;
import components.TableComponent;
import com.codeborne.selenide.SelenideElement;
import java.util.List;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {
    String practiceFormRoute = "/automation-practice-form";

    SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderInputWrapper = $("#genterWrapper"),
            phoneNumberInput =  $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            calendarComponent = $(".react-datepicker"),
            subjectsInput = $("#subjectsInput"),
            subjectsAutocompleteMenu = $(".subjects-auto-complete_menu"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            pictureUpload = $("#uploadPicture"),
            currentAddressInput =  $("#currentAddress"),
            stateSelect = $("#state"),
            citySelect = $("#city"),
            submitButton = $("#submit"),
            tableComponent = $(".modal-content");

    CalendarComponent calendar = new CalendarComponent();
    TableComponent tableWithUserData = new TableComponent();

    String validationBorderColor = "rgb(220, 53, 69)";
    String validationTextColor = "rgba(220, 53, 69, 1)";

    public PracticeFormPage openPage() {
        open(practiceFormRoute);
        return this;
    }

    public PracticeFormPage setUserFirstName(String firstName) {
        firstNameInput
                .setValue(firstName);
        return this;
    }

    public PracticeFormPage setUserLastName(String lastName) {
        lastNameInput
                .setValue(lastName);
        return this;
    }

    public PracticeFormPage setUserEmail(String userEmail) {
        emailInput
                .setValue(userEmail);
        return this;
    }

    public PracticeFormPage setUserGender(String userGender) {
        genderInputWrapper
                .$(byText(userGender))
                .click();
        return this;
    }

    public PracticeFormPage setUserPhoneNumber(String userPhoneNumber) {
        phoneNumberInput
                .setValue(userPhoneNumber);
        return this;
    }

    public PracticeFormPage setUserDateOfBirth(String year, String month, Integer week, String day) {
        dateOfBirthInput.click();
        calendarComponent.shouldBe(visible);
        calendar.setDate(year, month, week, day);
        calendarComponent.shouldNotBe(visible);
        return this;
    }

    public PracticeFormPage setUserSubjects(List<String> userSubjects) {
        for (String subject: userSubjects) {
            subjectsInput
                    .shouldBe(visible)
                    .setValue(subject)
                    .pressEnter();
        }
        subjectsAutocompleteMenu.shouldNotBe(visible);
        return this;
    }

    public PracticeFormPage setUserHobbies(List<String> userHobbies) {
        for (String hobbie: userHobbies) {
            hobbiesWrapper.
                    shouldBe(visible)
                    .$(byText(hobbie))
                    .click();
        }
        return this;
    }

    public PracticeFormPage setUserPicture(String userPictureName) {
        pictureUpload.uploadFromClasspath(userPictureName);
        return this;
    }

    public PracticeFormPage setUserCurrentAddress(String userAddress) {
        currentAddressInput.setValue(userAddress);
        return this;
    }

    public PracticeFormPage setUserStateAndCity(String userState, String userCity) {
        stateSelect
                .scrollTo()
                .click();
        stateSelect
                .find("[class*='menu']")
                .shouldBe(visible)
                .$(byText(userState)).click();

        citySelect.shouldBe(interactable)
                .click();

        citySelect
                .find("[class*='menu']")
                .shouldBe(visible)
                .$(byText(userCity))
                .click();
        return this;
    }

    public PracticeFormPage sendForm() {
        submitButton
                .scrollTo()
                .click();
        return this;
    }

    public PracticeFormPage checkTableValue(String label, String value) {
        tableComponent.shouldBe(visible);
        tableWithUserData.assertTableEntry(label, value);
        return this;
    }

    public PracticeFormPage checkPhoneValidation() {
        phoneNumberInput.shouldHave(cssValue("border-color", validationBorderColor));
        return this;
    }

    public PracticeFormPage checkFirstNameValidation() {
        firstNameInput.shouldHave(cssValue("border-color", validationBorderColor));
        return this;
    }

    public PracticeFormPage checkLastNameValidation() {
       lastNameInput.shouldHave(cssValue("border-color", validationBorderColor));
        return this;
    }

    public PracticeFormPage checkGenderValidation() {
        genderInputWrapper.$$(".custom-control label")
                .forEach(element -> element.shouldHave(cssValue("color", validationTextColor)));
        return this;
    }

    public PracticeFormPage checkFormNotSent() {
        tableComponent.shouldNotBe(visible);
        return this;
    }
}
