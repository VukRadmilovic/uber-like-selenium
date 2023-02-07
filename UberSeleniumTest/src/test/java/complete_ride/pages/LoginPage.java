package complete_ride.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class LoginPage {

    private final WebDriver driver;
    private final String PAGE_URL="http://localhost:4200/entrance";
    @FindBy(css = "input[name='email']")
    WebElement emailInput;
    @FindBy(css = "input[name='password']")
    WebElement passwordInput;
    @FindBy(id="submit")
    WebElement loginButton;
    List<WebElement> invalidFields;

    @FindBy(className = "mat-mdc-snack-bar-label")
    WebElement credentialsError;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void clickOnLogin() {
        this.loginButton.click();
    }

    public void insertEmail(String email) {
        this.emailInput.sendKeys(email);
    }

    public void clearEmail(){
        this.emailInput.clear();
    }

    public void clearPassword(){
        this.passwordInput.clear();
    }

    public void insertPassword(String password) {
        this.passwordInput.sendKeys(password);
    }

    public boolean isNotExistingUser(){
        try {
            this.credentialsError = driver.findElement(By.className("mat-mdc-snack-bar-label"));
            return true;
        }
        catch(NoSuchElementException ex) {
            return false;
        }
    }

    public String getInvalidFieldName() {
        this.invalidFields = driver.findElements(By.className("mat-form-field-invalid"));
        if(this.invalidFields.size() == 0) return "";
        else if(this.invalidFields.size() == 2) return "both";
        else {
            WebElement invalid = this.invalidFields.get(0);
            WebElement invalidInput = invalid.findElement(By.tagName("input"));
            return invalidInput.getAttribute("name");
        }
    }

    public void waitUntilPageIsLoaded(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.of(5,ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.visibilityOf(loginButton));
    }

    public void waitUntilPopupAppears(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.of(5,ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.visibilityOf(this.credentialsError));
    }

    public String getRedirectedPage() {
        String currentUrl = driver.getCurrentUrl();
        String[] urlParts = currentUrl.split("/");
        return urlParts[urlParts.length - 1];
    }
}
