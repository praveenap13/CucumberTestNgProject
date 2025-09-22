package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utility.ConfigReader;

@CucumberOptions(features="src/test/resources/features",
        glue={"stepdefinitions","utility"},
        plugin= {"pretty","html:target/cucumber-reports","json:target/cucumber.json","utility.ExtentCucumberAdapter"},
        monochrome = true,
        publish=true
)

public class TestRunner extends AbstractTestNGCucumberTests {
    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void defineBrowser(@Optional("chrome") String browser) {
        ConfigReader.setProperty("browser", browser); // store in config
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios(){
      return super.scenarios();
    }
}
/*
mvn clean test -Dbrowser=edge
mvn clean test -Dbrowser=chrome -Durl=https://www.automationexercise.com
 */