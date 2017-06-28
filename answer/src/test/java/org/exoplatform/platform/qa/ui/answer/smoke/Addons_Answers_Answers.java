package org.exoplatform.platform.qa.ui.answer.smoke;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.answer.pageobject.*;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.commons.objectdatabase.TextBoxDatabase;
import org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Button.ELEMENT_OK_BUTTON_LINK;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.answer.AnswerLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

@Tag("smoke")
@Tag("answer")

public class Addons_Answers_Answers extends Base {

HomePagePlatform homePagePlatform;
AnswerManagement answerManagement;
CommentManagement commentManagement;
QuestionManagement questionManagement;

	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
		homePagePlatform=new HomePagePlatform(this);
		answerManagement=new AnswerManagement(this);
		commentManagement=new CommentManagement(this);
		questionManagement=new QuestionManagement(this);
	}

	/**
	 * Case ID:116817.
	 * Test Case Name: Answer a question.
	 * Case ID:116818.
	 * Test Case Name: Edit an answer.
	 * Case ID:116819.
	 * Test Case Name: Delete an answer.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test04_AddAnswerAQuestion() {
		String answer = "answer"+getRandomNumber();
		String newanswer = "newanswer"+getRandomNumber();
		String question= "question"+getRandomNumber();
		String fullName = "fullName"+getRandomNumber();
		String content= "content"+getRandomNumber();

		info("Test 4: Answer a question");
		/*Step Number: 1
		 *Step Name: Prepare data: Create a category, a question,
		 *Step Description: 
			- Login as john
			- Go to Answers
			- Create a category
			- Create a question in this category
		 *Input Data: 

		 *Expected Outcome: 
			- Category is created successfully.
			- Question is created successfully*/

		/*Step number: 2
		 *Step Name: Answer a question
		 *Step Description: 
			- Open that question
			-Click on Answer,
			- Put data into editor
			- Set other fields
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Answer a question successfully,
			- Show the answer below this question*/
		homePagePlatform.goToAnswer();
		questionManagement.goToSubmitQuestion();
		questionManagement.inputDataToQuestionForm(question, "", null, "");
		$(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
		$(ELEMENT_OK_BUTTON_LINK).click();
		$(byText(question)).click();
		answerManagement.goToAnswerQuestion(question);
		answerManagement.inputDataToAnswer(answer, null, null, null);
		///click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		$(ELEMENT_ANSWER_FORM_SAVE_BUTTON).click();
		$(byText(answer)).waitUntil(Condition.appears,Configuration.timeout);
		questionManagement.deleteQuestion(question);
	}
	@Test
	public  void test05_EditAnswerAQuestion() {
		String newanswer = "newanswer" + getRandomNumber();
		String answer = "answer" + getRandomNumber();
		String fullName = "fullName" + getRandomNumber();
		String question = "question" + getRandomNumber();

		info("Test 5: Edit an answer");
		/*Step number: 3
		 *Step Name: Edit an answer
		 *Step Description: 
			- Open that answer, click More Actions 
			-
			-> Edit Answer
			- Input data into editor
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- An "Edit answer" popup is shown.
			- New content of answer is saved*/
		homePagePlatform.goToAnswer();
		questionManagement.goToSubmitQuestion();
		questionManagement.inputDataToQuestionForm(question, "", null, "");
		$(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
		$(ELEMENT_OK_BUTTON_LINK).click();
		$(byText(question)).click();
		answerManagement.goToAnswerQuestion(question);
		answerManagement.inputDataToAnswer(answer, null, null, null);
		///click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		$(ELEMENT_ANSWER_FORM_SAVE_BUTTON).click();
		$(byText(answer)).waitUntil(Condition.appears, Configuration.timeout);

		answerManagement.goToActionOfAnswerFromMoreAction(answer, AnswerManagement.actionAnswerOption.EDIT);
		answerManagement.inputDataToAnswer(newanswer, null, null, null);

		$(ELEMENT_ANSWER_FORM_SAVE_BUTTON).click();
		$(byText(newanswer)).waitUntil(Condition.appears, Configuration.timeout);
		questionManagement.deleteQuestion(question);
	}

		@Test
		public  void test06_DeleteAnswerAQuestion(){
			String newanswer = "newanswer"+getRandomNumber();
			String answer = "answer"+getRandomNumber();
			String fullName = "fullName"+getRandomNumber();
			String question= "question"+getRandomNumber();

		info("Test 6: Delete an answer");
		/*Step number: 3
		 *Step Name: Delete an answer
		 *Step Description: 
			- Open that answer
			- Click More Actions 
			-
			-> Delete Answer
		 *Input Data: 

		 *Expected Outcome: 
			The answer is deleted successfully*/
		homePagePlatform.goToAnswer();
		questionManagement.goToSubmitQuestion();
		questionManagement.inputDataToQuestionForm(question, "", null, "");
		$(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON).click();
		$(ELEMENT_OK_BUTTON_LINK).click();
		$(byText(question)).click();
		answerManagement.goToAnswerQuestion(question);
		answerManagement.inputDataToAnswer(answer, null, null, null);
		$(ELEMENT_ANSWER_FORM_SAVE_BUTTON).click();
		$(byText(answer)).waitUntil(Condition.appears,Configuration.timeout);
		answerManagement.goToActionOfAnswerFromMoreAction(answer, AnswerManagement.actionAnswerOption.DELETE);
		$( ELEMENT_ANSWER_DELETE_FORM_OK_BUTTON ).click();
		questionManagement.deleteQuestion(question);

	}

	/**
	 * Case ID:116820.
	 * Test Case Name: Add a comment.
	 * Case ID:116821.
	 * Test Case Name: Edit a comment.
	 * Case ID:116822.
	 * Test Case Name: Delete a comment.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test07_08_09_AddEditDeleteAComment() {
		String answer =  "answer"+getRandomNumber();
		String comment =  "comment"+getRandomNumber();
		String newcomment =  "newcomment"+getRandomNumber();
		String question1= "question1"+getRandomNumber();
		String fullName = "fullName"+getRandomNumber();

		info("Test 7: Add a comment");
		/*Step Number: 1
		 *Step Name: Prepare data: Create a question, answer
		 *Step Description: 
			- Go to Answers
			- Create a question
			- Answer this question
		 *Input Data: 

		 *Expected Outcome: 
			- Question is created successfully
			- Answer successfully*/
		homePagePlatform.goToAnswer();
		answerManagement.goToAnswerQuestion(question1);
		answerManagement.inputDataToAnswer(answer, null, null, null);
		//click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		clickByJavascript(ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
		waitForAndGetElement(ELEMENT_ANSWER_AUTHOR.replace("$answer", answer).replace("$fullname", fullName));

		/*Step number: 2
		 *Step Name: Comment on a question
		 *Step Description: 
			- Open that question
			- Click on Comment link
			- Put values
			- Click on save
		 *Input Data: 

		 *Expected Outcome: 
			- Comment successfully*/
		commentManagement.goToCommentQuestion(question1);
		commentManagement.inputDataToComment(comment);
		//click(comMang.ELEMENT_COMMENT_FORM_SAVE_BUTTON);
		clickByJavascript(ELEMENT_COMMENT_FORM_SAVE_BUTTON, 2);
		waitForAndGetElement(ELEMENT_COMMENT_AUTHOR.replace("$comment", comment).replace("$fullname", fullName));

		info("Test 8: Edit a comment");
		/*Step number: 3
		 *Step Name: Edit a comment
		 *Step Description: 
			- Open a comment
			- Click More Actions 
			-
			-> Edit Comment
			- Input data into fields
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Edit comment successfully
			- New content of comment is saved*/
		commentManagement.goToActionOfCommentFromMoreAction(comment, CommentManagement.actionCommentOption.EDIT);
		commentManagement.inputDataToComment(newcomment);
		//click(comMang.ELEMENT_COMMENT_FORM_SAVE_BUTTON);
		clickByJavascript(ELEMENT_COMMENT_FORM_SAVE_BUTTON, 2);
		waitForAndGetElement(ELEMENT_COMMENT_AUTHOR.replace("$comment", newcomment).replace("$fullname", fullName));
		waitForElementNotPresent(ELEMENT_COMMENT_AUTHOR.replace("$comment", comment).replace("$fullname", fullName));

		info("Test 9: Delete a comment");
		/*Step number: 3
		 *Step Name: Delete a comment
		 *Step Description: 
			- Open a comment
			- Click More Actions 
			-
			-> Delete Comment
			- Click OK in the confirmation message
		 *Input Data: 

		 *Expected Outcome: 
			- Delete a comment successfully*/
		commentManagement.deleteComment(newcomment);
	}


}