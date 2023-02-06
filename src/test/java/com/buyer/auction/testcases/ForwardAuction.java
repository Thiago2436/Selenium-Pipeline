package com.buyer.auction.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.awt.AWTException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.admin.login.pages.BuyerHomePage;
import com.admin.login.pages.BuyerLoginPage;
import com.buyer.events.processor.CommandProcessor;
import com.sol.qa.base.TestBase;
import com.sol.qa.util.XlsReader;

public class ForwardAuction extends TestBase {
  BuyerHomePage buyerHomePage;
  BuyerLoginPage buyerLoginPage;

  CommandProcessor initCommandProcessor;

  public String event_URL = "https://yamuna.krinati.co/admin/buyer/event-rules/edit/3876";

  @BeforeMethod
  public void setUp() throws MalformedURLException, InterruptedException{
    initialization();
    //buyerHomePage = new BuyerHomePage();
    //buyerLoginPage = new BuyerLoginPage(getDriver());
    initCommandProcessor = new CommandProcessor();
  }

  @Test
  public void init_Forward_Auction_NoPQC_SingleCurrency_211() throws InterruptedException, AWTException {
    String command, argument1, argument2, argument3, argument4;							
    XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Forward_Auction_Creation/01Forward_Auction_NoPQC_SingleCurrency.xlsx");
    String sheetName = "init";
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int intRow=2; intRow<=sheetRowCount; intRow++) {
      command= reader.getCellData(sheetName, 0, intRow);
      argument1 = reader.getCellData(sheetName, 1, intRow);
      argument2 = reader.getCellData(sheetName, 2, intRow);
      argument3 = reader.getCellData(sheetName, 3, intRow);
      argument4 = reader.getCellData(sheetName, 4, intRow);	
      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);
    }

  }

  @Test
  public void init_Forward_Auction_NotApprovalPQC_MultiCurrency_211() throws InterruptedException, AWTException {
    String command, argument1, argument2, argument3, argument4;									
    XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Forward_Auction_Creation/02Forward_Auction_NotApprovalPQC_MultiCurrency.xlsx");
    String sheetName = "init";
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int intRow=2; intRow<=sheetRowCount; intRow++) {
      command= reader.getCellData(sheetName, 0, intRow);
      argument1 = reader.getCellData(sheetName, 1, intRow);
      argument2 = reader.getCellData(sheetName, 2, intRow);
      argument3 = reader.getCellData(sheetName, 3, intRow);
      argument4 = reader.getCellData(sheetName, 4, intRow);	
      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);
    }

  }

  @Test
  public void init_Forward_Auction_NotApprovalPQC_SingleCurrency_MultiCurrency_211() throws InterruptedException, AWTException {
    String command, argument1, argument2, argument3, argument4;								
    XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Forward_Auction_Creation/03Forward_Auction_NotApprovalPQC_SingleCurrency.xlsx");
    String sheetName = "init";
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int intRow=2; intRow<=sheetRowCount; intRow++) {
      command= reader.getCellData(sheetName, 0, intRow);
      argument1 = reader.getCellData(sheetName, 1, intRow);
      argument2 = reader.getCellData(sheetName, 2, intRow);
      argument3 = reader.getCellData(sheetName, 3, intRow);
      argument4 = reader.getCellData(sheetName, 4, intRow);	
      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);
    }

  }

  @Test
  public void init_Forward_Staggered_ApprovalPQC_SingleCurrency_211() throws InterruptedException, AWTException {
    String command, argument1, argument2, argument3, argument4;								
    XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Forward_Auction_Creation/04Forward_Staggered_ApprovalPQC_SingleCurrency.xlsx");
    String sheetName = "init";
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int intRow=2; intRow<=sheetRowCount; intRow++) {
      command= reader.getCellData(sheetName, 0, intRow);
      argument1 = reader.getCellData(sheetName, 1, intRow);
      argument2 = reader.getCellData(sheetName, 2, intRow);
      argument3 = reader.getCellData(sheetName, 3, intRow);
      argument4 = reader.getCellData(sheetName, 4, intRow);	
      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);
    }

  }

  @Test
  public void init_Forward_Auction_Parallel_ApprovalPQC_MultiCurrency_211() throws InterruptedException, AWTException {
    String command, argument1, argument2, argument3, argument4;								
    XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Forward_Auction_Creation/05Forward_Auction_Parallel_ApprovalPQC_MultiCurrency.xlsx");
    String sheetName = "init";
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int intRow=2; intRow<=sheetRowCount; intRow++) {
      command= reader.getCellData(sheetName, 0, intRow);
      argument1 = reader.getCellData(sheetName, 1, intRow);
      argument2 = reader.getCellData(sheetName, 2, intRow);
      argument3 = reader.getCellData(sheetName, 3, intRow);
      argument4 = reader.getCellData(sheetName, 4, intRow);	
      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);
    }

  }

  @Test
  public void init_Forward_Auction_NoPQC_MultiCurrency_211() throws InterruptedException, AWTException {
    String command, argument1, argument2, argument3, argument4;								
    XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Forward_Auction_Creation/06Forward_Auction_NoPQC_MultiCurrency.xlsx");
    String sheetName = "init";
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int intRow=2; intRow<=sheetRowCount; intRow++) {
      command= reader.getCellData(sheetName, 0, intRow);
      argument1 = reader.getCellData(sheetName, 1, intRow);
      argument2 = reader.getCellData(sheetName, 2, intRow);
      argument3 = reader.getCellData(sheetName, 3, intRow);
      argument4 = reader.getCellData(sheetName, 4, intRow);	
      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);
    }

  }


  public WebElement navigationPricingSheet(String linkName) throws InterruptedException {
    System.out.println("Navigation Link");
    System.out.println("Link>>>>" + linkName);
    getDriver().navigate().to(event_URL);
    Thread.sleep(6000);
    WebElement naviagtionItem = getDriver()
        .findElement(By.xpath("//*[@class ='breadcrumbs-navigation ng-star-inserted']"));
    WebElement navigationSelected = naviagtionItem.findElement(By.id("pricingSheetChevron"));
    System.out.println("WebElement>>>" + navigationSelected);
    return navigationSelected;
  }

  @Test(priority = 6)
  public void testExistingEvent() throws InterruptedException, AWTException {
    // String argument2 = "";
    Thread.sleep(10000);
    XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Forward_Auction/04Forward_Monitor_Staggered_ApprovalPQC_SingleCurrency.xlsx");
    //XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Forward_Auction_Creation/03Forward_Auction_NotApprovalPQC_SingleCurrency.xlsx");
    //String sheetName = "ExcelUpload";
    String sheetName = "init1";
    int sheetRowCount = reader.getRowCount(sheetName);
    System.out.println("sheetRowCount>>>>"+sheetRowCount);
    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3= reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);

      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

    }

  }

  /*
   * @AfterMethod public void tearDown() { driver.quit(); }
   */


}

