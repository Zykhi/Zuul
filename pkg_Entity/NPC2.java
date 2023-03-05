package pkg_Entity;

/**
 * This class implements NPC2
 * 
 * @author C.Diouy
 */
public class NPC2 extends Entity {

    /**
     * Create NPC2
     */
    public NPC2() {
        super(0, 0, 0, 0, 0, 0, true);
        setHP(aJsonReader.getNPC2HP());
        setMaxHP(aJsonReader.getNPC2MaxHP());
        setDef(aJsonReader.getNPC2Defense());
        setSpeDef(aJsonReader.getNPC2SpeDefense());
        setAtt(aJsonReader.getNPC2Attack());
        setSpeAtt(aJsonReader.getNPC2SpeAttack());
        setFightable(aJsonReader.getNPC2Fightable());
        setName(aJsonReader.getNPC2Name());
        setDialog(aJsonReader.getNPC2Dialogue());
        setImageName(aJsonReader.getNPC2Image());
        setFullImageName(aJsonReader.getNPC2FullImage());
        setMoves(aJsonReader.getNPC2Moves());
        setAttackDamage(aJsonReader.getNPC2AttackDamage());
        setAttackAccuracy(aJsonReader.getNPC2AttackAccuracy());
        setAttackType(aJsonReader.getNPC2AttackType());
    }

}
