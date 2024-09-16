package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.WaitUtils;

public class MySettingsPage extends BasePage{
	public static Logger logger = LogManager.getLogger("MySettingsPage");
	public MySettingsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//a[text()='My Settings']")
	public WebElement mySettings;
	
	@FindBy(id="PersonalInfo_font")
	public WebElement personalLink;
	
	@FindBy(id ="LoginHistory_font")
	public WebElement loginHistory;
	
	public void clickPersonalLink() {
	
		this.personalLink.click();
		logger.debug("clicked on Personal Link");
	}
	
	public void clickLoginHistory()
	{
		this.loginHistory.click();
		logger.debug("clicked on Login History Link");
	}

	public void selectMysettingsPage() {
		this.mySettings.click();
		logger.debug("clicked on My Settings link");
	}
}
