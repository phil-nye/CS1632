import java.lang.*;

/*  This sets up the layout of a room.
*/

public class Room {
    private String description;
    private String item;
    private boolean north;
    private boolean south;

    // Room constructor
    public Room() {
        description = new String();
        item = new String();
        north = false;
        south = false;
    }
    
    // Used to build a room (description)
    public void setDescription(String desc) {
       description = desc; 
    }
    
    // Used to build a room (items)
    public void setItem(String it) {
        item = it;
    }
    
    // Used to build a room (north door)
    public void setNorth() {
        north = true;
    }
    
    // Used to build a room (south door)
    public void setSouth() {
        south = true;
    }
    
    // Used to "look" and show description upon entering a room
    public String getDescription() {
        return description;
    }
    
    // Used to "look" and show if any items are in the room
    public String getItem() {
        return item;
    }
    
    // Used to prevent users from using "north" instruction if no north
    // door exists
    public boolean hasNorthDoor() {
        return north;
    }
    
    // Used to prevent users from using "south" instruction if no south
    // doo exists
    public boolean hasSouthDoor() {
        return south;
    }
}
