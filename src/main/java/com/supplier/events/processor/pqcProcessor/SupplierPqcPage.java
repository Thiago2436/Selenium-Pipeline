package com.supplier.events.processor.pqcProcessor;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SupplierPqcPage {

  private WebDriver driver;
  private JavascriptExecutor js;

  @FindBy(css = "[data-test = 'pqc-submit-btn']")
  private WebElement pqcSubmit;

  public SupplierPqcPage(final WebDriver drivers) {
    this.driver = drivers;
    js = (JavascriptExecutor) drivers;
    PageFactory.initElements(drivers, this);
    }

  public final WebDriver getDriver() {
    return driver;
    }

  public final WebElement supplierNavigation(final String navigationTab) {
    String navigationLink = navigationTab + "-link";
    WebElement naviagtionElement = driver.findElement(By.cssSelector("[data-test = '" + navigationLink + "']"));
    return naviagtionElement;
    }

  public final WebElement supplierInputData(final String inputType, final String indexNum) {
    String inputString = "pqc-" + inputType + "-" + indexNum;
    WebElement inputElement = driver.findElement(By.cssSelector("[data-test = '" + inputString + "']"));
    return inputElement;
    }

  public final WebElement singleChoiceData(final String inputType, final String indexNum) {
    String inputString = "pqc-" + inputType + "-" + indexNum;
    WebElement inputElement = driver.findElement(By.cssSelector("[data-test = '" + inputString + "']"));
    return inputElement;
    }

  public final WebElement datePickerOption() {
    WebElement dateElement = driver.findElement(By.cssSelector("[aria-label = 'Open calendar']"));
    return dateElement;
    }

  public final WebElement getPqcSubmit() {
    return pqcSubmit;
    }
  }
