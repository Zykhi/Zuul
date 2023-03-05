package pkg_Entity;

/**
 * This class implements NPC3
 * 
 * @author C.Diouy
 */
public class NPC3 extends Entity {

    /**
     * Create NPC3
     */
    public NPC3() {
        super(0, 0, 0, 0, 0, 0, true);
        setHP(aJsonReader.getNPC3HP());
        setMaxHP(aJsonReader.getNPC3MaxHP());
        setDef(aJsonReader.getNPC3Defense());
        setSpeDef(aJsonReader.getNPC3SpeDefense());
        setAtt(aJsonReader.getNPC3Attack());
        setSpeAtt(aJsonReader.getNPC3SpeAttack());
        setFightable(aJsonReader.getNPC3Fightable());
        setName(aJsonReader.getNPC3Name());
        setDialog(aJsonReader.getNPC3Dialogue());
        setImageName(aJsonReader.getNPC3Image());
        setFullImageName(aJsonReader.getNPC3FullImage());
        setMoves(aJsonReader.getNPC3Moves());
        setAttackDamage(aJsonReader.getNPC3AttackDamage());
        setAttackAccuracy(aJsonReader.getNPC3AttackAccuracy());
        setAttackType(aJsonReader.getNPC3AttackType());
    }

}