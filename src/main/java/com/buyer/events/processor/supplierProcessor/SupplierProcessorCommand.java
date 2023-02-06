package com.buyer.events.processor.supplierProcessor;

import java.awt.AWTException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.buyer.events.processor.ITabCommandProcessor;
import com.buyer.events.processor.pricingSheetProcessor.PricingSheetProcessorCommand;
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.TestUtil;
import com.sol.qa.util.XlsReader;

public class SupplierProcessorCommand  implements ITabCommandProcessor {

  private SupplierCreationPage supplierCreatePage;
  final int sleepTime = 1000;
  private JavascriptExecutor js;
  private final int count3 = 3;
  private final int count4 = 4;
  private WindowManager windowManager;
  private PricingSheetProcessorCommand pricingSheetProcessor;
  public SupplierProcessorCommand(final WindowManager windowManger) {
    this.windowManager = windowManger;
    supplierCreatePage = new SupplierCreationPage(this.driver());

    }

  private RemoteWebDriver driver() {
    return  this.windowManager.getActiveDriver();
    }
  /**
   *This method have XLs_Reader , command , argument1 , argument2 , argument3 and argument4 as Parameter
   *It have switch command and its corresponding method.
   */
  public void processCommand(final XlsReader reader, final String command, final String argument1, final String argument2, final String argument3,
      final String argument4) throws AWTException, InterruptedException {
    //supplierCreatePage = new SupplierCreationPage(this.driver());

    switch (command) {
      case "searchSupplier":
        performSearchSupplier();
        break;
      case "enterSupplierName":
        setSupplierName(argument1);
        break;
      case "advanceSearch":

        performSearchEvent(argument1);
        break;
      case "NavigationTab":
        pricingSheetProcessor = new PricingSheetProcessorCommand(windowManager);
        pricingSheetProcessor.performNavigation(argument1);
        break;
      case "AddContactSupplier":
        performAddContactSupplier();
        break;
      case "AddNewSupplierEvent":
        performAddSupplierEvent(reader, argument1);
        break;

      case "InviteToEvent":
        performInviteEvent();
        break;
      case "AddNewSupplier":
        addNewSupplier();
        break;
      case "RegisteredCompanyName":
        getCompanyName(argument1);
        break;
      case "ContactPersonName":
        setPersonName(argument1);
        break;
      case "Email":
        getContactEmail(argument1);
        break;
      case "MobileNumber":
        setContactMobile(argument1);
        break;
      case "Country":
        setCountryName(argument1);
        break;
      case "State":
        setStateName(argument1);
        break;
      case "PanNo":
        setPanNumber(argument1);
        break;
      case "AddSupplierEvent":
        performConfirmSupplier();
        break;
      case "AddContact":
        performAddContact();
        break;
      case "SelectSupplierToInvite":
        selectSupplierFromList(argument1);
        break;
      case "AddContactDetails":
        addContactDetails(argument1, argument2, argument3);
        break;
      case "removeSupplier":
        removeSupplierFromList(argument1);
        break;
      case "proceedButton":
        performProceed();
        break;
      case "CancelAddContact":
        performCancel();
        break;
      case "YesNoButton":
        pricingSheetProcessor = new PricingSheetProcessorCommand(windowManager);
        pricingSheetProcessor.performYesNoButton(argument1);
        break;
      case "Assertion":
        switch (argument1) {
          case "visibilityOfAddContact":
            visibilityOfAddContact();
            break;

          default:
            System.out.println("no switch match");

          }
        break;
      default:
        System.out.println("no match");

      }
    }


  /**
   * This method clicks Add Supplier Event Button.
   */
  public void performAddSupplierEvent(final XlsReader reader, final String sheetName) throws InterruptedException, AWTException {
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, count3, i);
      String argument4 = reader.getCellData(sheetName, count4, i);
      processCommand(reader, command, argument1, argument2, argument3, argument4);

      }
    }

  /**
   * This method clicks search supplier button.
   */
  public void performSearchSupplier() {
    WebDriverWait wait = new WebDriverWait(this.driver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    wait.until(ExpectedConditions.visibilityOfAllElements(supplierCreatePage.getSearchSupplier()));
    clickOn(this.driver(), supplierCreatePage.getSearchSupplier(), TestUtil.getTimeOut());

    }

  /**
   * This method clicks search button and enter the supplier name.
   */
  public void setSupplierName(final String supName) throws AWTException {
    clickOn(this.driver(), supplierCreatePage.getSearchSupplierName(), TestUtil.getTimeOut());
    WebDriverWait wait = new WebDriverWait(this.driver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    wait.until(ExpectedConditions.elementToBeClickable(supplierCreatePage.getSearchSupplierName()));
    sendkeys(this.driver(), supplierCreatePage.getSearchSupplierName(), TestUtil.getTimeOut(), supName);
    supplierCreatePage.getSearchSupplierName().sendKeys(Keys.ENTER);
    }

  /**
   * This method clicks search button for the given supplier name.
   */
  public void performSearchEvent(final String supName) throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(this.driver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    Thread.sleep(sleepTime);
    wait.until(ExpectedConditions.elementToBeClickable(supplierCreatePage.getSearchViewButton()));
    clickOn(this.driver(), supplierCreatePage.getSearchViewButton(), TestUtil.getTimeOut());
    Thread.sleep(sleepTime);
    if (supplierCreatePage.getSuppliersInvitedTable().getText().equals("Showing search results")) {
      wait.until(ExpectedConditions
          .refreshed(ExpectedConditions.visibilityOfAllElements(supplierCreatePage.getTableList())));
      List<WebElement> rows = supplierCreatePage.getTableList().findElements(By.tagName("mat-row"));

      for (int i = 0; i < rows.size(); i++) {
        List<WebElement> checkBoxElement = rows.get(i).findElements(By.tagName("mat-checkbox"));
        List<WebElement> cols = rows.get(i).findElements(By.tagName("mat-panel-title"));
        for (int j = 0; j < cols.size(); j++) {
          System.out.println("cols" + cols.get(j).getText());
          if (cols.get(j).getText().equals(supName)) {
            System.out.println("if loop");
            checkBoxElement.get(j).click();

            }
          // cols.get(j).click();
          }
        }

      }
    }

  /**
   * This method clicks on Invite To Event Button.
   */
  public void performInviteEvent() {
    if (supplierCreatePage.getInviteToEventButton().isEnabled()) {
      clickOn(this.driver(), supplierCreatePage.getInviteToEventButton(), TestUtil.getTimeOut());

      }

    }

  /**
   * This method clicks on Add New Supplier Button.
   */
  public void addNewSupplier() {
    if (supplierCreatePage.getAddNewSupplierButton().isEnabled()) {
      clickOn(this.driver(), supplierCreatePage.getAddNewSupplierButton(), TestUtil.getTimeOut());
      }

    }

  /**
  * This method clicks on proceed button.
  */
  public void performProceed() {
    boolean proceed = supplierCreatePage.getProceedButton().isEnabled();
    AssertJUnit.assertTrue(proceed);
    clickOn(this.driver(), supplierCreatePage.getProceedButton(), TestUtil.getTimeOut());
    }

  /**
   * This method enters the company  name in the company field.
   */
  public void getCompanyName(final String companyName) throws AWTException {
    WebDriverWait wait = new WebDriverWait(this.driver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    wait.until(ExpectedConditions.visibilityOfAllElements(supplierCreatePage.getAddSupplierForm()));
    if (supplierCreatePage.getAddSupplierForm().isDisplayed()) {
      sendkeys(this.driver(), supplierCreatePage.getCompanyName(), TestUtil.getTimeOut(), companyName);

      }

    }

  /**
   * This method enters the person name in the name field.
   */
  public void setPersonName(final String name) throws AWTException {
    if (supplierCreatePage.getAddSupplierForm().isDisplayed()) {
      sendkeys(this.driver(), supplierCreatePage.getContactPersonName(), TestUtil.getTimeOut(), name);
      }

    }

  /**
   * This method enters the email in the email field.
   */
  public void getContactEmail(final String email) throws AWTException {
    if (supplierCreatePage.getAddSupplierForm().isDisplayed()) {
      sendkeys(this.driver(), supplierCreatePage.getContactEmail(), TestUtil.getTimeOut(), email);
      }

    }

  /**
   * This method enters the mobile in the mobile field.
   */
  public void setContactMobile(final String mobile) throws AWTException {
    if (supplierCreatePage.getAddSupplierForm().isDisplayed()) {
      sendkeys(this.driver(), supplierCreatePage.getTelephoneNumber(), TestUtil.getTimeOut(), mobile);
      }

    }

  /**
   * This method enters the country name in the country field.
   */
  public void setCountryName(final String countryName) throws AWTException {
    if (supplierCreatePage.getAddSupplierForm().isDisplayed()) {
      clickOn(this.driver(), supplierCreatePage.getCountry(), TestUtil.getTimeOut());
      sendkeys(this.driver(), supplierCreatePage.getCountry(), TestUtil.getTimeOut(), countryName);
      supplierCreatePage.countryOption(countryName);

      }

    }


  /**
   * This method enters the state name in the state field.
   */
  public void setStateName(final String state) throws AWTException {
    if (supplierCreatePage.getAddSupplierForm().isDisplayed()) {
      sendkeys(this.driver(), supplierCreatePage.getState(), TestUtil.getTimeOut(), state);
      supplierCreatePage.stateOption(state);
      }

    }

  /**
   * This method enters the Pan No if that field is displayed.
   */
  public void setPanNumber(final String panNum) throws AWTException {
    if (supplierCreatePage.getAddSupplierForm().isDisplayed()) {
      sendkeys(this.driver(), supplierCreatePage.getPanNo(), TestUtil.getTimeOut(), panNum);
      }

    }

  /**
   * This method clicks on the Confirm Supplier button.
   */
  public void performConfirmSupplier() throws AWTException, InterruptedException {
    Thread.sleep(sleepTime);
    if (supplierCreatePage.getConfirmAddSupplierButton().isEnabled()) {
      clickOn(this.driver(), supplierCreatePage.getConfirmAddSupplierButton(), TestUtil.getTimeOut());

      }

    }
  /**
   * This method selects the supplier name from the list.
   */
  public void selectSupplierFromList(final String supName) throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(this.driver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    wait.until(
        ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(supplierCreatePage.getTableList())));
    List<WebElement> rows = supplierCreatePage.getTableList().findElements(By.tagName("mat-row"));
    for (int i = 0; i < rows.size(); i++) {
      List<WebElement> checkBoxElement = rows.get(i).findElements(By.tagName("mat-checkbox"));
      List<WebElement> cols = rows.get(i).findElements(By.tagName("mat-panel-title"));
      for (int j = 0; j < cols.size(); j++) {
        if (cols.get(j).getText().equals(supName)) {
          js.executeScript("arguments[0].scrollIntoView();", checkBoxElement.get(j));
          checkBoxElement.get(j).click();
          }
        // cols.get(j).click();
        }
      }

    }

  /**
   * This method removes the supplier name from the list.
   */
  public void removeSupplierFromList(final String supplierNo) throws InterruptedException {
    Thread.sleep(sleepTime);
    /*
     * WebElement tableId = this.driver().findElement(By.className("cdk-table"));
     * WebDriverWait wait = new WebDriverWait(this.driver(),
     * Duration.ofSeconds(TestUtil.getTimeOut()));
     * wait.until(ExpectedConditions.refreshed(ExpectedConditions.
     * visibilityOfAllElements(tableId))); List<WebElement> rows =
     * tableId.findElements(By.tagName("mat-row")); for (int i = 0; i < rows.size();
     * i++) { List<WebElement> supplierName =
     * rows.get(i).findElements(By.xpath("//*[@data-column = 'supplierName']"));
     * List<WebElement> removeSupplier = rows.get(i)
     * .findElements(By.xpath("//*[@data-column = 'removeSupplier']")); for (int j =
     * 0; j < supplierName.size(); j++) { if
     * (supplierName.get(j).getText().equals(supName)) {
     * removeSupplier.get(j).click(); Thread.sleep(2); } } }
     */
    int supplierId = Integer.parseInt(supplierNo);
    String s = String.valueOf(supplierId - 1);
    System.out.println("String Value>>>>" + s);
    WebElement removeSupplier = this.driver().findElement(By.cssSelector("[id = 'invited" + s + "'] [data-column = removeSupplier]"));
    removeSupplier.click();
    }

  /**
   * This method clicks on Add Contact Button.
   */
  public void performAddContact() {
    clickOn(this.driver(), supplierCreatePage.getAddContact(), TestUtil.getTimeOut());
    }

  /**
   * This method checks on the visibility of Add Contact Details - Email , Telephone , Cancel Button , AddContact Button.
   */
  public void visibilityOfAddContact() throws InterruptedException {
    Thread.sleep(sleepTime);
    boolean companyNameReadOnly = false;
    if ("true".equalsIgnoreCase(supplierCreatePage.getCompanyName().getAttribute("readonly"))) {
      companyNameReadOnly = true;
      }
    Assert.assertEquals(true, companyNameReadOnly);
    Assert.assertEquals(true, supplierCreatePage.getContactEmail().isDisplayed());
    Assert.assertEquals(true, supplierCreatePage.getTelephoneNumber().isDisplayed());
    Assert.assertEquals(false, supplierCreatePage.getAddContactButton().isEnabled());
    Assert.assertEquals(true, supplierCreatePage.getCancelContact().isEnabled());

    }

  /**
   * This method enter on Add Contact details - Contact Name , Email and Telephone.
   */
  public void addContactDetails(final String contactPerson, final String email, final String mobile) throws InterruptedException {
    sendkeys(this.driver(), supplierCreatePage.getAddContactName(), TestUtil.getTimeOut(), contactPerson);
    sendkeys(this.driver(), supplierCreatePage.getContactEmail(), TestUtil.getTimeOut(), email);
    sendkeys(this.driver(), supplierCreatePage.getTelephoneNumber(), TestUtil.getTimeOut(), mobile);
    }

  /**
   * This method clicks on Add Contact Button.
   */
  public void performAddContactSupplier() throws InterruptedException {
    clickOn(this.driver(), supplierCreatePage.getAddContactButton(), TestUtil.getTimeOut());
    }

  /**
   * This method clicks on cancel button.
   */
  public void performCancel() throws InterruptedException {
    clickOn(this.driver(), supplierCreatePage.getCancelContact(), TestUtil.getTimeOut());
    }

  public static final void sendkeys(final RemoteWebDriver driver, final WebElement element, final int timeout, final String value) {
    new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
    element.sendKeys(value);
    }

  public static final void clickOn(final RemoteWebDriver driver, final WebElement element, final int timeout) {
    new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
    element.click();
    }

  }
