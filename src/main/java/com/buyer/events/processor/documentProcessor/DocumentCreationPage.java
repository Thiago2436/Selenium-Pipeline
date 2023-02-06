package com.buyer.events.processor.documentProcessor;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sol.qa.util.TestUtil;

public class DocumentCreationPage  {

  private WebDriver driver;
 // private JavascriptExecutor js;

  public DocumentCreationPage(final WebDriver drivers) {
    this.driver = drivers;
    //js = (JavascriptExecutor) drivers;
    PageFactory.initElements(driver, this);
    }

  public final WebDriver getDriver() {
    return driver;
    }

  @FindBy(id = "addDocuments")
  private WebElement fileUpload;

  @FindBy(id = "addLink")
  private WebElement addLink;

  @FindBy(id = "confirmAction")
  private WebElement confirmAction;

  @FindBy(id = "cancelAction")
  private WebElement cancelAction;

  @FindBy(className = "no-docs")
  private WebElement noDocsLabel;

  @FindBy(xpath = "//input[@type='file']")
  private WebElement fileUploadPath;

  @FindBy(id = "linkAddress")
  private WebElement urlLinkAddress;

  @FindBy(id = "linkText")
  private WebElement textToDisplay;

  @FindBy(id = "cancelLink")
  private WebElement cancelAddLink;

  @FindBy(id = "saveLink")
  private WebElement saveAddLink;

  @FindBy(id = "proceedEvent")
  private WebElement proceedButton;

  @FindBy(xpath = "//*[@data-column='visibleToSuppliers']")
  private WebElement visibleToSuppliers;

  @FindBy(xpath = "//*[@id='row0']/mat-cell[4]")
  private WebElement fileUploadedDate;

  public final WebElement getFileUpload() {
    return fileUpload;
    }

  public final WebElement getAddLink() {
    return addLink;
    }

  public final WebElement getConfirmAction() {
    return confirmAction;
    }

  public final WebElement getCancelAction() {
    return cancelAction;
    }

  public final WebElement getNoDocsLabel() {
    return noDocsLabel;
    }

  public final WebElement getFileUploadPath() {
    return fileUploadPath;
    }

  public final WebElement getUrlLinkAddress() {
    return urlLinkAddress;
    }

  public final WebElement getTextToDisplay() {
    return textToDisplay;
    }

  public final WebElement getCancelAddLink() {
    return cancelAddLink;
    }

  public final WebElement getSaveAddLink() {
    return saveAddLink;
    }

  public final WebElement getProceedButton() {
    return proceedButton;
    }

  public final WebElement getVisibleToSuppliers() {
    return visibleToSuppliers;
    }

  public final WebElement getFileUploadedDate() {
    return fileUploadedDate;
    }

  public final WebElement editDocumentName(final String fileRowNumber) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtil.getTimeOut()));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#row" + fileRowNumber + " [data-column='docName']")));
    WebElement docName = driver.findElement(By.cssSelector("#row" + fileRowNumber + " [data-column='docName']"));
    return docName;
    }


  public final WebElement uploadedDocumentName(final String fileRowNumber) {
    WebElement docName = driver.findElement(By.xpath("//*[@id='row" + fileRowNumber + "']/mat-cell[2]/span"));
    return docName;
    }

  public final  WebElement fileType(final String fileRowNumber) {
    WebElement uploadBy = driver.findElement(By.xpath("//*[@id='row" + fileRowNumber + "']/mat-cell[3]"));
    return uploadBy;
    }

  public final WebElement uploadedBy(final String fileRowNumber) {
    WebElement uploadBy = driver.findElement(By.xpath("//*[@id='row" + fileRowNumber + "']/mat-cell[5]/span"));
    return uploadBy;
    }

  public final WebElement fileSize(final String fileRowNumber) {
    WebElement docSize = driver.findElement(By.xpath("//*[@id='row" + fileRowNumber + "']/mat-cell[6]"));
    return docSize;
    }

  public final WebElement editDocument(final String fileRowNumber) {
    WebElement editDoc = driver.findElement(By.cssSelector("#row" + fileRowNumber + " [data-column = 'editDoc']"));
    return editDoc;
    }

  public final WebElement editLink(final String fileRowNumber) {
    WebElement editLink = driver
        .findElement(By.cssSelector("#row" + fileRowNumber + " [data-column = 'editLink']"));
    return editLink;
    }

  public final WebElement deleteDocument(final String fileRowNumber) {
    WebElement deleteDoc = driver
        .findElement(By.cssSelector("#row" + fileRowNumber + " [data-column = 'deleteDoc']"));
    return deleteDoc;
    }

  public final WebElement downloadDocument(final String fileRowNumber) {
    WebElement downloadDoc = driver
        .findElement(By.cssSelector("#row" + fileRowNumber + " [data-column = 'downloadDoc']"));

    return downloadDoc;
    }

  }
