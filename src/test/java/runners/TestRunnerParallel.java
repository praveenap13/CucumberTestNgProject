package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

    @CucumberOptions(features="src/test/resources/features",
            glue={"stepdefinitions","utility"},
            plugin= {"pretty","html:target/cucumber-reports",
                    "json:target/cucumber.json",
                    "utility.ExtentCucumberAdapter"},
            monochrome = true,
            publish=true
    )

    public class TestRunnerParallel extends AbstractTestNGCucumberTests {

        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios(){
            return super.scenarios();
        }
    }


