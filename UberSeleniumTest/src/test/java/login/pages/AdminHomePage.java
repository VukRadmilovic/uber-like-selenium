package login.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class AdminHomePage {

    private final WebDriver driver;
    private final String PAGE_URL="http://localhost:4200/admin-home";

    @FindBy(xpath = "//span[contains(text(),'Kreiraj Vozača')]")
    WebElement createDriverNavItem;

    public AdminHomePage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void waitUntilPageIsLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.visibilityOf(this.createDriverNavItem));
    }
}
