package com.buyer.events.processor.auctionConsole;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AuctionConsolePage {

  private WebDriver driver;
  // private JavascriptExecutor js;

  public AuctionConsolePage(final WebDriver drivers) {
    this.driver = drivers;
     //js = (JavascriptExecutor) drivers;
    PageFactory.initElements(driver, this);
    }

  public final WebDriver getDriver() {
    return driver;
    }
  }
