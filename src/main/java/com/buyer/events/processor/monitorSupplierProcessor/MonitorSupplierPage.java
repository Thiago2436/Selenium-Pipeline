package com.buyer.events.processor.monitorSupplierProcessor;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MonitorSupplierPage {

  private WebDriver driver;
  private JavascriptExecutor js;

  public MonitorSupplierPage(final WebDriver drivers) {
    this.driver = drivers;
    js = (JavascriptExecutor) drivers;
    PageFactory.initElements(drivers, this);
    }

  public final WebDriver getDriver() {
    return driver;
    }

  @FindBy(css = "[data-test = 'total-invited']")
  private WebElement totalInvitedCount;

  @FindBy(css = "[data-test = 'intend-to-participate']")
  private WebElement intendParticipateCount;

  @FindBy(css = "[data-test ='declined-count']")
  private WebElement declineParticipateCount;

  @FindBy(css = "[data-test ='response-submitted-count']")
  private WebElement responseSubmittedCount;

  @FindBy(css = "[data-test ='resend-invite-btn']")
  private WebElement resendInviteBtn;

  @FindBy(css = "[data-test ='place-surrogate-bid']")
  private WebElement placeSurrogateBid;

  @FindBy(css = "[data-test ='block-supplier-btn']")
  private WebElement blockSupplier;

  @FindBy(css = "[data-test ='unblock-supplier-btn']")
  private WebElement unblockSupplier;

  @FindBy(css = "[data-test ='refresh-icon']")
  private WebElement refreshIcon;

  @FindBy(id = "viewbutton")
  private WebElement viewHistory;

  public final WebElement getTotalInvitedCount() {
    return totalInvitedCount;
    }

  public final WebElement getIntendParticipateCount() {
    return intendParticipateCount;
    }

  public final WebElement getDeclineParticipateCount() {
    return declineParticipateCount;
    }

  public final WebElement getResponseSubmittedCount() {
    return responseSubmittedCount;
    }

  public final  WebElement getViewHistory() {
    return viewHistory;
    }

  public final WebElement getResendInviteBtn() {
    return resendInviteBtn;
    }

  public final WebElement getPlaceSurrogateBid() {
    return placeSurrogateBid;
    }

  public final WebElement getBlockSupplier() {
    return blockSupplier;
    }

  public final WebElement getUnblockSupplier() {
    return unblockSupplier;
    }

  public final WebElement getRefreshIcon() {
    return refreshIcon;
    }

  public final WebElement actionForSuppliers(final String rowNo) {
    int value = Integer.parseInt(rowNo) + 1;
    WebElement actionLink = driver
        .findElement(By.cssSelector("[data-test = suppliers-invited-table] [role = row]:nth-child(" + value
            + ") [data-test = suppliers-action-btn]"));
    return actionLink;
    }

  public final WebElement navigationMonitorPhase(final String navigationTab) {
    String navigationLink = navigationTab + "-tab";
    WebElement naviagtionElement = driver.findElement(By.cssSelector("[data-test = " + navigationLink + "]"));
    return naviagtionElement;
    }
  }
