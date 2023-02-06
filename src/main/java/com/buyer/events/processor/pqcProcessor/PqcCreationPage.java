package com.buyer.events.processor.pqcProcessor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PqcCreationPage {
  private WebDriver driver;

  public PqcCreationPage(final WebDriver webdriver) {
    this.driver = webdriver;
    //js = (JavascriptExecutor) webdriver;
    PageFactory.initElements(driver, this);

    }

  public final WebDriver getDriver() {
    return driver;
    }

  @FindBy(name = "question")
  private WebElement questionName;

  @FindBy(id = "proceedEvent")
  private WebElement proceedButton;

  @FindBy(name = "sub_section_name")
  private WebElement subSectionElement;

  @FindBy(name = "options_textarea")
  private WebElement singleChoiceOption;

  @FindBy(id = "drag-and-drop-area")
  private WebElement dragAndDrop;

  @FindBy(id = "approved-choice-input")
  private WebElement approvedChoiceInput;

  @FindBy(name = "dependent_question")
  private WebElement dependentQuestion;

  @FindBy(name = "question_id_type")
  private WebElement dependentQuestionType;

  @FindBy(name = "answer_type")
  private WebElement dependentAnswerType;

  @FindBy(xpath = "/html/body/fuse-root/fuse-main/mat-sidenav-container/mat-sidenav/div/app-questionnaire-sidenav/div/div[3]/button[2]/span[1]/span")
  private WebElement saveButton;

  public final WebElement getQuestionName() {
    return questionName;
    }

  public final WebElement getProceedButton() {
    return proceedButton;
    }

  public final WebElement getSubSectionElement() {
    return subSectionElement;
    }

  public final WebElement getSingleChoiceOption() {
    return singleChoiceOption;
    }

  public final WebElement getDragAndDrop() {
    return dragAndDrop;
    }

  public final WebElement getApprovedChoiceInput() {
    return approvedChoiceInput;
    }

  public final WebElement getDependentQuestion() {
    return dependentQuestion;
    }

  public final WebElement getDependentQuestionType() {
    return dependentQuestionType;
    }

  public final WebElement getDependentAnswerType() {
    return dependentAnswerType;
    }

  public final WebElement getSaveButton() {
    return saveButton;
    }

  public final WebElement sectionWebElement(final String addType) {
    WebElement sectionWeb = getDriver().findElement(By.xpath("//*[@data-test = '" + addType + "']"));
    return sectionWeb;
    }

  public final WebElement dropEmptyWebElement() {
    WebElement dropWebElement = getDriver().findElement(By.cssSelector(".empty-elem"));
    return dropWebElement;
    }

  /*
   * public WebElement DropFieldWebElement(String dropPath) { WebElement
   * dropWebElement = driver.findElement(By.
   * cssSelector(".drag-and-drop-area smooth-dnd-container > div > div:nth-child("
   * +dropPath+")")); return dropWebElement; }
   */

  public final WebElement dropFieldWebElement(final String dropPath) {
    WebElement dropWebElement = getDriver().findElement(By.cssSelector("#dnd-drag-" + dropPath + ""));
    return dropWebElement;
    }

  public  final WebElement dropFieldInitialElement() {
    WebElement dropWebElement = getDriver().findElement(By.cssSelector("#drag-and-drop-area"));
    return dropWebElement;
    }

  public final WebElement selectOption(final String selectedChoice) {

    WebElement overlayType = getDriver().findElement(By.className("cdk-overlay-container"));
    // Get all options in a list
    WebElement singleOptions = overlayType
        .findElement(By.xpath("//*[@class = 'mat-option-text' and contains(text(),'" + selectedChoice + "')]"));

    return singleOptions;
    }

  }
