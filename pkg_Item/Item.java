package pkg_Item;

/**
 * This class implement item for the game
 * 
 * @author C.Diouy
 * @version 2022.02.19
 */
public class Item {
    private String aName;
    private double aPrice;
    private double aWeight;
    private String aDescription;
    private boolean aFightableItem;

    /**
     * Create an item with a name, a price, a weight and a description
     * 
     * @param pName        name of the item
     * @param pPrice       price of the item
     * @param pWeight      weight of the item
     * @param pDescription description of the item
     * @param pFightableItem   true if the item is fightable
     */
    public Item(final String pName, final double pPrice, final double pWeight, final String pDescription,
            boolean pFightableItem) {
        this.aName = pName;
        this.aPrice = pPrice;
        this.aWeight = pWeight;
        this.aDescription = pDescription;
        this.aFightableItem = pFightableItem;
    }

    /**
     * Redefine toString()
     * 
     * @return a string with all informations about an item
     */
    @Override
    public String toString() {
        return this.aName +
                ", price : " + this.aPrice +
                ", weight : " + this.aWeight + "kg, " +
                this.aDescription;
    }

    /**
     * This String get a description
     * 
     * @return the description of the item (the one that was defined in the
     *         constructor).
     */
    public String getDescription() {
        return this.aDescription;
    }

    /**
     * This String get the name of the item
     * 
     * @return the name of the item (the one that was defined in the
     *         constructor).
     */
    public String getName() {
        return this.aName;
    }

    /**
     * This double get the price of the item
     * 
     * @return the price of the item (the one that was defined in the
     *         constructor).
     */
    public double getPrice() {
        return this.aPrice;
    }

    /**
     * This double get the name of the item
     * 
     * @return the weight of the item (the one that was defined in the
     *         constructor).
     */
    public double getWeight() {
        return this.aWeight;
    }

    /**
     * This function get if the item is a fightable item
     * 
     * @return true if the item is a fightable item, false otherwise
     */
    public boolean isFightableItem() {
        return this.aFightableItem;
    }

}
