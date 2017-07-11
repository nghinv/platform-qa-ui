package org.exoplatform.platform.qa.ui.platform.wiki;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;



@Tag("wiki")
@Tag("smoke")
public class WikiBasicActionManagePageRename extends Base {

	HomePagePlatform homePagePlatform;
	WikiHomePage wikiHomePage;
	ManageLogInOut manageLogInOut;
	WikiManagement wikiManagement;
	RichTextEditor richTextEditor;


	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
		homePagePlatform = new HomePagePlatform(this);
		wikiHomePage = new WikiHomePage(this);
		manageLogInOut = new ManageLogInOut(this);
		wikiManagement = new WikiManagement(this);

		try {
			richTextEditor = new RichTextEditor(this);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

		@Test
		public void test01_RenamePage () {
			info("Test 01: Rename Page");
			String wiki1 = "wiki1" + getRandomNumber();
			String wiki2 = "wiki2" + getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Rename Page
		 *Step Description: 
			- Open an existing page by clicking on page name in navigation tree.
			Rename page by one of two ways
			1.
			- Double-click on page name in toolbar
			- Page's title field is enable and allow edit. 
			- Rename page and hit Enter key
			2.
			- Click Edit icon to edit page
			- Fill new title
			- Click Save

		 *Input Data: 

		 *Expected Outcome: 
			Selected Page is renamed and new name is displayed in toolbar and navigation tree*/

			homePagePlatform.goToWiki();
			wikiHomePage.goToAddBlankPage();
			richTextEditor.addSimplePage(wiki1, wiki1);
			wikiManagement.saveAddPage();
			$(byText(wiki1)).should(Condition.exist);

			info("Double click on the title of the page");
			wikiHomePage.selectAPage(wiki1);
			wikiManagement.renamePageByDoubleClick(wiki1, wiki2);
			$(byText(wiki2)).should(Condition.exist);

			info("Delete the page");
			homePagePlatform.goToWiki();
			wikiHomePage.deleteWiki(wiki2);

	}
}