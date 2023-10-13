package locators;



import org.openqa.selenium.By;
import basePage.BasePage;
import io.appium.java_client.android.AndroidDriver;


public class RegisterLocators extends BasePage{
	
	private By username_field=By.xpath("//android.widget.EditText[@text='Choose a username']");
	private By login_btn=By.xpath("//android.widget.TextView[@text='Login']");
	private By pinScreenText=By.xpath("//android.widget.TextView[contains(@text,'security')]");
	private By xIDScreen=By.xpath("//android.widget.TextView[@text='Your xID0']");
	private By doneBtn=By.xpath("//android.widget.TextView[@text='Done']");
	private By finalStepBtn=By.xpath("//android.widget.TextView[@text='Final Step']");
	private AndroidDriver driver;
	
	public RegisterLocators(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public String getNumber(String username,char[] pin) {
		driver.findElement(username_field).sendKeys(username);
		driver.navigate().back();
		driver.findElement(finalStepBtn).click();
		waitforElementPresent(pinScreenText);
		enterPin(pin);
		waitforElementInvisibilty(pinScreenText);
		enterPin(pin);
		waitforElementPresent(xIDScreen);
		return driver.findElement(xID).getText();
	}
	
	public boolean verifyRegister() {
		driver.findElement(doneBtn).click();
		waitforElementPresent(securityAlert);
		driver.findElement(confirmAlertBtn).click();
		waitforElementInvisibilty(securityAlert);
		return driver.findElement(xID).isDisplayed();
}
	public boolean verifyInvalidRegister(String username) {
		driver.findElement(username_field).sendKeys(username);
		driver.navigate().back();
		driver.findElement(finalStepBtn).click();
		if(username.toLowerCase().contains("xpal")&&driver.findElements(pinScreenText).isEmpty()){
		return true;
		}
		return false;
	}
	public LoginLocators verifyLoginScreen(String xID,String password) {
		driver.findElement(login_btn).click();
		return new LoginLocators(driver);
	}
	
	
	
}