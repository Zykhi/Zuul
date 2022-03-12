import java.util.HashMap;
import java.util.Random;

/**
 * This class represents part of TransporterRoom
 * 
 * @author C.Diouy
 * @version 2022.03.02
 */
public class RoomRandomizer {
    private HashMap<String, Room> aAllRooms;
    private Object[] aRoomsArrayList;
    private boolean aAlea;

    /**
     * this constructor create a room randomizer
     */
    public RoomRandomizer() {
        this.aAlea = false;
    }

    /**
     * Find a random room
     * 
     * @return room in array list
     */
    public Room findRandomRoom() {
        Random vRandom = new Random();
        int vRandomIntInArray = vRandom.nextInt(this.aRoomsArrayList.length);
        return (Room) this.aRoomsArrayList[vRandomIntInArray];
    }

    /**
     * This method set the room for alea command
     * 
     * @param pRoom Room to set
     */
    public void setAlea(Room pRoom) {
        this.aRoomsArrayList = new Object[] { pRoom };
    }

    /**
     * This method set all the room random for the transporterRoom
     * 
     * @param pAllRooms all the room of the game
     */
    public void setRandom(final HashMap<String, Room> pAllRooms) {
        this.aAllRooms = pAllRooms;
        this.aRoomsArrayList = this.aAllRooms.values().toArray();
    }

    /**
     * This boolean return if alea is on or off
     * 
     * @return true if aAlea is active
     *         false if isnt
     */
    public boolean isAlea() {
        return this.aAlea;
    }

}
