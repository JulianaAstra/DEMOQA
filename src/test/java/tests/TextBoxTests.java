package tests;

import pages.TextBoxPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static utils.RandomUtils.*;

public class TextBoxTests extends TestBase {
    TextBoxPage textBoxPage;
    String firstName;
    String email;
    String currentAddress;
    String permanentAddress;

    @BeforeEach
    public void setData() {
        textBoxPage = new TextBoxPage();
        firstName = getRandomFirstName();
        email = getRandomEmail();
        currentAddress = getRandomAddress();
        permanentAddress = getRandomAddress();
    }

    @Test
    void fillFormTest() {
        textBoxPage
            .openTextBoxPage()
            .setUserFirstName(firstName)
            .setUserEmail(email)
            .setUserCurrentAddress(currentAddress)
            .setUserPermanentAddress(permanentAddress)
            .sendForm()
            .checkOutput(firstName, email, currentAddress, permanentAddress);
    }
}
