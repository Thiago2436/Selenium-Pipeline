package com.admin.authProcessor;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.sol.qa.base.TestBase;

public class AuthPage extends TestBase {

  public AuthPage() {
    PageFactory.initElements(getDriver(), this);
    }

  //Page Factory - OR:
  @FindBy(xpath  = "//input[contains(@formcontrolname,'email')]")
  private WebElement buyerUsername;

  @FindBy(xpath  = "//input[contains(@formcontrolname,'password')]")
  private WebElement buyerPassword;

  @FindBy(id = "loginBtn")
  private WebElement loginBtn;

  public final WebElement getBuyerUsername() {
    return buyerUsername;
    }

  public final WebElement getBuyerPassword() {
    return buyerPassword;
    }

  public final WebElement getLoginBtn() {
    return loginBtn;
    }
  }
