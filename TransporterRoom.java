/**
 * This class create a transporter room
 * 
 * @author C.Diouy
 * @version 2022.03.02
 */
public class TransporterRoom extends Room {

    private RoomRandomizer aRoomRandomizer;

    /**
     * Constructor of Transporter Room
     * 
     * @param pDescription description of the room
     * @param pImage       image of the room
     * @param pRoomRandomizer Room random
     */
    public TransporterRoom(String pDescription, String pImage, final RoomRandomizer pRoomRandomizer) {
        super(pDescription, pImage);
        this.aRoomRandomizer = pRoomRandomizer;
    }

    /**
     * 
     * @param pDirection direction of the exit
     * @return a random room
     */
    @Override
    public Room getExit(final String pDirection) {
        return this.aRoomRandomizer.findRandomRoom();
    }
}
