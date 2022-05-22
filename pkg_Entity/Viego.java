package pkg_Entity;

/**
 * This class implements Viego
 * 
 * @author C.Diouy
 */
public class Viego extends Entity {

    /**
     * Create Viego
     */
    public Viego() {
        super(200, 200, 100, 100, 100, 100, true);
        setName("Viego");
        setDialog("you will die");
        setImageName("faceImages/viego.jpg");
        setFullImageName("faceImages/fullViego.gif");
        aMoves[0][0] = "Flamethrower";
        aMoves[0][1] = "90";
        aMoves[0][2] = "95";
        aMoves[0][3] = "physical";
        aMoves[1][0] = "BladeStroke";
        aMoves[1][1] = "80";
        aMoves[1][2] = "100";
        aMoves[1][3] = "physical";
        aMoves[2][0] = "AirSlash";
        aMoves[2][1] = "85";
        aMoves[2][2] = "80";
        aMoves[2][3] = "physical";
    }

}
