package order_ride;


import order_ride.pages.DriverHomePage;
import order_ride.pages.LoginPage;
import order_ride.pages.PassengerHomePage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderRideTest {
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

    @ParameterizedTest
    @DisplayName("Test Order Ride for Passenger")
    @CsvSource({"true", "false"})
    public void OrderRide(String driverLogin) {
        if(driverLogin.equals("true")) {
            LoginPage loginPageDriver = new LoginPage(driverDriver);
            loginPageDriver.waitUntilPageIsLoaded();

            loginPageDriver.insertEmail("driver1@mail.com");
            loginPageDriver.insertPassword("Test2test");

            loginPageDriver.clickOnLogin();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            DriverHomePage driverHome = new DriverHomePage(driverDriver);
            driverHome.waitUntilPageIsLoaded();
        }
        LoginPage loginPagePassenger = new LoginPage(driverPassenger);
        loginPagePassenger.waitUntilPageIsLoaded();

        loginPagePassenger.insertEmail("passenger1@mail.com");
        loginPagePassenger.insertPassword("Test2test");
        loginPagePassenger.clickOnLogin();
        try {
            Thread.sleep(500);
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
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
