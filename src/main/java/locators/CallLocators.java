package locators;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import basePage.BasePage;
import io.appium.java_client.android.AndroidDriver;

public class CallLocators extends BasePage{
	
	private AndroidDriver driver;
	private By audioCallIcon=By.xpath("(//android.widget.TextView)[6]");
	private By videoCallIcon=By.xpath("	//android.widget.TextView[contains(@text,'xID')]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup");
	private By ringingStatus=By.xpath("//android.widget.TextView[@text='Ringing...']");
	private By acceptCallBtn=By.xpath("//android.widget.TextView[@text='Accept']/preceding-sibling::android.view.ViewGroup");
	private By callTimer=By.xpath("//android.widget.TextView[matches(@text,'[\\d]{2}\\s:\\s[\\d]{2}')]");
	private By callDuration=By.xpath("//android.widget.TextView[starts-with(@text,'Call')]");
	private By cameraOffIcon=By.xpath("(//android.view.View)[2]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup");
	private By receiverID=By.xpath("//android.widget.TextView[matches(@text,'[\\d]{3}\\s[\\d]{3}\\s[\\d]{3}')]");
	private String callTime;
	private String recordedCallTime;
	private By callMinutes=By.xpath("//android.widget.TextView[contains(@text,'minutes')]");

	public CallLocators(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public void makeCall(String friend_xID,String callType) {
		driver.findElement(chat_icon).click();
		driver.findElement(dialpad_icon).click();
		System.out.println("DialPadIconClicked");
		driver.findElement(searchId_field).sendKeys(friend_xID);
		driver.navigate().back();
		waitforElementPresent(xID);
		if(callType.equals("Audio Call")) {
			driver.findElement(audioCallIcon).click();
		}
		else {
			driver.findElement(msg_icon).click();
			driver.findElement(videoCallIcon).click();
		}
		waitforElementPresent(ringingStatus);
	}
	
	public void makeCallPhysical(String friend_xID,String callType) {
		driver.findElement(chat_icon).click();
		driver.findElement(dialpad_icon).click();
		System.out.println("DialPadIconClicked");
		driver.findElement(searchId_field).sendKeys(friend_xID);
		driver.navigate().back();
		waitforElementPresent(xID);
		if(callType.equals("Audio Call")) {
			driver.findElement(audioCallIcon).click();
		}
		else {
			driver.findElement(msg_icon).click();
			driver.findElement(videoCallIcon).click();
		}
		waitforElementPresent(ringingStatus);
	}
	
	public void acceptAudioCall(){
		driver.findElement(acceptCallBtn).click();
		waitforElementPresent(callTimer);
	}
	
	public int getMinutes() {
		String counterText=driver.findElement(callMinutes).getText();
		Pattern pattern = Pattern.compile("\\b\\d+\\b");
        Matcher matcher = pattern.matcher(counterText);

        if (matcher.find()) {
            String match = matcher.group();
            return Integer.parseInt(match);
        }
        return 0;
	}
	
	public void gotoBackgroundandReturn() {
		driver.runAppInBackground(Duration.ofSeconds(40));
	}
	
	public void acceptVideoCall(){
		driver.findElement(acceptCallBtn).click();
		waitforElementPresent(callTimer);
	}
	public void endVideoCall(){
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		WebDriverWait waitForTime=new WebDriverWait(driver,Duration.ofSeconds(30));
		waitForTime.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>(){
	        public Boolean apply(WebDriver driver)  
	        {
	        	String timerText = driver.findElement(callTimer).getText().replaceAll("\\s", "");
	        	String[] splitTime = timerText.split(":");
		        int mins = Integer.parseInt(splitTime[0].trim());
		        int secs = Integer.parseInt(splitTime[1].trim());
		        int totalSeconds = mins * 60 + secs;
		        return totalSeconds >= 30;	
	        }
	    });
		tapByCoordinates(330,640);
		waitforElementPresent(receiverID);
		callTime=driver.findElement(callTimer).getText();
		tapByCoordinates(600,1080);
		waitforElementPresent(callDuration);
		recordedCallTime=driver.findElement(callDuration).getText();
	}
	public void endAudioCall() {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>(){
	        public Boolean apply(WebDriver driver)  
	        {
	        	String timerText = driver.findElement(callTimer).getText().replaceAll("\\s", "");
	        	String[] splitTime = timerText.split(":");
		        int mins = Integer.parseInt(splitTime[0].trim());
		        int secs = Integer.parseInt(splitTime[1].trim());
		        int totalSeconds = mins * 60 + secs;
		        return totalSeconds >= 20;	
	        }
	    });
		callTime=driver.findElement(callTimer).getText();
		tapByCoordinates(600,1080);
		new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(callTimer));
	}
	public boolean audioCallDuration() {
		driver.findElement(msg_icon).click();
		String callLog=driver.findElement(callDuration).getText();
		int actualDuration=Integer.parseInt(callLog.substring(20));
		int expectedDuration=Integer.parseInt(callTime.substring(5));
		int down=expectedDuration-2;
	    int up=expectedDuration+2;
		return (actualDuration>=down&&actualDuration<=up);
	}
	public boolean verifyVideoCallDuration() {
		int actualDuration=Integer.parseInt(recordedCallTime.substring(20));
		int expectedDuration=Integer.parseInt(callTime.substring(5));
		int down=expectedDuration-2;
	    int up=expectedDuration+2;
		return (actualDuration>=down&&actualDuration<=up);
	}
	
	
	public void endCall() {
		tapByCoordinates(600,1080);
	}
	
	public void openApp(char[] pin) {
		driver.activateApp("com.mess.engerx");
		enterPin(pin);
		waitforElementInvisibilty(forgotPinBtn);
	}
	
	public void endCallAndWait() {
		endCall();
		new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(callTimer));
	}
}
