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

public class GroupTest{
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
	
	@Test(priority=1)
	public void createAndJoinGroup() {
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
		groupLocators1=chatsPage1.clickGroup();
		groupLocators1.createGroup("Group");
		driver=driver2;
		groupLocators2=chatsPage2.openAndJoin();
		groupLocators2.joinGroup();
		driver=driver1;
		groupLocators1=chatsPage1.openGroupChat();
		Assert.assertTrue(groupLocators1.verifyJoined());
	}
	
	@Test(dependsOnMethods={"createAndJoinGroup"})
	public void sendTextMessageinGroup() {
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
		String text="txt";
		groupLocators1=chatsPage1.openGroupChat();
		groupLocators1.sendText(text);
		driver=driver2;
		groupLocators2=chatsPage2.openGroupChat();
		Assert.assertTrue(groupLocators2.verifyMsgReceived(text));
	}
	
	@Test(dependsOnMethods={"createAndJoinGroup"})
	public void sendCapturedImageinGroup() {
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
		groupLocators1=chatsPage1.openGroupChat();
		groupLocators1.captureImage();
		driver=driver2;
		groupLocators2=chatsPage2.openGroupChat();
		Assert.assertTrue(groupLocators2.verifyMediaReceived());
		Assert.assertTrue(groupLocators2.isMediaDownloaded());
	}
	
	@Test(dependsOnMethods={"createAndJoinGroup"})
	public void sendCapturedVideoinGroup() {
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
		chatsPage1.killAndOpen(pinArray);
		groupLocators1=chatsPage1.openGroupChat();
		groupLocators1.captureVideo();
		driver=driver2;
		groupLocators2=chatsPage2.openGroupChat();
		Assert.assertTrue(groupLocators2.verifyMediaReceived());
		Assert.assertTrue(groupLocators2.isMediaDownloaded());
	}
	
	@Test(dependsOnMethods={"createAndJoinGroup"})
	public void sendBrowsedImageinGroup() {
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
		groupLocators1=chatsPage1.openGroupChat();
		groupLocators1.browseImage();
		driver=driver2;
		groupLocators2=chatsPage2.openGroupChat();
		Assert.assertTrue(groupLocators2.verifyMediaReceived());
		Assert.assertTrue(groupLocators2.isMediaDownloaded());
	}
	
	@Test(dependsOnMethods={"sendBrowsedImageinGroup"})
	public void sendMultiImageinGroup() {
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
		groupLocators1=chatsPage1.openGroupChat();
		groupLocators1.browseMultiImage();
		driver=driver2;
		groupLocators2=new GroupLocators(driver2);
		Assert.assertTrue(groupLocators2.multiImgCount());
	}
	
	@Test(dependsOnMethods={"sendMultiImageinGroup"})
	public void sendVideoinGroup() {
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
		chatsPage1.killAndOpen(pinArray);
		groupLocators1=chatsPage1.openGroupChat();
		groupLocators1.browseVideo();
		driver=driver2;
		groupLocators2=chatsPage2.openGroupChat();
		Assert.assertTrue(groupLocators2.verifyMediaReceived());
		Assert.assertTrue(groupLocators2.isMediaDownloaded());
	}
	
	@Test(dependsOnMethods={"createAndJoinGroup"})
	public void sendOfflineMessagesinGroup() {
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
		String offlineText="Offline";
		driver=driver2;
		groupLocators2=new GroupLocators(driver2);
		groupLocators2.backgroundAndOffline();
		driver=driver1;
		groupLocators1=chatsPage1.openGroupChat();
		groupLocators1.sendText(offlineText);
		driver=driver2;
		groupLocators2.Online();
		try {
		groupLocators2.openPush("...".toCharArray());
		}
		catch(Exception e) {
			System.out.println("Push Not Received");
			groupLocators2.closeNotification();
		}
		groupLocators2=new GroupLocators(driver2);
		Assert.assertTrue(groupLocators2.verifyMsgReceived(offlineText));
	}
	
	@Test(priority=2)
	public void deleteGroup() {
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
		groupLocators1=chatsPage1.openGroupChat();
		groupLocators1.verifyGroupDeleted();
		boolean isDeletedonAdmin=groupLocators1.isGroupDeleted();
		driver=driver2;
		groupLocators2=new GroupLocators(driver2);
		boolean isDeletedonMember=groupLocators2.isGroupDeleted();
		Assert.assertTrue(isDeletedonAdmin&&isDeletedonMember);
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
