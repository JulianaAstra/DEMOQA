package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import static tests.TestData.*;
import static utils.RandomUtils.*;

public class PracticeFormTests extends TestBase{
    PracticeFormPage practiceFormPage;

    @BeforeEach
    void setupUserData() {
        practiceFormPage = new PracticeFormPage();
    }

    @Test
    void fillFullFormTest() {
        String firstName = getRandomFirstName();
        String lastName = getRandomLastName();
        String gender = getRandomGender();
        String phoneNumber = getRandomPhone();
        String email = getRandomEmail();
        String currentAddress = getRandomAddress();
        String imageName = getRandomImageName();
        String hobby = getRandomHobby();
        String subject = getRandomSubject();

        practiceFormPage
                .openPage()
                .setUserFirstName(firstName)
                .setUserLastName(lastName)
                .setUserEmail(email)
                .setUserGender(gender)
                .setUserPhoneNumber(phoneNumber)
                .setUserDateOfBirth(year, month, week, day)
                .setUserSubjects(subject)
                .setUserHobbies(hobby)
                .setUserPicture(imageName)
                .setUserCurrentAddress(currentAddress)
                .setUserStateAndCity(state, city)
                .sendForm()
                .checkTableValue("Student Name", firstName + " " + lastName)
                .checkTableValue("Student Email", email)
                .checkTableValue("Gender", gender)
                .checkTableValue("Mobile", phoneNumber)
                .checkTableValue("Date of Birth", "0" + day + " " + month + "," + year)
                .checkTableValue("Subjects", subject)
                .checkTableValue("Hobbies", hobby)
                .checkTableValue("Picture", imageName)
                .checkTableValue("Address", currentAddress)
                .checkTableValue("State and City", state + " " + city);
    }

    @Test
    void fillMinimumFormTest() {
        String firstName = getRandomFirstName();
        String lastName = getRandomLastName();
        String gender = getRandomGender();
        String phoneNumber = getRandomPhone();

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
                .setRandomUserFirstName()
                .setRandomUserLastName()
                .setRandomUserGender()
                .setUserPhoneNumber(invalidPhoneNumber)
                .sendForm()
                .checkPhoneValidation()
                .checkFormNotSent();
    }
}
