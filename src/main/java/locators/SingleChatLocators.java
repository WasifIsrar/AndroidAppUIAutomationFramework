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
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionStateBuilder;

public class SingleChatLocators extends BasePage{
	
	private AndroidDriver driver;
	private By contact_icon=By.xpath("(//android.widget.TextView)[3]");
	private By msg_btn=By.xpath("//android.widget.TextView[@text='...']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup[1]");
	private By msg_field=By.xpath("//android.widget.EditText[@text='Enter text here']");
	private By send_btn=By.xpath("//android.widget.EditText/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[2]");
	private By deliveredStatus=By.xpath("//android.widget.TextView[@text='Delivered']");
	private By sentStatus=By.xpath("//android.widget.TextView[@text='Sent']");
	private By addMedia_btn=By.xpath("//android.widget.EditText[@text='Enter text here']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
	private By addDisMediaBtn=By.xpath("//android.widget.EditText[@text='Enter disappearing text']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
	private By camera_btn=By.xpath("//android.widget.TextView[@text='Open Camera']");
	private By captureBtn=By.xpath("(//android.view.ViewGroup)[25]");
	private By browseImg_btn=By.xpath("//android.widget.TextView[@text='Browse Images']");
	private By browseVideo_btn=By.xpath("//android.widget.TextView[@text='Browse Video']");
	private By cancel_btn=By.xpath("//android.widget.TextView[@text='Cancel']");
	private By navBar=AppiumBy.accessibilityId("Show roots");
	private By navTitle=By.xpath("//android.widget.TextView[@text='Open from']");
	private By storageBtn=By.xpath("//android.widget.TextView[@text='sdk_gphone64_x86_64']");
	private By picturesBtn=By.xpath("//android.widget.TextView[@text='Pictures']");
	private By downloadBtn=By.xpath("//android.widget.TextView[@text='Download']");
	private By sendMedia_btn=By.xpath("//android.widget.TextView[@text='...']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup");
	private By sendRecordedVideo_btn=By.xpath("//android.widget.TextView[@text='...']/parent::android.view.ViewGroup/child::android.view.ViewGroup");
	private By shareContact_btn=By.xpath("//android.widget.TextView[@text='Share Contact']");
	private By sharedContact_item=By.xpath("//android.widget.TextView[@text='Friend2']");
	private By receivedContact=By.xpath("//android.widget.TextView[@text=' Add To Contacts']");
	private By disappearing_btn=By.xpath("//android.widget.EditText[@text='Enter text here']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[1]");
	private By msg_time=By.xpath("//android.widget.TextView[matches(@text,'^(1[0-2]|0?[1-9]):[0-5][0-9]\\s(AM|PM)$')]");
	private By disTime=By.xpath("//android.widget.TextView[@text='s']");
	private By wheelTitle=By.xpath("//android.widget.TextView[@text='Disappearing Timer']");
	private By setTimer_btn=By.xpath("//android.widget.TextView[@text='Set Timer']");
	private By disappearing_field=By.xpath("//android.widget.EditText[@text='Enter disappearing text']");
	private By chatHeader=By.xpath("//android.widget.TextView[@text='xID: ...']");
	private By invalidToast=By.xpath("//android.widget.Toast[@text='The file should be less than 50 MB']");
	private By selectBtn=By.id("com.google.android.documentsui:id/action_menu_select");
	private By alertMsg=By.id("android:id/message");
	private By downloadImgText=By.xpath("//android.widget.TextView[@text='Tap to download']");
	private By downloadImgBtn=By.xpath("//android.widget.TextView[@text='Tap to download']/preceding-sibling::android.view.ViewGroup");
	private By disappearingOnBtn=By.xpath("//android.widget.EditText[@text='Enter disappearing text']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[1]");
	private By xPalPushTitle=By.xpath("//android.widget.TextView[@text='xPal']");
	private By xPalPushText=By.id("android:id/big_text");
	private By crossIcon=By.xpath("(//android.view.View)[2]/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup");
	private By recentFolder=By.xpath("//android.widget.TextView[@text='Recent']");
	private By downloadMediaBtn=By.xpath("//android.widget.TextView[@text='Tap to download']/preceding-sibling::android.view.ViewGroup");
	private By downloadingTxt=By.xpath("//android.widget.TextView[@text='Downloading...']");
	private By selectMsgBtn=By.xpath("//android.widget.TextView[@text='Select']");
	private By selectCount=By.xpath("//android.widget.TextView[@text=' 1  Selected']");
	private By eraseBtn=By.xpath("//android.widget.TextView[@text='Erase']");
	private By eraseHereBtn=By.xpath("//android.widget.TextView[@text='Erase from Here']");
	private By eraseEverywhereBtn=By.xpath("//android.widget.TextView[@text='Erase from Everywhere']");
	private By erasedMsg=By.xpath("//android.widget.TextView[contains(@text,'erased')]");
	private By chatHead=By.xpath("//android.widget.TextView[contains(@text,'xID:')]");
	private By erasedChatAlert=By.xpath("//android.widget.TextView[contains(@text,'has deleted this chat.')]");
	private By notDeliveredText=By.xpath("//android.widget.TextView[@text='Not Delivered']");
	private By resendIcon=By.xpath("//android.widget.TextView[@text='Not Delivered']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView");
	private By resendBtn=By.xpath("//android.widget.TextView[@text='Resend']");
	private By backBtn=By.xpath("//android.widget.TextView[contains(@text,':')]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/android.widget.TextView");
	private By friend1=By.xpath("//android.widget.TextView[@text='Friend1']");
	private By addContactBtn=By.xpath("//android.widget.TextView[@text='ADD TO CONTACTS']");
	private By shareDocBtn=By.xpath("//android.widget.TextView[@text='Share Document']");
	private By downloadDocBtn=By.xpath("//android.widget.TextView[contains(@text,'cdt-automation.pdf')]/following-sibling::android.view.ViewGroup/android.widget.TextView");
	private By isDoc=By.xpath("//android.widget.TextView[contains(@text,'cdt-automation.pdf')]");
	private By docDownloadPercent=By.xpath("//android.widget.TextView[contains(@text,'%')]");
	
	
	public SingleChatLocators(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public boolean cancel() {
		driver.findElement(contact_icon).click();
		driver.findElement(msg_btn).click();
		driver.findElement(addMedia_btn).click();
		driver.findElement(cancel_btn).click();
		return(driver.findElement(msg_field).isDisplayed());
	}
	
	public boolean sendText(String msg) {
		driver.findElement(msg_field).sendKeys(msg);
		driver.findElement(send_btn).click();
		WebElement ele=waitforElementPresent(deliveredStatus);
		return ele.isDisplayed();
	}
	
	public boolean verifyLimit() {
		List<WebElement> textField=driver.findElements(msg_field);
		return textField.size()==0;
	}
	
	public void waitForSentStatus(String msg) {
		driver.findElement(msg_field).sendKeys(msg);
		driver.findElement(send_btn).click();
		waitforElementPresent(sentStatus);
	}
	
	public void captureImg() {
		driver.findElement(addMedia_btn).click();
		driver.findElement(camera_btn).click();
		new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(camera_btn));
		driver.findElement(captureBtn).click();
	    driver.findElement(sendMedia_btn).click();
		waitforElementPresent(deliveredStatus);
	}
	public void captureVid() {
		driver.findElement(addMedia_btn).click();
		driver.findElement(camera_btn).click();
		new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(camera_btn));
		WebElement capture=driver.findElement(captureBtn);
		driver.executeScript("mobile: longClickGesture",ImmutableMap.of("elementId",((RemoteWebElement)capture).getId(),"duration",6000));
		driver.findElement(sendRecordedVideo_btn).click();
		waitforElementPresent(deliveredStatus);
	}
	public void browseImg() {
		driver.findElement(addMedia_btn).click();
		driver.findElement(browseImg_btn).click();
		driver.findElement(mediaFile).click();
		driver.findElement(sendMedia_btn).click();
		waitforElementPresent(deliveredStatus);
	}
	public void selectImage() {
		driver.findElement(addMedia_btn).click();
		driver.findElement(browseImg_btn).click();
		driver.findElement(navBar).click();
		waitforElementPresent(navTitle);
		driver.findElement(recentFolder).click();
		driver.findElement(mediaFile).click();
		waitforElementPresent(sendMedia_btn);
		driver.findElement(crossIcon).click();
		driver.findElement(back_btn).click();
		waitforElementPresent(xID);
	}
	public void browseImages() {
		driver.findElement(addMedia_btn).click();
		driver.findElement(browseImg_btn).click();
		List<WebElement> images=driver.findElements(mediaFile);
		WebElement firstImage=images.get(0);
		driver.executeScript("mobile: longClickGesture",ImmutableMap.of("elementId",((RemoteWebElement)firstImage).getId(),"duration",2000));
		for (int i = 1; i < images.size()-1; i++) {
		   images.get(i).click();
		}
		driver.findElement(selectBtn).click();
		driver.findElement(sendMedia_btn).click();
		waitforElementPresent(deliveredStatus);
	}
	public String browseInvalidImages() {
		driver.findElement(addMedia_btn).click();
		driver.findElement(browseImg_btn).click();
		List<WebElement> images=driver.findElements(mediaFile);
		WebElement firstImage=images.get(0);
		driver.executeScript("mobile: longClickGesture",ImmutableMap.of("elementId",((RemoteWebElement)firstImage).getId(),"duration",2000));
		for (int i = 1; i < images.size(); i++) {
		   images.get(i).click();
		}
		driver.findElement(selectBtn).click();
		WebElement alert=waitforElementPresent(alertMsg);
		return alert.getText();
	}
	
	public void browseVid() {
		driver.findElement(addMedia_btn).click();
		driver.findElement(browseVideo_btn).click();
		List<WebElement> list_Videos=driver.findElements(mediaFile);
		list_Videos.get(1).click();
		driver.findElement(sendMedia_btn).click();
		waitforElementPresent(deliveredStatus); 
	}
	public boolean browseInvalidVid() {
		driver.findElement(addMedia_btn).click();
		driver.findElement(browseVideo_btn).click();
		List<WebElement> list_Videos=driver.findElements(mediaFile);
		list_Videos.get(0).click();
		waitforElementPresent(invalidToast);
		return true;
	}
	public void contact() {
		driver.findElement(addMedia_btn).click();
		driver.findElement(shareContact_btn).click();
		driver.findElement(sharedContact_item).click();
		waitforElementPresent(deliveredStatus);
	}
	public void sendDocument() {
		driver.findElement(addMedia_btn).click();
		driver.findElement(shareDocBtn).click();
		List<WebElement> list_Videos=driver.findElements(mediaFile);
		list_Videos.get(2).click();
		driver.findElement(sendMedia_btn).click();
		waitforElementPresent(deliveredStatus);
	}
	public boolean isDocumentReceived() {
		return driver.findElement(isDoc).isDisplayed();
	}
	public boolean downloadDocument() {
		driver.findElement(downloadDocBtn).click();
		return waitforElementInvisibilty(docDownloadPercent);
		
	}
	public void disappearingMsg(String msg) {
		driver.findElement(disappearing_btn).click();
		waitforElementPresent(wheelTitle);
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 362, 869));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(new Pause(finger, Duration.ofMillis(200)));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(2000), PointerInput.Origin.viewport(),362,779));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(swipe));
		driver.findElement(setTimer_btn).click();
		WebElement disField=waitforElementPresent(disappearing_field);
		disField.sendKeys(msg);
		driver.findElement(send_btn).click();
		waitforElementPresent(deliveredStatus);
	}
	
	public boolean checkTextMsg(String text){
		waitforElementPresent(chatHeader);
		return driver.findElement(By.xpath("//android.widget.TextView[@text='"+text+"']")).isDisplayed();	
	}
	public boolean checkMedia() {
		waitforElementPresent(chatHeader);
		return driver.findElement(downloadImgText).isDisplayed();
	}
	public boolean checkContact() {
		waitforElementPresent(chatHeader);
		return driver.findElement(receivedContact).isDisplayed();
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
	public boolean isMediaDownloaded() {
		driver.findElement(downloadMediaBtn).click();
		return new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(downloadingTxt));
	}
	
	
	public void seeMessage() {
		driver.findElement(disappearingOnBtn).click();
		waitforElementPresent(wheelTitle);
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 362, 920));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(new Pause(finger, Duration.ofMillis(200)));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(),362,978));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(swipe));
		driver.findElement(setTimer_btn).click();	
	}
	public boolean hasMessageDisappeared() {
		return new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfElementLocated(disTime));
	}
	public boolean checkMultiImages(){
		waitforElementPresent(chatHeader);
		List<WebElement> msg=driver.findElements(msg_time);
		System.out.println(msg.size());
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 615, 174));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(new Pause(finger, Duration.ofMillis(200)));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(2000), PointerInput.Origin.viewport(),615,942));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(swipe));
		msg.addAll(driver.findElements(msg_time));
		System.out.println(msg.size());
		if(msg.size()==4) {
			return true;
		}
		return false;
		
	}
	public void sendDisappearingImage() {
		driver.findElement(disappearing_btn).click();
		waitforElementPresent(wheelTitle);
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 362, 869));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(new Pause(finger, Duration.ofMillis(200)));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(2000), PointerInput.Origin.viewport(),362,779));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(swipe));
		driver.findElement(setTimer_btn).click();
		waitforElementPresent(disappearing_field);
		driver.findElement(addDisMediaBtn).click();
		driver.findElement(browseImg_btn).click();
		driver.findElement(mediaFile).click();
		driver.findElement(sendMedia_btn).click();
		waitforElementPresent(deliveredStatus);
	}
	
	public void sendDisImage() {
		driver.findElement(disappearing_btn).click();
		waitforElementPresent(wheelTitle);
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 362, 869));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(new Pause(finger, Duration.ofMillis(200)));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(2000), PointerInput.Origin.viewport(),362,779));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(swipe));
		driver.findElement(setTimer_btn).click();
		waitforElementPresent(disappearing_field);
		driver.findElement(addDisMediaBtn).click();
		driver.findElement(browseImg_btn).click();
		driver.findElement(mediaFile).click();
		driver.findElement(sendMedia_btn).click();
		waitforElementPresent(deliveredStatus);
	}
	
	public void downloadDisappearingImage(){
		waitforElementPresent(chatHeader);
		driver.findElement(disappearingOnBtn).click();
		waitforElementPresent(wheelTitle);
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 362, 920));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(new Pause(finger, Duration.ofMillis(200)));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(),362,978));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(swipe));
		driver.findElement(setTimer_btn).click();
		driver.findElement(downloadImgBtn).click();
	}
	public boolean hasImageDisappeared() {
		return new WebDriverWait(driver,Duration.ofSeconds(35)).until(ExpectedConditions.invisibilityOfElementLocated(disTime));
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
	
	public boolean isMsgSelected(String text) {
		WebElement ele=driver.findElement(By.xpath("//android.widget.TextView[@text='"+text+"']"));
		longPress(ele);
		driver.findElement(selectMsgBtn).click();
		return driver.findElement(selectCount).isDisplayed();
	}
	
	public void selectMsg(String msgContent) {
		WebElement ele=driver.findElement(By.xpath("//android.widget.TextView[@text='"+msgContent+"']"));
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId() , "duration", 5000
			));
		driver.findElement(selectMsgBtn).click();
	}
	
	public boolean eraseFromHere() {
		driver.findElement(eraseBtn).click();
		driver.findElement(eraseHereBtn).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		return wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				List<WebElement> msg=driver.findElements(msg_time);
				return msg.size()==0;
			}
		});
	}
	
	public boolean eraseFromEverywhere() {
		driver.findElement(eraseBtn).click();
		driver.findElement(eraseEverywhereBtn).click();
		return driver.findElement(erasedMsg).isDisplayed();
	}
	
	public boolean verifyErasedOnReceiver() {
		return driver.findElement(erasedMsg).isDisplayed();
	}
		
	public boolean eraseChatHere() {
		driver.findElement(chatHead).click();
		WebElement eraseBtn=scrollTo("Erase Messages");
		eraseBtn.click();
		driver.findElement(eraseHereBtn).click();
		driver.findElement(confirmBtn).click();
		WebElement ID=waitforElementPresent(xID);
		System.out.println(ID.getText());
		return ID.isDisplayed();
	}
	
	public boolean eraseChatEverywhere() {
		driver.findElement(chatHead).click();
		WebElement eraseBtn=scrollTo("Erase Messages");
		eraseBtn.click();
		driver.findElement(eraseEverywhereBtn).click();
		driver.findElement(confirmBtn).click();
		WebElement ID=waitforElementPresent(xID);
		return ID.isDisplayed();
	}
	
	public boolean verifyChatErasedFromReceiver() {
		boolean isAlertReceived=driver.findElement(erasedChatAlert).isDisplayed();
		driver.findElement(ok_btn).click();
		return isAlertReceived;
	}
	
	public boolean resend() {
		waitforElementPresent(notDeliveredText);
		driver.findElement(resendIcon).click();
		driver.findElement(resendBtn).click();
		return waitforElementPresent(deliveredStatus).isDisplayed();
	}
	
	public void clickBackBtn(){
		driver.findElement(backBtn).click();
	}
	
	public void selectImg() {
		
		driver.findElement(addMedia_btn).click();
		driver.findElement(browseImg_btn).click();
		driver.findElement(mediaFile).click();
		driver.findElement(sendMedia_btn).click();
		waitforElementPresent(deliveredStatus);
	}
	
	public void select4Imgs() {
		driver.findElement(addMedia_btn).click();
		driver.findElement(browseImg_btn).click();
		List<WebElement> images=driver.findElements(mediaFile);
		WebElement firstImage=images.get(0);
		driver.executeScript("mobile: longClickGesture",ImmutableMap.of("elementId",((RemoteWebElement)firstImage).getId(),"duration",2000));
		for (int i = 1; i < images.size()-1; i++) {
		   images.get(i).click();
		}
		driver.findElement(selectBtn).click();
		driver.findElement(sendMedia_btn).click();
		waitforElementPresent(deliveredStatus);
	}
	
	private void longPress(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId() , "duration", 5000
			));
	}
	
	public void terminate() {
		driver.findElement(friend1).click();
		WebElement terminateBtn=scrollTo("Terminate");
		terminateBtn.click();
		driver.findElement(confirmBtn).click();
		waitforElementPresent(xID);
	}
	
	public void verifyContactDeleted() {
		driver.findElement(friend1).click();
		WebElement terminateBtn=scrollTo("Terminate");
		terminateBtn.click();
		driver.findElement(confirmBtn).click();
	}
	
	public void addFromChat() {
		driver.findElement(addContactBtn).click();
		driver.findElement(saveBtn).click();
	}
}