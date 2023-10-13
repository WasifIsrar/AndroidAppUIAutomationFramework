package testcases;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
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
import locators.SettingsLocators;


public class AddRecoveryTest {
	
	public AndroidDriver driver;
	private LoginLocators loginPage;
	private ContactLocators chatsPage;
	private String ID;
	
	@BeforeMethod
	public void configureAppium(Method method){
		UiAutomator2Options options=new UiAutomator2Options();
		options.setCapability("udid", "emulator-5554");
		options.setApp(AppPathManager.getInstance().getAppPath());
		options.setCapability("autoGrantPermissions", true);
		options.setSystemPort(8300);
		try {
		driver=new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		try {
		getData();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		System.out.println("Starting test: " + method.getName());
	}
	
	@Test
	public void addSecurityQuestions() {
		BasePage basePage=new BasePage(driver);
		basePage.waitForWelcomeScreen();
		loginPage=basePage.gotoLogin();
		chatsPage=loginPage.verifyChatPage(ID,"...".toCharArray(),"...");
		SettingsLocators settingsLocators=chatsPage.gotoNewSettingsMenu();
		Assert.assertTrue(settingsLocators.setQuestions("My Answer"));
	}
	
	public void getData() throws IOException{
		HashMap<String,String> data=getJsonDatatoMap(System.getProperty("user.dir")+"//src//test//java//data//number.json");
		ID=data.get("number");
	}
	public HashMap<String, String> getJsonDatatoMap(String filePath) throws IOException {
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		HashMap<String,String> data=mapper.readValue(jsonContent, new TypeReference<HashMap<String,String>>(){
		});
		return data;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown(ITestResult result) {
		if(driver!=null) {
		driver.quit();
		System.out.println("Finished Test: " + result.getMethod().getMethodName());
		}
	}
}
