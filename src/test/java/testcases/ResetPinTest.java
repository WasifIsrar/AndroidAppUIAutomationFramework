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

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import basePage.BasePage;
import helper.AppPathManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import locators.ContactLocators;
import locators.LoginLocators;
import locators.ResetPasswordLocators;

public class ResetPinTest {
	
	public AndroidDriver driver;
	private ResetPasswordLocators resetPasswordPage;
	private LoginLocators loginPage;
	private ContactLocators chatsPage;
	private String xID="...";
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
	
	@Test(priority=1)
	public void resetPinByQuestions() {
		BasePage basePage=new BasePage(driver);
		basePage.waitForWelcomeScreen();
		basePage.gotoLogin();
		resetPasswordPage=new ResetPasswordLocators(driver);
		resetPasswordPage.resetPin("...");
		loginPage=resetPasswordPage.resetPinQuestions("2233".toCharArray(),"My Answer");
		try {
			updatePin("2233");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			getData();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		loginPage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(xID,pinArray,password);
		Assert.assertTrue(chatsPage.getxID(xID));
	}
	
	@Test(priority=2)
	public void resetPinByPhrase() {
		BasePage basePage=new BasePage(driver);
		basePage.waitForWelcomeScreen();
		basePage.gotoLogin();
		resetPasswordPage=new ResetPasswordLocators(driver);
		resetPasswordPage.resetPin("...");
		loginPage=resetPasswordPage.resetPinPhrase("1234".toCharArray(),"My Phrase");
		try {
			updatePin("1234");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			getData();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		loginPage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(xID,pinArray,password);
		Assert.assertTrue(chatsPage.getxID(xID));
	}
	
	@Test(priority=3)
	public void resetPinByPassword() {
		BasePage basePage=new BasePage(driver);
		basePage.waitForWelcomeScreen();
		basePage.gotoLogin();
		resetPasswordPage=new ResetPasswordLocators(driver);
		resetPasswordPage.resetPin("...");
		loginPage=resetPasswordPage.resetPinPassword("2233".toCharArray(),password);
		try {
			updatePin("2233");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			getData();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		loginPage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(xID,pinArray,password);
		Assert.assertTrue(chatsPage.getxID(xID));
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
	
	public void updatePin(String new_pin) throws StreamWriteException, DatabindException, IOException {
		HashMap<String, String> data=new HashMap<String,String>();
		try {
			data = getJsonDatatoMap(System.getProperty("user.dir")+"//src//test//java//data//pin.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.put("pin", new_pin);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File(System.getProperty("user.dir")+"//src//test//java//data//pin.json"), data);
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown(ITestResult result) {
		if(driver!=null) {
		driver.quit();
		System.out.println("Finished Test: " + result.getMethod().getMethodName());
		}
	}
}
