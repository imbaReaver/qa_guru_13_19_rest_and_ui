package guru.qa.tests;

import guru.qa.config.Project;
import guru.qa.helpers.AllureAttachments;
import guru.qa.helpers.DriverSettings;
import guru.qa.helpers.DriverUtils;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.pages.CustomerInfoPage;
import guru.qa.pages.LoginPage;
import guru.qa.pages.RegistrationPage;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import guru.qa.data.TestData;


@ExtendWith({AllureJunit5.class})
public class TestBase {

    static TestData testData = new TestData();
    RegistrationPage registrationPage = new RegistrationPage();
    CustomerInfoPage customerInfoPage = new CustomerInfoPage();
    LoginPage loginPage = new LoginPage();

    @BeforeAll
    static void beforeAll() {
        DriverSettings.configure();
    }

    @BeforeEach
    public void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    public void afterEach() {
        String sessionId = DriverUtils.getSessionId();

        AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSource();
        AllureAttachments.addBrowserConsoleLogs();

        Selenide.closeWebDriver();

        if (Project.isVideoOn()) {
            AllureAttachments.addVideo(sessionId);
        }
    }
}
