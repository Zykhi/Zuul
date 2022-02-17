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
public class Game {
    private Room aCurrentRoom;
    private Parser aParser;
    private HashMap<String, Room> aRooms;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        this.createRooms();
        this.aParser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {

        this.aRooms = new HashMap<String, Room>();

        Room vOutside, vCatacombs, vLobby, vTreasure, vBoss1Room, vBoss2Room, vBoss3Room;

        vOutside = new Room("outside the dungeon");
        vCatacombs = new Room("in the catacombs");
        vLobby = new Room("the main room of the dungeon");
        vTreasure = new Room("an empty room"); // or not ;)
        vBoss1Room = new Room("boss room 1"); // need change desc of boss room
        vBoss2Room = new Room("boss room 2");
        vBoss3Room = new Room("boss room 3");

        aRooms.put("Outside", vOutside);
        aRooms.put("Catacombs", vCatacombs);
        aRooms.put("Lobby", vLobby);
        aRooms.put("Treasure", vTreasure);
        aRooms.put("Boss1Room", vBoss1Room);
        aRooms.put("Boss1Room", vBoss2Room);
        aRooms.put("Boss1Room", vBoss3Room);

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

        this.aCurrentRoom = vOutside;
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {
        printWelcome();
        boolean vFinished = false;
        Command vCommand;

        while (!vFinished) {
            vCommand = aParser.getCommand();
            vFinished = processCommand(vCommand);

        }
        System.out.println("Goodbye knight");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println("Welcome to Zuul GOTY Edition !");
        System.out.println("Zuul GOTY Edition is a new, incredibly and fantastic adventure game.");
        System.out.println("Type 'help' if you need help.");
        printLocationInfo();
    }

    /**
     * This method print the info of the room with the exit when you enter on it
     */
    private void printLocationInfo() {
        System.out.println(aCurrentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param pCommand
     * @return If this command ends the game, true is returned, otherwise false is
     *         returned.
     */
    private boolean processCommand(final Command pCommand) {
        if (pCommand.isUnknown() == true) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (pCommand.getCommandWord().equals("quit")) {
            return this.quit(pCommand);
        } else if (pCommand.getCommandWord().equals("go")) {
            this.goRoom(pCommand);
            return false;
        } else if (pCommand.getCommandWord().equals("help")) {
            this.printHelp();
            return false;
        } else if (pCommand.getCommandWord().equals("look")) {
            if (pCommand.hasSecondWord()) {
                System.out.println("I don't know how to look here");
                return false;
            } else {
                this.look();
                return false;
            }
        } else if (pCommand.getCommandWord().equals("eat")) {
            this.eat();
            return false;
        } else {
            return false;
        }
    }

    // implementations of user commands:

    /**
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * 
     * @param pDirection
     */
    private void goRoom(final Command pDirection) {
        if (!pDirection.hasSecondWord()) {
            System.out.println("Go where ?");
            return;
        }

        String vDirection = pDirection.getSecondWord();
        Room vNextRoom = this.aCurrentRoom.getExit(vDirection);

        if (vNextRoom == null) {
            System.out.println("\n" + "there is no door!");
        }

        else {
            this.aCurrentRoom = vNextRoom;
            printLocationInfo();
        }
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You leave the fight.");
        System.out.println("You wander around the dungeon.");
        System.out.println("");
        System.out.println("Your command words are:");
        aParser.getCommandString();
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     * @param pQuit
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(final Command pQuit) {
        if (pQuit.hasSecondWord() == true) {
            System.out.println("Quit what ?");
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method print the info of the room with the exit when you wrote in a chat
     */
    private void look() {
        printLocationInfo();
    }

    private void eat() {
        System.out.println("You have eaten now and you are not hungry any more.");
    }
}// Game