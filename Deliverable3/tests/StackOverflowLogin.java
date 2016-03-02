import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Philip Ni on 2016.
 * 
 * REQUIRES MOZILLA FIREFOX WEB BROWSER
 * 
 * fojallupigi-4562
 * fojallupigi-4562@yopmail.com (http://www.yopmail.com/en/)
 * asdf12345
 * 
 * As a Stack Overflow user
 * I want to be able to login to my Stack Overflow account
 * So that I can ask unique questions and/or submit unique answers to questions.
 */

public class StackOverflowLogin {
    @SuppressWarnings("unchecked")
    
    static WebDriver driver;
    
    
    // Open the Stack Overflow homepage
    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }
    
    // Close browser windows at the end of test runs
    @After
    public void tearDown() throws Exception {
        driver.close();
    }
    
    // Helper method to wait for pages/actions to finish loading
    private static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        }
        catch(InterruptedException ie) {
            System.out.println("ERROR WAITING: Failed to generate a Thread to wait.");
            ie.printStackTrace();
        }
    }
    
    // Given that I am on the main page
    // And I am not logged in yet
    // And the black topbar-wrapper at the top of the homepage exists on every Stack Overflow page
    // When I click on the login button on the topbar-wrapper
    // Then I should be presented a new login page
    @Test
    public void testLoginLink() {
        driver.get("http://stackoverflow.com");
        
        WebElement loginLink = driver.findElement(By.linkText("log in"));
        loginLink.click();
        
        try{
            WebElement newPage = driver.findElement(By.id("login-form"));
            assertTrue(newPage.isDisplayed());
        }
        catch(NoSuchElementException nsee) {
            fail();
        }
    }
    
    // Given that I have navigated to the Stack Overflow login page
    // When I enter valid email and password credentials
    // Then I should be presented with a new homepage that is customized to the user account
    @Test
    public void testValidLogin() {
        // just navigate to login page directly with url to make tests faster
        driver.get("http://stackoverflow.com/users/login");
                
        try {
            // Enter login credentials and submit
            driver.findElement(By.id("email")).sendKeys("fojallupigi-4562@yopmail.com");
            driver.findElement(By.id("password")).sendKeys("asdf12345");
            driver.findElement(By.id("submit-button")).click();
            
            // Wait for 3 seconds to let the new page load
            wait(3);
            
            //String homepageTitle = driver.getTitle();
            String homepageTitle = driver.getTitle();
            assertTrue(homepageTitle.equals("Stack Overflow"));
        }
        catch(NoSuchElementException nsee) {
            System.out.println("ValidLogin() Error: Failed to find an element.");
            fail();
        }
    }
    
    // Given that I have navigated to the Stack Overflow login page
    // When I enter invalid email credentials
    // Then I should be presented with a popup balloon next to the email field that indicates the email address is
    // not tied to any existing user accounts
    @Test
    public void testInvalidEmail() {
        // just navigate to login page directly with url to make tests faster
        driver.get("http://stackoverflow.com/users/login") ;
        
        try {
            // Enter invalid login credentials
            driver.findElement(By.id("email")).sendKeys("asdf@qwertyldkfj.com");
            driver.findElement(By.id("password")).sendKeys("asdf12345");
            driver.findElement(By.id("submit-button")).click();
    
            // Wait for 3 seconds to let the error message load
            wait(3);
    
            // Get error balloon message
            WebElement errorBalloon = driver.findElement(By.className("message-text"));
            String messageText = errorBalloon.getText();
            System.out.println(messageText);
            assertEquals("We could not find an account for that email address.", messageText);
        }
        catch(NoSuchElementException nsee) {
            System.out.println("InvalidEmail() Error: Failed to find an element.");
            fail();
        }
    }
    
    // Given that the user is on the homepage
    // And they are not logged in to a valid account
    // When the "Ask Question" button is selected
    // Then the user is linked to a login page so that only registered users can ask questions
    @Test
    public void testInvalidAsk() {
        // Go to homepage
        driver.get("http://stackoverflow.com/");
        
        try {
            // Select "Ask Question" option
            WebElement askElement = driver.findElement(By.linkText("Ask Question"));
            askElement.click();
    
            // Wait 5 seconds for the page to load
            wait(5);
    
            // The url should be a login page
            String newPageUrl = driver.getCurrentUrl();
            assertTrue(newPageUrl.contains("https://stackoverflow.com/users/login"));
        }
        catch(NoSuchElementException nsee) {
            fail();
        }
    }
    
    // Given that the user is on the homepage
    // And they are logged in to a valid account
    // When the "Ask Question" button is selected
    // Then the user is linked to a new page that explains how to ask a question
    @Test
    public void testValidAsk() {
        try {
            // Login
            driver.get("http://stackoverflow.com/users/login");
            driver.findElement(By.id("email")).sendKeys("fojallupigi-4562@yopmail.com");
            driver.findElement(By.id("password")).sendKeys("asdf12345");
            driver.findElement(By.id("submit-button")).click();
    
            // Wait 3 seconds for homepage to load
            wait(3);
    
            // Select "Ask Question" option
            WebElement askElement = driver.findElement(By.linkText("Ask Question"));
            askElement.click();
    
            // Wait 5 seconds for the page to load
            wait(5);
    
            // The url should be the page where the user can read about asking questions, before proceeding to ask a question
            String newPageUrl = driver.getCurrentUrl();
            assertTrue(newPageUrl.contains("stackoverflow.com/questions/ask/advice?"));
        }
        catch(NoSuchElementException nsee) {
            fail();
        }
    }
}
