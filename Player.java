import java.util.Stack;

public class Player {
    private Room aCurrentRoom;
    private Stack<Room> aPreviousRooms;
    private String aName;
    private UserInterface aGui;
    private Parser aParser;
    private ItemList aInventory;
    private int aMaxWeight;
    private int aMovement;

    public Player(final Room pCurrentRoom, final String pName) {
        this.aCurrentRoom = pCurrentRoom;
        this.aName = pName;
        this.aPreviousRooms = new Stack<Room>();
        this.aParser = new Parser();
        this.aInventory = new ItemList();
        this.aMaxWeight = 20;
        this.aMovement = 20;
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
            this.setRoom(vNextRoom);
            printLocationInfo();
            aMovement -= 1;

        }
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
            if (vItemName.equals("Cookie") && this.aInventory.getItemList().contain(vItem)) {
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
            this.setRoom(vPreviousRoom); // was aCurrentRoom = vPreviousRoom
            printLocationInfo();
            aMovement -= 1;
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
            this.aGui.println("What do you want to take");
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
            this.aGui.println("What do you want to drop");
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

    public void charge() {
        Beamer vBeamer = (Beamer) this.aInventory.getItemName("teleporter");
        if (this.aInventory.contain(vBeamer)) {
            vBeamer.charge(this.aCurrentRoom);
            this.aGui.println("Teleporter is charged");
        } else {
            this.aGui.println("You dont have teleporter");
        }
    }

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
     * This method print the info of the room with the exit when you enter on it
     */
    protected void printLocationInfo() {
        this.aGui.println(this.getCurrentRoom().getLongDescription());
        if (this.getCurrentRoom().getImageName() != null) {
            this.aGui.showImage(this.getCurrentRoom().getImageName());
        }
    }

    /**
     * This method clear the stack
     */
    public void clearStack() {
        this.aPreviousRooms.clear();
    }

}
