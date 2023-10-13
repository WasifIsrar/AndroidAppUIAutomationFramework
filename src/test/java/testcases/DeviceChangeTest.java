package testcases;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import basePage.BasePage;
import helper.AppPathManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import locators.ContactLocators;
import locators.LoginLocators;

public class DeviceChangeTest {
	public AndroidDriver driver;
	private AndroidDriver driver1;
	private BasePage basePage;
	private LoginLocators loginPage;
	private ContactLocators chatsPage1;
	private ContactLocators chatsPage2;
	private AndroidDriver driver2;
	private String user="...";
	
	@BeforeMethod
	public void setup(Method method) {
		UiAutomator2Options options1=new UiAutomator2Options();
		options1.setNewCommandTimeout(Duration.ofSeconds(90));
		options1.setApp(AppPathManager.getInstance().getAppPath());
		options1.setCapability("autoGrantPermissions", true);
		options1.setCapability("udid", "emulator-5554");
		options1.setSystemPort(8300);
		try {
			driver1=new AndroidDriver(new URL("http://127.0.0.1:4723"),options1);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver=driver1;
		driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		basePage=new BasePage(driver1);
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage1=loginPage.verifyLogin(user,"...".toCharArray(),"...");
		UiAutomator2Options options2=new UiAutomator2Options();
		options2.setNewCommandTimeout(Duration.ofSeconds(90));
		options2.setApp(AppPathManager.getInstance().getAppPath());
		options2.setCapability("autoGrantPermissions", true);
		options2.setCapability("udid", "emulator-5556");
		options2.setSystemPort(9000);
		try {
			driver2=new AndroidDriver(new URL("http://127.0.0.1:4725"),options2);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver=driver2;
		driver2.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		System.out.println("Starting test: " +method.getName());
	}
	
	@Test
	public void changeDevice() {
		basePage=new BasePage(driver2);
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage2=loginPage.verifyLogin(user,"...".toCharArray(),"...");
		boolean isLoggedIn=chatsPage2.getxID(user);
		driver=driver1;
		Assert.assertTrue(isLoggedIn&&chatsPage1.isLoggedOut());
	}
	
	@Test
	public void changeDeviceWhileInBackground() {
		driver=driver1;
		chatsPage1.gotoBackground();
		driver=driver2;
		basePage=new BasePage(driver2);
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage2=loginPage.verifyLogin(user,"...".toCharArray(),"...");
		boolean isLoggedIn=chatsPage2.getxID(user);
		driver=driver1;
		Assert.assertTrue(isLoggedIn&&chatsPage1.openAppAndWaitForLogout());
	}
	
	@AfterMethod(alwaysRun=true)
	public void afterChat(ITestResult result) {
		boolean driver1Quit = false;
		boolean driver2Quit = false;
		if(driver1!=null) {
		driver1.quit();
		driver1Quit=true;
		}
		if(driver2!=null) {
		driver2.quit();
		driver2Quit=true;
		}	
		if(driver1Quit&&driver2Quit) {
		System.out.println("Finished Test: " + result.getMethod().getMethodName());
		}
	}
}
