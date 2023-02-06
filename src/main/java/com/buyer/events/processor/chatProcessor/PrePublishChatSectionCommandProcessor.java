package com.buyer.events.processor.chatProcessor;

import java.awt.AWTException;

import org.openqa.selenium.ElementClickInterceptedException;

import com.buyer.events.processor.ITabCommandProcessor;
import com.sol.qa.util.XlsReader;

public class PrePublishChatSectionCommandProcessor implements ITabCommandProcessor  {
  final int sleepTime = 2000;
  private ChatSectionPage chatSection = new ChatSectionPage();

  /**
   * This method clicks on open chat section.
   */
  public void openChatSection() {
    chatSection.getChatButton().click();
    chatSection.getNewMessageButton().click();
    }

  /**
   * This method clicks on close chat section.
   */
  public void closeChatSection() {
    chatSection.getSendMessageButton().click();
    chatSection.getNewMessageCloseButton().click();
    }

  /**
   * This method send chat to internal stakeholder.
   */
  public void sendChatToInternalStakeholder(final String chatMessage)
      throws InterruptedException, AWTException, ElementClickInterceptedException {
    openChatSection();
    chatSection.getInternalStakeHolders().click();
    chatSection.getEnterTextChat().sendKeys(chatMessage);
    closeChatSection();
    }

  /**
   * This method send the Attachment Chat to Internal Stakeholders.
   */
  public void sendAttachmentChatToInternalStakholders(final String fileLink) throws InterruptedException {
    openChatSection();
    chatSection.getInternalStakeHolders().click();
    String filePath = System.getProperty("user.dir") + "//src//main//java//com//sol//qa//testdata//" + fileLink;
    chatSection.getAttachFilePath().sendKeys(filePath);
    chatSection.getEnterTextChat().sendKeys("Attachment chat");
    Thread.sleep(sleepTime);
    chatSection.getSendMessageButton().click();
    chatSection.getNewMessageCloseButton().click();
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

      case "SendTextToInternalStakeholder":
        sendChatToInternalStakeholder(argument1);
        break;

      case "SendAttachmentToInternalStakeholder":
        sendAttachmentChatToInternalStakholders(argument1);
      default:
        System.out.println("no match");

      }
    }
  }
