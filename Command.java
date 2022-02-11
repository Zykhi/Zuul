/**
 * This class is part of the "Zuul GOTY Edition" application.
 * "Zuul GOTY Edition" is a very simple, text based adventure game.
 * 
 * This class manages the operation of commands for the game.
 * It is used to create the base of the command.
 *
 * @author Michael Kolling and David J. Barnes + D.Bureau + C.Diouy
 * @version 2008.03.30 + 2019.09.25 + 2022.02.11
 */
public class Command {
    private String aCommandWord;
    private String aSecondWord;

    /**
     * This constructor init command system with 2 words
     * 
     * @param pCommandWord
     * @param pSecondWord
     */
    public Command(final String pCommandWord, final String pSecondWord) {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    }

    /**
     * This String get the command word
     * 
     * @return CommandWord
     */
    public String getCommandWord() {
        return this.aCommandWord;
    }

    /**
     * This String get the second word
     * 
     * @return SecondWord
     */
    public String getSecondWord() {
        return this.aSecondWord;
    }

    /**
     * This boolean check if there is a second word
     * 
     * @return true or false
     */
    public boolean hasSecondWord() {
        return this.aSecondWord != null;
    }

    /**
     * This boolean check if the command is known
     * 
     * @return true or false
     */
    public boolean isUnknown() {
        return this.aCommandWord == null;
    }
} // Command
