package pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import utils.FileUtils;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "Login")
	public WebElement loginButton;

	@FindBy(xpath = "//input[@id='username']")
	public WebElement userName;

	@FindBy(id = "password")
	public WebElement password;

	@FindBy(id = "forgot_password_link")
	public WebElement forgotPassowrd;

	@FindBy(name = "rememberUn")
	public WebElement rememberMe;

	@FindBy(id = "hint_back_chooser")
	public WebElement savedUserName;
	
	@FindBy(how = How.ID, using = "error")
	public WebElement errorMessage;
	
	@FindBy(xpath = "//*[@id='userNavButton']")
	public WebElement usermenu;
	
	@FindBy(xpath ="//a[@title='Logout']")
	public WebElement logoutOption;
	
	@FindBy(id ="un")
	public WebElement userNameFYP;
	
	@FindBy(id ="continue")
	public WebElement Continue;

	public void userNameForgotYourPassword(String username)
	{
		this.userNameFYP.sendKeys(username);
		logger.debug("Username is entered");
	}
	
	public void enterUsername(String username) {
		this.userName.sendKeys(username);
		logger.debug("Username is entered");
	}
	
	public void enterPassword(String passWord) {
		this.password.sendKeys(passWord);
		logger.debug("password is entered");
	}
	
	public String getErrorMessage() {
		logger.debug("Error message is fetched");
		return this.errorMessage.getText();
	}
	
	public void clickLogin() {
		this.loginButton.click();
		logger.debug("Logging button clicked");
	}
	
	public String getValueAttribute(WebElement element) {
		return element.getAttribute("value");
	}
	
	public HomePage loginToApp(WebDriver driver, String username, String passWord) {
		this.enterUsername(username);
		this.enterPassword(passWord);
		this.clickLogin();
		return new HomePage(driver);
	}
	
	public HomePage loginToApp(WebDriver driver) throws FileNotFoundException, IOException {
		driver.navigate().to(FileUtils.readLoginPropertiesFile("prod.url"));
		logger.debug("Navigated to the login page");
		this.enterUsername(FileUtils.readLoginPropertiesFile("valid.username"));
		this.enterPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		this.clickLogin();
		return new HomePage(driver);
	}
}
