/**
 * This class implements beamer
 *
 * @author C.Diouy
 * @version 2022.02.26
 */
public class Beamer extends Item {
    private boolean aIsCharged;
    private Room aRoomCharged;

    /**
     * Create beamer
     */
    public Beamer() {
        super("teleporter", 10, 5, "teleport where it's charged", false);
        this.aIsCharged = false;
        this.aRoomCharged = null;
    }

    /**
     * charge the beamer
     * 
     * @param pRoom room where you charge the beamer
     */
    public void charge(final Room pRoom) {
        this.aIsCharged = true;
        this.aRoomCharged = pRoom;
    }

    /**
     * for teleport
     * 
     * @return at the room where you charge the beamer
     */
    public Room fire() {
        this.aIsCharged = false;
        Room vRoom = this.aRoomCharged;
        this.aRoomCharged = null;
        return vRoom;
    }

    /**
     * @return true if is charged
     *         false if isnt
     */
    public boolean isCharged() {
        return this.aIsCharged;
    }
}
