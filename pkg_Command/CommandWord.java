package pkg_Command;

import pkg_Core.JSONReader;

/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author Michael Kolling and David J. Barnes + C.Diouy
 * @version 2006.03.30 + 2022.02.25
 */
public enum CommandWord {
    // A value for each command word, plus one for unrecognised
    // commands.
    HELP(""), GO("go"), QUIT("quit"), LOOK("look"), EAT("eat"), BACK("back"),
    TEST("test"), TAKE("take"), DROP("drop"), INVENTORY("inventory"), UNKNOWN("?"),
    CHARGE("charge"), FIRE("fire"), EXIT("exit"), ALEA("alea"), FIGHT("fight"),
    LEAVE("leave"), USE("use"), GIVE("give");

    // The command string.
    private String aCommandString;
    static JSONReader aJsonReader = new JSONReader();

    /**
     * Initialise with the corresponding command word.
     * 
     * @param pCommandWord The command string.
     */
    CommandWord(String pCommandWord) {
        this.aCommandString = pCommandWord;
    }

    /**
     * @return The command word as a string.
     */
    public String toString() {
        return aCommandString;
    }

}