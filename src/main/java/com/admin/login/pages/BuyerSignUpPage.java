package com.admin.login.pages;

import com.sol.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
* This class will have webElements of buyer SignUp Page.
* @author  YamunaS
*/
public class BuyerSignUpPage extends TestBase {

  @FindBy(xpath = "//input[contains(@formcontrolname,'username')]")
  private WebElement signUpName;

  @FindBy(xpath = "//input[contains(@formcontrolname,'email')]")
  private WebElement signUpEmail;

  @FindBy(xpath = "//input[contains(@formcontrolname,'password')]")
  private WebElement signPassword;

  @FindBy(xpath = "//input[contains(@formcontrolname,'confirm_password')]")
  private WebElement confirmPassword;

  @FindBy(xpath = "//button[@class = 'submit-button mat-flat-button mat-accent']")
  private WebElement register;

  @FindBy(xpath = "//*[@id=\"reg-form\"]/div/form/div[7]/span")
  private WebElement signIn;

  @FindBy(xpath = "//*[@class = 'mat-error ng-tns-c31-19 ng-star-inserted' and contains(text(),' User name is required ')]")
  private WebElement errorName;

  @FindBy(xpath = "//*[@class = 'mat-error ng-tns-c31-19 ng-star-inserted' and contains(text(),' Email is required ')]")
  private WebElement errorEmail;

  @FindBy(xpath = "//*[@class = 'error-message mat-hint ng-tns-c31-19 ng-star-inserted']")
  private WebElement errorPassword;

  @FindBy(xpath = "//*[@class = 'mat-hint ng-tns-c31-19 ng-star-inserted' and contains(text(),' You have already signed up ')]")
  private WebElement errorValEmal;

  @FindBy(xpath = "//*[@class='mat-hint ng-tns-c31-19 ng-star-inserted' and contains(text(),' This email does not exist')]")
  private WebElement errorMailMessage;

  @FindBy(xpath = "//*[@class='error-message mat-hint ng-tns-c31-19 ng-star-inserted' and "
      + "contains(text(),'The password must have minimum 8 characters including at least 1 uppercase, 1 lowercase and 1 numeric characters.')]")
  private WebElement errorPasswordMessage;

  @FindBy(xpath = "//*[@class = 'mat-hint ng-tns-c31-19 ng-star-inserted' and contains(text(),' Password mismatch ')]")
  private WebElement errorconfirmPassword;

  @FindBy(xpath = "//div[@class = 'login-form-div ng-tns-c31-19 ng-star-inserted']")
  private WebElement signInPage;

  @FindBy(xpath = "//*[@class = 'ng-tns-c31-19' and @style = 'font-size:16px;color:green']")
  private WebElement successSignUp;

  @FindBy(xpath = "//*[@id=\"login-form\"]/div")
  private WebElement loginDiv;

  public final WebElement getSignUpName() {
    return signUpName;
    }
  public final WebElement getSignUpEmail() {
    return signUpEmail;
    }

  public final WebElement getSignPassword() {
    return signPassword;
    }

  public final WebElement getConfirmPassword() {
    return confirmPassword;
    }

  public final WebElement getRegister() {
    return register;
    }

  public final WebElement getSignIn() {
    return signIn;
    }
  public final WebElement getErrorName() {
    return errorName;
    }

  public final WebElement getErrorEmail() {
    return errorEmail;
    }

  public final WebElement getErrorPassword() {
    return errorPassword;
    }

  public final WebElement getErrorValEmal() {
    return errorValEmal;
    }

  public final WebElement getErrorMailMessage() {
    return errorMailMessage;
    }

  public final WebElement getErrorPasswordMessage() {
    return errorPasswordMessage;
    }

  public final WebElement getErrorconfirmPassword() {
    return errorconfirmPassword;
    }

  public final WebElement getSignInPage() {
    return signInPage;
    }

  public final WebElement getSuccessSignUp() {
    return successSignUp;
    }

  public final WebElement getLoginDiv() {
    return loginDiv;
    }

/**
* This constructor have PageFactory initElements.
*/
  public BuyerSignUpPage() {
    PageFactory.initElements(getDriver(), this);
    WebElement signUpButton = getDriver().findElement(By.xpath("//*[@id=\"login-form\"]/div/form/div[6]/span"));
    signUpButton.click();
    }
  }
