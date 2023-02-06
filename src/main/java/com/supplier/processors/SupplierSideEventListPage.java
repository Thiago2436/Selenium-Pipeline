package com.supplier.processors;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SupplierSideEventListPage  {

  private WebDriver driver;
  final int sleepTime = 1000;

  public SupplierSideEventListPage(final WebDriver webdriver) {
    this.driver = webdriver;
    //js = (JavascriptExecutor) webdriver;
    PageFactory.initElements(driver, this);

    }

  public final WebDriver getDriver() {
    return driver;
    }

  public final WebElement navigateSupplierToEvent(final String eventNumber) throws InterruptedException {
    WebElement selectedEvent = null;
    boolean flag = true;
    while (flag) {
      List<WebElement> eventCount = getDriver().findElements(By.cssSelector("mat-row[role = 'row']"));
      Thread.sleep(sleepTime);
      for (int i = 1; i <= eventCount.size(); i++) {
        String currentEvent = getDriver().findElement(By.cssSelector("[data-test = id-" + i + "]")).getText();
        if (currentEvent.equalsIgnoreCase(eventNumber)) {
          selectedEvent = getDriver().findElement(By.cssSelector("[data-test = id-" + i + "]"));
          flag = false;
          break;
          }
        }
      if (nextPageButton.getAttribute("class").contains("disabled")) {
        flag = false;
        break;
        } else {
        nextPageButton.click();
        }
      }
    return selectedEvent;
    }

  @FindBy(xpath = "//button[@aria-label='Next page']")
   private WebElement nextPageButton;

  @FindBy(xpath = "//button[@aria-label='Previous page']")
  private WebElement previousPage;

  @FindBy(css = "[data-test = 'intend-to-participate']")
  private WebElement participateButton;

  @FindBy(xpath = "//button[contains(text(),'DECLINE TO PARTICIPATE']")
  private WebElement declineButton;

  @FindBy(xpath = "/html/body/fuse-root/fuse-main/mat-sidenav-container/mat-sidenav-content"
      + "/div/div/div/fuse-content/app-event/div/div/div[2]/div[2]/img")
  private WebElement chatButton;

  @FindBy(id = "chatText")
  private WebElement enterChat;

  @FindBy(id = "sendText")
  private WebElement sendText;

  @FindBy(id = "uploadChatDoc")
  private WebElement sendDocumentChat;

  @FindBy(xpath = "/html/body/fuse-root/fuse-main/mat-sidenav-container/mat-sidenav/div/app-chat-view/div/div[3]/div/input")
  private WebElement fileUploadPath;

  @FindBy(id = "closeChat")
  private WebElement closeChat;


  /**
   * This getMessageText Method returns the web element of the parameter messageString passed.
   */
  public final WebElement getMessageText(final String messageString) {
    WebElement messageText = getDriver().findElement(By.xpath("//*[contains(text(),'" + messageString + "')]"));
    return messageText;
    }

  /**
   * This messageDiv Method returns the web element of the parameter messageString passed.
   */
  public final WebElement messageDiv(final String messageString) {
    WebElement messageDiv = null;
    WebElement chatParentDiv = getDriver().findElement(By.tagName("app-chat-content"));
    List<WebElement> childMessageDiv = chatParentDiv.findElements(By.className("ng-star-inserted"));
    for (WebElement messageDivTemp : childMessageDiv) {
      if (messageDivTemp.findElement(By.xpath("//*[contains(text(),'" + messageString + "')]")).getText().equals(messageString)) {
        messageDiv = messageDivTemp;
        }
      }
    System.out.println(messageDiv.getText());
    return messageDiv;
    }

  /**
   * This Method returns the web element of the parameter messageString passed.
   */
  public final WebElement getAttachmetSymbol(final String messageString) {
    WebElement attachmentSymbol = messageDiv(messageString).findElement(By.xpath("//*[contains(text(),'attach_file')]"));
    return attachmentSymbol;
    }

  /**
   * This Method returns the web element of the messageString passed.
   */
  public final WebElement getTimeOfMessage(final String messageString) {
    WebElement messageTime = messageDiv(messageString).findElement(By.className("message-date"));
    return messageTime;
    }

  public final WebElement getNextPageButton() {
    return nextPageButton;
    }

  public final WebElement getPreviousPage() {
    return previousPage;
    }

  public final WebElement getParticipateButton() {
    return participateButton;
    }

  public final WebElement getDeclineButton() {
    return declineButton;
    }

  public final WebElement getChatButton() {
    return chatButton;
    }

  public final WebElement getEnterChat() {
    return enterChat;
    }

  public final WebElement getSendText() {
    return sendText;
    }

  public final WebElement getSendDocumentChat() {
    return sendDocumentChat;
    }

  public final WebElement getFileUploadPath() {
    return fileUploadPath;
    }

  public final WebElement getCloseChat() {
    return closeChat;
    }
  }
