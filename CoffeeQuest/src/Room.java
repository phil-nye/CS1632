import java.lang.*;

/*  This sets up the layout of the rooms.
*/

public class Room {
    private String description;
    private String item;
    private boolean north;
    private boolean south;

    public Room() {
        description = new String();
        item = new String();
        north = false;
        south = false;
    }
    
    public void setDescription(String desc) {
       description = desc; 
    }
    
    public void setItem(String it) {
        item = it;
    }
    
    public void setNorth() {
        north = true;
    }
    
    public void setSouth() {
        south = true;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getItem() {
        return item;
    }
    
    public boolean hasNorthDoor() {
        return north;
    }
    
    public boolean hasSouthDoor() {
        return south;
    }
}
