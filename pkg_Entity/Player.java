package pkg_Entity;

import java.util.Stack;
import javax.swing.Timer;
import pkg_Command.Command;
import pkg_Command.Parser;
import pkg_Core.JSONReader;
import pkg_Item.Beamer;
import pkg_Item.Item;
import pkg_Item.ItemList;
import pkg_Item.Potion;
import pkg_Room.Door;
import pkg_Room.Room;
import pkg_UI.UserInterface;

/**
 * This class implements player
 *
 * @author C.Diouy
 */
public class Player extends Entity {

  private Room aCurrentRoom;
  private Stack<Room> aPreviousRooms;
  private UserInterface aGui;
  private Parser aParser;
  private JSONReader aJsonReader;
  private ItemList aInventory;
  private int aMaxWeight;
  private int aMovement;
  private int aArtefactCounter;
  private int aPlayerMove;
  private int aEniMove;
  private boolean aDevMode;

  /**
   * this constructor init the player
   *
   * @param pCurrentRoom Start room
   */
  public Player(final Room pCurrentRoom) {
    super(0, 0, 0, 0, 0, 0, true);
    this.aJsonReader = new JSONReader();
    setName(aJsonReader.getPlayerName());
    setHP(aJsonReader.getPlayerHP());
    setMaxHP(aJsonReader.getPlayerMaxHP());
    setDef(aJsonReader.getPlayerDef());
    setSpeDef(aJsonReader.getPlayerSpeDef());
    setAtt(aJsonReader.getPlayerAtt());
    setSpeAtt(aJsonReader.getPlayerSpeAtt());
    setFightable(aJsonReader.getPlayerFightable());
    setMoves(aJsonReader.getPlayerMoves());
    setAttackDamage(aJsonReader.getPlayerAttackDamage());
    setAttackAccuracy(aJsonReader.getPlayerAttackAccuracy());
    setAttackType(aJsonReader.getPlayerAttackType());
    this.aCurrentRoom = pCurrentRoom;
    this.aPreviousRooms = new Stack<Room>();
    this.aParser = new Parser();
    this.aInventory = new ItemList();
    this.aMaxWeight = aJsonReader.getMaxWeight();
    this.aMovement = aJsonReader.getMaxMovement();
    this.aArtefactCounter = aJsonReader.getArtefactNb();
    this.aDevMode = false;
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
  private void printWelcome() {
    String vWelcomeText = aJsonReader.getWelcomeText();
    if (isDevMode()) {
      this.aGui.println(vWelcomeText);
      this.aGui.println(aJsonReader.getEndWelcomeText());
      this.printLocationInfo();
      this.aGui.enable(true);
      this.aGui.startTimer();
    } else {
      if (this.aGui.isSound() == false) {
        this.aGui.soundOff();
      } else {
        this.aGui.playDialogSound("welcome");
      }
      this.aGui.slowPrintln(vWelcomeText);
    }
  }

  /**
   * Print out some help information.
   * Here we print some stupid, cryptic message and a list of the
   * command words.
   */
  public void printHelp() {
    this.aGui.println(aJsonReader.getHelpText());
    this.aGui.println("");
    this.aGui.println(aParser.getCommandString());
    this.aGui.println("");
    this.aGui.println(aJsonReader.getEndHelpText());
  }

  /**
   * Try to go to one direction. If there is an exit, enter the new
   * room, otherwise print an error message.
   *
   * @param pDirection use for check if there are second word
   */
  public void goRoom(final Command pDirection) {
    if (!pDirection.hasSecondWord()) {
      // if there is no second word, we don't know where to go...
      this.aGui.println(aJsonReader.getGoWhere());
      return;
    }
    this.getPreviousRooms().push(this.getCurrentRoom());

    String vDirection = pDirection.getSecondWord();
    Door vDoor = this.getCurrentRoom().getDoor(vDirection);

    // Try to leave current room.
    Room vNextRoom = this.getCurrentRoom().getExit(vDirection);
    if (vNextRoom == null) {
      this.aGui.println(aJsonReader.getNoDoor());
    } else if (vDoor != null) {
      if (vDoor.isTrap()) {
        isTrapDoor();
      }
    } else {
      changeRoom(vNextRoom);
    }
  }

  private void isTrapDoor() {
    this.aGui.println(aJsonReader.getTrap());
    clearStack();
    return;
  }

  /**
   * This method is called when the player change the current room
   *
   * @param vNextRoom the next room
   */
  private void changeRoom(Room vNextRoom) {
    this.setRoom(vNextRoom);
    this.aGui.println("");
    showImage();
    printLocationInfo();
    if (aCurrentRoom.getCharacter() != null) {
      showDialog();
      // this.aGui.playDialogSound(this.aCurrentRoom.getCharacterName());
    } else {
      this.aGui.hideCharacterPanel();
    }
    this.aGui.exitButtonMethod();
    this.aCurrentRoom.updateNbrRoom();
    if (this.aGui.isSound() == false) {
      this.aGui.soundOff();
    } else {
      this.aGui.playRoomSound();
    }
    aMovement -= 1;

    if (this.getMovement() == 40) {
      clearStack();
      int vDelay = 100; // specify the delay for the timer
      Timer vTimer = new Timer(
        vDelay,
        e -> {
          // The following code will be executed once the delay is reached
          this.printWelcome();
        }
      );
      vTimer.setRepeats(false); // make sure the timer only runs once
      vTimer.start();

      if (!isDevMode()) {
        int vDelay2 = 26500; // specify the delay for the timer
        Timer vTimer2 = new Timer(
          vDelay2,
          e -> {
            // The following code will be executed once the delay is reached
            this.aGui.println(aJsonReader.getEndWelcomeText());
            this.printLocationInfo();
            this.aGui.enable(true);
            this.aGui.startTimer();
          }
        );
        vTimer2.setRepeats(false); // make sure the timer only runs once
        vTimer2.start();
      }
    }
  }

  /**
   * This method print the info of the room with the exit when you wrote in a chat
   * when there isnt second word, if there is it's print information about item
   *
   * @param pCommand to check if there is second word to change between look and
   *                 look item
   */
  public void look(final Command pCommand) {
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
      this.aGui.println(aJsonReader.getDontKnow());
    } else {
      this.aGui.println(vItem.toString());
    }
  }

  /**
   * This method is to eat a cookie (for the moment)
   *
   * @param pCommand to be sure there is a second word
   */
  public void eat(final Command pCommand) {
    String vItemName = pCommand.getSecondWord();
    Item vItem = this.aInventory.getItemName(vItemName);
    if (!pCommand.hasSecondWord()) {
      this.aGui.println(aJsonReader.getEatWhat());
    } else {
      if (
        vItemName.equals("cookie") &&
        this.aInventory.getItemList().containsValue(vItem)
      ) {
        this.aMaxWeight *= 2;
        this.aInventory.removeItem(vItemName, vItem);
        this.aInventory.removeWeight(vItem.getWeight());
        this.aGui.println(aJsonReader.getEatCookie());
        showInventory();
      } else {
        this.aGui.println(aJsonReader.getCantEat());
      }
    }
  }

  /**
   * This method is to return to the previous room to the starting room
   *
   * @param pCommand to be sure there is no second word
   */
  public void back(final Command pCommand) {
    if (pCommand.hasSecondWord()) {
      this.aGui.println(aJsonReader.getImpossible());
    } else if (this.getPreviousRooms().empty()) {
      this.aGui.println(aJsonReader.getCantBack());
    } else {
      Room vPreviousRoom = this.getPreviousRooms().pop();
      changeRoom(vPreviousRoom);
    }
  }

  /**
   * This method is to take an item
   *
   * @param pItemName name of the item to take
   */
  public void take(final Command pItemName) {
    String vItemName = pItemName.getSecondWord();
    Item vItem = this.aCurrentRoom.getItemName(vItemName);
    if (!pItemName.hasSecondWord()) {} else if (vItem == null) {
      this.aGui.println(aJsonReader.getDontKnow());
    } else if (
      this.aInventory.getWeight() + vItem.getWeight() > this.aMaxWeight
    ) {
      this.aGui.println(aJsonReader.getInventoryFull());
    } else {
      this.aInventory.addItem(vItemName, vItem);
      this.aInventory.addWeight(vItem.getWeight());
      this.aCurrentRoom.removeItem(vItemName, vItem);
      showInventory();
      this.aGui.println(this.aCurrentRoom.getItemString());

      if (isDevMode()) {
        this.aGui.takeItemAnimation(vItemName, 100);
      } else {
        this.aGui.takeItemAnimation(vItemName, 2000);
      }
    }
  }

  /**
   * This method is to drop an item
   *
   * @param pItemName name of the item to drop
   */
  public void drop(final Command pItemName) {
    String vItemName = pItemName.getSecondWord();
    Item vItem = this.aInventory.getItemName(vItemName);
    if (!pItemName.hasSecondWord()) {} else if (vItem == null) {
      this.aGui.println(aJsonReader.getDontHave());
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
  public void showInventory() {
    this.aGui.println(this.aInventory.getInventoryString());
    this.aGui.println(
        this.aInventory.getWeightString() + this.aMaxWeight + "kg"
      );
  }

  /**
   * This method charge the beamer
   */
  public void charge() {
    Beamer vBeamer = (Beamer) this.aInventory.getItemName("teleporter");
    if (this.aInventory.contain(vBeamer)) {
      vBeamer.charge(this.aCurrentRoom);
      this.aGui.println(aJsonReader.getTeleporterCharged());
    } else {
      this.aGui.println(aJsonReader.getDontHaveTeleporter());
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
        this.aGui.println(aJsonReader.getTeleporterUsed());
        printLocationInfo();
        showInventory();
      } else {
        this.aGui.println(aJsonReader.getTeleporterNotCharged());
      }
    } else {
      this.aGui.println(aJsonReader.getDontHaveTeleporter());
    }
  }

  /**
   * This method is to use an item
   *
   * @param pItemName name of the item to use
   */
  public void use(Command pItemName) {
    Potion vPotion = (Potion) this.aInventory.getItemName("potion");
    String vItemName = pItemName.getSecondWord();
    Item vItem = this.aInventory.getItemName(vItemName);
    if (!pItemName.hasSecondWord()) {
      this.aGui.println(aJsonReader.getSecondWordNeeded());
    } else if (vItem == null) {
      this.aGui.println(aJsonReader.getDontHave());
    } else {
      if (this.aInventory.contain(vPotion)) {
        this.heal(vPotion.getHealingPoint());
        this.aGui.updateBattleUI();
      }
      this.aInventory.removeItem(vItemName, vItem);
      this.aInventory.removeWeight(vItem.getWeight());
    }
  }

  /**
   * This method is called when the player used give command
   *
   * @param pItemName name of the item to give
   */
  public void give(Command pItemName) {
    String vItemName = pItemName.getSecondWord();
    Item vItem = this.aInventory.getItemName(vItemName);
    if (!pItemName.hasSecondWord()) {
      this.aGui.println(aJsonReader.getSecondWordNeeded());
    } else if (vItem == null) {
      this.aGui.println(aJsonReader.getDontHave());
    } else {
      giveArtefactToGarret(vItemName);
      this.aInventory.removeItem(vItemName, vItem);
      this.aInventory.removeWeight(vItem.getWeight());
    }
  }

  /**
   * This method write in UI when you left the menu
   */
  public void exit() {}

  /**
   * This method is called when player want to fight
   */
  public void fight() {
    if (
      this.aCurrentRoom.getCharacter() != null &&
      this.aCurrentRoom.getCharacter().isFightable()
    ) {
      this.aGui.startBattleUI();
      this.aGui.playBattleRoomSound();
      this.aGui.showBattlePanel();
      showFullCharacter();
      this.aGui.printlnBattle(aJsonReader.getStartBattle());
    } else {
      this.aGui.println(aJsonReader.getCantBattle());
    }
  }

  /**
   * This method is called when the player win the fight
   */
  private void victory() {
    this.aGui.slowPrintBattle(aJsonReader.getBattleVictory());
  }

  /**
   * This method is called when the player loose the fight
   */
  private void defeat() {
    this.aGui.slowPrintBattle(aJsonReader.getBattleDefeat());
  }

  private void setPlayerMove(int pMove) {
    aPlayerMove = pMove;
  }

  private void setEniMove(int pMove) {
    aEniMove = pMove;
  }

  public int getPlayerMove() {
    return aPlayerMove;
  }

  public int getEniMove() {
    return aEniMove;
  }

  /**
   * This method is called when the player click on attack 1 button
   */
  private void attack(int pPlayerMove, int pEniMove) {
    this.aGui.clearBattleArea();
    Entity vPlayer = this;
    Entity vEnemy = aCurrentRoom.getCharacter();

    Entity vPlayer1;
    Entity vPlayer2;

    vPlayer1 = vPlayer;
    vPlayer2 = vEnemy;

    int vMove1 = pPlayerMove;
    int vMove2 = pEniMove;

    vPlayer1.attack(vPlayer2, vMove1);
    if (!vPlayer1.isDead() && vPlayer1.isAttacking()) {
      setPlayerMove(vMove1);
    }
    if (vPlayer2.isDead()) {
      this.aGui.updateBattleUI();
      victory();
      return;
    }

    vPlayer2.attack(vPlayer1, vMove2);
    if (!vPlayer2.isDead() && vPlayer2.isAttacking()) {
      setEniMove(vMove2);
    }
    this.aGui.updateBattleUI();
    if (vPlayer1.isDead()) {
      this.aGui.updateBattleUI();
      defeat();
      return;
    }
    this.aGui.exitBattleButton();
  }

  public void attack1() {
    attack(0, 1);
  }

  /**
   * This method is called when the player click on attack 2 button
   */
  public void attack2() {
    attack(1, 1);
  }

  /**
   * This method is called when the player click on attack 3 button
   */
  public void attack3() {
    attack(2, 1);
  }

  /**
   * This method is called when the player click on defend button
   */
  public void defend() {
    this.aGui.clearBattleArea();
    this.improveDefense();
    this.aGui.printlnBattle(aJsonReader.getDefenseImproved());
    this.aGui.printlnBattle(
        aJsonReader.getDefenseIs() +
        this.getDef() +
        aJsonReader.getSpeDefIs() +
        this.getSpeDef()
      );
    this.aGui.printlnBattle("");
    Entity vPlayer = this;
    Entity vEnemy = aCurrentRoom.getCharacter();

    Entity vPlayer1;
    Entity vPlayer2;

    vPlayer1 = vPlayer;
    vPlayer2 = vEnemy;

    int vMove2 = 1;

    vPlayer2.attack(vPlayer1, vMove2);
    setEniMove(vMove2);
    this.aGui.updateBattleUI();
    if (vPlayer1.isDead()) {
      this.aGui.updateBattleUI();
      defeat();
      return;
    }
  }

  /**
   * This method is called when enemy attack
   *
   * @param pMove Number of the move in the table
   */
  public void enemyAttackString(int pMove) {
    this.aGui.clearBattleArea();
    String vName = this.aCurrentRoom.getCharacter().getName();
    Entity vEnemy = this.aCurrentRoom.getCharacter();
    if (isMissed()) {
      this.aGui.slowPrintBattle(vName + aJsonReader.getEniMissed());
      setMissed(false);
      return;
    } else {
      this.aGui.slowPrintBattle(
          vName + aJsonReader.getEniUsed() + vEnemy.getAttackString(pMove)
        );
    }
  }

  /**
   * This method is called when player attack
   *
   * @param pMove Number of the move in the table
   */
  public void playerAttackString(int pMove) {
    this.aGui.clearBattleArea();
    if (isMissed()) {
      this.aGui.slowPrintBattle(aJsonReader.getMissed());
      setMissed(false);
      return;
    } else {
      this.aGui.slowPrintBattle(aJsonReader.getUsed() + getAttackString(pMove));
    }
  }

  /**
   * This method print the info of the room with the exit when you enter on it
   */
  protected void printLocationInfo() {
    this.aGui.println(this.getCurrentRoom().getLongDescription());
  }

  /**
   * This method print the dialog of the character in the room
   */
  private void printCharacterDialog() {
    if (isDevMode()) {
      this.aGui.printlnEntity(this.getCurrentRoom().getDialog());
    } else {
      this.aGui.slowPrintEntity(this.getCurrentRoom().getDialog());
    }
  }

  /**
   * This method show the image of the room
   */
  protected void showImage() {
    if (this.getCurrentRoom().getImageName() != null) {
      this.aGui.showImage(this.getCurrentRoom().getImageName());
    }
  }

  /**
   * This method show the character in the dialog
   */
  protected void showCharacter() {
    if (this.getCurrentRoom().getCharacter().getImageName() != null) {
      this.aGui.showEntityImage(
          this.getCurrentRoom().getCharacter().getImageName()
        );
    }
  }

  /**
   * This method show the character for the battle
   */
  protected void showFullCharacter() {
    if (this.getCurrentRoom().getCharacter().getImageName() != null) {
      this.aGui.showFullEntityImage(
          this.getCurrentRoom().getCharacter().getFullImageName()
        );
      this.aGui.showFullPlayerImage();
    }
  }

  /**
   * This function get the enemy name
   *
   * @return String of the enemy name
   */
  public String getEnemyName() {
    if (this.getCurrentRoom().getCharacter().getName() == null) {
      return null;
    } else {
      return this.getCurrentRoom().getCharacter().getName();
    }
  }

  /**
   * This function get the moves
   *
   * @return String of the moves
   */
  public String getMovesString() {
    return this.getMoves();
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

  /**
   * This function get items in inventory
   *
   * @return a String with items in inventory
   */
  public String getCurrentInventoryFightableItemsString() {
    return this.aInventory.getInventoryFightableString();
  }

  /**
   * This method show dialog of the entity in the room
   */
  private void showDialog() {
    this.aGui.clearDialogArea();
    this.aGui.showCharacterPanel();
    showCharacter();
    printCharacterDialog();
  }

  /**
   * This is used to give the wedding ring to garret
   */
  private void giveWeddingRingToGarret() {
    if (this.aCurrentRoom.getCharacter().getName() == "Garret") {
      this.aGui.hideCharacterPanel();
      this.aGui.showCharacterPanel();
      this.aGui.clearDialogArea();
      this.aGui.showEntityImage("faceImages/garret.png");
      if (isDevMode()) {
        this.aGui.printlnEntity(aJsonReader.getGiveWeddingRing());
      } else {
        this.aGui.slowPrintEntity(aJsonReader.getGiveWeddingRing());
      }
      this.aGui.hideCharacterPanel();

      if (isDevMode()) {
        int vDelay = 100; // specify the delay for the timer
        Timer vTimer = new Timer(
          vDelay,
          e -> {
            // The following code will be executed once the delay is reached
            this.aGui.showCharacterPanel();
            this.aGui.clearDialogArea();
            this.aGui.showEntityImage("faceImages/eboo.png");
            this.aGui.printlnEntity("HOOT HOOT");
          }
        );
        vTimer.setRepeats(false); // make sure the timer only runs once
        vTimer.start();
      } else {
        int vDelay = 10000; // specify the delay for the timer
        Timer vTimer = new Timer(
          vDelay,
          e -> {
            // The following code will be executed once the delay is reached
            this.aGui.showCharacterPanel();
            this.aGui.clearDialogArea();
            this.aGui.showEntityImage("faceImages/eboo.png");
            this.aGui.slowPrintEntity("HOOT HOOT");
          }
        );
        vTimer.setRepeats(false); // make sure the timer only runs once
        vTimer.start();
      }
    } else {
      this.aGui.println(aJsonReader.getCantGive());
    }
  }

  /**
   * This method is used to give an artefact to garret
   *
   * @param pItemName Name of the item to give at garret
   */
  private void giveArtefactToGarret(String pItemName) {
    Item vItem = this.aInventory.getItemName(pItemName);
    if (this.aCurrentRoom.getCharacter().getName() == "Garret") {
      if (
        pItemName.equals(aJsonReader.getItem1Name()) &&
        this.aInventory.getItemList().containsValue(vItem)
      ) {
        giveArtefact();
      }
      if (
        pItemName.equals(aJsonReader.getItem2Name()) &&
        this.aInventory.getItemList().containsValue(vItem)
      ) {
        giveArtefact();
      }
      if (
        pItemName.equals(aJsonReader.getItem3Name()) &&
        this.aInventory.getItemList().containsValue(vItem)
      ) {
        giveArtefact();
      }
      if (
        pItemName.equals("wedding_ring") &&
        this.aInventory.getItemList().containsValue(vItem)
      ) {
        this.giveWeddingRingToGarret();
      }

      if (aArtefactCounter == 0) {
        allArtefactText();
      }
    } else {
      this.aGui.println(aJsonReader.getCantGive());
    }
  }

  /**
   * This method is used to give an artefact
   */
  private void giveArtefact() {
    aArtefactCounter--;
    this.aGui.hideCharacterPanel();
    this.aGui.showCharacterPanel();
    this.aGui.clearDialogArea();
    this.aGui.showEntityImage("faceImages/garret.png");
    if (isDevMode()) {
      this.aGui.printlnEntity(
          aJsonReader.getFoundArtefact() + aArtefactCounter
        );
    } else {
      this.aGui.slowPrintEntity(
          aJsonReader.getFoundArtefact() + aArtefactCounter
        );
    }
  }

  /**
   * This method is used to write a text when you get all the artefact
   */
  private void allArtefactText() {
    this.aGui.hideCharacterPanel();
    this.aGui.showCharacterPanel();
    this.aGui.clearDialogArea();
    this.aGui.showEntityImage("faceImages/garret.png");
    if (isDevMode()) {
      this.aGui.printlnEntity(aJsonReader.getAllArtefact());
    } else {
      this.aGui.slowPrintEntity(aJsonReader.getAllArtefact());
    }
  }

  /**
   * This setter is used to set dev mode
   *
   * @param pToggle on or off
   */
  public void setDevMode(boolean pToggle) {
    aDevMode = pToggle;
  }

  /**
   * This getter get if we are in dev mode
   *
   * @return devmode true or false
   */
  public boolean isDevMode() {
    return aDevMode;
  }

  /**
   * this getter get the nbr of artefact left
   *
   * @return artefact left
   */
  public int getArtefactCounter() {
    return aArtefactCounter;
  }
}
