package com.w2a.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class CustomListeners implements ITestListener {

    public void onFinish(ITestContext iTestContext) {
        // TODO Auto-generated method stub

    }

    public void onStart(ITestContext iTestContext) {
        // TODO Auto-generated method stub

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        // TODO Auto-generated method stub

    }

    public void onTestFailure(ITestResult iTestResult) {

        System.setProperty("org.uncommons.reportng.escape-output", "false");
        Reporter.log("Capturing screenshot");
        Reporter.log("<a target=\"_blank\" href=\"//Users//kat//repos//DataDriven//error.gif\">Screenshot</a>");


    }

    public void onTestSkipped(ITestResult iTestResult) {
        // TODO Auto-generated method stub

    }

    public void onTestStart(ITestResult iTestResult) {
        // TODO Auto-generated method stub

    }

    public void onTestSuccess(ITestResult iTestResult) {
        // TODO Auto-generated method stub

    }
}
