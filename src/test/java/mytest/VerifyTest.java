package mytest;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VerifyTest {

	WebDriver driver;

	@Test(dataProvider = "dp")
	public void login(String email, String password) {
		
		System.out.println("Start application");
		System.setProperty("webdriver.chrome.driver", "F:\\Automationsoftware\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("http://demowebshop.tricentis.com/login");
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.name("Password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value=\'Log in\']")).click();
		System.out.println("Successfully logged in.");
	}
	
    @Test(dependsOnMethods = "login")
    public void checktitle() throws Exception
    {
    	Set<String> windowIds = driver.getWindowHandles();
		Iterator<String> iter = windowIds.iterator();
		String mainWindow = iter.next();

		driver.switchTo().window(mainWindow);

		Thread.sleep(5000);
    	
    	String logintitle = driver.getTitle();
    	System.out.println(logintitle);
    	Assert.assertEquals("Demo Web Shop", logintitle);
        System.out.println("checked title of page");
        driver.quit();
    }
	

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { "tarunkhare@gmail.com", "abcd@1234" } };
	}

}
