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
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000; // default 4000
    }

    @Test
    void fillFormTest() {
        String route = "/automation-practice-form";
        String firstName = "Julia";
        String lastName = "K";
        String email = "test_email@ya.ru";
        String phoneNumber = "79009009090";
        String year = "1989";
        String month = "February";
        String day = "6";
        String subjectScience = "Computer Science";
        String subjectEnglish = "English";
        String subjectArts = "Arts";
        String hobbieReading = "Reading";
        String image = "src/test/resources/cat.jpg";

        open(route);

        // user name
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);

        // email
        $("#userEmail").setValue(email);

        // gender
        $(byText("Female")).click();

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
        $("#uploadPicture").uploadFile(new File(image));




        // state and city + city
        $("#submit").click();

        // modal window selector
        $("#output #name").shouldHave(text("Alex"));
        $("#output #email").shouldHave(text("alex@egorov.com"));
        $("#output #currentAddress").shouldHave(text("Some street 1"));
        $("#output #permanentAddress").shouldHave(text("Another street 1"));
    }
}
