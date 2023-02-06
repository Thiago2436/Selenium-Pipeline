package com.sol.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sol.qa.util.TestUtil;

public class TestBase {
  private  RemoteWebDriver driver;
  private  Properties prop;

  //static String hubURL = "http://172.30.169.125:4444/wd/hub";

  public TestBase() {
    try {
      prop = new Properties();
      FileInputStream ip = new FileInputStream(
          System.getProperty("user.dir") + "/src/main/java/com/sol" + "/qa/config/config.properties");
      prop.load(ip);

      } catch (FileNotFoundException e) {
      e.printStackTrace();
      } catch (IOException e) {
      e.printStackTrace();
      }
    }

  public static void initialization() throws MalformedURLException {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Downloads\\chromedriver_win32\\chromedriver.exe");
    /*
     * ChromeOptions options = new ChromeOptions();
     * options.addArguments("--incognito"); driver = new ChromeDriver(options);
     * driver.manage().window().maximize(); driver.manage().deleteAllCookies();
     */
    }

  public static void initializationForSupplier() throws MalformedURLException {
    // String browserName = prop.getProperty("supplierBrowse");
    DesiredCapabilities caps = new DesiredCapabilities();
    //caps.setBrowserName(prop.getProperty("browser"));
    caps.setPlatform(Platform.LINUX);
    // caps.setVersion("96");

    ChromeOptions options = new ChromeOptions();
    options.merge(caps);

    RemoteWebDriver driver = new ChromeDriver();
    // driver = new RemoteWebDriver(new URL(hubURL), caps);

    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().pageLoadTimeout(TestUtil.getPageLoadTimeout(), TimeUnit.SECONDS);
    driver.manage().timeouts().implicitlyWait(TestUtil.getImplicitWait(), TimeUnit.SECONDS);

   // driver.get(prop.getProperty("supplierUrl"));

    }

  public static RemoteWebDriver launchSupplierURLOnIgnito() throws IOException {

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--incognito");
    options.addArguments("window-size=1980,1080");
    RemoteWebDriver driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().pageLoadTimeout(TestUtil.getPageLoadTimeout(), TimeUnit.SECONDS);
    driver.manage().timeouts().implicitlyWait(TestUtil.getImplicitWait(), TimeUnit.SECONDS);
    // driver.get(prop.getProperty("supplierUrl"));
    return driver;

    }

  public static RemoteWebDriver launchBrowser() throws IOException {
    ChromeOptions options = new ChromeOptions();
    //  options.addArguments("--incognito");
    options.addArguments("window-size=1980,1080");
    RemoteWebDriver driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().pageLoadTimeout(TestUtil.getPageLoadTimeout(), TimeUnit.SECONDS);
    driver.manage().timeouts().implicitlyWait(TestUtil.getImplicitWait(), TimeUnit.SECONDS);
    return driver;

    }
  /**
   * This method give the screenshot jpg for the failed testcases.
   */
  public void failed(final String testMethodName) {

    File srcFile = (((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
    try {
      FileUtils.copyFile(srcFile, new File("./screenshots/" + testMethodName + "-" + ".jpg"));
      } catch (IOException e) {
      e.printStackTrace();
      }
    }

  public static  void sendkeys(final RemoteWebDriver driver, final WebElement element, final int timeout, final String value) {
    new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
    element.sendKeys(value);
    }

  public static  void clickOn(final RemoteWebDriver driver, final WebElement element, final int timeout) {
    new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
    element.click();
    }

  /**
   * This method getter for driver.
   */
  public final RemoteWebDriver getDriver() {
    return driver;
    }

  /**
   * This method getter for Prop.
   */
  public Properties getProp() {
    return prop;
    }
  }
