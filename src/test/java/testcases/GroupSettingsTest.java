package testcases;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import basePage.BasePage;
import helper.AppPathManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import locators.ContactLocators;
import locators.GroupLocators;
import locators.LoginLocators;

public class GroupSettingsTest {
	public AndroidDriver driver;
	private AndroidDriver driver1;
	private LoginLocators loginPage;
	private ContactLocators chatsPage1;
	private ContactLocators chatsPage2;
	GroupLocators groupLocators1;
	GroupLocators groupLocators2;
	private AndroidDriver driver2;
	private final String user2="...";
	public String password;
	private char[] pinArray;
	
	@BeforeMethod(alwaysRun=true)
	public void configure(Method method) {
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
		driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		try {
			getData();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		UiAutomator2Options options2=new UiAutomator2Options();
		options1.setNewCommandTimeout(Duration.ofSeconds(90));
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
		driver2.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver=driver1;
		System.out.println("Starting test: " +method.getName());
	}
	
	@Test
	public void joinGroupFromPush() {
		BasePage basePage=new BasePage(driver1);
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage1=loginPage.verifyChatPage("...",pinArray,password);
		driver=driver2;
		basePage=new BasePage(driver2);
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage2=loginPage.verifyChatPage(user2,"...".toCharArray(),"...");
		driver=driver1;
		chatsPage1.contactAndBack();
		driver=driver2;
		groupLocators2=new GroupLocators(driver);
		groupLocators2.gotoBackground();
		driver=driver1;
		groupLocators1=chatsPage1.clickGroup();
		groupLocators1.createGroup("Group");
		driver=driver2;
		try {
		groupLocators2.openGroupPush("...".toCharArray());
		}
		catch(Exception e) {
			System.out.println("Push not received");
			groupLocators2.closeNotification();
		}
		groupLocators2.joinGroup();
		driver=driver1;
		groupLocators1=chatsPage1.openGroupChat();
		Assert.assertTrue(groupLocators1.verifyJoined());
	}
	
	@Test
	public void removeMember() {
		BasePage basePage=new BasePage(driver1);
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage1=loginPage.verifyChatPage("...",pinArray,password);
		driver=driver2;
		basePage=new BasePage(driver2);
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage2=loginPage.verifyChatPage(user2,"...".toCharArray(),"...");
		chatsPage2.gotoBackground();
		driver=driver1;
		groupLocators1=chatsPage1.openGroupChat();
		boolean isRemovedOnAdmin=groupLocators1.verifyIsRemoved();
		driver=driver2;
		groupLocators2=new GroupLocators(driver2);
		try {
		groupLocators2.openRemovedPush("...".toCharArray());
		}
		catch(Exception e) {
			System.out.println("Push not received");
			groupLocators2.closeNotification();
		}
		driver=driver1;
		groupLocators1.deleteAfterRemove();
		driver=driver2;
		Assert.assertTrue(isRemovedOnAdmin&&groupLocators2.isRemoved());
	}
	

	/*	driver=driver1;
		groupLocators1.addAgain();
		groupLocators2=chatsPage2.openAndJoin();
		groupLocators2.joinGroup();
		groupLocators2.clickBackBtn();
		driver=driver1;
		Assert.assertTrue(groupLocators1.verifyAddedAgain());
		driver=driver2;
		groupLocators2=chatsPage2.openGroupChat();
		boolean left=groupLocators2.leaveGroup();
		driver=driver1;
		Assert.assertTrue(left&&groupLocators1.verifyLeft());
		groupLocators1.verifyGroupDeleted();
		boolean isDeletedonAdmin=groupLocators1.isGroupDeleted();
		driver=driver2;
		Assert.assertTrue(isDeletedonAdmin&&groupLocators2.isGroupDeleted());
	}*/
	
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
	
	public HashMap<String, String> getJsonDatatoMap(String filePath) throws IOException {
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		HashMap<String,String> data=mapper.readValue(jsonContent, new TypeReference<HashMap<String,String>>(){
		});
		return data;
	}

	public void getData() throws IOException{
		HashMap<String,String> data=getJsonDatatoMap(System.getProperty("user.dir")+"//src//test//java//data//credentials.json");
		password=data.get("password");
		HashMap<String,String> pin=getJsonDatatoMap(System.getProperty("user.dir")+"//src//test//java//data//pin.json");
		pinArray=pin.get("pin").toCharArray();
		
	}
}
