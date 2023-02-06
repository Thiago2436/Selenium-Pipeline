package com.buyer.events.processor.chatProcessor;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sol.qa.base.TestBase;

public class ChatSectionPage extends TestBase {

  public ChatSectionPage() {
    PageFactory.initElements(getDriver(), this);
    }

  @FindBy(id = "chat")
  private WebElement chatButton;

  @FindBy(id = "closeChat")
  private WebElement closeChatButton;

  @FindBy(id = "newMessage")
  private WebElement newMessageButton;

  @FindBy(id = "searchChat")
  private WebElement searchButton;

  @FindBy(id = "filterChat")
  private WebElement searchInput;

  @FindBy(id = "downloadChat")
  private WebElement downloadChatButton;

  @FindBy(id = "downloadChatWithAttachment")
  private WebElement downloadChatWithAttachment;

  @FindBy(id = "downloadChatWithoutAttachment")
  private WebElement downloadChatWithoutAttachment;

  @FindBy(xpath = "//*[@for='stakeholders-input']")
  private WebElement internalStakeHolders;

  @FindBy(xpath = "//*[contains(text(),'close')]")
  private List<WebElement> removeAttachmentButton;

  @FindBy(xpath = "//*[contains(text(),'Uploading')]")
  private List<WebElement> uploadingMessage;

  @FindBy(xpath = "//*[@for='invited-input']")
  private WebElement allInvitedSupplier;

  @FindBy(xpath = "//*[@for='participating-input']")
  private WebElement allParticipatingSupplier;

  @FindBy(xpath = "//*[@for='declined-input']")
  private WebElement allDeclinedSupplier;

  @FindBy(id = "chatMessage")
  private WebElement enterTextChat;

  @FindBy(xpath = "/html/body/div[3]/div[2]/div/mat-dialog-container/app-new-chat-message-component/div/div[2]/div[4]/input")
  private WebElement attachFilePath;

  @FindBy(id = "sendMessage")
  private WebElement sendMessageButton;

  @FindBy(id = "chatText")
  private WebElement enterInstantMessage;

  @FindBy(id = "sendText")
  private WebElement sendInstantMessageButton;

  @FindBy(id = "close-message-icon")
  private WebElement newMessageCloseButton;

  @FindBy(id = "backToChatList")
  private WebElement backToChatListButton;

  @FindBy(className = "chat-body")
  private List<WebElement>  supplierDivList;

  @FindBy(xpath = "//*[@data-test = 'message-box-container']")
  private List<WebElement>  messageContainers;

  @FindBy(className = "attachement")
  private WebElement  uploadErrorMessage;

  @FindBy(id = "monitorDiv")
  private List<WebElement>  postPublishScreen;

  @FindBy(xpath = "//mat-icon[contains(text(),'close')]")
  private List<WebElement>  uploadRemoveButton;

  @FindBy(className = "partipating-status")
  private List<WebElement>  suppliersStatus;

  @FindBy(id = "chat")
  private List<WebElement> chatButtons;

  public final WebElement getChatButton() {
    return chatButton;
    }

  public final WebElement getCloseChatButton() {
    return closeChatButton;
    }

  public final WebElement getNewMessageButton() {
    return newMessageButton;
    }

  public final WebElement getSearchButton() {
    return searchButton;
    }

  public final WebElement getSearchInput() {
    return searchInput;
    }

  public final WebElement getDownloadChatButton() {
    return downloadChatButton;
    }

  public final WebElement getDownloadChatWithAttachment() {
    return downloadChatWithAttachment;
    }

  public final WebElement getDownloadChatWithoutAttachment() {
    return downloadChatWithoutAttachment;
    }

  public final WebElement getInternalStakeHolders() {
    return internalStakeHolders;
    }

  public final List<WebElement> getRemoveAttachmentButton() {
    return removeAttachmentButton;
    }

  public final List<WebElement> getUploadingMessage() {
    return uploadingMessage;
    }

  public final WebElement getAllInvitedSupplier() {
    return allInvitedSupplier;
    }

  public final WebElement getAllParticipatingSupplier() {
    return allParticipatingSupplier;
    }

  public final WebElement getAllDeclinedSupplier() {
    return allDeclinedSupplier;
    }

  public final WebElement getEnterTextChat() {
    return enterTextChat;
    }

  public final WebElement getAttachFilePath() {
    return attachFilePath;
    }

  public final WebElement getSendMessageButton() {
    return sendMessageButton;
    }

  public final WebElement getEnterInstantMessage() {
    return enterInstantMessage;
    }

  public final WebElement getSendInstantMessageButton() {
    return sendInstantMessageButton;
    }

  public final WebElement getNewMessageCloseButton() {
    return newMessageCloseButton;
    }

  public final WebElement getBackToChatListButton() {
    return backToChatListButton;
    }

  public  final List<WebElement> getSupplierDivList() {
    return supplierDivList;
    }

  public final List<WebElement> getMessageContainers() {
    return messageContainers;
    }

  public final WebElement getUploadErrorMessage() {
    return uploadErrorMessage;
    }

  public final List<WebElement> getPostPublishScreen() {
    return postPublishScreen;
    }

  public final List<WebElement> getUploadRemoveButton() {
    return uploadRemoveButton;
    }

  public final List<WebElement> getSuppliersStatus() {
    return suppliersStatus;
    }

  public final List<WebElement> getChatButtons() {
    return chatButtons;
    }

  public final WebElement searchChatReceiver(final String supplierName) {
    WebElement searchedSupplier = getDriver().findElement(By.xpath("//*[contains(text(), '" + supplierName + "')]"));
    return searchedSupplier;
    }

  public final String getCheckBoxStatus(final WebElement checkBoxName) {
    WebElement checkBox = getDriver().findElement(By.id("stakeholders-input"));
    String status = checkBox.findElement(By.xpath("//*[@aria-checked = 'true']")).toString();
    return status;
    }

  public final WebElement getSupplierCheckBox(final String supplierName) {
    WebElement supplierCheckBox = null;
    List<WebElement> suppliersInNewMessage = getDriver()
        .findElements(By.xpath("//*[@class = 'pt-16 ng-star-inserted']"));
    for (WebElement supplierDiv : suppliersInNewMessage) {
      if (supplierDiv.findElement(By.className("supplier")).getText().equals(supplierName)) {
        supplierCheckBox = supplierDiv.findElement(By.className("mat-checkbox-layout"));
        }
      }
    return supplierCheckBox;
    }

  public final List<WebElement> getElementList(final String elementId) {
    List<WebElement> element = getDriver().findElements(By.id(elementId));
    return element;
    }

  public final WebElement getRequiredMessageDiv(final String messageString) {
    WebElement messageDiv = null;
    for (WebElement messageContent : messageContainers) {
      if (messageContent.getText().contains(messageString)) {
        messageDiv = messageContent;
        }
      }
    return messageDiv;
    }

  public final WebElement getMessageContainerByText(final String messageString) {
    WebElement messageContent = getRequiredMessageDiv(messageString);
    return messageContent;
    }

  public final WebElement getAttachmentName(final String messageString) {
    WebElement attachmentName = getRequiredMessageDiv(messageString).findElement(By.tagName("a"));
    return attachmentName;
    }

  public final String getLastMessageText() {
    return messageContainers.get(messageContainers.size() - 1).getText();
    }

  public final String getSecondLastMessageText() {
    List<WebElement> messages = getDriver().findElement(By.tagName("app-chat-content")).findElements(By.className("message-body"));
    return messages.get(messages.size() - 2).getText();
    }


  public final WebElement getTimeOfMessage(final String messageString) {
    WebElement messageTime = getRequiredMessageDiv(messageString).findElement(By.className("message-date"));
    return messageTime;
    }

  public final WebElement getStatusOfMessage(final String messageString) {
    WebElement messageStatus = getRequiredMessageDiv(messageString)
        .findElement(By.xpath("//*[@data-test = 'message-status']"));
    return messageStatus;
    }

  public final List<WebElement> getSimilarMessages(final String messageString) {
    List<WebElement> messages = getDriver().findElements(By.xpath("//*[contains(text(),'" + messageString + "')]"));
    return messages;
    }
  }
