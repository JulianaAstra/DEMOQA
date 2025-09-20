package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import static tests.TestData.*;
import static utils.Utils.addZeroToNumber;

public class PracticeFormTests extends TestBase{
    PracticeFormPage practiceFormPage;
    TestData testData;

    @BeforeEach
    void setupUserData() {
        practiceFormPage = new PracticeFormPage();
        testData = new TestData();
    }

    @Test
    void fillFullFormTest() {
        practiceFormPage
                .openPage()
                .setUserFirstName(testData.firstName)
                .setUserLastName(testData.lastName)
                .setUserEmail(testData.email)
                .setUserGender(testData.gender)
                .setUserPhoneNumber(testData.phoneNumber)
                .setUserDateOfBirth(String.valueOf(testData.year), String.valueOf(testData.month), testData.fullDate)
                .setUserSubjects(testData.subject)
                .setUserHobbies(testData.hobby)
                .setUserPicture(testData.imageName)
                .setUserCurrentAddress(testData.currentAddress)
                .setUserStateAndCity(testData.state, testData.city)
                .sendForm()
                .checkTableValue("Student Name", testData.firstName + " " + testData.lastName)
                .checkTableValue("Student Email", testData.email)
                .checkTableValue("Gender", testData.gender)
                .checkTableValue("Mobile", testData.phoneNumber)
                .checkTableValue("Date of Birth", addZeroToNumber(testData.day) + " " + testData.month + "," + testData.year)
                .checkTableValue("Subjects", testData.subject)
                .checkTableValue("Hobbies", testData.hobby)
                .checkTableValue("Picture", testData.imageName)
                .checkTableValue("Address", testData.currentAddress)
                .checkTableValue("State and City", testData.state + " " + testData.city);
    }

    @Test
    void fillMinimumFormTest() {
        practiceFormPage
                .openPage()
                .setUserFirstName(testData.firstName)
                .setUserLastName(testData.lastName)
                .setUserGender(testData.gender)
                .setUserPhoneNumber(testData.phoneNumber)
                .sendForm()
                .checkTableValue("Student Name", testData.firstName + " " + testData.lastName)
                .checkTableValue("Gender", testData.gender)
                .checkTableValue("Mobile", testData.phoneNumber);
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
                .setRandomUserFirstName()
                .setRandomUserLastName()
                .setRandomUserGender()
                .setUserPhoneNumber(invalidPhoneNumber)
                .sendForm()
                .checkPhoneValidation()
                .checkFormNotSent();
    }
}
