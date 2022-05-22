package pkg_Room;

import java.util.HashMap;
import java.util.Set;

import pkg_Entity.Entity;
import pkg_Item.Item;
import pkg_Item.ItemList;

/**
 * Class Room - a room in an adventure game.
 * 
 * This class is part of the "Zuul GOTY Edition" application.
 * "Zuul GOTY Edition" is a very simple, text based adventure game.
 * 
 * A "Room" represents one location in the scenery of the game. It is
 * connected to other rooms via exits. For each existing exit, the room
 * stores a reference to the neighboring room.
 *
 * @author Michael Kolling and David J. Barnes + D.Bureau + C.Diouy
 * @version 2008.03.30 + 2019.09.25 + 2022.02.11
 */
public class Room {
    private String aDescription;
    private HashMap<String, Room> aExits;
    private HashMap<String, Door> aDoors;
    private ItemList aItems;
    private String aImageName;
    private Entity aCharacter;
    private int aNbrEntry;

    /**
     * Create a room described by "aDescription" with a given image.
     * Initially, it has no exits. "aDescription" is something like
     * "in a kitchen" or "in an open court yard".
     * 
     * @param pDescription description of the room
     * @param pImage       image of the room
     */
    public Room(final String pDescription, final String pImage) {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
        this.aDoors = new HashMap<String, Door>();
        this.aItems = new ItemList();
        this.aImageName = pImage;
        this.aNbrEntry = 0;
    }

    /**
     * This String get a description
     * 
     * @return the description of the room (the one that was defined in the
     *         constructor).
     */
    public String getDescription() {
        return this.aDescription;
    }

    /**
     * return a long description of the room
     * 
     * @return a long description of this room, in the form:
     *         You are in the kitchen.
     *         Exits: north west
     */
    public String getLongDescription() {
        return "You are " + aDescription + ".\n" +
                getExitString() + "\n" +
                getItemString();
    }

    // EXIT

    /**
     * this Room get exit
     * 
     * @param pDirection name of the exit
     * @return the room that is reached if we go from this room in direction
     *         "direction". If there is no room in that direction, return null.
     */
    public Room getExit(String pDirection) {
        return aExits.get(pDirection);
    }

    /**
     * Define an exit from this room.
     * 
     * @param pDirection name of the exit
     * @param pNeighbor  room neighbor of actual room
     */
    public void setExit(final String pDirection, Room pNeighbor) {
        aExits.put(pDirection, pNeighbor);
    }

    /**
     * This String get all the exits in the String
     * 
     * @return a string describing the room's exits, for example
     *         "Exits: north west"
     */
    public String getExitString() {
        String vExitString = "Exits : ";
        Set<String> vKeys = aExits.keySet();
        for (String vExit : vKeys) {
            vExitString += " " + vExit;
        }
        return vExitString;
    }

    // ITEM

    /**
     * this Item get name
     * 
     * @param pName name of the item
     * @return the name of the item
     *         If there is no item with this name, return null.
     */
    public Item getItemName(String pName) {
        return this.aItems.getItemName(pName);
    }

    /**
     * Define an item from this room.
     * 
     * @param pName name of the item
     * @param pItem variable item
     */
    public void addItem(final String pName, final Item pItem) {
        this.aItems.addItem(pName, pItem);
    }

    /**
     * This method remove an item from the room
     * 
     * @param pName name of the item
     * @param pItem the item
     */
    public void removeItem(final String pName, final Item pItem) {
        this.aItems.removeItem(pName, pItem);
    }

    /**
     * This function get the string of the item in the room
     * 
     * @return the item in the room
     */
    public String getItemString() {
        if (this.aItems.isEmpty()) {
            return "No item here.";
        } else {
            return this.aItems.getItemString();
        }
    }

    // DOOR

    /**
     * This function get the door
     * 
     * @param pDirection direction of the exit
     * @return the door
     */
    public Door getDoor(final String pDirection) {
        return this.aDoors.get(pDirection);
    }

    /**
     * This method add a door
     * 
     * @param pDirection direction of the exit
     * @param pDoor      the door
     */
    public void addDoor(final String pDirection, final Door pDoor) {
        this.aDoors.put(pDirection, pDoor);
    }

    // IMAGE

    /**
     * This function get the image
     * 
     * @return a string describing the room's image name
     */
    public String getImageName() {
        return this.aImageName;
    }

    // CHARACTER

    /**
     * This function get the character
     * 
     * @return the character
     */
    public Entity getCharacter() {
        return this.aCharacter;
    }

    /**
     * This function get the character's name
     * 
     * @return the character's name
     */
    public String getCharacterName() {
        return this.getCharacter().getName();
    }

    /**
     * This method set the character
     * 
     * @param pCharacter the character
     */
    public void setCharacter(final Entity pCharacter) {
        this.aCharacter = pCharacter;
    }

    /**
     * This function get the dialog of the character
     * 
     * @return the dialog of the character
     */
    public String getDialog() {
        return this.getCharacter().getName() + " : " + "\n" + getCharacter().getDialog();
    }

    /**
     * This method update the numbrer of entry in the room
     */
    public void updateNbrRoom() {
        this.aNbrEntry += 1;
    }

    /**
     * This function get the number of entry in the room
     * 
     * @return the number of entry in the room
     */
    public int getNbrRoom() {
        return aNbrEntry;
    }

} // Room