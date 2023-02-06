package com.buyer.events.processor.pricingSheetProcessor.addNewTermProcessor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.sol.qa.base.TestBase;

public class AddNewTermPricingSheetPage extends TestBase  {

  public AddNewTermPricingSheetPage() {
    PageFactory.initElements(getDriver(), this);
    }

  @FindBy(id = "term_nameField")
  private WebElement termNameField;

  @FindBy(id = "alternate_bid_from_supplierField")
  private WebElement bidCriteria;

  @FindBy(id = "saveColumn")
  private WebElement saveColumn;

  @FindBy(id = "data_need_for_all_line_itemsField")
  private WebElement dataNeedForAllLineItem;

  @FindBy(id = "input_field_typeField")
  private WebElement inputFieldTypeField;

  @FindBy(id = "who_fillsField")
  private WebElement whoFillsField;

  @FindBy(id = "price_transformation_factorField")
  private WebElement priceTransformationFactorField;

  @FindBy(xpath = "//*[@data-placeholder = 'Add Min']")
  private WebElement minValueField;

  @FindBy(xpath = "//*[@data-placeholder = 'Add Max']")
  private WebElement maxValueField;

  @FindBy(xpath = "//input[contains(@formcontrolname,'event_name')]")
  private WebElement eventNameField;

  @FindBy(xpath = "/html/body/fuse-root/fuse-main/mat-sidenav-container/mat-sidenav-content/div/div/div/fuse-content/"
      + "app-newsource/div/mat-sidenav-container/mat-sidenav-content/div/div[3]/event-pricing-sheet/div/lot/div/mat-expansion-panel"
      + "/div/div/div/div[3]/div/table[1]/thead/tr[1]/th[6]")
  private WebElement extendPricePencil;

  @FindBy(id = "formulaField")
  private WebElement createFormulaField;

  @FindBy(id = "requires_a_totalField")
  private WebElement requiresTotalField;

  @FindBy(id = "required_for_formulaField")
  private WebElement requiresForFormulaField;

  @FindBy(id = "duplicateColumn")
  private WebElement duplicateColumnButton;

  @FindBy(id = "deleteColumn")
  private WebElement deleteColumnButton;

  @FindBy(id = "confirmAction")
  private WebElement confirmAction;

  public final WebElement getTermNameField() {
    return termNameField;
    }

  public final WebElement getBidCriteria() {
    return bidCriteria;
    }

  public final WebElement getSaveColumn() {
    return saveColumn;
    }

  public final WebElement getDataNeedForAllLineItem() {
    return dataNeedForAllLineItem;
    }

  public final WebElement getInputFieldTypeField() {
    return inputFieldTypeField;
    }

  public final WebElement getWhoFillsField() {
    return whoFillsField;
    }

  public final WebElement getPriceTransformationFactorField() {
    return priceTransformationFactorField;
    }

  public final WebElement getMinValueField() {
    return minValueField;
    }

  public final WebElement getMaxValueField() {
    return maxValueField;
    }

  public final WebElement getEventNameField() {
    return eventNameField;
    }

  public final WebElement getExtendPricePencil() {
    return extendPricePencil;
    }

  public final WebElement getCreateFormulaField() {
    return createFormulaField;
    }

  public final WebElement getRequiresTotalField() {
    return requiresTotalField;
    }

  public final WebElement getRequiresForFormulaField() {
    return requiresForFormulaField;
    }

  public final WebElement getDuplicateColumnButton() {
    return duplicateColumnButton;
    }

  public final WebElement getDeleteColumnButton() {
    return deleteColumnButton;
    }

  public final WebElement getConfirmAction() {
    return confirmAction;
    }

  public final WebElement fieldOption(final String fieldType) {
    WebElement overlayAuctionType = getDriver().findElement(By.className("cdk-overlay-container"));
    WebElement fieldOptions = overlayAuctionType.findElement(By.xpath("//*[@class = 'mat-option-text' and contains(text(),'" + fieldType + "')]"));
    return fieldOptions;
    }

  public final WebElement whoFillsOption(final String whoFillsType) {

    WebElement overlayAuctionType = getDriver().findElement(By.className("cdk-overlay-container"));
    // Get all options in a list
    WebElement fillsOptions = overlayAuctionType.findElement(By.xpath("//*[@class ="
        + " 'mat-option-text' and contains(text(),'" + whoFillsType + "')]"));
    return fillsOptions;
    }
  }
