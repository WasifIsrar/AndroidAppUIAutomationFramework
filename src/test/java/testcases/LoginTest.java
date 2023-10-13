package testcases;



import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;


public class LoginTest extends BaseTest{

	@Test
	public void login() {
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		Assert.assertTrue(chatsPage.getxID(ID));
	}
  }