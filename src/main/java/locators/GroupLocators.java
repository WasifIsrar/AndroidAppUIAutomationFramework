package locators;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import basePage.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionStateBuilder;

public class GroupLocators extends BasePage{
	
	private By friend1=By.xpath("//android.widget.TextView[@text='...']");
	private By friend2=By.xpath("//android.widget.TextView[@text='...']");
	private By nextBtn=By.xpath("//android.widget.TextView[@text='Next']");
	private By groupNameField=By.xpath("//android.widget.EditText[@text='Create Group Name']");
	private By createBtn=By.xpath("//android.widget.TextView[@text='Create']");
	private By createdGroup=By.xpath("//android.widget.TextView[@text='You created this group']");
	private By acceptBtn=By.xpath("//android.widget.TextView[@text='Accept']");
	private By joinedAlert=By.xpath("//android.widget.TextView[contains(@text,'Joined this group')]");
	private By groupName=By.xpath("//android.widget.TextView[@text='Group']");
	private By groupMember=By.xpath("//android.widget.TextView[@text='...']/parent::android.view.ViewGroup/preceding-sibling::android.widget.TextView");
	private By removeBtn=By.xpath("//android.widget.TextView[starts-with(@text,'Remove')]");
	private By confirmBtn=By.id("android:id/button1");
	private By msgField=By.xpath("//android.widget.EditText[@text='Enter text here']");
	private By sendBtn=By.xpath("//android.widget.EditText/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup");
	private By sentStatus=By.xpath("//android.widget.TextView[@text='Sent']");
	private By msgTime=By.xpath("//android.widget.TextView[matches(@text,'^(1[0-2]|0?[1-9]):[0-5][0-9]\\s(AM|PM)$')]");
	private By addMediaBtn=By.xpath("//android.widget.EditText[@text='Enter text here']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
	private By cameraBtn=By.xpath("//android.widget.TextView[@text='Open Camera']");
	private By captureBtn=By.xpath("(//android.view.ViewGroup)[25]");
	private By sendMediaBtn=By.xpath("(//android.widget.TextView[@text='Group'])[2]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup");
	private By sendRecordedVideo_btn=By.xpath("(//android.widget.TextView[@text='Group'])[2]/parent::android.view.ViewGroup/child::android.view.ViewGroup");
	private By browseImgBtn=By.xpath("//android.widget.TextView[@text='Browse Images']");
	private By storageBtn=By.xpath("//android.widget.TextView[@text='sdk_gphone64_x86_64']");
	private By picturesBtn=By.xpath("//android.widget.TextView[@text='Pictures']");
	private By mediaFile=By.id("com.google.android.documentsui:id/thumbnail");
	private By selectBtn=By.id("com.google.android.documentsui:id/action_menu_select");
	private By browseVideo_btn=By.xpath("//android.widget.TextView[@text='Browse Video']");
	private By downloadBtn=By.xpath("//android.widget.TextView[@text='Download']");
	private By downloadMediaBtn=By.xpath("//android.widget.TextView[@text='Tap to download']/preceding-sibling::android.view.ViewGroup");
	private By downloadingTxt=By.xpath("//android.widget.TextView[@text='Downloading...']");
	private By addMemberBtn=By.xpath("//android.widget.TextView[@text='Add Member']");
	private By addBtn=By.xpath("//android.widget.TextView[@text='Add']");
	private By xPalPushTitle=By.xpath("//android.widget.TextView[@text='xPal']");
	private By xPalPushText=By.id("android:id/big_text");
	private By downloadImgText=By.xpath("//android.widget.TextView[@text='Tap to download']");
	private By removedAlert=By.xpath("//android.widget.TextView[contains(@text,'removed')]");
	private By removedPushText=By.xpath("//android.widget.TextView[contains(@text,'Removed')]");
	private By leftAlert=By.xpath("//android.widget.TextView[contains(@text,'left')]");
	private By groupPushText=By.xpath("//android.widget.TextView[contains(@text,'New')]");
	private By members=By.xpath("//android.widget.TextView[@text='F']");

	
	private AndroidDriver driver;
	public GroupLocators(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public void createGroup(String groupName) {
		driver.findElement(friend1).click();
		driver.findElement(friend2).click();
		driver.findElement(nextBtn).click();
		driver.findElement(groupNameField).sendKeys(groupName);
		driver.findElement(createBtn).click();
		waitforElementPresent(createdGroup);
	}
	
	public void joinGroup() {
		driver.findElement(acceptBtn).click();
		waitforElementPresent(joinedAlert);
	}
	
	public boolean verifyJoined() {
		return driver.findElement(joinedAlert).isDisplayed();
	}
	
	public boolean verifyIsRemoved(){
		driver.findElement(groupName).click();
		WebElement ele=driver.findElement(groupMember);
		((JavascriptExecutor)driver).executeScript("mobile:longClickGesture", ImmutableMap.of( "elementId", ((RemoteWebElement) ele).getId(),"duration",5000));
		WebElement remove=waitforElementPresent(removeBtn);
		remove.click();
		driver.findElement(confirmBtn).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		return wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				List<WebElement> member=driver.findElements(friend1);
				if(member.size()==0) {
					return true;
				}
				return false;
			}
		});
	}
	
	public boolean isRemoved() {
		return driver.findElement(removedAlert).isDisplayed();
	}
	
	public boolean leaveGroup() {
		driver.findElement(groupName).click();
		WebElement leaveBtn=scrollTo("Leave Group");
		leaveBtn.click();
		driver.findElement(confirmBtn).click();
		return driver.findElement(xID).isDisplayed();
	}
	
	public boolean verifyLeft() {
		return driver.findElement(leftAlert).isDisplayed();
	}
	
	public void Online() {
		driver.setConnection(new ConnectionStateBuilder().withWiFiEnabled().build());
	}
	
	public void openPush(char[] pin) {
		driver.openNotifications();
		waitforElementPresent(xPalPushTitle);
		driver.findElement(xPalPushText).click();
		waitforElementPresent(forgotPinBtn);
		enterPin(pin);
		waitforElementInvisibilty(forgotPinBtn);
	}
	
	public void openGroupPush(char[] pin) {
		driver.openNotifications();
		waitforElementPresent(groupName);
		driver.findElement(groupPushText).click();
		waitforElementPresent(forgotPinBtn);
		enterPin(pin);
		waitforElementInvisibilty(forgotPinBtn);
	}
	
	public void openRemovedPush(char[] pin) {
		driver.openNotifications();
		waitforElementPresent(groupName);
		driver.findElement(removedPushText).click();
		waitforElementPresent(forgotPinBtn);
		enterPin(pin);
		waitforElementInvisibilty(forgotPinBtn);
	}
	
	public void deleteAfterRemove() {
		WebElement deleteBtn=scrollTo("Delete Group");
		deleteBtn.click();
		driver.findElement(ok_btn).click();
	}
	
	public void addAgain() {
		driver.findElement(groupName).click();
		driver.findElement(addMemberBtn).click();
		driver.findElement(friend1).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				List<WebElement> member=driver.findElements(members);
				if(member.size()==3) {
					return true;
				}
				return false;
			}
		});
		driver.findElement(addBtn).click();
	}
	
	public boolean verifyAddedAgain() {
		driver.findElement(back_btn).click();
		return driver.findElement(joinedAlert).isDisplayed();
	}
	
	public void sendText(String txt) {
		driver.findElement(msgField).sendKeys(txt);
		driver.findElement(sendBtn).click();
		waitforElementPresent(sentStatus);
	}
	
	public boolean verifyMsgReceived(String text) {
		return driver.findElement(By.xpath("//android.widget.TextView[@text='"+text+"']")).isDisplayed();
	}
	
	public boolean verifyMediaReceived() {
		return driver.findElement(downloadImgText).isDisplayed();
		
	}
	
	public void captureImage() {
		driver.findElement(addMediaBtn).click();
		driver.findElement(cameraBtn).click();
		new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(cameraBtn));
		driver.findElement(captureBtn).click();
	    driver.findElement(sendMediaBtn).click();
		waitforElementPresent(sentStatus);
	}
	
	public void captureVideo() {
		driver.findElement(addMediaBtn).click();
		driver.findElement(cameraBtn).click();
		new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(cameraBtn));
		WebElement capture=driver.findElement(captureBtn);
		driver.executeScript("mobile: longClickGesture",ImmutableMap.of("elementId",((RemoteWebElement)capture).getId(),"duration",6000));
		driver.findElement(sendRecordedVideo_btn).click();
		waitforElementPresent(sentStatus);
	}
	
	public void browseImage() {
		driver.findElement(addMediaBtn).click();
		driver.findElement(browseImgBtn).click();
		driver.findElement(mediaFile).click();
		driver.findElement(sendMediaBtn).click();
		waitforElementPresent(sentStatus);
	}
	
	public void browseMultiImage() {
		driver.findElement(addMediaBtn).click();
		driver.findElement(browseImgBtn).click();
		List<WebElement> images=driver.findElements(mediaFile);
		WebElement firstImage=images.get(0);
		driver.executeScript("mobile: longClickGesture",ImmutableMap.of("elementId",((RemoteWebElement)firstImage).getId(),"duration",2000));
		for (int i = 1; i < images.size()-1; i++) {
		   images.get(i).click();
		}
		driver.findElement(selectBtn).click();
		driver.findElement(sendMediaBtn).click();
		waitforElementPresent(sentStatus);
	}
	
	public boolean multiImgCount() {
		WebElement msgCount=waitforElementPresent(pin4);
		return msgCount.isDisplayed();
	}
	
	public void browseVideo() {
		driver.findElement(addMediaBtn).click();
		driver.findElement(browseVideo_btn).click();
		List<WebElement> list_Videos=driver.findElements(mediaFile);
		list_Videos.get(1).click();
		driver.findElement(sendMediaBtn).click();
	}
	
	public boolean isMediaDownloaded() {
		driver.findElement(downloadMediaBtn).click();
		return new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(downloadingTxt));
	}
	
	public boolean isMultiDownloaded() {
		List<WebElement> images=driver.findElements(downloadMediaBtn);
		List<Boolean> imagesDownloaded=new ArrayList<Boolean>();
		for(WebElement img:images) {
			img.click();
			imagesDownloaded.add(waitforElementInvisibilty(downloadingTxt));
	}
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 600, 993));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(new Pause(finger, Duration.ofMillis(200)));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(),600,223));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(swipe));
		List<WebElement> images2=driver.findElements(downloadMediaBtn);
		for(WebElement img:images2) {
			img.click();
			imagesDownloaded.add(waitforElementInvisibilty(downloadingTxt));
	}
		for(boolean isDownloaded:imagesDownloaded) {
			if(!isDownloaded) {
				return false;
			}
		}
		return true;
}
	public void changeNetwork(char[] pin) {
		driver.findElement(back_btn).click();
		driver.runAppInBackground(Duration.ofSeconds(-1));
		driver.setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
		driver.setConnection(new ConnectionStateBuilder().withDataEnabled().build());
		driver.activateApp("com.dev.messengerx");
		waitforElementPresent(forgotPinBtn);
		enterPin(pin);
		waitforElementInvisibilty(forgotPinBtn);
	}
	
	public boolean verifyMessagesComplete() {
		return driver.findElements(msgTime).size()==5;
	}
	
	public void verifyGroupDeleted() {
		driver.findElement(groupName).click();
		WebElement deleteBtn=scrollTo("Delete Group");
		deleteBtn.click();
		driver.findElement(confirmBtn).click();
	}
	
	public boolean isGroupDeleted() {
		return waitforElementInvisibilty(groupName);
	}
	
	public void clickBackBtn(){
		driver.findElement(back_btn).click();
	}
	
	public void selectImg() {
		driver.findElement(addMediaBtn).click();
		driver.findElement(browseImgBtn).click();
		driver.findElement(mediaFile).click();
		driver.findElement(sendMediaBtn).click();
		waitforElementPresent(sentStatus);
	}
	
} 

