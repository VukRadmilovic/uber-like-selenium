package login;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import login.pages.AdminHomePage;
import login.pages.DriverHomePage;
import login.pages.LoginPage;
import login.pages.PassengerHomePage;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest {

    public  WebDriver driver;

    @BeforeAll
    public void initializeWebDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    public void quitDriver() {
        driver.quit();
    }

    @ParameterizedTest
    @DisplayName("Test login - all possible combinations, check for correct redirection")
    @CsvSource({"passenger1@mail.com,Test2test,passenger","driver1@mail.com,Test2test,driver","admin1@mail.com,Test2test,admin"})
    public void login(String userEmail,String userPassword, String userRole) {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitUntilPageIsLoaded();

        loginPage.insertEmail("");
        loginPage.insertPassword("");
        loginPage.clickOnLogin();
        Assertions.assertEquals("both",loginPage.getInvalidFieldName());
        Helper.takeScreenshot(driver, "login_both_fields_incorrect");

        loginPage.clearEmail();
        loginPage.clearPassword();
        loginPage.insertEmail("wrongFormat.com");
        loginPage.insertPassword(userPassword);
        loginPage.clickOnLogin();
        Assertions.assertEquals("email",loginPage.getInvalidFieldName());
        Helper.takeScreenshot(driver, "login_invalid_email_field");

        loginPage.clearEmail();
        loginPage.insertEmail("nonExistingMail@mail.com");
        loginPage.clickOnLogin();
        Assertions.assertEquals("",loginPage.getInvalidFieldName());
        loginPage.waitUntilPopupAppears();
        Assertions.assertTrue(loginPage.isNotExistingUser());
        Helper.takeScreenshot(driver, "login_all_fields_valid_but_email_nonexisting");

        loginPage.clearEmail();
        loginPage.insertEmail(userEmail);
        loginPage.clearPassword();
        loginPage.insertPassword("invalidPassword");
        loginPage.clickOnLogin();
        Assertions.assertEquals("",loginPage.getInvalidFieldName());
        loginPage.waitUntilPopupAppears();
        Assertions.assertTrue(loginPage.isNotExistingUser());
        Helper.takeScreenshot(driver, "login_all_fields_valid_but_password_wrong");


        loginPage.clearEmail();
        loginPage.insertEmail(userEmail);
        loginPage.clearPassword();
        loginPage.insertPassword(userPassword);
        loginPage.clickOnLogin();
        Assertions.assertEquals("",loginPage.getInvalidFieldName());
        if(userRole.equals("passenger")) {
            PassengerHomePage passengerHome = new PassengerHomePage(driver);
            passengerHome.waitUntilPageIsLoaded();
            Assertions.assertEquals("passenger-home", loginPage.getRedirectedPage());
            Helper.takeScreenshot(driver, "passenger_home_redirect");
        }

        if(userRole.equals("driver")) {
            DriverHomePage driverHome = new DriverHomePage(driver);
            driverHome.waitUntilPageIsLoaded();
            Assertions.assertEquals("driver-home", loginPage.getRedirectedPage());
            Helper.takeScreenshot(driver, "driver_home_redirect");
        }

        if(userRole.equals("admin")) {
            AdminHomePage adminHome = new AdminHomePage(driver);
            adminHome.waitUntilPageIsLoaded();
            Assertions.assertEquals("admin-home", loginPage.getRedirectedPage());
            Helper.takeScreenshot(driver, "admin_home_redirect");
        }

    }
}
