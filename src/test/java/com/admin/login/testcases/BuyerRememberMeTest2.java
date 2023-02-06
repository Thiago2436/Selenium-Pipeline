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
public class BuyerRememberMeTest2 extends TestBase {

  BuyerLoginPage loginPage;

  @BeforeMethod
  public void setUp() throws InterruptedException, MalformedURLException {

    initialization();
    }

  @Test(priority = 2)

  public void RememberMeChecked() throws MalformedURLException, InterruptedException {

    WebElement RememberMe = getDriver().findElement(By.xpath("//*[@id=\"mat-checkbox-1-input\"]"));
    WebElement username = getDriver().findElement(By.xpath("//*[@id=\"mat-input-0\"]"));
    WebElement password = getDriver().findElement(By.xpath("//input[@type='password']"));
    WebElement loginBtn = getDriver()
        .findElement(By.xpath("//button[@class='submit-button mat-flat-button mat-accent']"));

    if (!RememberMe.isSelected()) {

      username.sendKeys(getProp().getProperty("buyerEmail"));
      password.sendKeys(getProp().getProperty("password"));

      Assert.assertEquals(false, RememberMe.isSelected());
      JavascriptExecutor jse = (JavascriptExecutor) getDriver();
      jse.executeScript("arguments[0].click()", RememberMe);
      jse.executeScript("arguments[0].click();", loginBtn);

      getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.getPageLoadTimeout()));
      getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.getImplicitWait()));

      WebElement profileMenu = getDriver()
          .findElement(By.xpath("//*[@id=\"wrapper\"]/div/fuse-toolbar/mat-toolbar/div/div[2]/div/img"));
      profileMenu.click();

      WebElement logout = getDriver().findElement(By.xpath("//*[@id=\"cdk-overlay-0\"]/div/div/button[2]"));
      logout.click();

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

    WebElement RememberMe = getDriver().findElement(By.xpath("//*[@id=\"mat-checkbox-1-input\"]"));
    WebElement username = getDriver().findElement(By.xpath("//*[@id=\"mat-input-0\"]"));
    WebElement password = getDriver().findElement(By.xpath("//input[@type='password']"));
    WebElement loginBtn = getDriver()
        .findElement(By.xpath("//button[@class='submit-button mat-flat-button mat-accent']"));

    username.sendKeys(getProp().getProperty("buyerEmail"));
    password.sendKeys(getProp().getProperty("password"));

    Assert.assertEquals(false, RememberMe.isSelected());
    JavascriptExecutor jse = (JavascriptExecutor) getDriver();
    jse.executeScript("arguments[0].click();", loginBtn);
    getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.getPageLoadTimeout()));
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.getImplicitWait()));

    WebElement profileMenu = getDriver()
        .findElement(By.xpath("//*[@id=\"wrapper\"]/div/fuse-toolbar/mat-toolbar/div/div[2]/div/img"));
    profileMenu.click();

    WebElement logout = getDriver().findElement(By.xpath("//*[@id=\"cdk-overlay-0\"]/div/div/button[2]"));
    logout.click();

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
