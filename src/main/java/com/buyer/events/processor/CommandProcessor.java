package com.buyer.events.processor;

import com.buyer.events.processor.auctionConsole.AuctionConsoleProcessorCommand;
import com.buyer.events.processor.documentProcessor.DocumentProcessorCommand;
import com.buyer.events.processor.eventRulesProcessor.EventListProcessorCommand;
import com.buyer.events.processor.eventRulesProcessor.EventRulesAsserterCommand;
import com.buyer.events.processor.eventRulesProcessor.EventRulesProcessorCommand;
import com.buyer.events.processor.monitorSupplierProcessor.MonitorSupplierProcessorCommand;
import com.buyer.events.processor.overviewProcessor.OverviewProcessorCommand;
import com.buyer.events.processor.pqcProcessor.PqcProcessorCommand;
import com.buyer.events.processor.pricingSheetProcessor.PricingSheetProcessorCommand;
import com.buyer.events.processor.questionnaireProcessor.QuestionnaireProcessorCommand;
import com.buyer.events.processor.responseAnalysis.ResponseAnalysisProcessorCommand;
import com.buyer.events.processor.supplierProcessor.SupplierProcessorCommand;
import com.buyer.events.processor.teamMemberProcessor.TeamMemberProcessorCommand;
import com.buyer.events.processor.utils.Logger;
import com.sol.qa.base.TestBase;
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.TestUtil;
import com.sol.qa.util.XlsReader;
import com.supplier.events.processor.SupplierLoginProcessor;
import com.supplier.events.processor.auctionConsole.SupplierAuctionConsoleCommand;
import com.supplier.events.processor.pqcProcessor.SupplierPqcProcessorCommand;
import com.supplier.processors.SupplierSideEventListCommand;

import java.awt.AWTException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
* This class is Parent Processor for all other Child Processor.
* Each Processor accessing the processCommand from ITabCommandProcessor Interface.
* ProcessCommand will have switch cases.
* @author  YamunaS
*/
public class CommandProcessor extends TestBase {
  private Logger logger;
  private WindowManager windowManger;
  private EventRulesProcessorCommand eventRulesProcessor;
  private SupplierProcessorCommand supplierProcessor;
  private DocumentProcessorCommand documentProcessor;
  private PricingSheetProcessorCommand pricingSheetProcessor;
  private TeamMemberProcessorCommand teamMemberProcessor;
  private EventListProcessorCommand eventListProcessor;
  private EventRulesAsserterCommand assertEventRules;
  private SupplierSideEventListCommand supplierReponseProcessor;
  private SupplierAuctionConsoleCommand supplierAuctionConsole;
  private ResponseAnalysisProcessorCommand responseAnalysisProcessor;
  private AuctionConsoleProcessorCommand auctionConsoleProcessor;
  private QuestionnaireProcessorCommand questionnaireProcessor = new QuestionnaireProcessorCommand();
  private PqcProcessorCommand pqcProcessor;
  private SupplierLoginProcessor supplierLoginProcessor = new SupplierLoginProcessor();
  private OverviewProcessorCommand overviewProcessor;
  private MonitorSupplierProcessorCommand monitorSupplierProcessor;
  private SupplierPqcProcessorCommand supplierPqcProcessor;
  private String command = null;
  private String argument1 = null;
  private String argument2 = null;
  private String argument3 = null;
  private String argument4 = null;
  private final int count3 = 3;
  private final int count4 = 4;
  final int sleepTime = 1000;
  private Map<String, String> refMap;



/**
* This constructor have child processor constructor.
*/
  public CommandProcessor() {
    this.refMap = new HashMap<String, String>();
    this.logger = new Logger();
    this.windowManger = new WindowManager();

    }

  /**
   * This method have XLs_Reader and sheetName as Parameter
   * It has access teamMember child processor with number of row count with given SheetName.
   */
  public void createTeamMemberFunc(final XlsReader reader, final String sheetName) throws InterruptedException, AWTException {
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);
      argument2 = reader.getCellData(sheetName, 2, i);
      argument3 = reader.getCellData(sheetName, count3, i);
      argument4 = reader.getCellData(sheetName, count4, i);
      teamMemberProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }
    }


  /**
   * This method have XLs_Reader and sheetName as Parameter
   * It has access Document child processor with number of row count with given SheetName.
   *
   */
  public void invokeDocument(final XlsReader reader, final String sheetName) throws InterruptedException, AWTException {
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);
      argument3 = reader.getCellData(sheetName, count3, i);
      argument2 = reader.getCellData(sheetName, 2, i);
      argument4 = reader.getCellData(sheetName, count4, i);
      documentProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);
      }
    }

  /**
   * This method have XLs_Reader and sheetName as Parameter
   * It has access Supplier child processor with number of row count with given SheetName.
   */
  public void createSupplierFunc(final XlsReader reader, final String sheetName) throws InterruptedException, AWTException {
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);
      argument2 = reader.getCellData(sheetName, 2, i);
      argument3 = reader.getCellData(sheetName, count3, i);
      argument4 = reader.getCellData(sheetName, count4, i);
      supplierProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);
      }
    }

  /**
   * This method have XLs_Reader and sheetName as Parameter
   * It has access Questionnaire child processor with number of row count with given SheetName.
   */
  public void createQuestionnaireFunc(final XlsReader reader, final String sheetName) throws InterruptedException, AWTException {
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);
      argument2 = reader.getCellData(sheetName, 2, i);
      argument3 = reader.getCellData(sheetName, count3, i);
      argument4 = reader.getCellData(sheetName, count4, i);
      questionnaireProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);
      }
    }

  /**
   * This method have XLs_Reader and sheetName as Parameter
   * It has access PQC child processor with number of row count with given SheetName.
   */
  public void createPQCFunc(final XlsReader reader, final String sheetName) throws InterruptedException, AWTException {
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);
      argument2 = reader.getCellData(sheetName, 2, i);
      argument3 = reader.getCellData(sheetName, count3, i);
      argument4 = reader.getCellData(sheetName, count4, i);
      pqcProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }
    }

  /**
   * This method have XLs_Reader and sheetName as Parameter
   * It has access OverView child processor with number of row count with given SheetName.
   */
  public void invokeOverView(final XlsReader reader, final String sheetName) throws InterruptedException, AWTException {

    int sheetRowCount = reader.getRowCount(sheetName);
    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);
      argument2 = reader.getCellData(sheetName, 2, i);
      argument3 = reader.getCellData(sheetName, count3, i);
      argument4 = reader.getCellData(sheetName, count4, i);
      overviewProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }
    }

  /**
   * This method have XLs_Reader and sheetName as Parameter
   * It has access OverView child processor with number of row count with given SheetName.
   */
  public void invokeMonitorSupplier(final XlsReader reader, final String sheetName) throws InterruptedException, AWTException {
    System.out.println("invokeMonitorSupplier>>>" + sheetName);
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);
      argument2 = reader.getCellData(sheetName, 2, i);
      argument3 = reader.getCellData(sheetName, count3, i);
      argument4 = reader.getCellData(sheetName, count4, i);
      monitorSupplierProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }
    }

  /**
   * This method have XLs_Reader and sheetName as Parameter
   * It has access supplier pqc child processor with number of row count with given SheetName.
   */
  public void invokeSupplierPqc(final XlsReader reader, final String sheetName) throws InterruptedException, AWTException {
    System.out.println("invokeMonitorSupplier>>>" + sheetName);
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);
      argument2 = reader.getCellData(sheetName, 2, i);
      argument3 = reader.getCellData(sheetName, count3, i);
      argument4 = reader.getCellData(sheetName, count4, i);
      supplierPqcProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }
    }




  /**
   * This method have XLs_Reader and sheetName as Parameter
   * It has access Supplier Response child processor with number of row count with given SheetName.
   */
  public void invokeSupplierResponse(final XlsReader reader, final String sheetName) throws InterruptedException, AWTException {

    int sheetRowCount = reader.getRowCount(sheetName);
    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);
      argument2 = reader.getCellData(sheetName, 2, i);
      argument3 = reader.getCellData(sheetName, count3, i);
      argument4 = reader.getCellData(sheetName, count4, i);
      supplierReponseProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }
    }

  /**
   * This method have XLs_Reader and sheetName as Parameter
   * It has access Supplier Response child processor with number of row count with given SheetName.
   */
  public void invokeResponseAnalysis(final XlsReader reader, final String sheetName) throws InterruptedException, AWTException {

    int sheetRowCount = reader.getRowCount(sheetName);
    System.out.println("Response Analysis >>>" + sheetRowCount);
    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);
      argument2 = reader.getCellData(sheetName, 2, i);
      argument3 = reader.getCellData(sheetName, count3, i);
      argument4 = reader.getCellData(sheetName, count4, i);
      responseAnalysisProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }
    }

  /**
   * This method have XLs_Reader and sheetName as Parameter
   * It has access Supplier Response child processor with number of row count with given SheetName.
   */
  public void invokeSupplierAuctionConsole(final XlsReader reader, final String sheetName) throws InterruptedException, AWTException {

    int sheetRowCount = reader.getRowCount(sheetName);
    System.out.println("Response Analysis >>>" + sheetRowCount);
    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);
      argument2 = reader.getCellData(sheetName, 2, i);
      argument3 = reader.getCellData(sheetName, count3, i);
      argument4 = reader.getCellData(sheetName, count4, i);
      supplierAuctionConsole.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }
    }

  /**
   * This method have XLs_Reader and sheetName as Parameter
   * It has access Supplier Response child processor with number of row count with given SheetName.
   */
  public void invokeAuctionConsole(final XlsReader reader, final String sheetName) throws InterruptedException, AWTException {

    int sheetRowCount = reader.getRowCount(sheetName);
    System.out.println("Response Analysis >>>" + sheetRowCount);
    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);
      argument2 = reader.getCellData(sheetName, 2, i);
      argument3 = reader.getCellData(sheetName, count3, i);
      argument4 = reader.getCellData(sheetName, count4, i);
      auctionConsoleProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }
    }

  /**
   * This method have XLs_Reader and sheetName as Parameter
   * It has access Supplier Response child processor with number of row count with given SheetName.
   */
  public void invokeNextSheet(final String xlsName, final String sheetName) throws InterruptedException, AWTException {

    XlsReader reader = new XlsReader("./src/main/java/com/sol/qa/testdata/Forward_Auction/" + xlsName);
    System.out.println("Reader >>>" + reader);
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);
      argument2 = reader.getCellData(sheetName, 2, i);
      argument3 = reader.getCellData(sheetName, count3, i);
      argument4 = reader.getCellData(sheetName, count4, i);
      processCommand(reader, command, argument1, argument2, argument3, argument4);

      }
    }

  /**
   * This method have XLs_Reader and sheetName as Parameter
   * It has access SupplierLogin child processor with number of row count with given SheetName.
   */
  public void loginSupplier(final XlsReader reader, final String sheetName) throws InterruptedException, AWTException {
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);
      argument2 = reader.getCellData(sheetName, 2, i);
      argument3 = reader.getCellData(sheetName, count3, i);
      argument4 = reader.getCellData(sheetName, count4, i);
      supplierLoginProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }
    }

  /**
   * This method have XLs_Reader and sheetName as Parameter
   * It has access Pricing Sheet child processor with number of row count with given SheetName.
   */
  public void invokePricingSheet(final XlsReader reader, final String sheetName) throws InterruptedException, AWTException {
    int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);
      argument2 = reader.getCellData(sheetName, 2, i);
      argument3 = reader.getCellData(sheetName, count3, i);
      argument4 = reader.getCellData(sheetName, count4, i);
      pricingSheetProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }
    }

  /**
  * This method will click the Publish Button.
  */
  public void publishButtonFunc() {
    WebDriverWait wait = new WebDriverWait(this.windowManger.getActiveDriver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    WebElement publishEvent = this.windowManger.getActiveDriver().findElement(By.id("publishEvent"));
    if (publishEvent.isEnabled()) {
      wait.until(ExpectedConditions.elementToBeClickable(publishEvent));
      publishEvent.click();

      }
    }


  /**
   * This method will receive currentURL and extract event id and store in refMap.
   */
  public void extractEventId(final String eventKey) {
    System.out.println("<<<<<Extract Event Id>>>>" + eventKey);
    System.out.println("Current URL>>>" + this.windowManger.getActiveDriver().getCurrentUrl());
    try {
      URL currentURL = new URL(this.windowManger.getActiveDriver().getCurrentUrl());
      System.out.println("GETPATH>>>>>" + currentURL.getPath());
      String separator = "/";
      String[] urlSplitValue = currentURL.getPath().split(Pattern.quote(separator));
      this.refMap.put(eventKey, urlSplitValue[count4]);
      System.out.println("Test Map>>>" + this.refMap.get(eventKey));
      } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      }

    }

  /**
   * This method have XLs_Reader and sheetName as Parameter
   * It has access Event Rules child processor with number of row count with given SheetName.
   * @throws ParseException
   */
  public void invokeEventRules(final XlsReader reader, final String sheetName) throws InterruptedException, AWTException {
    int sheetRowCount = reader.getRowCount(sheetName);

    for (int i = 2; i <= sheetRowCount; i++) {
      command = reader.getCellData(sheetName, 0, i);
      argument1 = reader.getCellData(sheetName, 1, i);
      argument2 = reader.getCellData(sheetName, 2, i);
      argument3 = reader.getCellData(sheetName, count3, i);
      argument4 = reader.getCellData(sheetName, count4, i);
      eventRulesProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);
      }
    }

  /**
   * This method will navigate to eventId specified with window id.
   */
  public void navigateBuyerURL(final String windowId, final String eventId, final String page) throws InterruptedException {
    String replaceCurlyBraces = eventId.replaceAll("[{}]", "");
    Thread.sleep(sleepTime);
    System.out.println("replaceCurlyBraces>>>" + replaceCurlyBraces);
    windowManger.navigateBuyerURL(windowId, this.refMap.get(replaceCurlyBraces), page);
    }


  /**
   * This method will navigate to supplier url.
   */
  public void navigateSupplierURL(final String windowId, final String eventId, final String supplierId) throws InterruptedException {
    System.out.println("EventId Supplier >>>" + eventId);
    String replaceCurlyBraces = eventId.replaceAll("[{}]", "");
   // windowManger.navigateSupplierURL(windowId, eventId);
    windowManger.navigateSupplierURL(windowId, this.refMap.get(replaceCurlyBraces), supplierId);
    }

  /**
   *This method have XLs_Reader , Command , Argument1 , Argument2 , Argument3 and Argument4 as Parameter
   *It have switch command and its corresponding method.
   * @throws ParseException
   */
  public void processCommand(final XlsReader reader, final String cmd, final String arg1, final String arg2, final String arg3, final String arg4)
      throws InterruptedException, AWTException {
    switch (cmd) {
      case "createNewEvent":
        eventListProcessor.createNewFunction();
        break;
      case "invokeEventRules":
        invokeEventRules(reader, arg1);
        break;
      case "invokePricingSheet":
        invokePricingSheet(reader, arg1);
        break;
      case "createTeamMember":
        createTeamMemberFunc(reader, arg1);
        break;
      case "createDocument":
        invokeDocument(reader, arg1);
        break;
      case "createSupplier":
        createSupplierFunc(reader, arg1);
        break;
      case "createQuestionnaire":
        createQuestionnaireFunc(reader, arg1);
        break;
      case "createPQC":
        createPQCFunc(reader, arg1);
        break;
      case "invokeOverview":
        invokeOverView(reader, arg1);
        break;
      case "invokeMonitorSupplier":
        invokeMonitorSupplier(reader, arg1);
        break;
      case "invokeSupplierPqc":
        invokeSupplierPqc(reader, arg1);
        break;
      case "Assert":
        assertEventRules.eventsButtonAssertion(arg1);
        break;
      case "invokeSupplierResponse":
        invokeSupplierResponse(reader, arg1);
        break;
      case "invokeNextSheet":
        invokeNextSheet(arg1, arg2);
        break;
      case "invokeResponseAnalysis":
        invokeResponseAnalysis(reader, arg1);
        break;
      case "publishEvent":
        publishButtonFunc();
        break;
      case "loginSupplier":
        loginSupplier(reader, arg1);
        break;
      case "openWindow":
        try {
          this.windowManger.openNewWindow(arg1);
          } catch (IOException e) {
          e.printStackTrace();
          }
        break;
      case "switchWindow":
        this.windowManger.switchWindow(arg1);
        overviewProcessor = new OverviewProcessorCommand(windowManger);
        eventRulesProcessor = new EventRulesProcessorCommand(windowManger);
        teamMemberProcessor = new TeamMemberProcessorCommand(windowManger);
        documentProcessor = new DocumentProcessorCommand(windowManger);
        eventListProcessor = new EventListProcessorCommand(windowManger);
        pricingSheetProcessor = new PricingSheetProcessorCommand(windowManger);
        supplierProcessor = new SupplierProcessorCommand(windowManger);
        assertEventRules = new EventRulesAsserterCommand(windowManger);
        pqcProcessor = new PqcProcessorCommand(windowManger);
        monitorSupplierProcessor = new MonitorSupplierProcessorCommand(windowManger);
        auctionConsoleProcessor = new AuctionConsoleProcessorCommand(windowManger);
        break;
      case "visitBuyerURL":
        this.windowManger.visitBuyerURL(arg1, arg2, arg3);
        responseAnalysisProcessor = new ResponseAnalysisProcessorCommand(windowManger);
        break;
      case "visitSupplierURL":
        this.windowManger.visitSupplierURL(arg1, arg2, arg3);
        supplierReponseProcessor = new SupplierSideEventListCommand(windowManger);
        supplierPqcProcessor = new SupplierPqcProcessorCommand(windowManger);
        supplierAuctionConsole = new SupplierAuctionConsoleCommand(windowManger);
        break;
      case "navigateURL":
        this.refMap.put(arg2, arg3);
        System.out.println("test_Event>>>>" + this.refMap.get(arg2));
        this.windowManger.navigateURL(arg1, arg3, arg4);
        break;
      case "navigateBuyerURL":
        navigateBuyerURL(arg1, arg2, arg3);
        break;
      case "navigateSupplierToEvent":
        //navigateSupplierURL(arg1, arg2, arg3);
        String replaceCurlyBraces = arg1.replaceAll("[{}]", "");
        supplierReponseProcessor.openEventFromList(this.refMap.get(replaceCurlyBraces));
        break;
      case "invokeAuctionConsole":
        invokeAuctionConsole(reader, arg1);
        break;
      case "invokeSupplierAuctionConsole":
        invokeSupplierAuctionConsole(reader, arg1);
        break;
      case "currentURL":
        this.windowManger.currentURL(arg1);
        break;
      case "closeWindow":
        this.windowManger.closeWindow(arg1);
        break;
      case "extractEventId":
        extractEventId(arg1);
        break;
      case "Assertion":
        switch (arg1) {
          case "ToastMessage":
            assertEventRules.assertToastMsg(arg2);
            break;
          default:
            System.out.println("no switch match");
          }
        break;
      default:
        System.out.println("no match");
      }
    }
  }
