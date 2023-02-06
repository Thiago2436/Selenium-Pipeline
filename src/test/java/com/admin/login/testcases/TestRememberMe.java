package com.admin.login.testcases;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.sol.qa.base.CustomListener;
import com.sol.qa.base.TestBase;

@Listeners(CustomListener.class)
public class TestRememberMe extends TestBase {

  public static WebDriver driver, driver1;
  DesiredCapabilities capabilities = new DesiredCapabilities();

  static String hubURL = "http://172.30.133.139:4444/wd/hub";

  @BeforeMethod
  public void setUp() throws InterruptedException {

    }
 
  @Test(priority = 1)

  public void RememberMe() throws MalformedURLException {

    System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
    // DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("test-type");
    options.addArguments("start-maximized");
    options.addArguments("user-data-dir=D:/temp/");
    capabilities.setCapability("chrome.binary", "./drivers/chromedriver.exe");
    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    // driver = new ChromeDriver(capabilities);

    // options.addArguments("user-data-dir=./temp/");
    /*
     * capabilities.setCapability("chrome.binary","./drivers/chromedriver.exe");
     * capabilities.setCapability(ChromeOptions.CAPABILITY,options); WebDriver
     * driver = new ChromeDriver(capabilities);
     */

    driver.get(getProp().getProperty("url"));

    driver.manage().window().maximize();

    WebElement RememberMe = driver.findElement(By.xpath("//*[@id=\"mat-checkbox-1-input\"]"));
    WebElement username = driver.findElement(By.xpath("//*[@id=\"mat-input-0\"]"));
    WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
    WebElement loginBtn = driver
        .findElement(By.xpath("//button[@class='submit-button mat-flat-button mat-accent']"));

    if (!RememberMe.isSelected()) {

      username.sendKeys(getProp().getProperty("buyerEmail"));
      password.sendKeys(getProp().getProperty("password"));

      Assert.assertEquals(false, RememberMe.isSelected());
      JavascriptExecutor jse = (JavascriptExecutor) driver;
      jse.executeScript("arguments[0].click()", RememberMe);
      jse.executeScript("arguments[0].click();", loginBtn);

      driver.close();

      // driver1 = new ChromeDriver(capabilities);
      driver1.get(getProp().getProperty("url"));
      driver1.manage().window().maximize();
      JavascriptExecutor js = (JavascriptExecutor) driver1;
      WebElement loginBtn1 = driver1
          .findElement(By.xpath("//button[@class='submit-button mat-flat-button mat-accent']"));

      js.executeScript("arguments[0].click();", loginBtn1);
      String expectedUrl = getProp().getProperty("expectedUrl");
      String actualUrl = driver1.getCurrentUrl();
      Assert.assertEquals(actualUrl, expectedUrl);

      // driver1.quit();
    } else {

      JavascriptExecutor js = (JavascriptExecutor) driver;
      // loginBtn =driver.findElement(By.xpath("//button[@class='submit-button
      // mat-flat-button mat-accent']"));

      js.executeScript("arguments[0].click();", loginBtn);
      String expectedUrl = getProp().getProperty("expectedUrl");
      String actualUrl = driver.getCurrentUrl();
      Assert.assertEquals(actualUrl, expectedUrl);
      driver.quit();
      }

    }

  }
