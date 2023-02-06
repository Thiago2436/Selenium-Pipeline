package com.buyer.events.processor.eventRulesProcessor;

import java.time.Duration;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.buyer.events.processor.ITabCommandProcessor;
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.TestUtil;
import com.sol.qa.util.XlsReader;

public class EventListProcessorCommand implements ITabCommandProcessor {
  private WindowManager windowManager;
  private EventListCreationPage eventListPage;
  final int sleepTime = 1000;

  public EventListProcessorCommand(final WindowManager windowManger) {
    this.windowManager = windowManger;
    eventListPage = new EventListCreationPage(this.driver());

    }

  private RemoteWebDriver driver() {
    return  this.windowManager.getActiveDriver();
    }


  /**
   * This method will click createNew button.
   * @throws InterruptedException
   */
  public void createNewFunction() throws InterruptedException {
    Thread.sleep(sleepTime);
    //eventListPage = new EventListCreationPage(this.driver());
    WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(TestUtil.getImplicitWait()));
    wait.until(ExpectedConditions.visibilityOf(eventListPage.getCreateNewButton()));
    System.out.println("Create New Button1");
    if (eventListPage.getCreateNewButton().isDisplayed()) {
      System.out.println("if loop");
      wait.until(ExpectedConditions.elementToBeClickable(eventListPage.getCreateNewButton()));
      eventListPage.getCreateNewButton().click();

      }
    System.out.println("Create New Button2");
    }


  @Override
  public final void processCommand(
      final XlsReader reader,
      final String command,
      final String argument1,
      final String argument2,
      final String argument3,
      final String argument4
  ) throws InterruptedException {
    switch (command) {
      case "InvokeNewEvent":
        createNewFunction();
        break;
      default:
        System.out.println("no match");
      }
    }

  }
