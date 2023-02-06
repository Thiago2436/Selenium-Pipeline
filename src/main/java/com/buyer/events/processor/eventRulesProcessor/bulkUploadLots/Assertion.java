package com.buyer.events.processor.eventRulesProcessor.bulkUploadLots;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.sol.qa.base.WindowManager;

public class Assertion {
  private WindowManager windowManager;


  public Assertion(final WindowManager windowManagerClass) {
    this.windowManager = windowManagerClass;

    }


  /**
   * This method returns the current active driver.
   */
  public WebDriver getDriver() {
    return this.windowManager.getActiveDriver();
    }



  /**
   * This method asserting the count of lots.
   */
  public void countOfLots(final int expectedLotsCount) throws InterruptedException {
    int totalLots = getDriver().findElements(By.tagName("lot")).size();
    Assert.assertEquals(totalLots, expectedLotsCount);
    }

  /**
   * This method asserting the errors.
   */
  public void verifyErrors(final String errors) throws InterruptedException {
    WebElement errorContainer = getDriver().findElement(By.className("error-container"));
    int errorCount = errorContainer.findElements(By.tagName("li")).size();
    String[] allExpectedErrors = errors.split("\n");
    Assert.assertEquals(errorCount, allExpectedErrors.length);
    String errorContainerText = errorContainer.getText();
    for (int i = 0; i < allExpectedErrors.length; i++) {
      Assert.assertTrue(errorContainerText.contains(allExpectedErrors[i]));
      }
    }

  }
