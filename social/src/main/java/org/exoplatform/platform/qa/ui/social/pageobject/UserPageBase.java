package org.exoplatform.platform.qa.ui.social.pageobject;

import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class UserPageBase {

  private final TestBase       testBase;

  private ElementEventTestBase evt;

  /**
   * constructor
   * 
   * @param dr
   */
  public UserPageBase(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * Go to my wiki
   */
  public void goToProfileTab() {
    info("Go to profile tab");
    evt.click(ELEMENT_HORIZONTAL_TOOLBAR_FIRST_APP_PROFILE);
  }

  /**
   * Go to my wiki
   */
  public void goToWikiTab() {
    info("Go to my wiki");
    evt.click(ELEMENT_HORIZONTAL_TOOLBAR_FORTH_APP_WIKI);
  }

  /**
   * Go to my activity tab
   */
  public void goToActivityTab() {
    info("Go to activity tab");
    evt.click(ELEMENT_HORIZONTAL_TOOLBAR_SECOND_APP_ACTIVITIES);
  }

  /**
   * Go to my activity tab
   */
  public void goToConnectionTab() {
    info("Go to activity tab");
    evt.click(ELEMENT_HORIZONTAL_TOOLBAR_THIRD_APP_CONNECTIONS);
  }

  /**
   * Go to my activity tab
   */
  public void goToDashboardTab() {
    info("Go to dashboard tab");
    evt.click(ELEMENT_HORIZONTAL_TOOLBAR_FIFTH_APP_DASHBOARD);
  }
}
