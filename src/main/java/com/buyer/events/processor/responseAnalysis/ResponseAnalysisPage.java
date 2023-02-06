package com.buyer.events.processor.responseAnalysis;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResponseAnalysisPage {
  private WebDriver driver;

  public ResponseAnalysisPage(final WebDriver drivers) {
    this.driver = drivers;
    //js = (JavascriptExecutor) drivers;
    PageFactory.initElements(drivers, this);
    }

  @FindBy(css = "[data-test ='hide-show-suppliers']")
  private WebElement hideShowSupplierDropdown;


  @FindBy(css = "[data-test ='hide-show-all-suppliers']")
  private WebElement hideShowAllSupplier;

  @FindBy(xpath =  "//*[@title = 'Download to excel']")
  private WebElement downloadExcel;

  public final WebDriver getDriver() {
    return driver;
    }

  public final WebElement getDownloadExcel() {
    return downloadExcel;
    }



  public final WebElement getHideShowAllSupplier() {
    return hideShowAllSupplier;
    }



  public final WebElement getHideShowSupplierDropdown() {
    return hideShowSupplierDropdown;
    }



  public final WebElement navigateResponseTab(final String navigationTab) {
    WebElement naviagtionElement = driver.findElement(By.cssSelector("[data-test = '" + navigationTab + "']"));
    return naviagtionElement;
    }

  public final WebElement validateResponseData(final String customerNo, final String questionNo) {
    WebElement responseData = driver.findElement(By.cssSelector("[data-test = 'supplier-response-" + customerNo + "-" + questionNo + ".']"));
    return responseData;
    }

  public final WebElement supplierNameElement(final String supplierIndex, final String supName) {
    WebElement responseData = driver.findElement(By.cssSelector("[data-test = " + supName + "-" + supplierIndex + "]"));
    return responseData;
    }

  public final WebElement pqcApproveButton(final String supplierNo) {
    WebElement approveBtn = driver.findElement(By.cssSelector("[data-test = 'pqc-approve-btn-" + supplierNo + "']"));
    System.out.println("Approve Button>>>" + approveBtn);
    return approveBtn;
    }

  public final WebElement pqcRejectButton(final String supplierNo) {
    WebElement rejectBtn = driver.findElement(By.cssSelector("[data-test = pqc-reject-btn-" + supplierNo + "]"));
    return rejectBtn;
    }

  public final WebElement reasonApproveReject(final String approveOrReject) {
    WebElement reason = driver.findElement(By.cssSelector("[data-test = reason-to-" + approveOrReject + "]"));
    return reason;
    }

  public final WebElement approveOrRejectButton(final String approveOrReject) {
    WebElement reason = driver.findElement(By.cssSelector("[data-test = " + approveOrReject + "-btn]"));
    return reason;
    }

  public final WebElement validateCommentIcon(final String supplierNo) {
    WebElement iconChat = driver.findElement(By.cssSelector("[data-test = comment-icon-" + supplierNo + "]"));
    return iconChat;
    }

  public final WebElement hideShowSupplier(final String supplierNo, final String supplierName, final String toggleYesOrNo) {
    WebElement supplierElement = driver.findElement(By.cssSelector("[data-test = " + supplierName + "-" + supplierNo + "-" + toggleYesOrNo + "]"));
    return supplierElement;
    }

  public final WebElement approveOrRejectCancel(final String approveOrReject) {
    WebElement reason = driver.findElement(By.cssSelector("[data-test = " + approveOrReject + "-cancle]"));
    return reason;
    }

  public final List<WebElement> pqcSupplierTable() {
    WebElement tableRow = driver.findElement(By.cssSelector("tr[role=row]:nth-child(1)"));
    List<WebElement> supplierTable = tableRow.findElements(By.className("supplier-name-text"));
    return supplierTable;
    }

  public final List<WebElement> isSupplierPresent(final String supplierIndex, final String supName) {
    WebElement tableRow = driver.findElement(By.cssSelector("tr[role=row]:nth-child(1)"));
    List<WebElement> supplierData = tableRow.findElements(By.cssSelector("[data-test = " + supName + "-" + supplierIndex + "]"));
    return supplierData;
    }

  }
