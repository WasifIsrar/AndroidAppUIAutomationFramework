package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import locators.SingleChatLocators;

public class ChatOptionsTest extends BaseTest{
	private SingleChatLocators singleChatLocators;
	private String user2="...";
	
	@Test
	public void selectMsg() {
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		String text="Text";
		singleChatLocators=chatsPage.openChat(user2);
		singleChatLocators.waitForSentStatus(text);
		Assert.assertTrue(singleChatLocators.isMsgSelected(text));
	}
	
	@Test
	public void eraseMessageFromHere() {
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		String text="Text";
		singleChatLocators=chatsPage.openChat(user2);
		singleChatLocators.waitForSentStatus(text);
		singleChatLocators.isMsgSelected(text);
		Assert.assertTrue(singleChatLocators.eraseFromHere());
	}
	
	@Test
	public void eraseChatFromHere() {
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		singleChatLocators=chatsPage.openChat(user2);
		singleChatLocators.waitForSentStatus("Text");
		Assert.assertTrue(singleChatLocators.eraseChatHere());
	}
}
