import java.util.HashMap;
import java.util.Set;

/**
 * This class is part of the "Zuul GOTY Edition" application.
 * "Zuul GOTY Edition" is a very simple, text based adventure game.
 * 
 * This class holds all informations for the room to the game.
 *
 * @author Michael Kolling and David J. Barnes + D.Bureau + C.Diouy
 * @version 2008.03.30 + 2019.09.25 + 2022.02.11
 */
public class Room {
    private String aDescription;
    private HashMap<String, Room> aExits;

    /**
     * This constructor initialize a description to the room
     * 
     * @param pDescription
     */
    public Room(final String pDescription) {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String,Room>();
    }

    /**
     * This String get a description
     * 
     * @return Description
     */
    public String getDescription() {
        return this.aDescription;
    }

    /**
     * return a long description of the room
     * 
     * @return a description of the room with exits
     */
    public String getLongDescription(){
        return "You are " + aDescription + ".\n" + getExitString(); 
    }

    /**
     * This method set the exits of the room
     * 
     * @param pDirection
     * @param pNeighbor 
     */
    public void setExits(final String pDirection, Room pNeighbor) {
        aExits.put(pDirection, pNeighbor);
    }

    /**
     * this Room get exit
     * 
     * @param pDirection
     * @return Exits
     */
    public Room getExit(String pDirection) {
        return aExits.get(pDirection);
    }

    /**
     * This String get all the exits in the String
     * 
     * @return vReturnString
     */
    public String getExitString() {
        String vReturnString = "Exits : ";
        Set<String> vKeys = aExits.keySet();
        for (String vExit : vKeys) {
            vReturnString += " " + vExit;
        }
        return vReturnString;
    }
} // Room