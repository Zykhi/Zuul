package pkg_Item;

import java.util.HashMap;
import java.util.Set;

/**
 * Item list class -
 * 
 * @author C.Diouy
 * @version 2022.02.22
 */
public class ItemList {
    private HashMap<String, Item> aItems;
    private double aWeight;

    /**
     * Constructor of item list
     */
    public ItemList() {
        this.aItems = new HashMap<String, Item>();
        this.aWeight = 0;
    }

    /**
     * This function get the name of the item
     * 
     * @param pName name of the item
     * @return a item
     */
    public Item getItemName(final String pName) {
        return this.aItems.get(pName);
    }

    /**
     * This function get the weight of the item
     * 
     * @return a double with the weight of the item
     */
    public double getWeight() {
        return this.aWeight;
    }

    /**
     * This String get all the item in a String
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

    /**
     * This function get all the item in inventory in a string
     * 
     * @return a string describing the inventory's items
     */
    public String getInventoryString() {
        if (this.aItems.isEmpty()) {
            return "Your inventory is empty";
        } else {
            StringBuilder vInventoryBuilder = new StringBuilder("Your inventory : ");
            Set<String> vKeys = aItems.keySet();
            for (String vS : vKeys) {
                vInventoryBuilder.append(" " + vS);
            }
            return vInventoryBuilder.toString();
        }
    }

    /**
     * This function get all the fightable item in inventory in a string
     * 
     * @return a string describing the inventory's fightable items
     */
    public String getInventoryFightableString() {
        if (this.aItems.isEmpty()) {
            return "Your inventory is empty";
        } else {
            StringBuilder vInventoryBuilder = new StringBuilder("Your inventory : ");
            Set<String> vKeys = aItems.keySet();
            for (String vS : vKeys) {
                if (aItems.get(vS).isFightableItem() == true) {
                    vInventoryBuilder.append(" " + vS);
                }
            }
            return vInventoryBuilder.toString();
        }
    }

    /**
     * This function get the inventory
     * 
     * @return inventory
     */
    public HashMap<String, Item> getItemList() {
        return this.aItems;
    }

    /**
     * This method add an item
     * 
     * @param pName name of the item
     * @param pItem the item
     */
    public void addItem(final String pName, final Item pItem) {
        this.aItems.put(pName, pItem);
    }

    /**
     * This method remove an item
     * 
     * @param pName name of the item
     * @param pItem the item
     */
    public void removeItem(final String pName, final Item pItem) {
        this.aItems.remove(pName, pItem);
    }

    /**
     * This method add the weight of item
     * 
     * @param pItemWeight the weight of the item
     */
    public void addWeight(final double pItemWeight) {
        this.aWeight += pItemWeight;
    }

    /**
     * This method remove the weight of item
     * 
     * @param pItemWeight the weight of the item
     */
    public void removeWeight(final double pItemWeight) {
        this.aWeight -= pItemWeight;
    }

    /**
     * This function get the String of the weight of inventory
     * 
     * @return the string of the weight of the inventory
     */
    public String getWeightString() {
        return "Weight : " + this.getWeight() + "kg / ";
    }

    /**
     * Check if contain a specific item
     * 
     * @param pItem The specific item
     * @return true if it's the specific item
     *         false if it's not
     */
    public boolean contain(final Item pItem) {
        return this.aItems.containsValue(pItem);
    }

    /**
     * Check if inventory is empty
     * 
     * @return true if inventory is empty
     *         false if there is item inside
     */
    public boolean isEmpty() {
        return this.aItems.isEmpty();
    }
}
