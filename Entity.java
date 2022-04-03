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
    private int aSpeed;

    public Entity(int pHP, int pMaxHP, int pDef, int pSpeDef, int pAtt,
            int pSpeAtt, int pSpeed) {
        this.aMoves = new String[4][4];
        this.aHP = pHP;
        this.aMaxHP = pMaxHP;
        this.aDef = pDef;
        this.aSpeDef = pSpeDef;
        this.aAtt = pAtt;
        this.aSpeAtt = pSpeAtt;
        this.aSpeed = pSpeed;
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

    public int getSpeed() {
        return this.aSpeed;
    }

    public int getHP() {
        return this.aHP;
    }

    public int getMaxHP() {
        return this.aMaxHP;
    }

    public String getMoves() {
        String vMoves = "";
        for (int i = 0; i < 4; i++)
            vMoves += aMoves[i][0] + " ";
        return vMoves;
    }

    public void calculateDamage(Entity pEntity, int pMove) {

        double vDamage = 0.0;
        double vRandom = Math.random() * 100;
        if (vRandom > Double.parseDouble(aMoves[pMove][2])) {
            System.out.println("Attack missed!");
            return;
        }
        if (aMoves[pMove][3].equals("special")) {
            vDamage = aSpeAtt * Double.parseDouble(aMoves[pMove][1]) / pEntity.aSpeDef;
        } else {
            vDamage = aAtt * Double.parseDouble(aMoves[pMove][1]) / pEntity.aDef;
        }
        pEntity.aHP -= (int) (vDamage);
    }

}
