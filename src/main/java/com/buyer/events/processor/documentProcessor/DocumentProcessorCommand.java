package com.buyer.events.processor.documentProcessor;

import com.buyer.events.processor.ITabCommandProcessor;
import com.sol.qa.base.WindowManager;
import com.sol.qa.util.TestUtil;
import com.sol.qa.util.XlsReader;
import java.awt.AWTException;
import java.io.File;
import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;



public class DocumentProcessorCommand implements ITabCommandProcessor {

  private DocumentCreationPage documentPage;
  private static String fileDownloadpath = "C:\\Users\\HP\\Downloads";
  private WindowManager windowManager;
  final int sleepTime = 5000;
  public DocumentProcessorCommand(final WindowManager windowManger) {
    this.windowManager = windowManger;
    documentPage = new DocumentCreationPage(this.driver());
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
    //documentPage = new DocumentCreationPage(this.driver());
    switch (command) {
      case "UploadDocumentButton":
        performUploadDocument();
        break;
      case "AddALink":
        performAddLink();
        break;
      case "FilePath":
        setFilePath(argument1);
        break;
      case "proceedButton":
        performProceed();
        break;
      case "VisibleToSupplier":
        visibleToSupplier(argument1);
        break;
      case "ConfirmAction":
        performConfirmVisibleToSupplier(argument1);
        break;
      case "Action":
        performActionToDocuments(argument1, argument2, argument3);
        break;
      case "UrlAddressAndText":
        setURLAddressLink(argument1, argument2);
        break;
      case "SaveLinkAddress":
        performSaveLink();
        break;
      case "Assertion":
        switch (argument1) {
          case "onLoadDocumentTab":
            onLoadDocumentAssertion();
            break;
          case "successfulUploadDocument":
            successfulUploadDocument(argument2, argument3, argument4);
            break;
          case "successfulUploadFileDetail":
            successfulUploadFileDetail(argument2, argument3, argument4);
            break;
          case "assertAction":
            assertAction(argument2);
            break;
          case "visibilityAddLinkPopUp":
            visibilityAddLinkPopUp();
            break;
          case "IsSaveButtonEnable":
            isSaveButtonEnable();
            break;

          default:
            System.out.println("no switch match");

          }
        break;
      default:
        System.out.println("No Document Process Command Match");

      }

    }

  /**
   * This method to enter the file upload button.
   */
  public void performUploadDocument() {
    WebDriverWait wait = new WebDriverWait(this.driver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    wait.until(ExpectedConditions.visibilityOf(documentPage.getFileUpload()));
    documentPage.getFileUpload().click();
    }

  /**
   * This method to enter add link button.
   */
  public void performAddLink() {
    WebDriverWait wait = new WebDriverWait(this.driver(), Duration.ofSeconds(TestUtil.getTimeOut()));
    wait.until(ExpectedConditions.visibilityOf(documentPage.getAddLink()));
    documentPage.getAddLink().click();
    }

  /**
   * This method to enter the file path of the document upload.
   */
  public void setFilePath(final String fileLink) throws AWTException {
    documentPage = new DocumentCreationPage(this.driver());
    String filePath = System.getProperty("user.dir") + "/src/main/java/com/sol/qa/testdata/" + fileLink;
    documentPage.getFileUploadPath().sendKeys(filePath);
    this.driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.getImplicitWait()));
    }

  /**
   * This method to click on proceed button.
   */
  public void performProceed() {
    boolean proceed = documentPage.getProceedButton().isEnabled();
    AssertJUnit.assertTrue(proceed);
    documentPage.getProceedButton().click();
    }

  /**
   * This method to assert the onLoad Document.
   */
  public void onLoadDocumentAssertion() throws InterruptedException {
    Thread.sleep(sleepTime);
    System.out.println("FileUpload>>>>>" + documentPage.getFileUpload().isDisplayed());
    Assert.assertEquals(true, documentPage.getFileUpload().isDisplayed());
    Assert.assertEquals(true, documentPage.getAddLink().isDisplayed());
    Assert.assertEquals(true, documentPage.getNoDocsLabel().isDisplayed());
    }

  /**
   * This method to assert the visibility of Add Link Pop up.
   */
  public void visibilityAddLinkPopUp() throws InterruptedException {
    Assert.assertEquals(true, documentPage.getUrlLinkAddress().isDisplayed());
    Assert.assertEquals(true, documentPage.getTextToDisplay().isDisplayed());
    Assert.assertEquals(true, documentPage.getCancelAddLink().isEnabled());
    Assert.assertEquals(false, documentPage.getSaveAddLink().isEnabled());
    }

  /**
   * This method to assert the validation uploaded document.
   */
  public void successfulUploadDocument(final String fileRowNum, final String documentName, final String documentType)
      throws InterruptedException {
    Thread.sleep(sleepTime);
    Assert.assertEquals(true, documentPage.getVisibleToSuppliers().isDisplayed());
    System.out.println("fileRowNum>>>>" + fileRowNum);
    System.out.println("File Name>>>" + documentPage.uploadedDocumentName(fileRowNum).getText());
    Assert.assertEquals(documentName, documentPage.uploadedDocumentName(fileRowNum).getText());
    System.out.println("File documentType>>>" + documentPage.fileType(fileRowNum).getText());
    Assert.assertEquals(documentType, documentPage.fileType(fileRowNum).getText());
    /*
     * Date objDate = new Date(); String strDateFormat = "MMM dd, yyyy";
     * SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
     * Assert.assertEquals(documentPage.fileUploadedDate.getText(),
     * objSDF.format(objDate));
     */

    }

  /**
   * This method to assert the validation successful file upload.
   */
  public void successfulUploadFileDetail(final String fileRowNum, final String uploadBy, final String fileSize)
      throws InterruptedException {
    Assert.assertEquals(uploadBy, documentPage.uploadedBy(fileRowNum).getText());
    Assert.assertEquals(fileSize, documentPage.fileSize(fileRowNum).getText());
    }

  /**
   * This method to assert the validation of the Edit , Delete and Download with row number.
   */
  public void assertAction(final String fileRowNum) throws InterruptedException {
    Assert.assertEquals(true, documentPage.editDocument(fileRowNum).isDisplayed());
    Assert.assertEquals(true, documentPage.deleteDocument(fileRowNum).isDisplayed());
    Assert.assertEquals(true, documentPage.downloadDocument(fileRowNum).isEnabled());
    }

  /**
   * This method to check the "visible to supplier" checkbox with file row number.
   */
  public void visibleToSupplier(final String fileRowNum) throws InterruptedException {
    WindowManager.clickOn(this.driver(), documentPage.getVisibleToSuppliers(), TestUtil.getTimeOut());
    }

  /**
   * This method to click on the checkbox for visible to supplier.
   */
  public void performConfirmVisibleToSupplier(final String yesOrNo) throws InterruptedException {
    if ("Yes".equalsIgnoreCase(yesOrNo)) {
      WindowManager.clickOn(this.driver(), documentPage.getConfirmAction(), TestUtil.getTimeOut());
      }
    if ("No".equalsIgnoreCase(yesOrNo)) {
      WindowManager.clickOn(this.driver(), documentPage.getCancelAction(), TestUtil.getTimeOut());
      }
    }

  /**
   * This method to perform the Actions on Document tab - Edit Document , Edit Line and Download.
   */
  public void performActionToDocuments(final String fileNum, final String actionOption, final String textToDisplay)
      throws InterruptedException, AWTException {
    String fileName = documentPage.uploadedDocumentName(fileNum).getText() + "."
        + documentPage.fileType(fileNum).getText();
    System.out.println("actionOption>>>" + actionOption);
    if ("editDocument".equalsIgnoreCase(actionOption)) {
      WindowManager.clickOn(this.driver(), documentPage.editDocument(fileNum), TestUtil.getTimeOut());
      documentPage.editDocumentName(fileNum).sendKeys(Keys.ENTER);

      }
    if ("editLink".equalsIgnoreCase(actionOption)) {
      System.out.println("Edit Link>>>>");
      WindowManager.clickOn(this.driver(), documentPage.editLink(fileNum), TestUtil.getTimeOut());
      Thread.sleep(sleepTime);
      documentPage.getTextToDisplay().clear();
      WindowManager.sendkeys(this.driver(), documentPage.getTextToDisplay(), TestUtil.getTimeOut(), textToDisplay);

      }

    if ("DownloadDocument".equalsIgnoreCase(actionOption)) {
      WindowManager.clickOn(this.driver(), documentPage.downloadDocument(fileNum), TestUtil.getTimeOut());

      boolean status = isFileDownloaded(fileName);
      Assert.assertEquals(true, status);
      /*
       * Robot bot = new Robot(); final int timeSleep = 1000; Thread.sleep(timeSleep);
       * bot.keyPress(KeyEvent.VK_CONTROL); bot.keyPress(KeyEvent.VK_J);
       * bot.keyRelease(KeyEvent.VK_CONTROL); bot.keyRelease(KeyEvent.VK_J);
       * Thread.sleep(sleepTime);
       * ArrayList<String> switchTabs = new
       * ArrayList<String>(this.driver().getWindowHandles());
       * this.driver().switchTo().window(switchTabs.get(1)); this.driver().close();
       * this.driver().switchTo().window(switchTabs.get(0));
       */

      }

    if ("DeleteDocument".equalsIgnoreCase(actionOption)) {
      WindowManager.clickOn(this.driver(), documentPage.deleteDocument(fileNum), TestUtil.getTimeOut());

      }
    }

  /**
   * This method to enter the UrlLinkAddress and TextToDisplay field.
   */
  public void setURLAddressLink(final String urlLink, final String textToDisplay) {
    WindowManager.sendkeys(this.driver(), documentPage.getUrlLinkAddress(), TestUtil.getTimeOut(), urlLink);
    WindowManager.sendkeys(this.driver(), documentPage.getTextToDisplay(), TestUtil.getTimeOut(), textToDisplay);

    }

  /**
   * This method to check whether the Save Add Link button is enabled or not.
   */
  public void isSaveButtonEnable() {
    Assert.assertEquals(true, documentPage.getSaveAddLink().isEnabled());
    }

  /**
   * This method clicks on Save button.
   */
  public void performSaveLink() {
    WindowManager.clickOn(this.driver(), documentPage.getSaveAddLink(), TestUtil.getTimeOut());
    }

  /**
   * This method to check whether file is downloaded or not.
   */
  public boolean isFileDownloaded(final String fileName) throws InterruptedException {

    Thread.sleep(sleepTime);
    boolean flag = false;

    File directory = new File(fileDownloadpath);

    File[] content = directory.listFiles();

    System.out.println("Length of Content >>>" + content.length);
    System.out.println("FileName>>>" + fileName);

    for (int i = 0; i < content.length; i++) {

      if (content[i].getName().equals(fileName)) {
        System.out.println("content File Name>>>>" + content[i].getName());
        flag = true;
        }
      }
    System.out.println("flag>>>" + flag);
    return flag;
    }
  }
