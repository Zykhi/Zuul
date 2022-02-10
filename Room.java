import java.util.HashMap;
import java.util.Set;

public class Room
{
    private String   aDescription;
    private HashMap<String, Room> aExits;
    
    public Room(final String pDescription)
    {
        this.aDescription = pDescription;
    }
    
    public String getDescription()
    {
        return this.aDescription;
    }
    
    public void setExits(final String pDirec, Room pNeighbor){
        aExits.put(pDirec, pNeighbor);
    }

    
    public Room getExit(String pDirection){
        return aExits.get(pDirection);
    }

    public String getExitString()
    {
        String vReturnString = "Exits : ";
        Set<String> vKeys = aExits.keySet();
        for(String vExit : vKeys){
            vReturnString += " " + vExit;
        }
        return vReturnString;
    }
} // Room