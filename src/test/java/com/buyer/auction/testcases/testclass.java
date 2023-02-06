package com.buyer.auction.testcases;


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
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.TestUtil;
import com.sol.qa.util.XlsReader;
import java.awt.AWTException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;





public class testclass extends TestBase {
  BuyerHomePage buyerHomePage;
  BuyerLoginPage buyerLoginPage;
  WindowManager windowManger;
  CommandProcessor initCommandProcessor;
  PricingSheetProcessorCommand pricingSheetProcessor;
  TeamMemberProcessorCommand teamMemberProcessor;
  SupplierProcessorCommand supplierProcessor;
  QuestionnaireProcessorCommand questionnaireProcessor;
  DocumentProcessorCommand documentProcessor;
  PqcProcessorCommand pqcProcessor;
  EventRulesProcessorCommand eventRulesProcessor;
  public String eventName = "Forward Auction Supplier Participate";
  TeamMemberCreationPage teamPage;
  OverviewProcessorCommand overviewProcessor;
  private WindowManager windowManager;

  @BeforeMethod
  public void setUp() throws MalformedURLException, InterruptedException {
    initialization();
    buyerHomePage = new BuyerHomePage();
    buyerLoginPage = new BuyerLoginPage(getDriver());
    initCommandProcessor = new CommandProcessor();
    supplierProcessor = new SupplierProcessorCommand(windowManager);
    buyerLoginPage.successBuyerlogin(getProp().getProperty("buyerEmail"), getProp().getProperty("password"));
    pricingSheetProcessor = new PricingSheetProcessorCommand(windowManger);
    teamMemberProcessor = new TeamMemberProcessorCommand(this.windowManager);
    questionnaireProcessor = new QuestionnaireProcessorCommand();
    pqcProcessor = new PqcProcessorCommand(this.windowManager);
    eventRulesProcessor = new EventRulesProcessorCommand(windowManger);
    documentProcessor = new DocumentProcessorCommand(windowManger);
    overviewProcessor = new OverviewProcessorCommand(windowManger);
    }

  public WebElement sourcingEventList(String eventName) throws InterruptedException {
    Thread.sleep(10000);
    WebElement monitorPhase = getDriver().findElement(By.id("mat-tab-label-0-2"));
    monitorPhase.click();
    Thread.sleep(5000);
    WebElement overlaySourcingList = getDriver().findElement(By.id("table"));
    WebElement sourceSelectedEvent = overlaySourcingList
        .findElement(By.xpath("//*[contains(text(),'" + eventName + "')]"));
    return sourceSelectedEvent;
    }

  public WebElement navigationPricingSheet(String linkName) throws InterruptedException {
    Thread.sleep(2000);
    WebElement naviagtionItem = getDriver().findElement(By.xpath(
        "//*[@class ='breadcrumbs-navigation ng-star-inserted']"));
    WebElement navigationSelected = naviagtionItem.findElement(By.id("suppliersChevron"));
    return navigationSelected;
    }

  @Test(priority = 27)
  public void sr204_PricingSheet_Tc027() throws InterruptedException, AWTException {
    Thread.sleep(10000);
    getDriver().navigate().to("https://yamuna.krinati.co/admin/monitor-event/event/5095/over-view");
    getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    XlsReader reader = new XlsReader(
        "./src/main/java/com/sol/qa/testdata/Forward_Auction/Forward_Auction_Monitor.xlsx");
    String sheetName = "TC01_ValidateOverviewDetails";
    int sheetRowCount = reader.getRowCount(sheetName);
    System.out.println("sheetRowCount>>>>" + sheetRowCount);
    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      overviewProcessor.processCommand(reader, command, argument1, argument2, 
          argument3, argument4);
      }
    }

	@Test(priority = 2)
	public void ForwardAuction211MonitorPhase() throws InterruptedException, AWTException {
		// String argument2 = "";
	  getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.getPageLoadTimeout()));
		WebElement sourceTypeOptions = sourcingEventList(eventName);

		sourceTypeOptions.click();

		WebElement proceedToPricingSheet = navigationPricingSheet("PRICING SHEET");
		proceedToPricingSheet.click();
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		XlsReader reader = new XlsReader(
				"./src/main/java/com/sol/qa/testdata/Forward_Auction/Forward_Auction_NoPQC_MultiCurrency.xlsx");
		// String sheetName = "Pricing_Sheet_Change_Formula";
		String sheetName = "init_base";
		int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      initCommandProcessor.processCommand(reader, command, argument1, argument2,
          argument3, argument4);
      }
    }

	@Test(priority = 3)
	public void SR204_PricingSheet_TC003() throws InterruptedException, AWTException {
		// String argument2 = "";

		WebElement sourceTypeOptions = sourcingEventList(eventName);

		sourceTypeOptions.click();
		WebElement proceedToPricingSheet = navigationPricingSheet("PRICING SHEET");
		proceedToPricingSheet.click();

		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		XlsReader reader = new XlsReader(
				"./src/main/java/com/sol/qa/testdata/Forward_Auction/Forward_Staggered_ApprovalPQC_SingleCurrency.xlsx");
		// String sheetName = "Pricing_Sheet_Change_Formula";
		String sheetName = "TC03_ValidateDocument";
		int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      documentProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }
    }

	@Test(priority = 13)
	public void SR204_PricingSheet_TC013() throws InterruptedException, AWTException {
		// String argument2 = "";

		WebElement sourceTypeOptions = sourcingEventList(eventName);

		sourceTypeOptions.click();
		WebElement proceedToPricingSheet = navigationPricingSheet("PRICING SHEET");
		proceedToPricingSheet.click();

		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Sourcing_Request_Test.xlsx");
		// String sheetName = "Pricing_Sheet_Change_Formula";
		String sheetName = "Create_TeamMember";
		int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      teamMemberProcessor.processCommand(reader, command, argument1, argument2, argument3,
          argument4);
      }
    }

	@Test(priority = 16)
	public void SR204_PricingSheet_TC016() throws InterruptedException, AWTException {
		// String argument2 = "";

		WebElement sourceTypeOptions = sourcingEventList(eventName);

		sourceTypeOptions.click();
		WebElement proceedToPricingSheet = navigationPricingSheet("PRICING SHEET");
		proceedToPricingSheet.click();

		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		XlsReader reader = new XlsReader(
				"./src/main/java/com/sol/qa/testdata/Forward_Auction/Forward_Auction_NoPQC_SingleCurrency.xlsx");
		// String sheetName = "Pricing_Sheet_Change_Formula";
		String sheetName = "supplierbackup";
		int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      supplierProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);
      }
    }

	@Test(priority = 4)
	public void SR204_PricingSheet_TC004() throws InterruptedException, AWTException {
		// String argument2 = "";

		WebElement sourceTypeOptions = sourcingEventList(eventName);

		sourceTypeOptions.click();
		WebElement proceedToPricingSheet = navigationPricingSheet("PRICING SHEET");
		proceedToPricingSheet.click();

		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Sourcing_Request_Test.xlsx");
		// String sheetName = "Pricing_Sheet_Change_Formula";
		String sheetName = "test_data";
		int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      pricingSheetProcessor.processCommand(reader, command, argument1, argument2, argument3, 
          argument4);
      }
    }

	@Test(priority = 18)
	public void SR204_SourcingRequest_TC018() throws InterruptedException, AWTException, ParseException {
		// String argument2 = "";
		String argument3 = "";
		String argument4 = "";
		WebElement sourceTypeOptions = sourcingEventList(eventName);

		sourceTypeOptions.click();

		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Sourcing_Request_Test.xlsx");
		// String sheetName = "Pricing_Sheet_Change_Formula";
		String sheetName = "TC018_EventRulesVal";
		int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      eventRulesProcessor.processCommand(reader, command, argument1, argument2, argument3, 
          argument4);
      }
    }

	@Test(priority = 07)
	public void SR204_SourcingRequest_TC007() throws InterruptedException, AWTException {
		// String argument2 = "";
		String argument3 = "";
		String argument4 = "";
		WebElement sourceTypeOptions = sourcingEventList(eventName);

		sourceTypeOptions.click();
		WebElement proceedToPricingSheet = navigationPricingSheet("DOCUMENTS");
		proceedToPricingSheet.click();

		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Sourcing_Request_Test.xlsx");
		// String sheetName = "Pricing_Sheet_Change_Formula";
		String sheetName = "TC07_AssertToast_Test";
		int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      pricingSheetProcessor.processCommand(reader, command, argument1, argument2, argument3,
          argument4);
      }
    }

	@Test(priority = 19)
	public void SR204_PricingSheet_TC019() throws InterruptedException, AWTException {
		// String argument2 = "";

		WebElement sourceTypeOptions = sourcingEventList(eventName);

		sourceTypeOptions.click();
		WebElement proceedToPricingSheet = navigationPricingSheet("PRICING SHEET");
		proceedToPricingSheet.click();

		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Sourcing_Request_Test.xlsx");
		// String sheetName = "Pricing_Sheet_Change_Formula";
		String sheetName = "TC019_TranformCost";
		int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      pricingSheetProcessor.processCommand(reader, command, argument1, argument2, argument3,
          argument4);
      }
    }

	@Test(priority = 20)
	public void SR204_PricingSheet_TC020() throws InterruptedException, AWTException {
		// String argument2 = "";

		WebElement sourceTypeOptions = sourcingEventList(eventName);

		sourceTypeOptions.click();
		WebElement proceedToPricingSheet = navigationPricingSheet("PRICING SHEET");
		proceedToPricingSheet.click();

		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Sourcing_Request_Test.xlsx");
		// String sheetName = "Pricing_Sheet_Change_Formula";
		String sheetName = "TC020_InvalidFormula";
		int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      pricingSheetProcessor.processCommand(reader, command, argument1, argument2, 
          argument3, argument4);

      }
    }

	@Test(priority = 22)
	public void SR204_PricingSheet_TC022() throws InterruptedException, AWTException {
		// String argument2 = "";

		WebElement sourceTypeOptions = sourcingEventList(eventName);

		sourceTypeOptions.click();
		WebElement proceedToPricingSheet = navigationPricingSheet("PRICING SHEET");
		proceedToPricingSheet.click();

		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Sourcing_Request_Test.xlsx");
		// String sheetName = "Pricing_Sheet_Change_Formula";
		String sheetName = "TC22_transformEvent";
		int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      pricingSheetProcessor.processCommand(reader, command, argument1, argument2, 
          argument3, argument4);
      }
    }

	@Test(priority = 23)
	public void SR204_PricingSheet_TC023() throws InterruptedException, AWTException {
		// String argument2 = "";

		WebElement sourceTypeOptions = sourcingEventList(eventName);

		sourceTypeOptions.click();
		WebElement proceedToPricingSheet = navigationPricingSheet("PRICING SHEET");
		proceedToPricingSheet.click();

		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Sourcing_Request_Test.xlsx");
		// String sheetName = "Pricing_Sheet_Change_Formula";
		String sheetName = "TC23_transformWithAlternateBid";
		int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      pricingSheetProcessor.processCommand(reader, command, argument1, argument2, 
          argument3, argument4);
      }
    }

	@Test(priority = 5)
	public void SR204_PricingSheet_TC005() throws InterruptedException, AWTException {
		// String argument2 = "";

		WebElement sourceTypeOptions = sourcingEventList(eventName);

		sourceTypeOptions.click();
		WebElement proceedToPricingSheet = navigationPricingSheet("PRICING SHEET");
		proceedToPricingSheet.click();

		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Sourcing_Request_Test.xlsx");
		// String sheetName = "Pricing_Sheet_Change_Formula";
		String sheetName = "Create_TeamMember";
		int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      initCommandProcessor.processCommand(reader, command, argument1, argument2, 
          argument3, argument4);
      }
    }

	@Test(priority = 6)
	public void SR204_PricingSheet_TC006() throws InterruptedException, AWTException {
		// String argument2 = "";

		WebElement sourceTypeOptions = sourcingEventList(eventName);

		sourceTypeOptions.click();
		WebElement proceedToPricingSheet = navigationPricingSheet("PRICING SHEET");
		proceedToPricingSheet.click();

		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Sourcing_Request_Test.xlsx");
		// String sheetName = "Pricing_Sheet_Change_Formula";
		String sheetName = "TC15_CopyEvent";
		int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      eventRulesProcessor.processCommand(reader, command, argument1, argument2, 
          argument3, argument4);
      }
    }
	
	@Test(priority = 6)
	public void SR204_PricingSheet_TC_testdata() throws InterruptedException, AWTException {
		// String argument2 = "";

		WebElement sourceTypeOptions = sourcingEventList(eventName);

		sourceTypeOptions.click();
		WebElement proceedToPricingSheet = navigationPricingSheet("PRICING SHEET");
		proceedToPricingSheet.click();

		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Sourcing_Request_Test.xlsx");
		// String sheetName = "Pricing_Sheet_Change_Formula";
		String sheetName = "Create_PricingSheet";
		int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      initCommandProcessor.processCommand(reader, command, argument1, argument2, 
           argument3, argument4);
      }
    }

	@Test(priority = 1)
	public void SR204_PricingSheet_TC_001() throws InterruptedException, AWTException {
		// String argument2 = "";

		WebElement sourceTypeOptions = sourcingEventList(eventName);

		sourceTypeOptions.click();
		WebElement proceedToPricingSheet = navigationPricingSheet("PRICING SHEET");
		proceedToPricingSheet.click();

		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Sourcing_Request_Test.xlsx");
		String sheetName = "TC_ValidEventRules";
		int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      pricingSheetProcessor.processCommand(reader, command, argument1, argument2, 
          argument3, argument4);
      }
    }

	@Test(priority = 10)
	public void SR204_PricingSheet_TC_010() throws InterruptedException, AWTException, ParseException {
		// String argument2 = "";

		WebElement sourceTypeOptions = sourcingEventList(eventName);

		sourceTypeOptions.click();
		WebElement proceedToPricingSheet = navigationPricingSheet("PRICING SHEET");
		proceedToPricingSheet.click();

		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Sourcing_Request_Test.xlsx");
		// String sheetName = "Pricing_Sheet_Change_Formula";
		String sheetName = "Validate_PS_TC15";
		int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      eventRulesProcessor.processCommand(reader, command, argument1, argument2, argument3, 
          argument4);

      }
    }

	@Test(priority = 29)
	public void SR204_PricingSheet_TC_029() throws InterruptedException, AWTException {
		// String argument2 = "";

		WebElement sourceTypeOptions = sourcingEventList(eventName);

		sourceTypeOptions.click();
		WebElement proceedToPricingSheet = navigationPricingSheet("PRICING SHEET");
		proceedToPricingSheet.click();

		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Sourcing_Request_Test.xlsx");
		// String sheetName = "Pricing_Sheet_Change_Formula";
		String sheetName = "TC27_NoAlternateBid";
		int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      pricingSheetProcessor.processCommand(reader, command, argument1, argument2, 
          argument3, argument4);
      }
    }

	@Test(priority = 24)
	public void SR204_PricingSheet_TC_024() throws InterruptedException, AWTException {
		// String argument2 = "";

		WebElement sourceTypeOptions = sourcingEventList(eventName);

		sourceTypeOptions.click();
		WebElement proceedToPricingSheet = navigationPricingSheet("PRICING SHEET");
		proceedToPricingSheet.click();

		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Sourcing_Request_Test.xlsx");
		// String sheetName = "Pricing_Sheet_Change_Formula";
		String sheetName = "TC004_AssignSuppliers";
		int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, 3, i);
      String argument4 = reader.getCellData(sheetName, 4, i);
      pricingSheetProcessor.processCommand(reader, command, argument1, argument2,
          argument3, argument4);
      }
    }

	@Test(priority = 14)
	public void SR204_SourcingRequest_TC014() throws InterruptedException, AWTException, ParseException {
		// String argument2 = "";
		String argument3 = "";
		String argument4 = "";
		WebElement sourceTypeOptions = sourcingEventList(eventName);

		sourceTypeOptions.click();

		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Sourcing_Request_Test.xlsx");
		// String sheetName = "Pricing_Sheet_Change_Formula";
		String sheetName = "TC002_DuplicateLot";
		int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      eventRulesProcessor.processCommand(reader, command, argument1, argument2, argument3, 
          argument4);
      }
    }

  }
