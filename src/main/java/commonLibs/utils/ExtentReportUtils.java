package commonLibs.utils;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtils {

	ExtentReports extentReports;

	ExtentSparkReporter extentHTMLReporter;

	ExtentTest extentTest;

	public ExtentReportUtils(String htmlReportFileName) {
		extentHTMLReporter = new ExtentSparkReporter(htmlReportFileName);

		extentReports = new ExtentReports();

		extentReports.attachReporter(extentHTMLReporter);
	}

	public void createTest(String testCaseName) {
		extentTest = extentReports.createTest(testCaseName);

	}

	public void addLog(Status status, String comment) {
		extentTest.log(status, comment);
	}

	public void addScreenshotToReport(String screenshotFileName) throws IOException {
		extentTest.addScreenCaptureFromPath(screenshotFileName);
	}

	public void closeRepor() {
		extentReports.flush();
	}

}
