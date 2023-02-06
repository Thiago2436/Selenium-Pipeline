package com.supplier.processors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sol.qa.base.TestBase;

public class SupplierSideLoginPage extends TestBase {
  public SupplierSideLoginPage(final RemoteWebDriver driver) {
    PageFactory.initElements(driver, this);
    }

  @FindBy(xpath = "//input[@formcontrolname='email']")
   private WebElement supplierUsername;

  @FindBy(xpath = "//input[@formcontrolname = 'password']")
   private WebElement supplierPassword;

  @FindBy(xpath = "//button[@id = 'login']")
   private  WebElement loginBtn;

  /**
   * This method gets username and password as parameter and login to the supplier home page.
   */
  public void loginAsSupplier(final String username, final String password) throws InterruptedException {
    supplierUsername.sendKeys(username);

    //sendkeys(this.driver, supplierUsername, 10, username);
    supplierPassword.sendKeys(password);
    //sendkeys(this.driver, supplierPassword, 10, password);
    loginBtn.click();
    //clickOn(this.driver, loginBtn, 10);
    }

  public final WebElement getSupplierUsername() {
    return supplierUsername;
    }

  public final WebElement getSupplierPassword() {
    return supplierPassword;
    }

  public final WebElement getLoginBtn() {
    return loginBtn;
    }
  }
