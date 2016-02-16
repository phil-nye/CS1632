/*  This creates the floor layout of the player's environment.
 */

import java.util.*;

public class Player {
    private ArrayList<Room> flr;
    private int curPos;
    
    public Player() {
        flr = setupRooms();
        curPos = 0;
    }
    
    // list available instructions and its function
    public String help() {
        String h = "HELP (case insensitive):\n" +
                                   "H - list available instructions and its function\n" +
                                   "I - show inventory status\n" +
                                   "N - move through the north door if possible\n" +
                                   "S - move through the south door if possible\n" +
                                   "L - look at the contents of a room for items; prints room description\n" +
                                   "D - drink whatever you have in your inventory; ends the game\n";
        return h;
    }
    
    // sets the current position of the player;
    public void setCurPos(int pos) {
        curPos = pos;
    }
    
    // gets the current position of the player;
    // used to determine where on the floor or which
    // room the player is in
    public int getCurPos() {
        return curPos;
    }
    
    // gets the floor object
    public ArrayList<Room> getFloor() {
        return flr;
    }
    
    // sets up the rooms for the game and adds them to the floor;
    // rooms and floor layout do not change throughout the game
    private static ArrayList<Room> setupRooms() {
        ArrayList<Room> rooms = new ArrayList<Room>();
        
        Room rm0, rm1, rm2, rm3, rm4, rm5;
        
        // First room
        rm0 = new Room();
        rm0.setDescription("You see a Small room.\n" +
                                   "It has a Quaint sofa.\n" +
                                   "A Magenta door leads North.\n");
        rm0.setItem("cream");   // rm0 has the cream
        rm0.setNorth();         // rm0 only has a north door
        
        // Second room
        rm1 = new Room();   //contains no items
        rm1.setDescription("You see a Funny room.\n" +
                                   "It has a Sad record player.\n" +
                                   "A Beige door leads North.\n" +
                                   "A Massive door leads South.\n");
        rm1.setNorth();
        rm1.setSouth();
        
        // Third room
        rm2 = new Room();
        rm2.setDescription("You see a Refinanced room.\n" +
                                   "It has a Bright lamp.\n" +
                                   "A Dead door leads North.\n" +
                                   "A Smart door leads South.\n");
        rm2.setItem("coffee");  // rm2 has the coffee
        rm2.setNorth();
        rm2.setSouth();
        
        // Fourth room
        rm3 = new Room();   // contains no items
        rm3.setDescription("You see a Dumb room.\n" +
                                   "It has a Springy mattress.\n" +
                                   "A Vivacious door leads North.\n" +
                                   "A Slim door leads South.\n");
        rm3.setNorth();
        rm3.setSouth();
        
        // Fifth room
        rm4 = new Room();   // contains no items
        rm4.setDescription("You see a Bloodthirsty room.\n" +
                                   "It has a Beautiful nightstand.\n" +
                                   "A Purple door leads North.\n" +
                                   "A Sandy door leads South.\n");
        rm4.setNorth();
        rm4.setSouth();
        
        //Sixth room
        rm5 = new Room();
        rm5.setDescription("You see a Rough room.\n" +
                                   "I has a Perfect air hockey table.\n" +
                                   "A Minimalist door leads North.\n");
        rm5.setItem("sugar");   // rm5 has the sugar
        rm5.setSouth();         // rm5 only has a south door
        
        rooms.add(rm0);
        rooms.add(rm1);
        rooms.add(rm2);
        rooms.add(rm3);
        rooms.add(rm4);
        rooms.add(rm5);
        
        return rooms;
    }
}
