package pkg_Entity;

/**
 * This class implements Warmog
 * 
 * @author C.Diouy
 */
public class Warmog extends Entity {

    /**
     * Create Warmog
     */
    public Warmog() {
        super(200, 200, 100, 100, 100, 100, true);
        setName("Warmog");
        setDialog("You are in my forest");
        setImageName("faceImages/warmog.png");
        setFullImageName("faceImages/fullwarmog.png");
        aMoves[0][0] = "Flamethrower";
        aMoves[0][1] = "90";
        aMoves[0][2] = "95";
        aMoves[0][3] = "physical";
        aMoves[1][0] = "Earthquake";
        aMoves[1][1] = "100";
        aMoves[1][2] = "80";
        aMoves[1][3] = "physical";
        aMoves[2][0] = "AirSlash";
        aMoves[2][1] = "85";
        aMoves[2][2] = "80";
        aMoves[2][3] = "physical";
    }

}