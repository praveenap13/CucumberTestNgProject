package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Parameters;

public class Hooks {

//private static ExtentReports extent=ExtentManager.getInstance();
//private static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<>();
    @Before
    public void setup(Scenario scenario) {
        // Get final browser + url (priority: XML > -D > config > default)
        String browser = ConfigReader.getFinalProperty("browser", "chrome");
        String url = ConfigReader.getFinalProperty("url", "https://default-url.com");


        DriverFactory.initDriver();
        DriverFactory.getDriver().get(url);

        // Log details in console + scenario logs
        System.out.println("🔹 Starting Scenario: " + scenario.getName());
        System.out.println("   ➡ Browser: " + browser);
        System.out.println("   ➡ URL: " + url);

        scenario.log("🖥 Browser: **" + browser + "**");
        scenario.log("🌐 URL: " + url);

       //  1. Try to fetch browser from TestNG parameter
//       String browser=null;
//        try {
//            browser = Reporter.getCurrentTestResult()
//                    .getTestContext()
//                    .getCurrentXmlTest()
//                    .getParameter("browser");
//        } catch (Exception ignored) {
//        }

      // String browser= System.getProperty("browser","chrome");

        //  2. If TestNG parameter not provided, fall back to config.properties
//        if (browser == null || browser.isEmpty()) {
//            browser = ConfigReader.getProperty("browser");
//        }



        // create test in Extent report
  //      ExtentTest test=extent.createTest(scenario.getName());
    //    extentTest.set(test);
    }

//    @AfterStep
//    public void afterStep(Scenario scenario){
//
//        String stepName = scenario.getSourceTagNames().toString();
//        if(scenario.isFailed()){
//            String base64Screenshot =  ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BASE64);
//            extentTest.get().fail("Step Failed", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
//        }else{
//            String base64Screenshot =  ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BASE64);
//
//            extentTest.get().pass(stepName,MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
//        }
//    }

    @After
    public void tearDown(Scenario scenario) {
//        if (scenario.isFailed()) {
//            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
//                    .getScreenshotAs(OutputType.BYTES);
//            scenario.attach(screenshot, "image/png", "Failed Step Screenshot");
//        }
        DriverFactory.quitDriver();
    }
//        if (scenario.isFailed()) {
//            extentTest.get().fail("Scenario failed: " + scenario.getName());
//        } else {
//            extentTest.get().pass("Scenario passed: " + scenario.getName());
//        }
//        DriverFactory.quitDriver();
//        extent.flush();
//    }
//
//    public static ExtentTest getTest() {
//        return extentTest.get();
//    }

}
