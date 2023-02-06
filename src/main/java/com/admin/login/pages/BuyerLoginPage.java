package com.admin.login.pages;

import com.sol.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
*This class will have webElements of login screen and method of successful buyer login.
*@author  YamunaS
*/
public class BuyerLoginPage extends TestBase {

  // Page Factory - OR:
  @FindBy(xpath = "//input[contains(@formcontrolname,'email')]")
  private WebElement username;

  @FindBy(xpath = "//input[contains(@formcontrolname,'password')]")
  private WebElement password;

  @FindBy(id = "loginBtn")
  private WebElement loginBtn;

  @FindBy(xpath = "//button[contains(text(),'Sign Up')]")
  private WebElement signUpBtn;

  @FindBy(id = "login-intro")
  private WebElement krinatiLogo;

  @FindBy(xpath = "//a[contains(text(),' FORGOT PASSWORD?')]")
  private WebElement forgotPassword;

  @FindBy(xpath = "//*[@id=\"mat-checkbox-1-input\"]")
  private WebElement rememberMe;

  @FindBy(id = "loginEmailError")
  private WebElement errorMailMessage;

  @FindBy(id = "loginPasswordError")
  private WebElement errorpasswordMessage;

  @FindBy(xpath = "//*[@id=\"wrapper\"]/div/fuse-toolbar/mat-toolbar/div/div[2]/div/img")
  private WebElement profileMenu;

  @FindBy(xpath = "//*[@id=\"cdk-overlay-0\"]/div/div/button[2]")
  private WebElement logout;

  // Initializing the Page Objects:
  public  BuyerLoginPage(final RemoteWebDriver driver) {
    PageFactory.initElements(driver, this);
    }

  public final WebElement getUsername() {
    return username;
    }




  public final WebElement getPassword() {
    return password;
    }




  public final WebElement getLoginBtn() {
    return loginBtn;
    }




  public final WebElement getSignUpBtn() {
    return signUpBtn;
    }




  public final WebElement getKrinatiLogo() {
    return krinatiLogo;
    }




  public final WebElement getForgotPassword() {
    return forgotPassword;
    }




  public final WebElement getRememberMe() {
    return rememberMe;
    }




  public final WebElement getErrorMailMessage() {
    return errorMailMessage;
    }




  public final WebElement getErrorpasswordMessage() {
    return errorpasswordMessage;
    }




  public final WebElement getProfileMenu() {
    return profileMenu;
    }




  public final WebElement getLogout() {
    return logout;
    }

  /**
  *This method gets the parameter of buyer email and password and click on the login button.
  */
  public void successBuyerlogin(final String emailBuyer, final String pwd) throws InterruptedException {
    System.out.println("SuccessLoginPage" + emailBuyer);
    // loginPage.username.sendKeys(prop.getProperty("buyerEmail"));
    String myString = emailBuyer;
    String[] splitString = myString.split("@");
    username.sendKeys(splitString[0] + '@');
    final int sleepTime = 1000;
    Thread.sleep(sleepTime);
    String[] mailString = splitString[1].split("");
    int len = mailString.length;
    for (int i = 0; i < len - 1; i++) {

      username.sendKeys(mailString[i]);
      Thread.sleep(2);

      }
    Thread.sleep(2);
    username.sendKeys(mailString[len - 1]);
    Thread.sleep(sleepTime);
    password.sendKeys(pwd);
    loginBtn.click();
    }
  }
