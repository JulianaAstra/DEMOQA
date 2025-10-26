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
        System.out.println("=== DEBUG: Starting configuration ===");
        System.out.println("Remote URL: https://user1:1234@selenoid.autotests.cloud/wd/hub");
        System.out.println("Browser: chrome");
        System.out.println("Browser version: 141.0");

        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 30000;
        Configuration.pageLoadTimeout = 30000;
        Configuration.pageLoadStrategy = "eager";

        // Включим подробное логирование
        Configuration.headless = false; // на всякий случай
        Configuration.browserCapabilities = new DesiredCapabilities();

        System.out.println("=== DEBUG: Configuration completed ===");
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
//        Configuration.browser = "chrome";
//        Configuration.browserVersion = "141.0";
//        Configuration.browserSize = "1920x1080";
//        Configuration.timeout = 10000;
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                "enableVNC", true,
//                "enableVideo", true
//        ));
//        Configuration.browserCapabilities = capabilities;
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
