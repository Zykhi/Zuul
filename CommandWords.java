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
    private final String[] aValidCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords() {
        this.aValidCommands = new String[9];
        this.aValidCommands[0] = "go";
        this.aValidCommands[1] = "help";
        this.aValidCommands[2] = "quit";
        this.aValidCommands[3] = "look";
        this.aValidCommands[4] = "eat";
        this.aValidCommands[5] = "back";
        this.aValidCommands[6] = "test";
        this.aValidCommands[7] = "take";
        this.aValidCommands[8] = "drop";
    } // CommandWords()

    /**
     * Check whether a given String is a valid command word.
     * 
     * @param pString Command line
     * 
     * @return true if a given string is a valid command,
     *         false if it isn't.
     */
    public boolean isCommand(final String pString) {
        for (int i = 0; i < this.aValidCommands.length; i++) {
            if (this.aValidCommands[i].equals(pString))
                return true;
        } // for
          // if we get here, the string was not found in the commands
        return false;
    } // isCommand()

    /**
     * @return a String of all valid commands.
     */
    public String getCommandList() {
        StringBuilder sCommands = new StringBuilder();
        for (int i = 0; i < aValidCommands.length; i++) {
            sCommands.append(aValidCommands[i] + "  ");
        }
        return sCommands.toString();
    }
} // CommandWords
