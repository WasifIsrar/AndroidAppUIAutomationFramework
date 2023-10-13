package testcases;


import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import locators.SettingsLocators;

public class EditProfileTest extends BaseTest{
	private SettingsLocators settingsLocators;
	
	@Test
	public void editProfile() {
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		settingsLocators=chatsPage.gotoSettingsMenu();
		Assert.assertTrue(settingsLocators.verifyProfileEdited("name"));
	}
	
	@Test
	public void invalidProfileName() {
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		settingsLocators=chatsPage.gotoSettingsMenu();
		Assert.assertTrue(settingsLocators.verifyInvalidName("..."));
	}
}
