package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;

public class ForgotPinTest extends BaseTest{
	
	@Test
	public void forgotPinOnOpeningApp() {
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		boolean isPinChanged=chatsPage.pinForgot(password,"...".toCharArray());
		try {
			updatePin("...");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		Assert.assertTrue(isPinChanged);
	}
}
