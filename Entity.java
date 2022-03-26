public class Entity {
    private String aName;
    private String aDialog;
    private String aImageName;

    public Entity(String pName, String pDialog, String pImageName) {
        this.aName = pName;
        this.aDialog = pDialog;
        this.aImageName = pImageName;
    }

    public String getName() {
        return this.aName;
    }

    public String getDialog() {
        return this.aDialog;
    }

    public String getImageName() {
        return this.aImageName;
    }
}
