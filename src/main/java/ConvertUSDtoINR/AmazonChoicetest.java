package ConvertUSDtoINR;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Assert;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;


	public class AmazonChoicetest {

	    WebDriver driver = null;

	    @BeforeMethod
	    public void setup() throws InterruptedException {
	     
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	       

	        // Open Amazon website
	        driver.get("https://www.amazon.com/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Fcart%2Fview.html%3Fref_%3Dnav_ya_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
	        Thread.sleep(2000);
	        
	        WebElement emailID = driver.findElement(By.id("ap_email"));
	        emailID.sendKeys("mathiruvenkadam@gmail.com");
	        WebElement Continue = driver.findElement(By.id("continue"));
	        Continue.click();
	        
	        WebElement password = driver.findElement(By.id("ap_password"));
	        password.sendKeys("xxxx");
	    }

	    @Test
	    public void searchAndAddToCartTest() throws InterruptedException {
	        //Search for 'Kindle'
	        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
	        searchBox.sendKeys("Kindle");
	        Thread.sleep(2000);
	        WebElement searchIcon = driver.findElement(By.id("nav-search-submit-text"));
	        searchIcon.click();
	        
	        //Find the product with "Amazon's Choice" tag and click it
	        WebElement amazonChoiceProduct = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[1]/div/span[1]/div[1]/div[2]/div/div/span/div/div/div/div[1]/div/div[1]/div/span/div/div/span/span/div/span[2]/span/span/div/span[1]"));
	        amazonChoiceProduct.click();

	        //Add the product to cart
	        WebElement addToCartButton = driver.findElement(By.id("a-autoid-1-announce"));
	        addToCartButton.click();

	        // Step 4: Go to the cart
	        WebElement cartButton = driver.findElement(By.id("nav-cart-count-container"));
	        cartButton.click();

	        //Verify that the item has been added to the cart
	        WebElement cartItem = driver.findElement(By.id("sc-active-b2679554-f37a-4b20-88e8-9d93fdcceeb3"));
	        Assert.assertTrue(cartItem.isDisplayed(), "Kindle should be present in the cart");
	    }

	    @AfterMethod
	    public void tearDown() {
	        // Close the browser
	        driver.quit();
	    }
	}


