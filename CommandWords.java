import java.util.HashMap;
import java.util.Set;

/**
 * This class is part of the "Zuul GOTY Edition" application.
 * "Zuul GOTY Edition" is a very simple, text based adventure game.
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author Michael Kolling and David J. Barnes + D.Bureau
 * @version 2008.03.30 + 2019.09.25
 */
public class CommandWords {
    // a constant array that will hold all valid command words
    private HashMap<String, CommandWord> aValidCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords() {
        this.aValidCommands = new HashMap<String, CommandWord>();
        this.aValidCommands.put("go", CommandWord.GO);
        this.aValidCommands.put("help", CommandWord.HELP);
        this.aValidCommands.put("quit", CommandWord.QUIT);
        this.aValidCommands.put("look", CommandWord.LOOK);
        this.aValidCommands.put("eat", CommandWord.EAT);
        this.aValidCommands.put("back", CommandWord.BACK);
        this.aValidCommands.put("test", CommandWord.TEST);
        this.aValidCommands.put("take", CommandWord.TAKE);
        this.aValidCommands.put("drop", CommandWord.DROP);
        this.aValidCommands.put("inventory", CommandWord.INVENTORY);
    } // CommandWords()

    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return aValidCommands.containsKey(aString);
    }

    /**
     * Find the CommandWord associated with a command word.
     * @param pCommandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String pCommandWord)
    {
        CommandWord vCommand = aValidCommands.get(pCommandWord);
        if(vCommand != null) {
            return vCommand;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }

    /**
     * @return a String of all valid commands.
     */
    public String getCommandList() {
        String vCommandString = "";
        Set<String> vKeys = aValidCommands.keySet();
        for (String vCommand : vKeys) {
            vCommandString += " " + vCommand;
        }
        return vCommandString;
    }
} // CommandWords
