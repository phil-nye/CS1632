/**
 * Unittests for Rooms.java
 */

import java.lang.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mockito;

public class RoomTests {
    // This tests that a new Room is not a null object.
    // A new room object should contain two boolean values that are
    // all initialized to false and two null strings. So, not null.
    @Test
    public void testInitRoom() {
        Room rm = new Room();
        assertNotEquals(null, rm);
    }
    
    // This tests the getDescription() method.
    // In an init Room, the description should be empty string.
    @Test
    public void testGetInitDescription() {
        Room rm = new Room();
        assertEquals(new String(), rm.getDescription());
    }
    
    // This tests the setDescription() method.
    // Method gets passed a string argument and sets
    // the room description to that string.
    @Test
    public void testSetDescription() {
        Room rm = new Room();
        rm.setDescription("a description");
        assertEquals("a description", rm.getDescription());
    }
    
    // This tests the setDescription() method.
    // If a null is passed into the method,
    // then the Room should return a null.
    @Test
    public void testSetNullDescription() {
        Room rm = new Room();
        rm.setDescription(null);
        assertEquals(null, rm.getDescription());
    }
    
    // This tests the getItem() method.
    // In an init Room, there should be no items; empty string.
    @Test
    public void testGetInitItem() {
        Room rm = new Room();
        assertEquals(new String(), rm.getItem());
    }
    
    // This tests the setDescription() method.
    // Method gets passed a string argument and sets
    // the room item to that string.
    @Test
    public void testSetItem() {
        Room rm = new Room();
        rm.setItem("an item");
        assertEquals("an item", rm.getItem());
    }
    
    // This tests the setItem() method.
    // If a null is passed into the method,
    // then the Room should return a null.
    @Test
    public void testSetNullItem() {
        Room rm = new Room();
        rm.setItem(null);
        assertEquals(null, rm.getItem());
    }
    
    // This tests the hasNorthDoor() method.
    // This method indicates that a north door exists by setting 
    // a boolean value to true or false. When Room is init, the
    // default value should be false.
    @Test
    public void testHasNorthDoorInit() {
        Room rm = new Room();
        assertEquals(false, rm.hasNorthDoor());
    }
    
    // This tests the setNorth() method.
    // This method modifies the init value to indicate that
    // a north door is present in a particular Room.
    // If setNorth() is invoked, then north should be true.
    @Test
    public void testSetNorthDoor() {
        Room rm = new Room();
        rm.setNorth();
        assertEquals(true, rm.hasNorthDoor());
    }
    
    // This tests the setNorth() method.
    // This method modifies the init value to indicate that
    // a north door is present in a particular Room.
    // If setNorth() is invoked multiple times, then north should 
    // still be true.
    @Test
    public void testMultiSetNorthDoor() {
        Room rm = new Room();
        rm.setNorth();
        rm.setNorth();
        assertEquals(true, rm.hasNorthDoor());
    }
    
    // This tests the hasSouthDoor() method.
    // This method indicates that a south door exists by setting 
    // a boolean value to true or false. When Room is init, the
    // default value should be false.
    @Test
    public void testHasSouthDoorInit() {
        Room rm = new Room();
        assertEquals(false, rm.hasSouthDoor());
    }
    
    // This tests the setSouth() method.
    // This method modifies the init value to indicate that
    // a south door is present in a particular Room.
    // If setSouth() is invoked, then south should be true.
    @Test
    public void testSetSouthDoor() {
        Room rm = new Room();
        rm.setSouth();
        assertEquals(true, rm.hasSouthDoor());
    }
    
    // This tests the setSouth() method.
    // This method modifies the init value to indicate that
    // a south door is present in a particular Room.
    // If setSouth() is invoked multiple times, then south should 
    // still be true.
    @Test
    public void testMultiSetSouthDoor() {
        Room rm = new Room();
        rm.setSouth();
        rm.setSouth();
        assertEquals(true, rm.hasSouthDoor());
    }
    
    // This tests to make sure that north is not modified 
    // when we wish to setSouth() door.
    // If north is not modified from the init value, then 
    // north should still be false.
    @Test
    public void testNWhenSetS() {
        Room rm = new Room();
        rm.setSouth();
        assertEquals(false, rm.hasNorthDoor());
    }
    
    // This tests to make sure that south is not modified 
    // when we wish to setNorth() door.
    // If south is not modified from the init value, then 
    // south should still be false.
    @Test
    public void testSWhenSetN() {
        Room rm = new Room();
        rm.setNorth();
        assertEquals(false, rm.hasSouthDoor());
    }
}
