package baseTest;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;


import basePage.BasePage;
import helper.AppPathManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import locators.ContactLocators;
import locators.LoginLocators;
import locators.SettingsLocators;
import locators.SingleChatLocators;


public class BaseTest {
	public AndroidDriver driver;
	public LoginLocators loginPage;
	public ContactLocators chatsPage;
	public SettingsLocators settingsPage;
	public String password;
	public String ID="...";
	public char[] pinArray;
	public BasePage basePage;
	
	@BeforeSuite
	public void cleanupImages(ITestContext testContext) {
		if(!testContext.getSuite().getName().equalsIgnoreCase("Default Suite")){
		System.out.println("Inside Before Suite");
		Path directoryPath = Paths.get(System.getProperty("user.dir")+"\\reports");

        try {
			Files.walkFileTree(directoryPath, new SimpleFileVisitor<Path>() {

			    @Override
			    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			        if (file.toString().toLowerCase().endsWith(".png")) {
			            Files.delete(file);
			        }
			        return FileVisitResult.CONTINUE;
			    }
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    }
	
	@BeforeMethod
	public void configureAppium(Method method){
		UiAutomator2Options options=new UiAutomator2Options();
		options.setCapability("udid", "emulator-5554");
		options.setApp(AppPathManager.getInstance().getAppPath());
		options.setCapability("autoGrantPermissions", true);
		options.setSystemPort(8300);
		try {
		driver=new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		basePage=new BasePage(driver);
		try {
			getData();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		System.out.println("Starting test: " + method.getName());
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown(ITestResult result) {
		if(driver!=null) {
		driver.quit();
		System.out.println("Finished Test: " + result.getMethod().getMethodName());
		}
	}
	public List<HashMap<String, String>> getListData(String filePath) throws IOException {
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
		});
		return data;
	}
	public HashMap<String, String> getJsonDatatoMap(String filePath) throws IOException {
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		HashMap<String,String> data=mapper.readValue(jsonContent, new TypeReference<HashMap<String,String>>(){
		});
		return data;
	}
	
	public void getData() throws IOException{
		
		HashMap<String,String> pass=getJsonDatatoMap(System.getProperty("user.dir")+"//src//test//java//data//credentials.json");
		password=pass.get("password");
		HashMap<String,String> pins=getJsonDatatoMap(System.getProperty("user.dir")+"//src//test//java//data//pin.json");
		pinArray=pins.get("pin").toCharArray();
	}
	public void updatePassword(String new_password) throws StreamWriteException, DatabindException, IOException {
		HashMap<String, String> data=new HashMap<String,String>();
		try {
			data = getJsonDatatoMap(System.getProperty("user.dir")+"//src//test//java//data//credentials.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.put("password", new_password);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File(System.getProperty("user.dir")+"//src//test//java//data//credentials.json"), data);
	}
	
	public void updatePin(String new_pin) throws StreamWriteException, DatabindException, IOException {
		HashMap<String, String> data=new HashMap<String,String>();
		try {
			data = getJsonDatatoMap(System.getProperty("user.dir")+"//src//test//java//data//pin.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.put("pin", new_pin);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File(System.getProperty("user.dir")+"//src//test//java//data//pin.json"), data);
	}
	
	@AfterSuite
	public void sendEmail(ITestContext testContext) {
		if(!testContext.getSuite().getName().equalsIgnoreCase("Default Suite")){
		   final String username = "...";
	        final String Emailpassword = "...";
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "...");
	        props.put("mail.smtp.port", "465");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.ssl.enable", "true");
	        Session session = Session.getInstance(props,
	                new Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, Emailpassword);
	                    }
	                });

	        try {
	        	InternetAddress[] addresses = {
	        	        new InternetAddress("...")
	        	    };
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("..."));
	            message.setRecipients(Message.RecipientType.TO,
	                    addresses);
	            message.setSubject("App Automated Run Report");
	            BodyPart messageBodyPart = new MimeBodyPart();
	            messageBodyPart.setText("Please find the attached report for App's automated run");
	            Multipart multipart = new MimeMultipart();
	            multipart.addBodyPart(messageBodyPart);
	            messageBodyPart = new MimeBodyPart();
	            String filename = System.getProperty("user.dir")+"\\reports\\report.html";
	            DataSource source = new FileDataSource(filename);
	            messageBodyPart.setDataHandler(new DataHandler(source));
	            messageBodyPart.setFileName(filename);
	            multipart.addBodyPart(messageBodyPart);
	            message.setContent(multipart);
	            Transport.send(message);
	        } catch (MessagingException e) {
	            throw new RuntimeException(e);
	        }
	        cleanUp();
		}
	}
	
	private void cleanUp() {
		UiAutomator2Options options=new UiAutomator2Options();
		options.setCapability("udid", "emulator-5554");
		options.setApp(AppPathManager.getInstance().getAppPath());
		options.setCapability("autoGrantPermissions", true);
		try {
			driver=new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		BasePage basePage=new BasePage(driver);
		basePage.waitForWelcomeScreen();
		String xID="...";
		loginPage=basePage.gotoLogin();
		try {
			getData();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		chatsPage=loginPage.verifyChatPage(xID,pinArray,password);
		chatsPage.deleteContacts();
		settingsPage=chatsPage.gotoSettingsMenu();
		String newPassword="...";
		settingsPage.verifyPassword(password, "...");
		try {
			updatePassword(newPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		settingsPage.verifyPin(pinArray, "...".toCharArray());
		try {
			updatePin("...");
		} catch (Exception e) {
			e.printStackTrace();
		}
		settingsPage.reverseDisable();
		driver.quit();
		}
	}