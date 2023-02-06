package com.supplier.login.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sol.qa.base.TestBase;

public class SupplierForgotPasswordPage extends TestBase {

  @FindBy(xpath = "//div[@style = 'padding-left:37px;padding-right:33px']/h2[@id = 'mat-dialog-title-0']")
  private WebElement passwordLabel;

  @FindBy(xpath = " //button[@class = 'ml-12 px-40 mat-stroked-button mat-accent']")
  private WebElement cancelButton;

  @FindBy(xpath = "//div[@class= 'mat-form-field-infix']/input[@id = 'mat-input-2']")
  private WebElement addEmail;

  @FindBy(xpath = "//a[contains(text(),' FORGOT PASSWORD?')]")
  private WebElement forgotPasswordLink;

  @FindBy(xpath = "//*[@id=\"mat-dialog-0\"]/app-supplier-forgot-password/mat-dialog-actions/button[2]")
  private WebElement sendButton;

  @FindBy(xpath = "//*[@id=\"mat-hint-0\" and @class='mat-hint ng-star-inserted']")
  private WebElement errorMessage;

  @FindBy(xpath = "//div[@style = 'text-align: center;font-size:16px;color:green' and @class='ng-star-inserted']")
  private WebElement successLink;

  public SupplierForgotPasswordPage() {
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
