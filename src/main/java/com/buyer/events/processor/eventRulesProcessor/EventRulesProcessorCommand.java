package com.buyer.events.processor.eventRulesProcessor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import com.buyer.events.processor.ITabCommandProcessor;
import com.buyer.events.processor.pricingSheetProcessor.PricingSheetProcessorCommand;
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.TestUtil;
import com.sol.qa.util.XlsReader;


public class EventRulesProcessorCommand  implements ITabCommandProcessor {
  private EventRulesCreationPage eventRulesPage;
  private EventRulesAsserterCommand assertEventRules;
  private PricingSheetProcessorCommand pricingSheetProcessor;
  final int sleepTime = 2000;
  private WindowManager windowManager;
  private JavascriptExecutor js;

  public EventRulesProcessorCommand(final WindowManager windowManger) {
    this.windowManager = windowManger;
    System.out.println("windowManager EventRules>>>" + windowManger);
    js = (JavascriptExecutor) this.driver();
    pricingSheetProcessor = new PricingSheetProcessorCommand(windowManger);
    eventRulesPage = new EventRulesCreationPage(this.driver());
    assertEventRules = new EventRulesAsserterCommand(windowManger);
    }

  private RemoteWebDriver driver() {
    return  this.windowManager.getActiveDriver();
    }

  /**
   *This method have XLs_Reader , command , argument1 , argument2 , argument3 and argument4 as Parameter
   *It have switch command and its corresponding method.
   * @throws InterruptedException
   * @throws ParseException
   */
  public void processCommand(final XlsReader reader, final String command, final String arg1, final  String arg2,
      final String arg3, final String arg4)
      throws InterruptedException {
    //eventRulesPage = new EventRulesCreationPage(this.driver());
    //assertEventRules = new EventRulesAsserterCommand(this.driver());
    switch (command) {
      case "EventName":
        setEventName(arg1);
        break;
      case "NavigationTab":
        pricingSheetProcessor.performNavigation(arg1);
        break;
      case "EventDescription":
        setEventDescription(arg1);
        break;
      case "CategorySearch":
        performCategorySearch(arg1);
        break;
      case "EventType":
        setEventType(arg1);
        break;
      case "CopyToContent":
        setCopyToContent(arg1);
        break;
      case "PQCCriteria":
        setPQCType(arg1);
        break;
      case "SupplierReviseLotPrice":
        supplierReviseLotPrice(arg1);
        break;
      case "CopyFromPreviousEvent":
        copyFromPreviousEvent(arg1);
        break;
      case "CustomField":
        switch (arg1) {
          case "DateInput":
            customDateInput(arg2, arg3);
            break;
          case "DateTime":
            customTimeInput(arg2, arg3);
            break;
          case "MultiLine":
            customMultiLine(arg2, arg3);
            break;
          case "SingleChoice":
            customSingleChoice(arg2, arg3);
            break;
          case "MultiChoice":
            setMultipleChoice(arg2);
            break;
          case "SingleLine":
            customeSingleLine(arg2, arg3);
            break;
          default:
            System.out.println("no match");
          }
        break;
      case "AuctionType":
        setAuctionType(arg1);
        break;
      case "StartDateofAuction":
        setStartDate(arg1);
        break;
      case "StartTimeofAuction":
        try {
          setStartTime(arg1);
          } catch (ParseException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          }
        break;
      case "LotClosureSequence":
        setLotClosure(arg1);
        break;
      case "RunTimeForFirstLot":
        setRunTimeFirstLot(arg1);
        break;
      case "RunTimeForAllLots":
        setRunTimeForAllLots(arg1);
        break;
      case "MainAction":
        pricingSheetProcessor.performMainAction(arg1);
        break;
      case "EachSubsequentLotToCloseIn":
        setSubsequentLotToClose(arg1);
        break;
      case "DisplayCompetitiveFeedbackToSuppliers":
        setCompetitiveFeedback(arg1);
        break;
      case "CompetitiveFeedbackToSuppliers":
        setCompetitiveFeedbackToSuppliers(arg1);
        break;
      case "ChangeInSupplierRankThatTriggersOvertime":
        setSupplierRank(arg1);
        break;
      case "OvertimeTriggeredIfBidReceivedInFinal":
        setOvertimeTrigger(arg1);
        break;
      case "RemainingTimeIfOvertimeIsTriggered":
        setRemainingTimeTrigger(arg1);
        break;
      case "TreatmentOfTieBids":
        setTreatmentTieBid(arg1);
        break;
      case "EventCurrency":
        setEventCurrency(arg1);
        break;
      case "Assert":
        assertEventRules.eventsButtonAssertion(arg1);
        break;
      case "proceedButton":
        performProceed();
        break;
      case "DueDateSupplierResponse":
        supplierResDueDateTime(arg1, arg2);
        break;
      case "Questionnaire":
        setQuestionnaire(arg1);
        break;
      case "PricingSheet":
        setPricingSheet(arg1);
        break;
      case "ScoringOfQuestionnaire":
        scoreQuestionFunc(arg1);
        break;
      case "MultiCurrencyEvent":
        performMultiCurrency(arg1);
        break;
      case "MultiCurrencyData":
        fromCurrencyToExchangeRate(reader, arg1);
        break;
      case "AddMultiCurrencyRow":
        addMultiCurrency(arg1);
        break;
      case "RankAndBestBid":
        performRankAndBestBid();
        break;
      case "PlaceInitialBidDuringPreBid":
        performPlaceInitialBid(arg1);
        break;
      case "Assertion":
        assertionCases(arg1, arg2);
        break;
      default:
        System.out.println("no match");
      }
    }

  /**
   * This method enter the given value for Event Name Field.
   */
  public void setEventName(final String eventName) {
    sendkeys(this.driver(), eventRulesPage.getEventNameField(), TestUtil.getTimeOut(), eventName);
    }

  /**
   * This method enter the given value for Event Description Field.
   */
  public void setEventDescription(final String eventDescription) {
    sendkeys(this.driver(), eventRulesPage.getEventDesc(), TestUtil.getTimeOut(), eventDescription);
    }

  /**
   * This method enter the given value for Category Search Field.
   */
  public void performCategorySearch(final String categorySearchTree) throws InterruptedException {
    clickOn(this.driver(), eventRulesPage.getCategoryTree(), TestUtil.getTimeOut());
    clickOn(this.driver(), eventRulesPage.getCategorySearch(), TestUtil.getTimeOut());
    sendkeys(this.driver(), eventRulesPage.getCategorySearch(), TestUtil.getTimeOut(), categorySearchTree);
    Thread.sleep(sleepTime);
    System.out.println("Category Search >>>" + categorySearchTree);
    WebElement selectedCategoryItem = eventRulesPage.optionType(categorySearchTree);
    clickOn(this.driver(), selectedCategoryItem, TestUtil.getTimeOut());
    clickOn(this.driver(), eventRulesPage.getConfirmCategoryButton(), TestUtil.getTimeOut());
    }

  /**
   * This method enter the given value for Event Type Field.
   */
  public void setEventType(final String eventTypeValue) throws InterruptedException {
    Thread.sleep(sleepTime);
    clickOn(this.driver(), eventRulesPage.getEventType(), TestUtil.getTimeOut());
    WebElement selectedAuctionType = eventRulesPage.selectOption(eventTypeValue);
    clickOn(this.driver(), selectedAuctionType, TestUtil.getTimeOut());
    }

  /**
   * This method enter the given value for Copy To Content Field.
   */
  public void setCopyToContent(final String copyContent) {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", eventRulesPage.getEventsToBeCopied());
    clickOn(this.driver(), eventRulesPage.getEventsToBeCopied(), TestUtil.getTimeOut());
    WebElement selectedEventCopyType = eventRulesPage.selectCheckBoxOption(copyContent);
    clickOn(this.driver(), selectedEventCopyType, TestUtil.getTimeOut());
    this.driver().findElement(By.className("cdk-overlay-backdrop")).click();
    }

  /**
   * This method enter the whether value for Score Questionnaire dropdown.
   */
  public void scoreQuestionFunc(final String scoreValue) throws InterruptedException {
    Thread.sleep(sleepTime);
    clickOn(this.driver(), eventRulesPage.getQuestionaireScored(), TestUtil.getTimeOut());
    WebElement selectedQuestionType = eventRulesPage.selectOption(scoreValue);
    clickOn(this.driver(), selectedQuestionType, TestUtil.getTimeOut());
    }

  /**
   * This method enter the given value for Place Initial Bid Field.
   */
  public void performPlaceInitialBid(final String yesOrNo) throws InterruptedException {
    if (yesOrNo.equalsIgnoreCase("Yes")) {
      if (eventRulesPage.getPlaceInitialBid().getAttribute("src").contains("assets/icons/toggle_no.svg")) {
        eventRulesPage.getPlaceInitialBid().click();
        }

      }
    }

  /**
   * This method enter the given value for Currency to Exchange Rate Field.
   */
  public void fromCurrencyToExchangeRate(final XlsReader reader, final String sheetName) throws InterruptedException {
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int i = 0; i <= sheetRowCount - 2; i++) {
      WebElement exchangeCurrency = this.driver()
          .findElement(By.cssSelector("[data-testid = 'row" + i + "'] [data-column = 'searchCurrency']"));
      ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView(true);", exchangeCurrency);
      clickOn(this.driver(), exchangeCurrency, TestUtil.getTimeOut());
      WebElement currencyOptions = eventRulesPage
          .selectOption(reader.getCellData(sheetName, "From Currency", i + 2));
      ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView(true);", currencyOptions);
      clickOn(this.driver(), currencyOptions, TestUtil.getTimeOut());
      WebElement exchangeRate = this.driver()
          .findElement(By.cssSelector("[data-testid = 'row" + i + "'] [data-column = 'rate']"));
      ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView(true);", exchangeRate);
      sendkeys(this.driver(), exchangeRate, TestUtil.getTimeOut(), reader.getCellData(sheetName, "Exchange Rate", i + 2));
      }
    }

  /**
   * This method enter the toggle whether the multicurrency field.
   */
  public void performMultiCurrency(final String yesOrNo) throws InterruptedException {
    Thread.sleep(sleepTime);
    if (yesOrNo.equalsIgnoreCase("Yes")) {
      ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", eventRulesPage.getMultiCurrency());
      if (eventRulesPage.getMultiCurrency().getAttribute("src").contains("assets/icons/toggle_no.svg")) {
        eventRulesPage.getMultiCurrency().click();
        }
      } else {
      ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", eventRulesPage.getMultiCurrency());
      if (eventRulesPage.getMultiCurrency().getAttribute("src").contains("assets/icons/toggle_yes.svg")) {
        eventRulesPage.getMultiCurrency().click();
        }
      }
    }

  /**
   * This method enter the given row count for multicurrency field.
   */
  public void addMultiCurrency(final String addCurrencyRow) {
    int addCurrencyInt = Integer.parseInt(addCurrencyRow);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", eventRulesPage.getAddCircleOutline());
    for (int i = 1; i <= addCurrencyInt; i++) {
      ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", eventRulesPage.getAddCircleOutline());
      eventRulesPage.getAddCircleOutline().click();
      }
    }

  /**
   * This method enter the given value for Rank And Best Bid field.
   */
  public void performRankAndBestBid() throws InterruptedException {
    System.out.println("perform Rank and Best Bid");
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", eventRulesPage.getRankCheckBox());
    eventRulesPage.getRankCheckBox().click();
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", eventRulesPage.getBestBidCheck());
    eventRulesPage.getBestBidCheck().click();
    }

  /**
   * This method enter the given value for pqc type field.
   */
  public void setPQCType(final String pqcType) {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", eventRulesPage.getPqcEvent());
    clickOn(this.driver(), eventRulesPage.getPqcEvent(), TestUtil.getTimeOut());
    WebElement pqcTypeOptions = eventRulesPage.selectOption(pqcType);
    clickOn(this.driver(), pqcTypeOptions, TestUtil.getTimeOut());
    }

  /**
   * This method enter the given value for competitive feedback to supplier field.
   */
  public void setCompetitiveFeedbackToSuppliers(final String competitiveType) {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", eventRulesPage.getLiveAuctionFeedback());
    clickOn(this.driver(), eventRulesPage.getLiveAuctionFeedback(), TestUtil.getTimeOut());
    WebElement competitiveOptions = eventRulesPage.selectOption(competitiveType);
    clickOn(this.driver(), competitiveOptions, TestUtil.getTimeOut());
    }

  /**
   * This method enter the given value for Event Currency Type field.
   */
  public void setEventCurrency(final String eventCurrencyType) {

    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", eventRulesPage.getEventCurrency());
    clickOn(this.driver(), eventRulesPage.getEventCurrency(), TestUtil.getTimeOut());
    WebElement eventCurrencyOptions = eventRulesPage.selectOption(eventCurrencyType);
    clickOn(this.driver(), eventCurrencyOptions, TestUtil.getTimeOut());
    }

  /**
   * This method enter the given value for Supplier Revise Lot Price field.
   */
  public void supplierReviseLotPrice(final String reviseValue) throws InterruptedException {
    clickOn(this.driver(), eventRulesPage.getReviseLotPriceType(), TestUtil.getTimeOut());
    WebElement selectedReviseType = eventRulesPage.selectOption(reviseValue);
    clickOn(this.driver(), selectedReviseType, TestUtil.getTimeOut());
    }

  /**
   * This method enter the given value for Copy From Previous Event field.
   */
  public void copyFromPreviousEvent(final String previousEventValue) throws InterruptedException {
    clickOn(this.driver(), eventRulesPage.getCopyFromPreviousImg(), TestUtil.getTimeOut());
    WebElement selectedpreviousEvent = eventRulesPage.selectOption(previousEventValue);
    clickOn(this.driver(), selectedpreviousEvent, TestUtil.getTimeOut());
    }

  /**
   * This method enter the given value for calendar picker for custom date.
   */
  public String calendarPicker(final Date customDate) throws InterruptedException {
    System.out.println("Calendar Picker >>>" + customDate);
    SimpleDateFormat formatYear = new SimpleDateFormat("YYYY");
    String selectYear = formatYear.format(customDate).toUpperCase();
    SimpleDateFormat formatMonth = new SimpleDateFormat("MMM");
    System.out.println("formatMonth>>>" + formatMonth);
    String selectMonth = formatMonth.format(customDate).toUpperCase();
    System.out.println("selectMonth>>>" + selectMonth);
    SimpleDateFormat formatDate = new SimpleDateFormat("dd");
    String selectDate = formatDate.format(customDate);
    Thread.sleep(sleepTime);
    WebDriverWait wait = new WebDriverWait(this.driver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    wait.until(ExpectedConditions.visibilityOf(eventRulesPage.getMonthAndYear()));

    String monthYeatVal = eventRulesPage.getMonthAndYear().getText();
    System.out.println("MonthYeatVal>>> " + monthYeatVal);
    String month = monthYeatVal.split(" ")[0].trim();
    System.out.println("month>>>" + month);
    String year = monthYeatVal.split(" ")[1].trim();
    System.out.println("year>>>" + year);
    while (!(month.equals(selectMonth) && year.equals(selectYear))) {
      System.out.println("while Loop >>>" + selectMonth);
      clickOn(this.driver(), eventRulesPage.getNextMonth(), TestUtil.getTimeOut());
      monthYeatVal = eventRulesPage.getMonthAndYear().getText();
      Thread.sleep(2);
      System.out.println("Next month >>>" + eventRulesPage.getNextMonth());
      System.out.println("while loop monthYeatVal" + eventRulesPage.getNextMonth().getText());
      month = monthYeatVal.split(" ")[0].trim();
      year = monthYeatVal.split(" ")[1].trim();
      System.out.println("year while loop>>>" + year);
      }
    return selectDate;
    }

  /**
   * This method enter the given value for Custom Date field.
   */
  public void customDateInput(final String cssSelectorId, final String customValue) throws InterruptedException {
    WebElement customWebElement = this.driver().findElement(By.cssSelector(cssSelectorId));
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", customWebElement);
    clickOn(this.driver(), customWebElement, TestUtil.getTimeOut());
    Date customDate = new Date(customValue);
    String selectedDate = calendarPicker(customDate);
    WebElement selectedAuctionDate = eventRulesPage.customDate(selectedDate);
    clickOn(this.driver(), selectedAuctionDate, TestUtil.getTimeOut());
    }

  /**
   * This method enter the given value for Supplier Response Due Date Time field.
   */
  public void supplierResDueDateTime(final String dueDate, final String dueTime) throws InterruptedException {
    clickOn(this.driver(), eventRulesPage.getOpenCalendar(), TestUtil.getTimeOut());
    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    String[] dateSplit = dueDate.split("\\+");
    String extendDate = dateSplit[1];
    Date currentDate = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(currentDate);
    c.add(Calendar.DATE, Integer.parseInt(extendDate));
    Date currentDatePlusOne = c.getTime();
    Date customDate = new Date(df.format(currentDatePlusOne));
    String selectedDate = calendarPicker(customDate);
    WebElement selectedAuctionDate = eventRulesPage.auctionDate(extendDate, selectedDate);
    clickOn(this.driver(), selectedAuctionDate, TestUtil.getTimeOut());
    supplierResDueTime(dueTime);
    }

  /**
   * This method enter the given value for Custom Auction Time field.
   */
  public void customTimeInput(final String cssSelectorId, final String customValue) {
    WebElement customWebElement = this.driver().findElement(By.cssSelector(cssSelectorId));
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", customWebElement);
    clickOn(this.driver(), customWebElement, TestUtil.getTimeOut());
    WebElement selectedAuctionTime = eventRulesPage.selectOption(customValue);
    clickOn(this.driver(), selectedAuctionTime, TestUtil.getTimeOut());
    }

  /**
   * This method enter the given value for Supplier Response Due Time field.
   */
  public void supplierResDueTime(final String dueDateTime) {
    String[] timeSplit = dueDateTime.split("\\+");
    String extendTime = timeSplit[1];
    SimpleDateFormat df = new SimpleDateFormat("hh.mm a");
    Date d = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(d);
    cal.add(Calendar.MINUTE, Integer.parseInt(extendTime));
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", eventRulesPage.getDueTimeForResponse());
    clickOn(this.driver(), eventRulesPage.getDueTimeForResponse(), TestUtil.getTimeOut());
    eventRulesPage.getDueTimeForResponse().clear();
    sendkeys(this.driver(), eventRulesPage.getDueTimeForResponse(), TestUtil.getTimeOut(), df.format(cal.getTime()));
    }

  /**
   * This method enter the given value whether the questionnaire included or not in checkbox.
   */
  public void setQuestionnaire(final String questionaire) {
    if (questionaire.equals("Yes")) {
      clickOn(this.driver(), eventRulesPage.getQuestionaireCheck(), TestUtil.getTimeOut());
      }
    }

  /**
   * This method enter the given value whether the pricing sheet included or not in checkbox.
   */
  public void setPricingSheet(final String pricing) {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", eventRulesPage.getPricingSheetSelect());
    if (pricing.equalsIgnoreCase("Yes")) {
      System.out.println("yes if");
      if (eventRulesPage.getPricingSheetCheck().getAttribute("aria-checked").equalsIgnoreCase("false")) {
        eventRulesPage.getPricingSheetSelect().click();
        }
      }
    if (pricing.equalsIgnoreCase("No")) {
      System.out.println("no if");
      if (eventRulesPage.getPricingSheetCheck().getAttribute("aria-checked").equalsIgnoreCase("true")) {
        eventRulesPage.getPricingSheetSelect().click();
        }
      }
    }

  /**
   * This method enter the given value in custom value for multiple line question field.
   */
  public void customMultiLine(final String cssSelectorId, final String customValue) {
    WebElement customWebElement = this.driver().findElement(By.cssSelector(cssSelectorId));
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", customWebElement);
    clickOn(this.driver(), customWebElement, TestUtil.getTimeOut());
    sendkeys(this.driver(), customWebElement, TestUtil.getTimeOut(), customValue);
    }

  /**
   * This method enter the given value in custom value for single choice question field.
   */
  public void customSingleChoice(final String cssSelectorId, final String customValue) {
    WebElement customSingleWebElement = this.driver().findElement(By.cssSelector(cssSelectorId));
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", customSingleWebElement);
    clickOn(this.driver(), customSingleWebElement, TestUtil.getTimeOut());
    WebElement selectedWantItCustom = eventRulesPage.selectOption(customValue);
    clickOn(this.driver(), selectedWantItCustom, TestUtil.getTimeOut());
    }

  /**
   * This method enter the given value in custom value for multiple choice question field.
   * @throws InterruptedException
   */
  public void setMultipleChoice(final String multiChoice) throws InterruptedException {
    WebElement categoryChoice = eventRulesPage.optionType(multiChoice);
    clickOn(this.driver(), categoryChoice, TestUtil.getTimeOut());
    }

  /**
   * This method enter the given value in custom value for Single Line question field.
   */
  public void customeSingleLine(final String cssSelectorId, final String customValue) {
    WebElement customWebElement = this.driver().findElement(By.cssSelector(cssSelectorId));
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", customWebElement);
    clickOn(this.driver(), customWebElement, TestUtil.getTimeOut());
    sendkeys(this.driver(), customWebElement, TestUtil.getTimeOut(), customValue);
    }

  /**
   * This method enter the given value in Auction Type field.
   */
  public void setAuctionType(final String auctionType) {
    clickOn(this.driver(), eventRulesPage.getAuctionType(), TestUtil.getTimeOut());
    WebElement selectedAuctionType = eventRulesPage.selectOption(auctionType);
    clickOn(this.driver(), selectedAuctionType, TestUtil.getTimeOut());
    }

  /**
   * This method enter the given value in Treatment Tie Bid field.
   */
  public void setTreatmentTieBid(final String tieBid) {
    clickOn(this.driver(), eventRulesPage.getTreatmentOfTieBids(), TestUtil.getTimeOut());
    WebElement selectedTieOption = eventRulesPage.selectOption(tieBid);
    clickOn(this.driver(), selectedTieOption, TestUtil.getTimeOut());
    }

  /**
   * This method enter the given value in Start Date For Auction field.
   */
  public void setStartDate(final String startDateAuction) throws InterruptedException {
    clickOn(this.driver(), eventRulesPage.getCalendarButton(), TestUtil.getTimeOut());
    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    String[] dateSplit = startDateAuction.split("\\+");
    String extendDate = dateSplit[1];
    Date currentDate = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(currentDate);
    c.add(Calendar.DATE, Integer.parseInt(extendDate));
    Date currentDatePlusOne = c.getTime();
    System.out.println("currentDatePlusOne>>>>" + currentDatePlusOne);
    Date customDate = new Date(df.format(currentDatePlusOne));
    System.out.println("customDate>>>" + customDate);
    String selectedDate = calendarPicker(customDate);
    WebElement selectedAuctionDate = eventRulesPage.auctionDate(extendDate, selectedDate);
    clickOn(this.driver(), selectedAuctionDate, TestUtil.getTimeOut());
    }

  /**
   * This method enter the given value in Start Time For Auction field.
   */
  public void setStartTime(final String auctionTime) throws ParseException, InterruptedException {
    String[] timeSplit = auctionTime.split("\\+");
    String extendTime = timeSplit[1];
    SimpleDateFormat df = new SimpleDateFormat("hh.mm a");
    Date d = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(d);
    cal.add(Calendar.MINUTE, Integer.parseInt(extendTime));
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", eventRulesPage.getStartTimeOfAuction());
    clickOn(this.driver(), eventRulesPage.getStartTimeOfAuction(), TestUtil.getTimeOut());
    eventRulesPage.getStartTimeOfAuction().clear();
    sendkeys(this.driver(), eventRulesPage.getStartTimeOfAuction(), TestUtil.getTimeOut(), df.format(cal.getTime()));
    }

  /**
   * This method enter the given value in Lot Closure Sequence field.
   */
  public void setLotClosure(final String lotClosureSeq) {
    clickOn(this.driver(), eventRulesPage.getLotClosureSequence(), TestUtil.getTimeOut());
    WebElement selectedLotClosure = eventRulesPage.selectOption(lotClosureSeq);
    clickOn(this.driver(), selectedLotClosure, TestUtil.getTimeOut());
    }

  /**
   * This method enter the given value in Run Time For All Lots field.
   */
  public void setRunTimeForAllLots(final String runTimeForAllLot) {
    sendkeys(this.driver(), eventRulesPage.getRunTimeAllLots(), TestUtil.getTimeOut(), convertStringToInt(runTimeForAllLot));
    }

  /**
   * This method enter the given value in Run Time First Lot field.
   */
  public void setRunTimeFirstLot(final String runTimeFirstLot) {
    sendkeys(this.driver(), eventRulesPage.getRunTimeForFirstLot(), TestUtil.getTimeOut(), convertStringToInt(runTimeFirstLot));
    }

  /**
   * This method enter the given value in Each Subsequent Lot to Closure field.
   */
  public void setSubsequentLotToClose(final String eachSubsequentLotClose) {
    sendkeys(this.driver(), eventRulesPage.getSubsequentLotTimer(), TestUtil.getTimeOut(), convertStringToInt(eachSubsequentLotClose));
    }

  /**
   * This method enter the given value in competitive feedback to supplier field.
   */
  public void setCompetitiveFeedback(final String compFeedbackToSup) throws InterruptedException {
    this.driver().manage().timeouts().implicitlyWait(TestUtil.getImplicitWait(), TimeUnit.SECONDS);
    if (compFeedbackToSup.equalsIgnoreCase("No")) {
      if (eventRulesPage.getFeedbackYesToggle().size() > 0) {
        ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", eventRulesPage.getFeedbackYesToggle());
        eventRulesPage.getFeedbackYesToggle().get(0).click();
        }
      }
    if (compFeedbackToSup.equalsIgnoreCase("Yes")) {
      if (eventRulesPage.getFeedbackNoToggle().size() > 0) {
        ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", eventRulesPage.getFeedbackNoToggle().get(0));
        eventRulesPage.getFeedbackNoToggle().get(0).click();
        }
      }
    }

  /**
   * This method enter the given value in supplier trigger overtime field.
   */
  public void setSupplierRank(final String supRankTriggerOverTime) {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", eventRulesPage.getRankTriggerOvertime());
    sendkeys(this.driver(), eventRulesPage.getRankTriggerOvertime(), TestUtil.getTimeOut(), convertStringToInt(supRankTriggerOverTime));
    }

  /**
   * This method enter the given value in overtime trigger field.
   */
  public void setOvertimeTrigger(final String overtimeTriggerBidReceived) {
    sendkeys(this.driver(), eventRulesPage.getOvertimeTriggerBidReceived(), TestUtil.getTimeOut(),
        convertStringToInt(overtimeTriggerBidReceived));
    }

  /**
   * This method enter the given value in remaining time trigger field.
   */
  public void setRemainingTimeTrigger(final String remainTimeIfOvertimeTrigger) {
    sendkeys(this.driver(), eventRulesPage.getRemainingTimeIfOvertimeTriggered(), TestUtil.getTimeOut(),
        convertStringToInt(remainTimeIfOvertimeTrigger));
    }


  /**
   * This method clicks on proceed button.
   */
  public void performProceed() {
    boolean proceed = eventRulesPage.getProceedButton().isEnabled();
    AssertJUnit.assertTrue(proceed);
    clickOn(this.driver(), eventRulesPage.getProceedButton(), TestUtil.getTimeOut());
    }

  /**
   * This method convert the String into Integer.
   */
  public String convertStringToInt(final String strToInt) {
    StringTokenizer runTime = new StringTokenizer(strToInt, ".");
    String intToStr = runTime.nextToken();
    return intToStr;
    }

  /**
   * This method have assertion cases.
   */
  public void assertionCases(final String arg1, final String arg2) throws InterruptedException {
    switch (arg1) {
      case "ToastMessage":
        assertEventRules.assertToastMsg(arg2);
        break;
      case "DisplayCompetitive":
        assertEventRules.competitiveFeedBack();
        break;
      case "CopyPreviousContentInfo":
        assertEventRules.copyPreviousContentInfo(arg2);
        break;
      case "CopyContentInfo":
        assertEventRules.copyContentInfo(arg2);
        break;
      case "IsContentCopyEnable":
        assertEventRules.assertContentCopy();
        break;
      default:
        System.out.println("no switch match" + arg1);
      }
    }

  public static final void sendkeys(final RemoteWebDriver driver, final WebElement element, final int timeout, final String value) {
    new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
    element.sendKeys(value);
    }

  public static final void clickOn(final RemoteWebDriver driver, final WebElement element, final int timeout) {
    new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
    element.click();
    }
  }
