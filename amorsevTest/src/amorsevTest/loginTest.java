package amorsevTest;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;


public class loginTest {
	WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
    	WebDriverManager.chromedriver().setup();
        
        // Set ChromeOptions to run Chrome in headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        
        driver = new ChromeDriver(options);
     // Set ChromeOptions to run Chrome in headed mode
    	//WebDriverManager.chromedriver().setup();
       // driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void validCredentialsLoginTest() {
        driver.get("https://www.demoblaze.com/");

        WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("login2")));
        loginLink.click();

        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
        usernameField.sendKeys("darmolyn");

        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.sendKeys("schoolbag");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Log in']")));
        loginButton.click();


        WebElement welcomeMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        Assert.assertTrue(welcomeMessage.getText().contains("Welcome darmolyn"),
                "Login Not Successful");
    }

    @Test
    public void invalidCredentialsLoginTest() {
    	driver.get("https://www.demoblaze.com/");

        WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("login2")));
        loginLink.click();

        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
        usernameField.sendKeys("darmol");

        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.sendKeys("schoolb");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Log in']")));
        loginButton.click();


        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        alert.accept(); 
     }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
