import java.util.HashMap;

/**
 * This class create a transporter room
 * 
 * @author C.Diouy
 * @version 2022.03.02
 */
public class TransporterRoom extends Room {

    private RoomRandomizer aRoomRandomizer;
    private String aAlea;

    /**
     * Constructor of Transporter Room
     * 
     * @param pDescription description of the room
     * @param pImage       image of the room
     */
    public TransporterRoom(String pDescription, String pImage) {
        super(pDescription, pImage);
        this.aAlea = null;
    }

    /**
     * This method set all the room in the room randomizer
     * 
     * @param pAllRooms hashmap contain all the room
     */
    public void setAllRooms(final HashMap<String, Room> pAllRooms) {
        this.aRoomRandomizer = new RoomRandomizer(pAllRooms);
    }

    /**
     * 
     * @param pDirection direction of the exit
     * @return a random room
     */
    @Override
    public Room getExit(final String pDirection) {
        if (this.aAlea == null) {
            return this.aRoomRandomizer.findRandomRoom();
        } else {
            return this.aRoomRandomizer.getRoom(pDirection);
        }
    }

    public void setNextRoom(final String pNextRoom) {
        this.aAlea = pNextRoom;
    }

}
