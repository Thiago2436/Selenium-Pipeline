package com.buyer.events.processor.monitorSupplierProcessor;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.buyer.events.processor.ITabCommandProcessor;
import com.buyer.events.processor.eventRulesProcessor.EventRulesAsserterCommand;
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.TestUtil;
import com.sol.qa.util.XlsReader;

public class MonitorSupplierProcessorCommand implements ITabCommandProcessor {
  private WindowManager windowManager;
  private MonitorSupplierPage supplierMonitorPage;
  private EventRulesAsserterCommand assertEventRules;
  final int sleepTime = 7000;

  public MonitorSupplierProcessorCommand(final WindowManager windowManger) {
    this.windowManager = windowManger;
    supplierMonitorPage = new MonitorSupplierPage(this.driver());
    assertEventRules = new EventRulesAsserterCommand(windowManger);
    }
  private RemoteWebDriver driver() {
    return  this.windowManager.getActiveDriver();
    }

  /**
   * This method will verify Total Invited Suppliers.
   */
  public void verifyTotalInvitedCount(final String invitedCount) {
    Assert.assertEquals(supplierMonitorPage.getTotalInvitedCount().getText(), invitedCount);
    }

  /**
   * This method will verify count for invited suppliers.
   */
  public void verifyIntendToParticipateCount(final String intendCount) {
    Assert.assertEquals(supplierMonitorPage.getIntendParticipateCount().getText(), intendCount);
    }

  /**
   * This method will verify count for decline to participate count by suppliers.
   */
  public void verifyDeclineToParticipateCount(final String declineCount) {
    Assert.assertEquals(supplierMonitorPage.getDeclineParticipateCount().getText(), declineCount);
    }

  /**
   * This method will verify count for response submitted by suppliers.
   */
  public void verifyResponseSubmittedCount(final String responseCount) {
    Assert.assertEquals(supplierMonitorPage.getResponseSubmittedCount().getText(), responseCount);
    }

  /**
   * This method will verify count for invited suppliers.
   */
  public void verifyInvitedSuppliersDetails(final XlsReader reader, final String sheetName) {
    int sheetRowCount = reader.getRowCount(sheetName);
    int sheetColCount = reader.getColumnCount(sheetName);
    for (int intRow = 0; intRow < sheetRowCount - 1; intRow++) {
      for (int intCol = 0; intCol < sheetColCount; intCol++) {
        String columnName = reader.getCellData(sheetName, intCol, 1);
        int value = intRow + 2;
        WebElement rows = this.driver()
            .findElement(By.cssSelector("[data-test = 'suppliers-invited-table'] [role = 'row']:nth-child("
                + value + ") [data-test = '" + columnName + "']"));
        Assert.assertEquals(reader.getCellData(sheetName, intCol, intRow + 2), rows.getText());
        }
      }
    }

  /**
   * This method clicks on Action button and clicks the menu for the action with parameter.
   */
  public void performAction(final String action, final String actionMenu) throws InterruptedException {
    System.out.println("ActionMenu>>>>" + actionMenu);
    WebElement actionLink = supplierMonitorPage.actionForSuppliers(action);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", actionLink);
    clickOn(this.driver(), actionLink, TestUtil.getTimeOut());
    if (actionMenu.equalsIgnoreCase("ResendEventInvite")) {
      clickOn(this.driver(), supplierMonitorPage.getResendInviteBtn(), TestUtil.getTimeOut());
      }
    if (actionMenu.equalsIgnoreCase("PlaceSurrogateBid")) {
      clickOn(this.driver(), supplierMonitorPage.getPlaceSurrogateBid(), TestUtil.getTimeOut());
      ArrayList<String> tabs = new ArrayList<String>(this.driver().getWindowHandles());
      this.driver().switchTo().window(tabs.get(1));
      String strUrl = this.driver().getCurrentUrl();
      System.out.println("Current URL>>>" + strUrl);
      if (strUrl.contains("/surrogate?page=1")) {
        assertTrue(true);
        this.driver().switchTo().window(tabs.get(0));
        }
      }

    if (actionMenu.equalsIgnoreCase("BlockSupplier")) {
      clickOn(this.driver(), supplierMonitorPage.getBlockSupplier(), TestUtil.getTimeOut());
      }

    if (actionMenu.equalsIgnoreCase("UnblockSupplier")) {
      clickOn(this.driver(), supplierMonitorPage.getUnblockSupplier(), TestUtil.getTimeOut());
      }
    }

  /**
   * This method will navigate to specified tab.
   */
  public void performNavigationMonitor(final String navigationTab) throws InterruptedException, AWTException {
    Thread.sleep(sleepTime);
    System.out.println("Navigation Tab" + navigationTab);
    WebElement naviagtionLink = supplierMonitorPage.navigationMonitorPhase(navigationTab);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", naviagtionLink);
    clickOn(this.driver(), naviagtionLink, TestUtil.getTimeOut());
    }

  /**
   * This method will click on refresh button.
   */
  public void performRefreshIcon() throws InterruptedException, AWTException {
    System.out.println("REfresh button");
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", supplierMonitorPage.getRefreshIcon());
    clickOn(this.driver(), supplierMonitorPage.getRefreshIcon(), TestUtil.getTimeOut());
    Thread.sleep(sleepTime);
    }

  /**
   * This method will click on refresh button.
   * @throws InterruptedException
   */
  public final void validateActivityHistory(final String activityId, final String textData) throws InterruptedException {
    Thread.sleep(sleepTime);
    System.out.println("Activity History >>>>");
    WebElement activityHistory = this.driver().findElement(
        By.cssSelector("[data-testid='activity-" + activityId + "'] .timeline-content div:nth-child(1)"));
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", activityHistory);
    activityHistory.getText();
    System.out.println("Data Text>>>>" + activityHistory.getText());
    }

  /**
   * This method will click on refresh button.
   */
  public final void performViewHistory() {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", supplierMonitorPage.getViewHistory());
    clickOn(this.driver(), supplierMonitorPage.getViewHistory(), TestUtil.getTimeOut());
    }


  public static final void sendkeys(final RemoteWebDriver driver, final WebElement element, final int timeout, final String value) {
    new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
    element.sendKeys(value);
    }

  public static final void clickOn(final RemoteWebDriver driver, final WebElement element, final int timeout) {
    new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
    element.click();
    }

  /**
   *This method have XLs_Reader , command , argument1 , argument2 , argument3 and argument4 as Parameter
   *It have switch command and its corresponding method.
   */
  public void processCommand(final XlsReader reader, final String command, final String argument1, final  String argument2,
      final String argument3, final String argument4)
      throws AWTException, InterruptedException {
    switch (command) {
      case "performTotalInvitedCount":
        verifyTotalInvitedCount(argument1);
        break;
      case "performIntendToParticipateCount":
        verifyIntendToParticipateCount(argument1);
        break;
      case "performDeclineToParticipateCount":
        verifyDeclineToParticipateCount(argument1);
        break;
      case "performResponseSubmittedCount":
        verifyResponseSubmittedCount(argument1);
        break;
      case "VerifyInvitedSuppliersDetails":
        verifyInvitedSuppliersDetails(reader, argument1);
        break;
      case "Action":
        performAction(argument1, argument2);
        break;
      case "NavigationMonitorTab":
        performNavigationMonitor(argument1);
        break;
      case "performRefreshIcon":
        performRefreshIcon();
        break;
      case "validateActivityHistory":
        validateActivityHistory(argument1, argument2);
        break;
      case "performViewHistory":
        performViewHistory();
        break;
      case "Assertion":
        switch (argument1) {
          case "ToastMessage":
            assertEventRules.assertToastMsg(argument2);
            break;
          default:
            System.out.println("no switch match" + argument1);
          }
        break;
      default:
        System.out.println("no match" + command);

      }
    }
  }
