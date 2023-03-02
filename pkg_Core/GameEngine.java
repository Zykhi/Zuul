package pkg_Core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import org.json.simple.parser.ParseException;
import pkg_Command.Command;
import pkg_Command.CommandWord;
import pkg_Command.Parser;
import pkg_Entity.Entity;
import pkg_Entity.Garret;
import pkg_Entity.Hazelgash;
import pkg_Entity.Player;
import pkg_Entity.Viego;
import pkg_Entity.Warmog;
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
  private JSONReader aJsonReader;
  private HashMap<String, Room> aRooms;
  private ArrayList<TransporterRoom> aTransporterRoom;
  private UserInterface aGui;
  private boolean aTest;

  /**
   * Create the game and initialise its internal map.
   * @throws ParseException
   * @throws IOException
   * @throws FileNotFoundException
   */
  public GameEngine() {
    this.aJsonReader = new JSONReader();
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
    Room vVictory = new Room("", "");

    Room vRoom1 = new Room(
      aJsonReader.getRoom1Description(),
      aJsonReader.getRoom1Image()
    );
    Room vRoom2 = new Room(
      aJsonReader.getRoom2Description(),
      aJsonReader.getRoom2Image()
    );
    Room vRoom3 = new Room(
      aJsonReader.getRoom3Description(),
      aJsonReader.getRoom3Image()
    );
    Room vRoom4 = new Room(
      aJsonReader.getRoom4Description(),
      aJsonReader.getRoom4Image()
    ); // or not ;)
    Room vRoom5 = new Room(
      aJsonReader.getRoom5Description(),
      aJsonReader.getRoom5Image()
    );
    Room vRoom6 = new Room(
      aJsonReader.getRoom6Description(),
      aJsonReader.getRoom6Image()
    );
    Room vRoom7 = new Room(
      aJsonReader.getRoom7Description(),
      aJsonReader.getRoom7Image()
    );

    // menu room
    aRooms.put("mainmenu", vMainMenu);
    aRooms.put("gameover", vGameOver);
    aRooms.put("victory", vVictory);

    // real room
    aRooms.put(aJsonReader.getRoom1Name(), vRoom1);
    aRooms.put(aJsonReader.getRoom2Name(), vRoom2);
    aRooms.put(aJsonReader.getRoom3Name(), vRoom3);
    aRooms.put(aJsonReader.getRoom4Name(), vRoom4);
    aRooms.put(aJsonReader.getRoom5Name(), vRoom5);
    aRooms.put(aJsonReader.getRoom6Name(), vRoom6);
    aRooms.put(aJsonReader.getRoom7Name(), vRoom7);

    // exit

    vMainMenu.setExit("play", vRoom1);

    //Room 1 exits
    vRoom1.setExit("north", aRooms.get(aJsonReader.getRoom1NorthExit()));
    vRoom1.setExit("south", aRooms.get(aJsonReader.getRoom1SouthExit()));
    vRoom1.setExit("east", aRooms.get(aJsonReader.getRoom1EastExit()));
    vRoom1.setExit("west", aRooms.get(aJsonReader.getRoom1WestExit()));
    vRoom1.setExit("up", aRooms.get(aJsonReader.getRoom1UpExit()));
    vRoom1.setExit("down", aRooms.get(aJsonReader.getRoom1DownExit()));

    //Room 2 exits
    vRoom2.setExit("north", aRooms.get(aJsonReader.getRoom2NorthExit()));
    vRoom2.setExit("south", aRooms.get(aJsonReader.getRoom2SouthExit()));
    vRoom2.setExit("east", aRooms.get(aJsonReader.getRoom2EastExit()));
    vRoom2.setExit("west", aRooms.get(aJsonReader.getRoom2WestExit()));
    vRoom2.setExit("up", aRooms.get(aJsonReader.getRoom2UpExit()));
    vRoom2.setExit("down", aRooms.get(aJsonReader.getRoom2DownExit()));

    //Room 3 exits
    vRoom3.setExit("north", aRooms.get(aJsonReader.getRoom3NorthExit()));
    vRoom3.setExit("south", aRooms.get(aJsonReader.getRoom3SouthExit()));
    vRoom3.setExit("east", aRooms.get(aJsonReader.getRoom3EastExit()));
    vRoom3.setExit("west", aRooms.get(aJsonReader.getRoom3WestExit()));
    vRoom3.setExit("up", aRooms.get(aJsonReader.getRoom3UpExit()));
    vRoom3.setExit("down", aRooms.get(aJsonReader.getRoom3DownExit()));

    //Room 4 exits
    vRoom4.setExit("north", aRooms.get(aJsonReader.getRoom4NorthExit()));
    vRoom4.setExit("south", aRooms.get(aJsonReader.getRoom4SouthExit()));
    vRoom4.setExit("east", aRooms.get(aJsonReader.getRoom4EastExit()));
    vRoom4.setExit("west", aRooms.get(aJsonReader.getRoom4WestExit()));
    vRoom4.setExit("up", aRooms.get(aJsonReader.getRoom4UpExit()));
    vRoom4.setExit("down", aRooms.get(aJsonReader.getRoom4DownExit()));

    //Room 5 exits
    vRoom5.setExit("north", aRooms.get(aJsonReader.getRoom5NorthExit()));
    vRoom5.setExit("south", aRooms.get(aJsonReader.getRoom5SouthExit()));
    vRoom5.setExit("east", aRooms.get(aJsonReader.getRoom5EastExit()));
    vRoom5.setExit("west", aRooms.get(aJsonReader.getRoom5WestExit()));
    vRoom5.setExit("up", aRooms.get(aJsonReader.getRoom5UpExit()));
    vRoom5.setExit("down", aRooms.get(aJsonReader.getRoom5DownExit()));

    //Room 6 exits
    vRoom6.setExit("north", aRooms.get(aJsonReader.getRoom6NorthExit()));
    vRoom6.setExit("south", aRooms.get(aJsonReader.getRoom6SouthExit()));
    vRoom6.setExit("east", aRooms.get(aJsonReader.getRoom6EastExit()));
    vRoom6.setExit("west", aRooms.get(aJsonReader.getRoom6WestExit()));
    vRoom6.setExit("up", aRooms.get(aJsonReader.getRoom6UpExit()));
    vRoom6.setExit("down", aRooms.get(aJsonReader.getRoom6DownExit()));

    //Room 7 exits
    vRoom7.setExit("north", aRooms.get(aJsonReader.getRoom7NorthExit()));
    vRoom7.setExit("south", aRooms.get(aJsonReader.getRoom7SouthExit()));
    vRoom7.setExit("east", aRooms.get(aJsonReader.getRoom7EastExit()));
    vRoom7.setExit("west", aRooms.get(aJsonReader.getRoom7WestExit()));
    vRoom7.setExit("up", aRooms.get(aJsonReader.getRoom7UpExit()));
    vRoom7.setExit("down", aRooms.get(aJsonReader.getRoom7DownExit()));
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
    Item vPotion = new Potion(aJsonReader.getPotionHealing());
    this.aRooms.get(aJsonReader.getPotionLocation())
      .addItem(aJsonReader.getPotionName(), vPotion);

    Item vItem1 = new Item(
      aJsonReader.getItem1Name(),
      aJsonReader.getItem1Price(),
      aJsonReader.getItem1Weight(),
      aJsonReader.getItem1Description(),
      aJsonReader.getItem1Fightable()
    );
    this.aRooms.get(aJsonReader.getItem1Location())
      .addItem(aJsonReader.getItem1Name(), vItem1);

    Item vItem2 = new Item(
      aJsonReader.getItem2Name(),
      aJsonReader.getItem2Price(),
      aJsonReader.getItem2Weight(),
      aJsonReader.getItem2Description(),
      aJsonReader.getItem2Fightable()
    );
    this.aRooms.get(aJsonReader.getItem2Location()).addItem(aJsonReader.getItem2Name(), vItem2);

    Item vItem3 = new Item(
      aJsonReader.getItem3Name(),
      aJsonReader.getItem3Price(),
      aJsonReader.getItem3Weight(),
      aJsonReader.getItem3Description(),
      aJsonReader.getItem3Fightable()
    );
    this.aRooms.get(aJsonReader.getItem3Location()).addItem(aJsonReader.getItem3Name(), vItem3);

    Item vItem4 = new Item(
      aJsonReader.getItem4Name(),
      aJsonReader.getItem4Price(),
      aJsonReader.getItem4Weight(),
      aJsonReader.getItem4Description(),
      aJsonReader.getItem4Fightable()
    );
    this.aRooms.get(aJsonReader.getItem4Location()).addItem(aJsonReader.getItem4Name(), vItem4);

    Item vItem5 = new Item(
      aJsonReader.getItem5Name(),
      aJsonReader.getItem5Price(),
      aJsonReader.getItem5Weight(),
      aJsonReader.getItem5Description(),
      aJsonReader.getItem5Fightable()
    );
    this.aRooms.get(aJsonReader.getItem5Location()).addItem(aJsonReader.getItem5Name(), vItem5);
  }

  /**
   * This method init door in the room
   */
  private void createDoor() {
    Door vTrapLobby = new Door(true);
    this.aRooms.get("lobby").addDoor("up", vTrapLobby);
  }

  /**
   * This method init the character
   */
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
    if (this.aPlayer.getArtefactCounter() == 0) {
      this.victory();
    } else if (this.aGui.isTimerEnd()) {
      this.gameOver();
      this.aGui.stopTimer();
    } else if (this.getPlayerHP() <= 0) {
      this.gameOver();
    } else if (aPlayer.getMovement() <= 0) {
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
          case GIVE:
            this.aPlayer.give(pCommandLine);
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
   * Victory
   */
  private void victory() {
    this.aGui.println("Victory");
    this.aGui.enable(false);
    this.aPlayer.setRoom(this.aRooms.get("victory"));
    this.aGui.playRoomSound();
    this.aGui.showVictoryPanel();
    this.aGui.startCredit();
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
    String[] vCIIS = aPlayer
      .getCurrentInventoryFightableItemsString()
      .split(" : ");
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
   *
   * @return true or false if devmode is on or off
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
} // Game
