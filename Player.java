import java.io.IOException;
import java.util.Stack;

/**
 * This class implements player
 * 
 * @author C.Diouy
 */
public class Player extends Entity {
    private Room aCurrentRoom;
    private Stack<Room> aPreviousRooms;
    private String aName;
    private UserInterface aGui;
    private Parser aParser;
    private ItemList aInventory;
    private int aMaxWeight;
    private int aMovement;

    /**
     * this constructor init the player
     * 
     * @param pCurrentRoom Start room
     */
    public Player(final Room pCurrentRoom) {
        super(100, 100, 100, 100, 100, 100);
        this.aCurrentRoom = pCurrentRoom;
        this.aName = "Edward";
        this.aPreviousRooms = new Stack<Room>();
        this.aParser = new Parser();
        this.aInventory = new ItemList();
        this.aMaxWeight = 20;
        this.aMovement = 40;
        aMoves[0][0] = "Flamethrower";
        aMoves[0][1] = "90";
        aMoves[0][2] = "95";
        aMoves[0][3] = "physical";
        aMoves[1][0] = "Earthquake";
        aMoves[1][1] = "100";
        aMoves[1][2] = "100";
        aMoves[1][3] = "physical";
        aMoves[2][0] = "Air Slash";
        aMoves[2][1] = "85";
        aMoves[2][2] = "80";
        aMoves[2][3] = "physical";
        aMoves[3][0] = "Rock Slide";
        aMoves[3][1] = "80";
        aMoves[3][2] = "85";
        aMoves[3][3] = "physical";
    }

    /**
     * get the number of movement
     * 
     * @return the nbr of movement
     */
    public int getMovement() {
        return this.aMovement;
    }

    /**
     * Get the current room
     * 
     * @return the current room
     */
    public Room getCurrentRoom() {
        return this.aCurrentRoom;
    }

    /**
     * This function get the max weight of the player can carry
     * 
     * @return max weight of inventory
     */
    public int getMaxWeight() {
        return this.aMaxWeight;
    }

    /**
     * This function is useless right now
     * 
     * @return name of the player
     */
    public String getName() {
        return this.aName;
    }

    /**
     * 
     * This setter set the next room
     * 
     * @param pNextRoom next room
     */
    public void setRoom(final Room pNextRoom) {
        this.aCurrentRoom = pNextRoom;
    }

    /**
     * This getter get the previous room
     * 
     * @return previous room
     */
    public Stack<Room> getPreviousRooms() {
        return this.aPreviousRooms;
    }

    /**
     * GUI Constructor
     * 
     * @param pGui UserInterface
     */
    public void setGUI(UserInterface pGui) {
        this.aGui = pGui;
    }

    // User Command

    /**
     * Print out the opening message for the player.
     */
    protected void printWelcome() {
        this.showImage();
        this.aGui.playDialogSound("welcome");
        this.aGui.playSound("outside", -1);
        this.aGui.slowPrintln("Welcome to the world of Zuul.");
        this.aGui.slowPrintln("You are Edward, the hero of this story.");
        this.aGui.slowPrintln(
                "You are a knight, the war has been raging for decades and you cannot sleep. You decide to go out to clear your mind by walking away from the castle. You come across a waterfall that hides a cave, mist emanating from it.");
        this.aGui.slowPrintln("It is at this moment that your adventure takes all its meaning.");
        this.aGui.slowPrintln("Good luck knight. If you need help, let me know.");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.aGui.println("Type '" + CommandWord.HELP.toString() + "' if you need help.");
        this.aGui.println("You have 20 minutes to escape from the dungeon before being trapped forever");
        this.printLocationInfo();
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    protected void printHelp() {
        this.aGui.println("You are lost. You leave the fight.");
        this.aGui.println("You wander around the dungeon.");
        this.aGui.println("");
        this.aGui.println(aParser.getCommandString());
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * 
     * @param pDirection use for check if there are second word
     */
    protected void goRoom(final Command pDirection) {
        if (!pDirection.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            this.aGui.println("Go where?");
            return;
        }
        this.getPreviousRooms().push(this.getCurrentRoom());

        String vDirection = pDirection.getSecondWord();
        Door vDoor = this.getCurrentRoom().getDoor(vDirection);

        // Try to leave current room.
        Room vNextRoom = this.getCurrentRoom().getExit(vDirection);
        if (vNextRoom == null) {
            this.aGui.println("There is no door!");
        } else if (vDoor != null) {
            if (vDoor.isTrap()) {
                this.aGui.println("its a trap");
                clearStack();
                return;
            }
        } else {
            changeRoom(vNextRoom);
        }
    }

    /**
     * This method is called when the player change the current room
     * 
     * @param vNextRoom the next room
     */
    private void changeRoom(Room vNextRoom) {
        this.setRoom(vNextRoom);
        showImage();
        printLocationInfo();
        if (aCurrentRoom.getCharacter() != null) {
            this.aGui.clearDialogArea();
            this.aGui.showCharacterPanel();
            showCharacter();
            printCharacterDialog();
            // this.aGui.playDialogSound(this.aCurrentRoom.getCharacterName());
        } else {
            this.aGui.hideCharacterPanel();
        }
        this.aGui.playRoomSound();
        aMovement -= 1;
    }

    /**
     * This method print the info of the room with the exit when you wrote in a chat
     * when there isnt second word, if there is it's print information about item
     * 
     * @param pCommand to check if there is second word to change between look and
     *                 look item
     */
    protected void look(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            this.lookItem(pCommand);
        } else {
            showImage();
            printLocationInfo();
        }
    }

    /**
     * This method print the description of the item passed as a param
     * 
     * @param pItemName to get the item name
     */
    protected void lookItem(final Command pItemName) {
        String vItemName = pItemName.getSecondWord();

        Item vItem = this.getCurrentRoom().getItemName(vItemName);

        if (vItem == null) {
            this.aGui.println("I dont know what do you mean");
        } else {
            this.aGui.println(vItem.toString());
        }
    }

    /**
     * This method is to eat a cookie (for the moment)
     * 
     * @param pCommand to be sure there is a second word
     */
    protected void eat(final Command pCommand) {
        String vItemName = pCommand.getSecondWord();
        Item vItem = this.aInventory.getItemName(vItemName);
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("what do you want to eat");
        } else {
            if (vItemName.equals("Cookie") && this.aInventory.getItemList().containsValue(vItem)) {
                this.aMaxWeight *= 2;
                this.aInventory.removeItem(vItemName, vItem);
                this.aInventory.removeWeight(vItem.getWeight());
                this.aGui.println("You eat a cookie");
                showInventory();
            } else {
                this.aGui.println("You cant eat that");
            }
        }
    }

    /**
     * This method is to return to the previous room to the starting room
     * 
     * @param pCommand to be sure there is no second word
     */
    protected void back(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            this.aGui.println("it's impossible");
        } else if (this.getPreviousRooms().empty()) { // was aPreviousRooms.empty()
            this.aGui.println("you cant back");
        } else {
            Room vPreviousRoom = this.getPreviousRooms().pop(); // was aPreviousRooms.pop()
            changeRoom(vPreviousRoom);
        }
    }

    /**
     * This method is to take an item
     * 
     * @param pItemName name of the item to take
     */
    protected void take(final Command pItemName) {
        String vItemName = pItemName.getSecondWord();
        Item vItem = this.aCurrentRoom.getItemName(vItemName);
        if (!pItemName.hasSecondWord()) {

        } else if (vItem == null) {
            this.aGui.println("This is not here");
        } else if (this.aInventory.getWeight() + vItem.getWeight() > this.aMaxWeight) {
            this.aGui.println("Your inventory is full. Drop some items");
        } else {

            this.aInventory.addItem(vItemName, vItem);
            this.aInventory.addWeight(vItem.getWeight());
            this.aCurrentRoom.removeItem(vItemName, vItem);
            showInventory();
            this.aGui.println(this.aCurrentRoom.getItemString());
        }
    }

    /**
     * This method is to drop an item
     * 
     * @param pItemName name of the item to drop
     */
    protected void drop(final Command pItemName) {
        String vItemName = pItemName.getSecondWord();
        Item vItem = this.aInventory.getItemName(vItemName);
        if (!pItemName.hasSecondWord()) {

        } else if (vItem == null) {
            this.aGui.println("You dont have this");
        } else {

            this.aInventory.removeItem(vItemName, vItem);
            this.aInventory.removeWeight(vItem.getWeight());
            this.aCurrentRoom.addItem(vItemName, vItem);
            showInventory();
            this.aGui.println(this.aCurrentRoom.getItemString());
        }
    }

    /**
     * This method print all info about inventory of the player
     */
    protected void showInventory() {
        this.aGui.println(this.aInventory.getInventoryString());
        this.aGui.println(this.aInventory.getWeightString() + this.aMaxWeight + "kg");
    }

    /**
     * This method charge the beamer
     */
    public void charge() {
        Beamer vBeamer = (Beamer) this.aInventory.getItemName("teleporter");
        if (this.aInventory.contain(vBeamer)) {
            vBeamer.charge(this.aCurrentRoom);
            this.aGui.println("Teleporter is charged");
        } else {
            this.aGui.println("You dont have teleporter");
        }
    }

    /**
     * This method fire the beamer
     */
    public void fire() {
        Beamer vBeamer = (Beamer) this.aInventory.getItemName("teleporter");
        if (this.aInventory.contain(vBeamer)) {
            if (vBeamer.isCharged()) {
                this.setRoom(vBeamer.fire());
                this.aInventory.removeItem("teleporter", vBeamer);
                this.aGui.println("You have been teleported. Teleporter is destroyed");
                printLocationInfo();
                showInventory();
            } else {
                this.aGui.println("teleporter isnt loaded.");
            }
        } else {
            this.aGui.println("You dont have teleporter.");
        }
    }

    /**
     * This method write in UI when you left the menu
     */
    public void exit() {
        this.aGui.println("you have left menu");
    }

    public void fight() throws IOException {
        Entity vPlayer = this;
        Entity vEnemy = aCurrentRoom.getCharacter();

        this.aGui.println("Let battle begin");
        Entity vPlayer1;
        Entity vPlayer2;
        if (vPlayer.aSpeed > vEnemy.aSpeed) {
            vPlayer1 = vPlayer;
            vPlayer2 = vEnemy;
        } else {
            vPlayer1 = vEnemy;
            vPlayer2 = vPlayer;
        }
        this.aGui.println(vPlayer1.aName + " starts");
        while (true) {
            this.aGui.println("\n\n" + vPlayer1.aName + "\nHP : " + vPlayer1.aHP + "\n");
            this.aGui.println("Moves:");
            this.aGui.println(vPlayer1.getMoves());
            this.aGui.println("\n\n" + vPlayer2.aName + "\nHP : " + vPlayer2.aHP + "\n");
            this.aGui.println("Moves:");
            this.aGui.println(vPlayer2.getMoves());
            this.aGui.println("\n\n" + vPlayer1.aName + ", choose a move (number)");
            int vMove1 = Integer.parseInt(this.aGui.getEntryField());
            vMove1--;
            this.aGui.println("\n\n" + vPlayer2.aName + ", choose a move (number)");
            int vMove2 = Integer.parseInt(this.aGui.getEntryField());
            vMove2--;

            vPlayer1.calculateDamage(vPlayer2, vMove1);
            if (vPlayer2.aHP <= 0) {
                this.aGui.println(vPlayer2.aName + " is defeated!\n" + vPlayer1.aName + " wins!");

            }
            vPlayer2.calculateDamage(vPlayer1, vMove2);
            if (vPlayer1.aHP <= 0) {
                this.aGui.println(vPlayer1.aName + " is defeated!\n" + vPlayer2.aName + " wins!");
            }
        }
    }

    /**
     * This method print the info of the room with the exit when you enter on it
     */
    protected void printLocationInfo() {
        this.aGui.println(this.getCurrentRoom().getLongDescription());
    }

    private void printCharacterDialog() {
        this.aGui.slowPrintEntity(this.getCurrentRoom().getDialog());
    }

    /**
     * This method show the image of the room
     */
    protected void showImage() {
        if (this.getCurrentRoom().getImageName() != null) {
            this.aGui.showImage(this.getCurrentRoom().getImageName());
        }
    }

    protected void showCharacter() {
        if (this.getCurrentRoom().getCharacter().getImageName() != null) {
            this.aGui.showEntityImage(this.getCurrentRoom().getCharacter().getImageName());
        }
    }

    /**
     * This method clear the stack
     */
    public void clearStack() {
        this.aPreviousRooms.clear();
    }

    /**
     * This function get the current room Item
     * 
     * @return String with all items in the room
     */
    public String getCurrentRoomItemsString() {
        return this.aCurrentRoom.getItemString();
    }

    /**
     * This function get items in inventory
     * 
     * @return a String with items in inventory
     */
    public String getCurrentInventoryItemsString() {
        return this.aInventory.getInventoryString();
    }
}
