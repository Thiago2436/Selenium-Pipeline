package com.buyer.events.processor.eventRulesProcessor.bulkUploadLots;

import com.buyer.events.processor.pricingSheetProcessor.PricingSheetCreationPage;
import com.sol.qa.base.WindowManager;

public class CommandHandler {

  private PricingSheetCreationPage pricingSheetPage;
  private Page bulkUploadDialog;
  private WindowManager windowManager;
  final int sleepTime = 3000;


  public CommandHandler(final WindowManager windowManger) {
    this.windowManager = windowManger;
    System.out.println("Command Handler Driver>>>" + this.windowManager.getActiveDriver());
    pricingSheetPage = new PricingSheetCreationPage(this.windowManager.getActiveDriver());
    bulkUploadDialog = new Page(this.windowManager.getActiveDriver());
    }

  /**
   * This method clicks on Open Bulk Upload Lots Popup button.
   */
  public void openBulkUploadLotsPopup() throws InterruptedException {
    pricingSheetPage.getAddLot().click();
    bulkUploadDialog.openBulkUploadLotsPopup().click();
    }

  /**
   * This method clicks on Upload Lots Via Excel button.
   */
  public void uploadLotsViaExcel(final String excelPath) throws InterruptedException {
    String excelFilePath = System.getProperty("user.dir") + excelPath;
    bulkUploadDialog.getExcelFileUpload().sendKeys(excelFilePath);
    Thread.sleep(sleepTime);
    }

  /**
   * This method clicks on download BulkUploadLots Excel.
   */
  public void downloadBulkUploadLotsExcel() throws InterruptedException {
    bulkUploadDialog.getDownloadBulkUploadLotsExcel().click();
    }
  }
