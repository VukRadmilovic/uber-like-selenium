package complete_ride;

import complete_ride.pages.DriverHomePage;
import complete_ride.pages.LoginPage;
import complete_ride.pages.PassengerHomePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CompleteRideTest {
    public WebDriver driverPassenger;
    public WebDriver driverDriver;

    @BeforeAll
    public void initializeWebDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driverDriver = new ChromeDriver();
        driverDriver.manage().window().maximize();

        driverPassenger = new ChromeDriver();
        driverPassenger.manage().window().maximize();
    }

    @AfterAll
    public void quitDriver() {
        driverPassenger.quit();
        driverDriver.quit();
    }

    @DisplayName("Testing the full ride with driver accepting and completing it")
    @Test
    public void FullRideWithComplete() {
        LoginPage loginPageDriver = new LoginPage(driverDriver);
        loginPageDriver.waitUntilPageIsLoaded();

        loginPageDriver.insertEmail("driver1@mail.com");
        loginPageDriver.insertPassword("Test2test");

        loginPageDriver.clickOnLogin();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        DriverHomePage driverHome = new DriverHomePage(driverDriver);
        driverHome.waitUntilPageIsLoaded();

        LoginPage loginPagePassenger = new LoginPage(driverPassenger);
        loginPagePassenger.waitUntilPageIsLoaded();

        loginPagePassenger.insertEmail("passenger1@mail.com");
        loginPagePassenger.insertPassword("Test2test");
        loginPagePassenger.clickOnLogin();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        PassengerHomePage passengerHome = new PassengerHomePage(driverPassenger);
        passengerHome.waitUntilPageIsLoaded();

        passengerHome.ToggleMap();
        Assertions.assertEquals("Unesi u polja", passengerHome.GetMapButtonText());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Actions actions = new Actions(driverPassenger);
        actions.moveToElement(passengerHome.getMap()).click().perform();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        actions.moveByOffset(-100, -100).click().perform();
        passengerHome.ClickOnOrder();
        passengerHome.SelectVehicleType("Standard");
        passengerHome.ClickOnKidTransportCheckbox();
        passengerHome.ClickOnTab2();
        passengerHome.ClickOnTab3();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        passengerHome.ClickOnPay();

        passengerHome.closeFavoriteRides();
        driverHome.acceptRide();
        driverHome.finishRide();

        Assertions.assertTrue(passengerHome.reviewWindowAppeared());
    }
}
