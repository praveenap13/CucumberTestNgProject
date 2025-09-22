package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {

    //ThreadLocal driver
    private static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<>();

    //get driver
    public static WebDriver getDriver(){
       return tlDriver.get();
    }
    //initialize driver
    public static WebDriver initDriver(){
        WebDriver driver =null;
      String browser= ConfigReader.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        tlDriver.set(driver);
        getDriver().manage().window().maximize();
      int waitTime= Integer.parseInt(ConfigReader.getProperty("implicitWait"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
        return getDriver();
    }

    public static void quitDriver(){
        getDriver().quit();
        tlDriver.remove();
    }

}
