package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import static tests.TestData.*;

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
                .setUserDateOfBirth(year, month, week, day)
                .setUserSubjects(testData.subject)
                .setUserHobbies(testData.hobby)
                .setUserPicture(testData.imageName)
                .setUserCurrentAddress(testData.currentAddress)
                .setUserStateAndCity(state, city)
                .sendForm()
                .checkTableValue("Student Name", testData.firstName + " " + testData.lastName)
                .checkTableValue("Student Email", testData.email)
                .checkTableValue("Gender", testData.gender)
                .checkTableValue("Mobile", testData.phoneNumber)
                .checkTableValue("Date of Birth", "0" + day + " " + month + "," + year)
                .checkTableValue("Subjects", testData.subject)
                .checkTableValue("Hobbies", testData.hobby)
                .checkTableValue("Picture", testData.imageName)
                .checkTableValue("Address", testData.currentAddress)
                .checkTableValue("State and City", state + " " + city);
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
