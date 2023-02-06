package com.buyer.events.processor.pricingSheetProcessor;

import com.buyer.events.processor.eventRulesProcessor.EventRulesAsserterCommand;
import com.buyer.events.processor.eventRulesProcessor.bulkUploadLots.Assertion;
import com.buyer.events.processor.eventRulesProcessor.bulkUploadLots.CommandHandler;
import com.buyer.events.processor.supplierProcessor.SupplierProcessorCommand;

import java.awt.AWTException;
import java.text.Format;

import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;


import com.buyer.events.processor.ITabCommandProcessor;
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.TestUtil;
import com.sol.qa.util.XlsReader;

public class PricingSheetProcessorCommand implements ITabCommandProcessor {

  /*
   * static class BulkUploadCommandHandler extends
   * com.buyer.events.processor.EventRulesProcessor.BulkUploadLots.CommandHandler
   * { } static class BulkUploadAssertion extends
   * com.buyer.events.processor.EventRulesProcessor.BulkUploadLots.Assertion { }
   */

  private PricingSheetCreationPage pricingSheetPage;
  private EventRulesAsserterCommand assertEventRules;
  private CommandHandler bulkUploadLots;
  private Assertion bulkUploadLotsAssertion;
  private SupplierProcessorCommand supplierProcessor;
  final int sleepTime = 1000;
  private WindowManager windowManager;
  private final int count3 = 3;
  private final int count4 = 4;
  private final int stringLength = 20;
  private JavascriptExecutor js;

  public PricingSheetProcessorCommand(final WindowManager windowManger) {
    this.windowManager = windowManger;
    System.out.println("windowManager OverviewProcessorCommand>>>" + windowManger);
    js = (JavascriptExecutor) this.driver();
   // bulkUploadLots = new CommandHandler(windowManger);
    pricingSheetPage = new PricingSheetCreationPage(this.driver());
    assertEventRules = new EventRulesAsserterCommand(windowManger);
    supplierProcessor = new SupplierProcessorCommand(windowManger);
    bulkUploadLots = new CommandHandler(windowManger);
    bulkUploadLotsAssertion = new Assertion(windowManger);
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
   * This method clicks on "Knowledge Library" and select the given sheet name specified.
   */
  public void getPricingSheet(final String sheetName) throws InterruptedException {
    clickOn(driver(), pricingSheetPage.getOpenKnowledgeLibrary(), TestUtil.getTimeOut());
    int lengthOfString = sheetName.length();
    String[] splitString = sheetName.split("");
    for (int count = 0; count < lengthOfString; count++) {
      pricingSheetPage.getOpenKnowledgeLibrary().sendKeys(splitString[count]);
      Thread.sleep(2);
      }
    WebElement requiredLot = pricingSheetPage.importLot(sheetName);
    clickOn(driver(), requiredLot, TestUtil.getTimeOut());
    }

  /**
   * This method clicks on "Add From Knowledge Library" button and
   * select either "My Library" and "Shared Library".
   */
  public void addFromKnowledgeLibrary(final String libraryName, final String pricingSheetName) throws InterruptedException {
    Thread.sleep(2);
    clickOn(driver(), pricingSheetPage.getAddLot(), TestUtil.getTimeOut());
    Thread.sleep(sleepTime);
    clickOn(driver(), pricingSheetPage.getAddLotFromLibraryButton(), TestUtil.getTimeOut());
    if (libraryName.equalsIgnoreCase("My Library")) {
      getPricingSheet(pricingSheetName);
      }
    if (libraryName.equalsIgnoreCase("Shared Library")) {
      clickOn(driver(), pricingSheetPage.getSharedLibraryButton(), TestUtil.getTimeOut());
      getPricingSheet(pricingSheetName);
      }
    Thread.sleep(sleepTime);
    clickOn(driver(), pricingSheetPage.getAddFromLibraryButton(), TestUtil.getTimeOut());
    }

  /**
   * This method assert the color for the Extend Price Pink Shade.
   */
  public void extendPriceShadeVal() {
    Assert.assertTrue(
        (pricingSheetPage.getExtendedPricePinkShade().getAttribute("class").contains("column-background-color")));
    }

  /**
   * This method clicks on Clear All Item Grey button.
   */
  public void clearAllItemGrey(final String lotNo) throws InterruptedException {
    Thread.sleep(sleepTime);
    WebElement idLot = pricingSheetPage.getLotId(lotNo);
    System.out.println("idLot>>>>" + idLot);
    WebElement chevronRightIcon = idLot.findElement(By.tagName("mat-icon"));
    if (chevronRightIcon.getText().equals("chevron_right")) {
      clickOn(driver(), chevronRightIcon, TestUtil.getTimeOut());
      }
    String temp = "#lot-" + lotNo + " [data-test = 'clear']";
    System.out.println("temp>>>>" + temp);
    WebElement addCircleOutline = idLot.findElement(By.cssSelector(temp));
    Assert.assertTrue((addCircleOutline.getAttribute("class").contains("disableClear")));
    }

  /**
   * This method count the Pricing Sheet Summary.
   */
  public void countPricingSheetSummary(final String noOfCount) {
    List<WebElement> countPricingSummary = driver().findElements(By.xpath("//*[@id = 'pricing-sheet-table']/tr"));
    int totalLot = Integer.valueOf(noOfCount);
    Assert.assertEquals(countPricingSummary.size(), totalLot);
    }

  /**
   * This method assert the Currency Value.
   */
  public void assertCurrency(final String currencyDetail) {
    driver().switchTo().frame(pricingSheetPage.getIframeElement());
    Assert.assertEquals(pricingSheetPage.getCurrencyTable().getText(), currencyDetail);
    driver().switchTo().defaultContent();
    }

  /**
   * This method assert the Currency Value.
   */
  public void assertPTFTable(final String lotNo) {
    driver().manage().timeouts().implicitlyWait(TestUtil.getImplicitWait(), TimeUnit.SECONDS);
    List<WebElement> ptfTableIsPresent = pricingSheetPage.ptfTableElement(lotNo);
    if (ptfTableIsPresent.size() <= 0) {
      Assert.assertTrue(true);
      } else {
      Assert.assertTrue(false);
      }

    }


  /**
   * This method assert whether the Line Items cleared.
   */
  public void assertLineItemCleared() throws InterruptedException {
    Thread.sleep(sleepTime);
    Assert.assertEquals("", pricingSheetPage.getLotTableElement()
        .findElement(By.xpath("//*[@data-testid = 'row0']/td[@data-test='Item Name']")).getText());
    Assert.assertEquals("", pricingSheetPage.getLotTableElement()
        .findElement(By.xpath("//*[@data-testid = 'row0']/td[@data-test='Quantity']")).getText());
    Assert.assertEquals("", pricingSheetPage.getLotTableElement()
        .findElement(By.xpath("//*[@data-testid = 'row0']/td[@data-test='UOM']")).getText());
    Assert.assertEquals("",
        pricingSheetPage.getLotTableElement()
        .findElement(
            By.xpath("//*[@data-testid = 'row0']/td[@data-test='Baseline Price']/span/span[2]"))
        .getText());
    Assert.assertEquals(1,
        pricingSheetPage.getLotTableElement().findElements(By.xpath("//*[@data-testid = 'row0']")).size());

    }

  /**
   * This method assert whether the Line Items cleared.
   */
  public void assertClearLineItemGreyed() {
    pricingSheetPage.getClearAllItem().getClass();
    Assert.assertTrue((pricingSheetPage.getClearAllItem().getAttribute("class").contains("disableClear")));

    }

  /**
   * This method assert given lot description.
   */
  public void assertLotDescription(final String lotDescription) throws InterruptedException {
    Thread.sleep(sleepTime);
    AssertJUnit.assertEquals(lotDescription, pricingSheetPage.getToastMessage().getText());
    }

  /**
   * This method assert whether the field is editable.
   */
  public void assertIsFieldEditable(final String lotNo) throws InterruptedException {
    Thread.sleep(sleepTime);
    System.out.println("lotNo>>>>>>" + lotNo);
    Assert.assertTrue((pricingSheetPage.getAddLot().getAttribute("disabled").contains("true")));
    Assert.assertTrue((pricingSheetPage.getDeleteLots().getAttribute("disabled").contains("true")));
    WebElement idLot = pricingSheetPage.getLotId(lotNo);
    WebElement chevronRightIcon = idLot.findElement(By.tagName("mat-icon"));

    if (chevronRightIcon.getText().equals("chevron_right")) {
      clickOn(driver(), chevronRightIcon, TestUtil.getTimeOut());

      }
    Assert.assertTrue(!(pricingSheetPage.lotDescriptionElement(lotNo).isEnabled()));
    Assert.assertTrue(!(pricingSheetPage.lotBidDecrement(lotNo).isEnabled()));
    Assert.assertTrue(!(pricingSheetPage.lotProtectionBidBuffer(lotNo).isEnabled()));
    Assert.assertTrue(!(pricingSheetPage.lotActionElement(lotNo).isEnabled()));
    Assert.assertTrue(
        (pricingSheetPage.clearAllItemsElement(lotNo).getAttribute("class").contains("disableClear")));
    Assert.assertTrue((pricingSheetPage.getLotTableElement().getAttribute("class").contains("disabled")));

    }

  /**
   *This method have XLs_Reader , command , argument1 , argument2 , argument3 and argument4 as Parameter
   *It have switch command and its corresponding method.
   */
  public void processCommand(final XlsReader reader, final String command, final String argument1, final  String argument2,
      final String argument3, final String argument4) throws AWTException, InterruptedException {
    switch (command) {
      case "createNewLot":
        createLot();
        break;
      case "deleteAllLots":
        deleteAllLots();
        break;
      case "openBulkUploadLotsPopup":
        bulkUploadLots.openBulkUploadLotsPopup();
        break;
      case "uploadLotsViaExcel":
        bulkUploadLots.uploadLotsViaExcel(argument1);
        break;
      case "downloadBulkUploadLotsExcel":
        bulkUploadLots.downloadBulkUploadLotsExcel();
        break;
      case "ACTIONS":
        performActions(argument1, argument2);
        break;
      case "lotName":
        setLotName(argument1, argument2);
        break;
      case "lotDescription":
        setLotDescription(argument1, argument2);
        break;
      case "DragAndDrop":
        performDragAndDrop(argument1, argument2);
        break;
      case "lotBidAction":
        lotBidAction(argument1, argument2);
        break;
      case "BidDecrementValue":
        lotBidDecrementValue(argument1, argument2);
        break;
      case "BidIncrementValue":
        lotBidDecrementValue(argument1, argument2);
        break;
      case "BidProtectionBuffer":
        lotBidBufferValue(argument1, argument2);
        break;
      case "EnterLineItems":
        lineItemsPrice(reader, argument1, argument2);
        break;
      case "publishEvent":
        performPublishEvent();
        break;
      case "ChangeColumnPosition":
        changeColumnPosition(argument1, argument2);
        break;
      case "addCircleOutline":
        addNewColumn(argument1);
        break;
      case "SetPriceTransformationFactor":
        setPTFtoSupplier(reader, argument1);
        break;
      case "TransformedPriceSetFormula":
        formulaForTransformPrice(argument1);
        break;
      case "SaveButton":
        performSave();
        break;
      case "YesNoButton":
        performYesNoButton(argument1);
        break;
      case "NavigationTab":
        performNavigation(argument1);
        break;
      case "MonitorNavigationTab":
        performMonitorNavigation(argument1);
        break;
      case "createSupplier":
        createSupplier(reader, argument1);
        break;
      case "removeSupplier":
        supplierProcessor.removeSupplierFromList(argument1);
        break;
      case "ClearAllLineItems":
        clearAllItems(argument1);
        break;
      case "LotFromKnowledgeLibrary":
        addFromKnowledgeLibrary(argument1, argument2);
        break;
      case "SetCeilingPrice":
        setCeilingPrice(reader, argument1, argument2);
        break;
      case "PricingSheetSummary":
        pricingSheetSummary(argument1, argument2, argument3, argument4);
        break;
      case "addTermName":
        addTermName(argument1);
        break;
      case "whoFills":
        setWhoFills(argument1);
        break;
      case "PriceTransformationFactor":
        priceTransformationFactorFunc();
        break;
      case "DataNeedForAllitem":
        dataNeedForAllitem();
        break;
      case "InputFieldType":
        setInputFieldType(argument1);
        break;
      case "RequiredFormula":
        setRequiredFormula(argument1);
        break;
      case "ChangeExtendedPriceFormula":
        changeEPFormula();
        break;
      case "CreateFormula":
        createFormula(argument1);
        break;
      case "RequiresALot":
        setRequiresLot(argument1);
        break;
      case "AlternateBidCriteria":
        alternateBidCriteria(argument1);
        break;
      case "ColumnToEdit":
        headerTableSelect(argument1, argument2);
        break;
      case "DuplicateColumn":
        performDuplicateColumn();
        break;
      case "SetMinimumValue":
        setMinimumValue(argument1);
        break;
      case "SetMaximumValue":
        setMaximumValue(argument1);
        break;
      case "DeleteColumn":
        performDeleteColumn();
        break;
      case "ConfirmAction":
        performConfirmAction();
        break;
      case "AssignSuppliers":
        performAssignInviteSuppliers(argument1);
        break;
      case "MainAction":
        performMainAction(argument1);
        break;
      case "Assertion":
        assertionCases(argument1, argument2);
        break;
      default:
        System.out.println("no match");
      }
    }

  /**
   * This method clicks on publish event.
   */
  public void performPublishEvent() {
    WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    if (pricingSheetPage.getPublishEvent().isEnabled()) {
      wait.until(ExpectedConditions.elementToBeClickable(pricingSheetPage.getPublishEvent()));
      clickOn(driver(), pricingSheetPage.getPublishEvent(), TestUtil.getTimeOut());

      }
    }

  /**
   * This method drag from the Source Column Position to Destination Column Position.
   */
  public void changeColumnPosition(final String columnSourcePos, final String columnDestPos) throws InterruptedException {
    Thread.sleep(sleepTime);
    String sourcePos = "header-" + columnSourcePos;
    WebElement selectedColumn = driver().findElement(By.xpath("//*[@class='cdk-drop-list']/th"));
    WebElement columnToChange = selectedColumn.findElement(By.xpath("//*[@data-test='" + sourcePos + "']"));
    Actions act = new Actions(driver());
    act.clickAndHold(columnToChange).build().perform();
    String descPos = "header-" + columnDestPos;
    WebElement selectedDropElement = driver().findElement(By.xpath("//*[@class='cdk-drop-list']/th[5]"));
    WebElement columnToDrop = selectedDropElement.findElement(By.xpath("//*[@data-test='" + descPos + "']"));
    act.moveToElement(columnToDrop).build().perform();
    act.release(columnToDrop).build().perform();
    }

  /**
   * This method clicks on the Add New Column .
   */
  public void addNewColumn(final String lotId) throws InterruptedException {
    WebElement idLot = pricingSheetPage.getLotId(lotId);
    WebElement chevronRightIcon = idLot.findElement(By.tagName("mat-icon"));
    if (chevronRightIcon.getText().equals("chevron_right")) {
      clickOn(driver(), chevronRightIcon, TestUtil.getTimeOut());
      }
    StringTokenizer splitlot = new StringTokenizer(lotId, ".");
    String lotIdStr = "lot-" + splitlot.nextToken();
    WebElement idLot1 = driver().findElement(By.id(lotIdStr));
    WebElement addCircleOutline = idLot1.findElement(By.cssSelector("#" + lotIdStr + " [data-test = 'addColumn']"));
    Thread.sleep(sleepTime);
    clickOn(driver(), addCircleOutline, TestUtil.getTimeOut());
    }

  /**
   * This method clicks on create Lot button.
   */
  public void createLot() throws InterruptedException {
    Thread.sleep(sleepTime);
    clickOn(driver(), pricingSheetPage.getAddLot(), TestUtil.getTimeOut());
    Thread.sleep(sleepTime);
    if (pricingSheetPage.getCreateNewLot().isDisplayed()) {
      clickOn(driver(), pricingSheetPage.getCreateNewLot(), TestUtil.getTimeOut());
      Thread.sleep(sleepTime);
      }
    }

  /**
   * This method enter the lot name for that field.
   */
  public void setLotName(final String lotId, final String lotName) throws InterruptedException {
    Thread.sleep(sleepTime);
    StringTokenizer splitlot = new StringTokenizer(lotId, ".");
    String lotIdStr = "lot-" + splitlot.nextToken();
    System.out.println("Lot ID>>>>" + lotIdStr);
    WebElement idLot = driver().findElement(By.id(lotIdStr));
    WebElement chevronRightIcon = idLot.findElement(By.tagName("mat-icon"));

    if (chevronRightIcon.getText().equals("chevron_right")) {
      clickOn(driver(), chevronRightIcon, TestUtil.getTimeOut());
      }
    Thread.sleep(sleepTime);

    WebElement testPencil = pricingSheetPage.getPencilImg(idLot, lotIdStr);
    clickOn(driver(), testPencil, TestUtil.getTimeOut());
    WebElement lotNameText = idLot.findElement(By.cssSelector("#" + lotIdStr + " [data-test = 'name']"));
    sendkeys(driver(), lotNameText, TestUtil.getTimeOut(), lotName);

    }

  /**
   * This method clicks on Actions button for the given lot id.
   */
  public void performActions(final String lotId, final String actionOption) throws InterruptedException {
    WebElement actionDropDown = pricingSheetPage.lotActionElement(lotId);
    clickOn(driver(), actionDropDown, TestUtil.getTimeOut());
    String actionValue = "";
    if (actionOption.equalsIgnoreCase("Allow Alternate Bid")) {
      actionValue = "toggleAlternateBid";

      }
    if (actionOption.equalsIgnoreCase("Enable Transformed Cost")) {
      actionValue = "toggleTransformedCost";

      }
    if (actionOption.equalsIgnoreCase("Disable Transformed Cost")) {
      actionValue = "disableTransformedCost";

      }
    if (actionOption.equalsIgnoreCase("Duplicate Lot")) {
      actionValue = "duplicateLot";

      }
    if (actionOption.equalsIgnoreCase("Delete Lot")) {
      actionValue = "deleteLot";

      }
    WebElement actionMenu = pricingSheetPage.actionMenuOption(actionValue);
    clickOn(driver(), actionMenu, TestUtil.getTimeOut());

    }

  /**
   * This method enter the value in Lot Description Field.
   */
  public void setLotDescription(final String lotId, final String lotDescription) {
    WebElement idLot = pricingSheetPage.getLotId(lotId);
    WebElement chevronRightIcon = idLot.findElement(By.tagName("mat-icon"));

    if (chevronRightIcon.getText().equals("chevron_right")) {
      clickOn(driver(), chevronRightIcon, TestUtil.getTimeOut());

      }

    WebElement lotDescript = pricingSheetPage.lotDescriptionElement(lotId);

    if (chevronRightIcon.getText().equals("chevron_right")) {
      clickOn(driver(), chevronRightIcon, TestUtil.getTimeOut());
      }
    if (lotDescript.isDisplayed()) {
      clickOn(driver(), lotDescript, TestUtil.getTimeOut());

      lotDescript.clear();
      sendkeys(driver(), lotDescript, TestUtil.getTimeOut(), lotDescription);

      }

    }

  /**
   * This method clicks Lot Bid Action Option.
   * @throws InterruptedException
   */
  public void lotBidAction(final String lotId, final String lotBidAction) throws InterruptedException {
    WebElement idLot = pricingSheetPage.getLotId(lotId);
    WebElement chevronRightIcon = idLot.findElement(By.tagName("mat-icon"));

    if (chevronRightIcon.getText().equals("chevron_right")) {
      clickOn(driver(), chevronRightIcon, TestUtil.getTimeOut());

      }

    WebElement bidActionField = pricingSheetPage.bidActionField(idLot);
    js.executeScript("arguments[0].scrollIntoView();", bidActionField);
    Thread.sleep(count3);
    clickOn(driver(), bidActionField, TestUtil.getTimeOut());
    Thread.sleep(count3);
    WebElement bidActionOption = bidActionField
        .findElement(By.xpath("//*[@class = 'mat-option-text' and contains(text(),' " + lotBidAction + "')]"));
    clickOn(driver(), bidActionOption, TestUtil.getTimeOut());

    }

  /**
   * This method clicks drag and drop element.
   */
  public void performDragAndDrop(final String source, final String destination) throws InterruptedException {
    WebElement selectedSourceElement = driver()
        .findElement(By.xpath("//*[@id='pricing-sheet-table']/tr[" + source + "]/td[1]/span"));

    Thread.sleep(sleepTime);
    Actions act = new Actions(driver());
    act.clickAndHold(selectedSourceElement).build().perform();
    WebElement selectedDropElement = driver()
        .findElement(By.xpath("//*[@id='pricing-sheet-table']/tr[" + destination + "]/td[1]/span"));
    act.moveToElement(selectedDropElement).build().perform();
    act.release(selectedDropElement).build().perform();
    }

  /**
   * This method enters on Lot Bid Decrement Value.
   */
  public void lotBidDecrementValue(final String lotId, final String bidDecrementVal) {
    WebElement bidDecrement = pricingSheetPage.lotBidDecrement(lotId);
    js.executeScript("arguments[0].scrollIntoView();", bidDecrement);
    bidDecrement.sendKeys(Keys.CONTROL + "a");
    bidDecrement.sendKeys(Keys.DELETE);
    sendkeys(driver(), bidDecrement, TestUtil.getTimeOut(), bidDecrementVal);

    }

  /**
   * This method enters on Lot Bid Buffer Value.
   */
  public void lotBidBufferValue(final String lotId, final String bidBufferVal) {

    WebElement protectionBuffer = pricingSheetPage.lotProtectionBidBuffer(lotId);
    js.executeScript("arguments[0].scrollIntoView();", protectionBuffer);
    protectionBuffer.sendKeys(Keys.CONTROL + "a");
    protectionBuffer.sendKeys(Keys.DELETE);
    sendkeys(driver(), protectionBuffer, TestUtil.getTimeOut(), bidBufferVal);

    }

  /**
   * This method enters on PTF Value.
   */
  public void setPTFtoSupplier(final XlsReader reader, final String sheetName) throws InterruptedException {
    System.out.println("setPTFtoSupplier>>>");
    Thread.sleep(sleepTime);
    int sheetRowCount = reader.getRowCount(sheetName);
    int sheetColCount = reader.getColumnCount(sheetName);
    WebElement ptfValue = null;
    for (int columns = 1; columns < sheetColCount; columns++) {
      for (int rows = 0; rows < sheetRowCount - 1; rows++) {
        String dataTest = reader.getCellData(sheetName, columns, 1);
        int tdCount = columns + 1;

        if (dataTest.contains("Price")) {
          ptfValue = driver()
              .findElement(By.xpath("//table[@data-test ='ptfTable']/tbody/tr[@data-testid = 'ptfRow"
                  + rows + "']/td[" + tdCount + "]/span/input[@data-test='ptf-" + dataTest + "']"));
          } else {
          ptfValue = driver()
              .findElement(By.xpath("//table[@data-test ='ptfTable']/tbody/tr[@data-testid = 'ptfRow"
                  + rows + "']/td[" + tdCount + "]/input[@data-test='ptf-" + dataTest + "']"));
          }

        sendkeys(driver(), ptfValue, TestUtil.getTimeOut(), reader.getCellData(sheetName, columns, rows + 2));
        }
      }

    }

  /**
   * This method clicks on Formula For Transform Price Field.
   */
  public void formulaForTransformPrice(final String newFormula) throws InterruptedException {
    final int timeThread = 500;
    Thread.sleep(timeThread);
    js.executeScript("arguments[0].scrollIntoView();", pricingSheetPage.getCreateFormula());
    pricingSheetPage.getCreateFormula().clear();
    sendkeys(driver(), pricingSheetPage.getCreateFormula(), TestUtil.getTimeOut(), newFormula);

    }

  /**
   * This method clicks Yes Or No button.
   */
  public void performYesNoButton(final String yesNo) {
    if (yesNo.equalsIgnoreCase("Yes")) {
      clickOn(driver(), pricingSheetPage.getConfirmAction(), TestUtil.getTimeOut());
      }
    if (yesNo.equalsIgnoreCase("No")) {
      clickOn(driver(), pricingSheetPage.getCancelAction(), TestUtil.getTimeOut());
      }

    }


  /**
   * This method clicks on navigation button.
   */
  public void performNavigation(final String navigationTab) throws InterruptedException {
    final int timeSlap = 3000;
    Thread.sleep(timeSlap);
    WebElement navigationLink = pricingSheetPage.navigationTab(navigationTab);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", navigationLink);
    navigationLink.click();
    Thread.sleep(timeSlap);
    }

  /**
   * This method clicks on Monitor Phase.
   */
  public void performMonitorNavigation(final String navigationTab) throws InterruptedException {
    Thread.sleep(sleepTime);
    WebElement navigationLink = pricingSheetPage.navigationMonitorTab(navigationTab);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", navigationLink);

    navigationLink.click();
    Thread.sleep(sleepTime);
    }

  /**
   * This method clicks on Clear All Items field.
   */
  public void clearAllItems(final String lotNo) {
    WebElement clearAllItems = pricingSheetPage.clearAllItemsElement(lotNo);
    clickOn(driver(), clearAllItems, TestUtil.getTimeOut());

    }

  /**
   * This method enter the lineItems Price for that particular lot id and sheet name.
   */
  public void lineItemsPrice(final XlsReader reader, final String lotId, final String sheetName) throws InterruptedException {
    WebElement idLot = pricingSheetPage.getLotId(lotId);
    WebElement chevronRightIcon = idLot.findElement(By.tagName("mat-icon"));
    if (chevronRightIcon.getText().equals("chevron_right")) {
      clickOn(driver(), chevronRightIcon, TestUtil.getTimeOut());
      }

    int sheetRowCount = reader.getRowCount(sheetName);
    System.out.println("sheetRowCount>>>>" + sheetRowCount);
    int sheetColCount = reader.getColumnCount(sheetName);
    System.out.println("sheetColCount>>>>" + sheetColCount);
    int cellCount = (sheetRowCount - 1) * sheetColCount;
    System.out.println("cellCount>>>>" + cellCount);
    Thread.sleep(sleepTime);
    WebElement firstCell = idLot.findElement(
        By.cssSelector("[data-testid = 'row0'] [data-test = '" + reader.getCellData(sheetName, 0, 1) + "']"));
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", firstCell);
    clickOn(driver(), firstCell, TestUtil.getTimeOut());

    for (int count = 1; count < cellCount; count++) {

      // Thread.sleep(1000);
      // js.executeScript("arguments[0].scrollIntoView();",
      // driver.switchTo().activeElement());

      driver().switchTo().activeElement().sendKeys(Keys.TAB);
      }
    for (int intRow = 0; intRow < sheetRowCount - 1; intRow++) {
      for (int intCol = 0; intCol < sheetColCount; intCol++) {
        String columnName = reader.getCellData(sheetName, intCol, 1);
        WebElement rows = idLot.findElement(
            By.cssSelector("[data-testid = 'row" + intRow + "'] [data-test = '" + columnName + "']"));
        if (reader.getCellData(sheetName, intCol, 1).equalsIgnoreCase("Baseline Price")) {
          WebElement basePrice = rows.findElement(By.xpath(".//span/span[2]"));
          sendkeys(driver(), basePrice, TestUtil.getTimeOut(), reader.getCellData(sheetName, intCol, intRow + 2));
          } else {
          sendkeys(driver(), rows, TestUtil.getTimeOut(), reader.getCellData(sheetName, intCol, intRow + 2));
          }
        }

      }

    }

  /**
   * This method enter the ceiling price.
   */
  public void setCeilingPrice(final XlsReader reader, final String lotId, final String sheetName)
      throws InterruptedException, AWTException {
    int sheetRowCount = reader.getRowCount(sheetName);
    int sheetColCount = reader.getColumnCount(sheetName);

    driver().switchTo().frame(pricingSheetPage.getIframeElement());
    Thread.sleep(sleepTime);
    String dataLotTrim;
    for (int columns = 1; columns < sheetColCount; columns++) {
      for (int rows = 1; rows < sheetRowCount; rows++) {
        System.out.println("Test 1>>>>" + reader.getCellData(sheetName, columns, 1));
        System.out.println("2>>>>" + reader.getCellData(sheetName, columns, 1).length());
        if (reader.getCellData(sheetName, columns, 1).length() > stringLength) {
          dataLotTrim = reader.getCellData(sheetName, columns, 1).substring(0, stringLength);
          } else {
          dataLotTrim = reader.getCellData(sheetName, columns, 1).substring(0,
             Math.min(reader.getCellData(sheetName, columns, 1).length(), TestUtil.getTimeOut()));
          }
        //String dataLotTrim = reader.getCellData(sheetName, columns, 1).substring(0,20);
        System.out.println("DataLotTrim >>>>" + dataLotTrim);
        WebElement ceilingElement = driver().findElement(By.xpath("//*[@data-lot = '" + dataLotTrim
            + "' and @data-supplier='" + reader.getCellData(sheetName, 0, rows + 1) + "']"));
        sendkeys(driver(), ceilingElement, TestUtil.getTimeOut(), reader.getCellData(sheetName, columns, rows + 1));

        }
      }
    driver().switchTo().defaultContent();
    }

  /**
   * This method enter the sheet name for supplier.
   */
  public void createSupplier(final XlsReader reader, final String sheetName) throws InterruptedException, AWTException {
    int sheetRowCount = reader.getRowCount(sheetName);
    for (int i = 2; i <= sheetRowCount; i++) {
      String command = reader.getCellData(sheetName, 0, i);
      String argument1 = reader.getCellData(sheetName, 1, i);
      String argument2 = reader.getCellData(sheetName, 2, i);
      String argument3 = reader.getCellData(sheetName, count3, i);
      String argument4 = reader.getCellData(sheetName, count4, i);
      supplierProcessor.processCommand(reader, command, argument1, argument2, argument3, argument4);

      }
    }

  /**
   * This method enter the Pricing Sheet Summary details.
   */
  public void pricingSheetSummary(final String lotSerialNo, final String lotName, final String noOfLineItem, final String baselinePrice)
      throws InterruptedException, AWTException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView(true);", pricingSheetPage.getSummaryTable());
    Thread.sleep(sleepTime);
    WebElement lotNumber = driver()
        .findElement(By.xpath("//*[@id = 'pricing-sheet-table']/tr[" + lotSerialNo + "]/td[1]/span/span"));
    Assert.assertEquals(lotNumber.getText(), lotSerialNo);

    WebElement lotNamePS = driver().findElement(
        By.xpath("//*[@id = 'pricing-sheet-table']/tr[" + lotSerialNo + "]/td[2][@data-test = 'ps-lot-name']"));
    Assert.assertEquals(lotNamePS.getText(), lotName);
    WebElement noOfItems = driver()
        .findElement(By.xpath("//*[@id='pricing-sheet-table']/tr[" + lotSerialNo + "]/td[3]"));
    Assert.assertEquals(noOfItems.getText(), noOfLineItem);
    WebElement baseLine = driver()
        .findElement(By.xpath("//*[@id='pricing-sheet-table']/tr[" + lotSerialNo + "]/td[4]/span/span[2]"));


    Format formatter = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
   //Format formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
    String baselinePriceFinal = formatter.format(Double.parseDouble(baselinePrice)).substring(1);
    System.out.println("1>>>" + baselinePriceFinal);

  //  String baselinePriceFinal1 = formatter.format(new BigDecimal(baselinePrice)).substring(1);
  //  System.out.println("2>>>"+baselinePriceFinal1);
    Assert.assertEquals(baseLine.getText(), baselinePriceFinal);

    }

  /**
   * This method clicks on delete All Lots.
   */
  public void deleteAllLots() throws InterruptedException {
    Thread.sleep(sleepTime);
    try {
      if (pricingSheetPage.getDeleteLots().isDisplayed()) {
        pricingSheetPage.getDeleteLots().click();
        Thread.sleep(sleepTime);
        }
      } catch (NoSuchElementException e) {
      System.out.println(e);
      }
    }

  /**
   * This method enter the Add Term Name Field.
   */
  public void addTermName(final String termName) throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", pricingSheetPage.getTermNameField());
    pricingSheetPage.getTermNameField().clear();
    final int timeSleep = 800;
    Thread.sleep(timeSleep);
    sendkeys(driver(), pricingSheetPage.getTermNameField(), TestUtil.getTimeOut(), termName);

    }

  /**
   * This method clicks on WhoFills Field.
   */
  public void setWhoFills(final String whoFillsType) throws InterruptedException {
    final int timeSleep = 300;
    Thread.sleep(timeSleep);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", pricingSheetPage.getWhoFillsField());
    clickOn(driver(), pricingSheetPage.getWhoFillsField(), TestUtil.getTimeOut());
    WebElement selectedFillType = pricingSheetPage.whoFillsOption(whoFillsType);
    clickOn(driver(), selectedFillType, TestUtil.getTimeOut());

    }

  /**
   * This method click Price Transformation Factor Field.
   */
  public void priceTransformationFactorFunc() {
    clickOn(driver(), pricingSheetPage.getPriceTransformationFactorField(), TestUtil.getTimeOut());
    }


  /**
   * This method clicks on save button.
   */
  public void performSave() {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", pricingSheetPage.getSaveColumn());
    clickOn(driver(), pricingSheetPage.getSaveColumn(), TestUtil.getTimeOut());

    }

  /**
   * This method clicks on Data Need All Item Field.
   */
  public void dataNeedForAllitem() {
    clickOn(driver(), pricingSheetPage.getDataNeedForAllLineItem(), TestUtil.getTimeOut());

    }

  /**
   * This method clicks Input Field Type Option.
   */
  public void setInputFieldType(final String fieldType) throws InterruptedException {
    clickOn(driver(), pricingSheetPage.getInputFieldTypeField(), TestUtil.getTimeOut());
    WebElement selectedQuestionType = pricingSheetPage.fieldOption(fieldType);
    Thread.sleep(sleepTime);
    clickOn(driver(), selectedQuestionType, TestUtil.getTimeOut());

    }

  /**
   * This method clicks on requires for formula field.
   */
  public void setRequiredFormula(final String formula) {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", pricingSheetPage.getRequiresForFormulaField());
    String str = pricingSheetPage.getRequiresForFormulaField().getText();
    String[] splitStr = str.split("\\s+");
    if (formula.contains(splitStr[0])) {
      System.out.println("Formula If Loop");
      } else {
      clickOn(driver(), pricingSheetPage.getRequiresForFormulaField(), TestUtil.getTimeOut());
      }

    }


  /**
   * This method clicks on Extend Price Pencil Icon.
   */
  public void changeEPFormula() throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", pricingSheetPage.getExtendPricePencil());
    clickOn(driver(), pricingSheetPage.getExtendPricePencil(), TestUtil.getTimeOut());
    }

  /**
   * This method enter the create formula field.
   */
  public void createFormula(final String formula) throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", pricingSheetPage.getCreateFormulaField());
    pricingSheetPage.getCreateFormulaField().clear();
    Thread.sleep(sleepTime);
    sendkeys(driver(), pricingSheetPage.getCreateFormulaField(), TestUtil.getTimeOut(), formula);

    }

  /**
   * This method clicks require total field.
   */
  public void setRequiresLot(final String yesNo) throws InterruptedException {
    Thread.sleep(sleepTime);
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", pricingSheetPage.getRequiresTotalField());
    String str = pricingSheetPage.getRequiresTotalField().getText();
    String[] splitStr = str.split("\\s+");
    if (yesNo.contains(splitStr[0])) {
      System.out.println("if loop");
      } else {
      clickOn(driver(), pricingSheetPage.getRequiresTotalField(), TestUtil.getTimeOut());
      }
    }

  /**
   * This method clicks on chevron right on lot Id.
   */
  public void headerTableSelect(final String lotId, final String colName) throws InterruptedException {
    Thread.sleep(sleepTime);
    WebElement idLot = pricingSheetPage.getLotId(lotId);
    WebElement chevronRightIcon = idLot.findElement(By.tagName("mat-icon"));

    if (chevronRightIcon.getText().equals("chevron_right")) {
      clickOn(driver(), chevronRightIcon, TestUtil.getTimeOut());
      }

    String dataTestValue = "lot-table-row-" + lotId;
    List<WebElement> rows = idLot.findElements(By.xpath("//*[@data-test='" + dataTestValue + "']/th"));
    String colNameDuplicate = "header-" + colName;
    for (WebElement row : rows) {
      if ((colNameDuplicate).equals(row.getAttribute("data-test"))) {
        WebElement headerColName = row.findElement(By.xpath(".//span"));
        Thread.sleep(sleepTime);
        clickOn(driver(), headerColName, TestUtil.getTimeOut());

        }
      }

    }

  /**
   * This method clicks on alternate bid criteria field.
   */
  public void alternateBidCriteria(final String bidCriteriaValue) {
    clickOn(driver(), pricingSheetPage.getBidCriteria(), TestUtil.getTimeOut());

    }

  /**
   * This method clicks in duplicate column button.
   */
  public void performDuplicateColumn() throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", pricingSheetPage.getDuplicateColumnButton());
    clickOn(driver(), pricingSheetPage.getDuplicateColumnButton(), TestUtil.getTimeOut());

    }

  /**
   * This method enter minimum value in that field.
   */
  public void setMinimumValue(final String minimumVal) {
    sendkeys(driver(), pricingSheetPage.getMinValueField(), TestUtil.getTimeOut(), minimumVal);

    }

  /**
   * This method enter maximum value in that field.
   */
  public void setMaximumValue(final String maximumVal) {
    sendkeys(driver(), pricingSheetPage.getMaxValueField(), TestUtil.getTimeOut(), maximumVal);
    }

  /**
   * This method clicks delete button.
   */
  public void performDeleteColumn() throws InterruptedException {
    ((JavascriptExecutor) this.driver()).executeScript("arguments[0].scrollIntoView();", pricingSheetPage.getDeleteColumnButton());
    clickOn(driver(), pricingSheetPage.getDeleteColumnButton(), TestUtil.getTimeOut());

    }

  /**
   * This method clicks confirm button.
   */
  public void performConfirmAction() throws InterruptedException {
    clickOn(driver(), pricingSheetPage.getConfirmAction(), TestUtil.getTimeOut());
    }

  /**
   * This method clicks Assign Invite Suppliers button.
   */
  public void performAssignInviteSuppliers(final String inviteSupplier) throws InterruptedException {
    System.out.println("InviteSuppliers >>>>" + inviteSupplier);
    driver().switchTo().frame(pricingSheetPage.getIframeElement());
    Thread.sleep(sleepTime);
    WebElement assignedSupplier = pricingSheetPage.supplierInvite(inviteSupplier);
    assignedSupplier.click();
    driver().switchTo().defaultContent();
    }

  /**
   * This method clicks Action button.
   */
  public void performMainAction(final String mainAction) throws InterruptedException {
    try {
      final int threadSleep = 10000;
      Thread.sleep(threadSleep);
      // driver.switchTo().parentFrame();
      pricingSheetPage.getMainAction().click();
      } catch (WebDriverException e) {
      pricingSheetPage.getMainAction().click();
      } catch (Exception ee) {
      ee.printStackTrace();
      throw ee;
      }
    WebElement mainActions = pricingSheetPage.mainActionOption(mainAction);
    mainActions.click();
    }

  /**
   * This method have assertion cases.
   */
  public void assertionCases(final String argument1, final String argument2) throws InterruptedException {
    switch (argument1) {
      case "LotCount":
        bulkUploadLotsAssertion.countOfLots(Integer.parseInt(argument2));
        break;
      case "ExtendPricePinkShade":
        extendPriceShadeVal();
        break;
      case "ClearAllItemGrey":
        clearAllItemGrey(argument2);
        break;
      case "countPricingSheetSummary":
        countPricingSheetSummary(argument2);
        break;
      case "assertCurrency":
        assertCurrency(argument2);
        break;
      case "assertPTFTable":
        assertPTFTable(argument2);
        break;
      case "FormulaErrorMessage":
        assertEventRules.columnMessage(argument2);
        break;
      case "IsLineitemCleared":
        assertLineItemCleared();
        break;
      case "IsClearLineItemGreyed":
        assertClearLineItemGreyed();
        break;
      case "LotDescription":
        assertLotDescription(argument2);
        break;
      case "ToastMessage":
        assertEventRules.assertToastMsg(argument2);
        break;
      case "AssertIsFieldEditable":
        assertIsFieldEditable(argument2);
        break;
      case "BulkUploadErrors":
        bulkUploadLotsAssertion.verifyErrors(argument2);
        break;
      default:
        System.out.println("no switch match");
      }
    }
  }
