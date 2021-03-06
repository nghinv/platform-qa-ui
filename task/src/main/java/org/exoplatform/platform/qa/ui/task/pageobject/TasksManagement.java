package org.exoplatform.platform.qa.ui.task.pageobject;

import static com.codeborne.selenide.Selectors.byText;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

/**
 * This class will define actions about management project
 */
public class TasksManagement {

  private final TestBase       testBase;

  public TaskManagementHome    tasHome;

  private ElementEventTestBase evt;

  public TasksManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.tasHome = new TaskManagementHome(testBase);
  }

  /**
   * Select an option in Task List
   * 
   * @param op is an option in the list as: Incoming,All tasks,...
   */
  public void selectOptionTask(optionTask op) {
    info("--Open Task Management--");
    tasHome.goToTasks();
    switch (op) {
    case Incoming:
      info("Select Incomming option");
      break;
    case All_Tasks:
      info("Select All tasks option");
      break;
    case Overdue:
      info("Select Overdue option");
      break;
    case Today:
      info("Select Today option");
      break;
    case Tommorrow:
      info("Select Tommorrow option");
      break;
    case Upcoming:
      info("Select Upcoming option");
      break;
    default:
      info("No option in the list.Please select correct option.");
      break;
    }
  }

  /**
   * Open Sorting list of Incoming
   */
  public void goToSortIncomingList() {
    info("Click on Sort list");
  }

  /**
   * Open Group list in Incoming
   */
  public void goToGroupIncoming() {
    info("Click on Group list");
  }

  public void addTask(String task) {

    ELEMENT_BUTTON_ADD_TASK.click();
    ELEMENT_INPUT_TASK_TITLE.setValue(task).pressEnter();
    ELEMENT_TASK_FORM.waitUntil(Condition.appears, Configuration.timeout);
  }

  public void editTask(String task, String newTask, String priority) {
    ELEMENT_TASKS_LIST.find(byText(task)).click();
    ELEMENT_TASK_FORM.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_TASK_FORM.find(byText(task)).click();
    ELEMENT_TASK_FORM_INPUT_TITLE.setValue(newTask);
    ELEMENT_TASK_FORM_PRIORITY.click();
    ELEMENT_TASK_SELECT_PRIORITY.waitUntil(Condition.appears, Configuration.timeout).selectOption(priority);
  }

  public void deleteTask(String task) {
    ELEMENT_TASKS_LIST.find(byText(task)).click();
    ELEMENT_TASK_FORM.waitUntil(Condition.appears, Configuration.timeout);
    ELEMENT_TASK_FORM_ICOND_DROP_DOWN_MENU.click();
    ELEMENT_TASK_BUTTON_DELETE.click();
    ELEMENT_TASK_BUTTON_DELETE_OK.click();
  }

  /**
   * Define options in Task list
   */
  public enum optionTask {
    Incoming, All_Tasks, Overdue, Today, Tommorrow, Upcoming;
  }
}
