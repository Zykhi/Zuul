import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private static final Pattern PATTERN = Pattern.compile("^(?<commandword>\\S+)\\s?(?<secondword>.*)$");
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
        String vWord1 = null;
        String vWord2 = null;

        Matcher vMatcher = PATTERN.matcher(pInputLine);
        MatchResult vResult = vMatcher.toMatchResult();

        if (vMatcher.find()) {
            String vCommandWord = vMatcher.group("commandword");
            String vSecondWord = vMatcher.group("secondword").replace(' ', '_');
            vWord1 = vCommandWord;
            vWord2 = vSecondWord.equals("") ? null : vSecondWord;
        }

        // System.out.println(vWord1 + " " + vWord2);
        return new Command(aCommandWords.getCommandWord(vWord1), vWord2);
    } // getCommand(.)

    /**
     * @return a String with valid command words.
     */
    public String getCommandString() // was showCommands()
    {
        return "Your commands are : " + this.aCommandWords.getCommandList();
    } // getCommandString()

} // Parser
