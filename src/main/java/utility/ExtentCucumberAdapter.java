package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ExtentCucumberAdapter implements ConcurrentEventListener {
        private static ExtentReports extent = ExtentManager.getInstance();
        private static ThreadLocal<ExtentTest> scenarioTest = new ThreadLocal<>();

        @Override
        public void setEventPublisher(EventPublisher publisher) {
            publisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
            publisher.registerHandlerFor(TestStepStarted.class, this::onStepStarted);
            publisher.registerHandlerFor(TestStepFinished.class, this::onStepFinished);
            publisher.registerHandlerFor(TestCaseFinished.class, this::onTestCaseFinished);
        }

        private void onTestCaseStarted(TestCaseStarted event) {
            ExtentTest test = extent.createTest(event.getTestCase().getName());
            scenarioTest.set(test);
        }

        private void onStepStarted(TestStepStarted event) {
            if (event.getTestStep() instanceof PickleStepTestStep) {
                PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
                scenarioTest.get().info("➡ " + step.getStep().getKeyword() + step.getStep().getText());
            }
        }

        private void onStepFinished(TestStepFinished event) {
            if (event.getTestStep() instanceof PickleStepTestStep) {
                String screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                        .getScreenshotAs(OutputType.BASE64);

                if (event.getResult().getStatus() == Status.PASSED) {
                    scenarioTest.get().pass("✅ Step passed",
                            MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
                } else if (event.getResult().getStatus() == Status.FAILED) {
                    scenarioTest.get().fail("❌ Step failed: " + event.getResult().getError().getMessage(),
                            MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
                }
            }
        }

        private void onTestCaseFinished(TestCaseFinished event) {
            if (event.getResult().getStatus() == Status.PASSED) {
                scenarioTest.get().pass("🎉 Scenario passed");
            }
            extent.flush();
        }
}
