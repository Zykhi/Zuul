import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RoomRandomizer {
    private HashMap<String, Room> aAllRooms;
    private ArrayList<Room> aRoomsArrayList;

    public RoomRandomizer(HashMap<String, Room> pAllRooms) {
        this.aAllRooms = pAllRooms;
        this.aRoomsArrayList = new ArrayList<Room>();
        fillArrayList();
    }

    private void fillArrayList() {
        HashMap<String, Room> vAllRooms = this.aAllRooms;
        for (String vRoomName : vAllRooms.keySet()) {
            this.aRoomsArrayList.add(vAllRooms.get(vRoomName));
        }
    }

    public Room findRandomRoom() {
        Random vRandom = new Random();
        int vRandomIntInArray = vRandom.nextInt(this.aRoomsArrayList.size());

        return this.aRoomsArrayList.get(vRandomIntInArray);
    }

}
