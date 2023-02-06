package com.supplier.events.processor;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import com.admin.login.pages.BuyerHomePage;
import com.buyer.events.processor.ITabCommandProcessor;
import com.sol.qa.base.TestBase;
import com.sol.qa.util.XlsReader;

public class SupplierLoginProcessor extends TestBase implements ITabCommandProcessor {

  private SupplierPage supplierLogin = new SupplierPage();
  private JavascriptExecutor js = (JavascriptExecutor) getDriver();
  private BuyerHomePage buyerHomePage = new BuyerHomePage();

  /**
   *This method have XLs_Reader , command , argument1 , argument2 , argument3 and argument4 as Parameter
   *It have switch command and its corresponding method.
   */
  public void processCommand(final XlsReader reader, final String command, final String argument1, final String argument2,
      final String argument3, final String argument4) throws AWTException, InterruptedException {
    // TODO Auto-generated method stub
    switch (command) {

      default:
        System.out.println("No Supplier Login Command Match");
      }

    }
  /**
   * This method launch browser for supplier login.
   */
  public void invokeSupplierLogin(final String userName, final String loginPassword) throws IOException, InterruptedException {

    launchBrowser();
    final int sleepTime = 3000;
    Thread.sleep(sleepTime);
    String winHandleBefore = getDriver().getWindowHandle();
    for (String winHandle : getDriver().getWindowHandles()) {
      getDriver().switchTo().window(winHandle);
      }

    }

  }
