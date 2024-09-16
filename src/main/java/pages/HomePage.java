package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.FileUtils;
import utils.WaitUtils;
import pages.MySettingsPage;

public class HomePage extends BasePage{
	
	public static Logger logger = LogManager.getLogger("HomePage");
	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[text()='Community']")
	public WebElement communityPanel;

	@FindBy(id = "userNavButton")
	public WebElement userMenu;

	@FindBy(xpath = "//*[@id='userNav-menuItems']/a")
	public List<WebElement> userMenuOptions;
	
	@FindBy(xpath = "//*[@id='userNav-menuItems']/a[1]")
	public WebElement myProfile;

	public boolean isHomePage() {
		return this.communityPanel.isDisplayed();
	}
	
	@FindBy(xpath = "//input[@id='username']")
	public WebElement userName;
	
	@FindBy(id = "password")
	public WebElement password;

	@FindBy(id = "Login")
	public WebElement loginButton;
	
	@FindBy(id = "userNavLabel")
	public WebElement usermenuforUserName;
	
	@FindBy(xpath = "//a[text()='My Settings")
	public WebElement mySettings;
	
	public void enterPassword(String passWord) {
		this.password.sendKeys(passWord);
		logger.debug("password is entered");
	}

    @FindBy(xpath="//a[text()='Developer Console']")
    public WebElement developerConsole;
    
    @FindBy(id ="editors-body")
    public WebElement developerconsoleIframe;
    
    
	public void clickUserMenu() {
		this.userMenu.click();
	}

	public void clickdeveloperConsole()
    {
    	developerConsole.click();
    }
    
	public MyProfilePage selectMyProfilePage(WebDriver driver) {
		this.myProfile.click();
		return new MyProfilePage(driver);
	}
	

	public void enterUsername(String username) {
		this.userName.sendKeys(username);
		logger.debug("Username is entered");
	}
	
	public void clickLogin() {
		this.loginButton.click();
		logger.debug("Logging button clicked");
	}

	/**
	 * This function will verify user menu options
	 * @return boolean true if all options are verified
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public boolean verifyUserMenuOptions() throws FileNotFoundException, IOException {
		boolean isUserMenuOptionsVerified = true;
		String[] expectedUserMenuOptions = FileUtils.readHomePropertiesFile("usermenu.options").split(",");
		for (int i = 0; i < expectedUserMenuOptions.length; i++) {
			if (expectedUserMenuOptions[i].equals(userMenuOptions.get(i).getText())) {
				System.out.println(
						"Expected: " + expectedUserMenuOptions[i] + " Actual: " + userMenuOptions.get(i).getText());
			} else {
				isUserMenuOptionsVerified = false;
			}
		}
		return isUserMenuOptionsVerified;
	}
	  public boolean verifyForceDeveloperConsoleWindow(WebDriver driver) {
	        boolean isIframeLoaded = false;
	        if (WaitUtils.explicitlyWaitForClickableElement(driver, developerconsoleIframe)) {
	            driver.switchTo().frame(developerconsoleIframe);
	            isIframeLoaded = true;  // Placeholder for iframe verification
	        } else {
	            System.out.println("Developer console iframe was not clickable or not loaded.");
	        }
	        return isIframeLoaded;
	    }
}
