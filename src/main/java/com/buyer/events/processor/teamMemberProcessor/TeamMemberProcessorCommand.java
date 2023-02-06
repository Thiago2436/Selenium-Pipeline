package com.buyer.events.processor.teamMemberProcessor;

import com.buyer.events.processor.ITabCommandProcessor;
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.TestUtil;
import com.sol.qa.util.XlsReader;
import java.awt.AWTException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;


public class TeamMemberProcessorCommand  implements ITabCommandProcessor {
  private TeamMemberCreationPage teamMemberPage;
  private WindowManager windowManager;
  final int sleepTime = 4000;

  public TeamMemberProcessorCommand(final WindowManager windowManger) {
    this.windowManager = windowManger;
    teamMemberPage = new TeamMemberCreationPage(this.driver());
    }

  /**
   * This method returns the current active driver.
   */
  public RemoteWebDriver driver() {
    return  this.windowManager.getActiveDriver();
    }


  /**
   * This method add new team member dropdown.
   */
  public void addMember() throws InterruptedException {
    Thread.sleep(sleepTime);
    clickOn(this.driver(), teamMemberPage.getAddMemberButton(), TestUtil.getTimeOut());
    Thread.sleep(sleepTime);
    clickOn(this.driver(), teamMemberPage.getAddMemberDropDown(), TestUtil.getTimeOut());
    }

  /**
   * This method add new team member dropdown.
   */
  public void addTeamMemberUser(final String arg1) throws InterruptedException {
    Thread.sleep(sleepTime);
    System.out.println("Team Member User>>>>");
    teamMemberPage.getAddMemberSearchUser().sendKeys(arg1);
    System.out.println("size>>>" + teamMemberPage.getTeamMemberPanel().size());
    for (int i = 0; i < teamMemberPage.getTeamMemberPanel().size(); i++) {
      List<WebElement> checkBoxElement = teamMemberPage.getTeamMemberPanel().get(i)
          .findElements(By.tagName("mat-pseudo-checkbox"));
      checkBoxElement.get(i).click();
      }
    Actions action = new Actions(this.driver());
    action.sendKeys(Keys.ESCAPE).build().perform();
    }

  /**
   * This method clicks cancel button.
   */
  public void performCancel() {
    WebDriverWait wait = new WebDriverWait(this.driver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    wait.until(ExpectedConditions.elementToBeClickable(teamMemberPage.getCancelButton()));
    clickOn(this.driver(), teamMemberPage.getCancelButton(), TestUtil.getTimeOut());
    }

  /**
   * This method clicks proceed button if it is enabled.
   */
  public void setProceed() {
    boolean proceed = teamMemberPage.getProceedButton().isEnabled();
    AssertJUnit.assertTrue(proceed);
    WebDriverWait wait = new WebDriverWait(this.driver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    wait.until(ExpectedConditions.elementToBeClickable(teamMemberPage.getProceedButton()));
    clickOn(this.driver(), teamMemberPage.getProceedButton(), TestUtil.getTimeOut());
    }

  /**
   * This method clicks on the confirm button.
   */
  public void performConfirm() {
    teamMemberPage = new TeamMemberCreationPage(this.driver());
    WebDriverWait wait = new WebDriverWait(this.driver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    wait.until(ExpectedConditions.elementToBeClickable(teamMemberPage.getConfirmMemberButton()));
    clickOn(this.driver(), teamMemberPage.getConfirmMemberButton(), TestUtil.getTimeOut());
    }

  /**
   * This method clicks pqc approver button.
   */
  public void performPQCApproverButton() throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(this.driver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    wait.until(ExpectedConditions.elementToBeClickable(teamMemberPage.getAddPqcApprover()));
    clickOn(this.driver(), teamMemberPage.getAddPqcApprover(), TestUtil.getTimeOut());
    Thread.sleep(sleepTime);
    clickOn(this.driver(), teamMemberPage.getAddMemberDropDown(), TestUtil.getTimeOut());
    }

  /**
   * This method clicks on the Approver button.
   */
  public void performApproversLink() {
    System.out.println("Approvers Tab");
    WebDriverWait wait = new WebDriverWait(this.driver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    wait.until(ExpectedConditions.elementToBeClickable(teamMemberPage.getApproversTab()));
    clickOn(this.driver(), teamMemberPage.getApproversTab(), TestUtil.getTimeOut());
    }

  /**
   *This method have XLs_Reader , command , argument1 , argument2 , argument3 and argument4 as Parameter
   *It have switch command and its corresponding method.
   */
  public void processCommand(final XlsReader reader, final String command, final String argument1, final String argument2, final String argument3,
      final String argument4) throws AWTException, InterruptedException {
    teamMemberPage = new TeamMemberCreationPage(this.driver());
    switch (command) {
      case "AddMemberButton":
        addMember();
        break;
      case "AddTeamMember":
        addTeamMemberUser(argument1);
        break;
      case "confirmButton":
        performConfirm();
        break;
      case "proceedButton":
        setProceed();
        break;
      case "ApproversTabLink":
        performApproversLink();
        break;
      case "addPQCApproverButton":
        performPQCApproverButton();
        break;
      default:
        System.out.println("no match");
      }

    }

  public static final void sendkeys(final RemoteWebDriver driver, final WebElement element, final int timeout, final String value) {
    new WebDriverWait(driver, Duration.ofSeconds(timeout))
      .until(ExpectedConditions.visibilityOf(element));
    element.sendKeys(value);
    }

  public static final void clickOn(final RemoteWebDriver driver, final WebElement element, final int timeout) {
    new WebDriverWait(driver, Duration.ofSeconds(timeout))
      .until(ExpectedConditions.elementToBeClickable(element));
    element.click();
    }

  }
