package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

import java.util.List;

public class PracticeFormTests extends TestBase{
    String firstName;
    String lastName;
    String email;
    String gender;
    String phoneNumber;
    String invalidPhoneNumber;
    String year;
    String month;
    Integer week = 1;
    String day;
    List<String> subjects;
    List<String> hobbies;
    String subjectScience;
    String subjectEnglish;
    String subjectArts;
    String hobbieReading;
    String imageName;
    String currentAddress;
    String state;
    String city;

    PracticeFormPage practiceFormPage;

    @BeforeEach
    void setupUserData() {
        firstName = "Julia";
        lastName = "K";
        email = "test_email@ya.ru";
        gender = "Female";
        phoneNumber = "7900900909";
        invalidPhoneNumber = "12345";
        year = "1989";
        month = "February";
        day = "6";
        subjectScience = "Computer Science";
        subjectEnglish = "English";
        subjectArts = "Arts";
        subjects = List.of(subjectScience, subjectEnglish, subjectArts);
        hobbieReading = "Reading";
        hobbies = List.of(hobbieReading);
        imageName = "cat.jpg";
        currentAddress = "Ryazan city";
        state = "Rajasthan";
        city = "Jaipur";

        practiceFormPage = new PracticeFormPage();
    }

    @Test
    void fillFullFormTest() {
        practiceFormPage
                .openPage()
                .setUserFirstName(firstName)
                .setUserLastName(lastName)
                .setUserEmail(email)
                .setUserGender(gender)
                .setUserPhoneNumber(phoneNumber)
                .setUserDateOfBirth(year, month, week, day)
                .setUserSubjects(subjects)
                .setUserHobbies(hobbies)
                .setUserPicture(imageName)
                .setUserCurrentAddress(currentAddress)
                .setUserStateAndCity(state, city)
                .sendForm()
                .checkTableValue("Student Name", firstName + " " + lastName)
                .checkTableValue("Student Email", email)
                .checkTableValue("Gender", gender)
                .checkTableValue("Mobile", phoneNumber)
                .checkTableValue("Date of Birth", "0" + day + " " + month + "," + year)
                .checkTableValue("Subjects", subjectScience + ", " + subjectEnglish + ", " + subjectArts)
                .checkTableValue("Hobbies", hobbieReading)
                .checkTableValue("Picture", imageName)
                .checkTableValue("Address", currentAddress)
                .checkTableValue("State and City", state + " " + city);
    }

    @Test
    void fillMinimumFormTest() {
        practiceFormPage
                .openPage()
                .setUserFirstName(firstName)
                .setUserLastName(lastName)
                .setUserGender(gender)
                .setUserPhoneNumber(phoneNumber)
                .sendForm()
                .checkTableValue("Student Name", firstName + " " + lastName)
                .checkTableValue("Gender", gender)
                .checkTableValue("Mobile", phoneNumber);
    }

    @Test
    void fillEmptyFormTest() {
        practiceFormPage
                .openPage()
                .sendForm()
                .checkFirstNameValidation()
                .checkLastNameValidation()
                .checkPhoneValidation()
                .checkGenderValidation()
                .checkFormNotSent();
    }

    @Test
    void fillFormInvalidPhoneTest() {
        practiceFormPage
                .openPage()
                .setUserFirstName(firstName)
                .setUserLastName(lastName)
                .setUserGender(gender)
                .setUserPhoneNumber(invalidPhoneNumber)
                .sendForm()
                .checkPhoneValidation()
                .checkFormNotSent();
    }
}
