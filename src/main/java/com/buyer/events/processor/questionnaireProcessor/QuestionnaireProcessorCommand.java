package com.buyer.events.processor.questionnaireProcessor;

import java.awt.AWTException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import com.buyer.events.processor.ITabCommandProcessor;
import com.sol.qa.base.TestBase;
import com.sol.qa.util.TestUtil;
import com.sol.qa.util.XlsReader;

public class QuestionnaireProcessorCommand extends TestBase implements ITabCommandProcessor {

  private QuestionnaireCreationPage questionnairePage = new QuestionnaireCreationPage();
  final int sleepTime = 2000;

  /**
   *This method have XLs_Reader , command , argument1 , argument2 , argument3 and argument4 as Parameter
   *It have switch command and its corresponding method.
   */
  public void processCommand(final XlsReader reader, final  String command, final String argument1, final String argument2,
      final String argument3, final String argument4) throws AWTException, InterruptedException {
    switch (command) {
      case "AddSectionToDrop":
        sectionOrSubSectionFunc(argument1, argument2);
        break;
      case "AddFieldToDrop":
        addSectionFunc(argument1, argument2);
        break;
      case "AddFieldToDrop1":
        addSectionFunc1(argument1, argument2);
        break;
      case "SectionName":
        sectionNameFunc(argument1);
        break;
      case "DoesNotRequireScoring":
        requireScoreFunc(argument1);
        break;
      case "SaveButton":
        saveButtonFunc();
        break;
      case "SubSectionName":
        subSectionNameFunc(argument1);
        break;
      case "QuestionName":
        singleLineName(argument1);
        break;
      case "SingleChoiceOption":
        singleChoiceOptionFunc(argument1);
        break;
      case "proceedButton":
        proceedButtonFunc();
        break;
      case "SectionWeightPercentage":
        weightInPercentage(argument1);
        break;
      case "QuestionWeightPercentage":
        questionWeightFunc(argument1);
        break;
      case "AssignEvaluator":
        assignEvaluator(argument1);
        break;

      default:
        System.out.println("no match");

      }

    }

  /**
   * This method add and the drop section column in the place specified.
   */
  public  void sectionOrSubSectionFunc(final String addSection, final String dropSection) throws InterruptedException {
    Thread.sleep(sleepTime);
    WebElement selectedSectionElement = questionnairePage.sectionWebElement(addSection);
    Actions act = new Actions(getDriver());
    act.clickAndHold(selectedSectionElement).build().perform();
    WebElement selectedDropElement = questionnairePage.dropSectionWebElement(dropSection);
    act.moveToElement(selectedDropElement).build().perform();
    act.release(selectedDropElement).build().perform();
    }

  /**
   * This method add and the drop section column in the place specified.
   */
  public void addSectionFunc1(final String addSection, final String dropSection) throws InterruptedException {
    Thread.sleep(sleepTime);
    WebElement selectedSectionElement = questionnairePage.sectionWebElement(addSection);
    Actions act = new Actions(getDriver());
    act.clickAndHold(selectedSectionElement).build().perform();
    WebElement selectedDropElement = questionnairePage.dropEmptyWebElement1(dropSection);
    act.moveToElement(selectedDropElement).build().perform();
    act.release(selectedDropElement).build().perform();
    }

  /**
   * This method add and the drop section column in the place specified.
   */
  public void addSectionFunc(final String addSection, final String dropSection) throws InterruptedException {
    final int timeSleep = 2000;
    Thread.sleep(timeSleep);
    WebElement selectedSectionElement = questionnairePage.sectionWebElement(addSection);
    Actions act = new Actions(getDriver());
    act.clickAndHold(selectedSectionElement).build().perform();
    WebElement selectedDropElement = questionnairePage.dropEmptyWebElement(dropSection);
    act.moveToElement(selectedDropElement).build().perform();
    act.release(selectedDropElement).build().perform();
    }

  /**
   * This method enters the question name in the section name.
   */
  public void sectionNameFunc(final String sectionName) {
    sendkeys(getDriver(), questionnairePage.getAddSectionName(), TestUtil.getTimeOut(), sectionName);
    }

  /**
   * This method enters the question name in the sub section name.
   */
  public void subSectionNameFunc(final String subSection) {
    sendkeys(getDriver(), questionnairePage.getSubSectionName(), TestUtil.getTimeOut(), subSection);
    }

  /**
   * This method assign the section weight in percentage.
   */
  public void weightInPercentage(final String percentage) {
    questionnairePage.getSectionWeight().clear();
    sendkeys(getDriver(), questionnairePage.getSectionWeight(), TestUtil.getTimeOut(), percentage);
    }

  /**
   * This method assign percentage received in the parameter as weight for the question.
   */
  public void questionWeightFunc(final String percentage) throws InterruptedException {
    System.out.println("Percentage>>>> " + percentage);
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    wait.until(ExpectedConditions.elementToBeClickable(questionnairePage.getQuestionWeight()));
    System.out.println("count>>>" + questionnairePage.getQuestionWeight().getText());
    questionnairePage.getQuestionWeight().sendKeys(Keys.BACK_SPACE);
    questionnairePage.getQuestionWeight().sendKeys(Keys.BACK_SPACE);
    questionnairePage.getQuestionWeight().sendKeys(Keys.BACK_SPACE);
    questionnairePage.getQuestionWeight().sendKeys(Keys.BACK_SPACE);
    // questionnairePage.questionWeight.clear();
    questionnairePage.getQuestionWeight().sendKeys(percentage);
    clickOn(getDriver(), questionnairePage.getQuestionWeight(), TestUtil.getTimeOut());
    }

  /**
   * This method selects assign evaluator given from the parameter.
   */
  public void assignEvaluator(final String evaluatorEmail) throws InterruptedException {
    System.out.println(evaluatorEmail);
    Thread.sleep(sleepTime);
    clickOn(getDriver(), questionnairePage.getAssignEvaluatorDropdown(), TestUtil.getTimeOut());
    questionnairePage.getSearchEvaluator().sendKeys(evaluatorEmail);
    for (int i = 0; i < questionnairePage.getEvaluatorPanel().size(); i++) {
      List<WebElement> checkBoxElement = questionnairePage.getEvaluatorPanel().get(i)
          .findElements(By.tagName("mat-pseudo-checkbox"));
      checkBoxElement.get(i).click();
      }
    Actions act = new Actions(getDriver());
    act.moveToElement(questionnairePage.getFormName()).click().perform();
    }

  /**
   * This method enter the question for single line name.
   */
  public void singleLineName(final String singleName) {
    sendkeys(getDriver(), questionnairePage.getQuestionName(), TestUtil.getTimeOut(), singleName);

    }

  /**
   * This method clicks on Single Choice Option as received from the parameter.
   */
  public void singleChoiceOptionFunc(final String singleOption) {
    System.out.println("Input>>" + singleOption);
    String[] arrOfStr = singleOption.split("&");

    for (String a : arrOfStr) {
      System.out.println("split>>>" + a);
      questionnairePage.getSingleChoiceOption().sendKeys(a);
      questionnairePage.getSingleChoiceOption().sendKeys(Keys.ENTER);
      }
    }

  /**
   * This method clicks on Does Not Require Scoring check box.
   */
  public  void requireScoreFunc(final String checkedOrNot) {
    if (checkedOrNot.equalsIgnoreCase("Yes")) {
      clickOn(getDriver(), questionnairePage.getDoesNotRequireScoring(), TestUtil.getTimeOut());
      }

    }

  /**
   * This method checks whether save button is enabled and clicks on it.
   */
  public void saveButtonFunc() {
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    wait.until(ExpectedConditions.elementToBeClickable(questionnairePage.getSaveButton()));
    clickOn(getDriver(), questionnairePage.getSaveButton(), TestUtil.getTimeOut());
    }


  /**
   * This method checks whether proceed button is enabled and clicks on it.
   */
  public void proceedButtonFunc() {

    boolean proceed = questionnairePage.getProceedButton().isEnabled();
    AssertJUnit.assertTrue(proceed);
    clickOn(getDriver(), questionnairePage.getProceedButton(), TestUtil.getTimeOut());
    }

  }
