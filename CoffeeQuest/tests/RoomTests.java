/**
 * Unittests for Rooms.java
 */

import java.lang.*;
import static org.junit.Assert.*;
import org.junit.Test;


public class RoomTests {
    // This tests that a new Room is not a null object.
    // A new room object should contain two boolean values that are
    // all initialized to false and two null strings. So, not null.
    @Test
    public void testInitRoom() {
        Room rm = new Room();
        assertNotEquals(null, rm);
    }
}
