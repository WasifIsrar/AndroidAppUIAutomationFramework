package testcases;



import org.testng.Assert;
import org.testng.annotations.Test;
import baseTest.BaseTest;
import locators.SettingsLocators;

public class PrivacyTest extends BaseTest{
	SettingsLocators settingsPage;
	
	@Test(priority=1)
	public void block() {
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		Assert.assertEquals("User blocked successfully", chatsPage.verifyBlocked());
		settingsPage=chatsPage.gotoSettingsMenu();
		Assert.assertTrue(settingsPage.isBlocked());
	}
	
	@Test(dependsOnMethods= {"block"})
	public void unblock() {
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		settingsPage=chatsPage.gotoSettingsMenu();
		Assert.assertTrue(settingsPage.unblock());
		Assert.assertTrue(chatsPage.verifyUnBlocked("..."));
	}
	
	@Test(priority=2)
	public void EditQuestions() {
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		settingsPage=chatsPage.gotoSettingsMenu();
		settingsPage.addQuestions(password, "My Answer",pinArray);
	}
	
	@Test(priority=3)
	public void EditPhrase() {
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		settingsPage=chatsPage.gotoSettingsMenu();
		settingsPage.addPhrase("My Phrase",pinArray);
	}
}
