package com.buyer.events.processor.overviewProcessor;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class OverviewCreationPage {
  private JavascriptExecutor js;
  private WebDriver driver;

  public OverviewCreationPage(final WebDriver drivers) {
    this.driver = drivers;
    js = (JavascriptExecutor) drivers;
    PageFactory.initElements(drivers, this);
    }

  public final WebDriver getDriver() {
    return driver;
    }

  @FindBy(css = "[data-test ='event-name']")
  private WebElement eventValueName;

  @FindBy(css = "[data-test ='event-id']")
  private WebElement eventId;

  @FindBy(css = "[data-test ='event-description']")
  private WebElement eventDescription;

  @FindBy(css = "[data-test ='event-category-name']")
  private WebElement categoryTree;

  @FindBy(css = "[data-test ='event-type']")
  private WebElement eventType;

  @FindBy(css = "[data-test ='copy-prev-event']")
  private WebElement copyFromPrevious;

  @FindBy(css = "[data-test ='predecessor-event']")
  private WebElement predecessorEvent;

  @FindBy(css = "[data-test ='pqc-value']")
  private WebElement pqcValue;

  @FindBy(css = "[data-test ='auction-type']")
  private WebElement auctionType;

  @FindBy(xpath = "(//*[@data-test ='auction-start-time']/div)")
  private WebElement auctionStartTime;

  @FindBy(xpath = "(//*[@data-test ='auction-start-time']/div[2])")
  private WebElement auctionzoneTime;

  @FindBy(css = "[data-test ='auction-pre-bid']")
  private WebElement auctionPreBid;

  @FindBy(css = "[data-test ='auction-lot-closure-sequence']")
  private WebElement auctionLotClosureSeqence;

  @FindBy(css = "[data-test ='auction-subsequent-lot-time']")
  private WebElement auctionSubsequentLotTime;

  @FindBy(css = "[data-test ='auction-runtime-forall-lots']")
  private WebElement auctionRuntimeForAllLots;

  @FindBy(css = "[data-test ='auction-run-time-first']")
  private WebElement auctionRunTimeFirst;

  @FindBy(css = "[data-test ='event-currency']")
  private WebElement eventCurrency;

  @FindBy(css = "[data-test ='multi-currency-value']")
  private WebElement isMultiCurrencyValue;

  @FindBy(css = "[data-test ='competitive-feedback']")
  private WebElement competitiveFeedback;

  @FindBy(css = "[data-test ='trigger-overtime-value']")
  private WebElement triggerOvertimeValue;

  @FindBy(css = "[data-test ='trigger-overtime-final']")
  private WebElement overtimeBidFinal;

  @FindBy(css = "[data-test ='set-remaining-time']")
  private WebElement setRemainingTime;

  @FindBy(css = "[data-test ='treatment-of-tie-bid']")
  private WebElement treatmentTieBid;

  @FindBy(css = "[data-test ='edit-team-btn']")
  private WebElement editTeamMember;

  @FindBy(css = "[data-test ='add-member-btn']")
  private WebElement addStakeholderMember;

  @FindBy(id = "selectTeamMember")
  private WebElement addTeamMember;

  @FindBy(xpath = "(//input[@placeholder = 'Search User...'])")
  private WebElement addMemberSearchUser;

  @FindBy(id = "selectTeamMember-panel")
  private List<WebElement> teamMemberPanel;

  @FindBy(xpath = "(//*[contains(@class,'no-docs')])")
  private WebElement noDocsUpload;

  @FindBy(css = "[data-test ='overview-upload-file']")
  private WebElement overViewUploadFile;

  @FindBy(css = "[data-test ='overview-upload-link']")
  private WebElement overViewUploadLink;

  @FindBy(css = "[data-test ='overview-download-all']")
  private WebElement overViewDownloadAll;

  public final WebElement getEventValueName() {
    return eventValueName;
    }

  public final WebElement getEventId() {
    return eventId;
    }

  public final WebElement getEventDescription() {
    return eventDescription;
    }

  public final WebElement getCategoryTree() {
    return categoryTree;
    }

  public final WebElement getEventType() {
    return eventType;
    }

  public final WebElement getCopyFromPrevious() {
    return copyFromPrevious;
    }

  public final WebElement getPredecessorEvent() {
    return predecessorEvent;
    }

  public final WebElement getPqcValue() {
    return pqcValue;
    }

  public final WebElement getAuctionType() {
    return auctionType;
    }

  public final WebElement getAuctionStartTime() {
    return auctionStartTime;
    }

  public final WebElement getAuctionzoneTime() {
    return auctionzoneTime;
    }

  public final WebElement getAuctionPreBid() {
    return auctionPreBid;
    }

  public final WebElement getAuctionLotClosureSeqence() {
    return auctionLotClosureSeqence;
    }

  public final WebElement getAuctionRuntimeForAllLots() {
    return auctionRuntimeForAllLots;
    }

  public final WebElement getEventCurrency() {
    return eventCurrency;
    }

  public final WebElement getIsMultiCurrencyValue() {
    return isMultiCurrencyValue;
    }

  public final WebElement getCompetitiveFeedback() {
    return competitiveFeedback;
    }

  public final WebElement getTriggerOvertimeValue() {
    return triggerOvertimeValue;
    }

  public  final WebElement getOvertimeBidFinal() {
    return overtimeBidFinal;
    }

  public final WebElement getSetRemainingTime() {
    return setRemainingTime;
    }

  public final WebElement getTreatmentTieBid() {
    return treatmentTieBid;
    }

  public final WebElement getEditTeamMember() {
    return editTeamMember;
    }

  public final WebElement getAddStakeholderMember() {
    return addStakeholderMember;
    }

  public final WebElement getAddTeamMember() {
    return addTeamMember;
    }

  public final WebElement getAddMemberSearchUser() {
    return addMemberSearchUser;
    }

  public final List<WebElement> getTeamMemberPanel() {
    return teamMemberPanel;
    }

  public final WebElement getNoDocsUpload() {
    return noDocsUpload;
    }

  public final WebElement getOverViewUploadFile() {
    return overViewUploadFile;
    }

  public final WebElement getOverViewUploadLink() {
    return overViewUploadLink;
    }

  public final WebElement getOverViewDownloadAll() {
    return overViewDownloadAll;
    }

  public final WebElement getAuctionRunTimeFirst() {
    return auctionRunTimeFirst;
    }

  public final WebElement getAuctionSubsequentLotTime() {
    return auctionSubsequentLotTime;
    }

  public final WebElement removeDocument(final String documentRowNo) {
    //int value = Integer.parseInt(documentRowNo) + 1;

    /*
     * WebElement removeDoc = driver.findElement(By.
     * cssSelector("[data-test = documents-table] [role = row]:nth-child(" + value +
     * ") [data-tes = remove-doc-btn] [role = img]"));
     */
    WebElement removeDoc = driver.findElement(By.cssSelector("[data-test = remove-doc-btn-" + documentRowNo + "] [role = img]"));

    return removeDoc;
    }

  public final WebElement downloadDocument(final String documentRowNo) throws InterruptedException {
    //int value = Integer.parseInt(documentRowNo) + 1;
    /*
     * WebElement downloadDoc = driver .findElement(By.
     * cssSelector("[data-test = documents-table] [role = row]:nth-child(" + value +
     * ") [data-test = download-document-btn]"));s
     */
    WebElement downloadDoc = driver
        .findElement(By.cssSelector("[data-test = download-document-btn-" + documentRowNo + "]"));
    final int sleepTime = 1000;
    Thread.sleep(sleepTime);
    return downloadDoc;
    }

  public final WebElement downloadLinkDoc(final String documentRowNo) {
    //int value = Integer.parseInt(documentRowNo) + 1;
    /*
     * WebElement downloadLink = driver.findElement(By.cssSelector(
     * "[data-test = documents-table] [role = row]:nth-child(" + value +
     * ") [data-test = view-link-btn]"));
     */
    WebElement downloadLink = driver.findElement(By.cssSelector("[data-test = view-link-btn-" + documentRowNo + "]"));
    return downloadLink;
    }

  public final WebElement navigationMonitorPhase(final String navigationTab) {
    String navigationLink = navigationTab + "-tab";
    System.out.println("Navigation Link>>>" + navigationLink);
    WebElement naviagtionElement = driver.findElement(By.xpath("//*[@data-test = '" + navigationLink + "']"));
    return naviagtionElement;
    }

  public final WebElement invitedSuppliers(final String supplierRowNo) {
    int value = Integer.parseInt(supplierRowNo) + 1;
    WebElement supplierTable = driver
        .findElement(By.cssSelector("[data-test = suppliers-invited-table] [role = row]:nth-child(" + value
            + ") [data-test = suppliers-name]"));
    return supplierTable;
    }

  }
