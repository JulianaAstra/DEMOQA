package pages;

import components.CalendarComponent;
import components.TableComponent;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.TestData;

import java.util.List;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static utils.RandomUtils.*;

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

    @Step("Открыть страницу")
    public PracticeFormPage openPage() {
        open(practiceFormRoute);
        return this;
    }

    @Step("Указать имя пользователя {firstName}")
    public PracticeFormPage setUserFirstName(String firstName) {
        firstNameInput
                .setValue(firstName);
        return this;
    }

    @Step("Указать фамилию пользователя {lastName}")
    public PracticeFormPage setUserLastName(String lastName) {
        lastNameInput
                .setValue(lastName);
        return this;
    }

    @Step("Указать email пользователя {userEmail}")
    public PracticeFormPage setUserEmail(String userEmail) {
        emailInput
                .setValue(userEmail);
        return this;
    }

    @Step("Указать пол пользователя {userGender}")
    public PracticeFormPage setUserGender(String userGender) {
        genderInputWrapper
                .$(byText(userGender))
                .click();
        return this;
    }

    @Step("Указать номер телефона пользователя {userPhoneNumber}")
    public PracticeFormPage setUserPhoneNumber(String userPhoneNumber) {
        phoneNumberInput
                .setValue(userPhoneNumber);
        return this;
    }

    @Step("Указать дату рождения пользователя: год - {year}, месяц - {month}, день - {date}")
    public PracticeFormPage setUserDateOfBirth(String year, String month, String date) {
        dateOfBirthInput.click();
        calendarComponent.shouldBe(visible);
        calendar.setDate(year, month, date);
        calendarComponent.shouldNotBe(visible);
        return this;
    }

    @Step("Указать любимый предмет пользователя {userSubject}")
    public PracticeFormPage setUserSubjects(String userSubject) {
        subjectsInput
                .shouldBe(visible)
                .setValue(userSubject)
                .pressEnter();
        subjectsAutocompleteMenu.shouldNotBe(visible);
        return this;
    }

    @Step("Указать хобби пользователя {userHobby}")
    public PracticeFormPage setUserHobbies(String userHobby) {
        hobbiesWrapper.
                shouldBe(visible)
                .$(byText(userHobby))
                .click();
        return this;
    }

    @Step("Указать аватар пользователя {userPictureName}")
    public PracticeFormPage setUserPicture(String userPictureName) {
        pictureUpload.uploadFromClasspath(userPictureName);
        return this;
    }

    @Step("Указать адрес пользователя {userAddress}")
    public PracticeFormPage setUserCurrentAddress(String userAddress) {
        currentAddressInput.setValue(userAddress);
        return this;
    }

    @Step("Указать штат {userState} и город {userCity} пользователя")
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

    @Step("Отправить форму")
    public PracticeFormPage sendForm() {
        submitButton
                .shouldBe(visible)
                .scrollTo()
                .click();
        return this;
    }

    @Step("Проверить значение в таблице {label} - {value}")
    public PracticeFormPage checkTableValue(String label, String value) {
        tableComponent.shouldBe(visible);
        tableWithUserData.assertTableEntry(label, value);
        return this;
    }

    @Step("Проверить валидацию некорректного номера телефона")
    public PracticeFormPage checkPhoneValidation() {
        phoneNumberInput.shouldHave(cssValue("border-color", validationBorderColor));
        return this;
    }

    @Step("Проверить валидацию обязательного поля имя")
    public PracticeFormPage checkFirstNameValidation() {
        firstNameInput.shouldHave(cssValue("border-color", validationBorderColor));
        return this;
    }

    @Step("Проверить валидацию обязательного поля фамилия")
    public PracticeFormPage checkLastNameValidation() {
       lastNameInput.shouldHave(cssValue("border-color", validationBorderColor));
        return this;
    }

    @Step("Проверить валидацию обязательного поля пол")
    public PracticeFormPage checkGenderValidation() {
        genderInputWrapper.$$(".custom-control label")
                .forEach(element -> element.shouldHave(cssValue("color", validationTextColor)));
        return this;
    }

    @Step("Проверить что форма не была отправлена")
    public PracticeFormPage checkFormNotSent() {
        tableComponent.shouldNotBe(visible);
        return this;
    }
}
