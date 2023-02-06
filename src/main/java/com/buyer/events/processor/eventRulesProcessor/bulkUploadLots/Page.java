package com.buyer.events.processor.eventRulesProcessor.bulkUploadLots;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page  {
  private WebDriver driver;

  public Page(final WebDriver drivers) {
    this.driver = drivers;
    PageFactory.initElements(drivers, this);
    }

  public final WebElement openBulkUploadLotsPopup() {
    WebElement uploadLotsViaExcelButton = driver.findElement(By.id("uploadLotsViaExcel"));
    return uploadLotsViaExcelButton;
    }

  @FindBy(id = "downloadBulkExcel")
  private WebElement downloadBulkUploadLotsExcel;

  @FindBy(xpath = "//input[@type='file']")
  private WebElement excelFileUpload;

  public final WebElement getDownloadBulkUploadLotsExcel() {
    return downloadBulkUploadLotsExcel;
    }

  public final WebElement getExcelFileUpload() {
    return excelFileUpload;
    }

  }
