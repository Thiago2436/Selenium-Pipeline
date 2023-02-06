package com.sol.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.admin.login.pages.BuyerLoginPage;
import com.sol.qa.util.TestUtil;
import com.supplier.processors.SupplierSideLoginPage;

public class WindowManager {
  private static HashMap<String, RemoteWebDriver> driverMap = new HashMap<String, RemoteWebDriver>();
  private RemoteWebDriver activeDriver;
  private static Properties prop;
  final int sleepTime = 2000;
  private static String storeEventId;

  public WindowManager() {
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

  /**
   * This method switch to new window to the parameter given.
   * @throws InterruptedException
   */
  public void switchWindow(final String windowId) throws InterruptedException {
    RemoteWebDriver driverToSwitch = driverMap.get(windowId);
    this.activeDriver = driverToSwitch;
    Thread.sleep(sleepTime);
    }

  /**
   * This method opens new window.
   */
  public void openNewWindow(final String windowId) throws IOException, InterruptedException {
    // driver.switchTo().newWindow(WindowType.WINDOW);

    RemoteWebDriver driver =  launchBrowser();
    driverMap.put(windowId, driver);
    this.activeDriver = driver;
    System.out.println("Browser windowId>>>" + driverMap.get(windowId));
    }

  /**
   * This method opens the new chrome browser.
   */
  public static RemoteWebDriver launchBrowser() throws IOException {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Downloads\\chromedriver_win32\\chromedriver.exe");

    ChromeOptions options = new ChromeOptions();
    options.addArguments("window-size=1980,1080");
    RemoteWebDriver driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.getPageLoadTimeout()));
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.getImplicitWait()));
    return driver;

    }


  /**
   * This method opens the buyer url with current Window id specified.
   */
  public void visitBuyerURL(final String windowId, final String userName, final String password) {
    try {
      driverMap.get(windowId).get(prop.getProperty("url"));
      BuyerLoginPage buyerLoginPage = new BuyerLoginPage(driverMap.get(windowId));
      buyerLoginPage.successBuyerlogin(userName, password);
      final int timeSleep = 1000;
      Thread.sleep(timeSleep);
      } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      }

    }

  /**
   * This method closes the current window.
   */
  public void closeWindow(final String windowId) {
    driverMap.get(windowId).close();
    }

  /**
   * This method opens the supplier url with current Window id specified.
   */
  public void visitSupplierURL(final String windowId, final String userName, final String password) throws InterruptedException {
    driverMap.get(windowId).get(prop.getProperty("supplierUrl"));
    SupplierSideLoginPage supplierLogin = new SupplierSideLoginPage(driverMap.get(windowId));
    supplierLogin.loginAsSupplier(userName, password);
    // To use supplier login processor here
    }

  /**
   * This method navigate url to speicified event id.
   */
  public void navigateURL(final String windowId, final String eventId, final String page) throws InterruptedException {
    Thread.sleep(sleepTime);
    String eventURL = "";
    this.storeEventId = eventId;
    if (page.equalsIgnoreCase("ERCreation")) {
      System.out.println("if loop");
      eventURL = "https://yamuna.krinati.co/admin/buyer/event-rules/edit/" + eventId;
      }
    if (page.equalsIgnoreCase("MonitorEvent")) {
      eventURL = "https://yamuna.krinati.co/admin/monitor-event/event/" + eventId;
      }

    driverMap.get(windowId).navigate().to(eventURL);
    Thread.sleep(sleepTime);
    }

  /**
   * This method navigate to specified event id.
   */
  public void navigateBuyerURL(final String windowId, final String eventId, final String page) throws InterruptedException {
    String eventURL = "";
    this.storeEventId = eventId;
    if (page.equalsIgnoreCase("ERCreation")) {
      System.out.println("if loop");
      eventURL = "https://yamuna.krinati.co/admin/buyer/event-rules/edit/" + eventId;
      }
    if (page.equalsIgnoreCase("MonitorEvent")) {
      System.out.println("if loop monitor Event");
      eventURL = "https://yamuna.krinati.co/admin/monitor-event/event/" + eventId;
      }

    driverMap.get(windowId).navigate().to(eventURL);
    }

  /**
   * This method navigate to specified event id.
   */
  public void navigateSupplierURL(final String windowId, final String eventId, final String supplierId) throws InterruptedException {
    Thread.sleep(sleepTime);
    this.storeEventId = eventId;
    String eventURL = "https://yamuna.krinati.co/admin/supplier-response/event/" + eventId + "/" + supplierId + "?page=1";
    driverMap.get(windowId).navigate().to(eventURL);
    }

  /**
   * This method store current URL.
   */
  public void currentURL(final String windowId) {
    String strUrl = this.activeDriver.getCurrentUrl();
    System.out.println("Current URL>>>" + strUrl);
    }


  public static final void sendkeys(final RemoteWebDriver driver, final WebElement element, final int timeout, final String value) {
    new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
    element.sendKeys(value);
    }

  public static final void clickOn(final RemoteWebDriver driver, final WebElement element, final int timeout) {
    new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
    element.click();
    }

  public final  HashMap<String, RemoteWebDriver> getDriverMap() {
    return driverMap;
    }

  public final RemoteWebDriver getActiveDriver() {
    return activeDriver;
    }

  public final  Properties getProp() {
    return prop;
    }

  public final String getStoreEventId() {
    return storeEventId;
    }

  }
