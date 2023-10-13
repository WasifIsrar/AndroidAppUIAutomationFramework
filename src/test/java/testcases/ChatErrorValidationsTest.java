package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import baseTest.BaseTest;
import locators.SingleChatLocators;

public class ChatErrorValidationsTest extends BaseTest{
	
	private SingleChatLocators singleChatLocators;
	private String user2="...";
	
	@Test(priority=2)
	public void browseInvalidVideo(){
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		chatsPage.killAndOpen(pinArray);
		singleChatLocators=chatsPage.openChat(user2);
		Assert.assertTrue(singleChatLocators.browseInvalidVid());
	}
	
	@Test(priority=1)
	public void selectMoreThan4Images(){
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		singleChatLocators=chatsPage.openChat(user2);
		Assert.assertEquals(singleChatLocators.browseInvalidImages(),"Maximum 4 images can be sent");
	}
}
