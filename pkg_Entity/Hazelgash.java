package pkg_Entity;

/**
 * This class implements Hazelgash
 * 
 * @author C.Diouy
 */
public class Hazelgash extends Entity {

    /**
     * Create Hazelgash
     */
    public Hazelgash() {
        super(200, 200, 100, 100, 100, 100, true);
        setName("Hazelgash");
        setDialog("Be careful of lava");
        setImageName("faceImages/hazelgash.png");
        setFullImageName("faceImages/fullhazelgash.png");
        aMoves[0][0] = "Flamethrower";
        aMoves[0][1] = "90";
        aMoves[0][2] = "95";
        aMoves[0][3] = "special";
        aMoves[1][0] = "Fireball";
        aMoves[1][1] = "100";
        aMoves[1][2] = "90";
        aMoves[1][3] = "special";
        aMoves[2][0] = "AirSlash";
        aMoves[2][1] = "85";
        aMoves[2][2] = "80";
        aMoves[2][3] = "special";
    }

}