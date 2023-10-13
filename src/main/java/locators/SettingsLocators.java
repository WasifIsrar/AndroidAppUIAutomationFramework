package locators;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import basePage.BasePage;
import io.appium.java_client.android.AndroidDriver;

public class SettingsLocators extends BasePage{
	
	private AndroidDriver driver;
	private By settingsbackBtn=By.xpath("//android.widget.TextView[@text='Settings']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView");
	private By profileHeader=By.xpath("//android.widget.TextView[@text='...']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView");
	private By displayName=By.xpath("//android.widget.TextView[@text='...']/parent::android.view.ViewGroup/preceding-sibling::android.widget.TextView");
	private By editIcon=By.xpath("//android.widget.EditText/following-sibling::android.view.ViewGroup/android.widget.TextView");
	private By editNameField=By.xpath("//android.widget.TextView[@text='My Stage Gold Card']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.EditText");
	private By changePin_btn=By.xpath("//android.widget.TextView[@text='Change PIN']");
	private By pin_1=By.xpath("//android.widget.TextView[@text='1']");
	private By pin_2=By.xpath("//android.widget.TextView[@text='2']");
	private By pin_3=By.xpath("//android.widget.TextView[@text='3']");
	private By pin_4=By.xpath("//android.widget.TextView[@text='4']");
	private By newpin_screen=By.xpath("//android.widget.TextView[contains(@text,'Select a new 4-Digit PIN')]");
	private By alert=By.id("android:id/message");
	private By ok_btn=By.xpath("//android.widget.Button[@text='OK']");
	private By changePassword_btn=By.xpath("//android.widget.TextView[@text='Change Password']");
	private By addPasswordBtn=By.xpath("//android.widget.TextView[@text='Add Password']");
	private By newpassword_field=By.xpath("//android.widget.EditText[@text='New Password']");
	private By confirmpassword_field=By.xpath("//android.widget.EditText[@text='Confirm New Password']");
	private By save_btn=By.xpath("//android.widget.TextView[@text='Save']");
	private By twoStep_btn=By.xpath("//android.widget.TextView[@text='Two-Step Verification']");
	private By turnOff_btn=By.xpath("//android.widget.TextView[@text='Turn Off']");
	private By done_btn=By.xpath("//android.widget.TextView[@text='Done']");
	private By email_field=By.xpath("//android.widget.EditText[@text='Enter Email']");
	private By codeField=By.xpath("//android.widget.EditText[@text='Enter Code']");
	private By codeScreen=By.xpath("//android.widget.TextView[@text='Enter Verification Code']");
	private By reverse_btn=By.xpath("//android.widget.TextView[@text='Total Wipeout0']");
	private By yes_btn=By.id("android:id/button1");
	private By reverse_icon=By.xpath("//android.widget.TextView[@text='Reverse PIN Security']/parent::android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup");
	private By block_btn=By.xpath("//android.widget.TextView[@text=\"Blocked xID's\"]");
	private By blocked_ID=By.xpath("//android.widget.TextView[@text='...']");
	private By unblock_btn=By.xpath("//android.widget.TextView[@text='Unblock']");
	private By blockList_empty=By.xpath("//android.widget.TextView[@text=\"You have no Blocked xID's yet.\"]");
	private By recovery_btn=By.xpath("//android.widget.TextView[@text='Account Recovery']");
	private By questions_btn=By.xpath("//android.widget.TextView[@text='Security Questions']");
	private By next_btn=By.xpath("//android.widget.TextView[@text='Next']");
	private By selectqs_btn=By.xpath("//android.widget.TextView[@text='Select your Question']");
	private By questionsModal=By.xpath("//android.widget.TextView[@text='Questions']");
	private By first_Q=By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]");
	private By answer_field=By.xpath("//android.widget.EditText[@text='Answer for Question']");
	private By continue_btn=By.xpath("//android.widget.TextView[@text='Continue']");
	private By q2=By.xpath("//android.widget.TextView[@text='02']");
	private By second_Q=By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]");
	private By q3=By.xpath("//android.widget.TextView[@text='03']");
	private By third_Q=By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]");
	private By recovery_screen=By.xpath("//android.widget.TextView[@text='Account Recovery']");
	private By phrase_btn=By.xpath("//android.widget.TextView[@text='Security Phrase']");
	private By phrase_field=By.xpath("//android.widget.EditText[@text='Your Phrase']");
	private By signout_btn=By.xpath("//android.widget.TextView[@text='Sign out']");
	private By signoutOptions_btn=By.xpath("//android.widget.TextView[@text='Sign out']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.view.View");
	private By switch_btn=By.xpath("//android.widget.TextView[@text='Switch Account']");
	private By addAccount_btn=By.xpath("//android.widget.TextView[@text='Add Another xID']");
	private By confirmSignout_btn=By.xpath("//android.widget.Button[@text='SIGN OUT']");
	private By permissionBtn=By.xpath("//android.widget.TextView[@text='Who Can Contact You']");
	private By byApprovalOnlyOption=By.xpath("//android.widget.TextView[@text='By Approval Only']");
	private By disablePinBtn=By.className("android.widget.Switch");
	private By imgBtn=By.xpath("(//android.widget.ImageView)[2]");
	private By blockBackBtn=By.xpath("//android.widget.TextView[@text=\"Blocked xID's\"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView");
	
	
	public SettingsLocators(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public String verifyPin(char[] oldPin,char[] newPin) {
		driver.findElement(changePin_btn).click();
		enterPin(oldPin);
		waitforElementPresent(newpin_screen);
		enterPin(newPin);
		waitforElementPresent(confirmpin_screen);
		enterPin(newPin);
		String alert_text=driver.findElement(alert).getText();
		driver.findElement(ok_btn).click();
		return alert_text;
	}
	public String verifyPassword(String current_pass,String new_pass) {
		driver.findElement(changePassword_btn).click();
		driver.findElement(currentpassword_field).sendKeys(current_pass);
		driver.navigate().back();
		driver.findElement(newpassword_field).sendKeys(new_pass);
		driver.findElement(confirmpassword_field).sendKeys(new_pass);
		driver.findElement(save_btn).click();
		waitforElementPresent(alert);
		String alert_text=driver.findElement(alert).getText();
		driver.findElement(ok_btn).click();
		return alert_text;
	}
	
	public boolean reverseEnable() {
		driver.findElement(reverse_btn).click();
		driver.findElement(toggle_btn).click();
		driver.findElement(yes_btn).click();
		return driver.findElement(settingsScreen).isDisplayed();
	}
	public void reverseDisable() {
		driver.findElement(reverse_btn).click();
		driver.findElement(toggle_btn).click();
		driver.findElement(yes_btn).click();
		waitforElementPresent(settingsScreen);
	}
	
	public boolean isBlocked() {
		scrollTo("Blocked xID's");
		driver.findElement(block_btn).click();
		return driver.findElement(blocked_ID).isDisplayed();
	}
	public boolean addQuestions(String password,String answer,char[] pin) {
		scrollTo("Account Recovery");
		driver.findElement(recovery_btn).click();
		driver.findElement(questions_btn).click();
		enterPin(pin);
		waitforElementPresent(questions_btn);
		driver.findElement(selectqs_btn).click();
		driver.findElement(first_Q).click();
		waitforElementInvisibilty(questionsModal);
		driver.findElement(answer_field).sendKeys(answer);
		driver.findElement(continue_btn).click();
		waitforElementPresent(q2);
		driver.findElement(selectqs_btn).click();
		driver.findElement(second_Q).click();
		waitforElementInvisibilty(questionsModal);
		driver.findElement(answer_field).sendKeys(answer);
		driver.findElement(continue_btn).click();
		waitforElementPresent(q3);
		driver.findElement(selectqs_btn).click();
		driver.findElement(third_Q).click();
		waitforElementInvisibilty(questionsModal);
		driver.findElement(answer_field).sendKeys(answer);
		driver.findElement(continue_btn).click();
		waitforElementPresent(recovery_screen);
		return true;
	}
	public boolean addPhrase(String phrase,char[] pin) {
		scrollTo("Account Recovery");
		driver.findElement(recovery_btn).click();
		driver.findElement(phrase_btn).click();
		enterPin(pin);
		driver.findElement(phrase_field).sendKeys(phrase);
		driver.findElement(next_btn).click();
		waitforElementPresent(recovery_screen);
		return true;
	}
	public String setTwoStep(String mail) {
		String code="";
		driver.findElement(twoStep_btn).click();
		driver.findElement(email_field).sendKeys(mail);
		driver.findElement(next_btn).click();
		driver.findElement(ok_btn).click();
		waitforElementPresent(codeScreen);
		try {
			code=getCodeFromEmail("Verification Code","Email verification code");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		driver.findElement(codeField).sendKeys(code);
		WebElement confirmationAlert=waitforElementPresent(alert);
		String alertText=confirmationAlert.getText();
		driver.findElement(ok_btn).click();
		return alertText;
	}
	
	public void disableTwoStep(String password) {
		driver.findElement(twoStep_btn).click();
		driver.findElement(turnOff_btn).click();
		driver.findElement(currentpassword_field).sendKeys(password);
		driver.findElement(done_btn).click();
		waitforElementPresent(settingsScreen);
	}
	public boolean unblock() {
		scrollTo("Blocked xID's");
		driver.findElement(block_btn).click();
		waitforElementPresent(unblock_btn);
		driver.findElement(unblock_btn).click();
		driver.findElement(yes_btn).click();
		boolean isEmpty=waitforElementPresent(blockList_empty).isDisplayed();
		driver.findElement(blockBackBtn).click();
		waitforElementPresent(settingsScreen);
		driver.findElement(settingsbackBtn).click();
		waitforElementPresent(xID);
		return isEmpty;
	}
	public boolean signout() {
		scrollTo("Sign out");
		driver.findElement(signout_btn).click();
		driver.findElement(signoutOptions_btn).click();
		driver.findElement(confirmSignout_btn).click();
		waitforElementPresent(welcomeScreen);
		return true;
	}

	public RegisterLocators switchAccount() {
		scrollTo("Switch Account");
		driver.findElement(switch_btn).click();
		driver.findElement(addAccount_btn).click();
		waitforElementPresent(login_btn);
		return new RegisterLocators(driver);
	}
	
	public void setToByApprovalOnly() {
		scrollTo("Who Can Contact You");
		driver.findElement(permissionBtn).click();
		driver.findElement(byApprovalOnlyOption).click();
		driver.findElement(save_btn).click();
		waitforElementPresent(settingsScreen);
		driver.findElement(settingsbackBtn).click();
	}
	
	public boolean verifyProfileEdited(String name) {
		driver.findElement(profileHeader).click();
		driver.findElement(editIcon).click();
		WebElement editField=new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(editNameField));
		editField.sendKeys(name);
		driver.findElement(done_btn).click();
		waitforElementPresent(settingsScreen);
		return driver.findElement(displayName).isDisplayed();
	}
	
	public boolean verifyInvalidName(String name) {
		driver.findElement(profileHeader).click();
		driver.findElement(editIcon).click();
		WebElement editField=new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(editNameField));
		editField.sendKeys(name);
		driver.findElement(done_btn).click();
		if(name.toLowerCase().contains("xpal")&&driver.findElements(settingsScreen).isEmpty()){
			return true;
			}
			return false;
	}
	public void disablePin() {
		driver.findElement(disablePinBtn).click();
	}
	
	public String verifyPasswordAdded(String password) {
		driver.findElement(addPasswordBtn).click();
		driver.findElement(newpassword_field).sendKeys(password);
		driver.findElement(confirmpassword_field).sendKeys(password);
		driver.findElement(save_btn).click();
		waitforElementPresent(alert);
		String alert_text=driver.findElement(alert).getText();
		driver.findElement(ok_btn).click();
		return alert_text;	
	}
	
	public boolean setQuestions(String answer) {
		scrollTo("Account Recovery");
		driver.findElement(recovery_btn).click();
		driver.findElement(questions_btn).click();
		driver.findElement(selectqs_btn).click();
		driver.findElement(first_Q).click();
		waitforElementInvisibilty(questionsModal);
		driver.findElement(answer_field).sendKeys(answer);
		driver.findElement(continue_btn).click();
		waitforElementPresent(q2);
		driver.findElement(selectqs_btn).click();
		driver.findElement(second_Q).click();
		waitforElementInvisibilty(questionsModal);
		driver.findElement(answer_field).sendKeys(answer);
		driver.findElement(continue_btn).click();
		waitforElementPresent(q3);
		driver.findElement(selectqs_btn).click();
		driver.findElement(third_Q).click();
		waitforElementInvisibilty(questionsModal);
		driver.findElement(answer_field).sendKeys(answer);
		driver.findElement(continue_btn).click();
		return waitforElementPresent(recovery_screen).isDisplayed();
	}
	
	public boolean setPhrase(String phrase) {
		scrollTo("Account Recovery");
		driver.findElement(recovery_btn).click();
		driver.findElement(phrase_btn).click();
		driver.findElement(phrase_field).sendKeys(phrase);
		driver.findElement(next_btn).click();
		return waitforElementPresent(recovery_screen).isDisplayed();
	}
	
	public boolean verifyPinDisabled() {
		scrollTo("Dont ask PIN for 12 Hours");
		driver.findElement(toggle_btn).click();
		driver.runAppInBackground(Duration.ofSeconds(5));
		if(driver.findElements(forgotPinBtn).size()==0) {
			return true;
		}
		return false;
	}
	
	public void scan(char[] pin) {
		driver.findElement(profileHeader).click();
		WebElement scanBtn=scrollTo("Scan QR Code");
		scanBtn.click();
		driver.findElement(imgBtn).click();
		driver.findElement(mediaFile).click();
		enterPin(pin);
	}
}