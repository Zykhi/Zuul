package pkg_Room;
/**
 * This class implements door
 * 
 * @author C.Diouy
 * @version 2022.03.02
 */
public class Door {
    private boolean aIsTrap;

    /**
     * This constructor create door
     * 
     * @param pTrap true if door is trap
     *              false if it's isnt
     */
    public Door(final boolean pTrap) {
        this.aIsTrap = pTrap;
    }

    /**
     * get if is trapdoor
     * 
     * @return true if door is trap
     *         false if it's isnt
     */
    public boolean isTrap() {
        return this.aIsTrap;
    }
}
