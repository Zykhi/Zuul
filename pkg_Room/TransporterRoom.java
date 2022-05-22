package pkg_Room;
import java.util.HashMap;

/**
 * This class create a transporter room
 * 
 * @author C.Diouy
 * @version 2022.03.02
 */

public class TransporterRoom extends Room {
    private RoomRandomizer aRandomRoom;
    private String aAlea;
    private HashMap<String, Room> aRooms;

    /**
     * Constructor of Transporter Room
     * 
     * @param pDescription description of the room
     * @param pImage       image of the room
     * @param pRooms       hashmap contains all the rooms
     */
    public TransporterRoom(final String pDescription, final String pImage, final HashMap<String, Room> pRooms) {
        super(pDescription, pImage);
        this.aRandomRoom = new RoomRandomizer(pRooms);
        this.aRooms = pRooms;
        this.aAlea = null;
    }

    /**
     * Refactior getExit method of Room class
     * 
     * @param pDirection direction of the exit
     * @return a random room
     */
    @Override
    public Room getExit(final String pDirection) {
        if (this.aAlea == null) {
            return this.aRandomRoom.findRandomRoom();
        } else {
            return this.aRooms.get(this.aAlea);
        }
    }

    /**
     * This method modify the random caracter of the random room
     * 
     * @param pNextRoom the room were transporter room teleport you
     */
    public void setNextRoom(final String pNextRoom) {
        this.aAlea = pNextRoom;
    }
}
