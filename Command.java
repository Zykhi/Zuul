/**
 * This class is part of the "Zuul GOTY Edition" application.
 * "Zuul GOTY Edition" is a very simple, text based adventure game.
 * 
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two strings: a command word and a second
 * word (for example, if the command was "take map", then the two strings
 * obviously are "take" and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is null.
 *
 * If the command had only one word, then the second word is null.
 *
 * @author Michael Kolling and David J. Barnes + D.Bureau + C.Diouy
 * @version 2008.03.30 + 2019.09.25 + 2022.02.11
 */
public class Command {
    private CommandWord aCommandWord;
    private String aSecondWord;

    /**
     * Create a command object. First and second word must be supplied, but
     * either one (or both) can be null. The command word should be null to
     * indicate that this was a command that is not recognised by this game.
     * 
     * @param pCommandWord first word like "go" or "help"
     * @param pSecondWord  second word if it's needed like "north"
     */
    public Command(final CommandWord pCommandWord, final String pSecondWord) {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord != null ? pSecondWord.toLowerCase() : null;
    }

    /**
     * This String get the command word
     * 
     * @return the command word (the first word) of this command. If the
     *         command was not understood, the result is null.
     */
    public CommandWord getCommandWord() {
        return this.aCommandWord;
    }

    /**
     * This String get the second word
     * 
     * @return the second word of this command. Returns null if there was no
     *         second word.
     */
    public String getSecondWord() {
        return this.aSecondWord;
    }

    /**
     * This boolean check if there is a second word
     * 
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord() {
        return this.aSecondWord != null;
    }

    /**
     * This boolean check if the command is known
     * 
     * @return true if this command was not understood.
     */
    public boolean isUnknown() {
        return this.aCommandWord == CommandWord.UNKNOWN;
    }
} // Command
