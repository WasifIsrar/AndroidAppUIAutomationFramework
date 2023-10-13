package testcases;



import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import baseTest.BaseTest;
import locators.SettingsLocators;

public class SettingsTest extends BaseTest{
	
	private SettingsLocators settingsPage;
	
	@Test(priority=1)
	public void changePin() {
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		settingsPage=chatsPage.gotoSettingsMenu();
		String alert=settingsPage.verifyPin(pinArray,"2233".toCharArray());
		try {
			updatePin("2233");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(alert,"PIN changed successfully");
	}
	
	@Test(priority=2)
	public void changePassword() {
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		String new_password="Qa1";
		settingsPage=chatsPage.gotoSettingsMenu();
		String alert=settingsPage.verifyPassword("Aa1",new_password);
		try {
			updatePassword(new_password);
		} catch (StreamWriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(alert,"Password changed successfully");
	}
	
	@Test(priority=3)
	public void enableReverse() {
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		settingsPage=chatsPage.gotoSettingsMenu();
		Assert.assertTrue(settingsPage.reverseEnable());
	}
	
	@Test(dependsOnMethods= {"enableReverse"})
	public void reversePin() {
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		Assert.assertTrue(chatsPage.verifyReverse(pinArray));
	}
	
	@Test
	public void disablePinFromSettings() {
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,pinArray,password);
		settingsPage=chatsPage.gotoSettingsMenu();
		Assert.assertTrue(settingsPage.verifyPinDisabled());
	}
}
