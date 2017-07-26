package org.exoplatform.platform.qa.ui.forum.pageobject;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.*;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.exoplatform.platform.qa.ui.selenium.Button;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ManageFileTestBase;

public class ForumHomePage {

  private final TestBase       testBase;

  public ManageAlert           alert;

  public Button                button;

  public ManageFileTestBase    mftb;

  private ElementEventTestBase evt;

  /**
   * constructor
   *
   * @param dr
   */
  public ForumHomePage(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
    this.button = new Button(testBase);
    this.mftb = new ManageFileTestBase(testBase);
  }

  /**
   * Go to home category
   */
  public void goToHomeCategory() {

    $(ELEMENT_CATEGORY_BREADCUMB_HOME).click();
  }

  /**
   * select a item in Manage Category Menu By QuynhPT
   *
   * @param item
   */
  public void selectItemAdministrationMenu(specifAdministrationMenu item) {
    info("Waiting administration menu is shown");
    $(ELEMENT_ACTIONBAR_ADMINISTRATION).should(Condition.appears);
  //  evt.waitForAndGetElement(ELEMENT_ACTIONBAR_ADMINISTRATION);
    info("Click on Manage menu");
    $(ELEMENT_ACTIONBAR_ADMINISTRATION).click();

    switch (item) {
    case SORT_SETTING:
      break;
    case CENSOR_KEYWORDS:
      break;
    case BBCODE:
      info("Click on BBCode link");
      $(ELEMENT_ACTIONBAR_ADMIN_BBCODE).click();
      break;
    case NOTIFICATIONS:
      break;
    case BANNED_IPS:
      break;
    case PRUNNING:
      break;
    case EXPORT:
      info("Export a category");
      $(ELEMENT_ACTIONBAR_ADMIN_EXPORT).click();
      break;
    case IMPORT:
      info("Import a category");
      $(ELEMENT_ACTIONBAR_ADMIN_IMPORT).click();
      break;
    default:
      break;
    }
  }

  /**
   * Import a category from Administration menu
   *
   * @param folderDowloadFile
   * @param nameFile
   */
  public void importCategory(String folderDowloadFile, String nameFile) {
    selectItemAdministrationMenu(specifAdministrationMenu.IMPORT);
    mftb.importFile(folderDowloadFile, nameFile);
    button.ok();

  }

  /**
   * Go to a detail category in list By QuynhPT
   *
   * @param name
   */
  public void goToCategory(String name) {
    // goToHomeCategory();
    // evt.click(ELEMENT_FORUM_DETAIL_FORUM_NAME_LINK.replace("${name}", name));
    $(byText(name)).click();
  }

  /**
   * Watch or UnWatch true is for watching false is for un-watching Update
   * QuynhPT
   *
   * @param watch
   */
  public void watchItem(boolean watch) {
    if (watch) {
      if (evt.waitForAndGetElement(ELEMENT_WATCH, testBase.getDefaultTimeout(), 0) != null) {
        info("Watch item");
        evt.click(ELEMENT_WATCH);
        evt.waitForMessage(MESSAGE_WATCH);
        evt.click(ELEMENT_OK_INFOR_POPUP);
        evt.waitForAndGetElement(ELEMENT_UNWATCH);
        info("Watch item successfully");
      } else {
        info("Not found watch link");
      }
    } else {
      if (evt.waitForAndGetElement(ELEMENT_UNWATCH, testBase.getDefaultTimeout(), 0) != null) {
        info("Unwatch item");
        evt.click(ELEMENT_UNWATCH);
        evt.waitForMessage(MESSAGE_UNWATCH);
        evt.click(ELEMENT_OK_INFOR_POPUP);
        evt.waitForAndGetElement(ELEMENT_WATCH);
        info("Unwatch item successfully");
      } else {
        info("Not found unwatch link");
      }
    }
  }

  /**
   * Open a forum
   *
   * @param name
   */
  public void goToForum(String name) {
    info("Click on the forum with the name:" + name);
    $(ELEMENT_SELECT_FORUM_TOPIC.replace("${link}", name)).click();

  }

  /**
   * Attach file in attach popup
   *
   * @param pathFile
   * @param fileName
   */
  public void attachFile(String pathFile, String fileName) {
    info("Attach a file");
    WebElement element = evt.waitForAndGetElement(ELEMENT_UPLOAD_POPUP_ATTACHMENT_FILE_INPUT, testBase.getDefaultTimeout(), 1, 2);
    ((JavascriptExecutor) testBase.getExoWebDriver().getWebDriver()).executeScript("arguments[0].style.display = 'block';",
                                                                                   element);
    info("Get the file to attach");
    element.sendKeys(testBase.getAbsoluteFilePath(pathFile + fileName));
    info("Verify that the file is attached");
    evt.waitForAndGetElement(ELEMENT_UPLOAD_POPUP_NAMEFILE.replace("${fileName}", fileName));
    info("The file is attached successfully");
    info("Click on Save button");
    evt.click(ELEMENT_UPLOAD_POPUP_ATTACHMENT_FILE_SAVE_BUTTON);

  }

  /**
   * Add a BBcode Update QuynhPT
   *
   * @param tag
   * @param replacement
   * @param description
   * @param example
   * @param use
   *          <li>add a java doc</li>
   */
  public void addBBCode(String tag, String replacement, String description, String example, boolean use) {
    selectItemAdministrationMenu(specifAdministrationMenu.BBCODE);
    info("Click on Add button of Add BBCode popup");
    $(ELEMENT_ADMIN_BBCODE_ADDBBCODE).click();
    info("Input new tag");
    $(ELEMENT_BBCODE_ADDBBCODEFORM_TAG).setValue(tag);
    info("Input new replacement");
    $(ELEMENT_BBCODE_ADDBBCODEFORM_REPLACEMENT).setValue(replacement);
    info("Input new description");
    $(ELEMENT_BBCODE_ADDBBCODEFORM_DESCRIPTION).setValue(description);
    info("Input new example");
    $(ELEMENT_BBCODE_ADDBBCODEFORM_EXAMPLE).setValue(example);
    if (use == true)
      $(ELEMENT_BBCODE_USE_OPTION).click();
    info("Click on Save button and save all changes");
    $(ELEMENT_SAVE_BBCODE).click();
    info("Verify that BBcode is created");
    $(byText(tag.toUpperCase())).should(Condition.exist);
    info("Close the popup");
    $(ELEMENT_BBCODE_POPUP_CLOSEBTN).click();
  }

  /**
   * Edit BBcode
   *
   * @param newTag
   * @param newReplacement
   * @param newDescription
   * @param newExample
   * @param use
   */
  public void editBBCode(String newTag, String newReplacement, String newDescription, String newExample, boolean use) {
    selectItemAdministrationMenu(specifAdministrationMenu.BBCODE);
    info("Click on Edit BBcode");
    $(ELEMENT_BBCODE_EDITBBCODE).click();
    info("Input new tag");
    $(ELEMENT_BBCODE_ADDBBCODEFORM_TAG).setValue(newTag);
    info("Input new replacement");
    $(ELEMENT_BBCODE_ADDBBCODEFORM_REPLACEMENT).setValue(newReplacement);
    info("Input new description");
    $(ELEMENT_BBCODE_ADDBBCODEFORM_DESCRIPTION).setValue(newDescription);
    info("Input new example");
    $(ELEMENT_BBCODE_ADDBBCODEFORM_EXAMPLE).setValue(newExample);
    if (use == true)
      $(ELEMENT_BBCODE_USE_OPTION).click();
    info("Click on Save button and save all changes");
    $(ELEMENT_EDITSITE_SAVEBTN).click();
    info("Verify that BBcode is edited with changes");
   $(byText(newTag.toUpperCase())).should(Condition.exist);
    info("Close the popup");
    $(ELEMENT_BBCODE_POPUP_CLOSEBTN).click();
  }

  /**
   * Delete a BBcode
   *
   * @param tag
   */
  public void deleteBBcode(String tag) {
    selectItemAdministrationMenu(specifAdministrationMenu.BBCODE);
    info("Click on Delete of the tag");
    $(ELEMENT_BBCODE_DELETEBBCODE).click();
    info("Click on OK buton of Confirm popup");
    $(ELEMENT_BBCODE_CONFIRM_DELETETAG).click();
    info("Verify that BBcode is closed");
    $(byText(tag.toUpperCase())).should(Condition.exist);
    info("Close the popup");
    $(ELEMENT_BBCODE_POPUP_CLOSEBTN).click();
  }

  /**
   * Bookmark a category and a forum
   *
   * @param name
   */
  public void bookmark(String name) {
    info("Click on Bookmark link on Action bar");
    $(ELEMENT_ACTIONBAR_BOOKMARK_ICON).click();
    info("Click on Bookmark link on Action bar to open Bookmark popup");
    $(ELEMENT_ACTIONBAR_BOOKMARK_MANAGER).click();
    info("Verify that the topic is bookmarked");
    $(byText(name)).should(Condition.exist);
    info("Delete the bookmark of the topic");
    $(ELEMENT_FORUM_BOOKMARK_DELETE).click();
    info("Verify that the bookmark is deleted");
    $(ELEMENT_FORUM_BOOKMARK_NAME).find(byText(name)).shouldNot(Condition.exist);
    info("Close the popup");
    $(ELEMENT_FORUM_BOOKMARK_CLOSE_ICON).click();
  }
  /**
   * Bookmark a topic
   *
   * @param name
   */
  public void topicbookmark(String name) {
    info("Click on More actions button");
    $(MORE_ACTIONS_TOPIC).click();
    info("Click on Bookmark buton in Topic more actions list");
    $(ELEMENT_TOPIC_BOOKMARK).click();
    info("Click on Bookmark link on Action bar to open Bookmark popup");
    $(ELEMENT_ACTIONBAR_BOOKMARK_MANAGER).click();
    info("Verify that the topic is bookmarked");
    $(byText(name)).should(Condition.exist);
    info("Delete the bookmark of the topic");
    $(ELEMENT_FORUM_BOOKMARK_DELETE).click();
    info("Verify that the bookmark is deleted");
    $(ELEMENT_FORUM_BOOKMARK_NAME).find(byText(name)).shouldNot(Condition.exist);
    info("Close the popup");
    $(ELEMENT_FORUM_BOOKMARK_CLOSE_ICON).click();
  }

  /**
   * Export a category from Action bar
   *
   * @param catName
   * @param fileName
   */
  public void exportCategory(String catName, String fileName) {
    selectItemAdministrationMenu(specifAdministrationMenu.EXPORT);
    info("Uncheck all category");
    evt.uncheck(ELEMENT_EXPORTCAT_EXPORTALL, 2);
    info("Select the category:" + catName);
    evt.check(ELEMENT_EXPORTCAT_EXPORT.replace("${title}", catName), 2);
    info("input name");
    evt.type(ELEMENT_FILENAME_INPUT, fileName, true);
    info("Save all changes");
    evt.click(ForumLocator.ELEMENT_SAVE_BTN);
  }

  /**
   * Open a topic
   *
   * @param name
   */
  public void goToTopic(String name) {
    info("Click on the topic with the name:" + name);
    $(byText(name)).click();
  }

  /**
   * Go to Private Message
   */
  public void goToPrivateMessage() {
    // TODO Auto-generated method stub
    info("Click on Private Message button");
    evt.click(ELEMENT_ACTIONBAR_PRIVATE_MESSAGE);
  }

  /**
   * Go to Compose New Message tab in Private Message
   */
  public void goToComposeNewMessageTab() {
    info("Click on Compose New Message tab");
    evt.click(ELEMENT_PRIVATE_MESSAGE_COMPOSE_MESSAGE_TAB);

  }

  /**
   * Select User in Compose New Message tab
   */
  public void gotoUserSelectorInComposeNewMessageTab() {
    info("-- Go to wiki home page --");
    evt.click(ELEMENT_COMPOSE_NEW_MESSAGE_USER_SELECTOR);
  }

  /**
   * function: Search user in User Selection Form when Compose New Private
   * Message
   */

  public void searchUser(String user, String searchOption) {
    info("--Search user " + user + "--");
    evt.type(ELEMENT_COMPOSE_NEW_MESSAGE_INPUT_SEARCH_USER_NAME, user, true);
    evt.select(ELEMENT_COMPOSE_NEW_MESSAGE_SELECT_SEARCH_OPTION, searchOption);
    evt.click(ELEMENT_COMPOSE_NEW_MESSAGE_SEARCH_ICON);
    evt.waitForTextPresent(user);
  }

  public void searchUserNotFound(String user, String searchOption) {
    info("--Search user " + user + "--");
    evt.type(ELEMENT_COMPOSE_NEW_MESSAGE_INPUT_SEARCH_USER_NAME, user, true);
    evt.select(ELEMENT_COMPOSE_NEW_MESSAGE_SELECT_SEARCH_OPTION, searchOption);
    evt.click(ELEMENT_COMPOSE_NEW_MESSAGE_SEARCH_ICON);
    evt.waitForTextNotPresent(user);
  }

  /**
   * Cancel send Private Message
   */
  public void cancelSendPrivateMessage() {
    info("Cancel Add or Edit Forum");
    evt.click(ELEMENT_PRIVATE_MESSAGE_CANCEL);
  }

  /**
   * Close User Selector page
   */
  public void closeUserSelector() {
    info("-- Go to User Selector page --");
    evt.click(ELEMENT_COMPOSE_NEW_MESSAGE_CLOSE_USER_SELETOR);

  }

  /**
   * Go to Manage User in Forum
   */
  public void goToManageUser() {
    // TODO Auto-generated method stub
    info("Click on Users button on Forum administration bar");
    evt.click(ELEMENT_FORUM_USER_LIST);

  }

  /**
   * function: Search user in User Manage Form
   */

  public void searchUserInUserList(String user, String searchOption) {
    info("--Search user " + user + "--");
    evt.type(ELEMENT_MANAGE_USER_INPUT_SEARCH_USER_NAME, user, true);
    evt.click(ELEMENT_MANAGE_USER_SEARCH_ICON);
    evt.waitForTextPresent(user);
  }

  public void searchUserInUserListNotFound(String user, String searchOption) {
    info("--Search user " + user + "--");
    evt.type(ELEMENT_MANAGE_USER_INPUT_SEARCH_USER_NAME, user, true);
    evt.click(ELEMENT_MANAGE_USER_SEARCH_ICON);
    evt.waitForTextNotPresent(user);
  }

  /**
   * Close User form
   */
  public void closeUserForm() {
    info("-- Go to User form --");
    evt.click(ELEMENT_USER_FORM_CLOSE);

  }

  /**
   * Go to My Subscription
   */
  public void gotoMySubscriptions() {
    evt.click(ELEMENT_FORUM_SETTINGS);
    evt.click(ELEMENT_FORUM_SETTINGS_MYSUSCRIB);
  }

  /**
   * Update email in Sub-scriptions
   */
  public void updateEmailInMySubscriptions(String email) {
    info("Update Email in My Subscriptions ");
    evt.type(ELEMENT_MY_SUBSCRIPTIONS_EMAIL_UPDATE, email, true);
    evt.click(ELEMENT_FORUM_SETTINGS_UPDATE);
    evt.waitForTextPresent(email);
  }

  /**
   * list sublinks in Administration menu
   *
   * @author quynhpt
   */
  public enum specifAdministrationMenu {
    SORT_SETTING, CENSOR_KEYWORDS, NOTIFICATIONS, BBCODE, PRUNNING, BANNED_IPS, EXPORT, IMPORT;
  }

}
