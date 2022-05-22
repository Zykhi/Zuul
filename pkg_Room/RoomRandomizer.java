package pkg_Room;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

/**
 * Thid class create a random room
 *
 * @author C.Diouy
 */
public class RoomRandomizer {
    private HashMap<String, Room> aRooms;

    /**
     * this constructor create a room randomizer
     * 
     * @param pRooms Hashmap contain all the room
     */
    public RoomRandomizer(final HashMap<String, Room> pRooms) {
        this.aRooms = pRooms;
    }

    /**
     * Find a random room
     * 
     * @return room in array list
     */
    public Room findRandomRoom() {
        Random vRandomNb = new Random();
        ArrayList<Room> vRoomsList = new ArrayList<Room>(this.aRooms.values());
        int vNb = vRandomNb.nextInt(vRoomsList.size());
        return vRoomsList.get(vNb);
    }
}
