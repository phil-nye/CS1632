/**
 * Unittests for Inventory.java
 */

import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mockito;

public class PlayerTests {
    // This tests that a new Player is not a null object.
    // The player object should contain all the information
    // to build each room, as well as the order the rooms are
    // placed.
    // The player object also contains the position of the player
    // (i.e. the room the player is in).
    @Test
    public void testInitPlayer() {
        Player plyr = new Player();
        assertNotEquals(null, plyr);
    }
    
    // This tests getCurPos() when Player is first init.
    // If the player always starts in the first room (index 0),
    // then the method should return 0.
    @Test
    public void testInitCurPos() {
        Player plyr = new Player();
        assertEquals(0, plyr.getCurPos());
    }
    
    // This tests setCurPost(), which overrides the previous
    // curPos value.
    // This allows the program to adjust the players position as 
    // they move through the floor into adjacent rooms.
    // If curPos is set to 3, then getCurPos should return 3.
    @Test
    public void testCurPosMoved() {
        Player plyr = new Player();
        plyr.setCurPos(3);
        assertEquals(3, plyr.getCurPos());
    }
    
    // This tests setCurPost(), which overrides the previous
    // curPos value.
    // This allows the program to adjust the players position as 
    // they move through the floor into adjacent rooms.
    // If curPos is set to 3 and decremented, then getCurPos 
    // should return 2.
    @Test
    public void testCurPosMovedBack() {
        Player plyr = new Player();
        plyr.setCurPos(3);
        plyr.setCurPos(plyr.getCurPos() - 1);
        assertEquals(2, plyr.getCurPos());
    }
    
    // This tests setCurPost(), which overrides the previous
    // curPos value.
    // This allows the program to adjust the players position as 
    // they move through the floor into adjacent rooms.
    // If curPos is set to 3 and incremented, then getCurPos 
    // should return 4.
    @Test
    public void testCurPosMovedForward() {
        Player plyr = new Player();
        plyr.setCurPos(3);
        plyr.setCurPos(plyr.getCurPos() + 1);
        assertEquals(4, plyr.getCurPos());
    }
    
    // This tests the getFloor() method when Player is init.
    // This method return an ArrayList of all the Room(s) on the floor.
    // The init value should not be null because the default Player 
    // constructor generates the floor layout when init.
    @Test
    public void testInitGetFloor() {
        Player plyr = new Player();
        ArrayList<Room> emptyFlr = new ArrayList<Room>();
        assertNotEquals(emptyFlr.isEmpty(), plyr.getFloor().isEmpty());
    }
    
    // This test checks the value of the first room to make sure it is 
    // init properly. We are checking the room description.
    // First room should be the Small room.
    @Test
    public void test1stRmOnFloor() {
        Room mockRm = Mockito.mock(Room.class);
        Mockito.when(mockRm.getDescription()).thenReturn("You see a Small room.\n" +
                                                                 "It has a Quaint sofa.\n" +
                                                                 "A Magenta door leads North.\n");
        Player plyr = new Player();
        Room rm = plyr.getFloor().get(0);
        assertEquals(mockRm.getDescription(), rm.getDescription());
    }
    
    // This test checks the value of the third room to make sure it is 
    // init properly. We are checking the room description.
    // Third room should be the Refinanced room.
    @Test
    public void test3rdRmOnFloor() {
        Room mockRm = Mockito.mock(Room.class);
        Mockito.when(mockRm.getDescription()).thenReturn("You see a Refinanced room.\n" +
                                                                 "It has a Bright lamp.\n" +
                                                                 "A Dead door leads North.\n" +
                                                                 "A Smart door leads South.\n");
        Player plyr = new Player();
        Room rm = plyr.getFloor().get(2);
        assertEquals(mockRm.getDescription(), rm.getDescription());
    }
    
    // This test checks the value of the room a player moves into.
    // The player will start in the first room on init and move to 
    // the second room. The description should show that the player
    // is now in the Funny room.
    @Test
    public void testPlyrMovedRmOnFloor() {
        Room mockRm = Mockito.mock(Room.class);
        Mockito.when(mockRm.getDescription()).thenReturn(
                        "You see a Funny room.\n" +
                        "It has a Sad record player.\n" +
                        "A Beige door leads North.\n" +
                        "A Massive door leads South.\n");
        Player plyr = new Player();
        plyr.setCurPos(plyr.getCurPos() + 1);
        Room rm = plyr.getFloor().get(plyr.getCurPos());
        assertEquals(mockRm.getDescription(), rm.getDescription());
    }
    
    // This tests the help() method.
    // Should return the help message.
    @Test
    public void testHelp() {
        String msg = "HELP (case insensitive):\n" +
                     "H - list available instructions and its function\n" +
                     "I - show inventory status\n" +
                     "N - move through the north door if possible\n" +
                     "S - move through the south door if possible\n" +
                     "L - look at the contents of a room for items; prints room description\n" +
                     "D - drink whatever you have in your inventory; ends the game\n";
        Player plyr = new Player();
        assertEquals(msg, plyr.help());
    }
}
