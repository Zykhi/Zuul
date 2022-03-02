import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 
 * 
 * @author C.Diouy
 * @version 2022.03.02
 */
public class RoomRandomizer {
    private HashMap<String, Room> aAllRooms;
    private ArrayList<Room> aRoomsArrayList;

    /**
     * this constructor create a room randomizer
     * 
     * @param pAllRooms hashmap contain all rooms
     */
    public RoomRandomizer(HashMap<String, Room> pAllRooms) {
        this.aAllRooms = pAllRooms;
        this.aRoomsArrayList = new ArrayList<Room>();
        fillArrayList();
    }

    /**
     * this method fill the array list
     */
    private void fillArrayList() {
        HashMap<String, Room> vAllRooms = this.aAllRooms;
        for (String vRoomName : vAllRooms.keySet()) {
            this.aRoomsArrayList.add(vAllRooms.get(vRoomName));
        }
    }

    /**
     * Find a random room
     * 
     * @return room in array list
     */
    public Room findRandomRoom() {
        Random vRandom = new Random();
        int vRandomIntInArray = vRandom.nextInt(this.aRoomsArrayList.size());

        return this.aRoomsArrayList.get(vRandomIntInArray);
    }

}
