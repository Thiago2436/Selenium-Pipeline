package com.buyer.events.processor.teamMemberProcessor;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class TeamMemberCreationPage {


  public TeamMemberCreationPage(final WebDriver driver) {
    PageFactory.initElements(driver, this);
    }

  @FindBy(id = "addMember")
  private WebElement addMemberButton;

  @FindBy(id = "cancelMember")
  private WebElement cancelButton;

  @FindBy(id = "proceedEvent")
  private WebElement proceedButton;

  @FindBy(id = "addEventTeamMember")
  private WebElement addMemberDropDown;

  @FindBy(xpath = "//input[@placeholder = 'Search User...']")
  private WebElement addMemberSearchUser;

  @FindBy(id = "confirmMember")
  private WebElement confirmMemberButton;

  @FindBy(id = "selectTeamMember-panel")
  private List<WebElement> teamMemberPanel;

  @FindBy(id = "approvers")
  private WebElement approversTab;

  @FindBy(id = "addPQCApprover")
  private WebElement addPqcApprover;

  public final WebElement getAddMemberButton() {
    return addMemberButton;
    }

  public final WebElement getCancelButton() {
    return cancelButton;
    }

  public final WebElement getProceedButton() {
    return proceedButton;
    }

  public final WebElement getAddMemberDropDown() {
    return addMemberDropDown;
    }

  public final WebElement getAddMemberSearchUser() {
    return addMemberSearchUser;
    }

  public final WebElement getConfirmMemberButton() {
    return confirmMemberButton;
    }

  public final List<WebElement> getTeamMemberPanel() {
    return teamMemberPanel;
    }

  public final WebElement getApproversTab() {
    return approversTab;
    }

  public final WebElement getAddPqcApprover() {
    return addPqcApprover;
    }


  }
