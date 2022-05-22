package pkg_Item;
public class Potion extends Item{
    private int aHealing;

    public Potion(int pHealingPoint) {
        super("potion", 0, 0, "this is a potion who heal " +pHealingPoint+" hp", true);
        aHealing = pHealingPoint;
    }

    public int getHealingPoint(){
        return this.aHealing;
    }
    
}
