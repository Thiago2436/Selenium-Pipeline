package com.buyer.events.processor.pqcProcessor;

import java.awt.AWTException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

import com.buyer.events.processor.ITabCommandProcessor;
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.TestUtil;
import com.sol.qa.util.XlsReader;

public class PqcProcessorCommand implements ITabCommandProcessor {
  final int sleepTime = 2000;
  private WindowManager windowManager;
  private PqcCreationPage pqcPage;

  public PqcProcessorCommand(final WindowManager windowManger) {
    this.windowManager = windowManger;
    pqcPage = new PqcCreationPage(this.driver());

    }

  private RemoteWebDriver driver() {
    return  this.windowManager.getActiveDriver();
    }


  /**
   *This method have XLs_Reader , command , argument1 , argument2 , argument3 and argument4 as Parameter
   *It have switch command and its corresponding method.
   */
  public void processCommand(final XlsReader reader, final String command, final String argument1, final String argument2, final String argument3,
      final String argument4) throws AWTException, InterruptedException {
    // TODO Auto-generated method stub
    switch (command) {
      case "AddFieldToSection":
        addSubSection(argument1);
        break;
      case "SubSectionName":
        setSubSectionName(argument1);
        break;
      case "SaveButton":
        performSaveDetials();
        break;
      case "AddFieldToDrop":
        setAddFieldType(argument1, argument2);
        break;
      case "AddTestInitial":
        setAddTestInitial(argument1);
        break;
      case "IsDepedentQuestion":
        setDependentQuestion(argument1, argument2, argument3);
        break;

      case "DragAndDrop":
        performDragAndDrop(argument1, argument2);
        break;

      case "QuestionName":
        setSingleLineName(argument1);
        break;
      case "AddQuestionToField":
        setAddFieldType(argument1, argument2);
        break;
      case "ChoiceOption":
        setChoiceOption(argument1);
        break;
      case "ApprovedSingleChoice":
        approvedSingleChoice(argument1);
        break;
      case "proceedButton":
        setProceed();
        break;
      default:
        System.out.println("no match");
      }

    }

  /**
   * This method enters the data for the Single Line Question.
   */
  public void setSingleLineName(final String singleName) {
    pqcPage.getQuestionName().sendKeys(singleName);
    }

  /**
   * This method enters the data for the SubSection Field.
   */
  public void setSubSectionName(final String sectionName) {
    pqcPage.getSubSectionElement().sendKeys(sectionName);
    }


  /**
   * This method clicks on save button.
   */
  public void performSaveDetials() {
    WebDriverWait wait = new WebDriverWait(this.driver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    wait.until(ExpectedConditions.elementToBeClickable(pqcPage.getSaveButton()));
    pqcPage.getSaveButton().click();
    }

  /**
   * This method will add the SubSection to the page.
   */
  public void addSubSection(final String addSection) throws InterruptedException {
    Thread.sleep(sleepTime);
    WebElement selectedSectionElement = pqcPage.sectionWebElement(addSection);
    Actions act = new Actions(this.driver());
    act.clickAndHold(selectedSectionElement).build().perform();
    WebElement selectedDropElement = pqcPage.dropEmptyWebElement();
    act.moveToElement(selectedDropElement).build().perform();
    act.release(selectedDropElement).build().perform();
    }

  /**
   * This method receives whether the question is dependent or not and enters the value for the dependent question.
   */
  public void setDependentQuestion(final String dependentOrNot, final String questionDepends, final String displayWhen)
      throws InterruptedException {

    if (dependentOrNot.contentEquals("Yes")) {
      clickOn(this.driver(), pqcPage.getDependentQuestion(), TestUtil.getTimeOut());
      clickOn(this.driver(), pqcPage.getDependentQuestionType(), TestUtil.getTimeOut());
      WebElement selectedQuestionOptions = pqcPage.selectOption(questionDepends);
      clickOn(this.driver(), selectedQuestionOptions, TestUtil.getTimeOut());
      clickOn(this.driver(), pqcPage.getDependentAnswerType(), TestUtil.getTimeOut());
      WebElement selectedAnswerOptions = pqcPage.selectOption(displayWhen);
      clickOn(this.driver(), selectedAnswerOptions, TestUtil.getTimeOut());
      }

    }

  /**
   * This method receive the source and destination point and add the field type.
   */
  public void setAddFieldType(final String addSection, final String dropSection) throws InterruptedException {
    Thread.sleep(sleepTime);
    System.out.println("-----AddSection------" + addSection);
    WebElement selectedSectionElement = pqcPage.sectionWebElement(addSection);

    Actions act = new Actions(this.driver());
    act.clickAndHold(selectedSectionElement).build().perform();
    WebElement selectedDropElement = pqcPage.dropFieldWebElement(dropSection);
    JavascriptExecutor js = (JavascriptExecutor) this.driver();
    js.executeScript("arguments[0].scrollIntoView();", selectedDropElement);
    act.moveToElement(selectedDropElement).build().perform();
    act.release(selectedDropElement).build().perform();
    Thread.sleep(sleepTime);
    System.out.println("-----dropSection------" + dropSection);
    }

  /**
   * This method enters initial first question for test.
   */
  public void setAddTestInitial(final String addSection) throws InterruptedException {
    Thread.sleep(sleepTime);
    WebElement selectedSectionElement = pqcPage.sectionWebElement(addSection);
    Actions act = new Actions(this.driver());
    act.clickAndHold(selectedSectionElement).build().perform();
    WebElement selectedDropElement = pqcPage.dropFieldInitialElement();
    act.moveToElement(selectedDropElement).build().perform();
    act.release(selectedDropElement).build().perform();
    }

  /**
   * This method enter the options of choice input field.
   */
  public void setChoiceOption(final String singleOption) {
    System.out.println("Input>>" + singleOption);
    String[] arrOfStr = singleOption.split("\\^");

    for (String strSpilt : arrOfStr) {
      System.out.println("split>>>" + strSpilt);
      pqcPage.getSingleChoiceOption().sendKeys(strSpilt);
      pqcPage.getSingleChoiceOption().sendKeys(Keys.ENTER);
      }
    }

  /**
   * This method clicks on Approved Choice and choose the option from list.
   */
  public void approvedSingleChoice(final String approvedChoice) {
    clickOn(this.driver(), pqcPage.getApprovedChoiceInput(), TestUtil.getTimeOut());
    WebElement selectedChoice = pqcPage.selectOption(approvedChoice);
    clickOn(this.driver(), selectedChoice, TestUtil.getTimeOut());
    }

  /**
   * This method clicks the proceed button after checking whether it is enabled.
   */
  public void setProceed() {

    boolean proceed = pqcPage.getProceedButton().isEnabled();
    AssertJUnit.assertTrue(proceed);
    pqcPage.getProceedButton().click();
    }

  /**
   * This method will drag and drop with source and destination source position.
   */
  public void performDragAndDrop(final String source, final String destination) throws InterruptedException {
    String webSourceLocator = "drag-icon-" + source + ".";
    WebElement selectedSourceElement = this.driver().findElement(By.id(webSourceLocator));
    Thread.sleep(sleepTime);
    Actions act = new Actions(this.driver());
    String webDestLocator = "drag-icon-" + destination + ".";
    act.clickAndHold(selectedSourceElement).build().perform();
    WebElement selectedDropElement = this.driver().findElement(By.id(webDestLocator));
    act.moveToElement(selectedDropElement).build().perform();
    act.release(selectedDropElement).build().perform();
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
