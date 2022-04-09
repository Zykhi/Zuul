import java.util.Random;

public class Entity {
    private String aName;
    private String aDialog;
    private String aImageName;
    private String aFullImage;
    protected String aMoves[][];
    private int aHP;

    public Entity(int pHP) {
        this.aHP = pHP;
    }

    public String getName() {
        return this.aName;
    }

    public void setName(String pName) {
        this.aName = pName;
    }

    public String getDialog() {
        return this.aDialog;
    }

    public void setDialog(String pDialog) {
        this.aDialog = pDialog;
    }

    public String getImageName() {
        return this.aImageName;
    }

    public void setImageName(String pImageName) {
        this.aImageName = pImageName;
    }

    public String getFullImageName() {
        return this.aFullImage;
    }

    public void setFullImageName(String pFullImage) {
        this.aFullImage = pFullImage;
    }

    public int getHP() {
        return this.aHP;
    }

    public void attack(Entity pEnemy) {
        Random vRandomNumber = new Random();
        int vDamage = vRandomNumber.nextInt(21) + 1;
        pEnemy.dealDamage(vDamage);
        System.out.println(getName() + " dealt " + vDamage + " to " + pEnemy.getName());
        System.out.println(pEnemy.getName() + " has " + pEnemy.getHP() + " HP left!");
    }

    public void dealDamage(int pDamage) {
        aHP -= pDamage;
        aHP = aHP < 0 ? 0 : aHP;
    }

    public boolean isDead() {
        return aHP <= 0;
    }
}
