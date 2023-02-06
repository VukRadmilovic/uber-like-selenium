package login;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Helper {

    public static void takeScreenshot(WebDriver driver, String name) {
        TakesScreenshot ts = (TakesScreenshot) driver;

        File file = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("./Screenshots/" + name + ".png"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
