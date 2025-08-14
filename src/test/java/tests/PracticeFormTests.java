package tests;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillFormTest() {
        String route = "/automation-practice-form";
        String firstName = "Julia";
        String lastName = "K";
        String email = "test_email@ya.ru";
        String gender = "Female";
        String phoneNumber = "7900900909";
        String year = "1989";
        String month = "February";
        String day = "6";
        String subjectScience = "Computer Science";
        String subjectEnglish = "English";
        String subjectArts = "Arts";
        String hobbieReading = "Reading";
        String routeToImg = "src/test/resources/";
        String imageName = "cat.jpg";
        String currentAddress = "Ryazan city";
        String state = "Rajasthan";
        String city = "Jaipur";

        open(route);

        // user name
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);

        // email
        $("#userEmail").setValue(email);

        // gender
        $(byText(gender)).click();

        // phone number
        $("#userNumber").setValue(phoneNumber);

        // date of birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker").shouldBe(visible);

        $("select[class*='year']").selectOption(year);
        $("select[class*='month']").selectOption(month);

        $$(".react-datepicker__week").get(1)
                .$$("[role='option']")
                .find(exactText(day))
                .click();

        $(".react-datepicker").shouldNotBe(visible);

        // subjects
        $("#subjectsInput").setValue(subjectScience).pressEnter();
        $("#subjectsInput").setValue(subjectEnglish).pressEnter();
        $("#subjectsInput").setValue(subjectArts).pressEnter();
        $(".subjects-auto-complete_menu").shouldNotBe(visible);

        // hobbies
        $("#hobbiesWrapper").$(byText(hobbieReading)).click();

        // picture
        $("#uploadPicture").uploadFile(new File(routeToImg + imageName));

        // current address
        $("#currentAddress").setValue(currentAddress);

        // state and city
        $("#state").scrollTo().click();
        $("#state").find("[class*='menu']").shouldBe(visible);
        $(byText(state)).click();

        $("#city").click();
        $("#city").find("[class*='menu']").shouldBe(visible);
        $(byText(city)).click();

        // submit
        $("#submit").click();

        // check data in modal window

        $(".modal-content").shouldBe(visible);

        assertTableEntry("Student Name", firstName + " " + lastName);
        assertTableEntry("Student Email", email);
        assertTableEntry("Gender", gender);
        assertTableEntry("Mobile", phoneNumber);
        assertTableEntry("Date of Birth", "0" + day + " " + month + "," + year);
        assertTableEntry("Subjects", subjectScience + ", " + subjectEnglish + ", " + subjectArts);
        assertTableEntry("Hobbies", hobbieReading);
        assertTableEntry("Picture", imageName);
        assertTableEntry("Address", currentAddress);
        assertTableEntry("State and City", state + " " + city);
    }

    private void assertTableEntry(String label, String value) {
        $$("tr").find(text(label))
                .$$("td").get(1)
                .shouldHave(exactText(value));
    }
}
