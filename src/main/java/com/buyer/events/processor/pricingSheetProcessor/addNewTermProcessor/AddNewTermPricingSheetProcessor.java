package com.buyer.events.processor.pricingSheetProcessor.addNewTermProcessor;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.buyer.events.processor.ITabCommandProcessor;
import com.buyer.events.processor.pricingSheetProcessor.PricingSheetCreationPage;
import com.sol.qa.base.TestBase;
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.TestUtil;
import com.sol.qa.util.XlsReader;


public class AddNewTermPricingSheetProcessor extends TestBase implements ITabCommandProcessor {

  private AddNewTermPricingSheetPage newTermCreationPage = new AddNewTermPricingSheetPage();
  private PricingSheetCreationPage pricingSheetPage;
  private JavascriptExecutor js = (JavascriptExecutor) getDriver();
  final int sleepTime = 1000;
  private WindowManager windowManager;

  public AddNewTermPricingSheetProcessor(final WindowManager windowManger) {
    this.windowManager = windowManger;
    System.out.println("windowManager OverviewProcessorCommand>>>" + windowManger);
    js = (JavascriptExecutor) this.driver();
    pricingSheetPage = new PricingSheetCreationPage(this.driver());
    }

  private RemoteWebDriver driver() {
    return  this.windowManager.getActiveDriver();
    }

  /**
   *This method have XLs_Reader , command , argument1 , argument2 , argument3 and argument4 as Parameter
   *It have switch command and its corresponding method.
   */
  public void processCommand(final XlsReader reader, final String command, final String argument1, final  String argument2,
      final String argument3, final String argument4)
      throws AWTException, InterruptedException {
    switch (command) {
      case "addTermName":
        addTermNameFunc(argument1);
        break;
      case "whoFills":
        whoFillsFunc(argument1);
        break;
      case "PriceTransformationFactor":
        priceTransformationFactorFunc(argument1);
        break;
      case "SaveButton":
        saveButtonFunc();
        break;
      case "ChangeColumnPosition":
        changeColumnPositionFunc(argument1);
        break;
      case "DataNeedForAllitem":
        dataNeedForAllitemFunc(argument1);
        break;
      case "InputFieldType":
        inputFieldTypeFunc(argument1);
        break;
      case "RequiredFormula":
        requiredFormulaFunc(argument1);
        break;
      case "ChangeExtendedPriceFormula":
        changeEPFormulaFunc();
        break;
      case "CreateFormula":
        createFormulaFunc(argument1);
        break;
      case "RequiresALot":
        requiresLotFunc(argument1);
        break;
      case "EnterLineItems":
        lineItemsPrice(reader, argument1, argument2);
        break;
      case "AlternateBidCriteria":
        alternateBidCriteria(argument1);
        break;
      case "ColumnToEdit":
        headerTableSelect(argument1, argument2);
        break;
      case "DuplicateColumn":
        duplicateColumnButton();
        break;
      case "SetMinimumValue":
        setMinimumValue(argument1);
        break;
      case "SetMaximumValue":
        setMaximumValue(argument1);
        break;
      case "DeleteColumn":
        deleteColumnButton();
        break;
      case "ConfirmAction":
        confirmActionButton();
        break;
      default:
        System.out.println("no match");
      }

    }

  /**
   * This method gets termName as Parameter and enter the new term name.
   */
  public  void addTermNameFunc(final String termName) throws InterruptedException {
    js.executeScript("arguments[0].scrollIntoView();", newTermCreationPage.getTermNameField());
    newTermCreationPage.getTermNameField().clear();
    Thread.sleep(sleepTime);
    newTermCreationPage.getTermNameField().sendKeys(termName);
    }

  /**
   * This method gets Yes or No for the Data Need For All Items.
   */
  public void dataNeedForAllitemFunc(final String allItem) {
    System.out.println("AllItem>>>>" + allItem);
    newTermCreationPage.getDataNeedForAllLineItem().click();
    }

  /**
   * This method gets formula as Parameter clicks on the required Formula Function Field.
   */
  public void requiredFormulaFunc(final String formula) {
    System.out.println("Requires For Formula Field");
    js.executeScript("arguments[0].scrollIntoView();", newTermCreationPage.getRequiresForFormulaField());
    //String str = newTermCreationPage.getRequiresForFormulaField().getText();
    //String[] splitStr = str.split("\\s+");
    /*
     * if(formula.contains(splitStr[0])) { } else { }
     */

    }

  /**
   * This method gets formula as Parameter and get the value.
   */
  public void createFormulaFunc(final String formula) throws InterruptedException {
    js.executeScript("arguments[0].scrollIntoView();", newTermCreationPage.getCreateFormulaField());
    newTermCreationPage.getCreateFormulaField().clear();
    Thread.sleep(sleepTime);
    newTermCreationPage.getCreateFormulaField().sendKeys(formula);
    }

  /**
   * This method gets yes or no for the toggle button.
   */
  public void requiresLotFunc(final String yesNo) throws InterruptedException {
    System.out.println("Requires A Total Field");
    Thread.sleep(sleepTime);
    js.executeScript("arguments[0].scrollIntoView();", newTermCreationPage.getRequiresTotalField());
    System.out.println("YesNo String >>>" + yesNo.trim() + "Checking");
    System.out.println("Get Text >>>" + newTermCreationPage.getRequiresTotalField().getText().trim() + "Checking2");


    String str = newTermCreationPage.getRequiresTotalField().getText();
    String[] splitStr = str.split("\\s+");
    if (yesNo.contains(splitStr[0])) {
      System.out.println("if loop");
      } else {
      clickOn(getDriver(), newTermCreationPage.getRequiresTotalField(), TestUtil.getTimeOut());
      }
    }

  /**
   * This method clicks on the field Alternate Bid Criteria Field.
   */
  public void alternateBidCriteria(final String bidCriteriaValue) {
    newTermCreationPage.getBidCriteria().click();
    }

  /**
   * This method send Value for Set Minimum Value.
   */
  public void setMinimumValue(final String minimumVal) {
    newTermCreationPage.getMinValueField().sendKeys(minimumVal);
    }

  /**
   * This method send Value for Set Maximum Value.
   */
  public void setMaximumValue(final String maximumVal) {
    newTermCreationPage.getMaxValueField().sendKeys(maximumVal);
    }


  /**
   * This method clicks for the Change EP Formula Field.
   */
  public void changeEPFormulaFunc() throws InterruptedException {
    System.out.println("ChangeEPFormula>>>");
    js.executeScript("arguments[0].scrollIntoView();", newTermCreationPage.getExtendPricePencil());
    System.out.println("extendPricePencil>>>" + newTermCreationPage.getExtendPricePencil());
    newTermCreationPage.getExtendPricePencil().click();
    }

  /**
   * This method clicks on button Duplicate Column.
   */
  public void duplicateColumnButton() throws InterruptedException {
    js.executeScript("arguments[0].scrollIntoView();", newTermCreationPage.getDuplicateColumnButton());
    newTermCreationPage.getDuplicateColumnButton().click();
    }

  /**
   * This method clicks on button delete column.
   */
  public void deleteColumnButton() throws InterruptedException {
    js.executeScript("arguments[0].scrollIntoView();", newTermCreationPage.getDeleteColumnButton());
    newTermCreationPage.getDeleteColumnButton().click();
    }


  /**
   * This method clicks on button Confirm Action.
   */
  public void confirmActionButton() throws InterruptedException {
    clickOn(getDriver(), newTermCreationPage.getConfirmAction(), TestUtil.getTimeOut());

    }

  /**
   * This method choose the option for selected Field Type.
   */
  public void inputFieldTypeFunc(final String fieldType) throws InterruptedException {
    newTermCreationPage.getInputFieldTypeField().click();
    WebElement selectedQuestionType = newTermCreationPage.fieldOption(fieldType);
    selectedQuestionType.click();
    Thread.sleep(sleepTime);
    }

  /**
   * This method choose the option for selected who fills field.
   */
  public  void  whoFillsFunc(final String whoFillsType) {
    newTermCreationPage.getWhoFillsField().click();
    WebElement selectedFillType = newTermCreationPage.whoFillsOption(whoFillsType);
    selectedFillType.click();
    }


  /**
   * This method clicks the price Transformation Factor Field.
   */
  public  final void priceTransformationFactorFunc(final String priceTransFactor) {

    newTermCreationPage.getPriceTransformationFactorField().click();

    }

  /**
   * This method clicks the Save Button Field.
   */
  public final void saveButtonFunc() {

    js.executeScript("arguments[0].scrollIntoView();", newTermCreationPage.getSaveColumn());
    newTermCreationPage.getSaveColumn().click();
    }

  /**
   * This method changes the column Position.
   */
  public final void changeColumnPositionFunc(final String columnChange) throws InterruptedException {
    System.out.println("Entering ChangeCoumnPosition");
    final int sleepTimes = 3000;
    Thread.sleep(sleepTimes);
    String test1 = "header-" + columnChange;
    WebElement selectedColumn = getDriver().findElement(By.xpath("//*[@class='cdk-drop-list']/th"));
    WebElement columnToChange = selectedColumn.findElement(By.xpath("//*[@data-test='" + test1 + "']"));
    Actions act = new Actions(getDriver());
    act.clickAndHold(columnToChange).build().perform();
    String test2 = "header-UOM";
    WebElement selectedDropElement = getDriver().findElement(By.xpath("//*[@class='cdk-drop-list']/th[5]"));
    WebElement columnToDrop = selectedDropElement.findElement(By.xpath("//*[@data-test='" + test2 + "']"));
    act.moveToElement(columnToDrop).build().perform();
    act.release(columnToDrop).build().perform();
    System.out.println("Successful complete");
    }

  /**
   * This method enter the line items for the particular lot Id.
   */
  public final void lineItemsPrice(final XlsReader reader, final String lotId, final String sheetName) {

    //Xls_Reader reader = new Xls_Reader("./src/main/java/com/sol/qa/testdata/Forward_Event_Rules.xlsx");
    String pricingTable = sheetName;
    WebElement idLot = pricingSheetPage.getLotId(lotId);
    WebElement chevronRightIcon = idLot.findElement(By.tagName("mat-icon"));

    if (chevronRightIcon.getText().equals("chevron_right")) {
      clickOn(getDriver(), chevronRightIcon, TestUtil.getTimeOut());
      //chevronRightIcon.click();
      }

    int sheetRowCount = reader.getRowCount(pricingTable);

    if (sheetRowCount == 2) {
      WebElement itemName = idLot.findElement(By.xpath(".//table[2]/tbody/tr/td[2]"));
      sendkeys(getDriver(), itemName, TestUtil.getTimeOut(), reader.getCellData(sheetName, "Item Name", 2));
      //itemName.sendKeys(reader.getCellData(sheetName, "Item Name", 2));
      WebElement itemQuantity = idLot.findElement(By.xpath(".//table[2]/tbody/tr/td[3]"));
      sendkeys(getDriver(), itemQuantity, TestUtil.getTimeOut(), reader.getCellData(sheetName, "Qty", 2));
      //itemQuantity.sendKeys(reader.getCellData(sheetName, "Qty", 2));
      WebElement uoM = idLot.findElement(By.xpath(".//table[2]/tbody/tr/td[4]"));
      //UOM.sendKeys(reader.getCellData(sheetName, "UOM", 2));
      sendkeys(getDriver(), uoM, TestUtil.getTimeOut(), reader.getCellData(sheetName, "UOM", 2));
      WebElement baselinePrice = idLot.findElement(By.xpath(".//table[2]/tbody/tr/td[7]/span/span[2]"));
      //baselinePrice.sendKeys(reader.getCellData(sheetName, "Baseline Price", 2));
      sendkeys(getDriver(), baselinePrice, TestUtil.getTimeOut(), reader.getCellData(sheetName, "Baseline Price", 2));
      WebElement newColumn = idLot.findElement(By.xpath(".//table[2]/tbody/tr/td[8]/span/span[1]"));
      //baselinePrice.sendKeys(reader.getCellData(sheetName, "Baseline Price", 2));
      sendkeys(getDriver(), newColumn, TestUtil.getTimeOut(), reader.getCellData(sheetName, "New Column", 2));
      } else {

      String beforePath = ".//table[2]/tbody/tr[";
      String afterPathItemName = "]/td[2]";
      String afterPathItemQuantity = "]/td[3]";
      String afterPathUOM = "]/td[4]";
      String afterPathbasePrice = "]/td[7]/span/span[2]";
      String afterPathNewColumn = "]/td[8]/span/span[1]";

      if (sheetRowCount > 2) {

        WebElement newColumn = idLot.findElement(By.xpath(".//table[2]/tbody/tr/td[8]/span/span[1]"));
        clickOn(getDriver(), newColumn, TestUtil.getTimeOut());
        //baselineFirst.click();
        newColumn.sendKeys(Keys.TAB);

        for (int i = 1; i < sheetRowCount - 1; i++) {

          String actualPathNewColumn = beforePath + i + afterPathNewColumn;
          WebElement newColumnPrice = idLot.findElement(By.xpath(actualPathNewColumn));
          // baselinePrice.click();
          newColumnPrice.sendKeys(Keys.TAB);

          }
        }

      for (int i = 1; i < sheetRowCount; i++) {

        String actualPathItemName = beforePath + i + afterPathItemName;
        String actualPathItemQuantity = beforePath + i + afterPathItemQuantity;
        String actualPathUOM = beforePath + i + afterPathUOM;
        String actualPathbasePrice = beforePath + i + afterPathbasePrice;
        String actualPathNewColumn = beforePath + i + afterPathNewColumn;

        WebElement itemName = idLot.findElement(By.xpath(actualPathItemName));
        sendkeys(getDriver(), itemName, TestUtil.getTimeOut(), reader.getCellData(sheetName, "Item Name", i + 1));
        WebElement itemQuantity = idLot.findElement(By.xpath(actualPathItemQuantity));
        sendkeys(getDriver(), itemQuantity, TestUtil.getTimeOut(), reader.getCellData(sheetName, "Qty", i + 1));
        WebElement uoM = idLot.findElement(By.xpath(actualPathUOM));
        sendkeys(getDriver(), uoM, TestUtil.getTimeOut(), reader.getCellData(sheetName, "UOM", i + 1));
        WebElement baselinePrice = idLot.findElement(By.xpath(actualPathbasePrice));
        sendkeys(getDriver(), baselinePrice, TestUtil.getTimeOut(), reader.getCellData(sheetName, "Baseline Price", i + 1));
        WebElement newColumn = idLot.findElement(By.xpath(actualPathNewColumn));
        sendkeys(getDriver(), newColumn, TestUtil.getTimeOut(), reader.getCellData(sheetName, "New Column", i + 1));
        }
      }
    }

  /**
   * This method clicks on the chevron for the particular lot Id.
   */
  public void headerTableSelect(final String lotId, final String colName) throws InterruptedException {
    System.out.println("Duplcate Lot success" + colName);
    Thread.sleep(sleepTime);
    WebElement idLot = pricingSheetPage.getLotId(lotId);
    WebElement chevronRightIcon = idLot.findElement(By.tagName("mat-icon"));
    if (chevronRightIcon.getText().equals("chevron_right")) {
      clickOn(getDriver(), chevronRightIcon, TestUtil.getTimeOut());
      // chevronRightIcon.click();
      }

    List<WebElement> rows = idLot.findElements(By.xpath("//*[@data-test='lot-table-row-1']/th"));
    System.out.println("Rows Count" + rows.size());
    String colNameDuplicate = "header-" + colName;
    for (WebElement row : rows) {
      if ((colNameDuplicate).equals(row.getAttribute("data-test"))) {
        WebElement headerColName = row.findElement(By.xpath(".//span"));
        Thread.sleep(sleepTime);
        headerColName.click();
        }
      }

    }
  }
