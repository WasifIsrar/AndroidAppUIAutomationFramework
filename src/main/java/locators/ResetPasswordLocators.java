package locators;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import basePage.BasePage;
import io.appium.java_client.android.AndroidDriver;

public class ResetPasswordLocators extends BasePage{
	
	private AndroidDriver driver;
	private By forgotPasswordBtn=By.xpath("//android.widget.TextView[@text='Forgot Password?']");
	private By resetpswd_btn=By.xpath("//android.widget.TextView[@text='Reset Password']");
	private By recoveryNumberField=By.xpath("//android.widget.TextView[contains(@text,'9')]/following-sibling::android.view.ViewGroup/android.widget.EditText");
	private By continue_btn=By.xpath("//android.widget.TextView[@text='Continue']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/parent::android.view.ViewGroup");
	private By qs_btn=By.xpath("//android.widget.TextView[@text='Security Questions']");
	private By passwordBtn=By.xpath("//android.widget.TextView[@text='Password']");
	private By ans1=By.xpath("(//android.widget.EditText)[1]");
	private By ans2=By.xpath("(//android.widget.EditText)[2]");
	private By ans3=By.xpath("(//android.widget.EditText)[3]");
	private By pswd_screen=By.xpath("//android.widget.TextView[@text='Create New Password']");
	private By pswd_field=By.xpath("//android.widget.EditText[@text='Enter Password']");
	private By confirmpswd_field=By.xpath("//android.widget.EditText[@text='Confirm Password']");
	private By ok_btn=By.id("android:id/button1");
	private By phrase_btn=By.xpath("//android.widget.TextView[@text='Security Phrase']");
	private By phrase_field=By.xpath("//android.widget.EditText[@text='Your Phrase']");
	private By next_btn=By.xpath("//android.widget.TextView[@text='Next']");
	private By email_btn=By.xpath("//android.widget.TextView[@text='Email']");
	private By email_field=By.xpath("//android.widget.EditText[@text='Enter Email']");
	
	private By codeField=By.xpath("//android.widget.EditText[@text='Enter Code']");
	
	
	public ResetPasswordLocators(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public LoginLocators resetbyQuestions(String number,char[] pin,String password,String answer) {
		driver.findElement(xID_field).sendKeys(number);
		driver.navigate().back();
		driver.findElement(continue_btn).click();
		enterPin(pin);
		driver.findElement(forgotPasswordBtn).click();
		driver.findElement(recoveryNumberField).sendKeys(number);
		final WebElement continueBtn=driver.findElement(continue_btn);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver driver) {
		        if(continueBtn.isEnabled()) { 
		            return true;
		        }
		        else
		            return false;
		    }
		});
		continueBtn.click();
		driver.findElement(qs_btn).click();
		driver.findElement(ans1).sendKeys(answer);
		driver.findElement(ans2).sendKeys(answer);
		driver.findElement(ans3).sendKeys(answer);
		driver.findElement(continue_btn).click();
		driver.findElement(pswd_field).sendKeys(password);
		driver.findElement(confirmpswd_field).sendKeys(password);
		driver.findElement(continue_btn).click();
		waitforElementPresent(alert);
		driver.findElement(ok_btn).click();
		return new LoginLocators(driver);
	}
	public LoginLocators resetbyPhrase(String number,char[] pin,String password,String phrase) {
		driver.findElement(xID_field).sendKeys(number);
		driver.navigate().back();
		driver.findElement(continue_btn).click();
		enterPin(pin);
		driver.findElement(forgotPasswordBtn).click();
		driver.findElement(recoveryNumberField).sendKeys(number);
		final WebElement continueBtn=driver.findElement(continue_btn);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver driver) {
		        if(continueBtn.isEnabled()) { 
		            return true;
		        }
		        else
		            return false;
		    }
		});
		continueBtn.click();
		driver.findElement(phrase_btn).click();
		driver.findElement(phrase_field).sendKeys(phrase);
		driver.findElement(next_btn).click();
		waitforElementPresent(pswd_screen);
		driver.findElement(pswd_field).sendKeys(password);
		driver.findElement(confirmpswd_field).sendKeys(password);
		driver.findElement(continue_btn).click();
		waitforElementPresent(alert);
		driver.findElement(ok_btn).click();
		return new LoginLocators(driver);
	}
	public LoginLocators resetbyEmail(String number,String password,String email) {
		driver.findElement(forgotPasswordBtn).click();
		driver.findElement(resetpswd_btn).click();
		driver.findElement(xID_field).sendKeys(number);
		driver.findElement(continue_btn).click();
		driver.findElement(email_btn).click();
		driver.findElement(email_field).sendKeys(email);
		driver.findElement(done_btn).click();
		driver.findElement(ok_btn).click();
		String code="";
		try {
			code = getCodeFromEmail("Recovery Code","Password recovery code");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(codeField).sendKeys(code);
		waitforElementPresent(pswd_screen);
		driver.findElement(pswd_field).sendKeys(password);
		driver.findElement(confirmpswd_field).sendKeys(password);
		driver.findElement(continue_btn).click();
		waitforElementPresent(alert);
		driver.findElement(ok_btn).click();
		return new LoginLocators(driver);
	}
	
	public void resetPin(String xid) {
	driver.findElement(xID_field).sendKeys(xid);
	driver.navigate().back();
	driver.findElement(continue_btn).click();
	waitforElementPresent(pinScreen);
	driver.findElement(forgotPinBtn).click();
	}
	
	public LoginLocators resetPinQuestions(char[] pin,String answer) {
		driver.findElement(qs_btn).click();
		driver.findElement(ans1).sendKeys(answer);
		driver.findElement(ans2).sendKeys(answer);
		driver.findElement(ans3).sendKeys(answer);
		driver.findElement(continue_btn).click();
		waitforElementPresent(changePinScreen);
		enterPin(pin);
		waitforElementPresent(confirmpin_screen);
		enterPin(pin);
		waitforElementPresent(alert);
		driver.findElement(ok_btn).click();
		return new LoginLocators(driver);
	}
	
	public LoginLocators resetPinPhrase(char[] pin,String phrase) {
		driver.findElement(phrase_btn).click();
		driver.findElement(phrase_field).sendKeys(phrase);
		driver.findElement(next_btn).click();
		waitforElementPresent(changePinScreen);
		enterPin(pin);
		waitforElementPresent(confirmpin_screen);
		enterPin(pin);
		waitforElementPresent(alert);
		driver.findElement(ok_btn).click();
		return new LoginLocators(driver);
	}
	
	public LoginLocators resetPinPassword(char[] pin,String password) {
		driver.findElement(passwordBtn).click();
		driver.findElement(currentpassword_field).sendKeys(password);
		driver.findElement(done_btn).click();
		waitforElementPresent(changePinScreen);
		enterPin(pin);
		waitforElementPresent(confirmpin_screen);
		enterPin(pin);
		waitforElementPresent(alert);
		driver.findElement(ok_btn).click();
		return new LoginLocators(driver);
	}
}
