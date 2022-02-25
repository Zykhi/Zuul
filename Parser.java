import java.util.StringTokenizer;

/**
 * This class is part of the "Zuul GOTY Edition" application.
 * "Zuul GOTY Edition" is a very simple, text based adventure game.
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author Michael Kolling and David J. Barnes + D.Bureau
 * @version 2008.03.30 + 2013.09.15
 */
public class Parser {

    private CommandWords aCommandWords; // holds all valid command words

    /**
     * Create a new Parser.
     */
    public Parser() {
        this.aCommandWords = new CommandWords();
    } // Parser()

    /**
     * Get a new command from the user. The command is read by
     * parsing the 'inputLine'.
     * 
     * @param pInputLine line enter in text area
     * 
     * @return The next command from the user.
     */
    public Command getCommand(final String pInputLine) {
        String vWord1;
        String vWord2;

        StringTokenizer tokenizer = new StringTokenizer(pInputLine);

        if (tokenizer.hasMoreTokens())
            vWord1 = tokenizer.nextToken(); // get first word
        else
            vWord1 = null;

        if (tokenizer.hasMoreTokens())
            vWord2 = tokenizer.nextToken(); // get second word
        else
            vWord2 = null;

        // note: we just ignore the rest of the input line.

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).

        if (this.aCommandWords.isCommand(vWord1))
            return new Command(vWord1, vWord2);
        else
            return new Command(null, vWord2);
    } // getCommand(.)

    /**
     * @return a String with valid command words.
     */
    public String getCommandString() // was showCommands()
    {
        String vCommandString = "Your commands are : " + this.aCommandWords.getCommandList();
        return vCommandString;
    } // getCommandString()

} // Parser
