package com.buyer.events.processor.supplierProcessor;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SupplierCreationPage {

  private WebDriver driver;
  private JavascriptExecutor js;

  public SupplierCreationPage(final WebDriver drivers) {
    this.driver = drivers;
    js = (JavascriptExecutor) drivers;
    PageFactory.initElements(driver, this);
    }

  public final WebDriver getDriver() {
    return driver;
    }


  @FindBy(id = "searchSupplier")
  private WebElement searchSupplier;

  @FindBy(xpath = "//input[contains(@formcontrolname,'supplier_name')]")
  private WebElement searchSupplierName;

  @FindBy(id = "advancedSearchSupplier")
  private WebElement searchViewButton;

  @FindBy(xpath = " //*[@id='supplier-part']/mat-sidenav-container/mat-sidenav-content/div/div[3]/event-suppliers/div/div[1]/div[1]/div/span")
  private WebElement suppliersInvitedTable;

  @FindBy(id = "inviteSuppliersToEvent")
  private WebElement inviteToEventButton;

  @FindBy(id = "proceedEvent")
  private WebElement proceedButton;

  @FindBy(id = "nonInvitedDataSource")
  private WebElement tableList;

  @FindBy(name = "addSupplier")
  private WebElement addSupplierForm;

  @FindBy(xpath = "//*[@data-column = 'addContact']")
  private WebElement addContact;

  @FindBy(id = "addSupplier")
  private WebElement addNewSupplierButton;

  @FindBy(id = "addContact")
  private WebElement addContactButton;

  @FindBy(id = "cancelContact")
  private WebElement cancelContact;

  @FindBy(xpath = "//input[contains(@formcontrolname,'company_name')]")
  private WebElement companyName;

  @FindBy(xpath = "//input[contains(@formcontrolname,'email')]")
  private WebElement contactEmail;

  @FindBy(id = "selectSupplierCountry")
  private WebElement country;

  @FindBy(xpath = "//input[contains(@formcontrolname,'pan_no')]")
  private WebElement panNo;

  @FindBy(xpath = "//input[contains(@formcontrolname,'contact_person')]")
  private WebElement contactPersonName;

  @FindBy(xpath = "//input[contains(@formcontrolname,'telephone_number')]")
  private WebElement telephoneNumber;

  @FindBy(id = "selectSupplierState")
  private WebElement state;

  @FindBy(id = "confirmAddingSupplier")
  private WebElement confirmAddSupplierButton;

  @FindBy(id = "cancelAddingSupplier")
  private WebElement cancelAddSupplierButton;

  @FindBy(xpath = "//input[contains(@formcontrolname,'name') and @data-placeholder = 'Contact Person']")
  private WebElement addContactName;

  public final WebElement getSearchSupplier() {
    return searchSupplier;
    }

  public final WebElement getSearchSupplierName() {
    return searchSupplierName;
    }

  public final WebElement getSearchViewButton() {
    return searchViewButton;
    }

  public final WebElement getSuppliersInvitedTable() {
    return suppliersInvitedTable;
    }

  public final WebElement getInviteToEventButton() {
    return inviteToEventButton;
    }

  public final WebElement getProceedButton() {
    return proceedButton;
    }

  public final WebElement getTableList() {
    return tableList;
    }

  public  final WebElement getAddSupplierForm() {
    return addSupplierForm;
    }

  public final WebElement getAddContact() {
    return addContact;
    }

  public  final WebElement getAddNewSupplierButton() {
    return addNewSupplierButton;
    }

  public final WebElement getAddContactButton() {
    return addContactButton;
    }

  public final WebElement getCancelContact() {
    return cancelContact;
    }

  public final WebElement getCompanyName() {
    return companyName;
    }

  public final WebElement getContactEmail() {
    return contactEmail;
    }

  public final WebElement getCountry() {
    return country;
    }

  public final WebElement getPanNo() {
    return panNo;
    }

  public final WebElement getContactPersonName() {
    return contactPersonName;
    }

  public final WebElement getTelephoneNumber() {
    return telephoneNumber;
    }

  public final  WebElement getState() {
    return state;
    }

  public final WebElement getConfirmAddSupplierButton() {
    return confirmAddSupplierButton;
    }

  public final WebElement getCancelAddSupplierButton() {
    return cancelAddSupplierButton;
    }

  public final WebElement getAddContactName() {
    return addContactName;
    }

  /**
   * This method selects the selected given country from the drop down option.
   */
  public void countryOption(final String countrySelect) {
    System.out.println("WebElement" + countrySelect + "Space Check");
    WebElement overlayPQCType = driver.findElement(By.className("cdk-overlay-container"));

    List<WebElement> countrySelected = overlayPQCType
        .findElements(By.xpath("//*[contains(text(),'" + countrySelect + "')]"));
    for (int i = 0; i < countrySelected.size(); i++) {
      System.out.println("Text>>>" + countrySelected.get(i).getText());
      if (countrySelected.get(i).getText().equalsIgnoreCase(countrySelect)) {
        System.out.println("if loop" + countrySelected.get(i).getText());

        }
      }

    }

  /**
   * This method selects the selected given state from the drop down option.
   */
  public void stateOption(final String stateSelect) {
    System.out.println("WebElement" + stateSelect + "Space Check");
    WebElement overlayPQCType = driver.findElement(By.className("cdk-overlay-container"));

    List<WebElement> stateSelected = overlayPQCType
        .findElements(By.xpath("//*[contains(text(),'" + stateSelect + "')]"));
    for (int i = 0; i < stateSelected.size(); i++) {
      System.out.println("Text>>>" + stateSelected.get(i).getText());
      if (stateSelected.get(i).getText().equalsIgnoreCase(stateSelect)) {
        System.out.println("if loop" + stateSelected.get(i).getText());

        }
      }

    }

  }
