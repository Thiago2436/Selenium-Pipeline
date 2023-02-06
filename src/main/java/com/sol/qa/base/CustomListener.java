package com.sol.qa.base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener extends TestBase implements ITestListener {

  public void onTestStart(final ITestResult result) {

    }

  public void onTestSuccess(final ITestResult result) {

    }

  /**
   * This method result for failure test cases.
   */
  public void onTestFailure(final ITestResult result) {
    failed(result.getMethod().getMethodName());

    }

  public void onTestSkipped(final ITestResult result) {

    }

  public void onTestFailedButWithinSuccessPercentage(final ITestResult result) {

    }

  public void onTestFailedWithTimeout(final ITestResult result) {

    }

  public void onStart(final ITestContext context) {

    }

  public void onFinish(final ITestContext context) {

    }

  }
