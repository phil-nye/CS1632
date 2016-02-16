import java.util.*;
import java.lang.*;

/*  This is the driver of the game and contains main.
*/

public class CoffeeQuest {
    public static void main(String args[]) {
        // Introduction
        System.out.println("Coffee Maker Quest 2.0 by Philip Ni (PCN7)\n");
    
        // Create game environment; setup the floor
        // Player starts in Room 1
        Player plyr = new Player();
        ArrayList<Room> flr = plyr.getFloor();
    
        // Create a new empty inventory
        Inventory inv = new Inventory();
    
        // Init user input
        Scanner kbd = new Scanner(System.in);
    
        while(true) {
            int plyrPos = plyr.getCurPos();
            Room rm = flr.get(plyrPos);    //current room
        
            System.out.println(rm.getDescription());    //print description
            System.out.print("INSTRUCTIONS (N,S,L,I,D,H) > ");    //give user prompt for input
        
            String instr = kbd.nextLine();
            System.out.println();
            instr = instr.toLowerCase();    //case-insensitive
        
            // Skip everything below and just loop if invalid instruction
            if(!instr.equals("n") && !instr.equals("s")
                       && !instr.equals("l") && !instr.equals("i")
                       && !instr.equals("d") && !instr.equals("h")) {
                System.out.println("What?\n");
                continue;
            }
        
            if(instr.equals("i")) {     //show inventory
                System.out.println(inv.showStatus());
            }
            else if(instr.equals("h")) {
                System.out.println(plyr.help());
            }
            else if(instr.equals("l")) {    //show room description
                System.out.println(rm.getDescription());
            
                //collect item if possible
                String item = rm.getItem();
                if(item != null && !item.equals("") &&
                           !item.isEmpty()) {
                    System.out.println("There might be something here...");
                    if(item.equals("coffee")) {
                        inv.setCoffee();
                        System.out.println("You found some caffeinated coffee!\n");
                    }
                    else if(item.equals("cream")) {
                        inv.setCream();
                        System.out.println("You found some creamy cream!\n");
                    }
                    else if(item.equals("sugar")) {
                        inv.setSugar();
                        System.out.println("You found some sweet sugar!\n");
                    }
                    else {
                        System.out.println("You don't see anything out of the ordinary.\n");
                    }
                }
                else {
                    System.out.println("You don't see anything out of the ordinary.\n");
                }
            }
            else if(instr.equals("n") && rm.hasNorthDoor()) {    //move north if possible
                plyr.setCurPos(plyrPos + 1);
            }
            else if(instr.equals("s") && rm.hasSouthDoor()) {    //move south if possible
                plyr.setCurPos(plyrPos - 1);
            }
            else if(instr.equals("d")) {    //take a drink of whatever you have
                //print inventory
                System.out.println(inv.showStatus());
            
                if(inv.checkWin()) {    //you have all the ingredients; win
                    System.out.println("You drink the beverage and are ready to study!\n");
                    System.out.println("You win!");
                    System.exit(0);
                }
                else {  //you are missing ingredients; lose
                    //check for what you are missing and provide a message
                    if(!inv.getCoffeeStatus() && inv.getCreamStatus() && inv.getSugarStatus()) {
                        //no coffee
                        System.out.println("You drink the sweetened cream, but without caffeine, you cannot study.");
                    }
                    else if(inv.getCoffeeStatus() && !inv.getCreamStatus() && inv.getSugarStatus()) {
                        //no cream
                        System.out.println("Without cream, you get an ulcer and cannot study.");
                    }
                    else if(inv.getCoffeeStatus() && inv.getCreamStatus() && !inv.getSugarStatus()) {
                        //no sugar
                        System.out.println("Without sugar, the coffee is too bitter.  You cannot study.");
                    }
                    else if(!inv.getCoffeeStatus() && !inv.getCreamStatus() && inv.getSugarStatus()) {
                        //no coffee or cream
                        System.out.println("You eat the sugar, but without caffeine, you cannot study.");
                    }
                    else if(!inv.getCoffeeStatus() && inv.getCreamStatus() && !inv.getSugarStatus()) {
                        //no coffee or sugar
                        System.out.println("You drink the cream, but without caffeine, you cannot study.\n");
                    }
                    else if(inv.getCoffeeStatus() && !inv.getCreamStatus() && !inv.getSugarStatus()) {
                        //no cream or sugar
                        System.out.println("Without cream, you get an ulcer and cannot study.");
                    }
                    else if(!inv.getCoffeeStatus() && !inv.getCreamStatus() && !inv.getSugarStatus()) {
                        //nothing
                        System.out.println("You drink the air, as you have no coffee, sugar, or cream.");
                        System.out.println("The air is invigorating, but not invigorating enough.  You cannot study.");
                    }
                
                    System.out.println("You lose!");
                    System.exit(-1);
                }
            }
            else {      //Instruction not recognized
                System.out.println("What?\n");
            }
        }
    }
}
