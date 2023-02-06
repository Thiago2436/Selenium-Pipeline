package com.buyer.auction.testcases;

import java.awt.AWTException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.admin.login.pages.BuyerHomePage;
import com.admin.login.pages.BuyerLoginPage;
import com.buyer.events.processor.CommandProcessor;
import com.buyer.events.processor.documentProcessor.DocumentProcessorCommand;
import com.buyer.events.processor.eventRulesProcessor.EventRulesProcessorCommand;
import com.buyer.events.processor.overviewProcessor.OverviewProcessorCommand;
import com.buyer.events.processor.pqcProcessor.PqcProcessorCommand;
import com.buyer.events.processor.pricingSheetProcessor.PricingSheetProcessorCommand;
import com.buyer.events.processor.questionnaireProcessor.QuestionnaireProcessorCommand;
import com.buyer.events.processor.supplierProcessor.SupplierProcessorCommand;
import com.buyer.events.processor.teamMemberProcessor.TeamMemberCreationPage;
import com.buyer.events.processor.teamMemberProcessor.TeamMemberProcessorCommand;
import com.sol.qa.base.TestBase;
import com.sol.qa.util.XlsReader;
import com.supplier.events.processor.SupplierLoginProcessor;

public class ForwardMonitorTestCases extends TestBase {
  BuyerHomePage buyerHomePage;
  BuyerLoginPage buyerLoginPage;
  CommandProcessor initCommandProcessor;
  PricingSheetProcessorCommand pricingSheetProcessor;
  TeamMemberProcessorCommand teamMemberProcessor;
  SupplierProcessorCommand supplierProcessor;
  QuestionnaireProcessorCommand questionnaireProcessor;
  DocumentProcessorCommand documentProcessor;
  PqcProcessorCommand pqcProcessor;
  EventRulesProcessorCommand eventRulesProcessor;
  public String event_name = "Forward Auction Supplier Participate";
  TeamMemberCreationPage teamPage;
  OverviewProcessorCommand overviewProcessor;
  SupplierLoginProcessor supplierLoginProcessor;

  @BeforeMethod
  public void setUp() throws MalformedURLException, InterruptedException {
    initialization();
    // buyerHomePage = new BuyerHomePage();
    // buyerLoginPage = new BuyerLoginPage();
    initCommandProcessor = new CommandProcessor();
    supplierLoginProcessor = new SupplierLoginProcessor();
    // buyerHomePage = buyerLoginPage.Successlogin(prop.getProperty("buyerEmail"),
    // prop.getProperty("password"));
    }

  public WebElement sourcingEventList(String eventName) throws InterruptedException {
    Thread.sleep(10000);
    // Monitor Phase
    WebElement monitorPhase = getDriver().findElement(By.id("mat-tab-label-0-2"));
    monitorPhase.click();
    Thread.sleep(5000);
    WebElement overlaySourcingList = getDriver().findElement(By.id("table"));
    System.out.println("check in>>>>");
    // Get all options in a list
    WebElement sourceSelectedEvent = overlaySourcingList
        .findElement(By.xpath("//*[contains(text(),'" + eventName + "')]"));
    return sourceSelectedEvent;
    }

  public WebElement navigationPricingSheet(String linkName) throws InterruptedException {
    Thread.sleep(2000);
    WebElement naviagtionItem = getDriver()
        .findElement(By.xpath("//*[@class ='breadcrumbs-navigation ng-star-inserted']"));
    WebElement navigationSelected = naviagtionItem.findElement(By.id("suppliersChevron"));
    return navigationSelected;
    }

  @Test(priority = 01)
  public void FA212_ForwardMonitorAuction_TC01() throws InterruptedException, AWTException {
    Thread.sleep(10000);
    //	driver.navigate().to("https://yamuna.krinati.co/admin/monitor-event/event/5095/over-view");
    //	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    XlsReader reader = new XlsReader(
        "./src/main/java/com/sol/qa/testdata/Forward_Auction/Forward_Auction_Monitor.xlsx");
    // String sheetName = "Pricing_Sheet_Change_Formula";
    String sheetName = "init";
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }
    }

  @Test(priority = 02)
  public void FA212_ForwardMonitorAuction_TC02() throws InterruptedException, AWTException {
    Thread.sleep(10000);
    getDriver().navigate().to("https://yamuna.krinati.co/admin/monitor-event/event/5095/over-view");
    getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    XlsReader reader = new XlsReader(
        "./src/main/java/com/sol/qa/testdata/Forward_Auction/Forward_Auction_Monitor.xlsx");
    String sheetName = "TC02_ValidateOverviewTeamMember";
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      overviewProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }
    }

  @Test(priority = 03)
  public void FA212_ForwardMonitorAuction_TC03() throws InterruptedException, AWTException {
    Thread.sleep(10000);
    getDriver().navigate().to("https://yamuna.krinati.co/admin/monitor-event/event/5095/over-view");
    getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    XlsReader reader = new XlsReader(
        "./src/main/java/com/sol/qa/testdata/Forward_Auction/Forward_Auction_Monitor.xlsx");
    String sheetName = "TC03_ValidateOverviewDocument";
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      overviewProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);
      }
    }

  @Test(priority = 04)
  public void FA212_ForwardMonitorAuction_TC04() throws InterruptedException, AWTException {

    Thread.sleep(10000);
    getDriver().navigate().to("https://yamuna.krinati.co/admin/monitor-event/event/5095/over-view");
    getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    XlsReader reader = new XlsReader(
        "./src/main/java/com/sol/qa/testdata/Forward_Auction/Forward_Auction_Monitor.xlsx");

    String sheetName = "TC04_ValidateOverviewSupplier";
    int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      overviewProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }
    }

  @Test(priority = 12)
  public void FA212_ForwardMonitorAuction_TC12() throws InterruptedException, AWTException {
    Thread.sleep(10000);
    // driver.navigate().to("https://yamuna.krinati.co/admin/monitor-event/event/5095/over-view");
    // driver.navigate().to("https://yamuna.krinati.co/supplier");
    // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    XlsReader reader = new XlsReader(
        "./src/main/java/com/sol/qa/testdata/Forward_Auction/Forward_Auction_Monitor.xlsx");
    String sheetName = "TC012_ValidatePQCApprNoNeed";
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);
      }
    }

  }
