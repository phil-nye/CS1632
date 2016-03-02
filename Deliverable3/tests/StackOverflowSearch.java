import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
 * I want to be able to use advanced search options with my search
 * So that I can filter out entries that don't fulfill my query
 */
public class StackOverflowSearch {
    @SuppressWarnings("unchecked")
    
    static WebDriver driver;
    
    @Before
    public void setup() {
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
    
    // Given that I am on the Stack Overflow homepage
    // Then I can use the search bar in the topbar element
    @Test
    public void testSearchExists() {
        WebElement searchBar = driver.findElement(By.cssSelector("#search > input:nth-child(1)"));
        assertTrue(searchBar.isDisplayed());
    }
    
    // Given that I am on the homepage
    // When I enter a keyword into the search bar in the topbar
    // Then the results should contain my keyword in the submission body
    @Test
    public void testSearchKeyword() {
        try {
            // put keyword into searchbar
            WebElement searchBar = driver.findElement(By.cssSelector("#search > input:nth-child(1)"));
            searchBar.sendKeys("\"selenium\"");
            searchBar.submit();
    
            // Wait 3 seconds for results to load
            wait(3);
    
            // find keyword in question excerpt body
            WebElement result = driver.findElement(By.className("result-highlight"));
            String text = result.getText().toLowerCase();    //set to lower; users may have used wonky character casing
            assertTrue(text.equals("selenium"));
        }
        catch(NoSuchElementException nsee) {
            fail();
        }
    }
    
    @Test
    public void testSearchByScore() {
        try {
            // put search query into searchbar
            WebElement searchBar = driver.findElement(By.cssSelector("#search > input:nth-child(1)"));
            searchBar.sendKeys("\"selenium\" score:1000");  //search "selenium" entries with a vote score of at least 1000
            searchBar.submit();
    
            // Wait 3 seconds for results to load
            wait(3);
    
            // find vote score and check if at least 1000
            WebElement scoreElement = driver.findElement(By.cssSelector(".vote-count-post > strong:nth-child(1)"));
            String score = scoreElement.getText();
            assertTrue(Integer.valueOf(score) >= 1000);
        }
        catch(NoSuchElementException nsee) {
            fail();
        }
    }
    
    // Given that I am on the homepage
    // When I enter a keyword to search into the search bar in the topbar
    // Then I can add a type criteria so that I can search for only questions (votes and answer count shown)
    @Test
    public void testSearchByQuestions() {
        try {
            // put search query into searchbar
            WebElement searchBar = driver.findElement(By.cssSelector("#search > input:nth-child(1)"));
            searchBar.sendKeys("\"selenium\" is:question");  //search "selenium" entries with a vote score of at least 1000
            searchBar.submit();
    
            // Wait 3 seconds for results to load
            wait(3);
    
            // find vote score element
            WebElement scoreElement = driver.findElement(By.className("vote"));
    
            //find number of answers accepted element
            WebElement ansAccepted = driver.findElement(By.cssSelector("div.status.answered-accepted"));
    
            assertTrue(scoreElement.isDisplayed() && ansAccepted.isDisplayed());
        }
        catch(NoSuchElementException nsee) {
            fail();
        }
    }
    
    // Given that I am on the homepage
    // When I enter a keyword to search into the search bar in the topbar
    // Then I can add a type criteria so that I can search for only answers (only votes shown)
    @Test
    public void testSearchByAnswers() {
        try {
            // put search query into searchbar
            WebElement searchBar = driver.findElement(By.cssSelector("#search > input:nth-child(1)"));
            searchBar.sendKeys("\"selenium\" is:answer");  //search "selenium" entries with a vote score of at least 1000
            searchBar.submit();
    
            // Wait 3 seconds for results to load
            wait(3);
    
            // find vote score element
            WebElement scoreElement = driver.findElement(By.className("vote"));
    
            assertTrue(scoreElement.isDisplayed());
        }
        catch(NoSuchElementException nsee) {
            fail();
        }
    }
    
    // Given that I am on the homepage
    // When I enter a keyword into the search bar in the topbar
    // Then the results should contain my keyword in the results' tags
    @Test
    public void testSearchByTags() {
        try {
            // put keyword into searchbar
            WebElement searchBar = driver.findElement(By.cssSelector("#search > input:nth-child(1)"));
            searchBar.sendKeys("[selenium]");
            searchBar.submit();
    
            // Wait 3 seconds for results to load
            wait(3);
    
            // find keyword in result tag
            List<WebElement> elementList = driver.findElements(By.className("post-tag"));
    
            for (WebElement element : elementList) {
                String tag = element.getText().toLowerCase();    //set to lower; users may have used wonky character casing
        
                if (tag.equals("selenium")) {
                    assertTrue(tag.equals("selenium"));
                }
            }
        }
        catch(NoSuchElementException nsee) {
            fail();
        }
    }
    
    // Given that I am on the homepage
    // When I enter a keyword into the search bar in the topbar
    // Then I can add a views criteria to see the results with at least the amount of views I specify
    @Test
    public void testSearchByViews() {
        try {
            // put keyword into searchbar
            WebElement searchBar = driver.findElement(By.cssSelector("#search > input:nth-child(1)"));
            searchBar.sendKeys("\"selenium\" views:200000");
            searchBar.submit();
    
            // Wait 3 seconds for results to load
            wait(3);
    
            // The question, "Q: Take a screenshot with Selenium WebDriver" by user James Hollingworth has 200,000 views with "selenium" in the post
            List<WebElement> results = driver.findElements(By.cssSelector("div.question-summary.search-result"));
    
            for (WebElement result : results) {
                String hollingworthSubmit = result.getAttribute("id");
        
                if (hollingworthSubmit.equals("question-summary-3422262")) {
                    assertEquals("question-summary-3422262", hollingworthSubmit);
                }
            }
        }
        catch(NoSuchElementException nsee) {
            fail();
        }
    }
}
