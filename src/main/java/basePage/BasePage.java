package basePage;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.search.SubjectTerm;
import locators.LoginLocators;
import locators.RegisterLocators;

public class BasePage {

	public By xID_field=By.xpath("//android.widget.EditText[@text='Enter your xID']");
	public By welcomeScreen=By.xpath("//android.widget.TextView[@text='Create Account']");
	public By login_btn=By.xpath("//android.widget.TextView[@text='Login']");
	public By continueBtn=By.xpath("//android.widget.TextView[@text='Continue']");
	public By passwordScreen=By.xpath("//android.widget.TextView[@text=' Password']");
	public By securityAlert=By.xpath("//android.widget.TextView[@text='IMPORTANT']");
	public By confirmAlertBtn=By.xpath("//android.widget.TextView[@text='Confirm']");
	public AndroidDriver driver;
	public By settingsScreen=By.xpath("//android.widget.TextView[@text='Settings']");
	public By chat_icon=By.xpath("(//android.widget.TextView)[1]");
	public By dialpad_icon=By.xpath("//android.widget.TextView[@text='New Message']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup");
	public By searchId_field=By.xpath("//android.widget.TextView[@text='Enter xID']/following-sibling::android.widget.EditText");
	public By msg_icon=By.xpath("(//android.widget.TextView)[7]");
	public By confirmBtn=By.id("android:id/button1");
	private By pin1=By.xpath("//android.widget.TextView[@text='1']");
	private By pin2=By.xpath("//android.widget.TextView[@text='2']");
	private By pin3=By.xpath("//android.widget.TextView[@text='3']");
	public By pin4=By.xpath("//android.widget.TextView[@text='4']");
	public By pinScreen=By.xpath("//android.widget.TextView[@text='Verification PIN']");
	public By back_btn=By.xpath("(//android.widget.TextView)[1]");
	public By xID=By.xpath("//android.widget.TextView[matches(@text,'[\\d]{3}\\s[\\d]{3}\\s[\\d]{3}')]");
	public By contact_icon=By.xpath("(//android.widget.TextView)[3]");
	public By contactsScreen=By.xpath("//android.widget.TextView[@text='Contacts']");
	public By confirmpin_screen=By.xpath("//android.widget.TextView[contains(@text,'Confirm 4-Digit PIN')]");
	public By currentpassword_field=By.xpath("//android.widget.EditText[@text='Current Password']");
	public By forgotPinBtn=By.xpath("//android.widget.TextView[@text='Forgot PIN?']");
	public By done_btn=By.xpath("//android.widget.TextView[@text='Done']");
	public By changePinScreen=By.xpath("//android.widget.TextView[@text='Change PIN']");
	public By alert=By.id("android:id/message");
	public By ok_btn=By.id("android:id/button1");
	public By saveBtn=By.xpath("//android.widget.TextView[@text='Save']");
	public By toggle_btn=By.className("android.widget.Switch");
	public By mediaFile=By.id("com.google.android.documentsui:id/thumbnail");
	
	public BasePage(AndroidDriver driver) {
		this.driver=driver;
	}

	public LoginLocators gotoLogin() {
		driver.findElement(login_btn).click();
		return new LoginLocators(driver);
	}
	public RegisterLocators gotoRegister() {
		driver.findElement(welcomeScreen).click();
		return new RegisterLocators(driver);
	}
	public WebElement waitforElementPresent(By by) {
		return new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(by));
	}
	public boolean waitforElementInvisibilty(By by) {
		return new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	public WebElement scrollTo(String onText) {
		return driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+onText+"\"));"));
	}
	
	public void enterPin(char[] pin) {
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+pin[0]+"']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+pin[1]+"']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+pin[2]+"']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+pin[3]+"']")).click();
	}
	
	public void waitForWelcomeScreen() {
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfElementLocated(welcomeScreen));
	}
	//Goes tobackground for indefinite Time
	public void gotoBackground() {
		driver.runAppInBackground(Duration.ofSeconds(-1));
	}
	
	public void closeNotification() {
		driver.pressKey(new KeyEvent (AndroidKey.BACK));
	}
	
	public void tapByCoordinates(int x,int y) {
		PointerInput finger = new PointerInput(org.openqa.selenium.interactions.PointerInput.Kind.TOUCH, "finger"); 
		org.openqa.selenium.interactions.Sequence clickPosition = new org.openqa.selenium.interactions.Sequence(finger, 1);
		clickPosition .addAction(finger.createPointerMove(Duration.ZERO, Origin.viewport(), x,y))
		.addAction(finger.createPointerDown(MouseButton.LEFT.asArg())) .addAction(finger.createPointerUp(MouseButton.LEFT.asArg())); 
		driver.perform(Arrays.asList(clickPosition));
	}
	//Goes to Background for indefinite time and turns off network
	public void backgroundAndOffline() {
		driver.runAppInBackground(Duration.ofSeconds(-1));
		driver.setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
		driver.setConnection(new ConnectionStateBuilder().withDataDisabled().build());
	}
	
	private HashMap<String, String> getJsonDatatoMap(String filePath) throws IOException {
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		HashMap<String,String> data=mapper.readValue(jsonContent, new TypeReference<HashMap<String,String>>(){
		});
		return data;
	}
	
	private char[] getData() throws IOException{
		HashMap<String,String> pins=getJsonDatatoMap(System.getProperty("user.dir")+"//src//test//java//data//pin.json");
		String pin=pins.get("pin");
		return pin.toCharArray();
	}
}

