package pkg_Core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import pkg_Command.Command;
import pkg_Command.CommandWord;
import pkg_Command.Parser;
import pkg_Entity.Entity;
import pkg_Entity.Garret;
import pkg_Entity.Hazelgash;
import pkg_Entity.Player;
import pkg_Entity.Viego;
import pkg_Entity.Warmog;
import pkg_Item.Beamer;
import pkg_Item.Item;
import pkg_Item.Potion;
import pkg_Room.Door;
import pkg_Room.Room;
import pkg_Room.TransporterRoom;
import pkg_UI.UserInterface;

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
    private boolean aTest;

    /**
     * Create the game and initialise its internal map.
     */
    public GameEngine() {
        this.createRooms();
        this.createItems();
        this.createDoor();
        this.createPlayer();
        this.createCharacter();
        this.aParser = new Parser();
        this.aTest = false;
    }

    /**
     * setup the user interface
     * 
     * @param pUserInterface need user interface for gui
     */
    public void setGUI(final UserInterface pUserInterface) {
        this.aGui = pUserInterface;
        this.aPlayer.setGUI(pUserInterface);
        this.aGui.playSound("mainmenu", -1);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {

        aRooms = new HashMap<String, Room>();

        this.aTransporterRoom = new ArrayList<TransporterRoom>();

        Room vMainMenu = new Room("", "");
        Room vGameOver = new Room("", "");

        Room vOutside = new Room("outside, the entrance to a cave catches your eye", "gameImages/outside.gif");
        Room vCatacombs = new Room("in the catacombs", "gameImages/catacombs.gif");
        Room vLobby = new Room("the main room of the dungeon", "gameImages/lobby.gif");
        Room vTreasure = new Room("an empty room", "gameImages/treasure.gif"); // or not ;)
        Room vBoss1Room = new Room("entering in the forest of Warmog the giant", "gameImages/boss1.gif");
        Room vBoss2Room = new Room("entering in Viego's castle, he turns his back to you and looks away",
                "gameImages/boss2.gif");
        Room vBoss3Room = new Room(
                "entering in the magic room of Hazelgash, be careful with the lava and the lightning that could hit you",
                "gameImages/boss3.gif");
        TransporterRoom vTestRoom = new TransporterRoom("this is a test room", "gameImages/test.gif", this.aRooms);

        this.aTransporterRoom.add(vTestRoom);

        aRooms.put("mainmenu", vMainMenu);
        aRooms.put("gameover", vGameOver);

        aRooms.put("outside", vOutside);
        aRooms.put("catacombs", vCatacombs);
        aRooms.put("lobby", vLobby);
        aRooms.put("treasure", vTreasure);
        aRooms.put("boss1room", vBoss1Room);
        aRooms.put("boss2room", vBoss2Room);
        aRooms.put("boss3room", vBoss3Room);
        aRooms.put("testroom", vTestRoom);

        // exit
        vMainMenu.setExit("play", vOutside);

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

        vTestRoom.setExit("south", vBoss1Room);

    }

    /**
     * This method init the player
     */
    private void createPlayer() {
        this.aPlayer = new Player(this.aRooms.get("mainmenu"));
    }

    /**
     * This method init item in the room
     */
    private void createItems() {
        Item vTest = new Item("TestItem", 10, 5, "This is a test item", false); // Name, price, weight, desc
        Item vPotion = new Potion(50); // Name, price, weight, desc
        this.aRooms.get("outside").addItem("test", vTest);
        this.aRooms.get("outside").addItem("potion", vPotion);

        Item vWarmogArmor = new Item("warmog's_armor", 0, 40, "This is the armor of Warmog the Giant", false);
        this.aRooms.get("boss1room").addItem("warmog's_armor", vWarmogArmor);

        Item vBOTRK = new Item("Blade_Of_The_Ruined King", 0, 20,
                "This is the blade of Viego, it weighs nothing compared to its burden", false);
        this.aRooms.get("boss2room").addItem("blade_of_the_ruined_king", vBOTRK);

        Item vFrostFireGauntlet = new Item("Frostfire_Gauntlet", 0, 10, "This is the last artefact of the dungeon",
                false);
        this.aRooms.get("boss3room").addItem("frostfire_gauntlet", vFrostFireGauntlet);

        Item vWeddingRing = new Item("wedding_ring", 0, 0, "This is a wedding ring, it's will be usefull", false);
        this.aRooms.get("catacombs").addItem("wedding_ring", vWeddingRing);

        Item vMagicCookie = new Item("cookie", 0, 0, "This is a magic cookie", false);
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

    private void createCharacter() {

        Garret vGarret = new Garret();
        this.aRooms.get("lobby").setCharacter(vGarret);

        Warmog vWarmog = new Warmog();
        this.aRooms.get("boss1room").setCharacter(vWarmog);

        Viego vViego = new Viego();
        this.aRooms.get("boss2room").setCharacter(vViego);

        Hazelgash vHazelgash = new Hazelgash();
        this.aRooms.get("boss3room").setCharacter(vHazelgash);
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param pCommandLine command who's enter by the player
     */
    public void interpretCommand(final Command pCommandLine) {

        CommandWord vCommandWord = pCommandLine.getCommandWord();
        if (aPlayer.getMovement() <= 0) {
            this.gameOver();
        } else if (this.aGui.isTimerEnd()) {
            this.gameOver();
            this.aGui.stopTimer();
        } else if (this.getPlayerHP() <= 0) {
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

                    case EXIT:
                        this.aPlayer.exit();
                        break;

                    case ALEA:
                        this.alea(pCommandLine);
                        break;

                    case FIGHT:
                        this.aPlayer.fight();
                        break;

                    case USE:
                        this.aPlayer.use(pCommandLine);
                        break;

                    default:
                        this.aGui.println("I don't know what you mean...");
                        break;
                }

            } catch (Exception pE) {
                // use try catch to avoid error
            }
        }
    }

    /**
     * End the game
     */
    private void endGame() {
        this.aGui.println("Thank you for playing. Good bye.");
        this.aGui.enable(false);
    }

    /**
     * Game over
     */
    private void gameOver() {
        this.aGui.println("Game over.");
        this.aGui.enable(false);
        this.aPlayer.setRoom(this.aRooms.get("gameover"));
        this.aGui.playRoomSound();
        this.aGui.showGameOverPanel();
    }

    /**
     * This method get the HP of the player
     * 
     * @return the HP of the player
     */
    public int getPlayerHP() {
        return this.aPlayer.getHP();
    }

    /**
     * This method get the max HP of the player
     * 
     * @return the max HP of the player
     */
    public int getMaxPlayerHP() {
        return this.aPlayer.getMaxHP();
    }

    /**
     * This method get the name of the player
     * 
     * @return the name of the player
     */
    public String getPlayerName() {
        return this.aPlayer.getName();
    }

    /**
     * This method get the HP of the enemy
     * 
     * @return the HP of the enemy
     */
    public int getEnemyHP() {
        Entity vEnemy = this.aPlayer.getCurrentRoom().getCharacter();
        int vEnemyHP = vEnemy.getHP();
        return vEnemyHP;
    }

    /**
     * This method get the name of the enemy
     * 
     * @return the name of the enemy
     */
    public String getEnemyName() {
        return this.aPlayer.getEnemyName();
    }

    /**
     * This method is called when the player click on attack 1 button
     */
    public void attack1Button() {
        this.aPlayer.attack1();
    }

    /**
     * This method is called when the player click on attack 2 button
     */
    public void attack2Button() {
        this.aPlayer.attack2();
    }

    /**
     * This method is called when the player click on attack 3 button
     */
    public void attack3Button() {
        this.aPlayer.attack3();
    }

    /**
     * This method is called when the player click on defend button
     */
    public void defendButton() {
        this.aPlayer.defend();
    }

    /**
     * This function get the String of player's moves
     * 
     * @return the String of player's moves
     */
    public String getMovesString() {
        return this.aPlayer.getMovesString();
    }

    /**
     * This function get the items in the room. This function is used for the
     * UserInterface
     * 
     * @return String with all items in the room
     */
    public String getCurrentRoomItemsString() {
        String[] vCRIS = aPlayer.getCurrentRoomItemsString().split(" : ");
        if (vCRIS.length > 1) {
            return vCRIS[1];
        } else {
            return null;
        }
    }

    /**
     * This function get the item in the inventory. This function is used for the
     * UserInterface
     * 
     * @return String with all items in inventory
     */
    public String getCurrentInventoryItemsString() {
        String[] vCIIS = aPlayer.getCurrentInventoryItemsString().split(" : ");
        if (vCIIS.length > 1) {
            return vCIIS[1];
        } else {
            return null;
        }
    }

    /**
     * This function get the fightable item in the inventory. This function is used
     * for the
     * UserInterface
     * 
     * @return String with all fightable items in inventory
     */
    public String getCurrentInventoryFightableItemsString() {
        String[] vCIIS = aPlayer.getCurrentInventoryFightableItemsString().split(" : ");
        if (vCIIS.length > 1) {
            return vCIIS[1];
        } else {
            return null;
        }
    }

    /**
     * This function get the key of the room with the value of the HashMap
     * 
     * @param pHashMap the HashMap
     * @param pRoom    the room in value in the HashMap
     * @return String of the key of the room
     * @author https://www.techiedelight.com/get-map-key-from-value-java/
     */
    private String getKey(HashMap<String, Room> pHashMap, Room pRoom) {
        for (String vKey : pHashMap.keySet()) {
            if (pRoom.equals(pHashMap.get(vKey))) {
                return vKey;
            }
        }
        return null;
    }

    /**
     * This method play the sound of the current room
     */
    public void playRoomSound() {
        HashMap<String, Room> vRooms = aRooms;
        Room vRoom = aPlayer.getCurrentRoom();
        String vCurrentRoomString = getKey(vRooms, vRoom);
        this.aGui.stopSound();
        this.aGui.playSound(vCurrentRoomString, -1);
    }

    /**
     * This method play the sound of the battle
     */
    public void playBattleRoomSound() {
        HashMap<String, Room> vRooms = aRooms;
        Room vRoom = aPlayer.getCurrentRoom();
        String vCurrentRoomString = getKey(vRooms, vRoom);
        this.aGui.stopSound();
        if (this.aGui.isSound() == false) {
            this.aGui.soundOff();
        } else {
            this.aGui.playBattleSound(vCurrentRoomString, -1);
        }
    }

    /**
     * This setter is used to set dev mode
     * 
     * @param pToggle on or off
     */
    public void setDevMode(boolean pToggle) {
        aPlayer.setDevMode(pToggle);
    }

    /**
     * This getter get if we are in dev mode
     */
    public boolean isDevMode() {
        return aPlayer.isDevMode();
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
                Scanner vScanner = new Scanner(new File("testFiles/" + vFile + ".txt"));
                String vCommandString = vScanner.nextLine();
                this.aTest = true;
                while (vScanner.hasNextLine()) {
                    Command vCommand = aParser.getCommand(vCommandString);
                    interpretCommand(vCommand);
                    vCommandString = vScanner.nextLine();
                }
                this.aTest = false;
            } catch (final FileNotFoundException pE) {
                this.aGui.println(pE.getMessage());
            }
        } else {
            this.aGui.println("This command need a second word");
        }
    }

    /**
     * this method is used to set a room for the random room
     * 
     * @param pRoom Room to set for the exit of the random room
     */
    private void alea(final Command pRoom) {
        if (!aTest) {
            this.aGui.println("You need to be in test mode");
        }
        if (pRoom.hasSecondWord() && aTest) {
            String vRoomName = pRoom.getSecondWord();
            if (vRoomName == null) {
                System.out.println("Room dont found");
            }
            for (TransporterRoom vTransporterRoom : this.aTransporterRoom) {
                vTransporterRoom.setNextRoom(vRoomName);
            }
        }
    }
}// Game