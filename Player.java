import java.util.Stack;
import javax.swing.Timer;

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
    private ItemList aInventory;
    private int aMaxWeight;
    private int aMovement;
    /**
     * this constructor init the player
     * 
     * @param pCurrentRoom Start room
     */
    public Player(final Room pCurrentRoom) {
        super(200, 200, 100, 100, 100, 100, true);
        this.aCurrentRoom = pCurrentRoom;
        this.setName("Edward");
        this.aPreviousRooms = new Stack<Room>();
        this.aParser = new Parser();
        this.aInventory = new ItemList();
        this.aMaxWeight = 20;
        this.aMovement = 41;
        aMoves[0][0] = "Flamethrower";
        aMoves[0][1] = "90";
        aMoves[0][2] = "95";
        aMoves[0][3] = "physical";
        aMoves[1][0] = "Earthquake";
        aMoves[1][1] = "100";
        aMoves[1][2] = "100";
        aMoves[1][3] = "physical";
        aMoves[2][0] = "AirSlash";
        aMoves[2][1] = "85";
        aMoves[2][2] = "0";
        aMoves[2][3] = "physical";
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
    protected void printWelcome() {
        this.aGui.playDialogSound("welcome");
        this.aGui.slowPrintln("Welcome to the world of Zuul." + '\n' + "You are Edward, the hero of this story." + '\n'
                +
                "You are a knight, the war has been raging for decades and you cannot sleep. You decide to go out to clear your mind by walking away from the castle. You come across a waterfall that hides a cave, mist emanating from it."
                + '\n' +
                "It is at this moment that your adventure takes all its meaning." + '\n' +
                "Good luck knight. If you need help, let me know." + '\n');

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
        this.aGui.println("");
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
        this.aCurrentRoom.updateNbrRoom();
        this.aGui.playRoomSound();
        aMovement -= 1;

        if (this.getMovement() == 40) {
            int vDelay = 100;// specify the delay for the timer
            Timer vTimer = new Timer(vDelay, e -> {
                // The following code will be executed once the delay is reached
                this.printWelcome();
            });
            vTimer.setRepeats(false);// make sure the timer only runs once
            vTimer.start();

            int vDelay2 = 26000;// specify the delay for the timer
            Timer vTimer2 = new Timer(vDelay2, e -> {
                // The following code will be executed once the delay is reached
                this.aGui.println("Type '" + CommandWord.HELP.toString() + "' if you need help." + '\n'
                        + "You have 20 minutes to escape from the dungeon before being trapped forever" + '\n');
                this.printLocationInfo();
                this.aGui.startTimer();
            });
            vTimer2.setRepeats(false);// make sure the timer only runs once
            vTimer2.start();
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

    public void use(Command pItemName) {
        Potion vPotion = (Potion) this.aInventory.getItemName("potion");
        String vItemName = pItemName.getSecondWord();
        Item vItem = this.aInventory.getItemName(vItemName);
        if (!pItemName.hasSecondWord()) {

        } else if (vItem == null) {

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
     * This method write in UI when you left the menu
     */
    public void exit() {
        this.aGui.println("you have left menu");
    }

    /**
     * This method is called when player want to fight
     */
    public void fight() {
        if (this.aCurrentRoom.getCharacter() != null && this.aCurrentRoom.getCharacter().isFightable()) {
            this.aGui.updateBattleUI();
            this.aGui.playBattleRoomSound();
            this.aGui.showBattlePanel();
            showFullCharacter();
            //showFullPlayerCharacter();
            this.aGui.printlnBattle("Let battle begin");

        } else {
            this.aGui.println("You cant start a battle here");
        }
    }

    /**
     * This method is called when the player win the fight
     */
    private void victory() {
        this.aGui.printlnBattle("Opponent is dead!");
        this.aGui.printlnBattle("You are the ultimate warrior.");
    }

    /**
     * This method is called when the player loose the fight
     */
    private void defeat() {
        this.aGui.printlnBattle("You are dead!");
        this.aGui.printlnBattle("You have been slain.");
        // this.aGui.hideBattlePanel();
    }

    /**
     * This method is called when the player click on attack 1 button
     */
    public void attack1() {
        this.aGui.clearBattleArea();
        Entity vPlayer = this;
        Entity vEnemy = aCurrentRoom.getCharacter();

        Entity vPlayer1;
        Entity vPlayer2;

        vPlayer1 = vPlayer;
        vPlayer2 = vEnemy;

        int vMove1 = 0;
        int vMove2 = 1;

        vPlayer1.attack(vPlayer2, vMove1);
        playerAttackString(vMove1);
        this.aGui.updateBattleUI();
        if (vPlayer2.isDead()) {
            victory();
            return;
        }

        vPlayer2.attack(vPlayer1, vMove2);
        enemyAttackString(vMove2);
        this.aGui.updateBattleUI();
        if (vPlayer1.isDead()) {
            defeat();
            return;
        }
        this.aGui.exitBattleButton();
    }

    /**
     * This method is called when the player click on attack 2 button
     */
    public void attack2() {
        this.aGui.clearBattleArea();
        Entity vPlayer = this;
        Entity vEnemy = aCurrentRoom.getCharacter();

        Entity vPlayer1;
        Entity vPlayer2;

        vPlayer1 = vPlayer;
        vPlayer2 = vEnemy;

        int vMove1 = 1;
        int vMove2 = 1;

        vPlayer1.attack(vPlayer2, vMove1);
        playerAttackString(vMove1);
        this.aGui.updateBattleUI();
        if (vPlayer2.isDead()) {
            victory();
            return;
        }

        vPlayer2.attack(vPlayer1, vMove2);
        enemyAttackString(vMove2);
        this.aGui.updateBattleUI();
        if (vPlayer1.isDead()) {
            defeat();
            return;
        }
        this.aGui.exitBattleButton();
    }

    /**
     * This method is called when the player click on attack 3 button
     */
    public void attack3() {
        this.aGui.clearBattleArea();
        Entity vPlayer = this;
        Entity vEnemy = aCurrentRoom.getCharacter();

        Entity vPlayer1;
        Entity vPlayer2;

        vPlayer1 = vPlayer;
        vPlayer2 = vEnemy;

        int vMove1 = 2;
        int vMove2 = 1;

        vPlayer1.attack(vPlayer2, vMove1);
        playerAttackString(vMove1);
        this.aGui.updateBattleUI();
        if (vPlayer2.isDead()) {
            victory();
            return;
        }

        vPlayer2.attack(vPlayer1, vMove2);
        enemyAttackString(vMove2);
        this.aGui.updateBattleUI();
        if (vPlayer1.isDead()) {
            defeat();
            return;
        }
        this.aGui.exitBattleButton();
    }

    /**
     * This method is called when the player click on defend button
     */
    public void defend() {
        this.aGui.clearBattleArea();
        this.improveDefence();
        this.aGui.printlnBattle("Your defense has improved");
        this.aGui.printlnBattle(
                "Your defense is now : " + this.getDef() + " and your special defense is now : " + this.getSpeDef());
        this.aGui.printlnBattle("");
        Entity vPlayer = this;
        Entity vEnemy = aCurrentRoom.getCharacter();

        Entity vPlayer1;
        Entity vPlayer2;

        vPlayer1 = vPlayer;
        vPlayer2 = vEnemy;

        int vMove2 = 1;

        vPlayer2.attack(vPlayer1, vMove2);
        enemyAttackString(vMove2);
        this.aGui.updateBattleUI();
        if (vPlayer1.isDead()) {
            defeat();
            return;
        }

    }

    /**
     * This function print the string of the attack
     *
     * @param pMove Number of the move in the table
     * @return String of the attack
     */
    private String getAttackString(int pMove) {
        String vAttackString = "";
        vAttackString += aMoves[pMove][0];
        return vAttackString;
    }

    /**
     * This method is called when enemy attack
     *
     * @param pMove Number of the move in the table
     */
    private void enemyAttackString(int pMove) {
        String vName = this.aCurrentRoom.getCharacter().getName();
        //TODO : add text when attack missed
        /*if () {
            this.aGui.printlnBattle(vName + " missed");
            this.aGui.printlnBattle("");
        } else {*/
            this.aGui.printlnBattle(vName + " used " + getAttackString(pMove));
            this.aGui.printlnBattle("");
        //}
    }

    /**
     * This method is called when player attack
     *
     * @param pMove Number of the move in the table
     */
    private void playerAttackString(int pMove) {
        //TODO : add text when attack missed
        /*if () {
            this.aGui.printlnBattle("You missed");
            this.aGui.printlnBattle("");
        } else {*/
            this.aGui.printlnBattle("You used " + getAttackString(pMove));
            this.aGui.printlnBattle("");
        //}
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

    /**
     * This method show the character in the dialog
     */
    protected void showCharacter() {
        if (this.getCurrentRoom().getCharacter().getImageName() != null) {
            this.aGui.showEntityImage(this.getCurrentRoom().getCharacter().getImageName());
        }
    }

    /**
     * This method show the character for the battle
     */
    protected void showFullCharacter() {
        if (this.getCurrentRoom().getCharacter().getImageName() != null) {
            this.aGui.showFullEntityImage(this.getCurrentRoom().getCharacter().getFullImageName());
            this.aGui.showFullPlayerImage();
        }
    }

    /**
     * This method show the player for the battle
     
    protected void showFullPlayerCharacter() {
        if (this.getCurrentRoom().getCharacter().getImageName() != null) {
            this.aGui.showFullPlayerImage();
        }
    }*/

    /**
     * This function get the enemy name
     * 
     * @return String of the enemy name
     */
    protected String getEnemyName() {
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
    //FIXME: return only fightable item
    public String getCurrentInventoryFightableItemsString() {
        return this.aInventory.getInventoryString();
    }
}
