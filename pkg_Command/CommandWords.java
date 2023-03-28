package pkg_Command;
import java.util.HashMap;
import java.util.Set;

import pkg_Core.JSONReader;

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
    private JSONReader aJsonReader = new JSONReader();

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords() {
        aValidCommands = new HashMap<String, CommandWord>();
        this.aValidCommands.put(aJsonReader.getHelpCommandWord(), CommandWord.HELP);
        this.aValidCommands.put(aJsonReader.getGoCommandWord(), CommandWord.GO);
        this.aValidCommands.put(aJsonReader.getQuitCommandWord(), CommandWord.QUIT);
        this.aValidCommands.put(aJsonReader.getLookCommandWord(), CommandWord.LOOK);
        this.aValidCommands.put(aJsonReader.getEatCommandWord(), CommandWord.EAT);
        this.aValidCommands.put(aJsonReader.getBackCommandWord(), CommandWord.BACK);
        this.aValidCommands.put(aJsonReader.getTestCommandWord(), CommandWord.TEST);
        this.aValidCommands.put(aJsonReader.getTakeCommandWord(), CommandWord.TAKE);
        this.aValidCommands.put(aJsonReader.getDropCommandWord(), CommandWord.DROP);
        this.aValidCommands.put(aJsonReader.getInventoryCommandWord(), CommandWord.INVENTORY);
        this.aValidCommands.put(aJsonReader.getChargeCommandWord(), CommandWord.CHARGE);
        this.aValidCommands.put(aJsonReader.getFireCommandWord(), CommandWord.FIRE);
        this.aValidCommands.put(aJsonReader.getExitCommandWord(), CommandWord.EXIT);
        this.aValidCommands.put(aJsonReader.getAleaCommandWord(), CommandWord.ALEA);
        this.aValidCommands.put(aJsonReader.getFightCommandWord(), CommandWord.FIGHT);
        this.aValidCommands.put(aJsonReader.getLeaveCommandWord(), CommandWord.LEAVE);
        this.aValidCommands.put(aJsonReader.getUseCommandWord(), CommandWord.USE);
        this.aValidCommands.put(aJsonReader.getGiveCommandWord(), CommandWord.GIVE);
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
