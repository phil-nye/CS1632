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
    
    
}
