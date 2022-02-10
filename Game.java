public class Game
{
    private Room aCurrentRoom;
    private Parser aParser;
    
    private void createRooms(){
        Room vOutside = new Room("outside the main entrance of the university");
        Room vTheatre = new Room("in a lecture theatre");
        Room vPub = new Room ("campus pub");
        Room vLab  = new Room ("computing lab");
        Room vOffice = new Room ("computing admin");

        vOutside.setExits("east", vTheatre);
        vOutside.setExits("south", vLab);
        vOutside.setExits("west", vPub);
        
        vTheatre.setExits("west", vOutside);
        
        vPub.setExits("east", vOutside);
        
        vLab.setExits("north", vOutside);
        vLab.setExits("east", vOffice);
        
        vOffice.setExits("west", vLab);


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
        System.out.println("Welcome to the World of Zuul !");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        printLocationInfo();
    }
    
    private void printHelp()
    {
        System.out.println("You are lost. You are alone.");
        System.out.println("You wander around at the university.");
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
        System.out.println("Goodbye");
    }
    
    private void printLocationInfo()
    {
        System.out.println("You are "+aCurrentRoom.getDescription());
        System.out.println(aCurrentRoom.getExitString());
    }
}//Game