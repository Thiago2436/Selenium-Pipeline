package com.supplier.login.testcases;

import com.sol.qa.base.CustomListener;
import com.sol.qa.base.TestBase;
import com.sol.qa.util.TestUtil;
import com.supplier.login.pages.SupplierLoginPage;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



@Listeners(CustomListener.class)
public class SupplierLoginTest extends TestBase {
  SupplierLoginPage supplierloginPage;
  
  // Initializing the Page Objects:
  public SupplierLoginTest(RemoteWebDriver driver) {
    PageFactory.initElements(driver, this);
  }


  @BeforeMethod
  public void setUp() throws MalformedURLException {
    initializationForSupplier();
    supplierloginPage = new SupplierLoginPage(getDriver());
  }

  /*
   * @Test(priority=1) public void loginPageTitleTest(){
   * 
   * Assert.assertEquals(supplierloginPage.SupplierTitle, "Supplier Sign In"); }
   */

  @Test(priority = 1)
  public void krinatiLogoImageTest() {

    Assert.assertTrue(supplierloginPage.getKrinatiLogo().isDisplayed());
  }

  @Test(priority = 2)
  public void validateEmail() throws InterruptedException {

    // supplierloginPage.userMail.sendKeys(prop.getProperty("invalidEmail"));
    String myString = getProp().getProperty("invalidEmail");
    String[] splitString = myString.split("@");
    supplierloginPage.getUserMail().sendKeys(splitString[0] + '@');
    Thread.sleep(1000);
    String[] mailString = splitString[1].split("");
    int len = mailString.length;
    for (int i = 0; i < len - 1; i++) {

      supplierloginPage.getUserMail().sendKeys(mailString[i]);
      Thread.sleep(2);

    }
    Thread.sleep(2);
    supplierloginPage.getUserMail().sendKeys(mailString[len - 1]);

    Thread.sleep(1000);
    supplierloginPage.getPassword().click();
    Assert.assertEquals(supplierloginPage.getErrorMailMessage().getText(),
        "You cannot login with this email ID. Please contact your buyer.");
  }

  @Test(priority = 4)
  public void SuccessloginTest(String emailSupplier, String pwd) throws InterruptedException {

    // supplierloginPage.userMail.sendKeys(prop.getProperty("supplierEmail"));
    String myString = emailSupplier;
    String[] splitString = myString.split("@");
    supplierloginPage.getUserMail().sendKeys(splitString[0] + '@');
    Thread.sleep(1000);
    String[] mailString = splitString[1].split("");
    int len = mailString.length;
    for (int i = 0; i < len - 1; i++) {

      supplierloginPage.getUserMail().sendKeys(mailString[i]);
      Thread.sleep(2);

    }
    Thread.sleep(2);
    supplierloginPage.getUserMail().sendKeys(mailString[len - 1]);

    Thread.sleep(1000);
    //supplierloginPage.password.sendKeys(prop.getProperty("supplierPassword"));
    supplierloginPage.getPassword().sendKeys(pwd);
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    js.executeScript("arguments[0].click();", supplierloginPage.getLoginBtn());
    Thread.sleep(2000);
    getDriver().manage().timeouts().implicitlyWait(TestUtil.getImplicitWait(), TimeUnit.SECONDS);
    // String actualURL = supplierloginPage.driver.getCurrentUrl();
    // System.out.println(actualURL);

    // Assert.assertEquals(actualURL, prop.getProperty("expectedSupUrl"));
  }

  @Test(priority = 5)
  public void verifyPassword() throws InterruptedException {
    // loginPage.username.sendKeys(prop.getProperty("buyerEmail"));
    String myString = getProp().getProperty("supplierEmail");
    String[] splitString = myString.split("@");
    supplierloginPage.getUserMail().sendKeys(splitString[0] + '@');
    Thread.sleep(1000);
    String[] mailString = splitString[1].split("");
    int len = mailString.length;
    for (int i = 0; i < len - 1; i++) {

      supplierloginPage.getUserMail().sendKeys(mailString[i]);
      Thread.sleep(2);

    }
    Thread.sleep(2);
    supplierloginPage.getUserMail().sendKeys(mailString[len - 1]);

    Thread.sleep(1000);
    supplierloginPage.getPassword().sendKeys(getProp().getProperty("InvalidConPassword"));
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    js.executeScript("arguments[0].click();", supplierloginPage.getLoginBtn());
    supplierloginPage.getLoginBtn().click();

    Assert.assertEquals(supplierloginPage.getErrorpasswordMessage().getText(), "Invalid Password");
  }

  @AfterMethod
  public void tearDown() {
    getDriver().quit();
  }



}
