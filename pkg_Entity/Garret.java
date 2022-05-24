package pkg_Entity;

/**
 * This class implements Garret
 * 
 * @author C.Diouy
 */
public class Garret extends Entity {

    /**
     * Create Garret
     */
    public Garret() {
        super(100, 100, 100, 100, 100, 100, false);
        setName("Garret");
        setDialog("Welcome to the dungeon! How are you?");
        setImageName("faceImages/garret.png");
    }

}