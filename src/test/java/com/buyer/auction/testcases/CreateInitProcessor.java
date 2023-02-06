package com.buyer.auction.testcases;

import java.awt.AWTException;
import java.net.MalformedURLException;
import java.text.ParseException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.admin.login.pages.BuyerHomePage;
import com.admin.login.pages.BuyerLoginPage;
import com.buyer.events.processor.CommandProcessor;
import com.buyer.events.processor.eventRulesProcessor.EventRulesProcessorCommand;
import com.sol.qa.base.TestBase;
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.XlsReader;

public class CreateInitProcessor extends TestBase {
  BuyerHomePage buyerHomePage;
  BuyerLoginPage buyerLoginPage;

  CommandProcessor initCommandProcessor;

  EventRulesProcessorCommand eventRulesProcessor;
  private WindowManager windowManger;

  @BeforeMethod
  public void setUp() throws MalformedURLException, InterruptedException {
    initialization();

    buyerHomePage = new BuyerHomePage();

    buyerLoginPage = new BuyerLoginPage(getDriver());

    initCommandProcessor = new CommandProcessor();
    
    eventRulesProcessor = new EventRulesProcessorCommand(windowManger);

    buyerLoginPage.successBuyerlogin(getProp().getProperty("buyerEmail"), getProp().getProperty("password"));

    }

  @Test
  public void init_ReverseStaggeredAuction() throws InterruptedException, AWTException, ParseException {

    String command, argument1, argument2 = null, argument3 = null;

    XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Reverse_Staggered_Creation.xlsx");
    String sheetName = "init_auction";

    int sheetRowCount = reader.getRowCount(sheetName);

    System.out.println("Row Count>>>" + sheetRowCount);

    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);

      String argument4 = reader.getCellData(sheetName, 4, i);

      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }

    }

  @Test
  public void init_ForwardStaggeredAuction() throws InterruptedException, AWTException, ParseException {

    String command, argument1, argument2 = null, argument3 = null;

    XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Forward_Staggered_Creation.xlsx");
    String sheetName = "init_auction";

    int sheetRowCount = reader.getRowCount(sheetName);

    System.out.println("Row Count>>>" + sheetRowCount);

    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);

      String argument4 = reader.getCellData(sheetName, 4, i);

      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }

    }

  @Test
  public void init_ForwardParallelAuction() throws InterruptedException, AWTException, ParseException {

    String command, argument1, argument2 = null, argument3 = null;

    XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Forward_Parallel_Creation.xlsx");
    String sheetName = "init_auction";

    int sheetRowCount = reader.getRowCount(sheetName);

    System.out.println("Row Count>>>" + sheetRowCount);

    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);

      String argument4 = reader.getCellData(sheetName, 4, i);

      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }

    }

  @Test
  public void init_ForwardSerialAuction() throws InterruptedException, AWTException, ParseException {

    String command, argument1, argument2 = null, argument3 = null;

    XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Forward_Serial_Creation.xlsx");
    String sheetName = "init_auction";

    int sheetRowCount = reader.getRowCount(sheetName);

    System.out.println("Row Count>>>" + sheetRowCount);

    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);

      String argument4 = reader.getCellData(sheetName, 4, i);

      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }

    }

  @Test
  public void init_ReverseParallelAuction() throws InterruptedException, AWTException, ParseException {

    String command, argument1, argument2 = null, argument3 = null;

    XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Reverse_Parallel_Creation.xlsx");
    String sheetName = "init_auction";

    int sheetRowCount = reader.getRowCount(sheetName);

    System.out.println("Row Count>>>" + sheetRowCount);

    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);

      String argument4 = reader.getCellData(sheetName, 4, i);

      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }

    }

  @Test
  public void init_ReverseSerialAuction() throws InterruptedException, AWTException, ParseException {

    String command, argument1, argument2 = null, argument3 = null;

    XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Reverse_Serial_Creation.xlsx");
    String sheetName = "init_auction";

    int sheetRowCount = reader.getRowCount(sheetName);

    System.out.println("Row Count>>>" + sheetRowCount);

    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);

      String argument4 = reader.getCellData(sheetName, 4, i);

      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }

    }

  @Test
  public void init_SR_NoPQC_Ques_NoScore() throws InterruptedException, AWTException, ParseException {

    String command, argument1, argument2 = null, argument3 = null;

    XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/SR_NoPQC_Ques_NoScore.xlsx");
    String sheetName = "init";

    int sheetRowCount = reader.getRowCount(sheetName);

    System.out.println("Row Count>>>" + sheetRowCount);

    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);

      String argument4 = reader.getCellData(sheetName, 4, i);

      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }

    }

  @Test
  public void init_SR_PQC_Ques_ScoringAtQuestion() throws InterruptedException, AWTException, ParseException {

    String command, argument1, argument2 = null, argument3 = null;

    XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/SR_PQC_Ques_ScoringAtQuestion.xlsx");
    String sheetName = "init";

    int sheetRowCount = reader.getRowCount(sheetName);

    System.out.println("Row Count>>>" + sheetRowCount);

    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);
      String argument4 = reader.getCellData(sheetName, 4, i);

      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }

    }

  @Test
  public void init_SR_PQC_Ques_ScoringAtSection() throws InterruptedException, AWTException, ParseException {

    String command, argument1, argument2 = null, argument3 = null;

    XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/SR_PQC_Ques_ScoringAtSection.xlsx");
    String sheetName = "init";

    int sheetRowCount = reader.getRowCount(sheetName);

    System.out.println("Row Count>>>" + sheetRowCount);

    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);

      String argument4 = reader.getCellData(sheetName, 4, i);

      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }

    }

  @Test
  public void init_SourcingRequest() throws InterruptedException, AWTException, ParseException {

    String command, argument1, argument2 = null, argument3 = null;

    XlsReader reader = new XlsReader(
        "./src/main/java/com/sol/qa/testdata/SR 204 Pricing Creation_NoPQC_SingleCurrency.xlsx");
    String sheetName = "init";

    int sheetRowCount = reader.getRowCount(sheetName);
    for (int intRow = 2; intRow <= sheetRowCount; intRow++) {
      command = reader.getCellData(sheetName, 0, intRow);
      argument1 = reader.getCellData(sheetName, 1, intRow);
      String argument4 = reader.getCellData(sheetName, 4, intRow);
      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }

    }

  @Test
  public void init_Forward_Auction_NoPQC_SingleCurrency_211() throws InterruptedException, AWTException, ParseException {
    String command, argument1, argument2 = null, argument3 = null;
    XlsReader reader = new XlsReader(
        "./src/main/java/com/sol/qa/testdata/Forward_Auction_Creation/Forward_Auction_NoPQC_SingleCurrency.xlsx");
    String sheetName = "init";
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int intRow = 2; intRow <= sheetRowCount; intRow++) {
      command = reader.getCellData(sheetName, 0, intRow);
      argument1 = reader.getCellData(sheetName, 1, intRow);
      String argument4 = reader.getCellData(sheetName, 4, intRow);
      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);
      }

    }

  @Test
  public void init_Forward_Auction_NotApprovalPQC_MultiCurrency_211() throws InterruptedException, AWTException {
    String command, argument1, argument2 = null, argument3 = null;
    XlsReader reader = new XlsReader(
        "./src/main/java/com/sol/qa/testdata/Forward_Auction_Creation/Forward_Auction_NotApprovalPQC_MultiCurrency.xlsx");
    String sheetName = "init";
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int intRow = 2; intRow <= sheetRowCount; intRow++) {
      command = reader.getCellData(sheetName, 0, intRow);
      argument1 = reader.getCellData(sheetName, 1, intRow);
      String argument4 = reader.getCellData(sheetName, 4, intRow);
      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);
      }

    }

  @Test
  public void init_Forward_Auction_NotApprovalPQC_SingleCurrency_MultiCurrency_211()
      throws InterruptedException, AWTException {
    String command, argument1, argument2 = null, argument3 = null;
    XlsReader reader = new XlsReader(
        "./src/main/java/com/sol/qa/testdata/Forward_Auction_Creation/Forward_Auction_NotApprovalPQC_SingleCurrency.xlsx");
    String sheetName = "init";
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int intRow = 2; intRow <= sheetRowCount; intRow++) {
      command = reader.getCellData(sheetName, 0, intRow);
      argument1 = reader.getCellData(sheetName, 1, intRow);
      String argument4 = reader.getCellData(sheetName, 4, intRow);
      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);
      }

    }

  @Test
  public void init_Forward_Staggered_ApprovalPQC_SingleCurrency_211() throws InterruptedException, AWTException {
    String command, argument1, argument2 = null, argument3 = null;
    XlsReader reader = new XlsReader(
        "./src/main/java/com/sol/qa/testdata/Forward_Auction_Creation/Forward_Staggered_ApprovalPQC_SingleCurrency.xlsx");
    String sheetName = "init";
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int intRow = 2; intRow <= sheetRowCount; intRow++) {
      command = reader.getCellData(sheetName, 0, intRow);
      argument1 = reader.getCellData(sheetName, 1, intRow);
      String argument4 = reader.getCellData(sheetName, 4, intRow);
      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);
      }

    }

  @Test
  public void init_Forward_Auction_Parallel_ApprovalPQC_MultiCurrency_211()
      throws InterruptedException, AWTException {
    String command, argument1, argument2 = null, argument3 = null;
    XlsReader reader = new XlsReader(
        "./src/main/java/com/sol/qa/testdata/Forward_Auction_Creation/Forward_Auction_Parallel_ApprovalPQC_MultiCurrency.xlsx");
    String sheetName = "init";
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int intRow = 2; intRow <= sheetRowCount; intRow++) {
      command = reader.getCellData(sheetName, 0, intRow);
      argument1 = reader.getCellData(sheetName, 1, intRow);
      String argument4 = reader.getCellData(sheetName, 4, intRow);
      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);
      }

    }

  @Test
  public void init_Forward_Auction_NoPQC_MultiCurrency_211() throws InterruptedException, AWTException {
    String command, argument1, argument2 = null, argument3 = null;
    XlsReader reader = new XlsReader(
        "./src/main/java/com/sol/qa/testdata/Forward_Auction_Creation/Forward_Auction_NoPQC_MultiCurrency.xlsx");
    String sheetName = "init";
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int intRow = 2; intRow <= sheetRowCount; intRow++) {
      command = reader.getCellData(sheetName, 0, intRow);
      argument1 = reader.getCellData(sheetName, 1, intRow);
      String argument4 = reader.getCellData(sheetName, 4, intRow);
      initCommandProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);
      }

    }

  // @AfterMethod public void tearDown() { driver.quit(); }

  }
