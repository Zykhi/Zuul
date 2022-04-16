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

    public String getName() {
        return this.aName;
    }

    public void setName(String pName) {
        this.aName = pName;
    }

    public String getDialog() {
        return this.aDialog;
    }

    public int getDef() {
        return this.aDef;
    }

    public int getSpeDef() {
        return this.aSpeDef;
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

    public int getMaxHP() {
        return this.aMaxHP;
    }

    public boolean isFightable(){
        return this.aFightable;
    }

    public void improveDefence() {
        this.aDef += 20;
        this.aSpeDef += 20;
    }

    public void heal(int pHealingPoint) {
        this.aHP += pHealingPoint;
    }

    public void attack(Entity pEnemy, int pMove) {
        double vDamage = 0.0;
        double vRandom = Math.random() * 100;
        if (vRandom > Double.parseDouble(aMoves[pMove][2])) {
            System.out.println("attack missed");
            return;
        }
        if (aMoves[pMove][3].equals("special")) {
            vDamage = aSpeAtt * Double.parseDouble(aMoves[pMove][1]) / pEnemy.aSpeDef;
        } else {
            vDamage = aAtt * Double.parseDouble(aMoves[pMove][1]) / pEnemy.aDef;
        }
        pEnemy.aHP -= (int) (vDamage);
    }

    public boolean isDead() {
        return aHP <= 0;
    }

    public String getMoves() {
        String vMoves = "";
        for (int i = 0; i < 3; i++)
            vMoves += aMoves[i][0] + " ";
        return vMoves;
    }
}
