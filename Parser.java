import java.util.Scanner;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
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
    private CommandWords aValidCommands; // (voir la classe CommandWords)
    private Scanner aReader; // permettra de lire les commandes au clavier

    /**
     * Create a new Parser.
     */
    public Parser() {
        this.aValidCommands = new CommandWords();
        this.aReader = new Scanner(System.in);
        // System.in designe le clavier, comme System.out designe l'ecran
    } // Parser()

    /**
     * Get a new command from the user. The command is read by
     * parsing the 'inputLine'.
     * 
     * @return The next command from the user.
     */
    public Command getCommand() {
        String vInputLine; // contiendra toute la ligne tapee
        String vWord1 = null;
        String vWord2 = null;

        System.out.print("> "); // affiche le prompt (invite de commande)

        vInputLine = this.aReader.nextLine(); // lit la ligne tapee au clavier

        // cherche jusqu'a 2 mots dans la ligne tapee
        Scanner vTokenizer = new Scanner(vInputLine);
        if (vTokenizer.hasNext()) {
            vWord1 = vTokenizer.next(); // get first word
            if (vTokenizer.hasNext()) {
                vWord2 = vTokenizer.next(); // get second word
                // note: we just ignore the rest of the input line.
            } // if
        } // if

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if (this.aValidCommands.isCommand(vWord1)) {
            return new Command(vWord1, vWord2);
        } else {
            return new Command(null, null); // C'est surtout le premier null qui est important ici.
        }
    } // getCommand()

    /**
     * Returns a String with valid command words.
     */
    public String getCommandString() // was showCommands()
    {
        return this.aValidCommands.getCommandList();
    } // getCommandString()
} // Parser
