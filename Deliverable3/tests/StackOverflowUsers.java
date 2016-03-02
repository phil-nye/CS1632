import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Not;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Philip Ni on 2016.
 * 
 * REQUIRES MOZILLA FIREFOX WEB BROWSER
 * 
 * As a Stack Overflow user
 * I want to be able to find users based on reputation, account creation date, voters, editors, and moderators
 * So that I can decide which users are reputable or not
 */

public class StackOverflowUsers {
    @SuppressWarnings("unchecked")
    
    static WebDriver driver;
    
    
    // Open the Stack Overflow homepage
    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.get("http://stackoverflow.com");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }
    
    // Close browser windows at the end of test runs
    @After
    public void tearDown() throws Exception {
        //driver.close();
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
    
    // Given that I am on the homepage
    // When I click on the Users button
    // Then I should be presented the Users search page
    @Test
    public void testAccessUserSearch() {
        try {
            driver.findElement(By.id("nav-users")).click();
            wait(3);
            assertEquals("http://stackoverflow.com/users", driver.getCurrentUrl());
        }
        catch(NoSuchElementException nsee) {
            fail();
        }
    }
    
    // Given that I am on the Users search page
    // When I click on the "new users" tab
    // Then I should be able to see users created within the past 31 days ordered by their reputation scores
    @Test
    public void testNewUsers() {
        try {
            //Navigate to users search page (faster this way)
            driver.get("http://stackoverflow.com/users");
            
            //find "new users" tab
            driver.findElement(By.cssSelector("a[data-value='newusers']")).click();
            wait(3);
            
            //get scores; first user's score > second user's score > third user's score
            List<WebElement> users = driver.findElements(By.className("-flair"));
            String[] sArr = users.get(0).getText().split("\\s+");   //get first user; should have highest rep
            double rep1 = Integer.valueOf(sArr[0]);
            sArr = users.get(1).getText().split("\\s+");            //get second user; has second highest rep
            double rep2 = Integer.valueOf(sArr[0]);
            sArr = users.get(2).getText().split("\\s+");            //get third user; has third highest rep
            double rep3 = Integer.valueOf(sArr[0]);

            assertTrue(rep1 >= rep2 && rep2 >= rep3);
        }
        catch(NoSuchElementException nsee) {
            fail();
        }
    }
    
    // Given that I am on the Users search page
    // When I enter a username in the "find users" search box
    // Then I should be presented with usernames containing that exact name
    @Test
    public void testUserSearch() {
        try {
            //Navigate to users search page (faster this way)
            driver.get("http://stackoverflow.com/users");
            
            wait(3);
            
            //Get user-search box; auto-polling by javascript; no submit() needed
            driver.findElement(By.id("userfilter")).sendKeys("fojallupigi-4562");
                        
            wait(4);
            
            //determine if my username is the only result
            String username = driver.findElement(By.cssSelector(".user-details > a:nth-child(1)")).getText();
            assertEquals("fojallupigi-4562", username);
        }
        catch(NoSuchElementException nsee) {
            fail();
        }
    }
    
    // Given that I am on the Users search page
    // When I select the "editors" tab
    // Then I should see users who have edited at least 5 posts
    @Test
    public void testEditorsTab() {
        try {
            //Navigate to users search page (faster this way)
            driver.get("http://stackoverflow.com/users");
    
            wait(3);
            
            //Select editors tab
            driver.findElement(By.cssSelector("#tabs > a:nth-child(4)")).click();
            
            wait(5);
            
            //Find if first user's info contains their number of edits
            WebElement userDetails = driver.findElement(By.className("user-tags"));
            assertTrue(userDetails.getText().contains("edits"));
        }
        catch(NoSuchElementException nsee) {
            fail();
        }
    }
    
    // Given that I am on the Users search page
    // When I select the "moderators" tab
    // Then I should see users who are the current forum moderators and the year they were elected
    @Test
    public void testModeratorsTab() {
        try {
            //Navigate to users search page (faster this way)
            driver.get("http://stackoverflow.com/users");
            
            wait(3);
            
            //Select moderators tab
            driver.findElement(By.cssSelector("#tabs > a:nth-child(5)")).click();
            
            wait(5);
            
            //Find if first user's info contains the year they were elected to become a moderator
            WebElement userDetails = driver.findElement(By.cssSelector("#user-browser > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > div:nth-child(3) > a:nth-child(1)"));
            
            assertTrue(userDetails.getText().contains("elected"));
        }
        catch(NoSuchElementException nsee) {
            fail();
        }
    }
}
