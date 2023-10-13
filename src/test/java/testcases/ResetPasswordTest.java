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
import locators.LoginLocators;
import locators.ResetPasswordLocators;



public class ResetPasswordTest{
	
	private LoginLocators loginPage;
	public AndroidDriver driver;
	private String xID="...";
	private ResetPasswordLocators resetPassword;
	private String password;
	private char[] pinArray;
	
	@BeforeMethod
	public void configure(Method method) throws MalformedURLException {
		UiAutomator2Options options=new UiAutomator2Options();
		options.setCapability("udid","emulator-5554");
		options.setApp(AppPathManager.getInstance().getAppPath());
		options.setCapability("autoGrantPermissions", true);
		options.setSystemPort(8300);
		driver=new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		try {
			getData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Starting test: " + method.getName());
	}
	
	@Test
	public void resetByQs() {
		BasePage basePage=new BasePage(driver);
		basePage.waitForWelcomeScreen();
		basePage.gotoLogin();
		resetPassword=new ResetPasswordLocators(driver);
		loginPage=resetPassword.resetbyQuestions(xID,pinArray,password,"My Answer");
		ContactLocators chatsPage=loginPage.verifyChatPage(xID,pinArray,password);
		Assert.assertTrue(chatsPage.getxID(xID));
	}
	
	@Test
	public void resetByPhrase() {
		BasePage basePage=new BasePage(driver);
		basePage.waitForWelcomeScreen();
		basePage.gotoLogin();
		resetPassword=new ResetPasswordLocators(driver);
		loginPage=resetPassword.resetbyPhrase(xID,pinArray,password,"My Phrase");
		ContactLocators chatsPage=loginPage.verifyChatPage(xID,pinArray,password);
		Assert.assertTrue(chatsPage.getxID(xID));
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		driver.quit();
		System.out.println("Finished Test: " + result.getMethod().getMethodName());
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
