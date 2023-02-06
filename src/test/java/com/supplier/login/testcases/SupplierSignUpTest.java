package com.supplier.login.testcases;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.sol.qa.base.CustomListener;
import com.sol.qa.base.TestBase;
import com.supplier.login.pages.SupplierSignUpPage;

@Listeners(CustomListener.class)
public class SupplierSignUpTest extends TestBase {

  WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

  SupplierSignUpPage supplierSignPage;

  public SupplierSignUpTest() {
    super();
  }

  @BeforeMethod
  public void setUp() throws MalformedURLException {
    initializationForSupplier();
    supplierSignPage = new SupplierSignUpPage();
  }

  @Test(priority = 1)
  public void verifyElemntsOnPageTest() {

    supplierSignPage.getSignUpName().isEnabled();
    supplierSignPage.getSignUpEmail().isEnabled();
    supplierSignPage.getSignPassword().isEnabled();
    supplierSignPage.getConfirmPassword().isEnabled();
    supplierSignPage.getRegister().isDisplayed();
    supplierSignPage.getSignIn().isDisplayed();

  }

  @Test(priority = 2)
  public void UserNameEmptyCheck() {
    supplierSignPage.getSignUpName().sendKeys("");

    supplierSignPage.getSignUpEmail().click();
    Assert.assertEquals(supplierSignPage.getErrorName().getText(), "User name is required");
  }

  @Test(priority = 3)
  public void emailIdEmptyCheck() {
    supplierSignPage.getSignUpEmail().sendKeys("");

    supplierSignPage.getSignPassword().click();
    Assert.assertEquals(supplierSignPage.getErrorEmail().getText(), "Email is required");
  }

  @Test(priority = 4)
  public void passwordEmptyCheck() {
    supplierSignPage.getSignPassword().sendKeys("");

    supplierSignPage.getConfirmPassword().click();
    Assert.assertEquals(supplierSignPage.getErrorPassword().getText(), "Password required");
  }

  @Test(priority = 5)
  public void existEmailId() throws InterruptedException {
    // supplierSignPage.signUpEmail.sendKeys(prop.getProperty("existingEmail"));
    String myString = getProp().getProperty("existingEmail");
    String[] splitString = myString.split("@");
    supplierSignPage.getSignUpEmail().sendKeys(splitString[0] + '@');
    Thread.sleep(1000);

    String[] mailString = splitString[1].split("");
    int len = mailString.length;
    for (int i = 0; i < len - 1; i++) {

      supplierSignPage.getSignUpEmail().sendKeys(mailString[i]);
      Thread.sleep(2);

    }
    Thread.sleep(2);
    supplierSignPage.getSignUpEmail().sendKeys(mailString[len - 1]);

    Thread.sleep(1000);

    wait.until(ExpectedConditions.textToBePresentInElementValue(supplierSignPage.getSignUpEmail(),
        getProp().getProperty("existingEmail")));

    Assert.assertEquals(supplierSignPage.getErrorValEmal().getText(), "This Email ID is already registered.");
  }

  @Test(priority = 6)
  public void validateEmailId() throws InterruptedException {
    // supplierSignPage.signUpEmail.sendKeys(prop.getProperty("invalidEmail"));
    String myString = getProp().getProperty("invalidEmail");
    String[] splitString = myString.split("@");
    supplierSignPage.getSignUpEmail().sendKeys(splitString[0] + '@');
    String[] mailString = splitString[1].split("");
    int len = mailString.length;
    for (int i = 0; i < len - 1; i++) {

      supplierSignPage.getSignUpEmail().sendKeys(mailString[i]);
      Thread.sleep(2);

    }
    Thread.sleep(2);
    supplierSignPage.getSignUpEmail().sendKeys(mailString[len - 1]);

    Thread.sleep(1000);

    wait.until(ExpectedConditions.textToBePresentInElementValue(supplierSignPage.getSignUpEmail(),
        getProp().getProperty("invalidEmail")));
    Assert.assertEquals(supplierSignPage.getErrorMailMessage().getText(),
        "You cannot register with this email ID. Please contact your buyer.");
  }

  @Test(priority = 7)
  public void validatePassword() throws InterruptedException {
    supplierSignPage.getSignPassword().sendKeys("12345");

    supplierSignPage.getConfirmPassword().click();

    wait.until(ExpectedConditions.elementToBeClickable(supplierSignPage.getConfirmPassword()));

    // System.out.println(signPage.errorPasswordMessage.getText());

    Assert.assertEquals(supplierSignPage.getErrorPasswordMessage().getText(),
        "The password must have minimum 8 characters including at least 1 uppercase, 1 lowercase and 1 numeric characters.");

  }

  @Test(priority = 8)
  public void invalidConfirmPassword() throws InterruptedException {
    // supplierSignPage.signUpName.sendKeys(supplierProp.getProperty("supplierEmail"));

    supplierSignPage.getSignUpName().sendKeys(getProp().getProperty("SupplierName"));
    // supplierSignPage.signUpEmail.sendKeys(prop.getProperty("supplierEmail"));
    String myString = getProp().getProperty("supplierEmail");
    String[] splitString = myString.split("@");
    supplierSignPage.getSignUpEmail().sendKeys(splitString[0] + '@');
    String[] mailString = splitString[1].split("");
    int len = mailString.length;
    for (int i = 0; i < len - 1; i++) {

      supplierSignPage.getSignUpEmail().sendKeys(mailString[i]);
      Thread.sleep(2);

    }
    Thread.sleep(2);
    supplierSignPage.getSignUpEmail().sendKeys(mailString[len - 1]);

    Thread.sleep(1000);

    wait.until(ExpectedConditions.textToBePresentInElementValue(supplierSignPage.getSignUpEmail(),
        getProp().getProperty("supplierEmail")));
    supplierSignPage.getSignPassword().sendKeys(getProp().getProperty("supplierPassword"));
    supplierSignPage.getConfirmPassword().sendKeys(getProp().getProperty("InvalidConPassword"));
    Thread.sleep(2000);
    // register.click();
    Assert.assertEquals(supplierSignPage.getErrorconfirmPassword().getText(), "Password mismatch");
  }

  @Test(priority = 9)
  public void validRegistrationTest() throws InterruptedException {

    supplierSignPage.getSignUpName().sendKeys(getProp().getProperty("SupplierName"));
    // supplierSignPage.signUpEmail.sendKeys(prop.getProperty("supplierEmail"));

    String register = getProp().getProperty("supplierEmail");
    String[] regString = register.split("@");
    supplierSignPage.getSignUpEmail().sendKeys(regString[0] + '@');
    String[] mailString = regString[1].split("");
    int len = mailString.length;
    for (int i = 0; i < len - 1; i++) {

      supplierSignPage.getSignUpEmail().sendKeys(mailString[i]);
      Thread.sleep(2);

    }
    Thread.sleep(2);
    supplierSignPage.getSignUpEmail().sendKeys(mailString[len - 1]);

    Thread.sleep(1000);

    wait.until(ExpectedConditions.textToBePresentInElementValue(supplierSignPage.getSignUpEmail(),
        getProp().getProperty("supplierEmail")));

    wait.until(ExpectedConditions.textToBePresentInElementValue(supplierSignPage.getSignUpEmail(),
        getProp().getProperty("supplierEmail")));
    supplierSignPage.getSignPassword().sendKeys(getProp().getProperty("supplierPassword"));
    supplierSignPage.getConfirmPassword().sendKeys(getProp().getProperty("supplierPassword"));
    supplierSignPage.getRegister().click();
    Assert.assertEquals(supplierSignPage.getSuccessSignUp().getText(), "Registered Successfully");

  }

  @Test(priority = 10)
  public void SignInPage() {
    supplierSignPage.getSignIn().click();

    supplierSignPage.getSignInPage().isDisplayed();

  }

  @AfterMethod
  public void tearDown() {
    getDriver().quit();
  }

}
