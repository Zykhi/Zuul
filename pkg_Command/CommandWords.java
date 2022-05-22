package pkg_Command;
import java.util.HashMap;
import java.util.Set;

/**
 * This class is part of the "Zuul GOTY Edition" application.
 * "Zuul GOTY Edition" is a very simple, text based adventure game.
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author Michael Kolling and David J. Barnes + D.Bureau + C.Diouy
 * @version 2008.03.30 + 2019.09.25 + 2022.02.25
 */
public class CommandWords {
    // A mapping between a command word and the CommandWord
    // associated with it.
    private HashMap<String, CommandWord> aValidCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords() {
        aValidCommands = new HashMap<String, CommandWord>();
        for (CommandWord vCommand : CommandWord.values()) {
            if (vCommand != CommandWord.UNKNOWN) {
                aValidCommands.put(vCommand.toString(), vCommand);
            }
        }
    } // CommandWords()

    /**
     * Check whether a given String is a valid command word.
     * 
     * @param pString command entered by the player
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String pString) {
        return aValidCommands.containsKey(pString);
    }

    /**
     * Find the CommandWord associated with a command word.
     * 
     * @param pCommandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String pCommandWord) {
        CommandWord vCommand = aValidCommands.get(pCommandWord);
        if (vCommand != null) {
            return vCommand;
        } else {
            return CommandWord.UNKNOWN;
        }
    }

    /**
     * This function get all commands in a String
     * 
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
