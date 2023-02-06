package com.supplier.events.processor;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sol.qa.base.TestBase;

public class SupplierPage extends TestBase {

  public SupplierPage() {
    PageFactory.initElements(getDriver(), this);
    }

  @FindBy(xpath = "//input[contains(@formcontrolname,'email')]")
  private WebElement userMail;

  @FindBy(xpath = "//input[contains(@formcontrolname,'password')]")
  private WebElement password;

  @FindBy(xpath = "//button[@id = 'login']")
  private WebElement loginBtn;

  public final WebElement getUserMail() {
    return userMail;
    }

  public final WebElement getPassword() {
    return password;
    }

  public final WebElement getLoginBtn() {
    return loginBtn;
    }

  }
