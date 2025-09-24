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
        //Priority order:
        // 1. Maven Surefire (-Dbrowser=edge -Durl=https://…)
        // 2. TestNG parameter (set into System.setProperty in TestRunner)
        // 3. config.properties (fallback)

        String browser = System.getProperty("browser",
                ConfigReader.getProperty("browser", "chrome"));
        String url = System.getProperty("url",
                ConfigReader.getProperty("url", "https://google.com"));

        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "chrome":
            default:
                driver = new ChromeDriver();
                break;
        }

        tlDriver.set(driver);
        getDriver().manage().window().maximize();
      int waitTime= Integer.parseInt(ConfigReader.getProperty("implicitWait"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
        return getDriver();
    }

    public static void quitDriver(){
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }

}
