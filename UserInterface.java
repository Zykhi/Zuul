import java.net.URL;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
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
    private JLayeredPane aLayeredPane;
    private JTextField aEntryField;
    private JTextArea aLog;
    private JTextArea aEntityLog;
    private JLabel aImage;
    private JLabel aBattleGroundImage;
    private JLabel aEntityImage;
    private JLabel aEntityFullImage;
    private JLabel aGameTimer;
    private JLabel aEnemyHP;
    private JLabel aPlayerHP;
    private JPanel aEntityPanel;
    private JPanel aBattlerPanel;
    private JButton aQuitButton;
    private JButton aNorthButton;
    private JButton aSouthButton;
    private JButton aEastButton;
    private JButton aWestButton;
    private JButton aUpButton;
    private JButton aDownButton;
    private JButton aBackButton;
    private JButton aHelpButton;
    private JButton aDropButton;
    private JButton aTakeButton;
    private JButton aChargeButton;
    private JButton aFireButton;
    private JButton aInventoryButton;
    private JButton aSkipButton;
    private Parser aParser;
    private Clip aClip;
    private Clip aDialogClip;
    private Timer aTimer;
    private ActionListener aTaskTimer;
    private Font aFont;
    private Font aButtonsFont;
    private Font aTextFont;
    private int aTime = 60;
    private int aMinute;
    private int aSecond;
    private int aDelay = 1000;
    private int aEndTime = 20;
    private int aIndex;
    private int aSelectedMove;

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param pGameEngine The GameEngine object implementing the game logic.
     */
    public UserInterface(final GameEngine pGameEngine) {
        this.aEngine = pGameEngine;
        this.createFont();
        this.createGUI();
        this.aParser = new Parser();
        this.startTimer();
    } // UserInterface(.)

    // print methods

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
     * This method prints slowly the text in param
     * 
     * This code was found on internet and update for my use
     * 
     * @author https://github.com/Vourem/SlowPrint-Method/blob/master/SlowPrint
     * @param pText Text you want to write slowly
     */
    public void slowPrint(final String pText) {

        for (char c : pText.toCharArray()) {
            String vMessage = Character.toString(c);

            this.aLog.append(vMessage);
            this.aLog.setCaretPosition(this.aLog.getDocument().getLength());

            try {
                Thread.sleep(aTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is like SlowPrint but for println
     * 
     * @param pText Text you want to write slowly
     */
    public void slowPrintln(final String pText) {
        this.slowPrint(pText + "\n");
    }

    /**
     * Print out some text into the entity text area.
     * 
     * @param pText like sysout but for gui, is the text in " "
     */
    public void printEntity(final String pText) {
        this.aEntityLog.append(pText);
        this.aEntityLog.setCaretPosition(this.aEntityLog.getDocument().getLength());
    } // print(.)

    /**
     * Print out some text into the entity text area, followed by a line break.
     * 
     * @param pText like sysout but for gui, is the text in " "
     */
    public void printlnEntity(final String pText) {
        this.printEntity(pText + "\n");
    } // println(.)

    // FIXME Exception in thread "AWT-EventQueue-0"
    // java.lang.StringIndexOutOfBoundsException: begin 0, end 22, length 21
    public void slowPrintEntity(final String pText) {

        Timer timer = new Timer(60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent pEvent) {
                aEntityLog.setText(pText.substring(0, aIndex));
                aIndex++;
                if (aIndex > pText.length()) {
                    ((Timer) pEvent.getSource()).stop();
                }
            }

        });

        timer.start();
        aIndex = 0;
    }

    public void clearDialogArea() {
        this.aEntityLog.setText("");
    }

    /**
     * This method is called when skip button is clicked
     * It's write quikly the text of the slowPrint method
     */
    public void skipMethod() {
        this.aTime = 1;
        stopDialogSound();
    }

    // sound methods

    /**
     * This method play sound.
     * 
     * This code was found on internet and update for my use
     * 
     * @author https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
     * @param pFile File you want to play
     * @param pLoop Number of repetitions of the sound (-1 is infinite)
     */
    public void playSound(final String pFile, final int pLoop) {
        try {
            AudioInputStream vAudioInputStream = AudioSystem
                    .getAudioInputStream(new File("gameSounds/" + pFile + ".wav").getAbsoluteFile());
            aClip = AudioSystem.getClip();
            aClip.open(vAudioInputStream);
            aClip.start();
            aClip.loop(pLoop);
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    /**
     * This method play sound of the narrator
     */
    public void playDialogSound(final String pFile) {
        try {
            AudioInputStream vAudioInputStream = AudioSystem
                    .getAudioInputStream(new File("gameSounds/" + pFile + ".wav").getAbsoluteFile());
            aDialogClip = AudioSystem.getClip();
            aDialogClip.open(vAudioInputStream);
            aDialogClip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    /**
     * This method stop sound.
     */
    public void stopSound() {
        aClip.stop();
    }

    /**
     * This method stop the sound of the narrator
     */
    public void stopDialogSound() {
        aDialogClip.stop();
    }

    /**
     * This method play sound of the room
     */
    public void playRoomSound() {
        this.aEngine.playRoomSound();
    }

    // image method

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
     * Show an image file in the interface.
     * 
     * @param pImageName name of the image for the room
     */
    public void showEntityImage(final String pImageName) {
        String vImagePath = "" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource(vImagePath);
        if (vImageURL == null)
            System.out.println("Image not found : " + vImagePath);
        else {
            ImageIcon vIcon = new ImageIcon(vImageURL);
            this.aEntityImage.setIcon(vIcon);
            this.aMyFrame.pack();
        }
    }

    // enable method

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
            this.aQuitButton.removeActionListener(this);
            this.aNorthButton.removeActionListener(this);
            this.aSouthButton.removeActionListener(this);
            this.aEastButton.removeActionListener(this);
            this.aWestButton.removeActionListener(this);
            this.aUpButton.removeActionListener(this);
            this.aDownButton.removeActionListener(this);
            this.aBackButton.removeActionListener(this);
            this.aHelpButton.removeActionListener(this);
            this.aDropButton.removeActionListener(this);
            this.aTakeButton.removeActionListener(this);
            this.aFireButton.removeActionListener(this);
            this.aChargeButton.removeActionListener(this);
            this.aInventoryButton.removeActionListener(this);
            this.aSkipButton.removeActionListener(this);
        }
    } // enable(.)

    // font method

    /**
     * This method create custom font
     */
    private void createFont() {
        // to import custom font
        // https://www.ryisnow.online/2021/04/java-for-beginner-how-to-use-custom-font.html
        try {
            aFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/8bit.ttf"));
            GraphicsEnvironment vGraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            vGraphicsEnvironment.registerFont(aFont);

            aTextFont = aFont.deriveFont(18f);
            aButtonsFont = aFont.deriveFont(14f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    // gui method

    /**
     * Set up graphical user interface.
     */
    private void createGUI() {
        this.aMyFrame = new JFrame();
        this.aMyFrame.setTitle("Zuul GOTY Edition");
        this.aMyFrame.setResizable(false);
        this.aMyFrame.setPreferredSize(new Dimension(1077, 765));

        ImageIcon aGameIcon = new ImageIcon("gameImages/game.png");

        this.aMyFrame.setIconImage(aGameIcon.getImage());

        this.createButton();
        this.createPanel();

        this.aMyFrame.add(aLayeredPane);

        // to end program when window is closed
        // update the method to have something shorter
        this.aMyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.aMyFrame.pack();
        this.aMyFrame.setVisible(true);
        this.aEntryField.requestFocus();

    } // createGUI()

    /**
     * This method creates all the button
     */
    public void createButton() {
        this.aQuitButton = new JButton("quit");
        this.aNorthButton = new JButton("north ▲");
        this.aEastButton = new JButton("east ▶");
        this.aSouthButton = new JButton("south ▼");
        this.aWestButton = new JButton("◀ west");
        this.aUpButton = new JButton("up △");
        this.aDownButton = new JButton("down ▽");
        this.aBackButton = new JButton("back ");
        this.aHelpButton = new JButton("help ?");
        this.aDropButton = new JButton("drop");
        this.aTakeButton = new JButton("take");
        this.aFireButton = new JButton("fire");
        this.aChargeButton = new JButton("charge");
        this.aInventoryButton = new JButton("bag");
        this.aSkipButton = new JButton("skip");

        // add custom font on buttons
        this.aQuitButton.setFont(aButtonsFont);
        this.aNorthButton.setFont(aButtonsFont);
        this.aEastButton.setFont(aButtonsFont);
        this.aSouthButton.setFont(aButtonsFont);
        this.aWestButton.setFont(aButtonsFont);
        this.aUpButton.setFont(aButtonsFont);
        this.aDownButton.setFont(aButtonsFont);
        this.aBackButton.setFont(aButtonsFont);
        this.aHelpButton.setFont(aButtonsFont);
        this.aDropButton.setFont(aButtonsFont);
        this.aTakeButton.setFont(aButtonsFont);
        this.aFireButton.setFont(aButtonsFont);
        this.aChargeButton.setFont(aButtonsFont);
        this.aInventoryButton.setFont(aButtonsFont);
        this.aSkipButton.setFont(aButtonsFont);

        // add some event listeners to some components
        this.aQuitButton.addActionListener(this);
        this.aNorthButton.addActionListener(this);
        this.aSouthButton.addActionListener(this);
        this.aEastButton.addActionListener(this);
        this.aWestButton.addActionListener(this);
        this.aUpButton.addActionListener(this);
        this.aDownButton.addActionListener(this);
        this.aBackButton.addActionListener(this);
        this.aHelpButton.addActionListener(this);
        this.aDropButton.addActionListener(e -> dropButtonMethod());
        this.aTakeButton.addActionListener(e -> takeButtonMethod());
        this.aFireButton.addActionListener(this);
        this.aChargeButton.addActionListener(this);
        this.aInventoryButton.addActionListener(e -> battleButtonMethod());
        this.aSkipButton.addActionListener(this);

        // set action to write differents names
        this.aQuitButton.setActionCommand("quit");
        this.aNorthButton.setActionCommand("go north");
        this.aSouthButton.setActionCommand("go south");
        this.aEastButton.setActionCommand("go east");
        this.aWestButton.setActionCommand("go west");
        this.aUpButton.setActionCommand("go up");
        this.aDownButton.setActionCommand("go down");
        this.aBackButton.setActionCommand("back");
        this.aHelpButton.setActionCommand("help");
        this.aDropButton.setActionCommand("drop");
        this.aTakeButton.setActionCommand("take");
        this.aFireButton.setActionCommand("fire");
        this.aChargeButton.setActionCommand("charge");
        this.aInventoryButton.setActionCommand("inventory");
        this.aSkipButton.setActionCommand("skip");
    }

    /**
     * This method create all the panels
     */
    private void createPanel() {

        this.aEntryField = new JTextField(34);
        this.aEntryField.addActionListener(this);

        this.aLog = new JTextArea();
        this.aLog.setEditable(false);
        this.aLog.setLineWrap(true);
        this.aLog.setWrapStyleWord(true);
        this.aLog.setMargin(new Insets(10, 10, 10, 10));
        this.aLog.setFont(aTextFont);
        this.aLog.setForeground(Color.white);
        this.aLog.setBackground(Color.darkGray);
        JScrollPane vListScroller = new JScrollPane(this.aLog);
        vListScroller.setPreferredSize(new Dimension(414, 707));

        this.aEntityLog = new JTextArea();
        this.aEntityLog.setEditable(false);
        this.aEntityLog.setLineWrap(true);
        this.aEntityLog.setWrapStyleWord(true);
        this.aEntityLog.setMargin(new Insets(10, 10, 10, 10));
        this.aEntityLog.setFont(aTextFont);
        this.aEntityLog.setForeground(Color.white);
        this.aEntityLog.setBackground(Color.darkGray);
        JScrollPane vEntityScroller = new JScrollPane(this.aEntityLog);
        vEntityScroller.setPreferredSize(new Dimension(430, 100));

        this.aEntityPanel = new JPanel();
        this.aEntityImage = new JLabel();
        this.aEntityPanel.setPreferredSize(new Dimension(630, 100));
        this.aEntityPanel.setLayout(new BorderLayout());
        this.aEntityPanel.add(vEntityScroller, BorderLayout.CENTER);
        this.aEntityPanel.add(this.aEntityImage, BorderLayout.EAST);
        this.aEntityPanel.setSize(this.aEntityPanel.getPreferredSize());
        this.aEntityPanel.setLocation(10, 540);
        this.aEntityPanel.setVisible(false);

        JPanel vImagePanel = new JPanel();
        this.aImage = new JLabel();
        vImagePanel.setPreferredSize(new Dimension(650, 650));
        vImagePanel.setSize(vImagePanel.getPreferredSize());
        vImagePanel.setLocation(0, 0);
        vImagePanel.setLayout(new BorderLayout());
        vImagePanel.add(this.aImage, BorderLayout.CENTER);

        // this is timer label
        aGameTimer = new JLabel();
        aGameTimer.setForeground(Color.white);
        aGameTimer.setFont(aTextFont);
        aGameTimer.setSize(300, 50);
        aGameTimer.setLocation(15, 0);

        JPanel vTextPanel = new JPanel();
        vTextPanel.setLayout(new BorderLayout());
        vTextPanel.add(vListScroller, BorderLayout.CENTER);
        vTextPanel.add(this.aEntryField, BorderLayout.SOUTH);
        vTextPanel.setSize(vTextPanel.getPreferredSize());
        vTextPanel.setLocation(660, 0);

        JPanel vMovementButtonPanel = new JPanel();
        JPanel vCenterButtonPanel = new JPanel();

        vCenterButtonPanel.setLayout(new GridLayout(1, 2));
        vCenterButtonPanel.setBackground(Color.DARK_GRAY);
        vCenterButtonPanel.add(this.aUpButton);
        vCenterButtonPanel.add(this.aDownButton);

        vMovementButtonPanel.setPreferredSize(new Dimension(365, 87));
        vMovementButtonPanel.setLayout(new BorderLayout());
        vMovementButtonPanel.setBackground(Color.DARK_GRAY);
        vMovementButtonPanel.add(this.aNorthButton, BorderLayout.NORTH);
        vMovementButtonPanel.add(this.aWestButton, BorderLayout.WEST);
        vMovementButtonPanel.add(this.aSouthButton, BorderLayout.SOUTH);
        vMovementButtonPanel.add(this.aEastButton, BorderLayout.EAST);
        vMovementButtonPanel.add(vCenterButtonPanel, BorderLayout.CENTER);
        vMovementButtonPanel.setSize(vMovementButtonPanel.getPreferredSize());
        vMovementButtonPanel.setLocation(0, 650);

        JPanel vActionButtonPanel = new JPanel();
        vActionButtonPanel.setPreferredSize(new Dimension(294, 87));
        vActionButtonPanel.setLayout(new GridLayout(3, 3));
        vActionButtonPanel.setBackground(Color.DARK_GRAY);
        vActionButtonPanel.add(this.aBackButton);
        vActionButtonPanel.add(this.aHelpButton);
        vActionButtonPanel.add(this.aQuitButton);
        vActionButtonPanel.add(this.aDropButton);
        vActionButtonPanel.add(this.aTakeButton);
        vActionButtonPanel.add(this.aFireButton);
        vActionButtonPanel.add(this.aChargeButton);
        vActionButtonPanel.add(this.aInventoryButton);
        vActionButtonPanel.add(this.aSkipButton);
        vActionButtonPanel.setSize(vActionButtonPanel.getPreferredSize());
        vActionButtonPanel.setLocation(365, 650);

        this.aBattlerPanel = new JPanel();
        this.aBattlerPanel.setSize(new Dimension(650, 650));
        this.aBattlerPanel.setLocation(0, 0);
        
        aBattleGroundImage = new JLabel();
        aBattleGroundImage.setSize(650, 650);
        aBattleGroundImage.setLocation(0, 0);
        // use JProgressBar for health bar
        aEnemyHP = new JLabel();
        aEnemyHP.setForeground(Color.white);
        aEnemyHP.setBackground(Color.darkGray);
        aEnemyHP.setFont(aTextFont);
        aEnemyHP.setSize(300, 50);
        aEnemyHP.setLocation(315, 0);

        aPlayerHP = new JLabel();
        aPlayerHP.setForeground(Color.white);
        aPlayerHP.setBackground(Color.darkGray);
        aPlayerHP.setFont(aTextFont);
        aPlayerHP.setSize(300, 50);
        aPlayerHP.setLocation(15, 400);

        aEntityFullImage = new JLabel();
        aEntityFullImage.setSize(230, 340);
        aEntityFullImage.setLocation(315, 50);

        this.aBattlerPanel.add(aBattleGroundImage);
        this.aBattlerPanel.add(aEnemyHP);
        this.aBattlerPanel.add(aPlayerHP);
        this.aBattlerPanel.add(aEntityFullImage);

        this.aLayeredPane = new JLayeredPane();
        this.aLayeredPane.add(vImagePanel, JLayeredPane.DEFAULT_LAYER);
        this.aLayeredPane.add(vTextPanel, JLayeredPane.DEFAULT_LAYER);
        this.aLayeredPane.add(vMovementButtonPanel, JLayeredPane.DEFAULT_LAYER);
        this.aLayeredPane.add(vActionButtonPanel, JLayeredPane.DEFAULT_LAYER);
        this.aLayeredPane.add(this.aBattlerPanel, JLayeredPane.PALETTE_LAYER);
        this.aLayeredPane.add(aGameTimer, JLayeredPane.MODAL_LAYER);
        this.aLayeredPane.add(this.aEntityPanel, JLayeredPane.MODAL_LAYER);
        
    }

    // action listeners methods

    /**
     * This method is called when you click on the drop button
     * It's to change UI to show items in inventory
     */
    public void dropButtonMethod() {
        JButton[] vButtons = { aBackButton, aHelpButton, aQuitButton, aDropButton, aTakeButton, aFireButton,
                aChargeButton, aInventoryButton };
        if (aEngine.getCurrentInventoryItemsString() != null) {
            String[] vOutput = aEngine.getCurrentInventoryItemsString().split(" ");
            for (int i = 0; i < vOutput.length; i++) {
                vButtons[i].setText(vOutput[i]);
                vButtons[i].setActionCommand("drop " + vOutput[i]);
            }
            for (int i = vOutput.length; i < 8; i++) {
                vButtons[i].setText("");
                vButtons[i].setActionCommand("");
                vButtons[i].removeActionListener(this);
            }
            aSkipButton.setText("exit");
            aSkipButton.setActionCommand("exit");
            aSkipButton.addActionListener(e -> exitButtonMethod());
            for (int i = vOutput.length; i < 8; i++) {
                vButtons[i].addActionListener(this);
            }
            this.println("What do you want to drop ?");
        } else {
            this.println("Your inventory is empty");
        }
    }

    /**
     * This method is called when you click on the take button
     * It's to change UI to show items in the room
     */
    public void takeButtonMethod() {
        JButton[] vButtons = { aBackButton, aHelpButton, aQuitButton, aDropButton, aTakeButton, aFireButton,
                aChargeButton, aInventoryButton };

        if (aEngine.getCurrentRoomItemsString() != null) {
            String[] vOutput = aEngine.getCurrentRoomItemsString().split(" ");
            for (int i = 0; i < vOutput.length; i++) {
                vButtons[i].setText(vOutput[i]);
                vButtons[i].setActionCommand("take " + vOutput[i]);
            }
            for (int i = vOutput.length; i < 8; i++) {
                vButtons[i].setText("");
                vButtons[i].setActionCommand("");
                vButtons[i].removeActionListener(this);
            }
            aSkipButton.setText("exit");
            aSkipButton.setActionCommand("exit");
            aSkipButton.addActionListener(e -> exitButtonMethod());
            for (int i = vOutput.length; i < 8; i++) {
                vButtons[i].addActionListener(this);
            }
            this.println("What do you want to take ?");
        } else {
            this.println("There is nothing to take here");
        }
    }

    public void battleButtonMethod() {
        JButton[] vButtons = { aBackButton, aHelpButton, aQuitButton, aDropButton, aTakeButton, aFireButton,
                aChargeButton, aInventoryButton };
        String[] vOutput = aEngine.getMovesString().split(" ");
        for (int i = 0; i < vOutput.length; i++) {
            vButtons[i].setText(vOutput[i]);
            vButtons[i].setActionCommand("");
            setSelectedMove(i + 1);
        }
        for (int i = vOutput.length; i < 8; i++) {
            vButtons[i].setText("");
            vButtons[i].setActionCommand("");
            vButtons[i].removeActionListener(this);
        }
        aSkipButton.setText("exit");
        aSkipButton.setActionCommand("exit");
        aSkipButton.addActionListener(e -> exitButtonMethod());
        for (int i = vOutput.length; i < 8; i++) {
            vButtons[i].addActionListener(this);
        }

    }

    /**
     * This method is called when the exit button is clicked
     * It's back to "main" menu of the UI
     */
    public void exitButtonMethod() {
        aQuitButton.setText("quit");
        aBackButton.setText("back");
        aHelpButton.setText("help ?");
        aDropButton.setText("drop");
        aTakeButton.setText("take");
        aFireButton.setText("fire");
        aChargeButton.setText("charge");
        aInventoryButton.setText("bag");
        aSkipButton.setText("skip");

        aQuitButton.setActionCommand("quit");
        aBackButton.setActionCommand("back");
        aHelpButton.setActionCommand("help");
        aDropButton.setActionCommand("drop");
        aTakeButton.setActionCommand("take");
        aFireButton.setActionCommand("fire");
        aChargeButton.setActionCommand("charge");
        aInventoryButton.setActionCommand("inventory");
        aSkipButton.setActionCommand("skip");
    }

    private void setSelectedMove(int pSelectedMove) {
        this.aSelectedMove = pSelectedMove;
    }

    public int getSelectedMove() {
        return this.aSelectedMove;
    }

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

    public String getEntryField() {
        return this.aEntryField.getText();
    }

    // timer methods

    /**
     * This method start the timer
     * 
     * @author https://www.developpez.net/forums/anocode.php?id=f5b514a1cac1a7a4fd52f16767094ab1
     */
    public void startTimer() {
        aSecond = 0;
        aMinute = 0;
        aTaskTimer = new ActionListener() {
            public void actionPerformed(ActionEvent pE) {

                aSecond++;
                if (aSecond == 60) {
                    aSecond = 0;
                    aMinute++;
                }
                aGameTimer.setText("elapsed time : " + aMinute + " : " + aSecond);
            }
        };

        aTimer = new Timer(aDelay, aTaskTimer);
        aTimer.start();
    }

    /**
     * this method stop the timer
     */
    public void stopTimer() {
        aTimer.stop();
    }

    /**
     * this function get the minute of the timer
     * 
     * @return a minute of the timer
     */
    public int getMinute() {
        return aMinute;
    }

    /**
     * this function get the second of the timer
     * 
     * @return a second of the timer
     */
    public int getSecond() {
        return aSecond;
    }

    /**
     * this boolean check if timer is end
     * 
     * @return true if timer is end
     *         false if isnt
     */
    public boolean isTimerEnd() {
        if (getMinute() < aEndTime) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @author https://riptutorial.com/swing/example/498/delay-a-ui-task-for-a-specific-period
     */
    public void showCharacterPanel() {
        int vDelay = 1000;// specify the delay for the timer
        Timer vTimer = new Timer(vDelay, e -> {
            // The following code will be executed once the delay is reached
            this.aEntityPanel.setVisible(true);
        });
        vTimer.setRepeats(false);// make sure the timer only runs once
        vTimer.start();
    }

    public void hideCharacterPanel() {
        this.aEntityPanel.setVisible(false);
    }

} // UserInterface
