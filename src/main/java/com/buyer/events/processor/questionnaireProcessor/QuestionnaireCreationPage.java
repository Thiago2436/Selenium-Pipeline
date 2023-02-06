package com.buyer.events.processor.questionnaireProcessor;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sol.qa.base.TestBase;

public class QuestionnaireCreationPage extends TestBase {

  public QuestionnaireCreationPage() {
    PageFactory.initElements(getDriver(), this);

    }

  @FindBy(xpath = "//*[@class = 'empty-elem more-space ng-star-inserted']")
  private WebElement dragDropSectionBox;

  @FindBy(xpath = "//*[@class = 'drop-btn-items ng-star-inserted' and text()='Section']")
  private WebElement addSection;

  @FindBy(name = "section_name")
  private WebElement addSectionName;

  @FindBy(name = "sub_section_name")
  private WebElement subSectionName;

  @FindBy(name = "section_weight")
  private WebElement sectionWeight;

  @FindBy(name = "question")
  private WebElement questionName;

  @FindBy(name = "options_textarea")
  private WebElement singleChoiceOption;

  @FindBy(id = "proceedEvent")
  private WebElement proceedButton;

  @FindBy(name = "year")
  private WebElement assignEvaluatorDropdown;

  @FindBy(name = "is_section_scoring_required")
  private WebElement doesNotRequireScoring;

  @FindBy(xpath = "//*[text()='Drag & drop Section here']")
  private WebElement dragDropSubSectionBox;

  @FindBy(xpath = "//input[@placeholder = 'Search Evaluator...']")
  private WebElement searchEvaluator;

  @FindBy(id = "mat-select-10-panel")
  private List<WebElement> evaluatorPanel;

  @FindBy(className = "sidenav-body")
  private WebElement formName;

  @FindBy(xpath = "/html/body/fuse-root/fuse-main/mat-sidenav-container/mat-sidenav-content/div/div/div/fuse-content"
      + "/app-newsource/div/mat-sidenav-container/mat-sidenav-content/div/div[3]/event-questionnaries/div/div[2]/smooth-dnd-container"
      + "/div/smooth-dnd-draggable[1]/section-template/mat-expansion-panel/div/div/smooth-dnd-container/div/smooth-dnd-draggable/question-template"
      + "/div/div[1]/div/span[1]/input")
  private WebElement questionWeight;

  @FindBy(xpath = "/html/body/fuse-root/fuse-main/mat-sidenav-container/mat-sidenav/div/app-questionnaire-sidenav/div/div[3]/button[2]/span[1]/span")
  private WebElement saveButton;

  public final WebElement getDragDropSectionBox() {
    return dragDropSectionBox;
    }

  public final WebElement getAddSection() {
    return addSection;
    }

  public final WebElement getAddSectionName() {
    return addSectionName;
    }

  public final WebElement getSubSectionName() {
    return subSectionName;
    }

  public final WebElement getSectionWeight() {
    return sectionWeight;
    }

  public final WebElement getQuestionName() {
    return questionName;
    }

  public final WebElement getSingleChoiceOption() {
    return singleChoiceOption;
    }

  public final WebElement getProceedButton() {
    return proceedButton;
    }

  public final WebElement getAssignEvaluatorDropdown() {
    return assignEvaluatorDropdown;
    }

  public final WebElement getDoesNotRequireScoring() {
    return doesNotRequireScoring;
    }

  public final WebElement getDragDropSubSectionBox() {
    return dragDropSubSectionBox;
    }

  public final WebElement getSearchEvaluator() {
    return searchEvaluator;
    }

  public final List<WebElement> getEvaluatorPanel() {
    return evaluatorPanel;
    }

  public final WebElement getFormName() {
    return formName;
    }

  public final WebElement getQuestionWeight() {
    return questionWeight;
    }

  public final WebElement getSaveButton() {
    return saveButton;
    }

  public final WebElement sectionWebElement(final String addType) {
    WebElement sectionWeb = getDriver()
        .findElement(By.xpath("//*[@class = 'drop-btn-items ng-star-inserted' and text()='" + addType + "']"));
    return sectionWeb;
    }

  public final WebElement dropSectionWebElement(final String dropPath) {

    WebElement dropWebElement = getDriver().findElement(By.cssSelector(".empty-elem:last-of-type"));
    return dropWebElement;
    }

  public final WebElement dropEmptyWebElement1(final String dropPath) {
    WebElement dropWebElement = getDriver().findElement(By.cssSelector(
        ".right-container > smooth-dnd-container > div > smooth-dnd-draggable:nth-child(" + dropPath + ")"));

    return dropWebElement;
    }

  public final WebElement dropEmptyWebElement(final String dropPath) {

    WebElement test = getDriver().findElement(By.className("right-container"));
    WebElement dropWebElement = getDriver().findElement(By.cssSelector(
        ".right-container > smooth-dnd-container > div > smooth-dnd-draggable:nth-child(" + dropPath + ")"));
    //WebElement dropWebElement = driver.findElement(By.tagName("smooth-dnd-container"));

    return dropWebElement;
    }

  }
