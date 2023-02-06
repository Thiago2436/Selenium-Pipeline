package com.buyer.events.processor.eventRulesProcessor;

import com.buyer.events.processor.pricingSheetProcessor.PricingSheetCreationPage;
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.TestUtil;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;

public class EventRulesAsserterCommand  {
  private EventRulesCreationPage eventRulesPage;
  private PricingSheetCreationPage pricingSheetPage;
  final int sleepTime = 500;
  private WindowManager windowManager;

  public EventRulesAsserterCommand(final WindowManager windowManagers) {
    this.windowManager = windowManagers;
    eventRulesPage = new EventRulesCreationPage(this.driver());
    pricingSheetPage = new PricingSheetCreationPage(this.driver());
    //js = (JavascriptExecutor) drivers;
    PageFactory.initElements(this.driver(), this);
    }

  public final WebDriver driver() {
    return this.windowManager.getActiveDriver();
    }

  /**
   * This method assert the proceed button enabled or disabled.
   */
  public final void eventsButtonAssertion(final String arg1) throws InterruptedException {

    eventRulesPage = new EventRulesCreationPage(this.driver());
    System.out.println("Assert Driver2>>>>" + this.driver());
    if (arg1.equalsIgnoreCase("ProceedButtonDisabled")) {
      boolean proceed = eventRulesPage.getProceedButton().isEnabled();
      AssertJUnit.assertFalse(proceed);

      }
    if (arg1.equalsIgnoreCase("PublishButtonEnabled")) {
      boolean publish = eventRulesPage.getPublishEvent().isEnabled();
      AssertJUnit.assertTrue(publish);

      }

    if (arg1.equalsIgnoreCase("AuctionStartTime")) {
      Thread.sleep(sleepTime);
      if (eventRulesPage.getAuctionStartMsg().getText().equals("AUCTION STARTS IN")) {
        AssertJUnit.assertEquals("AUCTION STARTS IN", eventRulesPage.getAuctionStartMsg().getText());
        }

      }

    }

  /**
   * This method assert the error message for supplier or service .
   */
  public void columnMessage(final String errorMsg) throws InterruptedException {
    Thread.sleep(sleepTime);
    if (errorMsg == "SUP_PER") {
      AssertJUnit.assertEquals("Can not delete \"SUP_PER\". It is part of a formula for \"Extended Price\".",
          pricingSheetPage.getToastMessage().getText());
      }
    if (errorMsg == "Service") {
      AssertJUnit.assertEquals("Can not delete \"Service Price\". It is part of a formula for \"Unit Price\".",
          pricingSheetPage.getToastMessage().getText());
      }

    pricingSheetPage.getToastCloseButton().click();

    }

  /**
   * This method assert the competitive feedback field.
   */
  public void competitiveFeedBack() throws InterruptedException {
    List<WebElement> compSupplier = eventRulesPage.competitiveDisplay();
    driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.getImplicitWait()));
    if (compSupplier.size() < 0) {
      AssertJUnit.assertTrue(true);

      }

    }

  /**
   * This method assert the Content Copy is disabled or not.
   */
  public void assertContentCopy() throws InterruptedException {
    boolean assertContentCopy = eventRulesPage.getEventsToBeCopied().getAttribute("class").contains("disable");
    AssertJUnit.assertFalse(assertContentCopy);
    }

  /**
   * This method assert the Toast Error message display.
   */
  public void assertToastMsg(final String errorMsg) throws InterruptedException {
    Thread.sleep(sleepTime);
    pricingSheetPage = new PricingSheetCreationPage(this.driver());
    if (errorMsg.contains(pricingSheetPage.getToastMessage().getText())) {
      AssertJUnit.assertTrue(true);
      } else {
      AssertJUnit.assertTrue(false);
      }
    System.out.println("Toast Button");
    pricingSheetPage.getToastCloseButton().click();

    }

  /**
   * This method assert the given value with copy previous content info tool tip Field.
   */
  public void copyPreviousContentInfo(final String copyPreviousMsg) throws InterruptedException {
    Actions action = new Actions(driver());
    action.moveToElement(eventRulesPage.getCopyFromPreviousEvent()).perform();
    Thread.sleep(sleepTime);
    AssertJUnit.assertEquals(copyPreviousMsg, eventRulesPage.getCopyFromPreviousEvent().getAttribute("mattooltip"));
    }

  /**
   * This method assert the given value with copy content info tool tip Field.
   */
  public void copyContentInfo(final String copyContent) throws InterruptedException {
    Actions act = new Actions(driver());
    act.moveToElement(eventRulesPage.getCopyContent()).perform();
    Thread.sleep(sleepTime);
    AssertJUnit.assertEquals(copyContent, eventRulesPage.getCopyContent().getAttribute("mattooltip"));
    }

  }
