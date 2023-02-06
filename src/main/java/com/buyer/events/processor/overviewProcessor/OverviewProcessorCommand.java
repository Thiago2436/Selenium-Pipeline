package com.buyer.events.processor.overviewProcessor;



import java.awt.AWTException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import com.buyer.events.processor.ITabCommandProcessor;
import com.buyer.events.processor.documentProcessor.DocumentProcessorCommand;
import com.buyer.events.processor.eventRulesProcessor.EventRulesAsserterCommand;
import com.buyer.events.processor.pricingSheetProcessor.PricingSheetProcessorCommand;
import com.buyer.events.processor.teamMemberProcessor.TeamMemberProcessorCommand;
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.TestUtil;
import com.sol.qa.util.XlsReader;

public class OverviewProcessorCommand implements ITabCommandProcessor {

  private TeamMemberProcessorCommand teamMemberProcess;
  private DocumentProcessorCommand documentProcess;
  private PricingSheetProcessorCommand pricingSheetProcess;
  private EventRulesAsserterCommand assertEventRules;
  private WindowManager windowManager;
  private OverviewCreationPage overViewPage;
  final int sleepTime = 6000;

  public OverviewProcessorCommand(final WindowManager windowManger) {
    this.windowManager = windowManger;
    System.out.println("windowManager OverviewProcessorCommand>>>" + windowManger);
    overViewPage = new OverviewCreationPage(this.driver());
    teamMemberProcess = new TeamMemberProcessorCommand(windowManger);
    documentProcess = new DocumentProcessorCommand(windowManger);
    assertEventRules = new EventRulesAsserterCommand(windowManger);
    pricingSheetProcess = new PricingSheetProcessorCommand(windowManger);
    }


  private RemoteWebDriver driver() {
    return  this.windowManager.getActiveDriver();
    }

  public static final void sendkeys(final RemoteWebDriver driver, final WebElement element, final int timeout, final String value) {
    new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
    element.sendKeys(value);
    }

  public static final void clickOn(final RemoteWebDriver driver, final WebElement element, final int timeout) {
    new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
    element.click();
    }

  /**
   *This method have XLs_Reader , command , argument1 , argument2 , argument3 and argument4 as Parameter
   *It have switch command and its corresponding method.
   */
  public void processCommand(final XlsReader reader, final String command, final String argument1, final  String argument2,
      final String argument3, final String argument4)
      throws AWTException, InterruptedException {
    switch (command) {
      case "verifyEventName":
        verifyEventName(argument1);
        break;
      case "verifyEventId":
        verifyEventId(argument1);
        break;
      case "verifyEventDescription":
        verifyEventDescription(argument1);
        break;
      case "verifyCategoryTree":
        verifyCategoryTree(argument1);
        break;
      case "verifyEventType":
        verifyEventType(argument1);
        break;
      case "verifyCopyFromPrevious":
        verifyCopyFromPrevious(argument1);
        break;
      case "verifyPredecessorEvent":
        verifyPredecessorEvent(argument1);
        break;
      case "verifyPQC":
        verifyPQC(argument1);
        break;
      case "verifyAuctionType":
        verifyAuctionType(argument1);
        break;
      case "verifyStartTimeAuction":
        verifyStartTimeAuction(argument1, argument2);
        break;
      case "verifyInitialPreBid":
        verifyInitialPreBid(argument1);
        break;
      case "verifyLotClosureSequence":
        verifyLotClosureSequence(argument1);
        break;
      case "verifyRunTimeAllLot":
        verifyRunTimeAllLot(argument1);
        break;
      case "verifyAuctionRunTimeFirst":
        verifyAuctionRunTimeFirst(argument1);
        break;
      case "verifyEventCurrency":
        verifyEventCurrency(argument1);
        break;
      case "verifyIsMultiCurrency":
        verifyIsMultiCurrency(argument1);
        break;
      case "verifyCompetitiveFeedback":
        verifyCompetitiveFeedback(argument1);
        break;
      case "verifySupplierRankTriggerOvertime":
        verifySupplierRankTriggerOvertime(argument1);
        break;
      case "verifyOvertimetriggerBidReceiveFinal":
        verifyOvertimetriggerBidReceiveFinal(argument1);
        break;
      case "verifyAuctionSubsequentLotTime":
        verifyAuctionSubsequentLotTime(argument1);
        break;
      case "verifySetRemainingTime":
        verifySetRemainingTime(argument1);
        break;
      case "verifyTreatmentOfTieBid":
        verifyTreatmentOfTieBid(argument1);
        break;
      case "performEditTeamMember":
        performEditTeamMember();
        break;
      case "addStakeholderMember":
        addStakeholderMember();
        break;
      case "UploadLink":
        performAddLink();
        break;

      case "AddTeamMember":
        addTeamMemberUser(argument1);
        break;
      case "confirmButton":
        teamMemberProcess.performConfirm();
        break;
      case "noDocsUpload":
        assertNoDocsUpload(argument1);
        break;
      case "UploadFile":
        uploadFileDocument();
        break;
      case "FilePath":
        documentProcess.setFilePath(argument1);
        break;
      case "UrlAddressAndText":
        documentProcess.setURLAddressLink(argument1, argument2);
        break;
      case "SaveLinkAddress":
        documentProcess.performSaveLink();
        break;
      case "removeUploadedDocument":
        removeUploadedDocument(argument1);
        break;
      case "downloadUploadedDocument":
        downloadUploadedDocument(argument1);
        break;
      case "downloadLinkDocument":
        downloadUploadedLink(argument1);
        break;
      case "YesNoButton":
        pricingSheetProcess.performYesNoButton(argument1);
        break;
      case "performDownloadAllDocument":
        overViewDownloadAllDoc();
        break;
      case "NavigationMonitorTab":
        performNavigationMonitor(argument1);
        break;
      case "Assertion":
        switch (argument1) {
          case "ToastMessage":
            assertEventRules.assertToastMsg(argument2);
            break;
          default:
            System.out.println("no switch match" + argument1);

          }
        break;
      default:
        System.out.println("no match" + command);

      }
    }

  /**
  * This method verify event name field with the given data.
  */
  public void verifyEventName(final String eventName) throws InterruptedException {
    AssertJUnit.assertEquals(eventName, overViewPage.getEventValueName().getText());
    }

  /**
  * This method verify event description field with the given data.
  */
  public void verifyEventDescription(final String eventDescription) throws InterruptedException {
    AssertJUnit.assertEquals(eventDescription, overViewPage.getEventDescription().getText());
    }

  /**
  * This method verify event id field with the given data.
  */
  public void verifyEventId(final String eventId) throws InterruptedException {
    System.out.println("verifyEventId>>>>" + eventId);
    AssertJUnit.assertEquals(eventId, overViewPage.getEventId().getText());
    }

  /**
  * This method verify category tree field with the given data.
  */
  public void verifyCategoryTree(final String categoryTree) throws InterruptedException {
    AssertJUnit.assertEquals(categoryTree, overViewPage.getCategoryTree().getText());
    }

  /**
  * This method verify event type field with the given data.
  */
  public void verifyEventType(final String eventType) throws InterruptedException {
    AssertJUnit.assertEquals(eventType, overViewPage.getEventType().getText());
    }

  /**
  * This method verify "Copy From Previous Event" field with the given data.
  */
  public void verifyCopyFromPrevious(final String copyFromPrevious) throws InterruptedException {
    AssertJUnit.assertEquals(copyFromPrevious, overViewPage.getCopyFromPrevious().getText());
    }


  /**
  * This method verify "Predecessor Event" field with the given data.
  */
  public void verifyPredecessorEvent(final String predecessorEvent) throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getPredecessorEvent());
    AssertJUnit.assertEquals(predecessorEvent, overViewPage.getPredecessorEvent().getText());
    }

  /**
  * This method verify "PQC" field with the given data.
  */
  public void verifyPQC(final String pqcValue) throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getPqcValue());
    AssertJUnit.assertEquals(pqcValue, overViewPage.getPqcValue().getText());
    }

  /**
  * This method verify "Auction Type" field with the given data.
  */
  public void verifyAuctionType(final String auctionValue) throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getAuctionType());
    AssertJUnit.assertEquals(auctionValue, overViewPage.getAuctionType().getText());
    }

  /**
  * This method verify "Start Time Auction" field with the given data.
  */
  public void verifyStartTimeAuction(final String startTimeValue, final String zoneValue) throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getAuctionStartTime());
    AssertJUnit.assertEquals(startTimeValue, overViewPage.getAuctionStartTime().getText());
    AssertJUnit.assertEquals(zoneValue, overViewPage.getAuctionzoneTime().getText());

    }

  /**
  * This method verify "Initial PreBid" field with the given data.
  */
  public void verifyInitialPreBid(final String preBidValue) throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getAuctionPreBid());
    AssertJUnit.assertEquals(preBidValue, overViewPage.getAuctionPreBid().getText());
    }

  /**
  * This method verify "Lot Closure Sequence" field with the given data.
  */
  public void verifyLotClosureSequence(final String lotClosureSequence) throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getAuctionLotClosureSeqence());
    AssertJUnit.assertEquals(lotClosureSequence, overViewPage.getAuctionLotClosureSeqence().getText());
    }

  /**
   * This method verify "Each subsequent lot to close in" field with the given data.
   */
  public void verifyAuctionSubsequentLotTime(final String auctionSubsequentLotTime) throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getAuctionSubsequentLotTime());
    AssertJUnit.assertEquals(auctionSubsequentLotTime, overViewPage.getAuctionSubsequentLotTime().getText());
    }

   /**
    * This method verify "Run time for first lot" field with the given data.
    */
  public void verifyAuctionRunTimeFirst(final String auctionRunTimeFirst) throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getAuctionRunTimeFirst());
    AssertJUnit.assertEquals(auctionRunTimeFirst, overViewPage.getAuctionRunTimeFirst().getText());
    }

  /**
  * This method verify "Run Time All Lot" field with the given data.
  */
  public void verifyRunTimeAllLot(final String runTimeAllLot) throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getAuctionRuntimeForAllLots());
    AssertJUnit.assertEquals(runTimeAllLot, overViewPage.getAuctionRuntimeForAllLots().getText());
    }

  /**
  * This method verify "Event Currency" field with the given data.
  */
  public void verifyEventCurrency(final String auctionEventCurrency) throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getEventCurrency());
    AssertJUnit.assertEquals(auctionEventCurrency, overViewPage.getEventCurrency().getText());
    }

  /**
  * This method verify whether it is multicurrency event or not field with the given data.
  */
  public void verifyIsMultiCurrency(final String isMultiCurrency) throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getIsMultiCurrencyValue());
    AssertJUnit.assertEquals(isMultiCurrency, overViewPage.getIsMultiCurrencyValue().getText());
    }

  /**
  * This method verify "Competitive Feedback" field with the given data.
  */
  public void verifyCompetitiveFeedback(final String competitiveFeedback) throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getCompetitiveFeedback());
    AssertJUnit.assertEquals(competitiveFeedback, overViewPage.getCompetitiveFeedback().getText());
    }

  /**
  * This method verify "Supplier Rank Trigger Overtime" field with the given data.
  */
  public void verifySupplierRankTriggerOvertime(final String supplierRankOvertime) throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getTriggerOvertimeValue());
    AssertJUnit.assertEquals(supplierRankOvertime, overViewPage.getTriggerOvertimeValue().getText());
    }

  /**
  * This method verify "Overtime Trigger Bid Receive Final" field with the given data.
  */
  public void verifyOvertimetriggerBidReceiveFinal(final String overtimeFinalBid) throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getOvertimeBidFinal());
    AssertJUnit.assertEquals(overtimeFinalBid, overViewPage.getOvertimeBidFinal().getText());
    }

  /**
  * This method will verify the set remaining time field.
  */
  public void verifySetRemainingTime(final String remainingTime) throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getSetRemainingTime());
    AssertJUnit.assertEquals(remainingTime, overViewPage.getSetRemainingTime().getText());
    }

  /**
   * This method verify Treatment Of Tie Bid field.
   */
  public void verifyTreatmentOfTieBid(final String tieBid) throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getTreatmentTieBid());
    AssertJUnit.assertEquals(tieBid, overViewPage.getTreatmentTieBid().getText());
    }

  /**
   * This method click on edit button on team member section.
   */
  public void performEditTeamMember() throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getEditTeamMember());
    clickOn(this.driver(), overViewPage.getEditTeamMember(), TestUtil.getTimeOut());
    }

  /**
   * This method will click on Add Team Member button.
   */
  public void addStakeholderMember() throws InterruptedException {
    Thread.sleep(sleepTime);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getAddStakeholderMember());
    clickOn(this.driver(), overViewPage.getAddStakeholderMember(), TestUtil.getTimeOut());
    Thread.sleep(sleepTime);
    clickOn(this.driver(), overViewPage.getAddTeamMember(), TestUtil.getTimeOut());

    }

  /**
   * This method will receive the details for adding Team Member in the dropdown search box.
   */
  public void addTeamMemberUser(final String arg1) throws InterruptedException {
    Thread.sleep(sleepTime);
    overViewPage.getAddMemberSearchUser().sendKeys(arg1);
    for (int i = 0; i < overViewPage.getTeamMemberPanel().size(); i++) {
      List<WebElement> checkBoxElement = overViewPage.getTeamMemberPanel().get(i)
          .findElements(By.tagName("mat-pseudo-checkbox"));
      checkBoxElement.get(i).click();
      }
    /*
     * Actions act = new Actions(driver);
     * act.moveToElement(teamMemberPage.addMemberDropDown).click().perform();
     */
    Actions action = new Actions(this.driver());
    action.sendKeys(Keys.ESCAPE).build().perform();

    }

  /**
   * This method assert the No Docs section if the documents are not uploaded.
   */
  public void assertNoDocsUpload(final String noDocs) throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getNoDocsUpload());
    AssertJUnit.assertEquals(noDocs, overViewPage.getNoDocsUpload().getText());

    }

  /**
   * This method will verify the uploaded file document.
   */
  public void uploadFileDocument() throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getOverViewUploadFile());
    clickOn(this.driver(), overViewPage.getOverViewUploadFile(), TestUtil.getTimeOut());
    }

  /**
   * This method verify the uploaded link.
   */
  public void performAddLink() {
    WebDriverWait wait = new WebDriverWait(this.driver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    wait.until(ExpectedConditions.visibilityOf(overViewPage.getOverViewUploadLink()));
    overViewPage.getOverViewUploadLink().click();
    }

  /**
   * This method will remove the uploaded document given.
   */
  public void removeUploadedDocument(final String removeDoc) throws InterruptedException {
    Thread.sleep(sleepTime);
    WebElement removeDocument = overViewPage.removeDocument(removeDoc);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", removeDocument);
    clickOn(this.driver(), removeDocument, TestUtil.getTimeOut());
    }

  /**
   * This method will download uploaded document.
   */
  public void downloadUploadedDocument(final String downloadDoc) throws InterruptedException, AWTException {

    WebElement downloadDocument = overViewPage.downloadDocument(downloadDoc);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", downloadDocument);
    clickOn(this.driver(), downloadDocument, TestUtil.getTimeOut());
    // documentProcess.closeDownloadedTab();
    }

  /**
   * This method will download the uploaded link.
   */
  public void downloadUploadedLink(final String downloadLink) throws InterruptedException, AWTException {
    WebElement downloadLinkDocument = overViewPage.downloadLinkDoc(downloadLink);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", downloadLinkDocument);
    clickOn(this.driver(), downloadLinkDocument, TestUtil.getTimeOut());
    // documentProcess.closeDownloadedTab();
    }

  /**
   * This method verify the download all documents.
   */
  public void overViewDownloadAllDoc() throws InterruptedException, AWTException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", overViewPage.getOverViewDownloadAll());
    clickOn(this.driver(), overViewPage.getOverViewDownloadAll(), TestUtil.getTimeOut());
    //documentProcess.closeDownloadedTab();
    }

  /**
   * This method will navigate to specified tab.
   */
  public void performNavigationMonitor(final String navigationTab) throws InterruptedException, AWTException {
    Thread.sleep(sleepTime);
    System.out.println("Navigation Tab");
    WebElement naviagtionLink = overViewPage.navigationMonitorPhase(navigationTab);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", naviagtionLink);
    clickOn(this.driver(), naviagtionLink, TestUtil.getTimeOut());
    }
  }
