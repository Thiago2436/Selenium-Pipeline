package com.admin.login.testcases;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.admin.login.pages.BuyerLoginPage;
import com.sol.qa.base.CustomListener;
import com.sol.qa.base.TestBase;
import com.sol.qa.util.TestUtil;

@Listeners(CustomListener.class)
public class BuyerRememberMeTest extends TestBase {

  BuyerLoginPage loginPage;

  @BeforeMethod
  public void setUp() throws InterruptedException, MalformedURLException {

    initialization();
    loginPage = new BuyerLoginPage(getDriver());
    }

  @Test(priority = 2)

  public void RememberMeChecked() throws MalformedURLException, InterruptedException {

    if (!loginPage.getRememberMe().isSelected()) {

      loginPage.getUsername().sendKeys(getProp().getProperty("buyerEmail"));
      loginPage.getPassword().sendKeys(getProp().getProperty("password"));

      Assert.assertEquals(false, loginPage.getRememberMe().isSelected());
      JavascriptExecutor jse = (JavascriptExecutor) getDriver();
      jse.executeScript("arguments[0].click()", loginPage.getRememberMe());
      jse.executeScript("arguments[0].click();", loginPage.getLoginBtn());


      getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.getPageLoadTimeout()));
      getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.getImplicitWait()));
      loginPage.getProfileMenu().click();

      loginPage.getLogout().click();

      Thread.sleep(1000);

      WebElement button = getDriver()
          .findElement(By.xpath("//button[@class='submit-button mat-flat-button mat-accent']"));
      button.click();

      String expectedUrl = getProp().getProperty("expectedUrl");
      String actualUrl = getDriver().getCurrentUrl();
      Assert.assertEquals(actualUrl, expectedUrl);

      }
    }

  @Test(priority = 1)

  public void RememberNotChecked() throws MalformedURLException, InterruptedException {

    loginPage.getUsername().sendKeys(getProp().getProperty("buyerEmail"));
    loginPage.getPassword().sendKeys(getProp().getProperty("password"));

    Assert.assertEquals(false, loginPage.getRememberMe().isSelected());
    JavascriptExecutor jse = (JavascriptExecutor) getDriver();
    jse.executeScript("arguments[0].click();", loginPage.getLoginBtn());

    getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.getPageLoadTimeout()));
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.getImplicitWait()));

    loginPage.getProfileMenu().click();

    loginPage.getLogout().click();

    Thread.sleep(1000);

    WebElement button = getDriver().findElement(By.xpath("//button[@class='submit-button mat-flat-button mat-accent']"));
    button.click();

    String expectedUrl = getProp().getProperty("expectedUrl");
    String actualUrl = getDriver().getCurrentUrl();
    Assert.assertEquals(actualUrl, expectedUrl);

    }

  @AfterMethod
  public void tearDown() {
    getDriver().quit();
    }

  }
