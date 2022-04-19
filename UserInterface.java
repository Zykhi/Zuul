import java.net.URL;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
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
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JDialog;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling
 * @version 1.0 (Jan 2003) DB edited (2019) CD edited (2022)
 */
public class UserInterface implements ActionListener {
    private GameEngine aEngine;
    private JFrame aGameWindow;
    private CardLayout aCardLayout;
    private JTextField aEntryField;
    private JTextArea aLog;
    private JTextArea aEntityLog;
    private JTextArea aBattleLog;
    private JLabel aImage;
    private JLabel aEntityImage;
    private JLabel aEntityFullImage;
    private JLabel aPlayerFullImage;
    private JLabel aMainMenuBackGroundImage;
    private JLabel aBattleBackground;
    private JLabel aEnemyName;
    private JLabel aPlayerName;
    private JLabel aSettingBackground;
    private JLabel aSoundBackground;
    private JLabel aGameOverBackGroundImage;
    private JLabel aGameTimer;
    private JProgressBar aEnemyHP;
    private JProgressBar aPlayerHP;
    private JPanel aEntityPanel;
    private JPanel aEnemyHPPanel;
    private JPanel aPlayerHPPanel;
    private JLayeredPane aGamePanel;
    private JLayeredPane aBattlerPanel;
    private JLayeredPane aMainMenuPanel;
    private JLayeredPane aSoundPanel;
    private JLayeredPane aSettingPanel;
    private JLayeredPane aGameOverPanel;
    private Container aSceneManager;
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
    private JButton aPlay;
    private JButton aSetting;
    private JButton aQuit;
    private JButton aQuit2;
    private JButton aBack;
    private JButton aBack2;
    private JButton aSound;
    private JButton aSoundOn;
    private JButton aSoundOff;
    private JButton aRunButton;
    private JButton aBagButton;
    private JButton aAttackButton;
    private JButton aDefendButton;
    private Parser aParser;
    private Clip aClip;
    private Clip aDialogClip;
    private Clip aSoundEffectClip;
    private Timer aTimer;
    private Timer aTextTimer;
    private ActionListener aTaskTimer;
    private Font aFont;
    private Font aButtonsFont;
    private Font aTextFont;
    private Font aBattleFont;
    private Font aMenuFont;
    private int aTime = 60;
    private int aMinute;
    private int aSecond;
    private int aDelay = 1000;
    private int aEndTime = 20;
    private int aIndex;

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

        aTextTimer = new Timer(aTime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent pEvent) {
                aLog.setText(pText.substring(0, aIndex));
                aIndex++;
                if (aIndex > pText.length()) {
                    ((Timer) pEvent.getSource()).stop();
                }
            }

        });

        aTextTimer.start();
        aIndex = 0;
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

    /**
     * Print out some text into the text area.
     * 
     * @param pText like sysout but for gui, is the text in " "
     */
    public void printBattle(final String pText) {
        this.aBattleLog.append(pText);
        this.aBattleLog.setCaretPosition(this.aBattleLog.getDocument().getLength());
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     * 
     * @param pText like sysout but for gui, is the text in " "
     */
    public void printlnBattle(final String pText) {
        this.printBattle(pText + "\n");
    } // println(.)

    public void clearDialogArea() {
        this.aEntityLog.setText("");
    }

    public void clearBattleArea() {
        this.aBattleLog.setText("");
    }

    /**
     * This method is called when skip button is clicked
     * It's write quikly the text of the slowPrint method
     */
    public void skipMethod() {
        aTime = 1;
        aTextTimer.stop();
        aTextTimer.setDelay(aTime);
        aTextTimer.start();
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
     * This method play the sound of the battle
     */
    public void playBattleSound(final String pFile, final int pLoop) {
        try {
            AudioInputStream vAudioInputStream = AudioSystem
                    .getAudioInputStream(new File("gameSounds/battle" + pFile + ".wav").getAbsoluteFile());
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
     * 
     * @param pFile Audio file of the dialog
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
     * This method play sound effect
     * 
     * @param pFile Audio file of the sound effect
     */
    public void playSoundEffect(final String pFile) {
        try {
            AudioInputStream vAudioInputStream = AudioSystem
                    .getAudioInputStream(new File("gameSounds/" + pFile + ".wav").getAbsoluteFile());
            aSoundEffectClip = AudioSystem.getClip();
            aSoundEffectClip.open(vAudioInputStream);
            aSoundEffectClip.start();
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
     * This method stop the sound of the narrator
     */
    public void stopSoundEffect() {
        aSoundEffectClip.stop();
    }

    /**
     * This method play sound of the room
     */
    public void playRoomSound() {
        this.aEngine.playRoomSound();
    }

    /**
     * This method play the sound of the battle
     */
    public void playBattleRoomSound() {
        this.aEngine.playBattleRoomSound();
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
            this.aGameWindow.pack();
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
            this.aGameWindow.pack();
        }
    }

    /**
     * Show an image file in the interface.
     * 
     * @param pImageName name of the image for the room
     */
    public void showFullEntityImage(final String pImageName) {
        String vImagePath = "" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource(vImagePath);
        if (vImageURL == null)
            System.out.println("Image not found : " + vImagePath);
        else {
            ImageIcon vIcon = new ImageIcon(vImageURL);
            this.aEntityFullImage.setIcon(vIcon);
            this.aGameWindow.pack();
        }
    }

    /**
     * Show an image file in the interface.
     */
    public void showFullPlayerImage() {
        String vImagePath = "faceImages/player.gif"; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource(vImagePath);
        if (vImageURL == null)
            System.out.println("Image not found : " + vImagePath);
        else {
            ImageIcon vIcon = new ImageIcon(vImageURL);
            this.aPlayerFullImage.setIcon(vIcon);
            this.aGameWindow.pack();
        }
    }

    /**
     * Show background image of the main menu.
     */
    public void showBackGroundImage() {
        String vImagePath = "gameImages/MainMenu.jpg"; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource(vImagePath);
        if (vImageURL == null)
            System.out.println("Image not found : " + vImagePath);
        else {
            ImageIcon vIcon = new ImageIcon(vImageURL);
            this.aMainMenuBackGroundImage.setIcon(vIcon);
            this.aGameWindow.pack();
        }
    }

    /**
     * Show background image of the battle
     */
    public void showBattleBackground() {
        String vImagePath = "gameImages/battleground.gif"; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource(vImagePath);
        if (vImageURL == null)
            System.out.println("Image not found : " + vImagePath);
        else {
            ImageIcon vIcon = new ImageIcon(vImageURL);
            this.aBattleBackground.setIcon(vIcon);
            this.aGameWindow.pack();
        }
    }

    /**
     * Show the background of setting menu
     */
    private void showSettingBackground() {
        String vImagePath = "gameImages/setting.jpg"; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource(vImagePath);
        if (vImageURL == null)
            System.out.println("Image not found : " + vImagePath);
        else {
            ImageIcon vIcon = new ImageIcon(vImageURL);
            this.aSettingBackground.setIcon(vIcon);
            this.aGameWindow.pack();
        }
    }

    /**
     * Show the background of the sound menu
     */
    private void showSoundBackground() {
        String vImagePath = "gameImages/sound.jpg"; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource(vImagePath);
        if (vImageURL == null)
            System.out.println("Image not found : " + vImagePath);
        else {
            ImageIcon vIcon = new ImageIcon(vImageURL);
            this.aSoundBackground.setIcon(vIcon);
            this.aGameWindow.pack();
        }
    }

    /**
     * Show the background of the game over menu
     */
    public void showGameOverImage() {
        String vImagePath = "gameImages/GameOver.jpg"; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource(vImagePath);
        if (vImageURL == null)
            System.out.println("Image not found : " + vImagePath);
        else {
            ImageIcon vIcon = new ImageIcon(vImageURL);
            this.aGameOverBackGroundImage.setIcon(vIcon);
            this.aGameWindow.pack();
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

            JButton[] vButtons = { aBackButton, aHelpButton, aQuitButton, aDropButton, aTakeButton, aFireButton,
                    aChargeButton, aInventoryButton };

            for (JButton currentButton : vButtons) {
                for (ActionListener al : currentButton.getActionListeners()) {
                    currentButton.removeActionListener(al);
                }
            }
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
            aBattleFont = aFont.deriveFont(24f);
            aMenuFont = aFont.deriveFont(25f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    // gui method

    /**
     * Set up graphical user interface.
     */
    private void createGUI() {
        this.aGameWindow = new JFrame();
        this.aGameWindow.setTitle("Zuul GOTY Edition");
        this.aGameWindow.setResizable(false);
        this.aGameWindow.setPreferredSize(new Dimension(1077, 765));

        ImageIcon aGameIcon = new ImageIcon("gameImages/game.png");

        this.aGameWindow.setIconImage(aGameIcon.getImage());

        this.createButton();
        this.createPanel();

        this.aGameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.aGameWindow.pack();
        this.aGameWindow.setVisible(true);
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
        this.aInventoryButton = new JButton("inventory");
        this.aSkipButton = new JButton("skip");
        this.aPlay = new JButton("Play");
        this.aSetting = new JButton("Settings");
        this.aQuit = new JButton("Quit");
        this.aQuit2 = new JButton("Quit");
        this.aBack = new JButton("Back");
        this.aBack2 = new JButton("Back");
        this.aSound = new JButton("Sound");
        this.aSoundOn = new JButton("Sound On");
        this.aSoundOff = new JButton("Sound Off");
        this.aRunButton = new JButton("Run");
        this.aBagButton = new JButton("Bag");
        this.aAttackButton = new JButton("Attack");
        this.aDefendButton = new JButton("Defend");

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
        this.aPlay.setFont(aMenuFont);
        this.aSetting.setFont(aMenuFont);
        this.aQuit.setFont(aMenuFont);
        this.aQuit2.setFont(aMenuFont);
        this.aBack.setFont(aMenuFont);
        this.aBack2.setFont(aMenuFont);
        this.aSound.setFont(aMenuFont);
        this.aSoundOn.setFont(aMenuFont);
        this.aSoundOff.setFont(aMenuFont);
        this.aRunButton.setFont(aMenuFont);
        this.aBagButton.setFont(aMenuFont);
        this.aAttackButton.setFont(aMenuFont);
        this.aDefendButton.setFont(aMenuFont);

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
        this.aInventoryButton.addActionListener(this);
        this.aSkipButton.addActionListener(e -> skipMethod());
        this.aPlay.addActionListener(e -> playButton());
        this.aPlay.addActionListener(this);
        this.aSetting.addActionListener(e -> settingsButton());
        this.aQuit.addActionListener(e -> quitButton());
        this.aQuit2.addActionListener(e -> quitButton());
        this.aBack.addActionListener(e -> backButton());
        this.aBack2.addActionListener(e -> backButton2());
        this.aSound.addActionListener(e -> soundButton());
        this.aSoundOn.addActionListener(e -> soundOnButton());
        this.aSoundOff.addActionListener(e -> soundOffButton());
        this.aBagButton.addActionListener(this);
        this.aAttackButton.addActionListener(e -> attackButton());
        this.aDefendButton.addActionListener(e -> this.aEngine.defendButton());
        this.aBagButton.addActionListener(e -> bagButtonMethod());
        this.aRunButton.addActionListener(e -> runButtonMethod());

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
        this.aPlay.setActionCommand("go play");
    }

    /**
     * This method create all the panels
     */
    private void createPanel() {
        createNPCPanel();
        createGamePanel();
        createBattlePanel();
        createMainMenuPanel();
        createSettingPanel();
        createSoundPanel();
        createGameOverPanel();

        aSceneManager = aGameWindow.getContentPane();
        aCardLayout = new CardLayout();
        aSceneManager.setLayout(aCardLayout);
        aSceneManager.add(aMainMenuPanel, "MainMenu");
        aSceneManager.add(aGamePanel, "Game");
        aSceneManager.add(aBattlerPanel, "Battle");
        aSceneManager.add(aSettingPanel, "Settings");
        aSceneManager.add(aSoundPanel, "Sound");
        aSceneManager.add(aGameOverPanel, "GameOver");
    }

    /**
     * This method create the panel of the game
     */
    private void createGamePanel() {
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

        this.aGamePanel = new JLayeredPane();
        this.aGamePanel.add(vImagePanel, JLayeredPane.DEFAULT_LAYER);
        this.aGamePanel.add(vTextPanel, JLayeredPane.DEFAULT_LAYER);
        this.aGamePanel.add(vMovementButtonPanel, JLayeredPane.DEFAULT_LAYER);
        this.aGamePanel.add(vActionButtonPanel, JLayeredPane.DEFAULT_LAYER);
        this.aGamePanel.add(aGameTimer, JLayeredPane.PALETTE_LAYER);
        this.aGamePanel.add(this.aEntityPanel, JLayeredPane.PALETTE_LAYER);
    }

    /**
     * This method create the panel of the battle
     */
    private void createBattlePanel() {

        this.aBattlerPanel = new JLayeredPane();
        this.aBattlerPanel.setPreferredSize(new Dimension(1077, 765));
        this.aBattlerPanel.setSize(this.aBattlerPanel.getPreferredSize());
        this.aBattlerPanel.setLocation(0, 0);

        aBattleBackground = new JLabel();
        aBattleBackground.setSize(aBattlerPanel.getPreferredSize());
        aBattleBackground.setLocation(0, 0);
        showBattleBackground();

        int enemyX = 400;
        int enemyY = 55;

        aEnemyHPPanel = new JPanel();
        aEnemyHPPanel.setPreferredSize(new Dimension(200, 70));
        aEnemyHPPanel.setSize(aEnemyHPPanel.getPreferredSize());
        aEnemyHPPanel.setLocation(enemyX, enemyY);
        aEnemyHPPanel.setBorder(new HealthPanelBorder());
        aEnemyHPPanel.setOpaque(false);
        aEnemyName = new JLabel();
        aEnemyName.setForeground(Color.white);
        aEnemyName.setFont(aTextFont);
        aEnemyName.setSize(300, 50);
        aEnemyName.setLocation(enemyX + 10, enemyY);

        startBattleUI();
        aEnemyHP.setBackground(Color.WHITE);
        aEnemyHP.setForeground(Color.GREEN);

        aEnemyHP.setPreferredSize(new Dimension(180, 10));
        aEnemyHP.setSize(aEnemyHP.getPreferredSize());
        aEnemyHP.setLocation(enemyX + 10, enemyY + 45);

        int playerX = 38;
        int playerY = 490;

        aPlayerHPPanel = new JPanel();
        aPlayerHPPanel.setPreferredSize(new Dimension(200, 70));
        aPlayerHPPanel.setSize(aPlayerHPPanel.getPreferredSize());
        aPlayerHPPanel.setLocation(playerX, playerY);
        aPlayerHPPanel.setBorder(new HealthPanelBorder());
        aPlayerHPPanel.setOpaque(false);
        aPlayerName = new JLabel();
        aPlayerName.setForeground(Color.white);
        aPlayerName.setFont(aTextFont);
        aPlayerName.setSize(300, 50);
        aPlayerName.setLocation(playerX + 10, playerY);
        aPlayerName.setText(aEngine.getPlayerName());
        aPlayerHP = new JProgressBar(0, this.aEngine.getMaxPlayerHP());
        aPlayerHP.setBackground(Color.white);
        aPlayerHP.setForeground(Color.green);
        aPlayerHP.setPreferredSize(new Dimension(180, 10));
        aPlayerHP.setSize(aEnemyHP.getPreferredSize());
        aPlayerHP.setLocation(playerX + 10, playerY + 45);
        aPlayerHP.setValue(this.aEngine.getPlayerHP());

        JPanel vBattleTextPanel = new JPanel();
        this.aBattleLog = new RoundJTextArea();
        vBattleTextPanel.setPreferredSize(new Dimension(600, 150));
        vBattleTextPanel.setOpaque(false);
        vBattleTextPanel.setSize(vBattleTextPanel.getPreferredSize());
        vBattleTextPanel.setLocation(38, 565);
        vBattleTextPanel.setLayout(new BorderLayout());
        this.aBattleLog.setEditable(false);
        this.aBattleLog.setLineWrap(true);
        this.aBattleLog.setWrapStyleWord(true);
        this.aBattleLog.setMargin(new Insets(10, 10, 10, 10));
        this.aBattleLog.setFont(aBattleFont);
        this.aBattleLog.setForeground(Color.white);
        this.aBattleLog.setBackground(Color.darkGray);

        vBattleTextPanel.add(this.aBattleLog, BorderLayout.CENTER);

        JPanel vBattleButtonPanel = new JPanel();
        vBattleButtonPanel.setPreferredSize(new Dimension(400, 150));
        vBattleButtonPanel.setOpaque(false);
        vBattleButtonPanel.setSize(vBattleButtonPanel.getPreferredSize());
        vBattleButtonPanel.setLocation(650, 565);
        vBattleButtonPanel.setLayout(new GridLayout(2, 2));
        vBattleButtonPanel.add(aAttackButton);
        vBattleButtonPanel.add(aBagButton);
        vBattleButtonPanel.add(aDefendButton);
        vBattleButtonPanel.add(aRunButton);

        aEntityFullImage = new JLabel();
        aEntityFullImage.setPreferredSize(new Dimension(455, 490));
        aEntityFullImage.setSize(aEntityFullImage.getPreferredSize());
        aEntityFullImage.setLocation(560, 0);

        aPlayerFullImage = new JLabel();
        aPlayerFullImage.setPreferredSize(new Dimension(320, 480));
        aPlayerFullImage.setSize(aPlayerFullImage.getPreferredSize());
        aPlayerFullImage.setLocation(100, 200);

        this.aBattlerPanel.add(aBattleBackground, JLayeredPane.DEFAULT_LAYER);
        this.aBattlerPanel.add(aEntityFullImage, JLayeredPane.PALETTE_LAYER);
        this.aBattlerPanel.add(aPlayerFullImage, JLayeredPane.PALETTE_LAYER);
        this.aBattlerPanel.add(vBattleTextPanel, JLayeredPane.MODAL_LAYER);
        this.aBattlerPanel.add(vBattleButtonPanel, JLayeredPane.PALETTE_LAYER);
        this.aBattlerPanel.add(aEnemyHPPanel, JLayeredPane.MODAL_LAYER);
        this.aBattlerPanel.add(aEnemyName, JLayeredPane.POPUP_LAYER);
        this.aBattlerPanel.add(aEnemyHP, JLayeredPane.POPUP_LAYER);
        this.aBattlerPanel.add(aPlayerHPPanel, JLayeredPane.MODAL_LAYER);
        this.aBattlerPanel.add(aPlayerName, JLayeredPane.POPUP_LAYER);
        this.aBattlerPanel.add(aPlayerHP, JLayeredPane.POPUP_LAYER);

    }

    /**
     * This method create the panel of the main menu
     */
    private void createMainMenuPanel() {
        this.aMainMenuPanel = new JLayeredPane();
        this.aMainMenuBackGroundImage = new JLabel();
        this.aMainMenuPanel.setPreferredSize(new Dimension(1077, 765));
        this.aMainMenuPanel.setSize(aMainMenuPanel.getPreferredSize());
        this.aMainMenuBackGroundImage.setSize(aMainMenuPanel.getPreferredSize());
        this.aMainMenuBackGroundImage.setLocation(0, 0);
        showBackGroundImage();

        JPanel vButtonsPanel = new JPanel();
        vButtonsPanel.setOpaque(false);
        vButtonsPanel.setPreferredSize(new Dimension(201, 150));
        vButtonsPanel.setSize(vButtonsPanel.getPreferredSize());
        vButtonsPanel.setLayout(new GridLayout(3, 1));
        vButtonsPanel.add(aPlay);
        vButtonsPanel.add(aSetting);
        vButtonsPanel.add(aQuit);
        vButtonsPanel.setLocation(438, 400);

        this.aMainMenuPanel.add(aMainMenuBackGroundImage, JLayeredPane.DEFAULT_LAYER);
        this.aMainMenuPanel.add(vButtonsPanel, JLayeredPane.PALETTE_LAYER);
    }

    /**
     * This method create the panel of the setting menu
     */
    private void createSettingPanel() {
        this.aSettingPanel = new JLayeredPane();
        this.aSettingBackground = new JLabel();
        this.aSettingPanel.setPreferredSize(new Dimension(1077, 765));
        this.aSettingPanel.setSize(aSettingPanel.getPreferredSize());
        this.aSettingBackground.setSize(aSettingPanel.getPreferredSize());
        this.aSettingBackground.setLocation(0, 0);
        showSettingBackground();

        JPanel vButtonsPanel = new JPanel();
        vButtonsPanel.setOpaque(false);
        vButtonsPanel.setPreferredSize(new Dimension(201, 150));
        vButtonsPanel.setSize(vButtonsPanel.getPreferredSize());
        vButtonsPanel.setLayout(new GridLayout(3, 1));
        vButtonsPanel.add(aSound);
        vButtonsPanel.add(aBack);
        vButtonsPanel.setLocation(438, 400);

        this.aSettingPanel.add(aSettingBackground, JLayeredPane.DEFAULT_LAYER);
        this.aSettingPanel.add(vButtonsPanel, JLayeredPane.PALETTE_LAYER);
    }

    /**
     * This method create the panel of the sound menu
     */
    private void createSoundPanel() {
        this.aSoundPanel = new JLayeredPane();
        this.aSoundBackground = new JLabel();
        this.aSoundPanel.setPreferredSize(new Dimension(1077, 765));
        this.aSoundPanel.setSize(aSoundPanel.getPreferredSize());
        this.aSoundBackground.setSize(aSoundPanel.getPreferredSize());
        this.aSoundBackground.setLocation(0, 0);
        showSoundBackground();

        JPanel vButtonsPanel = new JPanel();
        vButtonsPanel.setOpaque(false);
        vButtonsPanel.setPreferredSize(new Dimension(201, 150));
        vButtonsPanel.setSize(vButtonsPanel.getPreferredSize());
        vButtonsPanel.setLayout(new GridLayout(3, 1));
        vButtonsPanel.add(aSoundOn);
        vButtonsPanel.add(aSoundOff);
        vButtonsPanel.add(aBack2);
        vButtonsPanel.setLocation(438, 400);

        this.aSoundPanel.add(aSoundBackground, JLayeredPane.DEFAULT_LAYER);
        this.aSoundPanel.add(vButtonsPanel, JLayeredPane.PALETTE_LAYER);
    }

    /**
     * This method create the panel of the npc
     */
    private void createNPCPanel() {
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
    }

    /**
     * This method create the panel of game over
     */
    private void createGameOverPanel() {
        this.aGameOverPanel = new JLayeredPane();
        this.aGameOverBackGroundImage = new JLabel();
        this.aGameOverPanel.setPreferredSize(new Dimension(1077, 765));
        this.aGameOverPanel.setSize(aGameOverPanel.getPreferredSize());
        this.aGameOverBackGroundImage.setSize(aGameOverPanel.getPreferredSize());
        this.aGameOverBackGroundImage.setLocation(0, 0);
        showGameOverImage();

        JPanel vButtonsPanel = new JPanel();
        vButtonsPanel.setOpaque(false);
        vButtonsPanel.setPreferredSize(new Dimension(201, 150));
        vButtonsPanel.setSize(vButtonsPanel.getPreferredSize());
        vButtonsPanel.setLayout(new GridLayout(1, 1));
        vButtonsPanel.add(aQuit2);
        vButtonsPanel.setLocation(438, 400);

        this.aGameOverPanel.add(aGameOverBackGroundImage, JLayeredPane.DEFAULT_LAYER);
        this.aGameOverPanel.add(vButtonsPanel, JLayeredPane.PALETTE_LAYER);
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

    /**
     * This method is called when the player click on play button in main menu
     */
    private void playButton() {
        this.aCardLayout.show(aSceneManager, "Game");
        this.aLog.setText("");
    }

    /**
     * This method is called when the player click on settings button in main menu
     */
    private void settingsButton() {
        this.aCardLayout.show(aSceneManager, "Settings");
    }

    /**
     * This method is called when the player click on quit button in main menu
     */
    private void quitButton() {
        System.exit(0);
    }

    /**
     * This method is called when the player click on back button in setting menu
     */
    private void backButton() {
        this.aCardLayout.show(aSceneManager, "MainMenu");
    }

    /**
     * This method is called when the player click on back button in sound menu
     */
    private void backButton2() {
        this.aCardLayout.show(aSceneManager, "Settings");
    }

    /**
     * This method is called when the player click on sound button in settings menu
     */
    private void soundButton() {
        this.aCardLayout.show(aSceneManager, "Sound");
    }

    /**
     * This method is called when the player click on attack button in battle menu
     */
    public void attackButton() {
        JButton[] vButtons = { aAttackButton, aDefendButton, aBagButton, aRunButton };

        String[] vOutput = aEngine.getMovesString().split(" ");
        for (int i = 0; i < 3; i++) {
            vButtons[i].setText(vOutput[i]);
        }
        removeAL(vButtons);
        aAttackButton.addActionListener(e -> this.aEngine.attack1Button());
        aDefendButton.addActionListener(e -> this.aEngine.attack2Button());
        aBagButton.addActionListener(e -> this.aEngine.attack3Button());
        aRunButton.setText("Back");
        aRunButton.addActionListener(e -> exitBattleButton());

    }

    public void bagButtonMethod() {
        JButton[] vButtons = { aAttackButton, aDefendButton, aBagButton, aRunButton };
        if (aEngine.getCurrentInventoryFightableItemsString() != null) {
            String[] vOutput = aEngine.getCurrentInventoryFightableItemsString().split(" ");
            removeAL(vButtons);
            for (int i = 0; i < vOutput.length; i++) {
                vButtons[i].setText(vOutput[i]);
                vButtons[i].setActionCommand("use " + vOutput[i]);
                vButtons[i].addActionListener(this);
            }
            for (int i = vOutput.length; i < 3; i++) {
                vButtons[i].setText("");
                vButtons[i].setActionCommand("");
                vButtons[i].removeActionListener(this);
            }
            aRunButton.setText("Back");
            aRunButton.addActionListener(e -> exitBattleButton());
        } else {
            this.printlnBattle("Your inventory is empty");
        }
    }

    public void runButtonMethod() {
        hideBattlePanel();
    }

    /**
     * This method is called when the player click on exit button in battle menu
     */
    public void exitBattleButton() {
        JButton[] vButtons = { aAttackButton, aDefendButton, aBagButton, aRunButton };

        for (JButton currentButton : vButtons) {
            for (ActionListener al : currentButton.getActionListeners()) {
                currentButton.removeActionListener(al);
            }
        }
        aAttackButton.setText("Attack");
        aAttackButton.addActionListener(e -> attackButton());
        aDefendButton.setText("Defend");
        aDefendButton.addActionListener(e -> this.aEngine.defendButton());
        aBagButton.setText("Bag");
        aBagButton.addActionListener(e -> bagButtonMethod());
        aRunButton.setText("Run");
        aRunButton.addActionListener(e -> runButtonMethod());
    }

    /**
     * This method is update UI during the battle
     */
    public void updateBattleUI() {
        aEnemyHP.setValue(aEngine.getEnemyHP());
        aPlayerHP.setValue(aEngine.getPlayerHP());
    }

    public void startBattleUI(){
        aEnemyHP = new JProgressBar(0, aEngine.getEnemyHP());
        aEnemyName.setText(aEngine.getEnemyName());
        aEnemyHP.setValue(aEngine.getEnemyHP());
    }

    /**
     * This method is called when the player click on SoundOn button in main menu
     */
    // TODO : make sound on method works
    private void soundOnButton() {
        File vRepository = new File("gameSounds/");
        String vList[] = vRepository.list();

        if (vList != null) {
            // soundOn();
        } else {
            JDialog vDialog = new JDialog();
            JLabel vLabel = new JLabel("This version has no sound to satisfy the rendering requirements of Mr. Bureau");
            vLabel.setFont(aTextFont);
            vDialog.add(vLabel);
            vDialog.setSize(800, 100);
            vDialog.setLocation(100, 100);
            vDialog.setVisible(true);
        }
    }

    /**
     * This method is called when the player click on SoundOff button in main menu
     */
    // TODO: make sound off method works
    private void soundOffButton() {
        System.out.println("Sound Off");
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
        int vDelay = 100;// specify the delay for the timer
        Timer vTimer = new Timer(vDelay, e -> {
            // The following code will be executed once the delay is reached
            this.aEntityPanel.setVisible(true);
        });
        vTimer.setRepeats(false);// make sure the timer only runs once
        vTimer.start();
    }

    /**
     * This method hide the character panel
     */
    public void hideCharacterPanel() {
        this.aEntityPanel.setVisible(false);
    }

    /**
     * This method show the battle panel
     */
    public void showBattlePanel() {
        this.aCardLayout.show(aSceneManager, "Battle");
    }

    /**
     * This method hide the battle panel
     */
    public void hideBattlePanel() {
        this.aCardLayout.show(aSceneManager, "Game");
    }

    /**
     * This method show the game over panel
     */
    public void showGameOverPanel() {
        this.aCardLayout.show(aSceneManager, "GameOver");
    }

    private void removeAL(JButton[] pButtons) {
        for (JButton currentButton : pButtons) {
            for (ActionListener al : currentButton.getActionListeners()) {
                currentButton.removeActionListener(al);
            }
        }
    }

} // UserInterface
