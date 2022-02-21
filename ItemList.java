import java.util.HashMap;
import java.util.Set;

public class ItemList {
    private HashMap<String, Item> aItems;

    public ItemList() {
        this.aItems = new HashMap<String, Item>();
    }

    public Item getItemName(final String pName) {
        return this.aItems.get(pName);
    }

    /**
     * This String get all the item in the String
     * 
     * @return a string describing the room's items, for example
     *         "Items : sword shield"
     */
    public String getItemString() {
        String vItemString = "Items :";
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

    public String showInventory() {
        return this.getInventoryString();
    }

    public boolean isEmpty() {
        return this.aItems.isEmpty();
    }
}
