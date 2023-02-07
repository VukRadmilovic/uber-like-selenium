package complete_ride.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DriverHomePage {

    private final WebDriver driver;
    private final String PAGE_URL="http://localhost:4200/driver-home";

    @FindBy(className = "mat-mdc-slide-toggle")
    WebElement activityToggle;

    @FindBy(id = "accept")
    WebElement acceptButton;

    @FindBy(id = "deny")
    WebElement denyButton;

    @FindBy(id = "finish")
    WebElement finishButton;

    @FindBy(id = "logout")
    WebElement logoutButton;

    public DriverHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void acceptRide(){
        while(true){
            try{
                acceptButton.click();
                Thread.sleep(3000);
                if (driver.findElements(By.id("finish")).size() != 0 && finishButton.isDisplayed()) {
                    return;
                }
            }catch (Exception e){
                if (driver.findElements(By.id("finish")).size() != 0 && finishButton.isDisplayed()) {
                    return;
                }
            }
        }
    }

    public void denyRide(){
        denyButton.click();
    }

    public void finishRide(){
        while(true){
            try{
                finishButton.click();
                Thread.sleep(1000);
                if (driver.findElements(By.id("finish")).size() == 0) {
                    break;
                }
            }catch (Exception e){
                if (driver.findElements(By.id("finish")).size() == 0) {
                    break;
                }
            }
        }
    }

    public void logout(){
        logoutButton.click();
    }

    public void waitUntilPageIsLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.visibilityOf(this.activityToggle));
    }
}
