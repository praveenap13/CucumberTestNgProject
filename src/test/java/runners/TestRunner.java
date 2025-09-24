package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;
import utility.ConfigReader;
import utility.BrowserFactory;

@CucumberOptions(features="src/test/resources/features",
        glue={"stepdefinitions","utility"},
        plugin= {"pretty","html:target/cucumber-reports","json:target/cucumber.json","utility.ExtentCucumberAdapter"},
        monochrome = true,
        publish=true
)

public class TestRunner extends AbstractTestNGCucumberTests {
   // @BeforeClass(alwaysRun = true)
    @BeforeTest(alwaysRun = true)
    @Parameters("browser")
    public void defineBrowser(@Optional("chrome") String browserFromXml) {

            // Priority: XML > System Property > config.properties > default "chrome"
            String browser = !browserFromXml.isEmpty()
                    ? browserFromXml
                    : ConfigReader.getFinalProperty("browser", "chrome");

            System.setProperty("browser", browser); // propagate for Hooks/DriverFactory
            System.out.println("Final Browser Selected: " + browser);
            /*
                    if (browser != null && !browser.isEmpty()) {
            System.setProperty("browser", browser);
            BrowserFactory.setBrowser(browser);
            //
            System.out.println("Browser picked from TestNG XML: " + browser);
        } else {
            String configBrowser = ConfigReader.getProperty("browser");
            System.setProperty("browser", configBrowser);
            BrowserFactory.setBrowser(configBrowser);
           //
            System.out.println("Browser picked from config.properties: " + configBrowser);
             }
             */

    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios(){
      return super.scenarios();
    }
}
/*
All working
mvn clean test -Dbrowser=edge
mvn clean test -Dbrowser=chrome -Durl=https://www.automationexercise.com
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml

# Run with testng.xml parallel suite
mvn clean test -DsuiteXmlFile=testng.xml -Dbrowser=edge
 */