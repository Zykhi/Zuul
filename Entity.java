public class Entity {
    public String aName;
    public String aDialog;
    public String aImageName;
    public String aMoves[][];
    protected int aHP;
    private int aDef;
    private int aSpeDef;
    private int aAtt;
    private int aSpeAtt;
    protected int aSpeed;

    public Entity(int pHP, int pDef, int pSpeDef, int pAtt,
            int pSpeAtt, int pSpeed) {
        this.aMoves = new String[4][5];
        this.aHP = pHP;
        this.aDef = pDef;
        this.aSpeDef = pSpeDef;
        this.aAtt = pAtt;
        this.aSpeAtt = pSpeAtt;
        this.aSpeed = pSpeed;
    }

    public String getName() {
        return this.aName;
    }

    public String getDialog() {
        return this.aDialog;
    }

    public String getImageName() {
        return this.aImageName;
    }

    public String getMoves() {
        String vMoves= "";
        for (int i = 0; i < 4; i++)
            vMoves+= ((i + 1) + ". " + aMoves[i][0])+"\n";
        return vMoves;
    }

    void calculateDamage(Entity pEntity, int pMove) {

        System.out.println("\n\n");
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
