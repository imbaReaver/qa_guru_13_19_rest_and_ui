package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import guru.qa.config.Project;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static guru.qa.helpers.CustomApiListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;


public class WebShopTests extends TestBase {


    @BeforeAll
    static void configureBaseUrl() {
        RestAssured.baseURI = Project.config.apiUrl();
        Configuration.baseUrl = Project.config.webUrl();
    }

    @Test
    @DisplayName("User registration test")
    void registrationTest() {
        step("Fill and check registration form (API)", () ->
                given()
                        .filter(withCustomTemplates())
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .formParam("FirstName", testData.firstName)
                        .formParam("LastName", testData.lastName)
                        .formParam("Email", testData.fakeEmail)
                        .formParam("Password", testData.fakePassword)
                        .formParam("ConfirmPassword", testData.fakePassword)
                        .when()
                        .post("/register")
                        .then()
                        .statusCode(302)


        );

        step("Fill and check registration form (UI)", () -> {
            registrationPage.openPage()
                    .setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setMail(testData.fakeEmail)
                    .setPassword(testData.fakePassword)
                    .setConfirmPassword(testData.fakePassword)
                    .clickRegister()
                    .checkResult();
        });
    }


    @Test
    @DisplayName("User profile editing test")
    void profileEditingTest() {
        step("Fill login form and extract cookie (API)", () -> {
            String cookieValue = given()
                    .filter(withCustomTemplates())
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .log().all()
                    .formParam("Email", Project.config.userLogin())
                    .formParam("Password", Project.config.userPassword())
                    .when()
                    .post("/login")
                    .then()
                    .log().all()
                    .statusCode(302)
                    .extract().cookie("NOPCOMMERCE.AUTH");

            step("Fill cookie and edit profile (UI)", () -> {
                loginPage.openPage();
                Cookie authCookie = new Cookie("NOPCOMMERCE.AUTH", cookieValue);
                getWebDriver().manage().addCookie(authCookie);
                customerInfoPage
                        .openPage()
                        .setFirstName()
                        .setLastName()
                        .clickSaveButton()
                        .checkResult();
            });
        });

    }
}