import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

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
    private Player aPlayer;
    private Parser aParser;
    private HashMap<String, Room> aRooms;
    private UserInterface aGui;

    /**
     * Create the game and initialise its internal map.
     */
    public GameEngine() {
        this.createRooms();
        this.createPlayer();
        this.createItems();
        this.aParser = new Parser();
    }

    /**
     * setup the user interface
     * 
     * @param pUserInterface need user interface for gui
     */
    public void setGUI(final UserInterface pUserInterface) {
        this.aGui = pUserInterface;
        this.aPlayer.setGUI(pUserInterface);
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
        aRooms.put("Boss2Room", vBoss2Room);
        aRooms.put("Boss3Room", vBoss3Room);

        // exit
        vOutside.setExit("down", vLobby);

        vCatacombs.setExit("west", vBoss2Room);

        vLobby.setExit("north", vBoss1Room);
        vLobby.setExit("east", vBoss2Room);
        vLobby.setExit("west", vBoss3Room);

        vTreasure.setExit("east", vBoss3Room);

        vBoss1Room.setExit("south", vLobby);

        vBoss2Room.setExit("east", vCatacombs);
        vBoss2Room.setExit("west", vLobby);

        vBoss3Room.setExit("west", vTreasure);
        vBoss3Room.setExit("east", vLobby);

    }

    /**
     * This method init the player
     */
    private void createPlayer() {
        this.aPlayer = new Player(this.aRooms.get("Outside"), "Edward");
    }

    /**
     * This method init item in the room
     */
    private void createItems() {
        Item vTest = new Item("TestItem", 10, 5, "This is a test item"); // Name, price, weight, desc
        Item vTest2 = new Item("TestItem2", 20, 10, "This is a test item 2"); // Name, price, weight, desc
        this.aRooms.get("Outside").addItem("Test", vTest);
        this.aRooms.get("Outside").addItem("Test2", vTest2);

        Item vWarmogArmor = new Item("Warmog's Armor", 0, 40, "This is the armor of Warmog the Giant");
        this.aRooms.get("Boss1Room").addItem("Warmog's_Armor", vWarmogArmor);

        Item vBOTRK = new Item("Blade Of The Ruined King", 0, 20,
                "This is the blade of Viego, it weighs nothing compared to its burden");
        this.aRooms.get("Boss2Room").addItem("Blade_Of_The_Ruined_King", vBOTRK);

        Item vFrostFireGauntlet = new Item("Frostfire Gauntlet", 0, 10, "This is the last artefact of the dungeon");
        this.aRooms.get("Boss3Room").addItem("Frostfire_Gauntlet", vFrostFireGauntlet);

        Item vWeddingRing = new Item("Wedding Ring", 0, 0, "This is a wedding ring, it's will be usefull");
        this.aRooms.get("Catacombs").addItem("Wedding_ring", vWeddingRing);

        Item vMagicCookie = new Item("Cookie", 0, 0, "This is a magic cookie");
        this.aRooms.get("Treasure").addItem("Cookie", vMagicCookie);
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        this.aGui.println("Welcome to Zuul GOTY Edition !");
        this.aGui.println("Zuul GOTY Edition is a new, incredibly and fantastic adventure game.");
        this.aGui.println("Type 'help' if you need help.");
        this.aPlayer.printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param pCommandLine command who's enter by the player
     */
    public void interpretCommand(final Command pCommandLine) {

        this.aGui.println("> " + aGui.getEntryField());

        CommandWord vCommandWord = pCommandLine.getCommandWord();

        if (pCommandLine.isUnknown()) {
            this.aGui.println("I don't know what you mean...");
        }

        try {

            if (vCommandWord == CommandWord.HELP) {
                this.aPlayer.printHelp();
            } else if (vCommandWord == CommandWord.GO) {
                this.aPlayer.goRoom(pCommandLine);
            } else if (vCommandWord == CommandWord.QUIT) {
                if (pCommandLine.hasSecondWord()) {
                    this.aGui.println("Quit what?");
                } else {
                    this.endGame();
                }
            } else if (vCommandWord == CommandWord.LOOK) {
                this.aPlayer.look(pCommandLine);
            } else if (vCommandWord == CommandWord.EAT) {
                this.aPlayer.eat(pCommandLine);
            } else if (vCommandWord == CommandWord.BACK) {
                this.aPlayer.back(pCommandLine);
            } else if (vCommandWord == CommandWord.TEST) {
                this.test(pCommandLine);
            } else if (vCommandWord == CommandWord.TAKE) {
                this.aPlayer.take(pCommandLine);
            } else if (vCommandWord == CommandWord.DROP) {
                this.aPlayer.drop(pCommandLine);
            } else if (vCommandWord == CommandWord.INVENTORY) {
                this.aPlayer.showInventory();
            }
        } catch (Exception pE) {
            // use try catch to avoid error
            // System.out.println(pE.getMessage());
        }
    }

    /**
     * End the game
     */
    private void endGame() {
        this.aGui.println("Thank you for playing.  Good bye.");
        this.aGui.enable(false);
    }

    /**
     * This method is used to test game command
     * 
     * @param pFile test file
     */
    private void test(Command pFile) {
        if (pFile.hasSecondWord()) {
            try {
                String vFile = pFile.getSecondWord();
                Scanner vScanner = new Scanner(new File(vFile + ".txt"));
                String vCommandString = vScanner.nextLine();
                while (vScanner.hasNextLine()) {
                    Command vCommand = aParser.getCommand(vCommandString);
                    interpretCommand(vCommand);
                    vCommandString = vScanner.nextLine();
                }
            } catch (final FileNotFoundException pE) {
                this.aGui.println(pE.getMessage());
            }
        } else {
            this.aGui.println("This command need a second word");
        }
    }
}// Game