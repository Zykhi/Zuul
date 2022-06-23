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
        setDialog("Welcome to the dungeon! How are you? You need to fight 3 boss and collect artefact to lifting the curse!");
        setImageName("faceImages/garret.png");
    }

}
