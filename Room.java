public class Room
{
    private String   aDescription;
    private Room     aNorthExit;
    private Room     aEastExit;
    private Room     aSouthExit;
    private Room     aWestExit;
    
    public Room(final String pDescription)
    {
        this.aDescription = pDescription;
    }
    
    public String getDescription()
    {
        return this.aDescription;
    }
    
    public void setExits(final Room pNExit, final Room pSExit, final Room pEExit, final Room pWExit){
        this.aNorthExit = pNExit;
        this.aSouthExit = pSExit;
        this.aEastExit = pEExit;
        this.aWestExit = pWExit;
    }
    
    public Room getExit(String pDirection){
        if(pDirection.equals("north")){
            return aNorthExit;
        }
        if(pDirection.equals("east")){
            return aEastExit;
        }
        if(pDirection.equals("south")){
            return aSouthExit;
        }
        if(pDirection.equals("west")){
            return aWestExit;
        }
        return null;
    }
} // Room