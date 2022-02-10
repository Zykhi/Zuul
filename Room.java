public class Room
{
    private String aDescription;
    public Room aNorthExit;
    public Room aEastExit;
    public Room aSouthExit;
    public Room aWestExit;
    
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
} // Room









