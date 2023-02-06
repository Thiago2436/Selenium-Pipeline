package com.admin.login.testcases;

import java.net.MalformedURLException;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.admin.login.pages.BuyerForgotPassword;
import com.sol.qa.base.CustomListener;
import com.sol.qa.base.TestBase;

@Listeners(CustomListener.class)
public class BuyerForgotPasswordTest extends TestBase {

  BuyerForgotPassword forgotPasswordPage;

  public BuyerForgotPasswordTest() {
    super();

  }

  @BeforeMethod
  public void setUp() throws InterruptedException, MalformedURLException {

    initialization();

    forgotPasswordPage = new BuyerForgotPassword();
    forgotPasswordPage.getForgotPasswordLink().click();

    // System.out.println(driver.getCurrentUrl());

  }

  @Test(priority = 1)
  public void verifyPasswordPageLabel() {

    Assert.assertTrue(forgotPasswordPage.getPasswordLabel().isDisplayed());
  }

  @Test(priority = 2)
  public void cancelButtonEnabled() {

    Assert.assertTrue(forgotPasswordPage.getCancelButton().isEnabled());
  }

  @Test(priority = 3)
  public void sendButtonDisabled() {

    Assert.assertFalse(forgotPasswordPage.getSendButton().isEnabled());
  }

  @Test(priority = 4)
  public void validateEmail() throws InterruptedException {

    // forgotPasswordPage.AddEmail.sendKeys(prop.getProperty("invalidEmail"));
    String myString = getProp().getProperty("invalidEmail");
    String[] splitString = myString.split("@");

    forgotPasswordPage.getAddEmail().sendKeys(splitString[0] + '@');
    Thread.sleep(1000);

    String[] mailString = splitString[1].split("");
    int len = mailString.length;
    for (int i = 0; i < len - 1; i++) {

      forgotPasswordPage.getAddEmail().sendKeys(mailString[i]);
      Thread.sleep(2);

    }
    Thread.sleep(2);
    forgotPasswordPage.getAddEmail().sendKeys(mailString[len - 1]);

    Thread.sleep(1000);
    // forgotPasswordPage.AddEmail.sendKeys(Keys.ENTER);
    forgotPasswordPage.getSendButton().click();
    Assert.assertEquals(forgotPasswordPage.getErrorMessage().getText(),
        "This email does not exist. Please contact your Admin");
  }

  @Test(priority = 6)
  public void AddEmailAddress() throws InterruptedException {

    String myString = getProp().getProperty("buyerEmail");
    String[] splitString = myString.split("@");
    forgotPasswordPage.getAddEmail().sendKeys(splitString[0] + '@');

    String[] mailString = splitString[1].split("");
    int len = mailString.length;
    for (int i = 0; i < len - 1; i++) {

      forgotPasswordPage.getAddEmail().sendKeys(mailString[i]);
      Thread.sleep(2);

    }
    Thread.sleep(2);
    forgotPasswordPage.getAddEmail().sendKeys(mailString[len - 1]);
    Thread.sleep(1000);
    /*
     * WebDriverWait wait = new WebDriverWait (driver, 30);
     * wait.until(ExpectedConditions.textToBePresentInElementValue(
     * forgotPasswordPage.AddEmail, prop.getProperty("buyerEmail")));
     */

    Actions builder = new Actions(getDriver());
    builder.moveToElement(forgotPasswordPage.getSendButton()).click(forgotPasswordPage.getSendButton());
    builder.perform();

    // forgotPasswordPage.sendButton.click();

    /*
     * JavascriptExecutor js = (JavascriptExecutor)driver;
     * js.executeScript("arguments[0].click();", forgotPasswordPage.sendButton);
     */

    Assert.assertEquals(forgotPasswordPage.getSuccessLink().getText(), "Link sent successfully");

  }

  @AfterMethod
  public void tearDown() {
    getDriver().quit();
  }

}
