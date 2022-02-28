import java.util.HashMap;

public class TransporterRoom extends Room {

    private RoomRandomizer aRoomRandomizer;

    public TransporterRoom(String pDescription, String pImage) {
        super(pDescription, pImage);

    }

    public void setAllRooms(final HashMap<String, Room> pAllRooms) {
        this.aRoomRandomizer = new RoomRandomizer(pAllRooms);
    }

    @Override
    public Room getExit(final String pDirection) {
        return this.aRoomRandomizer.findRandomRoom();
    }

}
