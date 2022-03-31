public class Hazelgash extends Entity {

    public Hazelgash() {
        super(100, 100, 100, 100, 100, 100);
        aName = "Hazelgash";
        aDialog = "Be careful of lava";
        aImageName = "faceImages/hazelgash.png";
        aMoves[0][0] = "Flamethrower";
        aMoves[0][1] = "90";
        aMoves[0][2] = "95";
        aMoves[0][3] = "physical";
        aMoves[1][0] = "Earthquake";
        aMoves[1][1] = "100";
        aMoves[1][2] = "100";
        aMoves[1][3] = "physical";
        aMoves[2][0] = "Air Slash";
        aMoves[2][1] = "85";
        aMoves[2][2] = "80";
        aMoves[2][3] = "physical";
        aMoves[3][0] = "Rock Slide";
        aMoves[3][1] = "80";
        aMoves[3][2] = "85";
        aMoves[3][3] = "physical";
    }

}