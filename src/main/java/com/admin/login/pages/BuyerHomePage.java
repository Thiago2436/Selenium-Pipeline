package com.admin.login.pages;

import com.sol.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
*BuyerHomePage is the program of webElements.
*@author  YamunaS
*/
public class BuyerHomePage extends TestBase {

  @FindBy(xpath = "//button[@id = 'createEvent']")
  private WebElement createNewButton;

  public BuyerHomePage() {
    PageFactory.initElements(getDriver(), this);
    }

  public final WebElement getCreateNewButton() {
    return createNewButton;
    }
  }
