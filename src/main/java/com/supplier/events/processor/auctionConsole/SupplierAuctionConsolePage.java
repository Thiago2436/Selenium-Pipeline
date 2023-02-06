package com.supplier.events.processor.auctionConsole;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SupplierAuctionConsolePage {

  private WebDriver driver;
  private JavascriptExecutor js;

  public SupplierAuctionConsolePage(final WebDriver drivers) {
    this.driver = drivers;
    js = (JavascriptExecutor) drivers;
    PageFactory.initElements(drivers, this);
    }

  public final WebDriver getDriver() {
    return driver;
    }

  @FindBy(css = "[data-test ='lot-submit-btn']")
  private WebElement lotSubmitButton;

  @FindBy(css = "[data-test ='extended-price']")
  private WebElement extendedPrice;

  public final WebElement getLotSubmitButton() {
    return lotSubmitButton;
    }

  public final WebElement getExtendedPrice() {
    return extendedPrice;
    }

  public final WebElement navigationToTab(final String navigationTab) {
    WebElement naviagtionElement = driver.findElement(By.cssSelector("[data-test = '" + navigationTab + "']"));
    return naviagtionElement;
    }

  public final WebElement performLotNumber(final String lotNo) {
    WebElement radioButton = driver.findElement(By.cssSelector("#auction-lot-table tr:nth-child(" + lotNo + ") [name='summary_lot']"));
    return radioButton;
    }

  public final WebElement lotValueElement(final String lotNo) {
    WebElement lotElement = driver.findElement(By.cssSelector("[data-test = 'lot-value-" + lotNo + "'"));
    return lotElement;
    }
  }
