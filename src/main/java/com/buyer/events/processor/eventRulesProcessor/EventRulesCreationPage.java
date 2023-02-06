package com.buyer.events.processor.eventRulesProcessor;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class EventRulesCreationPage {
  //private JavascriptExecutor js;
  private WebDriver driver;
  final int sleepTime = 1000;

  public EventRulesCreationPage(final WebDriver drivers) {
    this.driver = drivers;
    //js = (JavascriptExecutor) drivers;
    PageFactory.initElements(drivers, this);

    }

  public final WebDriver getDriver() {
    return this.driver;
    }

  @FindBy(xpath = "//input[contains(@formcontrolname,'event_name')]")
  private WebElement eventNameField;

  @FindBy(id = "bold-description")
  private WebElement boldDesc;

  @FindBy(id = "italic-description")
  private WebElement italicDesc;

  @FindBy(id = "underline-description")
  private WebElement lineDesc;

  @FindBy(id = "editor")
  private WebElement eventDesc;

  @FindBy(id = "fontSizeSelector-description")
  private WebElement fontSize;

  @FindBy(xpath = "//input[contains(@formcontrolname,'categories')]")
  private WebElement categoryTree;

  @FindBy(id = "searchCategory")
  private WebElement categorySearch;

  @FindBy(xpath = "//input[contains(@formcontrolname,'event_type')]")
  private WebElement eventType;

  @FindBy(xpath = "//input[contains(@formcontrolname,'revise_lot_price_type')]")
  private WebElement reviseLotPriceType;

  @FindBy(css = "tr:nth-of-type(5) .mat-form-field-wrapper")
  private WebElement copyPreviousEvent;

  @FindBy(xpath = "//input[contains(@formcontrolname,'questionaire_scored')]")
  private WebElement questionaireScored;

  @FindBy(id = "proceedEvent")
  private WebElement proceedButton;

  @FindBy(id = "confirmCategory")
  private WebElement confirmCategoryButton;

  @FindBy(id = "multiCurrency")
  private WebElement multiCurrency;

  @FindBy(xpath = "//input[contains(@formcontrolname,'prequalifying_criteria')]")
  private WebElement pqcEvent;

  @FindBy(xpath = "//input[contains(@formcontrolname,'auction_type')]")
  private WebElement auctionType;

  @FindBy(xpath = "//*[@id = 'auctionStartDatePicker']")
  private WebElement calendarButton;

  @FindBy(xpath = "//button[@aria-label = 'Previous month']")
  private WebElement previousMonth;

  @FindBy(xpath = "//button[@aria-label = 'Next month']")
  private WebElement nextMonth;

  @FindBy(xpath = "//button[@aria-label = 'Choose month and year']")
  private WebElement monthAndYear;

  @FindBy(id = "responseDueDatePicker")
  private WebElement openCalendar;

  @FindBy(xpath = "//input[contains(@formcontrolname,'start_time_of_auction')]")
  private WebElement startTimeOfAuction;

  @FindBy(xpath = "//input[contains(@formcontrolname,'lot_closure_sequence')]")
  private WebElement lotClosureSequence;

  @FindBy(xpath = "//input[contains(@formcontrolname,'run_time_for_first_lot')]")
  private WebElement runTimeForFirstLot;

  @FindBy(xpath = "//input[contains(@formcontrolname,'run_time_for_all_lot')]")
  private WebElement runTimeAllLots;

  @FindBy(xpath = "//input[contains(@formcontrolname,'subsequent_lot_timer')]")
  private WebElement subsequentLotTimer;

  @FindBy(xpath = "//input[contains(@formcontrolname,'live_auction_feedback')]")
  private WebElement liveAuctionFeedback;

  @FindBy(xpath = "//input[contains(@formcontrolname,'supplier_rank_that_triggers_overtime')]")
  private WebElement rankTriggerOvertime;

  @FindBy(xpath = "//input[contains(@formcontrolname,'overtime_trigger_if_bid_received')]")
  private WebElement overtimeTriggerBidReceived;

  @FindBy(xpath = "//input[contains(@formcontrolname,'remaining_time_if_overtime_triggered')]")
  private WebElement remainingTimeIfOvertimeTriggered;

  @FindBy(xpath = "//*[@class = 'mat-icon-rtl-mirror mat-icon material-icons' and text() = 'chevron_right']")
  private WebElement chevronRightIcon;

  @FindBy(xpath = "//input[contains(@formcontrolname,'tie_bids')]")
  private WebElement treatmentOfTieBids;

  @FindBy(xpath = "//button[@id = 'publishEvent']")
  private WebElement publishEvent;

  @FindBy(xpath = "//*[@id='supplier-part']/div/div[2]/div[1]/div[1]")
  private WebElement auctionStartMsg;

  @FindBy(xpath = "//input[contains(@formcontrolname,'due_time_for_response')]")
  private WebElement dueTimeForResponse;

  @FindBy(id = "questionnaire")
  private WebElement questionaireCheck;

  @FindBy(id = "pricingSheet")
  private WebElement pricingSheetSelect;

  @FindBy(id = "pricingSheet-input")
  private WebElement pricingSheetCheck;

  @FindBy(xpath = "//input[contains(@formcontrolname,'toCurrency')]")
  private WebElement fromCurrencyElement;

  @FindBy(xpath = "//input[contains(@formcontrolname,'rate')]")
  private WebElement exchangeRateElement;

  @FindBy(css = "[data-column = 'add']")
  private WebElement addCircleOutline;

  @FindBy(id = "feedback-toggle-no")
  private  List<WebElement> feedbackNoToggle;

  @FindBy(id = "feedback-toggle-yes")
  private List<WebElement> feedbackYesToggle;

  @FindBy(xpath = "//*[@class = 'form-table']/tr[5]/td[2]/mat-form-field/div/div/div/mat-icon")
  private WebElement copyFromPreviousEvent;

  @FindBy(xpath = "//*[@class = 'form-table']/tr[5]/td[2]/mat-form-field/div/div/div/img")
  private WebElement copyFromPreviousImg;

  @FindBy(xpath = "//*[@class = 'form-table']/tr[6]/td[2]/mat-form-field/div/div/div/mat-icon")
  private WebElement copyContent;

  @FindBy(xpath = "//input[contains(@formcontrolname,'copy_from_previous_events')]")
  private WebElement copyFromPreviousEvents;

  @FindBy(xpath = "//*[contains(@formcontrolname,'events_to_be_copied')]")
  private WebElement eventsToBeCopied;

  @FindBy(xpath = "/html/body/fuse-root/fuse-main/mat-sidenav-container/mat-sidenav-content/div/div/div/fuse-content/app-event-rules"
      + "/div/div[2]/mat-sidenav-container/mat-sidenav-content/div/div[3]/div/div/form/div/table[2]/tr[10]/td[2]/mat-checkbox")
  private WebElement rankCheckBox;

  @FindBy(xpath = "/html/body/fuse-root/fuse-main/mat-sidenav-container/mat-sidenav-content/div/div/div/fuse-content/app-event-rules/div/div[2]/"
      + "mat-sidenav-container/mat-sidenav-content/div/div[3]/div/div/form/div/table[2]/tr[11]/td[2]/mat-checkbox")
  private WebElement bestBidCheck;

  @FindBy(xpath = "//input[contains(@formcontrolname,'currency')]")
  private WebElement eventCurrency;

  @FindBy(id = "placeInitialBids")
  private WebElement placeInitialBid;

  public final WebElement getEventNameField() {
    return eventNameField;
    }

  public final WebElement getBoldDesc() {
    return boldDesc;
    }

  public final WebElement getItalicDesc() {
    return italicDesc;
    }

  public  final WebElement getLineDesc() {
    return lineDesc;
    }

  public final WebElement getEventDesc() {
    return eventDesc;
    }

  public final WebElement getFontSize() {
    return fontSize;
    }

  public final WebElement getCategoryTree() {
    return categoryTree;
    }

  public final WebElement getCategorySearch() {
    return categorySearch;
    }

  public final WebElement getEventType() {
    return eventType;
    }

  public final WebElement getReviseLotPriceType() {
    return reviseLotPriceType;
    }

  public final WebElement getCopyPreviousEvent() {
    return copyPreviousEvent;
    }

  public final WebElement getQuestionaireScored() {
    return questionaireScored;
    }

  public final WebElement getProceedButton() {
    return proceedButton;
    }

  public final WebElement getConfirmCategoryButton() {
    return confirmCategoryButton;
    }

  public final WebElement getMultiCurrency() {
    return multiCurrency;
    }

  public final WebElement getPqcEvent() {
    return pqcEvent;
    }

  public final WebElement getAuctionType() {
    return auctionType;
    }

  public final WebElement getCalendarButton() {
    return calendarButton;
    }

  public final WebElement getPreviousMonth() {
    return previousMonth;
    }

  public final WebElement getNextMonth() {
    return nextMonth;
    }

  public final WebElement getMonthAndYear() {
    return monthAndYear;
    }

  public final WebElement getOpenCalendar() {
    return openCalendar;
    }

  public final WebElement getStartTimeOfAuction() {
    return startTimeOfAuction;
    }


  public final WebElement getLotClosureSequence() {
    return lotClosureSequence;
    }

  public final WebElement getRunTimeForFirstLot() {
    return runTimeForFirstLot;
    }

  public final WebElement getRunTimeAllLots() {
    return runTimeAllLots;
    }

  public final WebElement getSubsequentLotTimer() {
    return subsequentLotTimer;
    }

  public final WebElement getLiveAuctionFeedback() {
    return liveAuctionFeedback;
    }

  public final WebElement getRankTriggerOvertime() {
    return rankTriggerOvertime;
    }

  public final WebElement getOvertimeTriggerBidReceived() {
    return overtimeTriggerBidReceived;
    }

  public final WebElement getRemainingTimeIfOvertimeTriggered() {
    return remainingTimeIfOvertimeTriggered;
    }

  public final WebElement getChevronRightIcon() {
    return chevronRightIcon;
    }

  public final WebElement getTreatmentOfTieBids() {
    return treatmentOfTieBids;
    }

  public final WebElement getPublishEvent() {
    return publishEvent;
    }

  public final WebElement getAuctionStartMsg() {
    return auctionStartMsg;
    }

  public final WebElement getDueTimeForResponse() {
    return dueTimeForResponse;
    }

  public  final WebElement getQuestionaireCheck() {
    return questionaireCheck;
    }

  public final WebElement getPricingSheetSelect() {
    return pricingSheetSelect;
    }

  public final WebElement getPricingSheetCheck() {
    return pricingSheetCheck;
    }

  public final WebElement getFromCurrencyElement() {
    return fromCurrencyElement;
    }

  public final WebElement getExchangeRateElement() {
    return exchangeRateElement;
    }

  public final WebElement getAddCircleOutline() {
    return addCircleOutline;
    }

  public final List<WebElement> getFeedbackNoToggle() {
    return feedbackNoToggle;
    }

  public final List<WebElement> getFeedbackYesToggle() {
    return feedbackYesToggle;
    }

  public final WebElement getCopyFromPreviousEvent() {
    return copyFromPreviousEvent;
    }

  public final WebElement getCopyFromPreviousImg() {
    return copyFromPreviousImg;
    }

  public final WebElement getCopyContent() {
    return copyContent;
    }

  public final WebElement getCopyFromPreviousEvents() {
    return copyFromPreviousEvents;
    }

  public final WebElement getEventsToBeCopied() {
    return eventsToBeCopied;
    }

  public final WebElement getRankCheckBox() {
    return rankCheckBox;
    }

  public final  WebElement getBestBidCheck() {
    return bestBidCheck;
    }

  public final WebElement getEventCurrency() {
    return eventCurrency;
    }

  public final WebElement getPlaceInitialBid() {
    return placeInitialBid;
    }


  public final WebElement optionType(final String categoryType) throws InterruptedException {
    Thread.sleep(sleepTime);
    WebElement optionSelect = driver.findElement(By.xpath("//*[contains(text(),'" + categoryType + "')]"));
    return optionSelect;
    }

  public final WebElement questionCheckBox(final String selectCheckValue) {
    WebElement multiChoice = driver.findElement(By.id("questionnaire-input"));
    return multiChoice;
    }

  public final WebElement selectOption(final String selectedChoice) {

    WebElement overlayType = driver.findElement(By.className("cdk-overlay-container"));
    // Get all options in a list
    WebElement singleOptions = overlayType
        .findElement(By.xpath("//*[@class = 'mat-option-text' and contains(text(),'" + selectedChoice + "')]"));

    return singleOptions;
    }

  public final WebElement selectCheckBoxOption(final String selectedChoice) {

    WebElement overlayType = driver.findElement(By.className("cdk-overlay-container"));
    // Get all options in a list
    WebElement singleOptions = overlayType
        .findElement(By.xpath("//*[@value='" + selectedChoice + "']/mat-pseudo-checkbox"));

    return singleOptions;
    }

  public final WebElement auctionDate(final String extendDate, final String auctionDate) {
    System.out.println("extendDate" + extendDate);
    WebElement selectAuctionDate;
    String sourceString = StringUtils.stripStart(auctionDate, "0");
    System.out.println("sourceString>>>" + sourceString);
    if (extendDate.equals("0")) {
      selectAuctionDate = driver.findElement(By.xpath(
          "//*[@class = 'mat-calendar-body-cell-content mat-focus-indicator mat-calendar-body-today']"));
      } else {
      selectAuctionDate = driver.findElement(
          By.xpath("//*[@class = 'mat-calendar-body-cell-content mat-focus-indicator' and contains(text(),'"
              + sourceString + "')]"));
      }
    return selectAuctionDate;
    }

  public final  WebElement customDate(final String auctionDate) {
    System.out.println("auctionDate>>>" + auctionDate);
    String sourceString = StringUtils.stripStart(auctionDate, "0");
    System.out.println("sourceString>>>" + sourceString);
    WebElement selectAuctionDate = driver.findElement(
        By.xpath("//*[@class = 'mat-calendar-body-cell-content mat-focus-indicator' and contains(text(),'"
            + sourceString + "')]"));

    return selectAuctionDate;
    }

  public final  List<WebElement> competitiveDisplay() {

    List<WebElement> listCompetitiveDisplay = driver.findElements(By.id("feedback-toggle-no"));

    return listCompetitiveDisplay;
    }
  }
