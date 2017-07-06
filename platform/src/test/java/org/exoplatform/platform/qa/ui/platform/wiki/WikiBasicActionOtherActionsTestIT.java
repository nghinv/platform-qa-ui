package org.exoplatform.platform.qa.ui.platform.wiki;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.wiki.pageobject.RichTextEditor;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiHomePage;
import org.exoplatform.platform.qa.ui.wiki.pageobject.WikiManagement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import org.junit.jupiter.api.Test;


@Tag("wiki")
@Tag("test")

public class WikiBasicActionOtherActionsTestIT extends Base {

	HomePagePlatform homePagePlatform;
	WikiHomePage wikiHomePage;
	ManageLogInOut manageLogInOut;
	WikiManagement wikiManagement;
	RichTextEditor richTextEditor;
	NavigationToolbar navigationToolbar;
	SpaceManagement spaceManagement;
	SpaceHomePage spaceHomePage;

	@BeforeEach
	public void setupBeforeMethod() {
		info("Start setUpBeforeMethod");
		homePagePlatform = new HomePagePlatform(this);
		wikiHomePage = new WikiHomePage(this);
		manageLogInOut = new ManageLogInOut(this);
		wikiManagement = new WikiManagement(this);
		navigationToolbar = new NavigationToolbar(this);
		spaceManagement = new SpaceManagement(this);
		spaceHomePage = new SpaceHomePage(this);
		manageLogInOut.signInCas("john", "gtngtn");


		try {
			richTextEditor = new RichTextEditor(this);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@AfterEach
	public void signout(){manageLogInOut.signOut();}

	/**
	 * <li> Case ID:122810.</li>
	 * <li> Test Case Name: Move Page.</li>
	 * <li> Pre-Condition: </li>
	 * <li> Post-Condition: </li>
	 * issue WIKI-976, wrong address in the mail notification
	 */
	@Test
	public void test01_MovePage() {

		info("Test 1: Move Page");

		String title1 = "title1" + getRandomNumber();
		String title2 = "title2" + getRandomNumber();

		info("Create first new wiki pages");
		homePagePlatform.goToWiki();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(title1, title1);
		wikiManagement.saveAddPage();
		$(byText(title1)).should(Condition.exist);

		info("Create second new wiki pages");
		homePagePlatform.goToWiki();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(title2, title2);
		wikiManagement.saveAddPage();
		$(byText(title2)).should(Condition.exist);

		info("Move page 1 to page 2");
		wikiManagement.movePage(title1, title2);
		wikiHomePage.goToPageInformation(title2);
		$(byClassName("uiPageInfoHierarchy")).find(byText(title1)).should(Condition.exist);
		homePagePlatform.goToWiki();
		wikiHomePage.deleteWiki(title2);

	}

	@Test
	public void test02_MovePage_Intranet_MyWiki() {

		String title1 = "title1" + getRandomNumber();

		homePagePlatform.goToWiki();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(title1, title1);
		wikiManagement.saveAddPage();
		$(byText(title1)).should(Condition.exist);

		info("Move page to Mywiki");
		wikiManagement.selectSpaceDestination("My Wiki");
		navigationToolbar.goToMyWiki();
		$(byClassName("uiTreeExplorer")).find(byText(title1)).should(Condition.exist);
		wikiHomePage.deleteWiki(title1);

	}


	@Test
	public void test03_MovePage_MyWiki_Intranet() {

		String title1 = "title1" + getRandomNumber();

		navigationToolbar.goToMyWiki();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(title1, title1);
		wikiManagement.saveAddPage();
		$(byText(title1)).should(Condition.exist);

		info("Move page to Intranet");
		wikiManagement.selectSpaceDestination("Intranet");
		homePagePlatform.goToWiki();
		$(byClassName("uiTreeExplorer")).find(byText(title1)).should(Condition.exist);
		wikiHomePage.deleteWiki(title1);
	}


	@Test
	public void test04_MovePage_MyWiki_Space() {

		info("Create a space");
		String space = "space" + getRandomNumber();


		homePagePlatform.goToAllSpace();
		spaceManagement.addNewSpaceSimple(space, space, 6000);

		info("Create a wiki page");
		String title1 = "title1" + getRandomNumber();
		navigationToolbar.goToMyWiki();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(title1, title1);
		wikiManagement.saveAddPage();
		$(byText(title1)).should(Condition.exist);

		info("Move page to Space");
		wikiManagement.selectSpaceDestination(space);
		homePagePlatform.goToSpecificSpace(space);
		spaceHomePage.goToWikiTab();
		$(byClassName("uiTreeExplorer")).find(byText(title1)).should(Condition.exist);
		homePagePlatform.goToHomePage();
		homePagePlatform.goToAllSpace();
		spaceManagement.deleteSpace(space, false);

	}

	@Test
	public void test05_MovePage_Space_MyWiki() {

		info("Create a space");
		String space = "space" + getRandomNumber();

		homePagePlatform.goToAllSpace();
		spaceManagement.addNewSpaceSimple(space, space, 6000);

		info("Create a wiki page in space");
		String title1 = "title1" + getRandomNumber();
		spaceManagement.goToWikiTab();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(title1, title1);
		wikiManagement.saveAddPage();
		$(byText(title1)).should(Condition.exist);

		info("Move page to MyWiki");
		wikiManagement.selectSpaceDestination("My Wiki");
		navigationToolbar.goToMyWiki();
		$(byClassName("uiTreeExplorer")).find(byText(title1)).should(Condition.exist);
		wikiHomePage.deleteWiki(title1);
		homePagePlatform.goToHomePage();
		homePagePlatform.goToAllSpace();
		spaceManagement.deleteSpace(space, false);

	}

	@Test
	public void test06_MovePage_Intranet_Space() {

		info("Create a space");
		String space = "space" + getRandomNumber();
		homePagePlatform.goToAllSpace();
		spaceManagement.addNewSpaceSimple(space, space, 6000);

		info("Create a wiki page ");
		String title1 = "title1" + getRandomNumber();
		homePagePlatform.goToWiki();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(title1, title1);
		wikiManagement.saveAddPage();
		$(byText(title1)).should(Condition.exist);

		info("Move page to Space");
		wikiManagement.selectSpaceDestination(space);
		homePagePlatform.goToSpecificSpace(space);
		spaceHomePage.goToWikiTab();
		$(byClassName("uiTreeExplorer")).find(byText(title1)).should(Condition.exist);
		homePagePlatform.goToHomePage();
		homePagePlatform.goToAllSpace();
		spaceManagement.deleteSpace(space, false);

	}

	@Test
	public void test07_MovePage_Space_Intranet() {

		info("Create a space");
		String space = "space" + getRandomNumber();

		homePagePlatform.goToAllSpace();
		spaceManagement.addNewSpaceSimple(space, space, 6000);


		info("Create a wiki page in space");
		String title1 = "title1" + getRandomNumber();
		spaceManagement.goToWikiTab();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(title1, title1);
		wikiManagement.saveAddPage();
		$(byText(title1)).should(Condition.exist);

		info("Move page to Intranet");
		wikiManagement.selectSpaceDestination("Intranet");
		homePagePlatform.goToWiki();
		$(byClassName("uiTreeExplorer")).find(byText(title1)).should(Condition.exist);
		wikiHomePage.deleteWiki(title1);
		homePagePlatform.goToHomePage();
		homePagePlatform.goToAllSpace();
		spaceManagement.deleteSpace(space, false);
	}


	@Test
	public void test08_MovePage_Space2_Space1() {

		info("Create a space");
		String space1 = "space" + getRandomNumber();
		String space2 = "space" + getRandomNumber();

		homePagePlatform.goToAllSpace();
		spaceManagement.addNewSpaceSimple(space1, space1, 6000);
		homePagePlatform.goToAllSpace();
		spaceManagement.addNewSpaceSimple(space2, space2, 6000);

		info("Create a wiki page in space2");
		String title1 = "title1" + getRandomNumber();
		spaceManagement.goToWikiTab();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(title1, title1);
		wikiManagement.saveAddPage();
		$(byText(title1)).should(Condition.exist);

		info("Move page to Space1");

		wikiManagement.selectSpaceDestination(space1);
		homePagePlatform.goToSpecificSpace(space1);
		spaceHomePage.goToWikiTab();
		$(byClassName("uiTreeExplorer")).find(byText(title1)).should(Condition.exist);
		homePagePlatform.goToHomePage();
		homePagePlatform.goToAllSpace();
		spaceManagement.deleteSpace(space1, false);
		spaceManagement.deleteSpace(space2, false);

	}

	@Test
	public  void test09_MovePageInSameSpace() {
		info("Test 09 Move page in same space");

		String space = "space" + getRandomNumber();

		String wiki1 =  "wiki1" + getRandomNumber();
		String wiki2 = "wiki2" + getRandomNumber();


		info("Create space 1 and wiki page ");
		homePagePlatform.goToMySpaces();
		spaceManagement.addNewSpaceSimple(space,space);

		info("Add new wiki page 1 for space 1");
		spaceHomePage.goToWikiTab();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(wiki1,wiki1);
		wikiManagement.saveAddPage();
		$(byText(wiki1)).should(Condition.exist);

		info("Add new wiki page 2 for space ");
		wikiHomePage.goToHomeWikiPage();
		wikiHomePage.goToAddBlankPage();
		richTextEditor.addSimplePage(wiki2,wiki2);
		wikiManagement.saveAddPage();
		$(byText(wiki2)).should(Condition.exist);

		info("Move wiki page 1 to wiki page 2");
		homePagePlatform.goToSpecificSpace(space);
		spaceHomePage.goToWikiTab();
		wikiManagement.movePage(wiki1, wiki2);
		homePagePlatform.goToHomePage();
		homePagePlatform.goToAllSpace();
		spaceManagement.deleteSpace(space, false);

	}
































}

