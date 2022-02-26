public class Door {
    private boolean aIsTrap;

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
