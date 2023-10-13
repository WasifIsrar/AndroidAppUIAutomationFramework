package testcases;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

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
import io.appium.java_client.service.local.AppiumDriverLocalService;
import locators.ContactLocators;
import locators.LoginLocators;
import locators.SingleChatLocators;


public class ChatTest{
	
	public AndroidDriver driver;
	private AndroidDriver driver1;
	private LoginLocators loginPage;
	private ContactLocators chatsPage1;
	private ContactLocators chatsPage2;
	private AndroidDriver driver2;
	private SingleChatLocators singleChatLocators1;
	private SingleChatLocators singleChatLocators2;
	private final String user2="...";
	private String password;
	private char[] pinArray;
	
	@BeforeMethod(alwaysRun=true)
	public void BeforeChat(Method method) {
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
		driver2.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		try {
			getData();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		driver=driver1;
		System.out.println("Starting test: " +method.getName());
	}
	
	@Test(priority=1)
	public void textMsg(){
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
		String txtMsg="txt";
		singleChatLocators1=chatsPage1.openChat(user2);
		singleChatLocators1.sendText(txtMsg);
		driver=driver2;
		singleChatLocators2=chatsPage2.openReceivedChat();
		Assert.assertTrue(singleChatLocators2.checkTextMsg(txtMsg));		
	}
	
	@Test(priority=2)
	public void captureImage(){
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
		singleChatLocators1=chatsPage1.openChat(user2);
		singleChatLocators1.captureImg();
		driver=driver2;
		singleChatLocators2=chatsPage2.openReceivedChat();
		Assert.assertTrue(singleChatLocators2.checkMedia());
		Assert.assertTrue(singleChatLocators2.isMediaDownloaded());
	}
	
	@Test(priority=3)
	public void browseImage(){
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
		singleChatLocators1=chatsPage1.openChat(user2);
		singleChatLocators1.browseImg();
		driver=driver2;
		singleChatLocators2=chatsPage2.openReceivedChat();
		Assert.assertTrue(singleChatLocators2.checkMedia());
		Assert.assertTrue(singleChatLocators2.isMediaDownloaded());
	}
	
	@Test(priority=4)
	public void browseMultiImage(){
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
		singleChatLocators1=chatsPage1.openChat(user2);
		singleChatLocators1.browseImages();
		driver=driver2;
		Assert.assertTrue(chatsPage2.multiImgCount());
	}
	
	@Test(priority=5)
	public void browseValidVideo(){
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
		singleChatLocators1=chatsPage1.openChat(user2);
		singleChatLocators1.browseVid();
		driver=driver2;
		singleChatLocators2=chatsPage2.openReceivedChat();
		Assert.assertTrue(singleChatLocators2.checkMedia());
	}
	
	@Test(dependsOnMethods={"testcases.AddContactTest.addContact"})
	public void shareContact(){
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
		singleChatLocators1=chatsPage1.openChatOfFriend(user2);
		singleChatLocators1.contact();
		driver=driver2;
		singleChatLocators2=chatsPage2.openReceivedChat();
		Assert.assertTrue(singleChatLocators2.checkContact());
	}
	
	@Test(priority=6)
	public void captureVideo(){
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
		singleChatLocators1=chatsPage1.openChat(user2);
		singleChatLocators1.captureVid();
		driver=driver2;
		singleChatLocators2=chatsPage2.openReceivedChat();
		Assert.assertTrue(singleChatLocators2.checkMedia());
		Assert.assertTrue(singleChatLocators2.isMediaDownloaded());
	}
	
	@Test(priority=7)
	public void disappearingMsg() {
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
		String disText="Disappearing Text";
		singleChatLocators1=chatsPage1.openChat(user2);
		singleChatLocators1.disappearingMsg(disText);
		driver=driver2;
		singleChatLocators2=chatsPage2.openReceivedChat();
		singleChatLocators2.checkTextMsg(disText);
		singleChatLocators2.seeMessage();
		boolean hasDisappearedFromReceiver=singleChatLocators2.hasMessageDisappeared();
		driver=driver1;
		boolean hasDisappearedFromSender=singleChatLocators1.hasMessageDisappeared();
		Assert.assertTrue(hasDisappearedFromReceiver&&hasDisappearedFromSender);
	}
	
	@Test(priority=8)
	public void disappearingImage() {
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
		singleChatLocators1=chatsPage1.openChat(user2);
		singleChatLocators1.sendDisappearingImage();
		driver=driver2;
		singleChatLocators2=chatsPage2.openReceivedChat();
		singleChatLocators2.downloadDisappearingImage();
		boolean hasDisappearedFromReceiver=singleChatLocators2.hasImageDisappeared();
		driver=driver1;
		boolean hasDisappearedFromSender=singleChatLocators1.hasImageDisappeared();
		Assert.assertTrue(hasDisappearedFromSender&&hasDisappearedFromReceiver);
	}
	
	@Test(priority=9)
	public void offlineMessages() {
		BasePage basePage=new BasePage(driver1);
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage1=loginPage.verifyChatPage("...",pinArray,password);
		driver=driver2;
		basePage=new BasePage(driver2);
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage2=loginPage.verifyChatPage(user2,"...".toCharArray(),"...");
		String offlineMsg="Offline Message";
		SingleChatLocators singleChatLocators2=new SingleChatLocators(driver2);
		singleChatLocators2.backgroundAndOffline();
		driver=driver1;
		singleChatLocators1=chatsPage1.openChat(user2);
		singleChatLocators1.waitForSentStatus(offlineMsg);
		driver=driver2;
		singleChatLocators2.Online();
		try {
		singleChatLocators2.openPush("...".toCharArray());
		}
		catch(Exception e) {
			singleChatLocators2.closeNotification();
		}
		Assert.assertTrue(singleChatLocators2.checkTextMsg(offlineMsg));
	}
	
	@Test(priority=10)
	public void eraseMessageFromEverywhere() {
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
		String eraseMessage="Erased Message";
		singleChatLocators1=chatsPage1.openChat(user2);
		singleChatLocators1.sendText(eraseMessage);
		driver=driver2;
		singleChatLocators2=chatsPage2.openReceivedChat();
		singleChatLocators2.checkTextMsg(eraseMessage);
		driver=driver1;
		singleChatLocators1.isMsgSelected(eraseMessage);
		boolean isErasedFromSender=singleChatLocators1.eraseFromEverywhere();
		driver=driver2;
		Assert.assertTrue(isErasedFromSender&&singleChatLocators2.verifyErasedOnReceiver());
	}
	
	@Test(priority=11)
	public void eraseChatFromEverywhere() {
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
		String text="text";
		singleChatLocators1=chatsPage1.openChat(user2);
		singleChatLocators1.sendText(text);
		driver=driver2;
		singleChatLocators2=chatsPage2.openReceivedChat();
		singleChatLocators2.checkTextMsg(text);
		driver=driver1;
		boolean isErasedFromSender=singleChatLocators1.eraseChatEverywhere();
		driver=driver2;
		Assert.assertTrue(isErasedFromSender&&singleChatLocators2.verifyChatErasedFromReceiver());
	}
	
	@Test(priority=12)
	public void eraseChatAlert() {
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
		String text="text";
		singleChatLocators1=chatsPage1.openChat(user2);
		singleChatLocators1.sendText(text);
		driver=driver2;
		singleChatLocators2=chatsPage2.openReceivedChat();
		singleChatLocators2.checkTextMsg(text);
		singleChatLocators2.clickBackBtn();
		singleChatLocators2.gotoBackground();
		driver=driver1;
		boolean isErasedFromSender=singleChatLocators1.eraseChatEverywhere();
		driver=driver2;
		Assert.assertTrue(isErasedFromSender&&chatsPage2.verifyAlert("...".toCharArray()));
	}
	
	@Test(priority=13)
	public void sharePdf() {
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
		singleChatLocators1=chatsPage1.openChat(user2);
		singleChatLocators1.sendDocument();
		driver=driver2;
		singleChatLocators2=chatsPage2.openReceivedChat();
		Assert.assertTrue(singleChatLocators2.isDocumentReceived());
		Assert.assertTrue(singleChatLocators2.downloadDocument());
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
	
	public List<HashMap<String, String>> getListData(String filePath) throws IOException {
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
		});
		return data;
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
