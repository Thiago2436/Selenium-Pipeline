package com.admin.login.pages;

import com.sol.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
* BuyerForgotPassword is the dealing with webElements and Methods
* WebElements for the ForgotPassword for Buyer Screen.
*
* @author  YamunaS
*/
public class BuyerForgotPassword extends TestBase {

  @FindBy(xpath = "//*[@id=\"mat-dialog-0\"]/app-forgot-password/div[2]")
  private WebElement passwordLabel;

  @FindBy(xpath = " //button[@class = 'ml-12 px-40 mat-stroked-button mat-accent']")
  private WebElement cancelButton;

  @FindBy(xpath = "//div[@class= 'mat-form-field-infix']/input[@id = 'mat-input-2']")
  private WebElement addEmail;

  @FindBy(id = "forgotPassword")
  private WebElement forgotPasswordLink;

  @FindBy(xpath = "//button[@class = 'ml-12 px-48 mat-flat-button mat-accent']")
  private WebElement sendButton;

  @FindBy(xpath = "//*[@id=\"mat-hint-0\" and @class='mat-hint ng-star-inserted']")
  private WebElement errorMessage;

  @FindBy(xpath = "//*[@id=\"mat-dialog-0\"]/app-forgot-password/div[3]")
  private WebElement successLink;

  public BuyerForgotPassword() {
    PageFactory.initElements(getDriver(), this);
    }
  public final String newEmailValidation() throws InterruptedException {
    addEmail.sendKeys(getProp().getProperty("newUser"));
    final int sleepTime = 1000;
    Thread.sleep(sleepTime);
    sendButton.click();
    String message = successLink.getText();
    System.out.println("Message " + message);
    return message;
    }

  public final WebElement getPasswordLabel() {
    return passwordLabel;
    }

  public final WebElement getCancelButton() {
    return cancelButton;
    }

  public final WebElement getAddEmail() {
    return addEmail;
    }

  public final WebElement getForgotPasswordLink() {
    return forgotPasswordLink;
    }

  public final WebElement getSendButton() {
    return sendButton;
    }

  public final WebElement getErrorMessage() {
    return errorMessage;
    }

  public final WebElement getSuccessLink() {
    return successLink;
    }
  }
