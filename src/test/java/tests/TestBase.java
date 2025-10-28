package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {
    @BeforeAll
    static void setupConfig() {

        // ОТЛАДКА: выведем все системные свойства
        System.out.println("=== SYSTEM PROPERTIES ===");
        System.getProperties().forEach((key, value) -> {
            if (key.toString().contains("selenide") ||
                    key.toString().contains("browser") ||
                    key.toString().contains("baseUrl") ||
                    key.toString().contains("version")) {
                System.out.println("PROPERTY: " + key + " = " + value);
            }
        });

        String remote = System.getProperty("selenide.remote");
        String baseUrl = System.getProperty("baseUrl");

        Configuration.baseUrl = baseUrl;
        Configuration.remote = remote;

        System.out.println("=== CONFIGURATION ===");
        System.out.println("Remote from system: " + remote);
        System.out.println("BaseUrl from system: " + baseUrl);


        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("windowSize", "1920x1080");
        Configuration.browserVersion = System.getProperty("version", "141");
        Configuration.pageLoadStrategy = "eager";


//        Configuration.baseUrl = "https://demoqa.com";
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addAllureListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
