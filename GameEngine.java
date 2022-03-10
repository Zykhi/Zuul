import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
    private ArrayList<TransporterRoom> aTransporterRoom;
    private UserInterface aGui;

    /**
     * Create the game and initialise its internal map.
     */
    public GameEngine() {
        this.createRooms();
        this.createPlayer();
        this.createItems();
        this.createDoor();
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
        this.aPlayer.printWelcome();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {

        this.aRooms = new HashMap<String, Room>();
        this.aTransporterRoom = new ArrayList<TransporterRoom>();

        Room vOutside, vCatacombs, vLobby, vTreasure, vBoss1Room, vBoss2Room, vBoss3Room, vTestRoom;

        vOutside = new Room("outside the dungeon", "gameImages/outside.gif");
        vCatacombs = new Room("in the catacombs", "gameImages/catacombs.gif");
        vLobby = new Room("the main room of the dungeon", "gameImages/lobby.gif");
        vTreasure = new Room("an empty room", "gameImages/treasure.gif"); // or not ;)
        vBoss1Room = new Room("boss room 1", "gameImages/boss1.gif"); // need change desc of boss room
        vBoss2Room = new Room("boss room 2", "gameImages/boss2.gif");
        vBoss3Room = new Room("boss room 3", "gameImages/boss3.gif");

        vTestRoom = new TransporterRoom("This is a test room", "gameImages/test.gif");

        aRooms.put("outside", vOutside);
        aRooms.put("catacombs", vCatacombs);
        aRooms.put("lobby", vLobby);
        aRooms.put("treasure", vTreasure);
        aRooms.put("boss1room", vBoss1Room);
        aRooms.put("boss2room", vBoss2Room);
        aRooms.put("boss3room", vBoss3Room);
        aRooms.put("testroom", vTestRoom);

        ((TransporterRoom) vTestRoom).setAllRooms(this.aRooms);

        // exit
        vOutside.setExit("down", vLobby);

        vCatacombs.setExit("west", vBoss2Room);

        vLobby.setExit("up", vOutside);
        vLobby.setExit("north", vBoss1Room);
        vLobby.setExit("east", vBoss2Room);
        vLobby.setExit("west", vBoss3Room);

        vTreasure.setExit("east", vBoss3Room);

        vBoss1Room.setExit("south", vLobby);
        vBoss1Room.setExit("north", vTestRoom);

        vBoss2Room.setExit("east", vCatacombs);
        vBoss2Room.setExit("west", vLobby);

        vBoss3Room.setExit("west", vTreasure);
        vBoss3Room.setExit("east", vLobby);

    }

    /**
     * This method init the player
     */
    private void createPlayer() {
        this.aPlayer = new Player(this.aRooms.get("outside"), "Edward");
    }

    /**
     * This method init item in the room
     */
    private void createItems() {
        Item vTest = new Item("TestItem", 10, 5, "This is a test item"); // Name, price, weight, desc
        Item vTest2 = new Item("TestItem2", 20, 10, "This is a test item 2"); // Name, price, weight, desc
        this.aRooms.get("outside").addItem("test", vTest);
        this.aRooms.get("outside").addItem("test2", vTest2);

        Item vWarmogArmor = new Item("warmog's_armor", 0, 40, "This is the armor of Warmog the Giant");
        this.aRooms.get("boss1room").addItem("warmog's_armor", vWarmogArmor);

        Item vBOTRK = new Item("Blade_Of_The_Ruined King", 0, 20,
                "This is the blade of Viego, it weighs nothing compared to its burden");
        this.aRooms.get("boss2room").addItem("blade_of_the_ruined_king", vBOTRK);

        Item vFrostFireGauntlet = new Item("Frostfire_Gauntlet", 0, 10, "This is the last artefact of the dungeon");
        this.aRooms.get("boss3room").addItem("frostfire_gauntlet", vFrostFireGauntlet);

        Item vWeddingRing = new Item("wedding_ring", 0, 0, "This is a wedding ring, it's will be usefull");
        this.aRooms.get("catacombs").addItem("wedding_ring", vWeddingRing);

        Item vMagicCookie = new Item("cookie", 0, 0, "This is a magic cookie");
        this.aRooms.get("treasure").addItem("cookie", vMagicCookie);

        Beamer vBeamer = new Beamer();
        this.aRooms.get("outside").addItem("teleporter", vBeamer);
    }

    /**
     * This method init door in the room
     */
    private void createDoor() {
        Door vTrapLobby = new Door(true);
        this.aRooms.get("lobby").addDoor("up", vTrapLobby);
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param pCommandLine command who's enter by the player
     */
    public void interpretCommand(final Command pCommandLine) {

        this.aGui.println("> " + aGui.getEntryField());

        CommandWord vCommandWord = pCommandLine.getCommandWord();
        if (aPlayer.getMovement() <= 0) {
            this.gameOver();
        } else {

            try {

                switch (vCommandWord) {
                    case HELP:
                        this.aPlayer.printHelp();
                        getCurrentRoomItemsString();
                        break;

                    case GO:
                        this.aPlayer.goRoom(pCommandLine);
                        break;

                    case QUIT:
                        if (pCommandLine.hasSecondWord()) {
                            this.aGui.println("Quit what?");
                        } else {
                            this.endGame();
                        }
                        break;

                    case LOOK:
                        this.aPlayer.look(pCommandLine);
                        break;

                    case EAT:
                        this.aPlayer.eat(pCommandLine);
                        break;

                    case BACK:
                        this.aPlayer.back(pCommandLine);
                        break;

                    case TEST:
                        this.test(pCommandLine);
                        break;

                    case TAKE:
                        this.aPlayer.take(pCommandLine);
                        break;

                    case DROP:
                        this.aPlayer.drop(pCommandLine);
                        break;

                    case INVENTORY:
                        this.aPlayer.showInventory();
                        break;

                    case CHARGE:
                        this.aPlayer.charge();
                        break;

                    case FIRE:
                        this.aPlayer.fire();
                        break;

                    default:
                        this.aGui.println("I don't know what you mean...");
                        break;
                }

            } catch (Exception pE) {
                // use try catch to avoid error
                // System.out.println(pE.getMessage());
            }
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
     * Game over
     */
    private void gameOver() {
        this.aGui.println("game over.");
        this.aGui.enable(false);
    }

    protected String getCurrentRoomItemsString() {
        String[] vCRIS = aPlayer.getCurrentRoomItemsString().split(" : ");
        if (vCRIS.length > 1) {
            return vCRIS[1];
        } else {
            return null;
        }
    }

    protected String getCurrentInventoryItemsString() {
        String[] vCIIS = aPlayer.getCurrentInventoryItemsString().split(" : ");
        if (vCIIS.length > 1) {
            return vCIIS[1];
        } else {
            return null;
        }
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