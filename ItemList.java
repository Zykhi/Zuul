import java.util.HashMap;
import java.util.Set;

public class ItemList {
    private HashMap<String, Item> aItems;
    private double aWeight;

    public ItemList() {
        this.aItems = new HashMap<String, Item>();
        this.aWeight = 0;
    }

    public Item getItemName(final String pName) {
        return this.aItems.get(pName);
    }

    public double getWeight() {
        return this.aWeight;
    }

    /**
     * This String get all the item in the String
     * 
     * @return a string describing the room's items, for example
     *         "Items : sword shield"
     */
    public String getItemString() {
        String vItemString = "Items in the room :";
        Set<String> vKeys = aItems.keySet();
        for (String vItem : vKeys) {
            vItemString += " " + vItem;
        }
        return vItemString;
    }

    public String getInventoryString() {
        if (this.aItems.isEmpty()) {
            return "your inventory is empty";
        } else {
            StringBuilder vInventoryBuilder = new StringBuilder("your inventory : ");
            Set<String> vKeys = aItems.keySet();
            for (String vS : vKeys) {
                vInventoryBuilder.append(" " + vS);
            }
            return vInventoryBuilder.toString();
        }
    }

    public ItemList getItemList() {
        return this;
    }

    public void addItem(final String pName, final Item pItem) {
        this.aItems.put(pName, pItem);
    }

    public void removeItem(final String pName, final Item pItem) {
        this.aItems.remove(pName, pItem);
    }

    public void addWeight(final double pWeight) {
        this.aWeight += pWeight;
    }

    public void removeWeight(final double pWeight) {
        this.aWeight -= pWeight;
    }

    public String getWeightString() {
        String vWeightString = "Weight : " + this.getWeight() + "kg / 20kg";
        return vWeightString;
    }

    public boolean isEmpty() {
        return this.aItems.isEmpty();
    }
}
