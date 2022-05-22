package pkg_Item;

/**
 * This class implement potion item
 */
public class Potion extends Item {
    private int aHealing;

    /**
     * Create a potion whith a healing value
     * 
     * @param pHealingPoint healing point of the potion
     */
    public Potion(int pHealingPoint) {
        super("potion", 0, 0, "this is a potion who heal " + pHealingPoint + " hp", true);
        aHealing = pHealingPoint;
    }

    /**
     * This function get the healing point of the potion
     * 
     * @return the healing point of the potion
     */
    public int getHealingPoint() {
        return this.aHealing;
    }

}
