package locators;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import basePage.BasePage;
import io.appium.java_client.android.AndroidDriver;

public class LoginLocators extends BasePage{
	
	private AndroidDriver driver;
	
	private By password_field=By.xpath("//android.widget.EditText[@text='Enter your Password']");
	private By next_btn=By.xpath("//android.widget.TextView[@text='Next']");
	private By confirmScreen=By.xpath("//android.widget.TextView[@text='Device']");
	private By confirmLoginBtn=By.xpath("//android.widget.TextView[@text='Delete all my messages on other device']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.View");
	
	

	public LoginLocators(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
	}
	public ContactLocators verifyChatPage(String xID,char[] pin,String password) {
		driver.findElement(xID_field).sendKeys(xID);
		driver.navigate().back();
		driver.findElement(continueBtn).click();
		waitforElementPresent(pinScreen);
		enterPin(pin);
		driver.findElement(password_field).sendKeys(password);
		driver.findElement(continueBtn).click();
		waitforElementPresent(securityAlert);
		driver.findElement(confirmAlertBtn).click();
		waitforElementInvisibilty(securityAlert);
		return new ContactLocators(driver);
	}
	
	public ContactLocators verifyLogin(String xID,char[] pin,String password) {
		driver.findElement(xID_field).sendKeys(xID);
		driver.navigate().back();
		driver.findElement(continueBtn).click();
		waitforElementPresent(pinScreen);
		enterPin(pin);
		driver.findElement(confirmLoginBtn).click();
		System.out.println("Confirm Clicked");
		driver.findElement(password_field).sendKeys(password);
		driver.findElement(continueBtn).click();
		waitforElementPresent(securityAlert);
		driver.findElement(confirmAlertBtn).click();
		waitforElementInvisibilty(securityAlert);
		return new ContactLocators(driver);
	}
	
	public ContactLocators loginWithPin(String id,char[] pin) {
		driver.findElement(xID_field).sendKeys(id);
		driver.navigate().back();
		driver.findElement(continueBtn).click();
		waitforElementPresent(pinScreen);
		enterPin(pin);
		waitforElementPresent(securityAlert);
		driver.findElement(confirmAlertBtn).click();
		waitforElementInvisibilty(securityAlert);
		return new ContactLocators(driver);
	}
}
