package order_ride.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class PassengerHomePage {

    private final WebDriver driver;
    private final String PAGE_URL="http://localhost:4200/passenger-home";

    @FindBy(id="searchForm")
    WebElement searchRideForm;

    @FindBy(className = "mdc-button__label")
    List<WebElement> buttons;

    @FindBy(id = "map")
    WebElement map_element;

    @FindBy(id="mat-select-value-8")
    WebElement optionVan;

    public void SelectVehicleType(String type){

        WebElement select = driver.findElement(By.id("mat-select-0"));


        select.click();

        WebElement optionStandard = driver.findElement(By.id("mat-option-0"));
        WebElement optionLuxury = driver.findElement(By.id("mat-option-1"));
        WebElement optionVan = driver.findElement(By.id("mat-option-1"));
        if(type.equals("Standard")) optionStandard.click();
        if(type.equals("Luxury")) optionLuxury.click();
        if(type.equals("Van")) optionVan.click();
    }

    public WebElement getMap(){
        return map_element;
    }

    public PassengerHomePage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void ToggleMap(){
        buttons.get(3).click();
    }

    public String GetMapButtonText(){
        return buttons.get(3).getText();
    }

    public void ClickOnOrder(){
        buttons.get(2).click();
    }

    public void waitUntilPageIsLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.visibilityOf(this.searchRideForm));
    }

    public void ClickOnTab2(){
        WebElement tab = driver.findElement(By.id("cdk-step-label-0-1"));
        tab.click();
    }

    public void ClickOnTab3(){
        WebElement tab = driver.findElement(By.id("cdk-step-label-0-2"));
        tab.click();
    }

    public void ClickOnPay(){
        WebElement pay = driver.findElement(By.id("pay-button"));
        pay.click();
    }

    public void ClickOnKidTransportCheckbox(){
        WebElement checkbox = driver.findElement(By.id("mat-mdc-checkbox-1-input"));
        checkbox.click();
    }

    public void ClickOnPetTransportCheckbox(){
        WebElement checkbox = driver.findElement(By.id("mat-mdc-checkbox-2-input"));
        checkbox.click();
    }
}
