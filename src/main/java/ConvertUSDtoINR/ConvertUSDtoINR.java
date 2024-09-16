package ConvertUSDtoINR;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class ConvertUSDtoINR {

	WebDriver driver = null;
	@BeforeMethod
	public void setup()
	{
		driver = new ChromeDriver();
		driver.get("https://www.xe.com/");
		driver.manage().window().maximize();	
	}
	
	@Test
	public void testCurrencyConversion() throws InterruptedException, FileNotFoundException, IOException {
		
		WebElement fromCurrencyField = driver.findElement(By.xpath("//*[@id=\"midmarketFromCurrency\"]/button/div/div"));
        fromCurrencyField.click();
        fromCurrencyField.sendKeys("USD - US Dollar"); 
        
        Thread.sleep(2000);
        
        WebElement toCurrencyField = driver.findElement(By.xpath("//*[@id=\"midmarketToCurrency\"]/button/div/div"));
        toCurrencyField.click();
        
       
        //Select dropdown = new Select (toCurrencyField);
       // dropdown.selectByIndex(1);
       


        toCurrencyField.sendKeys("INR - Indian Rupee");
        
        Thread.sleep(2000);
        WebElement convertButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[4]/div[2]/div[1]/div[1]/div/div[2]/div[3]/button"));
        convertButton.click();
        
        Thread.sleep(2000);
        
        WebElement result = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[4]/div[2]/div[1]/div[1]/div/div[2]/div[3]/div/div[1]/div[1]")); 
        System.out.println("Conversion Result: " + result.getText());
        
	}
	
	  @AfterMethod
	    public void tearDown() { 
	       
	            driver.quit();

}
}