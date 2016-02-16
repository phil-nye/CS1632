import java.lang.*;

/*  This keeps track of the player's inventory.
*/

public class Inventory {
    private boolean coffee;
    private boolean cream;
    private boolean sugar;

    // inventory constructor
    public Inventory() {
        coffee = false;
        cream = false;
        sugar = false;
    }

    // adds coffee to the inventory
    public void setCoffee() {
        //item is obtained by parsing the resultant string from the output of the look instruction
        coffee = true;
    }

    // adds cream to the inventory
    public void setCream() {
        cream = true;
    }

    // adds sugar to the inventory
    public void setSugar() {
        sugar = true;
    }

    // determines if there is coffee in teh inventory
    public boolean getCoffeeStatus() {
        return coffee;
    }

    // determines if there is cream in the inventory
    public boolean getCreamStatus() {
        return cream;
    }

    // determines if there is sugar in the inventory
    public boolean getSugarStatus() {
        return sugar;
    }

    //prints the current status of the inventory
    public String showStatus() {
        String status = new String();
        
        //show coffee status
        if(coffee == true) {
            status += "You have a cup of delicious coffee.\n";
        }
        else {
            status += "YOU HAVE NO COFFEE!\n";
        }

        //show cream status
        if(cream == true) {
            status += "You have some fresh cream.\n";
        }
        else {
            status += "YOU HAVE NO CREAM!\n";
        }

        //show sugar status
        if(sugar == true) {
            status += "You have some tasty sugar.\n";
        }
        else {
            status += "YOU HAVE NO SUGAR!\n";
        }
        
        return status;
    }

    // used for the "drink" instruction; checks inventory for all items
    public boolean checkWin() {
        if(coffee == true && cream == true && sugar == true) {
            return true;
        }
        return false;
    }
}
