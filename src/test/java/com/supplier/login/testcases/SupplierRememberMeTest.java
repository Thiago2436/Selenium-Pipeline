package com.supplier.login.testcases;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sol.qa.base.TestBase;
import com.sol.qa.util.TestUtil;
import com.supplier.login.pages.SupplierLoginPage;

public class SupplierRememberMeTest extends TestBase {
  SupplierLoginPage loginSupPage;

  @BeforeMethod
  public void setUp() throws InterruptedException, MalformedURLException {

    initializationForSupplier();
    loginSupPage = new SupplierLoginPage(getDriver());
  }

  @Test(priority = 1)

  public void RememberNotChecked() throws MalformedURLException, InterruptedException {

    if (!loginSupPage.getRememberMe().isSelected()) {

      loginSupPage.getUserMail().sendKeys(getProp().getProperty("supplierEmail"));
      loginSupPage.getPassword().sendKeys(getProp().getProperty("supplierPassword"));

      Assert.assertEquals(false, loginSupPage.getRememberMe().isSelected());
      JavascriptExecutor jse = (JavascriptExecutor) getDriver();

      jse.executeScript("arguments[0].click();", loginSupPage.getLoginBtn());

      getDriver().manage().timeouts().pageLoadTimeout(TestUtil.getPageLoadTimeout(), TimeUnit.SECONDS);
      getDriver().manage().timeouts().implicitlyWait(TestUtil.getImplicitWait(), TimeUnit.SECONDS);

      WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
      wait.until(ExpectedConditions.elementToBeClickable(loginSupPage.getProfileMenu()));

      // loginSupPage.profileMenu.click();

      WebElement tableData = getDriver().findElement(By.xpath("//*[@id= 'table']"));
      wait.until(ExpectedConditions.visibilityOfAllElements(tableData));
      jse.executeScript("arguments[0].click()", loginSupPage.getProfileMenu());
      jse.executeScript("arguments[0].click()", loginSupPage.getLogout());

      Thread.sleep(1000);

      WebElement button = getDriver()
          .findElement(By.xpath("//button[@class='submit-button mat-flat-button mat-accent']"));
      button.click();

      String expectedUrl = getProp().getProperty("SupEventUrl");
      String actualUrl = getDriver().getCurrentUrl();
      Assert.assertEquals(actualUrl, expectedUrl);

    }
  }

  @Test(priority = 2)

  public void RememberMeChecked() throws MalformedURLException, InterruptedException {

    if (!loginSupPage.getRememberMe().isSelected()) {

      loginSupPage.getUserMail().sendKeys(getProp().getProperty("supplierEmail"));
      loginSupPage.getPassword().sendKeys(getProp().getProperty("supplierPassword"));

      Assert.assertEquals(false, loginSupPage.getRememberMe().isSelected());
      JavascriptExecutor jse = (JavascriptExecutor) getDriver();
      jse.executeScript("arguments[0].click()", loginSupPage.getRememberMe());
      jse.executeScript("arguments[0].click();", loginSupPage.getLoginBtn());

      getDriver().manage().timeouts().pageLoadTimeout(TestUtil.getPageLoadTimeout(), TimeUnit.SECONDS);
      getDriver().manage().timeouts().implicitlyWait(TestUtil.getImplicitWait(), TimeUnit.SECONDS);

      WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
      wait.until(ExpectedConditions.elementToBeClickable(loginSupPage.getProfileMenu()));

      // loginSupPage.profileMenu.click();

      WebElement tableData = getDriver().findElement(By.xpath("//*[@id= 'table']"));
      wait.until(ExpectedConditions.visibilityOfAllElements(tableData));
      jse.executeScript("arguments[0].click()", loginSupPage.getProfileMenu());
      jse.executeScript("arguments[0].click()", loginSupPage.getLogout());

      Thread.sleep(1000);

      WebElement button = getDriver()
          .findElement(By.xpath("//button[@class='submit-button mat-flat-button mat-accent']"));
      button.click();

      String expectedUrl = getProp().getProperty("SupEventUrl");
      String actualUrl = getDriver().getCurrentUrl();
      Assert.assertEquals(actualUrl, expectedUrl);

    }
  }

  @AfterMethod
  public void tearDown() {
    getDriver().quit();
  }

}
