package org.exoplatform.platform.qa.ui.selenium.platform;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_CONNECTION_EVERYONE_TITLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.ELEMENT_ANSWER_PORTLET;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.ELEMENT_FAQ_QUESTION_LIST;
import static org.exoplatform.platform.qa.ui.selenium.locator.calender.CalendarLocator.ELEMENT_CALENDAR_WORKING_PANEL;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class HomePagePlatform {

  private final TestBase       testBase;

  private ElementEventTestBase evt;

  public HomePagePlatform(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Go to Wiki portlet
   */
  public void goToWiki() {
    info("--Go to Wiki--");
    $(ELEMENT_WIKI_LINK_PLF).waitUntil(Condition.appears, Configuration.timeout);
    $(ELEMENT_WIKI_LINK_PLF).click();
  }

  public void goToChat() {
    info("--Go to chat--");
    $(byId("chat-status")).click();
    $(byClassName("notif-chat-open-link")).waitUntil(Condition.appears, Configuration.timeout).click();
  }

  /**
   * Go to Documents
   */
  public void goToDocuments() {
    info("--Go to Documents--");
    evt.click(ELEMENT_DOCUMENTS_LINK_PLF);

  }

  /**
   * Go to Home page
   */
  public void goToHomePage() {
    info("Click on Home link of intranet page");
    $(ELEMENT_HOME_LINK_PLF).click();
  }

  /**
   * Go to Home Calendar Page
   */
  public void goToCalendarPage() {
    info("-- Go to calendar home page --");
    ELEMENT_CALENDAR_LINK_PLF.waitUntil(Condition.appears, Configuration.timeout);
    info("click on Calendar link");
    ELEMENT_CALENDAR_LINK_PLF.click();
    info("Verify that Calendar page is shown");
    $(ELEMENT_CALENDAR_WORKING_PANEL).waitUntil(Condition.appears, Configuration.timeout);
    info("The calendar page is shown successfully");
  }

  public void goToTaskPage() {
    ELEMENT_TASKS_LINK_PLF.waitUntil(Condition.appears, Configuration.timeout).click();
  }

  /**
   * Go to my spaces
   */
  public void goToMySpaces() {
    info("-- Go to my spaces --");
    ELEMENT_MY_SPACE_LINK_PLF.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_MY_SPACE_LINK_PLF.click();
  }

  /**
   * Go to All space list
   */
  public void goToAllSpace() {
    info("Click on Join a space link");

    $(ELEMENT_ALL_SPACE_JOIN_LINK).waitUntil(Condition.appears, Configuration.timeout).click();

  }

  /**
   * Go to answer page
   */
  public void goToAnswer() {
    info("-- Go to answer page --");

    evt.waitForAndGetElement(ELEMENT_ANSWER_LINK_PLF);
    evt.click(ELEMENT_ANSWER_LINK_PLF, 0, true);
    if (evt.waitForAndGetElement(ELEMENT_ANSWER_PORTLET, 5000, 0) == null)
      testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    evt.waitForAndGetElement(ELEMENT_ANSWER_PORTLET);

  }

  /**
   * Go to forum page
   */
  public void goToForum() {
    info("-- Go to forum page --");
    $(ELEMENT_FORUM_LINK_PLF).click();
  }

  /**
   * Go to faq page
   */
  public void goToFaq() {
    info("Base url is " + testBase.getExoWebDriver().getBaseUrl());
    String url = testBase.getExoWebDriver().getBaseUrl() + "/intranet/home/FAQ";
    info("-- Go to FAQ page --");
    testBase.getExoWebDriver().getWebDriver().get(url);
    evt.waitForAndGetElement(ELEMENT_FAQ_QUESTION_LIST);
  }

  /**
   * Open Connections page
   */
  public void goToConnections() {
    info("--Go to Connections page---");

    info("Click on Connection link");
    $(ELEMENT_CONNECTIONS_LINK_PLF).click();
    info("Verify that the connections portlet is shown");
    $(ELEMENT_CONNECTION_EVERYONE_TITLE).waitUntil(Condition.appears, Configuration.timeout);
    info("The connections portlet is shown successfully");
  }

  /**
   * Go to a space
   *
   * @param space
   */
  public void goToSpecificSpace(String space) {
    info("Go to space " + space);

    ELEMENT_SPECIFIC_PANEL.find(byText(space)).click();

  }

  /**
   * Open friend profile page
   *
   * @param username
   */
  public void goToFriendProfilePage(String username) {
    info("Go to Friend profile page");
    testBase.getExoWebDriver().getWebDriver().get(testBase.getExoWebDriver().getBaseUrl() + "/intranet/profile/" + username);

  }

  /**
   * Select display mode for AS
   *
   * @param type as My Activities, All Activities....
   */
  public void selectDisplayMode(displayModeType type) {
    info("Open drop menu");
    evt.click(ELEMENT_HOMEPAGE_DROP_MENU_ARROW);
    switch (type) {
    case My_Activities:
      info("Select My Activities");
      evt.click(ELEMENT_HOMEPAGE_DROP_MENU_MY_ACTIVITIES);
      break;
    case All_Activities:
      info("Select All Activities");
      evt.click(ELEMENT_HOMEPAGE_DROP_MENU_ALL_ACTIVITIES);
      break;
    case My_Spaces:
      info("Select My Spaces");
      evt.click(ELEMENT_HOMEPAGE_DROP_MENU_MY_SPACES);
      break;
    case Connections:
      info("Select Connections");
      evt.click(ELEMENT_HOMEPAGE_DROP_MENU_CONNECTIONS);
      break;
    }
  }

  /**
   * Check display of user in Invitation gadget
   *
   * @param user
   * @param isPresent
   */
  public void checkDisplayInInvitationGadget(String user, boolean isPresent) {
    info("check display of user in Invitation");
    goToHomePage();
    if (isPresent)
      evt.waitForAndGetElement(ELEMENT_INVITATIONS_PEOPLE_AVATAR.replace("${name}", user));
    else
      evt.waitForElementNotPresent(ELEMENT_INVITATIONS_PEOPLE_AVATAR.replace("${name}", user));
  }

  /**
   * Load More Activities
   */
  public void loadMoreActivities() {

    info("Load more activities");
    evt.waitForAndGetElement(ELEMENT_PLF_HOMEPAGE_LOAD_MORE_BUTTON, testBase.getDefaultTimeout(), 1);
    evt.click(ELEMENT_PLF_HOMEPAGE_LOAD_MORE_BUTTON);
  }

  /**
   * Check display of user in Suggestion gadget
   *
   * @param user
   * @param isPresent
   */
  public void checkDisplayInSuggestionGadget(String user, boolean isPresent) {
    info("check display of user in Suggestion");
    goToHomePage();
    if (isPresent)
      evt.waitForAndGetElement(ELEMENT_SUGGESTION_NAME.replace("${name}", user));
    else
      evt.waitForElementNotPresent(ELEMENT_SUGGESTION_NAME.replace("${name}", user));
  }

  /**
   * Define display mode's type of the AS as My Activities,All Activities,...
   */
  public enum displayModeType {
    My_Activities, All_Activities, My_Spaces, Connections;
  }

}
