package com.buyer.events.processor.auctionConsole;

import java.awt.AWTException;
import java.text.Format;
import java.util.Locale;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.buyer.events.processor.ITabCommandProcessor;
import com.buyer.events.processor.responseAnalysis.ResponseAnalysisProcessorCommand;
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.XlsReader;

public class AuctionConsoleProcessorCommand implements ITabCommandProcessor {

  private WindowManager windowManager;
  final int sleepTime = 5000;
  private AuctionConsolePage auctionConsolePage;
  private ResponseAnalysisProcessorCommand responseAnalysis;
  final int colNumber = 4;

  public AuctionConsoleProcessorCommand(final WindowManager windowManger) {
    this.windowManager = windowManger;
    auctionConsolePage = new AuctionConsolePage(this.driver());
    responseAnalysis = new ResponseAnalysisProcessorCommand(windowManger);

    }

  private RemoteWebDriver driver() {
    return  this.windowManager.getActiveDriver();
    }

  /**
   * This method validate the Bid Table.
   */
  public void validateBidTable(final XlsReader reader, final String sheetName) throws InterruptedException {
    int sheetRowCount = reader.getRowCount(sheetName);
    int sheetColCount = reader.getColumnCount(sheetName);
    Format formatter = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
    for (int intRow = 0; intRow < sheetRowCount - 1; intRow++) {
      for (int intCol = 1; intCol < sheetColCount; intCol++) {
       // String columnName = reader.getCellData(sheetName, intCol, 1);
        //String SupplierIndex = reader.getCellData(sheetName, 0, intRow+2);
        String cellReader = reader.getCellData(sheetName, intCol, intRow + 2);
        WebElement tableElement = this.driver().findElement(
            By.cssSelector("[data-test = '" + reader.getCellData(sheetName, intCol, 1) + "-" + reader.getCellData(sheetName, 0,
                intRow + 2) + "']"));
        ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", tableElement);
        if (cellReader.equals("-")) {
          Assert.assertEquals(cellReader, tableElement.getText());
          } else if (intCol == colNumber) {
          Assert.assertEquals(reader.getCellDataTime(sheetName, intCol, intRow + 2), tableElement.getText());
          } else if (intCol == 1) {
          Assert.assertEquals(cellReader, tableElement.getText());
          } else {
          String finalValue = formatter.format(Double.parseDouble(cellReader)).substring(1);
          Assert.assertEquals(finalValue, tableElement.getText().substring(2));
          }
        }
      }
    }

  /**
   *This method have XLs_Reader , command , argument1 , argument2 , argument3 and argument4 as Parameter
   *It have switch command and its corresponding method.
   */
  public void processCommand(final XlsReader reader, final String command, final String argument1, final String argument2, final String argument3,
      final String argument4) throws AWTException, InterruptedException {
    //documentPage = new DocumentCreationPage(this.driver());
    switch (command) {
      case "validateBidTable":
        validateBidTable(reader, argument1);
        break;
      case "NavigationMonitorTab":
        responseAnalysis.performNavigationMonitor(argument1);
        break;
      default:
        System.out.println("No Auction Console Process Command Match");

      }
    }

  }
