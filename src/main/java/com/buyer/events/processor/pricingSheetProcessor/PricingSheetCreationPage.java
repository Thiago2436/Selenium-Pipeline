package com.buyer.events.processor.pricingSheetProcessor;

import java.util.List;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PricingSheetCreationPage  {
  private JavascriptExecutor js;
  private WebDriver driver;
  final int sleepTime = 1000;


  public PricingSheetCreationPage(final WebDriver drivers) {
    this.driver = drivers;
    js = (JavascriptExecutor) drivers;
    PageFactory.initElements(drivers, this);
    }

  public final WebDriver getDriver() {
    return driver;
    }

  @FindBy(id = "createNewLot")
  private WebElement createNewLot;

  @FindBy(xpath = "//*[@id='supplier-part']/div/div[2]/div[3]/button")
  private WebElement mainAction;

  @FindBy(xpath = "//*[@data-test = 'clear']")
  private WebElement clearAllItem;

  @FindBy(xpath = "//*[@data-test = 'addColumn']")
  private WebElement addCircleOutline;

  @FindBy(xpath = "//*[@id='cdk-drop-list-1']/th[8]")
  private WebElement transformPricePencilIcon;

  @FindBy(id = "publishEvent")
  private WebElement publishEvent;

  @FindBy(id = "confirmAction")
  private WebElement confirmAction;

  @FindBy(id = "formulaField")
  private WebElement createFormula;

  @FindBy(id = "cancelAction")
  private WebElement cancelAction;

  @FindBy(id = "saveColumn")
  private WebElement saveColumn;

  @FindBy(id = "suppliersChevron")
  private WebElement suppliersChevron;

  @FindBy(xpath = "//*[@id='toast-container']/div/div")
  private WebElement toastMessage;

  @FindBy(className = "summary-table")
  private WebElement summaryTable;

  @FindBy(xpath = "//*[@class='cdk-drop-list']/th[@data-test = 'header-Extended Price']")
  private WebElement extendedPricePinkShade;

  @FindBy(id = "addLotFromLibrary")
  private WebElement addLotFromLibraryButton;

  @FindBy(id = "searchLibrary")
  private WebElement openKnowledgeLibrary;

  @FindBy(id = "addFromLibrary")
  private WebElement addFromLibraryButton;

  @FindBy(id = "sharedLibrary")
  private WebElement sharedLibraryButton;

  @FindBy(xpath = "//*[@data-test = 'bidAction']")
  private WebElement bidAction;

  @FindBy(className = "cdk-overlay-pane")
  private WebElement overlayPricingSheet;

  @FindBy(id = "assignment")
  private WebElement iframeElement;

  @FindBy(xpath = "//*[@data-test = 'lotTable']")
  private WebElement lotTableElement;

  @FindBy(className = "toast-close-button")
  private WebElement toastCloseButton;

  @FindBy(id = "term_nameField")
  private WebElement termNameField;

  @FindBy(id = "who_fillsField")
  private WebElement whoFillsField;

  @FindBy(id = "price_transformation_factorField")
  private WebElement priceTransformationFactorField;

  @FindBy(id = "data_need_for_all_line_itemsField")
  private WebElement dataNeedForAllLineItem;

  @FindBy(id = "input_field_typeField")
  private WebElement inputFieldTypeField;

  @FindBy(id = "required_for_formulaField")
  private WebElement requiresForFormulaField;

  @FindBy(id = "requires_a_totalField")
  private WebElement requiresTotalField;

  @FindBy(id = "alternate_bid_from_supplierField")
  private WebElement bidCriteria;

  @FindBy(id = "duplicateColumn")
  private WebElement duplicateColumnButton;

  @FindBy(xpath = "//*[@data-placeholder = 'Add Min']")
  private WebElement minValueField;

  @FindBy(xpath = "//*[@data-placeholder = 'Add Max']")
  private WebElement maxValueField;

  @FindBy(id = "deleteColumn")
  private WebElement deleteColumnButton;

  @FindBy(id = "deleteAllLots")
  private WebElement deleteLots;

  @FindBy(css = "[data-test = 'addLot']")
  private WebElement addLot;

  @FindBy(xpath =
      "//*[@class = 'layout_flex__3cRl8 layout_contentCenter__nQ4Br layout_itemsCenter__3FenQ layout_flex1__rurke layout_borderLeft__3yZlO editable']"
      + "/span")
  private WebElement currencyTable;

  @FindBy(xpath = "/html/body/fuse-root/fuse-main/mat-sidenav-container/mat-sidenav-content/div/div/div/fuse-content/app-newsource/div/"
      + "mat-sidenav-container/mat-sidenav-content/div/div[3]/event-pricing-sheet/div/lot/div/mat-expansion-panel"
      + "/div/div/div/div[3]/div/table[1]/thead/tr[1]/th[6]")
  private WebElement extendPricePencil;

  @FindBy(id = "formulaField")
  private WebElement createFormulaField;

  public final WebElement getRequiresTotalField() {
    return requiresTotalField;
    }

  public final WebElement getCreateNewLot() {
    return createNewLot;
    }

  public final WebElement getMainAction() {
    return mainAction;
    }

  public final WebElement getClearAllItem() {
    return clearAllItem;
    }

  public final WebElement getAddCircleOutline() {
    return addCircleOutline;
    }

  public final WebElement getTransformPricePencilIcon() {
    return transformPricePencilIcon;
    }

  public final WebElement getPublishEvent() {
    return publishEvent;
    }

  public final WebElement getConfirmAction() {
    return confirmAction;
    }

  public final WebElement getCreateFormula() {
    return createFormula;
    }

  public final WebElement getCancelAction() {
    return cancelAction;
    }

  public final WebElement getSaveColumn() {
    return saveColumn;
    }

  public  final WebElement getSuppliersChevron() {
    return suppliersChevron;
    }

  public final WebElement getToastMessage() {
    return toastMessage;
    }

  public final WebElement getSummaryTable() {
    return summaryTable;
    }

  public final WebElement getExtendedPricePinkShade() {
    return extendedPricePinkShade;
    }

  public final WebElement getAddLotFromLibraryButton() {
    return addLotFromLibraryButton;
    }

  public final WebElement getOpenKnowledgeLibrary() {
    return openKnowledgeLibrary;
    }

  public  final WebElement getAddFromLibraryButton() {
    return addFromLibraryButton;
    }

  public final WebElement getSharedLibraryButton() {
    return sharedLibraryButton;
    }

  public final WebElement getBidAction() {
    return bidAction;
    }

  public final WebElement getOverlayPricingSheet() {
    return overlayPricingSheet;
    }

  public final WebElement getIframeElement() {
    return iframeElement;
    }

  public final WebElement getLotTableElement() {
    return lotTableElement;
    }

  public final WebElement getToastCloseButton() {
    return toastCloseButton;
    }

  public final WebElement getTermNameField() {
    return termNameField;
    }

  public final WebElement getWhoFillsField() {
    return whoFillsField;
    }

  public final WebElement getPriceTransformationFactorField() {
    return priceTransformationFactorField;
    }

  public final WebElement getRequiresForFormulaField() {
    return requiresForFormulaField;
    }

  public final WebElement getBidCriteria() {
    return bidCriteria;
    }

  public final WebElement getDuplicateColumnButton() {
    return duplicateColumnButton;
    }

  public final WebElement getMinValueField() {
    return minValueField;
    }

  public final WebElement getMaxValueField() {
    return maxValueField;
    }

  public final WebElement getDeleteColumnButton() {
    return deleteColumnButton;
    }

  public final WebElement getCurrencyTable() {
    return currencyTable;
    }

  public final WebElement getExtendPricePencil() {
    return extendPricePencil;
    }

  public final WebElement getCreateFormulaField() {
    return createFormulaField;
    }
  public final WebElement getDataNeedForAllLineItem() {
    return dataNeedForAllLineItem;
    }

  public final WebElement getInputFieldTypeField() {
    return inputFieldTypeField;
    }

  public final WebElement getDeleteLots() {
    return deleteLots;
    }

  public final WebElement getAddLot() {
    return addLot;
    }

  public final WebElement getLotId(final String lotId) {
    System.out.println("lotId>>>>" + lotId);
    StringTokenizer splitlot = new StringTokenizer(lotId, ".");
    String lotIdStr = "lot-" + splitlot.nextToken();

    WebElement idLot = driver.findElement(By.id(lotIdStr));

    return idLot;
    }

  public final WebElement bidActionField(final WebElement idLot) {
    WebElement bidActionFields = idLot.findElement(By.tagName("mat-select"));
    return bidActionFields;
    }

  public final WebElement getPencilImg(final WebElement idLot, final  String lotIdStr) {
    WebElement pencilImg = idLot.findElement(By.cssSelector("#" + lotIdStr + " [data-test = 'editName']"));

    return pencilImg;
    }

  public final WebElement setTransformPrice(final String supplierName) throws InterruptedException {
    System.out.println("supplierName>>>" + supplierName);
    Thread.sleep(sleepTime);
    WebElement transformPrice = driver.findElement(By.xpath("//span[contains(text(),'" + supplierName
        + "')]/parent::td//following-sibling::td//input[@class ='editable-input']"));
    return transformPrice;
    }

  public final WebElement setTransformPrice1(final String supplierName) throws InterruptedException {
    System.out.println("supplierName>>>" + supplierName);
    Thread.sleep(sleepTime);
    WebElement transformPrice = driver.findElement(By.xpath("//span[contains(text(),'" + supplierName
        + "')]/parent::td[2]//following-sibling::td//input[@class ='editable-input']"));
    return transformPrice;
    }

  public final WebElement navigationTab(final String tabLink) {
    String link = tabLink + "Chevron";
    System.out.println("Link>>>." + link);
    WebElement tabChevron = driver.findElement(By.id(link));
    return tabChevron;
    }

  public  final WebElement navigationMonitorTab(final String tabLink) {

    WebElement tabChevron = driver.findElement(By.xpath("//*[@routerlink = '" + tabLink + "']"));
    return tabChevron;
    }

  /**
   * This method clicks on publish button.
   */
  public void publishButtonFunc() {
    publishEvent.click();
    }

  public final  WebElement lotDescriptionElement(final String lotId) {
    WebElement idLot = getLotId(lotId);
    WebElement lotDescript = idLot.findElement(By.tagName("textarea"));
    return lotDescript;
    }

  public final WebElement chevronRightIcon(final String lotId) {
    WebElement idLot = getLotId(lotId);
    WebElement chevronRightIcon = idLot.findElement(By.tagName("mat-icon"));

    return chevronRightIcon;
    }

  public  final WebElement importLot(final String sheetName) {
    WebElement selectedLot = overlayPricingSheet.findElement(By.xpath("//*[contains(text(),'" + sheetName + "')]"));
    return selectedLot;
    }

  public final List<WebElement> ptfTableElement(final String lotId) {

    WebElement lotNumber = driver.findElement(By.id("lot-" + lotId));
    // Get all options in a list
    List<WebElement> ptfTable = lotNumber.findElements(By.xpath("//*[@data-test = 'ptfTable']"));

    return ptfTable;
    }

  public  final WebElement lotBidDecrement(final String lotId) {

    StringTokenizer splitlot = new StringTokenizer(lotId, ".");
    String lotIdStr = "lot-" + splitlot.nextToken();

    WebElement idLot = driver.findElement(By.id(lotIdStr));
    WebElement chevronRightIcon = idLot.findElement(By.tagName("mat-icon"));

    if (chevronRightIcon.getText().equals("chevron_right")) {
      chevronRightIcon.click();

      }
    WebElement bidDecrement = idLot.findElement(By.cssSelector("#" + lotIdStr + " [data-test = 'bidMovement']"));
    return bidDecrement;
    }

  public final WebElement lotProtectionBidBuffer(final String lotId) {
    StringTokenizer splitlot = new StringTokenizer(lotId, ".");
    String lotIdStr = "lot-" + splitlot.nextToken();
    WebElement idLot = driver.findElement(By.id(lotIdStr));
    WebElement chevronRightIcon = idLot.findElement(By.tagName("mat-icon"));
    if (chevronRightIcon.getText().equals("chevron_right")) {
      chevronRightIcon.click();
      }

    WebElement protectionBuffer = idLot
        .findElement(By.cssSelector("#" + lotIdStr + " [data-test = 'protectionBuffer']"));
    return protectionBuffer;
    }

  public final WebElement clearAllItemsElement(final String lotId) {

    WebElement lotNumber = driver.findElement(By.id("lot-" + lotId));
    // Get all options in a list
    WebElement clearAllItems = lotNumber.findElement(By.xpath("//*[@data-test = 'clear']"));

    return clearAllItems;
    }

  public final  WebElement whoFillsOption(final String whoFillsType) {

    WebElement overlayAuctionType = driver.findElement(By.className("cdk-overlay-container"));
    // Get all options in a list
    WebElement fillsOptions = overlayAuctionType
        .findElement(By.xpath("//*[@class = 'mat-option-text' and contains(text(),'" + whoFillsType + "')]"));

    return fillsOptions;
    }

  public  final WebElement fieldOption(final String fieldType) {

    WebElement overlayAuctionType = driver.findElement(By.className("cdk-overlay-container"));
    // Get all options in a list
    WebElement fieldOptions = overlayAuctionType
        .findElement(By.xpath("//*[@class = 'mat-option-text' and contains(text(),'" + fieldType + "')]"));

    return fieldOptions;
    }

  public final WebElement actionMenuOption(final String action) {

    WebElement actionOption = driver.findElement(By.xpath("//*[@data-row='" + action + "']"));

    return actionOption;
    }

  public final WebElement supplierInvite(final String supplierId) {
    WebElement supplierIdElement = driver.findElement(By.id(supplierId));
    return supplierIdElement;
    }

  public final WebElement mainActionOption(final String actionOpt) {

    WebElement actionMainOption = driver.findElement(By.xpath("//button[contains(text(),'" + actionOpt + "')]"));

    return actionMainOption;
    }

  public final WebElement lotActionElement(final String lotId) throws InterruptedException {
    Thread.sleep(sleepTime);
    StringTokenizer splitlot = new StringTokenizer(lotId, ".");
    String lotIdStr = "lot-" + splitlot.nextToken();
    WebElement idLot = driver.findElement(By.id(lotIdStr));
    Thread.sleep(sleepTime);
    WebElement actionDropDown = idLot.findElement(By.cssSelector("#" + lotIdStr + " [data-test = 'lotActions']"));
    return actionDropDown;
    }

  }
