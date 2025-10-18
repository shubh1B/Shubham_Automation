package com.shubham.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyTestListener implements ITestListener {

    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    static {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/extentReport.html");
        htmlReporter.config().setDocumentTitle("Automation Test Report");
        htmlReporter.config().setReportName("Functional Test Results");
        htmlReporter.config().setTheme(Theme.STANDARD);

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    public MyTestListener() {
        System.out.println(">>> MyTestListener initialized <<<");
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println(">>> TestNG Execution Started: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();
        ExtentTest test = extentReports.createTest(className + " :: " + methodName);
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = extentTest.get();
        if (test != null) {
            test.log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = extentTest.get();
        if (test != null) {
            test.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
            test.fail(result.getThrowable());

            // Optional: add screenshot
            // Example: test.addScreenCaptureFromPath("path_to_screenshot.png");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = extentTest.get();
        if (test != null) {
            test.log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println(">>> TestNG Execution Finished: " + context.getName());
        if (extentReports != null) {
            extentReports.flush();
            System.out.println(">>> Extent Report Flushed <<<");
        }
        extentTest.remove();
    }
}
