/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public enum CommandWord {
    // A value for each command word, plus one for unrecognised
    // commands.
    HELP("help"), GO("go"), QUIT("quit"), LOOK("look"), EAT("eat"), BACK("back"),
    TEST("test"), TAKE("take"), DROP("drop"), INVENTORY("inventory"), UNKNOWN("?");

    // The command string.
    private String aCommandString;

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