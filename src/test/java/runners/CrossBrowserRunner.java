package runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import utility.ConfigReader;

@CucumberOptions(features="src/test/resources/features",
        glue={"stepdefinitions","utility"},
        plugin= {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json",
                "utility.ExtentCucumberAdapter"},
        monochrome = true,
        publish=true
)
public class CrossBrowserRunner extends AbstractTestNGCucumberTests {

        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
            Object[][] scenarios = super.scenarios();

            // Wrap scenarios with browsers
            String[] browsers = {"chrome", "firefox", "edge"};
            Object[][] multiBrowserData = new Object[scenarios.length * browsers.length][2];

            int i = 0;
            for (String browser : browsers) {
                for (Object[] scenario : scenarios) {
                    multiBrowserData[i][0] = scenario[0]; // PickleWrapper
                    multiBrowserData[i][1] = scenario[1]; // FeatureWrapper
                    ConfigReader.setProperty("browser", browser); // pass browser
                    i++;
                }
            }
            return multiBrowserData;
        }

}
