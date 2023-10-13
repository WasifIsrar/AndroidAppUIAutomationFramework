package locators;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import basePage.BasePage;
import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;


public class ContactLocators extends BasePage{
	
	private AndroidDriver driver;
	private By add_icon=By.xpath("(//android.widget.TextView)[8]");
	private By pendingChat=By.xpath("//android.widget.TextView[@text='Sent you a contact request']");
	private By allowBtn=By.xpath("//android.widget.TextView[@text='Allow Communication']");
	private By contactName_field=By.xpath("//android.widget.TextView[@text='Contact Name']/preceding-sibling::android.widget.EditText");
	private By newMessageScreen=By.xpath("//android.widget.TextView[@text='New Message']");
	private By invite_btn=By.xpath("//android.widget.TextView[@text='Invite Friends']");
	private By inviteLink=By.id("android:id/content_preview_text");
	private By settings_btn=By.xpath("//android.widget.TextView[matches(@text,'[\\d]{3}\\s[\\d]{3}\\s[\\d]{3}')]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup");
	private By addRecoveryAlert=By.xpath("//android.widget.TextView[matches(@text,'[\\d]{3}\\s[\\d]{3}\\s[\\d]{3}')]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[1]");
	private By newSettingsBtn=By.xpath("//android.widget.TextView[matches(@text,'[\\d]{3}\\s[\\d]{3}\\s[\\d]{3}')]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[2]");
	private By msg_friend2=By.xpath("//android.widget.TextView[@text='...']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup[1]");
	private By chat=By.xpath("//android.widget.TextView[@text='Say Hello to ...']");
	private By pin1=By.xpath("//android.widget.TextView[@text='1']");
	private By pin2=By.xpath("//android.widget.TextView[@text='2']");
	private By pin3=By.xpath("//android.widget.TextView[@text='3']");
	private By pin4=By.xpath("//android.widget.TextView[@text='4']");
	private By block_btn=By.xpath("//android.widget.TextView[@text='Block']");
	private By confirmBtn=By.id("android:id/button1");
	private By confirm_msg=By.id("android:id/message");
	private By msg_field=By.xpath("//android.widget.EditText[@text='Enter text here']");
	private By friend2_header=By.xpath("//android.widget.TextView[@text='Friend2']");
	private By friend1_chat=By.xpath("//android.widget.TextView[contains(@text,'989')]");
	private By friendMsgIcon=By.xpath("(//android.widget.TextView)[8]");
	private By codeField=By.xpath("//android.widget.EditText[@text='Enter Code']");
	private By editBtn=By.xpath("//android.widget.TextView[@text='Edit']");
	private By deleteAllBtn=By.xpath("//android.widget.TextView[@text='Delete All']");
	private By emptyContacts=By.xpath("//android.widget.TextView[@text='Add a Contact']");
	private By groupBtn=By.xpath("//android.widget.TextView[@text='New Group']/preceding-sibling::android.widget.TextView");
	private By groupName=By.xpath("//android.widget.TextView[@text='Group']");
	private By inAppNotification=By.xpath("//android.widget.TextView[contains(@text,'~')]");
	private By acceptBtn=By.xpath("//android.widget.TextView[@text='Accept']");
	private By removedAlert=By.xpath("//android.widget.TextView[contains(@text,'removed')]");
	private By loggedOutAlert=By.xpath("//android.widget.TextView[@text='You are logged in from another device']");
	private By setupNowBtn=By.xpath("//android.widget.TextView[@text='Setup Now!']");
	private By erasedAlertText=By.xpath("//android.widget.TextView[contains(@text,'deleted their messages.')]");
	private By contactBackBtn=By.xpath("//android.widget.TextView[@text='Contacts']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView");
	
	
	public ContactLocators(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public boolean getxID(String ID) {
		boolean isPresent=false;
		waitforElementPresent(xID);
		List<WebElement> Ids=driver.findElements(xID);
		for(WebElement Id:Ids) {
			if(Id.getText().equals(ID)) {
				isPresent=true;
			}
		}
		return isPresent;
		
	}
	public String verifyContactAdded(String contactName,String friend_xID) {
		
		driver.findElement(chat_icon).click();
		driver.findElement(dialpad_icon).click();
		driver.findElement(searchId_field).sendKeys(friend_xID);
		driver.navigate().back();
		waitforElementPresent(xID);
		driver.findElement(add_icon).click();
		driver.findElement(contactName_field).clear();
		driver.findElement(contactName_field).sendKeys(contactName);
		driver.findElement(saveBtn).click();
		waitforElementPresent(newMessageScreen);
		driver.findElement(back_btn).click();
		waitforElementPresent(xID);
		driver.findElement(contact_icon).click();
		waitforElementPresent(contactsScreen);
		List<WebElement> contacts=driver.findElements(xID);
		for(WebElement contact:contacts) {
			if(contact.getText().equals(friend_xID)) {
				return contact.getText();
			}
		}
		return "Contact not added";
	}
	public boolean verifyLinkGenerated() {
		driver.findElement(invite_btn).click();
		waitforElementPresent(inviteLink);
		return true;
	}
	
	public SettingsLocators gotoSettingsMenu() {
		driver.findElement(settings_btn).click();
		waitforElementPresent(settingsScreen);
		driver.setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 100);
		return new SettingsLocators(driver);
	}
	
	public SettingsLocators gotoNewSettingsMenu() {
		driver.findElement(newSettingsBtn).click();
		waitforElementPresent(settingsScreen);
		driver.setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 100);
		return new SettingsLocators(driver);
	}
	
	public boolean verifyReverse(char[] pin) {
		
		boolean isEmpty=false;
		driver.runAppInBackground(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+pin[3]+"']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+pin[2]+"']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+pin[1]+"']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+pin[0]+"']")).click();
		List<WebElement> chats_list=driver.findElements(chat);
		driver.findElement(contact_icon).click();
		waitforElementPresent(contactsScreen);
		List<WebElement> contacts_list=driver.findElements(xID);
		if(chats_list.size()==0&&contacts_list.size()==0) {
			isEmpty=true;
		}
		return isEmpty;
	}
	public String verifyBlocked() {
		driver.findElement(contact_icon).click();
		driver.findElement(msg_friend2).click();
		waitforElementPresent(msg_field);
		driver.findElement(friend2_header).click();
		scrollTo("Block");
		driver.findElement(block_btn).click();
		driver.findElement(confirmBtn).click();
		waitforElementPresent(confirm_msg);
		String isAlert=driver.findElement(confirm_msg).getText();
		driver.findElement(ok_btn).click();
		return isAlert;
	}
	public boolean verifyUnBlocked(String id) {
		driver.findElement(contact_icon).click();
		waitforElementPresent(contactsScreen);
		List<WebElement> Ids= driver.findElements(xID);
		for(WebElement Id:Ids) {
			if(Id.getText().equals(id)) {
				return true;
			}
		}
		return false;
	}
	public SingleChatLocators openChat(String friend_xID) {
		driver.findElement(chat_icon).click();
		driver.findElement(dialpad_icon).click();
		driver.findElement(searchId_field).sendKeys(friend_xID);
		driver.navigate().back();
		waitforElementPresent(xID);
		driver.findElement(msg_icon).click();
		waitforElementPresent(msg_field);
		return new SingleChatLocators(driver);
	}
	public SingleChatLocators openChatOfFriend(String friend_xID) {
		driver.findElement(chat_icon).click();
		driver.findElement(dialpad_icon).click();
		driver.findElement(searchId_field).sendKeys(friend_xID);
		driver.navigate().back();
		waitforElementPresent(xID);
		driver.findElement(friendMsgIcon).click();
		waitforElementPresent(msg_field);
		return new SingleChatLocators(driver);
	}
	public SingleChatLocators openReceivedChat() {
		driver.findElement(friend1_chat).click();
		return new SingleChatLocators(driver);
	}
	public SingleChatLocators sendPending(String friend_xID) {
		driver.findElement(chat_icon).click();
		driver.findElement(dialpad_icon).click();
		driver.findElement(searchId_field).sendKeys(friend_xID);
		driver.navigate().back();
		waitforElementPresent(xID);
		driver.findElement(msg_icon).click();
		driver.findElement(confirmBtn).click();
		waitforElementPresent(msg_field);
		return new SingleChatLocators(driver);
	}
	public void openPending() {
		driver.findElement(contact_icon).click();
		driver.findElement(pendingChat).click();
		driver.findElement(allowBtn).click();
		
	}
	public boolean isAllowed() {
		waitforElementPresent(msg_field);
		return true;
	}
	
	public void deleteContacts() {
		driver.findElement(contact_icon).click();
		waitforElementPresent(contactsScreen);
		driver.findElement(editBtn).click();
		driver.findElement(deleteAllBtn).click();
		driver.findElement(confirmBtn).click();
		waitforElementPresent(emptyContacts);
		driver.findElement(back_btn).click();
		waitforElementPresent(xID);
	}
	
	public GroupLocators clickGroup() {
		driver.findElement(groupBtn).click();
		return new GroupLocators(driver);
	}
	
	public GroupLocators openGroupChat() {
		driver.findElement(groupName).click();
		waitforElementPresent(msg_field);
		return new GroupLocators(driver);
	}
	
	public boolean openRemovedChat() {
		driver.findElement(groupName).click();
		WebElement alert=waitforElementPresent(removedAlert);
		return alert.isDisplayed();
	}
	
	public GroupLocators openAndJoin() {
		driver.findElement(groupName).click();
		waitforElementPresent(acceptBtn);
		return new GroupLocators(driver);
	}
	
	public void waitforInApp() {
		waitforElementInvisibilty(inAppNotification);
	}
	
	public SingleChatLocators openSearchedChat() {
		driver.findElement(msg_icon).click();
		return new SingleChatLocators(driver);
	}
	
	public boolean multiImgCount() {
		WebElement msgCount=waitforElementPresent(pin4);
		return msgCount.isDisplayed();
	}
	
	public boolean openAppAndWaitForLogout() {
		driver.activateApp("com.mess.engerx");
		return waitforElementPresent(loggedOutAlert).isDisplayed();
	}
	
	public boolean isLoggedOut() {
		return driver.findElement(loggedOutAlert).isDisplayed();
	}
	
	public boolean pinForgot(String password,char[] pin) {
		driver.runAppInBackground(Duration.ofSeconds(5));
		driver.findElement(forgotPinBtn).click();
		driver.findElement(currentpassword_field).sendKeys(password);
		driver.findElement(done_btn).click();
		waitforElementPresent(changePinScreen);
		enterPin(pin);
		waitforElementPresent(confirmpin_screen);
		enterPin(pin);
		waitforElementPresent(alert);
		driver.findElement(ok_btn).click();
		enterPin(pin);
		return waitforElementInvisibilty(forgotPinBtn);
	}
	
	public void killAndOpen(char[] pin) {
		driver.terminateApp("com.mess.engerx");
		driver.activateApp("com.mess.engerx");
		waitforElementPresent(forgotPinBtn);
		enterPin(pin);
		waitforElementInvisibilty(forgotPinBtn);
	}
	
	public void contactAndBack() {
		driver.findElement(contact_icon).click();
		waitforElementPresent(contactsScreen);
		driver.findElement(back_btn).click();
		waitforElementPresent(xID);
	}
	
	public SettingsLocators openRecovery() {
		driver.findElement(addRecoveryAlert).click();
		driver.findElement(setupNowBtn).click();
		return new SettingsLocators(driver);
	}
	
	public boolean verifyContactDeleted(String ID) {
		driver.findElement(contact_icon).click();
		waitforElementPresent(contactsScreen);
		return driver.findElements(By.xpath("//android.widget.TextView[@text='"+ID+"']")).size()==0;
	}
	
	public boolean verifyPinDisabled(char[] pin) {
		driver.runAppInBackground(Duration.ofSeconds(5));
		driver.findElement(toggle_btn).click();
		enterPin(pin);
		waitforElementInvisibilty(forgotPinBtn);
		driver.runAppInBackground(Duration.ofSeconds(5));
		return driver.findElement(xID).isDisplayed();
	}
	
	public boolean verifyAlert(char[] pin) {
		driver.activateApp("com.mess.engerx");
		waitforElementPresent(forgotPinBtn);
		enterPin(pin);
		return driver.findElement(erasedAlertText).isDisplayed();
	}
}