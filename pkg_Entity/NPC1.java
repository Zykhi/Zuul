package pkg_Entity;

/**
 * This class implements NPC 1
 *
 * @author C.Diouy
 */
public class NPC1 extends Entity {

  /**
   * Create NPC 1
   */
  public NPC1() {
    super(0, 0, 0, 0, 0, 0, true);
    setHP(aJsonReader.getNPC1HP());
    setMaxHP(aJsonReader.getNPC1MaxHP());
    setDef(aJsonReader.getNPC1Defense());
    setSpeDef(aJsonReader.getNPC1SpeDefense());
    setAtt(aJsonReader.getNPC1Attack());
    setSpeAtt(aJsonReader.getNPC1SpeAttack());
    setFightable(aJsonReader.getNPC1Fightable());
    setName(aJsonReader.getNPC1Name());
    setDialog(aJsonReader.getNPC1Dialogue());
    setMidHPDialogue(/*aJsonReader.getNPC1MidHPDialogue()*/"this is a test");
    setImageName(aJsonReader.getNPC1Image());
    setFullImageName(aJsonReader.getNPC1FullImage());
    setMoves(aJsonReader.getNPC1Moves());
    setAttackDamage(aJsonReader.getNPC1AttackDamage());
    setAttackAccuracy(aJsonReader.getNPC1AttackAccuracy());
    setAttackType(aJsonReader.getNPC1AttackType());
  }
}
