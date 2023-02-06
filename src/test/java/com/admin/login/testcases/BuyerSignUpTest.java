package com.admin.login.testcases;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.admin.login.pages.ApiTesting;
import com.admin.login.pages.BuyerLoginPage;
import com.admin.login.pages.BuyerSignUpPage;
import com.sol.qa.base.CustomListener;
import com.sol.qa.base.TestBase;

@Listeners(CustomListener.class)
public class BuyerSignUpTest extends TestBase {

  BuyerLoginPage loginPage;

  BuyerSignUpPage signPage;
  ApiTesting api;

  public BuyerSignUpTest() {
    super();
    }

  @BeforeMethod
  public void setUp() throws MalformedURLException {
    initialization();
    signPage = new BuyerSignUpPage();
    }

  @Test(priority = 1)
  public void verifyElemntsOnPageTest() {

    signPage.getSignUpName().isEnabled();
    signPage.getSignUpEmail().isEnabled();
    signPage.getSignPassword().isEnabled();
    signPage.getConfirmPassword().isEnabled();
    signPage.getRegister().isDisplayed();
    signPage.getSignIn().isDisplayed();

    }

  @Test(priority = 2)
  public void UserNameEmptyCheck() {
    signPage.getSignUpName().sendKeys("");

    signPage.getSignUpEmail().click();
    Assert.assertEquals(signPage.getErrorName().getText(), "User name is required");
    }

  @Test(priority = 3)
  public void emailIdEmptyCheck() {
    signPage.getSignUpEmail().sendKeys("");

    signPage.getSignPassword().click();
    Assert.assertEquals(signPage.getErrorEmail().getText(), "Email is required");
    }

  @Test(priority = 4)
  public void passwordEmptyCheck() {
    signPage.getSignPassword().sendKeys("");

    signPage.getConfirmPassword().click();
    Assert.assertEquals(signPage.getErrorPassword().getText(), "Password required");
    }

  @Test(priority = 5)
  public void existEmailId() throws InterruptedException {
    // signPage.signUpEmail.sendKeys(prop.getProperty("existingUserName"));
    String myString = getProp().getProperty("existingUserName");
    String[] splitString = myString.split("@");
    signPage.getSignUpEmail().sendKeys(splitString[0] + '@');
    Thread.sleep(1000);
    String[] mailString = splitString[1].split("");
    int len = mailString.length;
    for (int i = 0; i < len - 1; i++) {

      signPage.getSignUpEmail().sendKeys(mailString[i]);
      Thread.sleep(2);

      }
    Thread.sleep(2);
    signPage.getSignUpEmail().sendKeys(mailString[len - 1]);

    Thread.sleep(1000);

    Assert.assertEquals(signPage.getErrorValEmal().getText(), "You have already signed up");
    }

  @Test(priority = 6)
  public void validateEmailId() throws InterruptedException {
    // signPage.signUpEmail.sendKeys(prop.getProperty("invalidEmail"));
    String myString = getProp().getProperty("invalidEmail");
    String[] splitString = myString.split("@");
    signPage.getSignUpEmail().sendKeys(splitString[0] + '@');
    Thread.sleep(1000);
    String[] mailString = splitString[1].split("");
    int len = mailString.length;
    for (int i = 0; i < len - 1; i++) {

      signPage.getSignUpEmail().sendKeys(mailString[i]);
      Thread.sleep(2);

      }
    Thread.sleep(2);
    signPage.getSignUpEmail().sendKeys(mailString[len - 1]);

    Thread.sleep(1000);

    Assert.assertEquals(signPage.getErrorMailMessage().getText(),
        "This email does not exist. Please contact your Admin");
    }

  @Test(priority = 7)
  public void validatePassword() throws InterruptedException {
    signPage.getSignPassword().sendKeys("12345");

    signPage.getConfirmPassword().click();
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
    wait.until(ExpectedConditions.elementToBeClickable(signPage.getConfirmPassword()));
    // System.out.println(signPage.errorPasswordMessage.getText());

    Assert.assertEquals(signPage.getErrorPasswordMessage().getText(),
        "The password must have minimum 8 characters including at least 1 uppercase, 1 lowercase and 1 numeric characters.");

    }

  @Test(priority = 8)
  public void invalidConfirmPassword() throws InterruptedException {
    signPage.getSignUpName().sendKeys(getProp().getProperty("buyerName"));
    String myString = getProp().getProperty("buyerEmail");
    String[] splitString = myString.split("@");
    signPage.getSignUpEmail().sendKeys(splitString[0] + '@');
    Thread.sleep(1000);
    String[] mailString = splitString[1].split("");
    int len = mailString.length;
    for (int i = 0; i < len - 1; i++) {

      signPage.getSignUpEmail().sendKeys(mailString[i]);
      Thread.sleep(2);

      }
    Thread.sleep(2);
    signPage.getSignUpEmail().sendKeys(mailString[len - 1]);

    Thread.sleep(1000);

    signPage.getSignPassword().sendKeys(getProp().getProperty("password"));
    signPage.getConfirmPassword().sendKeys(getProp().getProperty("InvalidConPassword"));
    Thread.sleep(2000);
    // register.click();
    Assert.assertEquals(signPage.getErrorconfirmPassword().getText(), "Password mismatch");
    }

  @Test(priority = 9)
  public void validRegistrationTest() throws InterruptedException {

    signPage.getSignUpName().sendKeys(getProp().getProperty("buyerName"));
    // signPage.signUpEmail.sendKeys(prop.getProperty("buyerEmail"));
    String EmailString = getProp().getProperty("buyerEmail");
    String[] splitEmail = EmailString.split("@");
    signPage.getSignUpEmail().sendKeys(splitEmail[0] + '@');
    Thread.sleep(1000);
    String[] mailString = splitEmail[1].split("");
    int len = mailString.length;
    for (int i = 0; i < len - 1; i++) {

      signPage.getSignUpEmail().sendKeys(mailString[i]);
      Thread.sleep(2);

      }
    Thread.sleep(2);
    signPage.getSignUpEmail().sendKeys(mailString[len - 1]);

    Thread.sleep(1000);

    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
    wait.until(
        ExpectedConditions.textToBePresentInElementValue(signPage.getSignUpEmail(), getProp().getProperty("buyerEmail")));

    signPage.getSignPassword().sendKeys(getProp().getProperty("password"));
    signPage.getConfirmPassword().sendKeys(getProp().getProperty("password"));

    signPage.getRegister().click();
    /*
     * JavascriptExecutor js = (JavascriptExecutor)driver;
     * js.executeScript("arguments[0].click();", signPage.register);
     */
    Assert.assertEquals(signPage.getSuccessSignUp().getText(), "Registered Successfully");

    }

  @Test(priority = 10)
  public void SignInPage() {
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    js.executeScript("arguments[0].click();", signPage.getSignIn());
    // signPage.signIn.click();
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
    wait.until(ExpectedConditions.visibilityOf(signPage.getLoginDiv()));

    Assert.assertTrue(signPage.getLoginDiv().isDisplayed());
    }

  @AfterMethod
  public void tearDown() {
    getDriver().quit();
    }

  }
