package com.supplier.processors;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.buyer.events.processor.ITabCommandProcessor;
import com.sol.qa.base.TestBase;
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.XlsReader;



public class SupplierSideLoginProcessorCommand extends TestBase implements ITabCommandProcessor {
  private WindowManager windowManager;
  private RemoteWebDriver driver;

  /**
   * This method gets the current window manager driver.
   */
  public RemoteWebDriver driver() {
    return this.windowManager.getActiveDriver();
    }

  public SupplierSideLoginProcessorCommand(final WindowManager windowManger) {
    this.windowManager = windowManger;
    }

  private SupplierSideLoginPage supplierSideLogin = new SupplierSideLoginPage(driver);

  /**
   * This method gets username and password as parameter and login to the supplier home page.
   */
  public void loginAsSupplier(final String username, final String password) throws InterruptedException {
    supplierSideLogin.getSupplierUsername().sendKeys(username);
    //sendkeys(this.driver, supplierSideLogin.supplierUsername, 10, username);
    supplierSideLogin.getSupplierPassword().sendKeys(password);
    //sendkeys(this.driver, supplierSideLogin.supplierPassword, 10, password);
    supplierSideLogin.getLoginBtn().click();
    //clickOn(this.driver, supplierSideLogin.loginBtn, 10);
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
  ) throws InterruptedException {
    switch (command) {
      case "SupplierLogin":
        loginAsSupplier(argument1, argument2);
        break;
      default:
        System.out.println("No Match");
      }
    }
  }
