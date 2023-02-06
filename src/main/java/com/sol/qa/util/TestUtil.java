package com.sol.qa.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.sol.qa.base.TestBase;

public class TestUtil extends TestBase {

  static final long PAGE_LOAD_TIMEOUT = 40;
  static final long IMPLICIT_WAIT = 40;
  static final int TIME_OUT = 30;
  private static JavascriptExecutor js;
  private static RemoteWebDriver driver;

  public static void runTimeInfo(final String messageType, final String message) throws InterruptedException {
    js = (JavascriptExecutor) driver;
    // Check for jQuery on the page, add it if need be
    js.executeScript("if (!window.jQuery) {"
        + "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
        + "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
        + "document.getElementsByTagName('head')[0].appendChild(jquery);" + "}");
    // Use jQuery to add jquery-growl to the page
    js.executeScript("$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js')");

    // Use jQuery to add jquery-growl styles to the page
    js.executeScript("$('head').append('<link rel=\"stylesheet\" "
        + "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " + "type=\"text/css\" />');");
    // jquery-growl w/ no frills
    js.executeScript("$.growl({ title: 'GET', message: '/' });");
    //'"+color+"'"
    if (messageType.equals("error")) {
      js.executeScript("$.growl.error({ title: 'ERROR', message: '" + message + "' });");
      } else if (messageType.equals("info")) {
      js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
      } else if (messageType.equals("warning")) {
      js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
      } else {
      System.out.println("no error message");
      }
    }

  public static long getPageLoadTimeout() {
    return PAGE_LOAD_TIMEOUT;
    }

  public static long getImplicitWait() {
    return IMPLICIT_WAIT;
    }

  public static int getTimeOut() {
    return TIME_OUT;
    }
  }
