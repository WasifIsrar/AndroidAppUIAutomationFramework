package testcases;



import org.testng.Assert;
import org.testng.annotations.Test;
import baseTest.BaseTest;
import locators.ContactLocators;
import locators.LoginLocators;
import locators.RegisterLocators;
import locators.SettingsLocators;

public class SignOutTest extends BaseTest{
	private SettingsLocators settingsPage;
	
	@Test
	public void signOut() {
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		settingsPage=chatsPage.gotoSettingsMenu();
		Assert.assertTrue(settingsPage.signout());	
	}
	
	@Test
	public void switchAccount() {
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		settingsPage=chatsPage.gotoSettingsMenu();
		RegisterLocators registerPage=settingsPage.switchAccount();
		LoginLocators loginPage=registerPage.gotoLogin();
		ContactLocators chatsPage=loginPage.verifyChatPage("...","1234".toCharArray(),"Aa1");
		Assert.assertTrue(chatsPage.getxID("..."));
	}
}
