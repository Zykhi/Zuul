import java.util.HashMap;
import java.util.Set;

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
    private HashMap<String, Item> aItems;
    private String aImageName;

    /**
     * Create a room described by "aDescription" with a given image.
     * Initially, it has no exits. "aDescription" is something like
     * "in a kitchen" or "in an open court yard".
     * 
     * @param pDescription
     */
    public Room(final String pDescription, final String pImage) {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
        this.aItems = new HashMap<String, Item>();
        this.aImageName = pImage;
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
        if (this.isEmpty()) {
            return "You are " + aDescription + ".\n" +
                    getExitString() + "\n" +
                    "No item here.";
        } else {
            return "You are " + aDescription + ".\n" +
                    getExitString() + "\n" +
                    getItemString();
        }
    }

    // EXIT

    /**
     * this Room get exit
     * 
     * @param pDirection
     * @return the room that is reached if we go from this room in direction
     *         "direction". If there is no room in that direction, return null.
     */
    public Room getExit(String pDirection) {
        return aExits.get(pDirection);
    }

    /**
     * Define an exit from this room.
     * 
     * @param pDirection
     * @param pNeighbor
     */
    public void setExits(final String pDirection, Room pNeighbor) {
        aExits.put(pDirection, pNeighbor);
    }

    /**
     * This String get all the exits in the String
     * 
     * @return a string describing the room's exits, for example
     *         "Exits: north west"
     */
    public String getExitString() {
        String vReturnString = "Exits : ";
        Set<String> vKeys = aExits.keySet();
        for (String vExit : vKeys) {
            vReturnString += " " + vExit;
        }
        return vReturnString;
    }

    // ITEM

    /**
     * Define an item from this room.
     * 
     * @param pName
     * @param pItem
     */
    public void setItems(final String pName, final Item pItem) {
        aItems.put(pName, pItem);
    }

    /**
     * This String get all the item in the String
     * 
     * @return a string describing the room's items, for example
     *         "Items : sword shield"
     */
    public String getItemString() {
        String vReturnString = "Items :";
        Set<String> vKeys = aItems.keySet();
        for (String vItem : vKeys) {
            vReturnString += " " + vItem;
        }
        return vReturnString;
    }

    public boolean isEmpty() {
        return this.aItems.isEmpty();
    }

    // IMAGE

    /**
     * Return a string describing the room's image name
     */
    public String getImageName() {
        return this.aImageName;
    }

} // Room