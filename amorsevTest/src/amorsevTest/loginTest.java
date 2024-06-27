package amorsevTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class loginTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "/Users/blessingolaiya/Desktop/SeleniumAutomation/drivers/chromedriver-mac-x64/chromedriver");
        driver = new ChromeDriver();
    }

    
    @Test
    public void validCredentialsLoginTest() {
    	
        driver.get("https://www.demoblaze.com/");
        
        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).sendKeys("darmolyn");
        driver.findElement(By.id("loginpassword")).sendKeys("schoolbag");
        
        WebElement loginButton = driver.findElement(By.cssSelector(".btn.btn-primary"));
        
        Assert.assertTrue(loginButton.getText().contains("Log In"),
        		"Button text does not contain 'Log In'");
        
        loginButton.click();
        
        WebElement welcomeMessage = driver.findElement(By.id("nameofuser"));
        
        Assert.assertTrue(welcomeMessage.getText().contains("Welcome darmolyn"),
        		"Login Not Successfull");
    }
    @Test
    public void invalidCredentialsLoginTest() {
    	
        driver.get("https://www.demoblaze.com/");
        
        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).sendKeys("darmol");
        driver.findElement(By.id("loginpassword")).sendKeys("schoolb");
        
        WebElement loginButton = driver.findElement(By.cssSelector(".btn.btn-primary"));
        
        Assert.assertTrue(loginButton.getText().contains("Log In"),
        		"Button text does not contain 'Log In'");
        loginButton.click();
        
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }
}
