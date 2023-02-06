package com.buyer.events.processor.eventRulesProcessor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EventListCreationPage {
  private WebDriver driver;

  public EventListCreationPage(final WebDriver drivers) {
    this.driver = drivers;
    System.out.println("2>>>" + this.driver);
    PageFactory.initElements(drivers, this);
    }

  public final WebDriver getDriver() {
    return driver;
    }

  @FindBy(id = "createEvent")
  private  WebElement createNewButton;

  public final WebElement getCreateNewButton() {
    return createNewButton;
    }

  }
