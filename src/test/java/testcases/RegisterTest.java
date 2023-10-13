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
import locators.RegisterLocators;


public class RegisterTest{
	public AndroidDriver driver;
	public BasePage basePage;
	public RegisterLocators registerPage;

	
	@BeforeMethod
	public void setup(Method method) {
		UiAutomator2Options options=new UiAutomator2Options();
		options.setCapability("udid","emulator-5554");
		options.setApp(AppPathManager.getInstance().getAppPath());
		options.setCapability("autoGrantPermissions", true);
		options.setSystemPort(8300);
		try {
			driver=new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("Starting test: " + method.getName());
	}
	@Test
	public void Register() throws MalformedURLException {
		basePage=new BasePage(driver);
		basePage.waitForWelcomeScreen();
		registerPage=basePage.gotoRegister();
		String number=registerPage.getNumber("Automator1","1234".toCharArray());
		try {
		addNumber(number);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		Assert.assertTrue(registerPage.verifyRegister());
	}
	
	@Test
	public void invalidRegister(){
		basePage=new BasePage(driver);
		basePage.waitForWelcomeScreen();
		registerPage=basePage.gotoRegister();
		Assert.assertTrue(registerPage.verifyInvalidRegister("..."));
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown(ITestResult result) {
		if(driver!=null) {
			driver.quit();
			System.out.println("Finished Test: " + result.getMethod().getMethodName());
			}
	}
	
	public void addNumber(String number) throws StreamWriteException, DatabindException, IOException {
		HashMap<String, String> data=new HashMap<String,String>();
		try {
			data = getJsonDatatoMap(System.getProperty("user.dir")+"//src//test//java//data//number.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.put("number", number);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File(System.getProperty("user.dir")+"//src//test//java//data//number.json"), data);
	}
	public HashMap<String, String> getJsonDatatoMap(String filePath) throws IOException {
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		HashMap<String,String> data=mapper.readValue(jsonContent, new TypeReference<HashMap<String,String>>(){
		});
		return data;
	}
}