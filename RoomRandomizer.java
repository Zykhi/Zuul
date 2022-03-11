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
    private Object[] aRoomsArrayList;
    private boolean aAlea;

    /**
     * this constructor create a room randomizer
     * 
     * @param pAllRooms hashmap contain all rooms
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

    public void setAlea(Room pRoom) {
        this.aRoomsArrayList = new Object[] {pRoom};
    }

    public void setRandom(final HashMap<String, Room> pAllRooms){
        this.aAllRooms = pAllRooms;
        this.aRoomsArrayList = this.aAllRooms.values().toArray();
    }

    public boolean isAlea(){
        return this.aAlea;
    }

}
