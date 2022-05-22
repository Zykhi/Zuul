package pkg_Entity;

/**
 * This class implements Entity
 * 
 * @author C.Diouy
 */
public class Entity {
    private String aName;
    private String aDialog;
    private String aImageName;
    private String aFullImage;
    protected String aMoves[][];
    private int aHP;
    private int aMaxHP;
    private int aDef;
    private int aSpeDef;
    private int aAtt;
    private int aSpeAtt;
    private boolean aFightable;
    private boolean aMissed;
    private boolean aDefeated;

    /**
     * This constructor create entity
     * 
     * @param pHP        health point
     * @param pMaxHP     max health point
     * @param pDef       defense
     * @param pSpeDef    special defense
     * @param pAtt       attack
     * @param pSpeAtt    special attack
     * @param pFightable true if entity is fightable
     *                   false if it's isnt
     */
    public Entity(int pHP, int pMaxHP, int pDef, int pSpeDef, int pAtt,
            int pSpeAtt, boolean pFightable) {
        this.aHP = pHP;
        this.aMaxHP = pMaxHP;
        this.aDef = pDef;
        this.aSpeDef = pSpeDef;
        this.aAtt = pAtt;
        this.aSpeAtt = pSpeAtt;
        this.aFightable = pFightable;
        this.aMoves = new String[3][4];
    }

    /**
     * This getter return name
     * 
     * @return name of entity
     */
    public String getName() {
        return this.aName;
    }

    /**
     * This setter set name
     * 
     * @param pName name of entity
     */
    public void setName(String pName) {
        this.aName = pName;
    }

    /**
     * This getter return dialog
     * 
     * @return dialog of entity
     */
    public String getDialog() {
        return this.aDialog;
    }

    /**
     * This setter set dialog
     * 
     * @param pDialog dialog of entity
     */
    public void setDialog(String pDialog) {
        this.aDialog = pDialog;
    }

    /**
     * This getter get defense
     * 
     * @return defense of entity
     */
    public int getDef() {
        return this.aDef;
    }

    /**
     * This getter get special defense
     * 
     * @return special defense of entity
     */
    public int getSpeDef() {
        return this.aSpeDef;
    }

    /**
     * This getter get health point
     * 
     * @return hp of entity
     */
    public int getHP() {
        return this.aHP;
    }

    /**
     * This getter get max health point
     * 
     * @return max hp of entity
     */
    public int getMaxHP() {
        return this.aMaxHP;
    }

    /**
     * this getter get if entity is fightable
     * 
     * @return true if entity is fightable
     *         false if it's isnt
     */
    public boolean isFightable() {
        return this.aFightable;
    }

    /**
     * this method improve defense
     */
    public void improveDefense() {
        this.aDef += 20;
        this.aSpeDef += 20;
    }

    /**
     * this method heal entity
     * 
     * @param pHealingPoint healing point
     */
    public void heal(int pHealingPoint) {
        this.aHP += pHealingPoint;
    }

    /**
     * this method is used to attack an entity
     * 
     * @param pEnemy entity to attack
     * @param pMove   move to use
     */
    public void attack(Entity pEnemy, int pMove) {
        aMissed = false;
        double vDamage = 0.0;
        double vRandom = Math.random() * 100;
        if (vRandom > Double.parseDouble(aMoves[pMove][2])) {
            aMissed = true;
            return;
        }
        if (aMoves[pMove][3].equals("special")) {
            vDamage = aSpeAtt * Double.parseDouble(aMoves[pMove][1]) / pEnemy.aSpeDef;
        } else {
            vDamage = aAtt * Double.parseDouble(aMoves[pMove][1]) / pEnemy.aDef;
        }
        pEnemy.aHP -= (int) (vDamage);
    }

    /**
     * this function is used to check if entity is dead
     * 
     * @return true if entity is dead
     */
    public boolean isDead() {
        return aHP <= 0;
    }

    /**
     * this function is used to check if entity's attack is missed
     * 
     * @return true if entity's attack is missed
     */
    public boolean isMissed() {
        return aMissed;
    }

    /**
     * this method is used to set entity's attack is missed
     * 
     * @param pMissed true if entity's attack is missed
     */
    public void setMissed(boolean pMissed) {
        aMissed = pMissed;
    }

    /**
     * this function is used to get entity's attack moves
     * 
     * @return entity's attack moves
     */
    public String getMoves() {
        String vMoves = "";
        for (int i = 0; i < 3; i++)
            vMoves += aMoves[i][0] + " ";
        return vMoves;
    }

    /**
     * This function print the string of the attack
     *
     * @param pMove Number of the move in the table
     * @return String of the attack
     */
    public String getAttackString(int pMove) {
        String vAttackString = "";
        vAttackString += aMoves[pMove][0];
        return vAttackString;
    }

    /**
     * This function get if the entity is defeated
     * 
     * @return true if the entity is defeated
     *         false if it's isnt
     */
    public boolean isDefeated() {
        return aDefeated;
    }

    /**
     * This function set the defeated status of the entity
     * 
     * @param pDefeated true if the entity is defeated
     *                  false if it's isnt
     */
    public void setDefeated(boolean pDefeated) {
        aDefeated = pDefeated;
    }

    /**
     * This function get the image of the entity
     * 
     * @return image of the entity
     */
    public String getImageName() {
        return this.aImageName;
    }

    /**
     * This function set the image of the entity
     * 
     * @param pImageName image of the entity
     */
    public void setImageName(String pImageName) {
        this.aImageName = pImageName;
    }

    /**
     * This function get the full image of the entity
     * 
     * @return the full image of the entity
     */
    public String getFullImageName() {
        return this.aFullImage;
    }

    /**
     * This function set the full image of the entity
     * 
     * @param pFullImage the full image of the entity
     */
    public void setFullImageName(String pFullImage) {
        this.aFullImage = pFullImage;
    }
}
