public class Game
{
    private Room aCurrentRoom;
    private Parser aParser;
    
    private void createRooms(){
        Room vOutside, vCatacombs, vLobby, vTreasure, vBoss1Room, vBoss2Room, vBoss3Room;
        
        vOutside = new Room("outside the dungeon");
        vCatacombs = new Room("in the catacombs");
        vLobby = new Room ("the main room of the dungeon");
        vTreasure  = new Room ("an empty room"); //or not ;)
        vBoss1Room = new Room ("boss room 1"); //need change desc of boss room
        vBoss2Room = new Room ("boss room 2");
        vBoss3Room = new Room ("boss room 3");

        vOutside.setExits("down", vLobby);
        
        vCatacombs.setExits("west", vBoss2Room);
        
        vLobby.setExits("north", vBoss1Room);
        vLobby.setExits("east", vBoss2Room);
        vLobby.setExits("west", vBoss3Room);
        
        vTreasure.setExits("east", vBoss3Room);
        
        vBoss1Room.setExits("south", vLobby);

        vBoss2Room.setExits("east", vCatacombs);
        vBoss2Room.setExits("west", vLobby);

        vBoss3Room.setExits("west", vTreasure);
        vBoss3Room.setExits("east", vLobby);

        this.aCurrentRoom = vOutside;
    }
    
    public Game(){
        this.createRooms();
        this.aParser = new Parser();
    }
    
    private void goRoom(final Command pDirection){
        if (!pDirection.hasSecondWord())
        {
             System.out.println("Go where ?");
             return;
        }

            
            String vDirection = pDirection.getSecondWord();
            Room vNextRoom = this.aCurrentRoom.getExit(vDirection);
        
        if(vNextRoom==null)
        {
            System.out.println("\n" + "there is no door!");
        }
        
        else
        {
            this.aCurrentRoom=vNextRoom;
            printLocationInfo();
        }
    }
    
    private void printWelcome()
    {
        System.out.println("Welcome to Zuul GOTY Edition !");
        System.out.println("Zuul GOTY Edition is a new, incredibly and fantastic adventure game.");
        System.out.println("Type 'help' if you need help.");
        printLocationInfo();
    }
    
    private void printHelp()
    {
        System.out.println("You are lost. You leave the fight.");
        System.out.println("You wander around the dungeon.");
        System.out.println("");
        System.out.println("Your command words are:");
        System.out.println("go quit help");
    }
    
    private boolean quit(final Command pQuit){
        if(pQuit.hasSecondWord()==true){
            System.out.println("Quit what ?");
            return false;}
            else{return true;}
    }
    
    private boolean processCommand(final Command pCommand)
    {
        if(pCommand.isUnknown()==true){
            System.out.println("I don't know what you mean...");
            return false;
        }
        
        if(pCommand.getCommandWord().equals("quit")){
            return this.quit(pCommand);
        }
        else if(pCommand.getCommandWord().equals("go")){
            this.goRoom(pCommand);
            return false;
        }
        else if(pCommand.getCommandWord().equals("help")){
            this.printHelp();
            return false;
        }
        else{return false;}
    }
    
    public void play()
    {
        printWelcome();
        boolean vFinished = false;
        Command vCommand;

        while(!vFinished)
        {
            vCommand=aParser.getCommand();
            vFinished=processCommand(vCommand);
 
        }
        System.out.println("Goodbye knight");
    }
    
    private void printLocationInfo()
    {
        System.out.println("You are "+aCurrentRoom.getDescription());
        System.out.println(aCurrentRoom.getExitString());
    }
}//Game