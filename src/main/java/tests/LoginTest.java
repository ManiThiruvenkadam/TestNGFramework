package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import listeners.ListenersSFDC;
import pages.HomePage;
import pages.LoginPage;
import utils.CommonUtils;
import utils.FileUtils;

@Listeners(ListenersSFDC.class)
public class LoginTest extends BaseTest{

	
	//@Test
	public void loginErrorMessageTC01() throws InterruptedException, FileNotFoundException, IOException
	{
		WebDriver driver = getBrowser();
		LoginPage lp = new LoginPage(driver);
		logger.info("Browser instance launched");
	     // Navigate to URL
	    String prodUrl = FileUtils.readLoginPropertiesFile("prod.url");
	    driver.navigate().to(prodUrl);
	    
	    test.get().info("App launched");
	    
	    String expectedUsername = FileUtils.readLoginPropertiesFile("valid.username");
	    lp.enterUsername(expectedUsername);
	    
	    test.get().info("User name entered");
	    
	    String actualUsername = lp.getValueAttribute(lp.userName);
		Assert.assertEquals(expectedUsername, actualUsername, "The actual and expected usernames should be same");
		lp.password.clear();
		
		String actualPassword = lp.getValueAttribute(lp.password);
		Assert.assertEquals("", actualPassword, "The actual and expected passwords should be same");
		lp.clickLogin();
		
		test.get().info("Login button clicked");
		
		CommonUtils.captureScreenshot(driver);
		
		Assert.assertEquals(lp.getErrorMessage(), FileUtils.readLoginPropertiesFile("error.text"), "Error message should be same");
		
		logger.info("loginErrorMessageTC01: Finished");
	}

	//@Test
	public void loginToSalesforceTC02() throws InterruptedException, FileNotFoundException, IOException 
	{
		WebDriver driver = getBrowser();

		LoginPage lp = new LoginPage(driver);
		SoftAssert sa = new SoftAssert();
		driver.navigate().to(FileUtils.readLoginPropertiesFile("prod.url"));
		
		logger.info("Browser instance launched");
		
		String expectedUsername = FileUtils.readLoginPropertiesFile("valid.username");
		lp.enterUsername(expectedUsername);
		
		test.get().info("User name entered");
		
		String actualUsername = lp.getValueAttribute(lp.userName);
		sa.assertEquals(expectedUsername, actualUsername, "The actual and expected usernames should be same");
		lp.enterPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		lp.clickLogin();
		
		test.get().info("Login button clicked");
		
		CommonUtils.captureScreenshot(driver);
		
		sa.assertEquals(driver.getTitle(), FileUtils.readLoginPropertiesFile("homepage.title"));
		//System.out.println("Reached last line");
		
		logger.info("loginErrorMessageTC02: Finished");
		
		sa.assertAll();
		//throw new ElementClickInterceptedException("");
		
		
	}

//	@Test()
	public void loginToSalesforce() throws InterruptedException, FileNotFoundException, IOException {
		WebDriver driver = getBrowser();
		LoginPage lp = new LoginPage(driver);
		driver.navigate().to(FileUtils.readLoginPropertiesFile("prod.url"));
		String expectedUsername = FileUtils.readLoginPropertiesFile("valid.username");
		HomePage hPage = lp.loginToApp(driver, expectedUsername, FileUtils.readLoginPropertiesFile("valid.password"));
		Assert.assertEquals(driver.getTitle(), FileUtils.readLoginPropertiesFile("homepage.title"));
		Assert.assertTrue(hPage.isHomePage(), "User should be in home page");
	}

	//Below code is to test data provider.. 
	
	//@Test(dataProvider = "ValidAccounts", dataProviderClass = CommonUtils.class)
	public void loginToSalesforceAccounts(String username, String pass)
			throws InterruptedException, FileNotFoundException, IOException {
		WebDriver driver = getBrowser();
		LoginPage lp = new LoginPage(driver);
		driver.navigate().to(FileUtils.readLoginPropertiesFile("prod.url"));
		HomePage hPage = lp.loginToApp(driver, username, pass);
//		Assert.assertEquals(driver.getTitle(), FileUtils.readLoginPropertiesFile("homepage.title"));
//		Assert.assertTrue(hPage.isHomePage(), "User should be in home page");
	}

		
	/*@DataProvider(name = "InvalidAccounts")
	public Object loginTestDataInValid() {
//		To read those user accounts logic
		return new Object[][] { {"mani.t@selectiva.io", "Welcome@1" }, { "manithiruvenkadam@selectiva.io", "welcome" },
				{ "dhruv@techarch.com", "mani@karthic" } };
	} */
	
	/*@DataProvider(name = "AccountNames")
	public String[] accounts() {
//		To read those user accounts logic
		return new String[] {"", ""};
	} */
	
	//@Test
	public void RememberUserNameCheckBoxTC03() throws InterruptedException, FileNotFoundException, IOException
	{
		 	SoftAssert sa = new SoftAssert();

			WebDriver driver = getBrowser();
			LoginPage lp = new LoginPage(driver);
			
			logger.info("Browser instance launched");
			
		    // Navigate to URL
		    String prodUrl = FileUtils.readLoginPropertiesFile("prod.url");
		    driver.navigate().to(prodUrl);
		    
		    test.get().info("App launched");
		    
		    // Validate Username
		    String expectedUsername = FileUtils.readLoginPropertiesFile("valid.username");
			lp.enterUsername(expectedUsername);
			
			test.get().info("User name entered");
			
			String actualUsername = lp.getValueAttribute(lp.userName);
			sa.assertEquals(expectedUsername, actualUsername, "The actual and expected usernames should be same");
			
			lp.enterPassword(FileUtils.readLoginPropertiesFile("valid.password"));
			
			test.get().info("Password entered");
			
		   
		    lp.rememberMe.click();
		    
		    test.get().info("RememberMe clicked");
			
			CommonUtils.captureScreenshot(driver);
		    
		    lp.clickLogin();
		    
		    test.get().info("Login button clicked");
			
			CommonUtils.captureScreenshot(driver);
			
		    Thread.sleep(2000);
		        
		    //click on usermenu
		    lp.usermenu.click();
		    Thread.sleep(2000);
		    //Logout from dropDown
		    lp.logoutOption.click();
		    Thread.sleep(2000);
		    
		    test.get().info("Login button clicked");
		    
		   // boolean status = lp.userName.isDisplayed();
		    logger.info("loginErrorMessageTC03: Finished");
		    
		    sa.assertAll();
	}
	
	//@Test
		public void TestForgotPasswordTC04A() throws InterruptedException, FileNotFoundException, IOException
		{
			WebDriver driver = getBrowser();
			LoginPage lp = new LoginPage(driver);
			logger.info("Browser instance launched");
		     // Navigate to URL
		    String prodUrl = FileUtils.readLoginPropertiesFile("prod.url");
		    driver.navigate().to(prodUrl);
		    
		    test.get().info("App launched");
		    
		    String expectedUsername = FileUtils.readLoginPropertiesFile("valid.username");
		    lp.enterUsername(expectedUsername);
		    
		    test.get().info("User name entered");
		    
		    
		    // Validate Username
		    String actualUsername = lp.getValueAttribute(lp.userName);
			Assert.assertEquals(expectedUsername, actualUsername, "The actual and expected usernames should be same");
		    
		    lp.forgotPassowrd.click();
		    test.get().info("User name entered");
		    CommonUtils.captureScreenshot(driver);
		    
		    Thread.sleep(2000);
		    lp.userNameForgotYourPassword(actualUsername);
		    Thread.sleep(2000);
		    lp.Continue.click();
		    Thread.sleep(2000);
		    
		    logger.info("loginErrorMessageTC04: Finished");
		}
	
	//@Test
	public void TestForgotPasswordTC04B() throws InterruptedException, FileNotFoundException, IOException
	{
		WebDriver driver = getBrowser();
		LoginPage lp = new LoginPage(driver);
		logger.info("Browser instance launched");
	     // Navigate to URL
	    String prodUrl = FileUtils.readLoginPropertiesFile("prod.url");
	    driver.navigate().to(prodUrl);
	    
	    test.get().info("App launched");
	    
		// Enter InValid Username
	    
	    String expectedUsername = FileUtils.readLoginPropertiesFile("invalid.username");
	    lp.enterUsername(expectedUsername);
	    
	    test.get().info("Invalid user name entered");
	    CommonUtils.captureScreenshot(driver);
	    
	    String actualUsername = lp.getValueAttribute(lp.userName);
		Assert.assertEquals(expectedUsername, actualUsername, "The actual and expected usernames should not be same");
	    
		lp.enterPassword(FileUtils.readLoginPropertiesFile("invalid.password"));
		
		test.get().info("Invalid Password entered");
		
		lp.clickLogin();
	    
	    test.get().info("Login button clicked");
		
	   // lp.getErrorMessage();
	
	}
	
	
}
