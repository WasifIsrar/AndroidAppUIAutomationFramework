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
import locators.ContactLocators;
import locators.LoginLocators;
import locators.SingleChatLocators;

public class ResendMessageTest{
	public AndroidDriver driver;
	private AndroidDriver driver1;
	private LoginLocators loginPage;
	private ContactLocators chatsPage1;
	private ContactLocators chatsPage2;
	private AndroidDriver driver2;
	private SingleChatLocators singleChatLocators1;
	private SingleChatLocators singleChatLocators2;
	private final String user2="782 142 618";
	private String password;
	private char[] pinArray;
	
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
		driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		try {
			getData();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
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
		driver=driver1;
		System.out.println("Starting test: " +method.getName());
	}
	
	@Test
	public void resendMessage() {
		BasePage basePage=new BasePage(driver1);
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage1=loginPage.verifyChatPage("...",pinArray,password);
		String txt="Text";
		singleChatLocators1=chatsPage1.openChat(user2);
		singleChatLocators1.waitForSentStatus(txt);
		driver=driver2;
		basePage=new BasePage(driver2);
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage2=loginPage.verifyChatPage(user2,"1234".toCharArray(),"Aa1");
		driver=driver1;
		boolean isSent=singleChatLocators1.resend();
		driver=driver2;
		singleChatLocators2=chatsPage2.openReceivedChat();
		Assert.assertTrue(isSent&&singleChatLocators2.checkTextMsg(txt));
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
