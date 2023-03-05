package pkg_Entity;

/**
 * This class implements NPC4
 * 
 * @author C.Diouy
 */
public class NPC4 extends Entity {

    /**
     * Create NPC4
     */
    public NPC4() {
        super(0, 0, 0, 0, 0, 0, true);
        setHP(aJsonReader.getNPC4HP());
        setMaxHP(aJsonReader.getNPC4MaxHP());
        setDef(aJsonReader.getNPC4Defense());
        setSpeDef(aJsonReader.getNPC4SpeDefense());
        setAtt(aJsonReader.getNPC4Attack());
        setSpeAtt(aJsonReader.getNPC4SpeAttack());
        setFightable(aJsonReader.getNPC4Fightable());
        setName(aJsonReader.getNPC4Name());
        setDialog(aJsonReader.getNPC4Dialogue());
        setImageName(aJsonReader.getNPC4Image());
        setFullImageName(aJsonReader.getNPC4FullImage());
        setMoves(aJsonReader.getNPC4Moves());
        setAttackDamage(aJsonReader.getNPC4AttackDamage());
        setAttackAccuracy(aJsonReader.getNPC4AttackAccuracy());
        setAttackType(aJsonReader.getNPC4AttackType());
    }

}
