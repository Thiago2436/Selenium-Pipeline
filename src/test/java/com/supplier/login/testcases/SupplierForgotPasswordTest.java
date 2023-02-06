package com.supplier.login.testcases;

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

import com.sol.qa.base.CustomListener;
import com.sol.qa.base.TestBase;
import com.supplier.login.pages.SupplierForgotPasswordPage;

@Listeners(CustomListener.class)
public class SupplierForgotPasswordTest extends TestBase {

  SupplierForgotPasswordPage supplierforgotPassword;

  public SupplierForgotPasswordTest() {
    super();

  }

  @BeforeMethod
  public void setUp() throws InterruptedException, MalformedURLException {

    initializationForSupplier();

    supplierforgotPassword = new SupplierForgotPasswordPage();
    supplierforgotPassword.getForgotPasswordLink().click();

    // System.out.println(driver.getCurrentUrl());

  }

  @Test(priority = 1)
  public void verifyPasswordPageLabel() {

    Assert.assertTrue(supplierforgotPassword.getPasswordLabel().isDisplayed());
  }

  @Test(priority = 2)
  public void cancelButtonEnabled() {

    Assert.assertTrue(supplierforgotPassword.getCancelButton().isEnabled());
  }

  @Test(priority = 3)
  public void sendButtonDisabled() {

    Assert.assertFalse(supplierforgotPassword.getSendButton().isEnabled());
  }

  @Test(priority = 4)
  public void validateEmail() throws InterruptedException {

    // supplierforgotPassword.AddEmail.sendKeys(prop.getProperty("invalidEmail"));
    String myString = getProp().getProperty("invalidEmail");
    String[] splitString = myString.split("@");
    supplierforgotPassword.getAddEmail().sendKeys(splitString[0] + '@');
    Thread.sleep(1000);
    String[] mailString = splitString[1].split("");
    int len = mailString.length;
    for (int i = 0; i < len - 1; i++) {

      supplierforgotPassword.getAddEmail().sendKeys(mailString[i]);
      Thread.sleep(2);

    }
    Thread.sleep(2);
    supplierforgotPassword.getAddEmail().sendKeys(mailString[len - 1]);

    Thread.sleep(1000);
    supplierforgotPassword.getSendButton().click();
    Assert.assertEquals(supplierforgotPassword.getErrorMessage().getText(),
        "You cannot login with this email ID. Please contact your buyer.");
  }

  @Test(priority = 6)
  public void AddEmailAddress() throws InterruptedException {

    String EmailAdd = getProp().getProperty("supplierEmail");
    String[] EmailAddress = EmailAdd.split("@");
    supplierforgotPassword.getAddEmail().sendKeys(EmailAddress[0] + '@');
    Thread.sleep(1000);
    String[] mailString = EmailAddress[1].split("");
    int len = mailString.length;
    for (int i = 0; i < len - 1; i++) {

      supplierforgotPassword.getAddEmail().sendKeys(mailString[i]);
      Thread.sleep(2);

    }
    Thread.sleep(2);
    supplierforgotPassword.getAddEmail().sendKeys(mailString[len - 1]);

    Thread.sleep(1000);
    supplierforgotPassword.getSendButton().click();
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
    wait.until(ExpectedConditions.elementToBeClickable(supplierforgotPassword.getSendButton()));
    // System.out.println(supplierforgotPassword.sendButton.isEnabled());

    // System.out.println("Click successful");
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    js.executeScript("arguments[0].click();", supplierforgotPassword.getSendButton());
    Assert.assertEquals(supplierforgotPassword.getSuccessLink().getText(), "Link sent successfully");

  }

  @AfterMethod
  public void tearDown() {
    getDriver().quit();
  }

}
