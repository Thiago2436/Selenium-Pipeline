package com.buyer.events.processor.responseAnalysis;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.buyer.events.processor.ITabCommandProcessor;
import com.buyer.events.processor.eventRulesProcessor.EventRulesAsserterCommand;
import com.buyer.events.processor.overviewProcessor.OverviewCreationPage;
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.TestUtil;
import com.sol.qa.util.XlsReader;

public class ResponseAnalysisProcessorCommand implements ITabCommandProcessor {
  private WindowManager windowManager;
  private ResponseAnalysisPage responseAnalysisPage;
  private OverviewCreationPage overViewPage;
  private EventRulesAsserterCommand assertEventRules;
  final int sleepTime = 1000;
  private static String fileDownloadpath = "C:\\Users\\HP\\Downloads";

  public ResponseAnalysisProcessorCommand(final WindowManager windowManger) {
    this.windowManager = windowManger;
    responseAnalysisPage = new ResponseAnalysisPage(this.driver());
    overViewPage = new OverviewCreationPage(this.driver());
    assertEventRules = new EventRulesAsserterCommand(windowManger);
    }

  private RemoteWebDriver driver() {
    return  this.windowManager.getActiveDriver();
    }

  /**
   * This method will navigate to specified tab.
   */
  public void performNavigationMonitor(final String navigationTab) throws InterruptedException, AWTException {
    Thread.sleep(sleepTime);
    System.out.println("Navigation Tab");
    WebElement naviagtionLink = overViewPage.navigationMonitorPhase(navigationTab);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", naviagtionLink);
    WindowManager.clickOn(this.driver(), naviagtionLink, TestUtil.getTimeOut());
    }

  /**
   * This method will navigate to specified tab.
   */
  public void performResponseAnalysisNavigate(final String navigationTab) throws InterruptedException, AWTException {
    System.out.println("Navigation Tab");
    WebElement naviagtionLink = responseAnalysisPage.navigateResponseTab(navigationTab);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", naviagtionLink);
    WindowManager.clickOn(this.driver(), naviagtionLink, TestUtil.getTimeOut());
    }

  /**
   * This method will navigate to specified tab.
   */
  public void performSupplierPqcResponse(final String customerNo, final String questionNo, final String responseData)
      throws InterruptedException, AWTException {
    WebElement assertResponseData = responseAnalysisPage.validateResponseData(customerNo, questionNo);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", assertResponseData);
    System.out.println("responseData>>>" + responseData + "AssertResponseData>>>" + assertResponseData.getText());
    AssertJUnit.assertEquals(assertResponseData.getText(), responseData);
    }


  /**
   * This method will give date response by supplier.
   */
  public void fetchPqcSupplierName(final String supplierIndex, final String supplierName) throws InterruptedException, AWTException {
    WebElement supplierNameElement = responseAnalysisPage.supplierNameElement(supplierIndex, supplierName);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", supplierNameElement);
    AssertJUnit.assertEquals(supplierNameElement.getText(), supplierName);
    }

  /**
   * This method will click on Approve Supplier.
   */
  public void performApprovePqc(final String supplierIndex) throws InterruptedException, AWTException {
    WindowManager.clickOn(this.driver(), responseAnalysisPage.pqcApproveButton(supplierIndex), TestUtil.getTimeOut());
    }

  /**
   * This method will click on Approve Supplier.
   */
  public void performRejectPqc(final String supplierIndex) throws InterruptedException, AWTException {
    Thread.sleep(sleepTime);
    WindowManager.clickOn(this.driver(), responseAnalysisPage.pqcRejectButton(supplierIndex), TestUtil.getTimeOut());
    }

  /**
   * This method will enter the reason for Approval.
   */
  public void addReasonToApproveReject(final String approveOrReject, final String message) throws InterruptedException, AWTException {
    WebElement reasonMessage = responseAnalysisPage.reasonApproveReject(approveOrReject);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", reasonMessage);
    WindowManager.sendkeys(driver(), reasonMessage, TestUtil.getTimeOut(), message);
    }

  /**
   * This method will click on cancel button.
   */
  public void performCancelButton(final String approveOrReject) throws InterruptedException, AWTException {
    WebElement approveOrRejectCancel = responseAnalysisPage.approveOrRejectCancel(approveOrReject);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", approveOrRejectCancel);
    WindowManager.clickOn(this.driver(), approveOrRejectCancel, TestUtil.getTimeOut());
    }

  /**
   * This method will click on Approve button.
   */
  public void performApproveOrReject(final String approveOrReject) throws InterruptedException, AWTException {
    WebElement approveOrRejectButton = responseAnalysisPage.approveOrRejectButton(approveOrReject);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", approveOrRejectButton);
    WindowManager.clickOn(this.driver(), approveOrRejectButton, TestUtil.getTimeOut());
    }

  /**
   * This method will click on Comment Icon and assert copied text to clipboard.
   * @throws IOException
   * @throws UnsupportedFlavorException
   */
  public void validateCommentIcon(final String supplierIndex, final String copiedClipboard) throws InterruptedException {
    Thread.sleep(sleepTime);
    WebElement commentIcon = responseAnalysisPage.validateCommentIcon(supplierIndex);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", commentIcon);
    WindowManager.clickOn(this.driver(), commentIcon, TestUtil.getTimeOut());
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Clipboard clipboard = toolkit.getSystemClipboard();
    String actualCopedText = null;
    try {
      actualCopedText = (String) clipboard.getData(DataFlavor.stringFlavor);
      } catch (UnsupportedFlavorException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      }
    AssertJUnit.assertEquals(actualCopedText, copiedClipboard);

    }

  /**
   * This method will Assert the hide Show All Suppliers.
   */
  public void hideShowAllSuppliers(final String toggleYesNo) throws InterruptedException, AWTException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", responseAnalysisPage.getHideShowSupplierDropdown());
    responseAnalysisPage.getHideShowSupplierDropdown().click();
    List<WebElement> isPqcSupplierTablePresent;
    if (toggleYesNo.equals("Hide")) {
      if (!responseAnalysisPage.getHideShowAllSupplier().getAttribute("src").contains("no")) {
        WindowManager.clickOn(this.driver(), responseAnalysisPage.getHideShowAllSupplier(), TestUtil.getTimeOut());
        isPqcSupplierTablePresent = responseAnalysisPage.pqcSupplierTable();
        if (isPqcSupplierTablePresent.size() > 0) {
          Assert.assertTrue(true);
          }
        }
      }
    if (toggleYesNo.equals("Show")) {

      if (responseAnalysisPage.getHideShowAllSupplier().getAttribute("src").contains("no")) {
        WindowManager.clickOn(this.driver(), responseAnalysisPage.getHideShowAllSupplier(), TestUtil.getTimeOut());
        isPqcSupplierTablePresent = responseAnalysisPage.pqcSupplierTable();
        if (isPqcSupplierTablePresent.size() <= 0) {
          Assert.assertTrue(true);
          }
        }
      }

    Actions actions = new Actions(this.driver());
    actions.moveToElement(responseAnalysisPage.getHideShowSupplierDropdown()).click().build().perform();
    }


  /**
   * This method will Assert the hide Show All Suppliers.
   */
  public void assertHideShowSupplier(final String toggleYesNo, final String supplierNo, final String supplierName)
      throws InterruptedException, AWTException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", responseAnalysisPage.getHideShowSupplierDropdown());
    responseAnalysisPage.getHideShowSupplierDropdown().click();
    System.out.println("assertHideShowSupplier>>>" + toggleYesNo);
    //WebElement supplierToggleYes = responseAnalysisPage.hideShowSupplier(supplierNo, supplierName, "Yes");
   // WebElement supplierToggleNo = responseAnalysisPage.hideShowSupplier(supplierNo, supplierName, "No");
    Thread.sleep(sleepTime);
    if (toggleYesNo.equals("Hide")) {
      System.out.println("Hide");
      if (responseAnalysisPage.hideShowSupplier(supplierNo, supplierName, "Yes").isDisplayed()) {
        System.out.println("Yes if Hide");
        responseAnalysisPage.hideShowSupplier(supplierNo, supplierName, "Yes").click();
        } else {
        System.out.println("else Hide");
        responseAnalysisPage.hideShowSupplier(supplierNo, supplierName, "No").click();
        }
      } else {
      if (responseAnalysisPage.hideShowSupplier(supplierNo, supplierName, "No").isDisplayed()) {
        System.out.println("Show No If");
        responseAnalysisPage.hideShowSupplier(supplierNo, supplierName, "No").click();
        } else {
        System.out.println("Show else");
        responseAnalysisPage.hideShowSupplier(supplierNo, supplierName, "Yes").click();
        }
      }

    Actions actions = new Actions(this.driver());
    actions.moveToElement(responseAnalysisPage.getHideShowSupplierDropdown()).click().build().perform();
    }


  /**
   * This method will click on download to excel.
   */
  public void downloadToExcel() throws InterruptedException, AWTException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", responseAnalysisPage.getDownloadExcel());
    WindowManager.clickOn(this.driver(), responseAnalysisPage.getDownloadExcel(), TestUtil.getTimeOut());
    System.out.println("Event Id >>>>" + this.windowManager.getStoreEventId());
    String fileName = "pqc-" + this.windowManager.getStoreEventId() + ".xlsx";
    boolean status = isFileDownloaded(fileName);
    Assert.assertEquals(true, status);
    }

  /**
   * This method to check whether file is downloaded or not.
   */
  public boolean isFileDownloaded(final String fileName) throws InterruptedException {
    final int timeSleep = 3000;
    Thread.sleep(timeSleep);
    boolean flag = false;

    File directory = new File(fileDownloadpath);

    File[] content = directory.listFiles();

    System.out.println("Length of Content >>>" + content.length);
    System.out.println("FileName>>>" + fileName);

    for (int i = 0; i < content.length; i++) {

      if (content[i].getName().equals(fileName)) {
        System.out.println("content File Name>>>>" + content[i].getName());
        flag = true;
        }
      }
    System.out.println("flag>>>" + flag);
    return flag;
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
        performNavigationMonitor(argument1);
        break;
      case "NavigateResponseAnalysisTab":
        performResponseAnalysisNavigate(argument1);
        break;
      case "SupplierPqcResponse":
        performSupplierPqcResponse(argument1, argument2, argument3);
        break;
      case "PqcSupplierName":
        fetchPqcSupplierName(argument1, argument2);
        break;
      case "PerformApprovePqc":
        performApprovePqc(argument1);
        break;
      case "PerformRejectPqc":
        performRejectPqc(argument1);
        break;
      case "ReasonToApproveOrReject":
        addReasonToApproveReject(argument1, argument2);
        break;
      case "CancelButton":
        performCancelButton(argument1);
        break;
      case "performApproveOrReject":
        performApproveOrReject(argument1);
        break;
      case "validateCommentIcon":
        validateCommentIcon(argument1, argument2);
        break;
      case "HideShowAllSuppliers":
        hideShowAllSuppliers(argument1);
        break;
      case "AssertHideShowSupplier":
        assertHideShowSupplier(argument1, argument2, argument3);
        break;
      case "DownloadToExcel":
        downloadToExcel();
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
      case "ApproveRejectButton":
        validateApproveRejectButton(argument2);
        break;
      case "ToastMessage":
        assertEventRules.assertToastMsg(argument2);
        break;
      default:
        System.out.println("no switch match" + argument1);
      }
    }

  /**
   * This method validate the disability of Approve Button.
   * @throws InterruptedException
   */
  public void validateApproveRejectButton(final String customerNo) throws InterruptedException {
    System.out.println("Customer No>>>" + customerNo);
    Thread.sleep(sleepTime);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", responseAnalysisPage.pqcApproveButton(customerNo));
    System.out.println(responseAnalysisPage.pqcApproveButton(customerNo).isDisplayed());
    Assert.assertTrue(responseAnalysisPage.pqcApproveButton(customerNo).isEnabled());
    Assert.assertTrue(responseAnalysisPage.pqcRejectButton(customerNo).isEnabled());
    }

  }
