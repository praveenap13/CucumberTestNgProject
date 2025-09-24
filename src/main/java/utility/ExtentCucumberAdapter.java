package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.List;

public class ExtentCucumberAdapter implements ConcurrentEventListener {
        private static ExtentReports extentReports = ExtentManager.getInstance();
        private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

        @Override
        public void setEventPublisher(EventPublisher publisher) {
            publisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
            publisher.registerHandlerFor(TestStepStarted.class, this::onStepStarted);
            publisher.registerHandlerFor(TestStepFinished.class, this::onStepFinished);
            publisher.registerHandlerFor(TestCaseFinished.class, this::onTestCaseFinished);
        }

        private void onTestCaseStarted(TestCaseStarted event) {
            ExtentTest test = extentReports.createTest(event.getTestCase().getName());
            extentTest.set(test);
            // Capture browser + url info
            String browser = ConfigReader.getFinalProperty("browser", "chrome");
            String url = ConfigReader.getFinalProperty("url", "https://default-url.com");

            test.info("Browser: <b>" + browser + "</b>");
            test.info("URL: <a href='" + url + "' target='_blank'>" + url + "</a>");
        }

        private void onStepStarted(TestStepStarted event) {
            if (event.getTestStep() instanceof PickleStepTestStep) {
                PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
                extentTest.get().info("➡ " + step.getStep().getKeyword() + step.getStep().getText());
            }
        }

        private void onStepFinished(TestStepFinished event) {
            if (event.getTestStep() instanceof PickleStepTestStep) {
                if (DriverFactory.getDriver() != null) {
                    String screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BASE64);

                    if (event.getResult().getStatus() == Status.PASSED) {
                        extentTest.get().pass("Step passed",
                                MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
                    } else if (event.getResult().getStatus() == Status.FAILED) {
                        extentTest.get().fail("Step failed: " + event.getResult().getError().getMessage(),
                                MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
                    }
                }else {
                    extentTest.get().warning("WebDriver was null, no screenshot captured.");
                }
            }
        }

        private void onTestCaseFinished(TestCaseFinished event) {
            if (event.getResult().getStatus() == Status.PASSED) {
                extentTest.get().pass("🎉 Scenario passed");
            }
            extentReports.flush();
        }

    public enum STATUS {
        INFO, PASS, FAIL
    }

    // single log method using enum
    public static void log(STATUS type, String message) {
        switch (type) {
            case INFO:
                extentTest.get().info(message);
                break;
            case PASS:
                extentTest.get().pass(message);
                break;
            case FAIL:
                extentTest.get().fail(message);
                break;
            default:
                extentTest.get().info(message); // fallback
        }
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    //helpers
    private String convertToHtmlTable(List<List<String>> cells) {
        StringBuilder table = new StringBuilder();
        table.append("<table border='1' style='border-collapse:collapse;'>");

        // Header row
        table.append("<tr>");
        for (String header : cells.get(0)) {
            table.append("<th>").append(header).append("</th>");
        }
        table.append("</tr>");

        // Data rows
        for (int i = 1; i < cells.size(); i++) {
            table.append("<tr>");
            for (String cell : cells.get(i)) {
                table.append("<td>").append(cell).append("</td>");
            }
            table.append("</tr>");
        }

        table.append("</table>");
        return table.toString();
    }
}
