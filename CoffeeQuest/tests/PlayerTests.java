/**
 * Unittests for Inventory.java
 */

import java.lang.*;
import static org.junit.Assert.*;
import org.junit.Test;

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
}
