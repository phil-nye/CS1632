import java.lang.*;

/*  This keeps track of the player's inventory.
*/

public class Inventory {
    private boolean coffee;
    private boolean cream;
    private boolean sugar;

    public Inventory() {
        coffee = false;
        cream = false;
        sugar = false;
    }

    public void setCoffee() {
        //item is obtained by parsing the resultant string from the output of the look instruction
        coffee = true;
    }

    public void setCream() {
        cream = true;
    }

    public void setSugar() {
        sugar = true;
    }

    public boolean getCoffeeStatus() {
        return coffee;
    }

    public boolean getCreamStatus() {
        return cream;
    }

    public boolean getSugarStatus() {
        return sugar;
    }

    //prints the current status of the inventory
    public void showInventory() {
        //show coffee status
        if(coffee == true) {
            System.out.println("You have a cup of delicious coffee.");
        }
        else {
            System.out.println("YOU HAVE NO COFFEE!");
        }

        //show cream status
        if(cream == true) {
            System.out.println("You have some fresh cream.");
        }
        else {
            System.out.println("YOU HAVE NO CREAM!");
        }

        //show sugar status
        if(sugar == true) {
            System.out.println("You have some tasty sugar.");
        }
        else {
            System.out.println("YOU HAVE NO SUGAR!");
        }
    }

    public boolean checkWin() {
        if(coffee == true && cream == true && sugar == true) {
            return true;
        }
        return false;
    }
}
