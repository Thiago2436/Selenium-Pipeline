package com.sol.qa.base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

  private int counter = 0;
  final int retryLimit = 3;


  /**
   * This method retry for the failed testcases.
   */
  public boolean retry(final ITestResult result) {
    if (counter < retryLimit) {
      counter++;
      return true;
      }
    return false;
    }

  }
