package com.supplier.events.processor.pqcProcessor;

import java.awt.AWTException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.buyer.events.processor.ITabCommandProcessor;
import com.buyer.events.processor.eventRulesProcessor.EventRulesAsserterCommand;
import com.buyer.events.processor.eventRulesProcessor.EventRulesCreationPage;
import com.buyer.events.processor.monitorSupplierProcessor.MonitorSupplierPage;
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.TestUtil;
import com.sol.qa.util.XlsReader;

public class SupplierPqcProcessorCommand implements ITabCommandProcessor {
  private WindowManager windowManager;
  private SupplierPqcPage supplierPqcPage;
  private MonitorSupplierPage monitorSupplierPage;
  private EventRulesCreationPage eventRulesPage;
  private EventRulesAsserterCommand assertEventRules;
  final int sleepTime = 3000;

  public SupplierPqcProcessorCommand(final WindowManager windowManger) {
    this.windowManager = windowManger;
    supplierPqcPage = new SupplierPqcPage(this.driver());
    monitorSupplierPage = new MonitorSupplierPage(this.driver());
    eventRulesPage = new EventRulesCreationPage(this.driver());
    assertEventRules = new EventRulesAsserterCommand(windowManger);
    }

  private RemoteWebDriver driver() {
    return  this.windowManager.getActiveDriver();
    }

  /**
   * This method will navigate to specified tab.
   */
  public void supplierNavigationLink(final String navigationTab) throws InterruptedException, AWTException {
    Thread.sleep(sleepTime);
    WebElement naviagtionLink = supplierPqcPage.supplierNavigation(navigationTab);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", naviagtionLink);
    WindowManager.clickOn(this.driver(), naviagtionLink, TestUtil.getTimeOut());
    //clickOn(this.driver(), naviagtionLink, TestUtil.getTimeOut());
    }

  /**
   * This method will give response by supplier.
   */
  public void inputResponse(final String inputType, final String indexNum, final String data) throws InterruptedException, AWTException {
    final int timeSleep = 1000;
    Thread.sleep(timeSleep);
    WebElement inputData = supplierPqcPage.supplierInputData(inputType, indexNum);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", inputData);
    inputData.clear();
    System.out.println("WebElement Data>>>" + inputData + "Data>>>>" + data);
    WindowManager.sendkeys(driver(), inputData, TestUtil.getTimeOut(), data);
    }

  /**
   * This method will give upload response by supplier.
   */
  public void inputUploadResponse(final String inputType, final String indexNum, final String data) throws InterruptedException, AWTException {
    WebElement inputData = supplierPqcPage.supplierInputData(inputType, indexNum);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", inputData);
    inputData.sendKeys(data);
    }

  /**
   * This method will give single choice response by supplier.
   */
  public void singleChoiceResponse(final String inputType, final String indexNum) throws InterruptedException, AWTException {
    WebElement singleChoice = supplierPqcPage.singleChoiceData(inputType, indexNum);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", singleChoice);
    WindowManager.clickOn(this.driver(), singleChoice, TestUtil.getTimeOut());
    }

  /**
   * This method will give date response by supplier.
   */
  public void inputDateResponse(final String customValue) throws InterruptedException, AWTException {
    WebElement datePicker = supplierPqcPage.datePickerOption();
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", datePicker);
    WindowManager.clickOn(this.driver(), datePicker, TestUtil.getTimeOut());
    Date customDate = new Date(customValue);
    String selectedDate = calendarPicker(customDate);
    WebElement selectedAuctionDate = eventRulesPage.customDate(selectedDate);
    WindowManager.clickOn(this.driver(), selectedAuctionDate, TestUtil.getTimeOut());
    }

  /**
   * This method click the submit button.
   * @throws InterruptedException
   */
  public void performSubmitResponse() throws InterruptedException {
    Thread.sleep(2);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", supplierPqcPage.getPqcSubmit());
    WindowManager.clickOn(this.driver(), supplierPqcPage.getPqcSubmit(), TestUtil.getTimeOut());
    }

  /**
   * This method enter the given value for calendar picker for custom date.
   */
  public String calendarPicker(final Date customDate) throws InterruptedException {
    System.out.println("Calendar Picker >>>" + customDate);
    SimpleDateFormat formatYear = new SimpleDateFormat("YYYY");
    String selectYear = formatYear.format(customDate).toUpperCase();
    SimpleDateFormat formatMonth = new SimpleDateFormat("MMM");
    System.out.println("formatMonth>>>" + formatMonth);
    String selectMonth = formatMonth.format(customDate).toUpperCase();
    System.out.println("selectMonth>>>" + selectMonth);
    SimpleDateFormat formatDate = new SimpleDateFormat("dd");
    String selectDate = formatDate.format(customDate);
    Thread.sleep(sleepTime);
    WebDriverWait wait = new WebDriverWait(this.driver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    wait.until(ExpectedConditions.visibilityOf(eventRulesPage.getMonthAndYear()));
    String monthYeatVal = eventRulesPage.getMonthAndYear().getText();
    System.out.println("MonthYeatVal>>> " + monthYeatVal);
    String month = monthYeatVal.split(" ")[0].trim();
    System.out.println("month>>>" + month);
    String year = monthYeatVal.split(" ")[1].trim();
    System.out.println("year>>>" + year);
    while (!(month.equals(selectMonth) && year.equals(selectYear))) {
      System.out.println("while Loop >>>" + selectMonth);
      WindowManager.clickOn(this.driver(), eventRulesPage.getNextMonth(), TestUtil.getTimeOut());
      monthYeatVal = eventRulesPage.getMonthAndYear().getText();
      Thread.sleep(2);
      System.out.println("Next month >>>" + eventRulesPage.getNextMonth());
      System.out.println("while loop monthYeatVal" + eventRulesPage.getNextMonth().getText());
      month = monthYeatVal.split(" ")[0].trim();
      year = monthYeatVal.split(" ")[1].trim();
      System.out.println("year while loop>>>" + year);
      }
    return selectDate;
    }

  /**
   *This method have XLs_Reader , command , argument1 , argument2 , argument3 and argument4 as Parameter
   *It have switch command and its corresponding method.
   */
  public void processCommand(final XlsReader reader, final String command, final String argument1, final  String argument2,
      final String argument3, final String argument4)
      throws AWTException, InterruptedException {
    switch (command) {
      case "NavigationMonitorTab":
        supplierNavigationLink(argument1);
        break;
      case "InputResponse":
        inputResponse(argument1, argument2, argument3);
        break;
      case "InputUploadResponse":
        inputUploadResponse(argument1, argument2, argument3);
        break;
      case "SingleChoiceResponse":
        singleChoiceResponse(argument1, argument2);
        break;
      case "InputDateResponse":
        inputDateResponse(argument1);
        break;
      case "submitResponse":
        performSubmitResponse();
        break;
      case "Assertion":
        assertionCases(argument1, argument2);
        break;
      default:
        System.out.println("no match" + command);
      }
    }

  /**
   * This method have assertion cases.
   */
  public void assertionCases(final String argument1, final String argument2) throws InterruptedException {
    switch (argument1) {
      case "ToastMessage":
        assertEventRules.assertToastMsg(argument2);
        break;
      default:
        System.out.println("no switch match");
      }
    }
  }
