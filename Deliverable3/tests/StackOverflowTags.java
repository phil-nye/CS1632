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
 * As a Stack Overflow user
 * I want to be able to browse through tags
 * So that I can find topics that interest me
 */

public class StackOverflowTags {
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
    
    // Given that I am on the homepage
    // When I click on the Tags button
    // Then I should be presented the Tags search page
    @Test
    public void testAccessTagSearch() {
        try {
            driver.findElement(By.id("nav-tags")).click();
            wait(3);
            assertEquals("http://stackoverflow.com/tags", driver.getCurrentUrl());
        }
        catch(NoSuchElementException nsee) {
            fail();
        }
    }
    
    // Given that I am on the Tags search page
    // When I click on the "popular" tab
    // Then the tags should show how many times the tag has been used today and this week
    @Test
    public void testPopularTagSearch() {
        try {
            driver.get("http://stackoverflow.com/tags");
            wait(3);
            
            //select "popular" tab; should be default selection, but doing it to test
            driver.findElement(By.cssSelector("a[title='most popular tags']")).click();
            wait(5);
            
            //make sure "asked today" and "this week" are present in the first result tag
            WebElement asked_today = driver.findElement(By.cssSelector("#tags-browser > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(4) > div:nth-child(1) > a:nth-child(1)"));
            boolean todayExists = asked_today.isDisplayed();
            
            WebElement asked_week = driver.findElement(By.cssSelector("#tags-browser > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(4) > div:nth-child(1) > a:nth-child(2)"));
            boolean weekExists = asked_week.isDisplayed();
            
            assertTrue(todayExists && weekExists);
        }
        catch(NoSuchElementException nsee) {
            fail();
        }
    }
    
    // Given that I am on the Tags search page
    // When I click on the "name" tab
    // Then the tags should be sorted by name in alphabetical order
    @Test
    public void testAlphabeticTagSearch() {
        try {
            driver.get("http://stackoverflow.com/tags");
            wait(3);
            
            //select "name" tab
            driver.findElement(By.cssSelector("#tabs > a:nth-child(2)")).click();
            wait(5);
            
            //make sure ".a" tag is first
            WebElement tag = driver.findElement(By.cssSelector("#tags-browser > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > a:nth-child(1)"));
            String firstTag = tag.getText();
            
            
            assertEquals(".a", firstTag);
        }
        catch(NoSuchElementException nsee) {
            fail();
        }
    }
    
    // Given that I am on the Tags search page
    // When I click on the "new" tab
    // Then the tags should be sorted by most recently created
    @Test
    public void testNewTagSearch() {
        try {
            driver.get("http://stackoverflow.com/tags");
            wait(3);
            
            //select "new" tab
            driver.findElement(By.cssSelector("#tabs > a:nth-child(3)")).click();
            wait(5);
            
            //make sure "created" is presented under a tag result
            WebElement tag = driver.findElement(By.cssSelector("#tags-browser > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(2) > div:nth-child(1)"));
            String created = tag.getText();
            
            assertTrue(created.contains("created"));
        }
        catch(NoSuchElementException nsee) {
            fail();
        }
    }
    
    // Given that I am on the Tags search page
    // When I use the search bar to find tags
    // Then the results should contain my query in the tag
    @Test
    public void testTagSearchBar() {
        try {
            driver.get("http://stackoverflow.com/tags");
            wait(3);
            
            //enter search word into tag search bar
            WebElement searchbar = driver.findElement(By.id("tagfilter"));
            searchbar.sendKeys("selenium");
            wait(5);    //dynamic searchbar searches as you type; wait to allow page to load
            
            //get tag name and make sure that it contains search word in it
            WebElement tag = driver.findElement(By.cssSelector("a.post-tag"));
            
            assertTrue(tag.getText().contains("selenium"));
        }
        catch(NoSuchElementException nsee) {
            System.out.println("ERROR");
            fail();
        }
    }
}
