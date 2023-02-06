package com.supplier.events.processor.auctionConsole;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.buyer.events.processor.ITabCommandProcessor;
import com.buyer.events.processor.eventRulesProcessor.EventRulesAsserterCommand;
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.TestUtil;
import com.sol.qa.util.XlsReader;

public class SupplierAuctionConsoleCommand implements ITabCommandProcessor {
  private WindowManager windowManager;
  final int sleepTime = 500;
  private SupplierAuctionConsolePage auctionConsolePage;
  private EventRulesAsserterCommand assertEventRules;

  public SupplierAuctionConsoleCommand(final WindowManager windowManger) {
    this.windowManager = windowManger;
    auctionConsolePage = new SupplierAuctionConsolePage(this.driver());
    assertEventRules = new EventRulesAsserterCommand(windowManger);
    }

  private RemoteWebDriver driver() {
    return  this.windowManager.getActiveDriver();
    }

  /**
   * This method will navigate to specified tab.
   */
  public void performSupplierNavigation(final String navigationTab) throws InterruptedException, AWTException {
    Thread.sleep(sleepTime);
    System.out.println("Navigation Tab");
    WebElement naviagtionLink = auctionConsolePage.navigationToTab(navigationTab);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", naviagtionLink);
    WindowManager.clickOn(this.driver(), naviagtionLink, TestUtil.getTimeOut());
    }

  /**
   * This method will click on submit button.
   */
  public void performLotSubmit(final String navigationTab) throws InterruptedException, AWTException {
    Thread.sleep(sleepTime);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();",  auctionConsolePage.getLotSubmitButton());
    WindowManager.clickOn(this.driver(), auctionConsolePage.getLotSubmitButton(), TestUtil.getTimeOut());
    }

  /**
   * This method will click on the Particular Lot Radio Button.
   */
  public void performGoToLotNumber(final String lotNo) throws InterruptedException, AWTException {
    WebElement lotNoCheckBox = auctionConsolePage.performLotNumber(lotNo);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", lotNoCheckBox);
    WindowManager.clickOn(this.driver(), lotNoCheckBox, TestUtil.getTimeOut());
    Thread.sleep(sleepTime);
    }

  /**
   * This method will validate the lot value.
   */
  public void validateLotValue(final String lotNo, final String lotValue) throws InterruptedException, AWTException {
    WebElement lotValueWebElement = auctionConsolePage.lotValueElement(lotNo);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", lotValueWebElement);
    Assert.assertEquals(lotValue, lotValueWebElement.getText());
    }

  /**
   * This method will enter the extended price.
   */
  public void enterExtendedPrice(final String price) throws InterruptedException, AWTException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();",  auctionConsolePage.getExtendedPrice());
    auctionConsolePage.getExtendedPrice().clear();
    WindowManager.sendkeys(this.driver(), auctionConsolePage.getExtendedPrice(), TestUtil.getTimeOut(), price);
    Thread.sleep(sleepTime);
    }


  /**
   * This method enter the lineItems Price for that particular lot id and sheet name.
   */
  public void enterPriceBidAction1(final XlsReader reader, final String lotId, final String sheetName) throws InterruptedException {
    Thread.sleep(sleepTime);
    int sheetRowCount = reader.getRowCount(sheetName);
    int sheetColCount = reader.getColumnCount(sheetName);
    for (int intRow = 0; intRow < sheetRowCount - 1; intRow++) {
      for (int intCol = 0; intCol < sheetColCount; intCol++) {
        String columnName = reader.getCellData(sheetName, intCol, 1);
        int rowData = intRow + 1;
        WebElement priceData = this.driver().findElement(
            By.cssSelector("[data-test = " + columnName + "-" + rowData + "]"));
        priceData.clear();
        WindowManager.sendkeys(driver(), priceData, TestUtil.getTimeOut(), reader.getCellData(sheetName, intCol, intRow + 2));

        }

      }
    }

  /**
   *This method have XLs_Reader , command , argument1 , argument2 , argument3 and argument4 as Parameter
   *It have switch command and its corresponding method.
   */
  public void processCommand(final XlsReader reader, final String command, final String argument1, final  String argument2,
      final String argument3, final String argument4)
      throws AWTException, InterruptedException {
    switch (command) {
      case "NavigationTab":
        performSupplierNavigation(argument1);
        break;
      case "EnterPriceBidAction1":
        enterPriceBidAction1(reader, argument1, argument2);
        break;
      case "PerformLotSubmit":
        performLotSubmit(argument1);
        break;
      case "GoToLotNumber":
        performGoToLotNumber(argument1);
        break;
      case "ValidateLotValue":
        validateLotValue(argument1, argument2);
        break;
      case "ExtendedPrice":
        enterExtendedPrice(argument1);
        break;
      case "Assertion":
        switch (argument1) {
          case "ToastMessage":
            assertEventRules.assertToastMsg(argument2);
            break;
          default:
            System.out.println("no switch match");
          }
        break;
      default:
        System.out.println("no match" + command);
      }
    }
  }
