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

        WebElement select = driver.findElement(By.id("mat-select-4"));


        select.click();

        WebElement optionStandard = driver.findElement(By.id("mat-select-value-6"));
        WebElement optionLuxury = driver.findElement(By.id("mat-select-value-7"));
        WebElement optionVan = driver.findElement(By.id("mat-option-11"));
        if(type == "Standart") optionStandard.click();
        if(type == "Luxury") optionLuxury.click();
        if(type == "Van") optionVan.click();
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

    public void ClickOnMap(){
        map_element.click();
    }
}
