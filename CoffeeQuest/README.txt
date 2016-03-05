=======================================
Coffee Quest 2.0 by Philip Ni (PCN7)
=======================================

Philip Ni
GitHub Repository - https://github.com/phil-nye/CS1632/tree/master/CoffeeQuest
CS1632 Software Quality Assurance
Deliverable 2
<br>
Purpose:
    Improves upon original Coffee Quest 1.0 by fixing bugs and adding missing or
    broken functionality.
<br>
Files in CoffeeQuest directory:
    1)  All tests are located in the directory called "tests".
        PlayerTests.java
        RoomTests.java
        InventoryTests.java

        *Note: CoffeeQuest.java only contains main() and is not tested.

    2)  All source code is located in the directory called "src".
        CoffeeQest.java     -->     contains main(); game driver that runs the game
        Player.java         -->     creates game environment (rooms) and layout of the rooms on the floor; determines which room the player is in; contains help()
        Room.java           -->     generates the room object; keeps track of what is in a particular room
        Inventory.java      -->     keeps track of the inventory; contains check for win/lose condition

    3)  README.txt

    4)  coverage.png is the screenshot of the coverage report generated in IntelliJ

    5)  deliverable2_writeup.docx contains a cover page, summary/difficulties of the program and tests, and the image of the coverage report

    6)  Mockito Jar ("mockito-all-1.10.19.jar")

    7)  JUnit Jar ("junit-4.12.jar")

    8)  Instructions on how to COMPILE AND RUN ("compile_and_run_directions.txt")

    9)  Script to compile source and tests automatically ("MakeMe.sh")

    10) Script to run tests automatically ("TestMe.sh")

<br><br>

*Note: It is recommended that you run unit tests in an IDE. I used IntelliJ.
