package com.admin.authProcessor;

import java.awt.AWTException;

import com.buyer.events.processor.ITabCommandProcessor;
import com.sol.qa.util.XlsReader;

public class AuthProcessorCommand implements ITabCommandProcessor {

  private AuthPage authPage = new AuthPage();

  public final void loginAsBuyer(final String username, final String password) {
    authPage.getBuyerUsername().sendKeys(username);
    System.out.println("Enter Username Successful");
    authPage.getBuyerPassword().sendKeys(password);
    System.out.println("Enter Password Successful");
    authPage.getLoginBtn().click();
    System.out.println("Authentication Processor Complete");
    }

  /**
   *This method have XLs_Reader , command , argument1 , argument2 , argument3 and argument4 as Parameter
   *It have switch command and its corresponding method.
   */
  public void processCommand(
      final XlsReader reader,
      final String command,
      final String argument1,
      final String argument2,
      final String argument3,
      final String argument4
  ) throws AWTException, InterruptedException {
    switch (command) {
      case "BuyerLogin":
        loginAsBuyer(argument1, argument2);
      default:
        System.out.println("no match");
      }
    }
  }
