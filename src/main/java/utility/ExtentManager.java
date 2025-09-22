package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
        private static ExtentReports extent;

        public static ExtentReports getInstance(){
            if(extent == null){
                ExtentSparkReporter sparkReporter=new ExtentSparkReporter("target/extent-reports/ExtentReport.html");
                sparkReporter.config().setDocumentTitle("Automation Test Report");
                sparkReporter.config().setReportName("Cucumber Test Results");
                extent = new ExtentReports();
                extent.attachReporter(sparkReporter);
            }
            return extent;
        }

}
