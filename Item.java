public class Item {
    private String aName;
    private double aPrice;
    private double aWeight;
    private String aDescription;

    public Item(final String pName, final double pPrice, final double pWeight, final String pDescription) {
        this.aName = pName;
        this.aPrice = pPrice;
        this.aWeight = pWeight;
        this.aDescription = pDescription;
    }

    @Override public String toString(){
        return this.aName + 
                ", price : "+ this.aPrice + 
                ", weight : " +this.aWeight + "kg, " +
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

}
