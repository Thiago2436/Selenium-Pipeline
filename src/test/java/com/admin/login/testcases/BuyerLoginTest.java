package com.admin.login.testcases;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.admin.login.pages.BuyerHomePage;
import com.admin.login.pages.BuyerLoginPage;
import com.sol.qa.base.CustomListener;
import com.sol.qa.base.TestBase;

@Listeners(CustomListener.class)
public class BuyerLoginTest extends TestBase {
  BuyerLoginPage loginPage;
  BuyerHomePage homePage;

  public BuyerLoginTest() {
    super();
    }

  @BeforeMethod
  public void setUp() throws MalformedURLException {
    initialization();

    loginPage = new BuyerLoginPage(getDriver());
    }

  @Test(priority = 1)
  public void loginPageTitleTest() {

    Assert.assertEquals(getDriver().getTitle(), "Krinati - Admin Dashboard");
    }

  @Test(priority = 2)
  public void krinatiLogoImageTest() {

    Assert.assertTrue(loginPage.getKrinatiLogo().isDisplayed());
    }

  @Test(priority = 3)
  public void validateEmail() throws InterruptedException {

    // loginPage.username.sendKeys(prop.getProperty("invalidEmail"));
    String myString = getProp().getProperty("invalidEmail");
    String[] splitString = myString.split("@");
    loginPage.getUsername().sendKeys(splitString[0] + '@');
    Thread.sleep(1000);
    String[] mailString = splitString[1].split("");
    int len = mailString.length;
    for (int i = 0; i < len - 1; i++) {

      loginPage.getUsername().sendKeys(mailString[i]);
      Thread.sleep(2);

      }
    Thread.sleep(2);
    loginPage.getUsername().sendKeys(mailString[len - 1]);

    Thread.sleep(1000);
    loginPage.getLoginBtn().click();
    Assert.assertEquals(loginPage.getErrorMailMessage().getText(),
        "This email does not exist. Please contact your Admin");
    }

  @Test(priority = 4)
  public void verifyPassword() throws InterruptedException {
    // loginPage.username.sendKeys(prop.getProperty("buyerEmail"));
    String myString = getProp().getProperty("buyerEmail");
    String[] splitString = myString.split("@");
    loginPage.getUsername().sendKeys(splitString[0] + '@');
    Thread.sleep(1000);
    String[] mailString = splitString[1].split("");
    int len = mailString.length;
    for (int i = 0; i < len - 1; i++) {

      loginPage.getUsername().sendKeys(mailString[i]);
      Thread.sleep(2);

      }
    Thread.sleep(2);
    loginPage.getUsername().sendKeys(mailString[len - 1]);

    Thread.sleep(1000);
    loginPage.getPassword().sendKeys(getProp().getProperty("InvalidConPassword"));
    loginPage.getLoginBtn().click();
    Assert.assertEquals(loginPage.getErrorpasswordMessage().getText(), "Invalid Password");
    }

  @Test(priority = 5)
  public void SuccessloginTest() throws InterruptedException {
    loginPage.successBuyerlogin(getProp().getProperty("buyerEmail"), getProp().getProperty("password"));

    }

  @AfterMethod
  public void tearDown() {
    getDriver().quit();
    }

  }
