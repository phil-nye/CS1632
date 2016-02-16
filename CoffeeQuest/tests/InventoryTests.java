/**
 * Unittests for Inventory.java
 */

import java.lang.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class InventoryTests {
    // This tests that a new inventory is not a null object.
    // A new inventory object should contain three boolean values that are
    // all initialized to false. So, not null.
    @Test
    public void testInitInventory() {
        Inventory inv = new Inventory();
        assertNotEquals(null, inv);
    }
    
    // This tests that the default inventory contains no coffee.
    // Also tests getter for coffee status ("getCoffeeStatus").
    // A new inventory should not have any items.
    // There should be no coffee (i.e. false).
    @Test
    public void testNoCoffee() {
        Inventory inv = new Inventory();
        assertEquals(false, inv.getCoffeeStatus());
    }
    
    // This tests that the default inventory contains no cream.
    // Also tests getter for cream status ("getCreamStatus()").
    // A new inventory should not have any items.
    // There should be no cream (i.e. false).
    @Test
    public void testNoCream() {
        Inventory inv = new Inventory();
        assertEquals(false, inv.getCreamStatus());
    }
    
    // This tests that the default inventory contains no sugar.
    // Also tests getter for sugar status ("getSugarStatus").
    // A new inventory should not have any items.
    // There should be no sugar (i.e. false).
    @Test
    public void testNoSugar() {
        Inventory inv = new Inventory();
        assertFalse(inv.getCreamStatus());
    }
    
    // This tests setCoffee(), where the inventory is updated to show
    // that there is now coffee in the inventory. After invoking setCoffee()
    // the status of coffee should be set to true.
    @Test
    public void testFoundCoffee() {
        Inventory inv = new Inventory();
        inv.setCoffee();
        assertTrue(inv.getCoffeeStatus());
    }
    
    // This tests setCream(), where the inventory is updated to show
    // that there is now cream in the inventory. After invoking setCream()
    // the status of cream should be set to true.
    @Test
    public void testFoundCream() {
        Inventory inv = new Inventory();
        inv.setCream();
        assertTrue(inv.getCreamStatus());
    }
    
    // This tests setSugar(), where the inventory is updated to show
    // that there is now sugar in the inventory. After invoking setSugar()
    // the status of sugar should be set to true.
    @Test
    public void testFoundSugar() {
        Inventory inv = new Inventory();
        inv.setSugar();
        assertTrue(inv.getSugarStatus());
    }
    
    // Tests Inventory's checkWin() win condition.
    // Coffee, cream, and sugar must all be set to true in order for the
    // method to return true. This method is called when the player takes
    // a drink.
    @Test
    public void testForWinning() {
        Inventory inv = new Inventory();
        inv.setCoffee();
        inv.setCream();
        inv.setSugar();
        assertTrue(inv.checkWin());
    }
    
    // Tests Inventory's checkWin() lose condition if one item is missing.
    // Testing if the inventory contains coffee and sugar, but no cream.
    // The method should return false because not all ingredients have been
    // obtained.
    @Test
    public void testLoseWithTwoItems() {
        Inventory inv = new Inventory();
        inv.setCoffee();
        inv.setSugar();
        assertFalse(inv.checkWin());
    }
    
    // Tests Inventory's checkWin() lose condition if two items are missing.
    // Testing if the inventory contains only cream, but no coffee or sugar.
    // The method should return false because not all ingredients have been
    // obtained.
    @Test
    public void testLoseWithOneItem() {
        Inventory inv = new Inventory();
        inv.setCream();
        assertFalse(inv.checkWin());
    }
    
    // Tests Inventory's checkWin() lose condition if all items are missing.
    // Testing if the player has not picked up any items and inventory is "empty".
    // The method should return false because not all ingredients have been
    // obtained.
    @Test
    public void testLoseIfEmpty() {
        Inventory inv = new Inventory();
        assertFalse(inv.checkWin());
    }
    
    // Tests showStatus() to if player has all items in inventory.
    // The method should return a string that indicates that coffee, sugar, and
    // cream have all been retrieved.
    @Test
    public void testShowStatusAll() {
        Inventory inv = new Inventory();
        inv.setCoffee();
        inv.setCream();
        inv.setSugar();
        String expected = "You have a cup of delicious coffee.\n" + "You have some fresh cream.\n" + "You have some tasty sugar.\n";
        assertEquals(expected, inv.showStatus());
    }
    
    // Tests showStatus() if player has no items collected in inventory.
    // The method should return a string that indicates that coffee, cream, and
    // sugar are missing.
    @Test
    public void testShowStatusNone() {
        Inventory inv = new Inventory();
        String expected = "YOU HAVE NO COFFEE!\n" + "YOU HAVE NO CREAM!\n" + "YOU HAVE NO SUGAR!\n";
        assertEquals(expected, inv.showStatus());
    }
    
    // Tests showStatus() if player has some items collected in inventory.
    // If the player has only collected cream and sugar, but no coffee, the
    // status provided from showStatus() should return a string that indicates
    // that there is not coffee, but there is cream and sugar.
    @Test
    public void testShowStatusSome() {
        Inventory inv = new Inventory();
        inv.setCream();
        inv.setSugar();
        String expected = "YOU HAVE NO COFFEE!\n" + "You have some fresh cream.\n" + "You have some tasty sugar.\n";
        assertEquals(expected, inv.showStatus());
    }
}
