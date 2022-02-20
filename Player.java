import java.util.Stack;

public class Player {
    private Room aCurrentRoom;
    private Stack<Room> aPreviousRooms;
    private String aName;

    public Player(final Room pCurrentRoom, final String pName) {
        this.aCurrentRoom = pCurrentRoom;
        this.aName = pName;
        this.aPreviousRooms = new Stack<Room>();
    }

    public Room getCurrentRoom() {
        return this.aCurrentRoom;
    }

    public void setRoom(final Room pNextRoom) {
        this.aCurrentRoom = pNextRoom;
    }

    public Stack<Room> getPreviousRooms() {
        return this.aPreviousRooms;
    }

}
