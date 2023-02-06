package com.supplier.login.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sol.qa.base.TestBase;

public class SupplierLoginPage extends TestBase {

  // Page Factory - OR:

  @FindBy(xpath = "//*[@id=\"login-form\"]/div/div/h4")
  private WebElement supplierTitle;

  @FindBy(xpath = "//input[contains(@formcontrolname,'email')]")
  private WebElement userMail;

  @FindBy(xpath = "//input[contains(@formcontrolname,'password')]")
  private WebElement password;

  @FindBy(xpath = "//button[@class = 'submit-button mat-flat-button mat-accent']")
  private WebElement loginBtn;

  @FindBy(xpath = "//button[contains(text(),'Sign Up')]")
  private WebElement signUpBtn;

  @FindBy(xpath = "//*[@id=\"login-intro\"]")
  private WebElement krinatiLogo;

  @FindBy(xpath = "//a[contains(text(),' FORGOT PASSWORD?')]")
  private WebElement forgotPassword;

  @FindBy(xpath = "//*[@id=\"mat-checkbox-1-input\"]")
  private WebElement rememberMe;

  @FindBy(xpath = "//*[@class ='mat-hint ng-star-inserted' and contains(text(),' You cannot login with this email ID. Please contact your buyer. ')]")
  private WebElement errorMailMessage;

  @FindBy(xpath = "//*[@class= 'mat-hint ng-star-inserted' and contains(text(),' Invalid Password ')]")
  private WebElement errorpasswordMessage;

  @FindBy(xpath = "//*[@id=\"wrapper\"]/div/fuse-toolbar/mat-toolbar/div/div[2]/div/img")
  private WebElement profileMenu;

  @FindBy(xpath = "//*[@id=\"cdk-overlay-0\"]/div/div/button[2]")
  private WebElement logout;

  // Initializing the Page Objects:
  public SupplierLoginPage(final RemoteWebDriver driver) {
    PageFactory.initElements(driver, this);
    }

  /**
   * This method gets store the supplier title.
   */
  public WebElement getSupplierTitle() {
    return supplierTitle;
    }


  public final WebElement getUserMail() {
    return userMail;
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
  }
