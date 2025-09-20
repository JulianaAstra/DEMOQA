package tests;

import pages.TextBoxPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TextBoxTests extends TestBase {
    TextBoxPage textBoxPage;
    TestData testData;

    @BeforeEach
    public void setData() {
        textBoxPage = new TextBoxPage();
        testData = new TestData();
    }

    @Test
    void fillFormTest() {
        textBoxPage
            .openTextBoxPage()
            .setUserFirstName(testData.firstName)
            .setUserEmail(testData.email)
            .setUserCurrentAddress(testData.currentAddress)
            .setUserPermanentAddress(testData.permanentAddress)
            .sendForm()
            .checkOutput(testData.firstName, testData.email, testData.currentAddress, testData.permanentAddress);
    }
}
