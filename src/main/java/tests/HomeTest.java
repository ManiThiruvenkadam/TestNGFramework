package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.HomePage;
import pages.LoginPage;
import pages.MyProfilePage;
import pages.MySettingsPage;
import utils.CommonUtils;
import utils.FileUtils;

public class HomeTest extends BaseTest{
	HomePage hp;
	MySettingsPage mp;
	WebDriver driver;
	@BeforeMethod
	public void login() throws FileNotFoundException, IOException {
		WebDriver driver = getBrowser();
		LoginPage lp = new LoginPage(driver);
		hp = lp.loginToApp(driver);
	}
	
	@Test
	public void usermenuforUserNameDropdDown_TC05() throws FileNotFoundException, IOException, InterruptedException 
	{
		WebDriver driver = getBrowser();
		HomePage hp = new HomePage(driver);
		logger.info("Browser instance launched");
	     // Navigate to URL
	    String prodUrl = FileUtils.readLoginPropertiesFile("prod.url");
	    driver.navigate().to(prodUrl);
	    logger.info("Browser instance launched");
	    test.get().info("App launched");
	    
	    String expectedUsername = FileUtils.readLoginPropertiesFile("valid.username");
	    hp.enterUsername(expectedUsername);
	    
	    test.get().info("User name entered");
	    
	    hp.enterPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		hp.clickLogin();
		test.get().info("Clicked on login button");
		
		Assert.assertTrue(hp.usermenuforUserName.isDisplayed(),"usermenuforUserName dropdown should be visible on the page");
		CommonUtils.captureScreenshot(driver);
		hp.clickUserMenu();
		
		test.get().info("Clicked on Usermenudropdown");
		
		Assert.assertTrue(hp.verifyUserMenuOptions(), "User Menu options should be verified");
		logger.info("TC05 comppleted");
	}

	//@Test
	public void verifyMyProfileOption_TC06() throws FileNotFoundException, IOException, InterruptedException {
		hp.clickUserMenu();
		Assert.assertTrue(hp.verifyUserMenuOptions(), "User Menu options should be verified");
	    MyProfilePage profilePage = hp.selectMyProfilePage(driver);
	    profilePage.clickEditProfile(driver);
	    Assert.assertTrue(profilePage.verifyContactIframeAvailability(driver), "");
	    Assert.assertTrue(profilePage.verifyAboutTab(driver), "");
	    Assert.assertTrue(profilePage.verifyLastNameChange(),"");
	    Assert.assertTrue(profilePage.verifyCreatePost(driver, "Hello Team"));
	    Assert.assertTrue(profilePage.verifyFileUpload(driver));
	    profilePage.clickOnAddPhoto(driver);
	    Assert.assertTrue(profilePage.verifyAddPhoto(driver));
	}
	
	//@Test
	public void mySettingsOptionfromUserMenu_TC07() throws FileNotFoundException, IOException, InterruptedException {
		hp.clickUserMenu();
		Assert.assertTrue(hp.verifyUserMenuOptions(), "User Menu options should be verified");
		mp.selectMysettingsPage();
		mp.clickPersonalLink();
		mp.clickLoginHistory();
		
	
		
	}

	@Test
	public void SelectDeveloperConsoleFromUserMenuTC08() throws InterruptedException, FileNotFoundException, IOException 
    {
        SoftAssert sa = new SoftAssert();
        hp.clickUserMenu(); 
        Thread.sleep(2000);
        Assert.assertTrue(hp.verifyUserMenuOptions(), "User Menu options should be verified");
       
        hp.clickdeveloperConsole();
        boolean isConsoleLoaded = hp.verifyForceDeveloperConsoleWindow(driver);
        Thread.sleep(3000);
        Assert.assertTrue(isConsoleLoaded, "Developer Console window did not load correctly");
        sa.assertAll();
        
    }  
	
	@Test
	public void SelectLogoutFromUserMenuTC09() throws InterruptedException, FileNotFoundException, IOException
	{
		SoftAssert sa = new SoftAssert();
        hp.clickUserMenu(); 
        Assert.assertTrue(hp.verifyUserMenuOptions(), "User Menu options should be verified");
       
		
		//boolean isloginpageloaded = um.verifyloginsalesforcecompage(driver);
		//Thread.sleep(2000);


	        sa.assertAll();
		
	}
}
