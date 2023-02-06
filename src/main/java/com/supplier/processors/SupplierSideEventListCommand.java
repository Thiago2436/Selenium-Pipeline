package com.supplier.processors;

import java.awt.AWTException;
import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.buyer.events.processor.CommandProcessor;
import com.buyer.events.processor.ITabCommandProcessor;
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.TestUtil;
import com.sol.qa.util.XlsReader;

public class SupplierSideEventListCommand implements ITabCommandProcessor {

  private WindowManager windowManager;
  final int sleepTime = 10000;

  private SupplierSideEventListPage supplierSideEventList;
  private CommandProcessor commandProcessor;

  public SupplierSideEventListCommand(final WindowManager windowManger) {
    this.windowManager = windowManger;
    supplierSideEventList = new SupplierSideEventListPage(this.driver());
    commandProcessor = new CommandProcessor();
    }

  private RemoteWebDriver driver() {
    return  this.windowManager.getActiveDriver();
    }

  /**
   * This method clicks the event from the list.
   */
  public void openEventFromList(final String eventId) throws InterruptedException {
    System.out.println("Enter1" + eventId);
    Thread.sleep(sleepTime);
    clickOn(this.driver(), supplierSideEventList.navigateSupplierToEvent(eventId), TestUtil.getTimeOut());
    }

  /**
   * This method clicks the Participate button.
   */
  public void clickPraticipate() throws InterruptedException {
    System.out.println("clickPraticipate >>>");
    clickOn(this.driver(), supplierSideEventList.getParticipateButton(), TestUtil.getTimeOut());
    }

  /**
   * This method clicks on the open chat section.
   */
  public void openChatSection() throws InterruptedException, ElementClickInterceptedException {
    clickOn(this.driver(), supplierSideEventList.getChatButton(), TestUtil.getTimeOut());
    }

  /**
   * This method enter the data in text chat.
   */
  public void enterTextChat(final String textMessage) throws InterruptedException {
    sendkeys(this.driver(), supplierSideEventList.getEnterChat(), TestUtil.getTimeOut(), textMessage);
    clickOn(this.driver(), supplierSideEventList.getSendText(), TestUtil.getTimeOut());
    }

  /**
   * This method add the attachment to the chat.
   */
  public void enterAttachChat(final String fileLink)
      throws InterruptedException, AWTException, ElementClickInterceptedException {
    String filePath = System.getProperty("user.dir") + "//src//main//java//com//sol//qa//testdata//" + fileLink;
    supplierSideEventList.getFileUploadPath().sendKeys(filePath);
    clickOn(this.driver(), supplierSideEventList.getCloseChat(), TestUtil.getTimeOut());
    }

  /**
   * This method validate the close chat section.
   */
  public void checkCloseChatSection() throws InterruptedException, AWTException, ElementClickInterceptedException {
    clickOn(this.driver(), supplierSideEventList.getChatButton(), TestUtil.getTimeOut());
    clickOn(this.driver(), supplierSideEventList.getCloseChat(), TestUtil.getTimeOut());
    }

  /**
   * This method check the message received from event owner.
   */
  public void checkTextReceivedFromEventOwner() {
    // messageString will be sent using hashmap
    String messageString = "Here is a test message";
    clickOn(this.driver(), supplierSideEventList.getChatButton(), TestUtil.getTimeOut());
    assert supplierSideEventList.getMessageText(messageString).isDisplayed();
    }

  /**
   * This method assert the attachment received from the event owner.
   */
  public void checkAttachmentReceivedFromEventOwner() {
    // fileNameString will be sent using hashmap
    String fileNameString = "Pricing Sheet-3417.xlsx";
    assert supplierSideEventList.getMessageText(fileNameString).isDisplayed();
    assert supplierSideEventList.getAttachmetSymbol(fileNameString).isDisplayed();
    System.out.println("Attachment received");
    }


  /**
   * This method assert the the time stamp for the text message.
   */
  public void assertTimeStampForTextMessage(final String messageString) {
    // fileNameString will be sent using hashmap
    String messageContent = "Here is a test message";
    assert supplierSideEventList.getTimeOfMessage(messageContent).getText().endsWith("AM") || supplierSideEventList.getTimeOfMessage(messageContent).
    getText().endsWith("PM");
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
      case "SelectEventFromList":
        openEventFromList(argument1);
        break;
      case "ParticipateEvent":
        clickPraticipate();
        break;
      case "OpenChatSection":
        openChatSection();
        break;
      case "SendTextChat":
        enterTextChat(argument1);
        break;
      case "SendAttachmentChat":
        enterAttachChat(argument1);
        break;
      case "CloseChatSection":
        checkCloseChatSection();
        break;
      case "ValidateTextMessageTimestamp":
        assertTimeStampForTextMessage(argument1);
        break;
      case "ValidateTextMessageReceived":
        checkTextReceivedFromEventOwner();
        break;
      case "ValidateAttachmentMessageReceived":
        checkAttachmentReceivedFromEventOwner();
        break;
      default:
        System.out.println("No Match");
      }
    }

  public static final void sendkeys(final RemoteWebDriver driver, final WebElement element, final int timeout, final String value) {
    new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
    element.sendKeys(value);
    }

  public static final void clickOn(final RemoteWebDriver driver, final WebElement element, final int timeout) {
    new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
    element.click();
    }
  }
