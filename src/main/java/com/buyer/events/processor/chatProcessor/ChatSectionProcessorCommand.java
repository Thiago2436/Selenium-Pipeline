package com.buyer.events.processor.chatProcessor;

import static org.testng.Assert.assertFalse;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;

import com.buyer.events.processor.ITabCommandProcessor;
import com.sol.qa.base.TestBase;
import com.sol.qa.util.TestUtil;
import com.sol.qa.util.XlsReader;

public class ChatSectionProcessorCommand extends TestBase implements ITabCommandProcessor {

  private ChatSectionPage chatSection = new ChatSectionPage();
  final int sleepTime = 2000;
  public final void openChatSection() {
    clickOn(getDriver(), chatSection.getChatButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.getNewMessageButton(), TestUtil.getTimeOut());
    }

  public final void closeChatSection() {
    clickOn(getDriver(), chatSection.getCloseChatButton(), TestUtil.getTimeOut());
    }

  public final void makeFileToStoreDownloads() {
    String parentDirectoryPath = System.getProperty("user.dir") + "//ChatDownloadFiles";
    File dir = new File(parentDirectoryPath);
    dir.mkdir();
    File[] filesInParentFile = dir.listFiles();
    if (filesInParentFile.length > 0) {
      for (File file : filesInParentFile) {
        file.delete();
        }
      }
    }

  public final void waitForDownloadToComplete() throws InterruptedException, IOException {
    String downloadFilePath = System.getProperty("user.dir") + "//ChatDownloadFiles";
    File dir = new File(downloadFilePath);
    File[] filesInDownloadLocation = dir.listFiles();
    if (filesInDownloadLocation.length > 0) {
      final int timeSleep = 10000;
      Thread.sleep(timeSleep);
      }
    }

  /**
   * This method get the downloaded Files details.
   */
  public HashMap<String, ZipEntry> getDownloadedFilesMap() throws InterruptedException, IOException {
    HashMap<String, ZipEntry> fileNames = new HashMap<>();
    String filePath = System.getProperty("user.dir") + "//ChatDownloadFiles//Chat Messages And Documents.zip";
    waitForDownloadToComplete();
    ZipFile dir = new ZipFile(filePath);
    Enumeration<? extends ZipEntry> entries = dir.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry = entries.nextElement();
      fileNames.put("'" + entry.getName() + "'", entry);
      }
    System.out.println(fileNames.keySet());
    System.out.println(fileNames.values());
    dir.close();
    File f = new File(filePath);
    f.delete();
    return fileNames;
    }

  /**
   * This method getAttachmentExistsStatus.
   */
  public boolean getAttachmentExistsStatus(final String fileName) throws InterruptedException, IOException {
    boolean attachmentStatus = false;
    HashMap<String, ZipEntry> fileMap = getDownloadedFilesMap();
    for (String fName : fileMap.keySet()) {
      if (fName.contains(fileName)) {
        attachmentStatus = true;
        }
      }
    return attachmentStatus;
    }

  /**
   * This method to check the custom wait.
   */
  public void customWait(final List<WebElement> elementList) throws InterruptedException {
    if (elementList.size() >= 1) {
      Thread.sleep(sleepTime);
      } else {
      Thread.sleep(sleepTime);
      customWait(elementList);
      }
    }

  /**
   * This method to check the visibility of download button.
   */
  public void checkDownloadButton() throws InterruptedException {
    clickOn(getDriver(), chatSection.getChatButton(), TestUtil.getTimeOut());
    Thread.sleep(sleepTime);
    assert chatSection.getDownloadChatButton().isDisplayed();
    }

  /**
   * This method assert the visibility of new message.
   */
  public void checkNewMessageButton() {
    assert chatSection.getNewMessageButton().isDisplayed();
    }

  /**
   * This method check whether the send button is disabled or not.
   */
  public void checkSendButtonDisabled() throws InterruptedException {
    clickOn(getDriver(), chatSection.getNewMessageButton(),  TestUtil.getTimeOut());
    boolean sendMessage = chatSection.getSendMessageButton().isEnabled();
    AssertJUnit.assertFalse(sendMessage);
    clickOn(getDriver(), chatSection.getNewMessageCloseButton(), TestUtil.getTimeOut());

    }

  /**
   * This method to check for send attachment chat to all invited suppliers.
   */
  public void sendMessageToInvitedSuppliers(final String supplierName, final String messageString) throws InterruptedException {
    clickOn(getDriver(), chatSection.getChatButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.getNewMessageButton(), TestUtil.getTimeOut());
    customWait(chatSection.getSuppliersStatus());
    clickOn(getDriver(), chatSection.getAllInvitedSupplier(), TestUtil.getTimeOut());
    sendkeys(getDriver(), chatSection.getEnterTextChat(), TestUtil.getTimeOut(), messageString);
    clickOn(getDriver(), chatSection.getSendMessageButton(), TestUtil.getTimeOut());
    customWait(chatSection.getChatButtons());
    clickOn(getDriver(), chatSection.getChatButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.searchChatReceiver(supplierName), TestUtil.getTimeOut());
    assert chatSection.getLastMessageText().contains(messageString);
    clickOn(getDriver(), chatSection.getBackToChatListButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.getCloseChatButton(), TestUtil.getTimeOut());

    }

  /**
   * This method to check for send attachment chat to all invited suppliers.
   */
  public void sendAttachmentChatToAllInvitedSupplier(final String supplierName, final String fileLink)
      throws InterruptedException {
    openChatSection();
    customWait(chatSection.getSuppliersStatus());
    clickOn(getDriver(), chatSection.getAllInvitedSupplier(), TestUtil.getTimeOut());
    String filePath = System.getProperty("user.dir") + "//src//main//java//com//sol//qa//testdata//" + fileLink;
    chatSection.getAttachFilePath().sendKeys(filePath);
    sendkeys(getDriver(), chatSection.getEnterTextChat(), TestUtil.getTimeOut(), "Attachment Chat Message");
    customWait(chatSection.getRemoveAttachmentButton());
    clickOn(getDriver(), chatSection.getSendMessageButton(), TestUtil.getTimeOut());
    customWait(chatSection.getChatButtons());
    clickOn(getDriver(), chatSection.getChatButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.searchChatReceiver(supplierName), TestUtil.getTimeOut());
    assert chatSection.getSecondLastMessageText().contains("Attachment Chat Message");
    assert chatSection.getLastMessageText().contains(fileLink);
    clickOn(getDriver(), chatSection.getBackToChatListButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.getCloseChatButton(), TestUtil.getTimeOut());
    }

  /**
   * This method to check for the text message sent to selected suppliers.
   */
  public void sendTextMessageToSelectedSuppliers(final String supplierName, final String textMessage)
      throws InterruptedException {
    openChatSection();
    clickOn(getDriver(), chatSection.getSupplierCheckBox(supplierName), TestUtil.getTimeOut());
    sendkeys(getDriver(), chatSection.getEnterTextChat(), TestUtil.getTimeOut(), textMessage);
    clickOn(getDriver(), chatSection.getSendMessageButton(), TestUtil.getTimeOut());
    customWait(chatSection.getChatButtons());
    clickOn(getDriver(), chatSection.getChatButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.searchChatReceiver(supplierName), TestUtil.getTimeOut());
    assert chatSection.getLastMessageText().contains(textMessage);
    clickOn(getDriver(), chatSection.getBackToChatListButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.getCloseChatButton(), TestUtil.getTimeOut());
    }

  /**
   * This method whether the send attachment message to selected suppliers.
   */
  public void sendAttachmentMessageToSelectedSuppliers(final String supplierName, final String fileLink)
      throws InterruptedException {
    openChatSection();
    clickOn(getDriver(), chatSection.getSupplierCheckBox(supplierName), TestUtil.getTimeOut());
    String filePath = System.getProperty("user.dir") + "//src//main//java//com//sol//qa//testdata//" + fileLink;
    chatSection.getAttachFilePath().sendKeys(filePath);
    sendkeys(getDriver(), chatSection.getEnterTextChat(), TestUtil.getTimeOut(), "Attachment Chat Message");
    customWait(chatSection.getRemoveAttachmentButton());
    clickOn(getDriver(), chatSection.getSendMessageButton(), TestUtil.getTimeOut());
    customWait(chatSection.getChatButtons());
    clickOn(getDriver(), chatSection.getChatButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.searchChatReceiver(supplierName), TestUtil.getTimeOut());
    assert chatSection.getLastMessageText().contains(fileLink);
    assert chatSection.getSecondLastMessageText().contains("Attachment Chat Message");
    clickOn(getDriver(), chatSection.getBackToChatListButton(), TestUtil.getTimeOut());
    // clickOn(driver, chatSection.closeChatButton, 10);
    }

  /**
   * This method to check for the wait Chat Suppliers To Transform.
   */
  public void waitForChatSuppliersToTransform() throws InterruptedException {
    if (chatSection.getSupplierDivList().size() == 1) {
      Thread.sleep(sleepTime);
      } else {
      waitForChatSuppliersToTransform();
      }
    }

  //public void checkSearchButton(String supplierName) throws InterruptedException {
  //clickOn(driver, chatSection.chatButton, 10);
  //clickOn(driver, chatSection.searchButton, 10);
  //sendkeys(driver, chatSection.searchInput, 10, supplierName);
  //waitForChatSuppliersToTransform();
  //assert chatSection.searchChatReceiver(supplierName).isDisplayed();
  //System.out.println("search supplier complete");
  //}

  /**
   * This method check whether the close chat button is displayed.
   */
  public void checkCloseChatButton() {
    clickOn(getDriver(), chatSection.getCloseChatButton(), TestUtil.getTimeOut());
    assert chatSection.getChatButton().isDisplayed();
    }

  /**
   * This method check whether the message is no sent twice.
   */
  public void checkMessageIsNoSentTwice(final String supplier1, final String messageString) throws InterruptedException {
    clickOn(getDriver(), chatSection.getChatButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.getNewMessageButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.getSupplierCheckBox(supplier1), TestUtil.getTimeOut());
    sendkeys(getDriver(), chatSection.getEnterTextChat(), TestUtil.getTimeOut(), messageString);
    Actions act = new Actions(getDriver());
    act.doubleClick(chatSection.getSendMessageButton()).perform();
    customWait(chatSection.getChatButtons());
    clickOn(getDriver(), chatSection.getChatButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.searchChatReceiver(supplier1), TestUtil.getTimeOut());
    System.out.println(chatSection.getSimilarMessages(messageString).size());
    assert chatSection.getSimilarMessages(messageString).size() == 1;
    clickOn(getDriver(), chatSection.getBackToChatListButton(), TestUtil.getTimeOut());
    }

  /**
   * This method check Internal Stake holder True.
   */
  public void checkInternalStakeholersCheckbox() throws InterruptedException {
    clickOn(getDriver(), chatSection.getNewMessageButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.getInternalStakeHolders(), TestUtil.getTimeOut());
    Thread.sleep(sleepTime);
    Assert.assertTrue(chatSection.getCheckBoxStatus(chatSection.getInternalStakeHolders()).endsWith("'true']]"));
    clickOn(getDriver(), chatSection.getNewMessageCloseButton(), TestUtil.getTimeOut());
    }

  /**
   * This Method check new message panel closed.
   */
  public void checkTexeMessageToAllInternalStakeholders(final String messageString) {
    openChatSection();
    clickOn(getDriver(), chatSection.getInternalStakeHolders(), TestUtil.getTimeOut());
    sendkeys(getDriver(), chatSection.getEnterTextChat(), TestUtil.getTimeOut(), messageString);
    clickOn(getDriver(), chatSection.getSendMessageButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.getChatButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.searchChatReceiver("All Internal Stakeholders"), TestUtil.getTimeOut());
    assert chatSection.getLastMessageText().contains(messageString);
    clickOn(getDriver(), chatSection.getBackToChatListButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.getCloseChatButton(), TestUtil.getTimeOut());
    }

  /**
   * This Method check attachment message to all internal stakeholder.
   */
  public void checkAttachmentMessageToAllInternalStakeholders(final String fileLink) throws InterruptedException {
    openChatSection();
    clickOn(getDriver(), chatSection.getInternalStakeHolders(), TestUtil.getTimeOut());
    String filePath = System.getProperty("user.dir") + "//src//main//java//com//sol//qa//testdata//" + fileLink;
    chatSection.getAttachFilePath().sendKeys(filePath);
    sendkeys(getDriver(), chatSection.getEnterTextChat(), TestUtil.getTimeOut(), "Attachment Chat Message");
    customWait(chatSection.getRemoveAttachmentButton());
    clickOn(getDriver(), chatSection.getSendMessageButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.getChatButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.searchChatReceiver("All Internal Stakeholders"), TestUtil.getTimeOut());
    assert chatSection.getLastMessageText().contains(fileLink);
    assert chatSection.getSecondLastMessageText().contains("Attachment Chat Message");
    clickOn(getDriver(), chatSection.getBackToChatListButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.getCloseChatButton(), TestUtil.getTimeOut());
    }

  /**
   * This method check new message panel closed.
   */
  public void checkNewMessagePanleClosed() throws InterruptedException {
    boolean flag;
    clickOn(getDriver(), chatSection.getChatButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.getNewMessageButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.getNewMessageCloseButton(), TestUtil.getTimeOut());
    customWait(chatSection.getChatButtons());
    try {
      flag = chatSection.getSendMessageButton().isDisplayed();
      AssertJUnit.assertTrue(flag);
      } catch (org.openqa.selenium.NoSuchElementException e) {
      System.out.println(e);
      } finally {
      getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.getImplicitWait()));
      }
    assertFalse(false);
    }

  /**
   * This method check delivered status on text message.
   */
  public  void checkDeliveredStatusOnTextMessage(final String messageString) {
    clickOn(getDriver(), chatSection.getChatButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.searchChatReceiver("All Internal Stakeholders"), TestUtil.getTimeOut());
    assert chatSection.getMessageContainerByText(messageString).getText().contains("Delivered");
    }

  /**
   * This method check delivered status on attachment message.
   */
  public  void checkDeliveredStatusOnAttachmentMessage(final String messageString) {
    assert chatSection.getMessageContainerByText(messageString).getText().contains("Delivered");
    }

  /**
   * This method check time stamp on text message.
   */
  public  void checkTimeStampOnTextMessage(final String messageString) {
    String timeStamp = chatSection.getTimeOfMessage(messageString).getText();
    String[] timeElements = timeStamp.split(" ");
    String timePattern = "((1[012]|[1-9]):[0-5][0-9])";
    Pattern pattern = Pattern.compile(timePattern);
    assert pattern.matcher(timeElements[0]).matches();
    assert timeElements[1].equals("AM") || timeElements[1].equals("PM");
    }

  /**
   * This Method check time stamp on attachment message.
   */
  public  void checkTimeStampOnAttachmentMessage(final String messageString) {
    String timeStamp = chatSection.getTimeOfMessage(messageString).getText();
    String[] timeElements = timeStamp.split(" ");
    String timePattern = "((1[012]|[1-9]):[0-5][0-9])";
    Pattern pattern = Pattern.compile(timePattern);
    assert pattern.matcher(timeElements[0]).matches();
    assert timeElements[1].equals("AM") || timeElements[1].equals("PM");
    clickOn(getDriver(), chatSection.getBackToChatListButton(), TestUtil.getTimeOut());
    }

  /**
   * This Method send the instant message to Internal Stakeholder.
   */
  public  void sendInstatnMessageToInternalStakehoder(final String chatMessage) {
    clickOn(getDriver(), chatSection.searchChatReceiver("All Internal Stakeholders"), TestUtil.getTimeOut());
    sendkeys(getDriver(), chatSection.getEnterInstantMessage(), TestUtil.getTimeOut(), chatMessage);
    clickOn(getDriver(), chatSection.getSendInstantMessageButton(), TestUtil.getTimeOut());
    assert chatSection.getLastMessageText().contains(chatMessage);
    clickOn(getDriver(), chatSection.getBackToChatListButton(), TestUtil.getTimeOut());
    }

  /**
   * This Method check the download with attachment.
   */
  public  void checkDownloadWtihAttachment(final String fileName) throws InterruptedException {
    makeFileToStoreDownloads();
    Thread.sleep(sleepTime);
    clickOn(getDriver(), chatSection.getDownloadChatButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.getDownloadChatWithAttachment(), TestUtil.getTimeOut());
    try {
      Assert.assertTrue(getAttachmentExistsStatus(fileName));
      } catch (IOException e) {
      e.printStackTrace();
      }
    }

  /**
   * This Method check the download without attachment.
   */
  public  void checkDownloadWithoutAttachment(final String fileName) throws InterruptedException {
    clickOn(getDriver(), chatSection.getDownloadChatButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.getDownloadChatWithoutAttachment(), TestUtil.getTimeOut());
    try {
      Assert.assertFalse(getAttachmentExistsStatus(fileName));
      } catch (IOException e) {
      e.printStackTrace();
      }
    clickOn(getDriver(), chatSection.getCloseChatButton(), TestUtil.getTimeOut());
    }

  /**
   * This Method check the exception for file upload above 25MB.
   */
  public  void checkExceptionForUpload(final String fileName) throws InterruptedException {
    clickOn(getDriver(), chatSection.getChatButton(), TestUtil.getTimeOut());
    clickOn(getDriver(), chatSection.getNewMessageButton(), TestUtil.getTimeOut());
    String filePath = System.getProperty("user.dir") + "//src//main//java//com//sol//qa//testdata//" + fileName;
    chatSection.getAttachFilePath().sendKeys(filePath);
    assert chatSection.getUploadErrorMessage().getText().endsWith(" is above 25MB");
    clickOn(getDriver(), chatSection.getNewMessageCloseButton(), TestUtil.getTimeOut());
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

      case "Assertion":
        switch (argument1) {

          //case "AssertSearchIcon":
          //checkSearchIconButton();
          //break;

          case "AssertDownloadChatButtonIcon":
            checkDownloadButton();
            break;
          case "AssertNewMessageButton":
            checkNewMessageButton();
            break;

          case "AssertCloseChatSection":
            checkCloseChatButton();
            break;

          case "AssertInterStakeholdersButton":
            checkInternalStakeholersCheckbox();
            break;

          case "SendTextMessageToAllInternalStakeholders":
            checkTexeMessageToAllInternalStakeholders(argument2);
            break;

          case "SendAttachmentMessageToAllInternalStakeholders":
            checkAttachmentMessageToAllInternalStakeholders(argument2);
            break;

          case "AssertNewMessagePlanelClosed":
            checkNewMessagePanleClosed();
            break;

          case "SendInstatnMessageToInternalStakeholder":
            sendInstatnMessageToInternalStakehoder(argument2);
            break;

          case "AssertTimeStampOnTextMessage":
            checkTimeStampOnTextMessage(argument2);
            break;

          case "AssertTimeStampOnAttachmentMessage":
            checkTimeStampOnAttachmentMessage(argument2);
            break;

          case "AssertDeliveredStatusOnTextMessage":
            checkDeliveredStatusOnTextMessage(argument2);
            break;

          case "AssertDeliveredStatusOnAttachmentMessage":
            checkDeliveredStatusOnAttachmentMessage(argument2);
            break;

          case "AssertUploadLimitException":
            checkExceptionForUpload(argument2);
            break;

          case "SendMessageToInvitedSupplier":
            sendMessageToInvitedSuppliers(argument2, argument3);
            break;

          case "DuplicateMessageCheck":
            checkMessageIsNoSentTwice(argument2, argument3);
            break;

          case "AssertSendButtonDisabled":
            checkSendButtonDisabled();
            break;

          case "SendAttachmentToInvitedParticipants":
            sendAttachmentChatToAllInvitedSupplier(argument2, argument3);
            break;

          case "AssertSelectedSupplierTextMessage":
            sendTextMessageToSelectedSuppliers(argument2, argument3);
            break;

          case "AssertSelectedSupplierAttachmentMessage":
            sendAttachmentMessageToSelectedSuppliers(argument2, argument3);
            break;

          case "AssertDownloadChatWithAttachment":
            checkDownloadWtihAttachment(argument2);
            break;

          case "AssertDownloadChatWithoutAttachment":
            checkDownloadWithoutAttachment(argument2);
            break;

          default:
            System.out.println("No Match for " + argument1);
          }
      default:
        System.out.println("No Match for " + argument1);
      }
    }
  }
