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

public class Hooks {

//private static ExtentReports extent=ExtentManager.getInstance();
//private static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<>();
    @Before
    public void setup(Scenario scenario) {

      String url=  ConfigReader.getProperty("url");
        DriverFactory.initDriver().get(url);
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
