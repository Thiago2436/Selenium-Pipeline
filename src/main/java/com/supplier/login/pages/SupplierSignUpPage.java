package com.supplier.login.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.sol.qa.base.TestBase;

public class SupplierSignUpPage extends TestBase {

  final int sleepTime = 2000;

  @FindBy(xpath = "//div[@class='mat-form-field-infix']/input[@type='text' and @id=\"mat-input-2\"]")
  private WebElement signUpName;

  @FindBy(xpath = "//div[@class='mat-form-field-infix']/input[@formcontrolname='email' and @id=\"mat-input-3\"]")
  private WebElement signUpEmail;

  @FindBy(xpath = "//div[@class='mat-form-field-infix']/input[@type='password' and @id=\"mat-input-4\"]")
  private WebElement signPassword;

  @FindBy(xpath = "//div[@class='mat-form-field-infix']/input[@type='password' and @id=\"mat-input-5\"]")
  private WebElement confirmPassword;

  @FindBy(xpath = "//button[@class = 'submit-button mat-flat-button mat-accent']")
  private WebElement register;

  @FindBy(xpath = "//*[@id=\"reg-form\"]/div/form/div[7]/span")
  private WebElement signIn;

  @FindBy(xpath = "//*[@class = 'mat-error ng-star-inserted' and contains(text(),' User name is required ')]")
  private WebElement errorName;

  @FindBy(xpath = "//*[@class = 'mat-error ng-star-inserted' and contains(text(),' Email is required ')]")
  private WebElement errorEmail;

  @FindBy(xpath = "//*[@class = 'error-message mat-hint ng-star-inserted']")
  private WebElement errorPassword;

  @FindBy(xpath = "//*[@class='mat-hint ng-star-inserted' and contains(text(),'This Email ID is already registered.')]")
  private WebElement errorValEmal;

  @FindBy(xpath = "//*[@class='mat-hint ng-star-inserted' and contains(text(),'You cannot register with this email ID. Please contact your buyer')]")
  private WebElement errorMailMessage;

  @FindBy(xpath = "//*[@class='error-message mat-hint ng-star-inserted' and contains(text(),"
      + "'The password must have minimum 8 characters including at least 1 uppercase, 1 lowercase and 1 numeric characters.')]")
  private WebElement errorPasswordMessage;

  @FindBy(xpath = "//*[@class = 'mat-hint ng-star-inserted' and contains(text(),'Password mismatch')]")
  private WebElement errorconfirmPassword;

  @FindBy(xpath = "//*[@class = 'ng-untouched ng-pristine ng-valid']")
  private WebElement signInPage;

  @FindBy(xpath = "//*[@style = 'font-size:16px;color:green' and contains(text(),'Registered Successfully')]")
  private WebElement successSignUp;

  // Initializing the Page Objects:
  public SupplierSignUpPage() {
    PageFactory.initElements(getDriver(), this);
    WebElement signUpButton = getDriver().findElement(By.xpath("//*[@id=\"login-form\"]/div/form/div[6]/span"));
    signUpButton.click();

    }

  /**
   *This method validate the elements visibility on the Sign up page.
   */
  public void validateElemntsOnPageTest() {
    signUpName.isEnabled();
    signUpEmail.isEnabled();
    signPassword.isEnabled();
    confirmPassword.isEnabled();
    register.isDisplayed();
    signIn.isDisplayed();
    }

  /**
   *This method assert the username validation.
   */
  public void userNameEmptyCheck() {
    signUpName.sendKeys("");
    signUpEmail.click();
    Assert.assertEquals(errorName.getText(), "User name is required");
    }

  /**
   *This method assert Email Id validation.
   */
  public void emailIdEmptyCheck() {
    signUpEmail.sendKeys("");
    signPassword.click();
    Assert.assertEquals(errorEmail.getText(), "Email is required");
    }

  /**
   *This method validate the existing email id signing up.
   */
  public void existEmailId() throws InterruptedException {
    signUpEmail.sendKeys(getProp().getProperty("existingUserName"));
    Assert.assertEquals(errorValEmal.getText(), "You have already signed up");
    }

  /**
   *This method validate the error msg "This Email does not exist".
   */
  public void validateEmailId() throws InterruptedException {
    signUpEmail.sendKeys(getProp().getProperty("invalidEmail"));
    Assert.assertEquals(errorMailMessage.getText(), "This email does not exist. Please contact your Admin");
    }

  /**
   *This method validate the password required error message.
   */
  public void passwordEmptyCheck() {
    signPassword.sendKeys("");
    confirmPassword.click();
    Assert.assertEquals(errorPassword.getText(), "Password required");
    }

  /**
   *This method assert the "password mismatch" error message.
   */
  public void invalidConfirmPassword() throws InterruptedException {
    signUpName.sendKeys(getProp().getProperty("buyerName"));
    signUpEmail.sendKeys(getProp().getProperty("buyerEmail"));
    signPassword.sendKeys(getProp().getProperty("password"));
    confirmPassword.sendKeys(getProp().getProperty("InvalidConPassword"));
    Thread.sleep(sleepTime);
    Assert.assertEquals(errorconfirmPassword.getText(), "Password mismatch");
    }

  /**
   *This method does successfully registration sign in.
   */
  public void validRegistrationTest() throws InterruptedException {
    signUpName.sendKeys(getProp().getProperty("buyerName"));
    signUpEmail.sendKeys(getProp().getProperty("buyerEmail"));
    Thread.sleep(sleepTime);
    signUpEmail.sendKeys("m");
    signPassword.sendKeys(getProp().getProperty("password"));
    confirmPassword.sendKeys(getProp().getProperty("password"));
    register.click();
    register.click();
    Assert.assertEquals(successSignUp.getText(), "Registered Successfully");
    }

  /**
   *This method clicks on Sign In button.
   */
  public void signInClick() {
    signIn.click();
    signInPage.isDisplayed();
    }

  /**
   *This method validate the password.
   */
  public void validatePassword() throws InterruptedException {
    signPassword.sendKeys("12345");
    confirmPassword.click();
    Assert.assertEquals(errorPasswordMessage.getText(),
        "The password must have minimum 8 characters including at least 1 uppercase, 1 lowercase and 1 numeric characters.");
    }

  public final WebElement getSignUpName() {
    return signUpName;
    }

  public final WebElement getSignUpEmail() {
    return signUpEmail;
    }

  public final  WebElement getSignPassword() {
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
  }
