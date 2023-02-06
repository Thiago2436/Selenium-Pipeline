package com.admin.login.testcases;

import java.net.MalformedURLException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.admin.login.pages.BuyerHomePage;
import com.admin.login.pages.BuyerLoginPage;
import com.sol.qa.base.TestBase;

public class BuyerHomePageTest extends TestBase {

  BuyerLoginPage buyerLoginPage;

  BuyerHomePage buyerhomePage;

  public BuyerHomePageTest() {
    super();
  }

  @BeforeMethod
  public void setUp() throws InterruptedException, MalformedURLException {
    initialization();

    buyerLoginPage = new BuyerLoginPage(getDriver());

    buyerLoginPage.successBuyerlogin(getProp().getProperty("buyerEmail"), getProp().getProperty("password"));
  }

  @Test(priority = 1)
  public void verifyCreateButton() {
    buyerhomePage.getCreateNewButton().isEnabled();
  }

  @Test(priority = 2)
  public void createNewButton() {

    // buyerhomePage.createNewButton();
  }

  @AfterMethod
  public void tearDown() {
    getDriver().quit();
  }
}
