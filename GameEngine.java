import java.util.HashMap;

/**
 * This class is part of the "Zuul GOTY Edition" application.
 * "Zuul GOTY Edition" is a very simple, text based adventure game.
 * 
 * To play this game, create an instance of this class and call the "play"
 * method.
 * 
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game. It also evaluates and
 * executes the commands that the parser returns.
 *
 * @author Michael Kolling and David J. Barnes + D.Bureau + C.Diouy
 * @version 2008.03.30 + 2019.09.25 + 2022.02.11
 */
public class GameEngine {
    private Room aCurrentRoom;
    private Item aItem;
    private Parser aParser;
    private HashMap<String, Room> aRooms;
    private UserInterface aGui;

    /**
     * Create the game and initialise its internal map.
     */
    public GameEngine() {
        this.createRooms();
        this.aParser = new Parser();
    }

    public void setGUI(final UserInterface pUserInterface) {
        this.aGui = pUserInterface;
        this.printWelcome();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {

        this.aRooms = new HashMap<String, Room>();

        Room vOutside, vCatacombs, vLobby, vTreasure, vBoss1Room, vBoss2Room, vBoss3Room;

        vOutside = new Room("outside the dungeon", "gameImages/outside.gif");
        vCatacombs = new Room("in the catacombs", "gameImages/catacombs.gif");
        vLobby = new Room("the main room of the dungeon", "gameImages/lobby.gif");
        vTreasure = new Room("an empty room", "gameImages/treasure.gif"); // or not ;)
        vBoss1Room = new Room("boss room 1", "gameImages/boss1.gif"); // need change desc of boss room
        vBoss2Room = new Room("boss room 2", "gameImages/boss2.gif");
        vBoss3Room = new Room("boss room 3", "gameImages/boss3.gif");

        aRooms.put("Outside", vOutside);
        aRooms.put("Catacombs", vCatacombs);
        aRooms.put("Lobby", vLobby);
        aRooms.put("Treasure", vTreasure);
        aRooms.put("Boss1Room", vBoss1Room);
        aRooms.put("Boss1Room", vBoss2Room);
        aRooms.put("Boss1Room", vBoss3Room);

        // exit
        vOutside.setExits("down", vLobby);

        vCatacombs.setExits("west", vBoss2Room);

        vLobby.setExits("north", vBoss1Room);
        vLobby.setExits("east", vBoss2Room);
        vLobby.setExits("west", vBoss3Room);

        vTreasure.setExits("east", vBoss3Room);

        vBoss1Room.setExits("south", vLobby);

        vBoss2Room.setExits("east", vCatacombs);
        vBoss2Room.setExits("west", vLobby);

        vBoss3Room.setExits("west", vTreasure);
        vBoss3Room.setExits("east", vLobby);

        // item
        Item vTest = new Item("TestItem", 10, 5, "This is a test item"); // Name, price, weight, desc
        vOutside.setItems("Test", vTest);

        this.aCurrentRoom = vOutside;
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        this.aGui.println("Welcome to Zuul GOTY Edition !");
        this.aGui.println("Zuul GOTY Edition is a new, incredibly and fantastic adventure game.");
        this.aGui.println("Type 'help' if you need help.");
        printLocationInfo();
    }

    /**
     * This method print the info of the room with the exit when you enter on it
     */
    private void printLocationInfo() {
        this.aGui.println(aCurrentRoom.getLongDescription());
        if (this.aCurrentRoom.getImageName() != null) {
            this.aGui.showImage(this.aCurrentRoom.getImageName());
        }
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param pCommand
     * @return If this command ends the game, true is returned, otherwise false is
     *         returned.
     */
    public void interpretCommand(final String pCommandLine) {
        this.aGui.println("> " + pCommandLine);
        Command vCommand = this.aParser.getCommand(pCommandLine);

        if (vCommand.isUnknown()) {
            this.aGui.println("I don't know what you mean...");
            return;
        }

        String vCommandWord = vCommand.getCommandWord();
        if (vCommandWord.equals("help"))
            this.printHelp();
        else if (vCommandWord.equals("go"))
            this.goRoom(vCommand);
        else if (vCommandWord.equals("quit")) {
            if (vCommand.hasSecondWord())
                this.aGui.println("Quit what?");
            else
                this.endGame();
        } else if (vCommandWord.equals("look")) {
            if (vCommand.hasSecondWord()) {
                this.lookItem(vCommand);
            } else {
                this.look();
            }
        } else if (vCommandWord.equals("eat")) {
            this.eat();
        }
    }

    private void endGame() {
        this.aGui.println("Thank you for playing.  Good bye.");
        this.aGui.enable(false);
    }

    // implementations of user commands:

    /**
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * 
     * @param pDirection
     */
    private void goRoom(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            this.aGui.println("Go where?");
            return;
        }

        String vDirection = pCommand.getSecondWord();

        // Try to leave current room.
        Room vNextRoom = this.aCurrentRoom.getExit(vDirection);

        if (vNextRoom == null)
            this.aGui.println("There is no door!");
        else {
            this.aCurrentRoom = vNextRoom;
            printLocationInfo();
        }
    }

    private void lookItem(final Command pCommand) {
        String vItemName = pCommand.getSecondWord();

        Item vItem = aCurrentRoom.getItemName(vItemName);

        if (vItem == null) {
            this.aGui.println("I dont know what do you mean");
        } else {
            this.aGui.println(vItem.getDescription());
        }
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        this.aGui.println("You are lost. You leave the fight.");
        this.aGui.println("You wander around the dungeon.");
        this.aGui.println("");
        this.aGui.println("Your command words are : " + aParser.getCommandString());
    }

    /**
     * This method print the info of the room with the exit when you wrote in a chat
     */
    private void look() {
        printLocationInfo();
    }

    private void eat() {
        this.aGui.println("You have eaten now and you are not hungry any more.");
    }
}// Game