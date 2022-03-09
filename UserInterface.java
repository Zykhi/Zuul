import java.net.URL;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling
 * @version 1.0 (Jan 2003) DB edited (2019) CD edited (2022)
 */
public class UserInterface implements ActionListener {
    private GameEngine aEngine;
    private JFrame aMyFrame;
    private JTextField aEntryField;
    private JTextArea aLog;
    private JLabel aImage;
    private JButton aQuitButton;
    private JButton aNorthButton;
    private JButton aSouthButton;
    private JButton aEastButton;
    private JButton aWestButton;
    private JButton aUpButton;
    private JButton aDownButton;
    private Parser aParser;
    private JButton aBackButton;
    private JButton aHelpButton;
    private JButton aDropButton;
    private JButton aTakeButton;
    private Component aChargeButton;
    private Component aFireButton;
    private JButton aInventoryButton;

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param pGameEngine The GameEngine object implementing the game logic.
     */
    public UserInterface(final GameEngine pGameEngine) {
        this.aEngine = pGameEngine;
        this.createGUI();
        this.aParser = new Parser();
    } // UserInterface(.)

    /**
     * Print out some text into the text area.
     * 
     * @param pText like sysout but for gui, is the text in " "
     */
    public void print(final String pText) {
        this.aLog.append(pText);
        this.aLog.setCaretPosition(this.aLog.getDocument().getLength());
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     * 
     * @param pText like sysout but for gui, is the text in " "
     */
    public void println(final String pText) {
        this.print(pText + "\n");
    } // println(.)

    /**
     * 
     * 
     * @author https://github.com/Vourem/SlowPrint-Method/blob/master/SlowPrint
     * @param pText
     */
    public void slowPrint(final String pText) {

        for (char c : pText.toCharArray()) {
            String vMessage = Character.toString(c);

            this.aLog.append(vMessage);
            this.aLog.setCaretPosition(this.aLog.getDocument().getLength());

            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void slowPrintln(final String pText) {
        this.slowPrint(pText + "\n");
    }

    /**
     * 
     * @author https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
     */
    public void playWelcomeSound() {
        try {
            AudioInputStream vAudioInputStream = AudioSystem
                    .getAudioInputStream(new File("gameSounds/welcome.wav").getAbsoluteFile());
            Clip vClip = AudioSystem.getClip();
            vClip.open(vAudioInputStream);
            vClip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    /**
     * Show an image file in the interface.
     * 
     * @param pImageName name of the image for the room
     */
    public void showImage(final String pImageName) {
        String vImagePath = "" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource(vImagePath);
        if (vImageURL == null)
            System.out.println("Image not found : " + vImagePath);
        else {
            ImageIcon vIcon = new ImageIcon(vImageURL);
            this.aImage.setIcon(vIcon);
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the input field.
     * 
     * @param pOnOff true when enable
     *               false when disable
     */
    public void enable(final boolean pOnOff) {
        this.aEntryField.setEditable(pOnOff); // enable/disable
        if (!pOnOff) { // disable
            this.aEntryField.getCaret().setBlinkRate(0); // cursor won't blink
            this.aEntryField.removeActionListener(this); // won't react to entry
        }
    } // enable(.)

    /**
     * Set up graphical user interface.
     */
    private void createGUI() {
        this.aMyFrame = new JFrame();
        this.aMyFrame.setTitle("Zuul GOTY Edition");
        // this.aMyFrame.setSize(650, 950);
        this.aMyFrame.setResizable(false);
        this.aMyFrame.setBackground(Color.DARK_GRAY);

        this.aEntryField = new JTextField(34);

        ImageIcon aGameIcon = new ImageIcon("gameImages/game.png");

        this.aQuitButton = new JButton("quit");
        this.aNorthButton = new JButton("north ▲");
        this.aEastButton = new JButton("east ▶");
        this.aSouthButton = new JButton("south ▼");
        this.aWestButton = new JButton("◀ west");
        this.aUpButton = new JButton("up △");
        this.aDownButton = new JButton("down ▽");
        this.aBackButton = new JButton("back ↺");
        this.aHelpButton = new JButton("help ?");
        this.aDropButton = new JButton("drop ☛");
        this.aTakeButton = new JButton("take ☚");
        this.aFireButton = new JButton("fire ◎");
        this.aChargeButton = new JButton("charge ⌁");
        this.aInventoryButton = new JButton("bag ₿");

        this.aMyFrame.setIconImage(aGameIcon.getImage());

        this.aLog = new JTextArea();
        this.aLog.setEditable(false);
        this.aLog.setLineWrap(true);
        this.aLog.setWrapStyleWord(true);
        this.aLog.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane vListScroller = new JScrollPane(this.aLog);
        vListScroller.setPreferredSize(new Dimension(200, 200));
        vListScroller.setMinimumSize(new Dimension(100, 100));

        JPanel vImagePanel = new JPanel();
        this.aImage = new JLabel();

        vImagePanel.setLayout(new BorderLayout()); // ==> only five places
        vImagePanel.add(this.aImage, BorderLayout.CENTER);

        JPanel vTextPanel = new JPanel();

        vTextPanel.setLayout(new BorderLayout());
        vTextPanel.add(vListScroller, BorderLayout.CENTER);
        vTextPanel.add(this.aEntryField, BorderLayout.SOUTH);

        JPanel vButtonPanel1 = new JPanel();
        JPanel vCenterButtonPanel = new JPanel();

        vCenterButtonPanel.setLayout(new GridLayout(1, 2));
        vCenterButtonPanel.setBackground(Color.DARK_GRAY);
        vCenterButtonPanel.add(this.aUpButton);
        vCenterButtonPanel.add(this.aDownButton);

        vButtonPanel1.setLayout(new BorderLayout());
        vButtonPanel1.setBackground(Color.DARK_GRAY);
        vButtonPanel1.add(this.aNorthButton, BorderLayout.NORTH);
        vButtonPanel1.add(this.aWestButton, BorderLayout.WEST);
        vButtonPanel1.add(this.aSouthButton, BorderLayout.SOUTH);
        vButtonPanel1.add(this.aEastButton, BorderLayout.EAST);
        vButtonPanel1.add(vCenterButtonPanel, BorderLayout.CENTER);

        JPanel vButtonPanel2 = new JPanel();
        vButtonPanel2.setLayout(new GridLayout(3, 3));
        vButtonPanel2.setBackground(Color.DARK_GRAY);
        vButtonPanel2.add(this.aBackButton);
        vButtonPanel2.add(this.aHelpButton);
        vButtonPanel2.add(this.aQuitButton);
        vButtonPanel2.add(this.aDropButton);
        vButtonPanel2.add(this.aTakeButton);
        vButtonPanel2.add(this.aFireButton);
        vButtonPanel2.add(this.aChargeButton);
        vButtonPanel2.add(this.aInventoryButton);

        JPanel vPanel = new JPanel();
        vPanel.setLayout(new GridBagLayout());
        GridBagConstraints vGridBagLayout = new GridBagConstraints();
        vGridBagLayout.weightx = 1;
        vGridBagLayout.weighty = 1;

        vGridBagLayout.gridx = 0;
        vGridBagLayout.gridwidth = 2;
        vGridBagLayout.gridheight = 2;
        vGridBagLayout.gridy = 0;
        vGridBagLayout.fill = GridBagConstraints.BOTH;
        vPanel.add(vImagePanel, vGridBagLayout);

        vGridBagLayout.gridx = 0;
        vGridBagLayout.gridwidth = 1;
        vGridBagLayout.gridheight = 1;
        vGridBagLayout.gridy = 2;
        vPanel.add(vButtonPanel1, vGridBagLayout);

        vGridBagLayout.gridx = 1;
        vGridBagLayout.gridwidth = 1;
        vGridBagLayout.gridheight = 1;
        vGridBagLayout.gridy = 2;
        vPanel.add(vButtonPanel2, vGridBagLayout);

        vGridBagLayout.gridx = 2;
        vGridBagLayout.gridwidth = 1;
        vGridBagLayout.gridheight = 3;
        vGridBagLayout.gridy = 0;
        vPanel.add(vTextPanel, vGridBagLayout);

        Font vFont = new Font("Monospaced", Font.PLAIN, 14);

        this.aLog.setFont(vFont);
        this.aLog.setForeground(Color.white);
        this.aLog.setBackground(Color.darkGray);

        this.aMyFrame.getContentPane().add(vPanel, BorderLayout.CENTER);

        // add some event listeners to some components
        this.aEntryField.addActionListener(this);
        this.aQuitButton.addActionListener(this);

        // to end program when window is closed
        this.aMyFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.aMyFrame.pack();
        this.aMyFrame.setVisible(true);
        this.aEntryField.requestFocus();
    } // createGUI()

    /**
     * Actionlistener interface for entry textfield.
     * 
     * @param pE event of actionListener
     */
    public void actionPerformed(final ActionEvent pE) {
        // check the type of action
        if (pE.getActionCommand() != null) {
            this.aEngine.interpretCommand(aParser.getCommand(pE.getActionCommand()));
            this.aEntryField.setText(""); // to reset entry field
        } else {
            this.processCommand(); // never suppress this line
        }
    } // actionPerformed(.)

    /**
     * A command has been entered. Read the command and do whatever is
     * necessary to process it.
     */
    private void processCommand() {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText("");

        this.aEngine.interpretCommand(aParser.getCommand(vInput));
    } // processCommand()

    /**
     * get the entry field
     * 
     * @return the text in the entry field
     */
    public String getEntryField() {
        return this.aEntryField.getText();
    }
} // UserInterface
