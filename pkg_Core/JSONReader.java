package pkg_Core;

import java.io.File;
import java.io.FileNotFoundException;
// Java program to read JSON from a file

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONReader {

  private Object aJsonReader;
  private JSONObject aJsonObject;

  //Game Settings
  private String aLanguage;
  private String aGameName;
  private String aGameIcon;
  private long aGameWidth;
  private long aGameHeight;
  private long aArtefactNb;
  private String aQuitButton;
  private String aNorthButton;
  private String aSouthButton;
  private String aEastButton;
  private String aWestButton;
  private String aUpButton;
  private String aDownButton;
  private String aBackButton;
  private String aHelpButton;
  private String aDropButton;
  private String aTakeButton;
  private String aGiveButton;
  private String aChargeButton;
  private String aInventoryButton;
  private String aFightButton;
  private String aPlayButton;
  private String aDevModeButton;
  private String aSettingButton;
  private String aMajQuitButton;
  private String aMajBackButton;
  private String aSoundButton;
  private String aSoundOnButton;
  private String aSoundOffButton;
  private String aLanguageButton;
  private String aEnglishLanguageButton;
  private String aFrenchLanguageButton;
  private String aRunButton;
  private String aBagButton;
  private String aAttackButton;
  private String aDefendButton;
  private String aGameFont;
  private long aTextSizeFont;
  private long aButtonsSizeFont;
  private long aBattleSizeFont;
  private long aMenuSizeFont;
  private String aMainMenuFont;
  private long aTitleSizeFont;

  //Player
  private String aPlayerName;
  private long aMaxMovement;
  private long aMaxWeight;
  private long aPlayerHP;
  private long aPlayerMaxHP;
  private long aPlayerDef;
  private long aPlayerSpeDef;
  private long aPlayerAtt;
  private long aPlayerSpeAtt;
  private boolean aPlayerFightable;
  private String aPlayerMoves;
  private String aPlayerMove1;
  private String aPlayerMove2;
  private String aPlayerMove3;
  private String aPlayerAttackDamage;
  private String aPlayerAttackDamage1;
  private String aPlayerAttackDamage2;
  private String aPlayerAttackDamage3;
  private String aPlayerAttackAccuracy;
  private String aPlayerAttackAccuracy1;
  private String aPlayerAttackAccuracy2;
  private String aPlayerAttackAccuracy3;
  private String aPlayerAttackType;
  private String aPlayerAttackType1;
  private String aPlayerAttackType2;
  private String aPlayerAttackType3;

  //NPC1
  private String aNPC1Name;
  private String aNPC1Description;
  private String aNPC1Image;
  private String aNPC1FullImage;
  private String aNPC1Location;
  private Boolean aNPC1Fightable;
  private long aNPC1HP;
  private long aNPC1MaxHP;
  private long aNPC1Defense;
  private long aNPC1SpeDefense;
  private long aNPC1Attack;
  private long aNPC1SpeAttack;
  private String aNPC1Dialogue;
  private String aNPC1Moves;
  private String aNPC1Move1;
  private String aNPC1Move2;
  private String aNPC1Move3;
  private String aNPC1AttackDamage;
  private String aNPC1AttackDamage1;
  private String aNPC1AttackDamage2;
  private String aNPC1AttackDamage3;
  private String aNPC1AttackAccuracy;
  private String aNPC1AttackAccuracy1;
  private String aNPC1AttackAccuracy2;
  private String aNPC1AttackAccuracy3;
  private String aNPC1AttackType;
  private String aNPC1AttackType1;
  private String aNPC1AttackType2;
  private String aNPC1AttackType3;

  //NPC2
  private String aNPC2Name;
  private String aNPC2Description;
  private String aNPC2Image;
  private String aNPC2FullImage;
  private String aNPC2Location;
  private Boolean aNPC2Fightable;
  private long aNPC2HP;
  private long aNPC2MaxHP;
  private long aNPC2Defense;
  private long aNPC2SpeDefense;
  private long aNPC2Attack;
  private long aNPC2SpeAttack;
  private String aNPC2Dialogue;
  private String aNPC2Moves;
  private String aNPC2Move1;
  private String aNPC2Move2;
  private String aNPC2Move3;
  private String aNPC2AttackDamage;
  private String aNPC2AttackDamage1;
  private String aNPC2AttackDamage2;
  private String aNPC2AttackDamage3;
  private String aNPC2AttackAccuracy;
  private String aNPC2AttackAccuracy1;
  private String aNPC2AttackAccuracy2;
  private String aNPC2AttackAccuracy3;
  private String aNPC2AttackType;
  private String aNPC2AttackType1;
  private String aNPC2AttackType2;
  private String aNPC2AttackType3;

  //NPC3
  private String aNPC3Name;
  private String aNPC3Description;
  private String aNPC3Image;
  private String aNPC3FullImage;
  private String aNPC3Location;
  private Boolean aNPC3Fightable;
  private long aNPC3HP;
  private long aNPC3MaxHP;
  private long aNPC3Defense;
  private long aNPC3SpeDefense;
  private long aNPC3Attack;
  private long aNPC3SpeAttack;
  private String aNPC3Dialogue;
  private String aNPC3Moves;
  private String aNPC3Move1;
  private String aNPC3Move2;
  private String aNPC3Move3;
  private String aNPC3AttackDamage;
  private String aNPC3AttackDamage1;
  private String aNPC3AttackDamage2;
  private String aNPC3AttackDamage3;
  private String aNPC3AttackAccuracy;
  private String aNPC3AttackAccuracy1;
  private String aNPC3AttackAccuracy2;
  private String aNPC3AttackAccuracy3;
  private String aNPC3AttackType;
  private String aNPC3AttackType1;
  private String aNPC3AttackType2;
  private String aNPC3AttackType3;

  //NPC4
  private String aNPC4Name;
  private String aNPC4Description;
  private String aNPC4Image;
  private String aNPC4FullImage;
  private String aNPC4Location;
  private Boolean aNPC4Fightable;
  private long aNPC4HP;
  private long aNPC4MaxHP;
  private long aNPC4Defense;
  private long aNPC4SpeDefense;
  private long aNPC4Attack;
  private long aNPC4SpeAttack;
  private String aNPC4Dialogue;
  private String aNPC4Moves;
  private String aNPC4Move1;
  private String aNPC4Move2;
  private String aNPC4Move3;
  private String aNPC4AttackDamage;
  private String aNPC4AttackDamage1;
  private String aNPC4AttackDamage2;
  private String aNPC4AttackDamage3;
  private String aNPC4AttackAccuracy;
  private String aNPC4AttackAccuracy1;
  private String aNPC4AttackAccuracy2;
  private String aNPC4AttackAccuracy3;
  private String aNPC4AttackType;
  private String aNPC4AttackType1;
  private String aNPC4AttackType2;
  private String aNPC4AttackType3;

  //Text
  private String aWelcomeText;
  private String aEndWelcomeText;
  private String aLine1;
  private String aLine2;
  private String aLine3;
  private String aLine4;
  private String aLine5;
  private String aLine6;
  private String aLine7;

  //Room 1
  private String aRoom1Name;
  private String aRoom1Description;
  private String aRoom1Image;
  private String aRoom1NorthExit;
  private String aRoom1SouthExit;
  private String aRoom1EastExit;
  private String aRoom1WestExit;
  private String aRoom1UpExit;
  private String aRoom1DownExit;

  //Room 2
  private String aRoom2Name;
  private String aRoom2Description;
  private String aRoom2Image;
  private String aRoom2NorthExit;
  private String aRoom2SouthExit;
  private String aRoom2EastExit;
  private String aRoom2WestExit;
  private String aRoom2UpExit;
  private String aRoom2DownExit;

  //Room 3
  private String aRoom3Name;
  private String aRoom3Description;
  private String aRoom3Image;
  private String aRoom3NorthExit;
  private String aRoom3SouthExit;
  private String aRoom3EastExit;
  private String aRoom3WestExit;
  private String aRoom3UpExit;
  private String aRoom3DownExit;

  //Room 4
  private String aRoom4Name;
  private String aRoom4Description;
  private String aRoom4Image;
  private String aRoom4NorthExit;
  private String aRoom4SouthExit;
  private String aRoom4EastExit;
  private String aRoom4WestExit;
  private String aRoom4UpExit;
  private String aRoom4DownExit;

  //Room 5
  private String aRoom5Name;
  private String aRoom5Description;
  private String aRoom5Image;
  private String aRoom5NorthExit;
  private String aRoom5SouthExit;
  private String aRoom5EastExit;
  private String aRoom5WestExit;
  private String aRoom5UpExit;
  private String aRoom5DownExit;

  //Room 6
  private String aRoom6Name;
  private String aRoom6Description;
  private String aRoom6Image;
  private String aRoom6NorthExit;
  private String aRoom6SouthExit;
  private String aRoom6EastExit;
  private String aRoom6WestExit;
  private String aRoom6UpExit;
  private String aRoom6DownExit;

  //Room 7
  private String aRoom7Name;
  private String aRoom7Description;
  private String aRoom7Image;
  private String aRoom7NorthExit;
  private String aRoom7SouthExit;
  private String aRoom7EastExit;
  private String aRoom7WestExit;
  private String aRoom7UpExit;
  private String aRoom7DownExit;

  //Potion
  private String aPotionName;
  private long aPotionHealing;
  private String aPotionLocation;

  //Item 1
  private String aItem1Name;
  private String aItem1Description;
  private long aItem1Weight;
  private String aItem1Location;
  private String aItem1Image;
  private long aItem1Price;
  private boolean aItem1Fightable;

  //Item 2
  private String aItem2Name;
  private String aItem2Description;
  private long aItem2Weight;
  private String aItem2Location;
  private String aItem2Image;
  private long aItem2Price;
  private boolean aItem2Fightable;

  //Item 3
  private String aItem3Name;
  private String aItem3Description;
  private long aItem3Weight;
  private String aItem3Location;
  private String aItem3Image;
  private long aItem3Price;
  private boolean aItem3Fightable;

  //Item 4
  private String aItem4Name;
  private String aItem4Description;
  private long aItem4Weight;
  private String aItem4Location;
  private String aItem4Image;
  private long aItem4Price;
  private boolean aItem4Fightable;

  //Item 5
  private String aItem5Name;
  private String aItem5Description;
  private long aItem5Weight;
  private String aItem5Location;
  private String aItem5Image;
  private long aItem5Price;
  private boolean aItem5Fightable;

  public JSONReader() {
    // parsing file "GameInformation.json"
    try {
      this.aJsonReader =
        new JSONParser().parse(new FileReader("GameInformation.json"));
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }

    // typecasting obj to JSONObject
    this.aJsonObject = (JSONObject) aJsonReader;

    setLanguage();
    setGameInformation();
  }

  public void setGameInformation() {
    setGameSettings();

    //Player information
    setPlayerInformation();

    //NPC information
    setNPC1Information();
    setNPC2Information();
    setNPC3Information();
    setNPC4Information();

    //Item information
    setPotionInformation();
    setItem1Information();
    setItem2Information();
    setItem3Information();
    setItem4Information();
    setItem5Information();

    //Text information
    setAllWelcomeText();
    setCredit();

    //get room information
    setRoom1Information();
    setRoom2Information();
    setRoom3Information();
    setRoom4Information();
    setRoom5Information();
    setRoom6Information();
    setRoom7Information();
  }

  public void setLanguage() {
    Scanner vScanner;
    try {
      vScanner = new Scanner(new File("language.txt"));
      aLanguage = vScanner.nextLine();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public String getLanguage() {
    return aLanguage;
  }

  private void setGameSettings() {
    String vPath = "gameSettings" + getLanguage();

    Map vGameSettings = ((Map) aJsonObject.get(vPath));

    aGameName = (String) vGameSettings.get("name");
    aGameIcon = (String) vGameSettings.get("gameIcon");
    aGameWidth = (long) vGameSettings.get("width");
    aGameHeight = (long) vGameSettings.get("height");
    aArtefactNb = (long) vGameSettings.get("artefactNb");
    aQuitButton = (String) vGameSettings.get("quitButton");
    aNorthButton = (String) vGameSettings.get("northButton");
    aEastButton = (String) vGameSettings.get("eastButton");
    aSouthButton = (String) vGameSettings.get("southButton");
    aWestButton = (String) vGameSettings.get("westButton");
    aUpButton = (String) vGameSettings.get("upButton");
    aDownButton = (String) vGameSettings.get("downButton");
    aBackButton = (String) vGameSettings.get("backButton");
    aHelpButton = (String) vGameSettings.get("helpButton");
    aDropButton = (String) vGameSettings.get("dropButton");
    aTakeButton = (String) vGameSettings.get("takeButton");
    aGiveButton = (String) vGameSettings.get("giveButton");
    aChargeButton = (String) vGameSettings.get("chargeButton");
    aInventoryButton = (String) vGameSettings.get("inventoryButton");
    aFightButton = (String) vGameSettings.get("fightButton");
    aPlayButton = (String) vGameSettings.get("playButton");
    aDevModeButton = (String) vGameSettings.get("devModeButton");
    aSettingButton = (String) vGameSettings.get("settingButton");
    aMajQuitButton = (String) vGameSettings.get("majQuitButton");
    aMajBackButton = (String) vGameSettings.get("majBackButton");
    aSoundButton = (String) vGameSettings.get("soundButton");
    aSoundOnButton = (String) vGameSettings.get("soundOnButton");
    aSoundOffButton = (String) vGameSettings.get("soundOffButton");
    aLanguageButton = (String) vGameSettings.get("languageButton");
    aEnglishLanguageButton =
      (String) vGameSettings.get("englishLanguageButton");
    aFrenchLanguageButton = (String) vGameSettings.get("frenchLanguageButton");
    aRunButton = (String) vGameSettings.get("runButton");
    aBagButton = (String) vGameSettings.get("bagButton");
    aAttackButton = (String) vGameSettings.get("attackButton");
    aDefendButton = (String) vGameSettings.get("defendButton");
    aGameFont = (String) vGameSettings.get("gameFont");
    aTextSizeFont = (long) vGameSettings.get("textSizeFont");
    aButtonsSizeFont = (long) vGameSettings.get("buttonsSizeFont");
    aBattleSizeFont = (long) vGameSettings.get("battleSizeFont");
    aMenuSizeFont = (long) vGameSettings.get("menuSizeFont");
    aMainMenuFont = (String) vGameSettings.get("mainMenuFont");
    aTitleSizeFont = (long) vGameSettings.get("titleSizeFont");
  }

  public String getGameName() {
    return aGameName;
  }

  public String getGameIcon() {
    return aGameIcon;
  }

  public int getGameWidth() {
    return (int) aGameWidth;
  }

  public int getGameHeight() {
    return (int) aGameHeight;
  }

  public int getArtefactNb() {
    return (int) aArtefactNb;
  }

  public String getQuitButton() {
    return aQuitButton;
  }

  public String getNorthButton() {
    return aNorthButton;
  }

  public String getEastButton() {
    return aEastButton;
  }

  public String getSouthButton() {
    return aSouthButton;
  }

  public String getWestButton() {
    return aWestButton;
  }

  public String getUpButton() {
    return aUpButton;
  }

  public String getDownButton() {
    return aDownButton;
  }

  public String getBackButton() {
    return aBackButton;
  }

  public String getHelpButton() {
    return aHelpButton;
  }

  public String getDropButton() {
    return aDropButton;
  }

  public String getTakeButton() {
    return aTakeButton;
  }

  public String getGiveButton() {
    return aGiveButton;
  }

  public String getChargeButton() {
    return aChargeButton;
  }

  public String getInventoryButton() {
    return aInventoryButton;
  }

  public String getFightButton() {
    return aFightButton;
  }

  public String getPlayButton() {
    return aPlayButton;
  }

  public String getDevModeButton() {
    return aDevModeButton;
  }

  public String getSettingButton() {
    return aSettingButton;
  }

  public String getMajQuitButton() {
    return aMajQuitButton;
  }

  public String getMajBackButton() {
    return aMajBackButton;
  }

  public String getSoundButton() {
    return aSoundButton;
  }

  public String getSoundOnButton() {
    return aSoundOnButton;
  }

  public String getSoundOffButton() {
    return aSoundOffButton;
  }

  public String getLanguageButton() {
    return aLanguageButton;
  }

  public String getEnglishLanguageButton() {
    return aEnglishLanguageButton;
  }

  public String getFrenchLanguageButton() {
    return aFrenchLanguageButton;
  }

  public String getRunButton() {
    return aRunButton;
  }

  public String getBagButton() {
    return aBagButton;
  }

  public String getAttackButton() {
    return aAttackButton;
  }

  public String getDefendButton() {
    return aDefendButton;
  }

  public String getGameFont() {
    return aGameFont;
  }

  public float getTextSizeFont() {
    return (float) aTextSizeFont;
  }

  public float getButtonsSizeFont() {
    return (float) aButtonsSizeFont;
  }

  public float getBattleSizeFont() {
    return (float) aBattleSizeFont;
  }

  public float getMenuSizeFont() {
    return (float) aMenuSizeFont;
  }

  public String getMainMenuFont() {
    return aMainMenuFont;
  }

  public float getTitleSizeFont() {
    return (float) aTitleSizeFont;
  }

  private void setPlayerInformation() {
    String vPath = "player" + getLanguage();
    Map vPlayer = ((Map) aJsonObject.get(vPath));
    getPlayerAttributes(vPlayer);
  }

  private void getPlayerAttributes(Map pPlayer) {
    aPlayerName = (String) pPlayer.get("playerName");
    aMaxMovement = (long) pPlayer.get("maxMovement");
    aMaxWeight = (long) pPlayer.get("maxWeight");
    aPlayerHP = (long) pPlayer.get("playerHP");
    aPlayerMaxHP = (long) pPlayer.get("playerMaxHP");
    aPlayerDef = (long) pPlayer.get("playerDef");
    aPlayerSpeDef = (long) pPlayer.get("playerSpeDef");
    aPlayerAtt = (long) pPlayer.get("playerAtt");
    aPlayerSpeAtt = (long) pPlayer.get("playerSpeAtt");
    aPlayerFightable = (boolean) pPlayer.get("playerFightable");
    aPlayerMove1 = (String) pPlayer.get("playerMove1");
    aPlayerMove2 = (String) pPlayer.get("playerMove2");
    aPlayerMove3 = (String) pPlayer.get("playerMove3");
    aPlayerAttackDamage1 = (String) pPlayer.get("playerAttackDamage1");
    aPlayerAttackDamage2 = (String) pPlayer.get("playerAttackDamage2");
    aPlayerAttackDamage3 = (String) pPlayer.get("playerAttackDamage3");
    aPlayerAttackAccuracy1 = (String) pPlayer.get("playerAttackAccuracy1");
    aPlayerAttackAccuracy2 = (String) pPlayer.get("playerAttackAccuracy2");
    aPlayerAttackAccuracy3 = (String) pPlayer.get("playerAttackAccuracy3");
    aPlayerAttackType1 = (String) pPlayer.get("playerAttackType1");
    aPlayerAttackType2 = (String) pPlayer.get("playerAttackType2");
    aPlayerAttackType3 = (String) pPlayer.get("playerAttackType3");
  }

  // Get the name of the player
  public String getPlayerName() {
    return aPlayerName;
  }

  public int getMaxMovement() {
    return (int) aMaxMovement;
  }

  public int getMaxWeight() {
    return (int) aMaxWeight;
  }

  public int getPlayerHP() {
    return (int) aPlayerHP;
  }

  public int getPlayerMaxHP() {
    return (int) aPlayerMaxHP;
  }

  public int getPlayerDef() {
    return (int) aPlayerDef;
  }

  public int getPlayerSpeDef() {
    return (int) aPlayerSpeDef;
  }

  public int getPlayerAtt() {
    return (int) aPlayerAtt;
  }

  public int getPlayerSpeAtt() {
    return (int) aPlayerSpeAtt;
  }

  public boolean getPlayerFightable() {
    return aPlayerFightable;
  }

  public String getPlayerMoves() {
    aPlayerMoves = aPlayerMove1 + " " + aPlayerMove2 + " " + aPlayerMove3;
    return aPlayerMoves;
  }

  public String getPlayerAttackDamage() {
    aPlayerAttackDamage =
      aPlayerAttackDamage1 +
      " " +
      aPlayerAttackDamage2 +
      " " +
      aPlayerAttackDamage3;
    return aPlayerAttackDamage;
  }

  public String getPlayerAttackAccuracy() {
    aPlayerAttackAccuracy =
      aPlayerAttackAccuracy1 +
      " " +
      aPlayerAttackAccuracy2 +
      " " +
      aPlayerAttackAccuracy3;
    return aPlayerAttackAccuracy;
  }

  public String getPlayerAttackType() {
    aPlayerAttackType =
      aPlayerAttackType1 + " " + aPlayerAttackType2 + " " + aPlayerAttackType3;
    return aPlayerAttackType;
  }

  private void setAllWelcomeText() {
    String vPath = "printWelcome" + getLanguage();
    Map vText = ((Map) aJsonObject.get(vPath));
    aWelcomeText = (String) vText.get("welcomeText");
    aEndWelcomeText = (String) vText.get("endWelcomeText");
  }

  public String getWelcomeText() {
    return aWelcomeText;
  }

  public String getEndWelcomeText() {
    return aEndWelcomeText;
  }

  private void setCredit() {
    String vPath = "credit" + getLanguage();
    Map vCredit = ((Map) aJsonObject.get(vPath));
    aLine1 = (String) vCredit.get("line1");
    aLine2 = (String) vCredit.get("line2");
    aLine3 = (String) vCredit.get("line3");
    aLine4 = (String) vCredit.get("line4");
    aLine5 = (String) vCredit.get("line5");
    aLine6 = (String) vCredit.get("line6");
    aLine7 = (String) vCredit.get("line7");
  }

  public String getLine1() {
    return aLine1;
  }

  public String getLine2() {
    return aLine2;
  }

  public String getLine3() {
    return aLine3;
  }

  public String getLine4() {
    return aLine4;
  }

  public String getLine5() {
    return aLine5;
  }

  public String getLine6() {
    return aLine6;
  }

  public String getLine7() {
    return aLine7;
  }

  private void setRoom1Information() {
    String vPath = "room1";
    Map vRoom1 = ((Map) aJsonObject.get(vPath));

    aRoom1Name = (String) vRoom1.get("roomName");
    aRoom1Description = (String) vRoom1.get("roomDescription");
    aRoom1Image = (String) vRoom1.get("roomImage");
    getRoomExit("room1");
  }

  public String getRoom1Name() {
    return aRoom1Name;
  }

  public String getRoom1Description() {
    return aRoom1Description;
  }

  public String getRoom1Image() {
    return aRoom1Image;
  }

  public String getRoom1NorthExit() {
    return aRoom1NorthExit;
  }

  public String getRoom1SouthExit() {
    return aRoom1SouthExit;
  }

  public String getRoom1EastExit() {
    return aRoom1EastExit;
  }

  public String getRoom1WestExit() {
    return aRoom1WestExit;
  }

  public String getRoom1UpExit() {
    return aRoom1UpExit;
  }

  public String getRoom1DownExit() {
    return aRoom1DownExit;
  }

  private void setRoom2Information() {
    String vPath = "room2" + getLanguage();
    Map vRoom2 = ((Map) aJsonObject.get("room2"));

    aRoom2Name = (String) vRoom2.get("roomName");
    aRoom2Description = (String) vRoom2.get("roomDescription");
    aRoom2Image = (String) vRoom2.get("roomImage");
    getRoomExit("room2");
  }

  public String getRoom2Name() {
    return aRoom2Name;
  }

  public String getRoom2Description() {
    return aRoom2Description;
  }

  public String getRoom2Image() {
    return aRoom2Image;
  }

  public String getRoom2NorthExit() {
    return aRoom2NorthExit;
  }

  public String getRoom2SouthExit() {
    return aRoom2SouthExit;
  }

  public String getRoom2EastExit() {
    return aRoom2EastExit;
  }

  public String getRoom2WestExit() {
    return aRoom2WestExit;
  }

  public String getRoom2UpExit() {
    return aRoom2UpExit;
  }

  public String getRoom2DownExit() {
    return aRoom2DownExit;
  }

  private void setRoom3Information() {
    Map vRoom3 = ((Map) aJsonObject.get("room3"));

    aRoom3Name = (String) vRoom3.get("roomName");
    aRoom3Description = (String) vRoom3.get("roomDescription");
    aRoom3Image = (String) vRoom3.get("roomImage");
    getRoomExit("room3");
  }

  public String getRoom3Name() {
    return aRoom3Name;
  }

  public String getRoom3Description() {
    return aRoom3Description;
  }

  public String getRoom3Image() {
    return aRoom3Image;
  }

  public String getRoom3NorthExit() {
    return aRoom3NorthExit;
  }

  public String getRoom3SouthExit() {
    return aRoom3SouthExit;
  }

  public String getRoom3EastExit() {
    return aRoom3EastExit;
  }

  public String getRoom3WestExit() {
    return aRoom3WestExit;
  }

  public String getRoom3UpExit() {
    return aRoom3UpExit;
  }

  public String getRoom3DownExit() {
    return aRoom3DownExit;
  }

  private void setRoom4Information() {
    Map vRoom4 = ((Map) aJsonObject.get("room4"));

    aRoom4Name = (String) vRoom4.get("roomName");
    aRoom4Description = (String) vRoom4.get("roomDescription");
    aRoom4Image = (String) vRoom4.get("roomImage");
    getRoomExit("room4");
  }

  public String getRoom4Name() {
    return aRoom4Name;
  }

  public String getRoom4Description() {
    return aRoom4Description;
  }

  public String getRoom4Image() {
    return aRoom4Image;
  }

  public String getRoom4NorthExit() {
    return aRoom4NorthExit;
  }

  public String getRoom4SouthExit() {
    return aRoom4SouthExit;
  }

  public String getRoom4EastExit() {
    return aRoom4EastExit;
  }

  public String getRoom4WestExit() {
    return aRoom4WestExit;
  }

  public String getRoom4UpExit() {
    return aRoom4UpExit;
  }

  public String getRoom4DownExit() {
    return aRoom4DownExit;
  }

  private void setRoom5Information() {
    Map vRoom5 = ((Map) aJsonObject.get("room5"));

    aRoom5Name = (String) vRoom5.get("roomName");
    aRoom5Description = (String) vRoom5.get("roomDescription");
    aRoom5Image = (String) vRoom5.get("roomImage");
    getRoomExit("room5");
  }

  public String getRoom5Name() {
    return aRoom5Name;
  }

  public String getRoom5Description() {
    return aRoom5Description;
  }

  public String getRoom5Image() {
    return aRoom5Image;
  }

  public String getRoom5NorthExit() {
    return aRoom5NorthExit;
  }

  public String getRoom5SouthExit() {
    return aRoom5SouthExit;
  }

  public String getRoom5EastExit() {
    return aRoom5EastExit;
  }

  public String getRoom5WestExit() {
    return aRoom5WestExit;
  }

  public String getRoom5UpExit() {
    return aRoom5UpExit;
  }

  public String getRoom5DownExit() {
    return aRoom5DownExit;
  }

  private void setRoom6Information() {
    Map vRoom6 = ((Map) aJsonObject.get("room6"));

    aRoom6Name = (String) vRoom6.get("roomName");
    aRoom6Description = (String) vRoom6.get("roomDescription");
    aRoom6Image = (String) vRoom6.get("roomImage");
    getRoomExit("room6");
  }

  public String getRoom6Name() {
    return aRoom6Name;
  }

  public String getRoom6Description() {
    return aRoom6Description;
  }

  public String getRoom6Image() {
    return aRoom6Image;
  }

  public String getRoom6NorthExit() {
    return aRoom6NorthExit;
  }

  public String getRoom6SouthExit() {
    return aRoom6SouthExit;
  }

  public String getRoom6EastExit() {
    return aRoom6EastExit;
  }

  public String getRoom6WestExit() {
    return aRoom6WestExit;
  }

  public String getRoom6UpExit() {
    return aRoom6UpExit;
  }

  public String getRoom6DownExit() {
    return aRoom6DownExit;
  }

  private void setRoom7Information() {
    Map vRoom7 = ((Map) aJsonObject.get("room7"));

    aRoom7Name = (String) vRoom7.get("roomName");
    aRoom7Description = (String) vRoom7.get("roomDescription");
    aRoom7Image = (String) vRoom7.get("roomImage");
    getRoomExit("room7");
  }

  public String getRoom7Name() {
    return aRoom7Name;
  }

  public String getRoom7Description() {
    return aRoom7Description;
  }

  public String getRoom7Image() {
    return aRoom7Image;
  }

  public String getRoom7NorthExit() {
    return aRoom7NorthExit;
  }

  public String getRoom7SouthExit() {
    return aRoom7SouthExit;
  }

  public String getRoom7EastExit() {
    return aRoom7EastExit;
  }

  public String getRoom7WestExit() {
    return aRoom7WestExit;
  }

  public String getRoom7UpExit() {
    return aRoom7UpExit;
  }

  public String getRoom7DownExit() {
    return aRoom7DownExit;
  }

  private void getRoomExit(String pCurrentRoomInformation) {
    Map vExitRoom = ((Map) aJsonObject.get(pCurrentRoomInformation));

    switch (pCurrentRoomInformation) {
      case "room1":
        aRoom1NorthExit = (String) vExitRoom.get("roomNorthExit");
        aRoom1SouthExit = (String) vExitRoom.get("roomSouthExit");
        aRoom1EastExit = (String) vExitRoom.get("roomEastExit");
        aRoom1WestExit = (String) vExitRoom.get("roomWestExit");
        aRoom1UpExit = (String) vExitRoom.get("roomUpExit");
        aRoom1DownExit = (String) vExitRoom.get("roomDownExit");
        break;
      case "room2":
        aRoom2NorthExit = (String) vExitRoom.get("roomNorthExit");
        aRoom2SouthExit = (String) vExitRoom.get("roomSouthExit");
        aRoom2EastExit = (String) vExitRoom.get("roomEastExit");
        aRoom2WestExit = (String) vExitRoom.get("roomWestExit");
        aRoom2UpExit = (String) vExitRoom.get("roomUpExit");
        aRoom2DownExit = (String) vExitRoom.get("roomDownExit");
        break;
      case "room3":
        aRoom3NorthExit = (String) vExitRoom.get("roomNorthExit");
        aRoom3SouthExit = (String) vExitRoom.get("roomSouthExit");
        aRoom3EastExit = (String) vExitRoom.get("roomEastExit");
        aRoom3WestExit = (String) vExitRoom.get("roomWestExit");
        aRoom3UpExit = (String) vExitRoom.get("roomUpExit");
        aRoom3DownExit = (String) vExitRoom.get("roomDownExit");
        break;
      case "room4":
        aRoom4NorthExit = (String) vExitRoom.get("roomNorthExit");
        aRoom4SouthExit = (String) vExitRoom.get("roomSouthExit");
        aRoom4EastExit = (String) vExitRoom.get("roomEastExit");
        aRoom4WestExit = (String) vExitRoom.get("roomWestExit");
        aRoom4UpExit = (String) vExitRoom.get("roomUpExit");
        aRoom4DownExit = (String) vExitRoom.get("roomDownExit");
        break;
      case "room5":
        aRoom5NorthExit = (String) vExitRoom.get("roomNorthExit");
        aRoom5SouthExit = (String) vExitRoom.get("roomSouthExit");
        aRoom5EastExit = (String) vExitRoom.get("roomEastExit");
        aRoom5WestExit = (String) vExitRoom.get("roomWestExit");
        aRoom5UpExit = (String) vExitRoom.get("roomUpExit");
        aRoom5DownExit = (String) vExitRoom.get("roomDownExit");
        break;
      case "room6":
        aRoom6NorthExit = (String) vExitRoom.get("roomNorthExit");
        aRoom6SouthExit = (String) vExitRoom.get("roomSouthExit");
        aRoom6EastExit = (String) vExitRoom.get("roomEastExit");
        aRoom6WestExit = (String) vExitRoom.get("roomWestExit");
        aRoom6UpExit = (String) vExitRoom.get("roomUpExit");
        aRoom6DownExit = (String) vExitRoom.get("roomDownExit");
        break;
      case "room7":
        aRoom7NorthExit = (String) vExitRoom.get("roomNorthExit");
        aRoom7SouthExit = (String) vExitRoom.get("roomSouthExit");
        aRoom7EastExit = (String) vExitRoom.get("roomEastExit");
        aRoom7WestExit = (String) vExitRoom.get("roomWestExit");
        aRoom7UpExit = (String) vExitRoom.get("roomUpExit");
        aRoom7DownExit = (String) vExitRoom.get("roomDownExit");
        break;
    }
  }

  private void setPotionInformation() {
    String vPath = "potion" + getLanguage();
    Map vPotion = ((Map) aJsonObject.get(vPath));

    aPotionName = (String) vPotion.get("name");
    aPotionHealing = (long) vPotion.get("healing");
    aPotionLocation = (String) vPotion.get("location");
  }

  public String getPotionName() {
    return aPotionName;
  }

  public int getPotionHealing() {
    return (int) aPotionHealing;
  }

  public String getPotionLocation() {
    return aPotionLocation;
  }

  private void setItem1Information() {
    String vPath = "item1" + getLanguage();
    Map vItem1 = ((Map) aJsonObject.get(vPath));

    aItem1Name = (String) vItem1.get("name");
    aItem1Description = (String) vItem1.get("description");
    aItem1Location = (String) vItem1.get("location");
    aItem1Weight = (long) vItem1.get("weight");
    aItem1Image = (String) vItem1.get("image");
    aItem1Price = (long) vItem1.get("price");
    aItem1Fightable = (boolean) vItem1.get("fightable");
  }

  public String getItem1Name() {
    return aItem1Name;
  }

  public String getItem1Description() {
    return aItem1Description;
  }

  public String getItem1Location() {
    return aItem1Location;
  }

  public int getItem1Weight() {
    return (int) aItem1Weight;
  }

  public String getItem1Image() {
    return aItem1Image;
  }

  public int getItem1Price() {
    return (int) aItem1Price;
  }

  public boolean getItem1Fightable() {
    return aItem1Fightable;
  }

  private void setItem2Information() {
    String vPath = "item2" + getLanguage();
    Map vItem2 = ((Map) aJsonObject.get(vPath));

    aItem2Name = (String) vItem2.get("name");
    aItem2Description = (String) vItem2.get("description");
    aItem2Location = (String) vItem2.get("location");
    aItem2Weight = (long) vItem2.get("weight");
    aItem2Image = (String) vItem2.get("image");
    aItem2Price = (long) vItem2.get("price");
    aItem2Fightable = (boolean) vItem2.get("fightable");
  }

  public String getItem2Name() {
    return aItem2Name;
  }

  public String getItem2Description() {
    return aItem2Description;
  }

  public String getItem2Location() {
    return aItem2Location;
  }

  public int getItem2Weight() {
    return (int) aItem2Weight;
  }

  public String getItem2Image() {
    return aItem2Image;
  }

  public int getItem2Price() {
    return (int) aItem2Price;
  }

  public boolean getItem2Fightable() {
    return aItem2Fightable;
  }

  private void setItem3Information() {
    String vPath = "item3" + getLanguage();
    Map vItem3 = ((Map) aJsonObject.get(vPath));

    aItem3Name = (String) vItem3.get("name");
    aItem3Description = (String) vItem3.get("description");
    aItem3Location = (String) vItem3.get("location");
    aItem3Weight = (long) vItem3.get("weight");
    aItem3Image = (String) vItem3.get("image");
    aItem3Price = (long) vItem3.get("price");
    aItem3Fightable = (boolean) vItem3.get("fightable");
  }

  public String getItem3Name() {
    return aItem3Name;
  }

  public String getItem3Description() {
    return aItem3Description;
  }

  public String getItem3Location() {
    return aItem3Location;
  }

  public int getItem3Weight() {
    return (int) aItem3Weight;
  }

  public String getItem3Image() {
    return aItem3Image;
  }

  public int getItem3Price() {
    return (int) aItem3Price;
  }

  public boolean getItem3Fightable() {
    return aItem3Fightable;
  }

  private void setItem4Information() {
    String vPath = "item4" + getLanguage();
    Map vItem4 = ((Map) aJsonObject.get(vPath));

    aItem4Name = (String) vItem4.get("name");
    aItem4Description = (String) vItem4.get("description");
    aItem4Location = (String) vItem4.get("location");
    aItem4Weight = (long) vItem4.get("weight");
    aItem4Image = (String) vItem4.get("image");
    aItem4Price = (long) vItem4.get("price");
    aItem4Fightable = (boolean) vItem4.get("fightable");
  }

  public String getItem4Name() {
    return aItem4Name;
  }

  public String getItem4Description() {
    return aItem4Description;
  }

  public String getItem4Location() {
    return aItem4Location;
  }

  public int getItem4Weight() {
    return (int) aItem4Weight;
  }

  public String getItem4Image() {
    return aItem4Image;
  }

  public int getItem4Price() {
    return (int) aItem4Price;
  }

  public boolean getItem4Fightable() {
    return aItem4Fightable;
  }

  private void setItem5Information() {
    String vPath = "item5" + getLanguage();
    Map vItem5 = ((Map) aJsonObject.get(vPath));

    aItem5Name = (String) vItem5.get("name");
    aItem5Description = (String) vItem5.get("description");
    aItem5Location = (String) vItem5.get("location");
    aItem5Weight = (long) vItem5.get("weight");
    aItem5Image = (String) vItem5.get("image");
    aItem5Price = (long) vItem5.get("price");
    aItem5Fightable = (boolean) vItem5.get("fightable");
  }

  public String getItem5Name() {
    return aItem5Name;
  }

  public String getItem5Description() {
    return aItem5Description;
  }

  public String getItem5Location() {
    return aItem5Location;
  }

  public int getItem5Weight() {
    return (int) aItem5Weight;
  }

  public String getItem5Image() {
    return aItem5Image;
  }

  public int getItem5Price() {
    return (int) aItem5Price;
  }

  public boolean getItem5Fightable() {
    return aItem5Fightable;
  }

  private void setNPC1Information() {
    String vPath = "npc1" + getLanguage();
    Map vNPC1 = ((Map) aJsonObject.get(vPath));

    aNPC1Name = (String) vNPC1.get("name");
    aNPC1Description = (String) vNPC1.get("description");
    aNPC1Location = (String) vNPC1.get("location");
    aNPC1Image = (String) vNPC1.get("image");
    aNPC1FullImage = (String) vNPC1.get("fullImage");
    aNPC1Fightable = (boolean) vNPC1.get("fightable");
    aNPC1HP = (long) vNPC1.get("hp");
    aNPC1MaxHP = (long) vNPC1.get("maxHP");
    aNPC1Defense = (long) vNPC1.get("def");
    aNPC1SpeDefense = (long) vNPC1.get("speDef");
    aNPC1Attack = (long) vNPC1.get("att");
    aNPC1SpeAttack = (long) vNPC1.get("speAtt");
    aNPC1Dialogue = (String) vNPC1.get("dialogue");
    aNPC1Move1 = (String) vNPC1.get("move1");
    aNPC1Move2 = (String) vNPC1.get("move2");
    aNPC1Move3 = (String) vNPC1.get("move3");
    aNPC1AttackDamage1 = (String) vNPC1.get("attackDamage1");
    aNPC1AttackDamage2 = (String) vNPC1.get("attackDamage2");
    aNPC1AttackDamage3 = (String) vNPC1.get("attackDamage3");
    aNPC1AttackAccuracy1 = (String) vNPC1.get("attackAccuracy1");
    aNPC1AttackAccuracy2 = (String) vNPC1.get("attackAccuracy2");
    aNPC1AttackAccuracy3 = (String) vNPC1.get("attackAccuracy3");
    aNPC1AttackType1 = (String) vNPC1.get("attackType1");
    aNPC1AttackType2 = (String) vNPC1.get("attackType2");
    aNPC1AttackType3 = (String) vNPC1.get("attackType3");
  }

  public String getNPC1Name() {
    return aNPC1Name;
  }

  public String getNPC1Description() {
    return aNPC1Description;
  }

  public String getNPC1Location() {
    return aNPC1Location;
  }

  public String getNPC1Image() {
    return aNPC1Image;
  }

  public String getNPC1FullImage() {
    return aNPC1FullImage;
  }

  public boolean getNPC1Fightable() {
    return aNPC1Fightable;
  }

  public int getNPC1HP() {
    return (int) aNPC1HP;
  }

  public int getNPC1MaxHP() {
    return (int) aNPC1MaxHP;
  }

  public int getNPC1Defense() {
    return (int) aNPC1Defense;
  }

  public int getNPC1SpeDefense() {
    return (int) aNPC1SpeDefense;
  }

  public int getNPC1Attack() {
    return (int) aNPC1Attack;
  }

  public int getNPC1SpeAttack() {
    return (int) aNPC1SpeAttack;
  }

  public String getNPC1Dialogue() {
    return aNPC1Dialogue;
  }

  public String getNPC1Moves() {
    aNPC1Moves = aNPC1Move1 + " " + aNPC1Move2 + " " + aNPC1Move3;
    return aNPC1Moves;
  }

  public String getNPC1AttackDamage() {
    aNPC1AttackDamage =
      aNPC1AttackDamage1 + " " + aNPC1AttackDamage2 + " " + aNPC1AttackDamage3;
    return aNPC1AttackDamage;
  }

  public String getNPC1AttackAccuracy() {
    aNPC1AttackAccuracy =
      aNPC1AttackAccuracy1 +
      " " +
      aNPC1AttackAccuracy2 +
      " " +
      aNPC1AttackAccuracy3;
    return aNPC1AttackAccuracy;
  }

  public String getNPC1AttackType() {
    aNPC1AttackType =
      aNPC1AttackType1 + " " + aNPC1AttackType2 + " " + aNPC1AttackType3;
    return aNPC1AttackType;
  }

  private void setNPC2Information() {
    String vPath = "npc2" + getLanguage();
    Map vNPC2 = ((Map) aJsonObject.get(vPath));

    aNPC2Name = (String) vNPC2.get("name");
    aNPC2Description = (String) vNPC2.get("description");
    aNPC2Location = (String) vNPC2.get("location");
    aNPC2Image = (String) vNPC2.get("image");
    aNPC2FullImage = (String) vNPC2.get("fullImage");
    aNPC2Fightable = (boolean) vNPC2.get("fightable");
    aNPC2HP = (long) vNPC2.get("hp");
    aNPC2MaxHP = (long) vNPC2.get("maxHP");
    aNPC2Defense = (long) vNPC2.get("def");
    aNPC2SpeDefense = (long) vNPC2.get("speDef");
    aNPC2Attack = (long) vNPC2.get("att");
    aNPC2SpeAttack = (long) vNPC2.get("speAtt");
    aNPC2Dialogue = (String) vNPC2.get("dialogue");
    aNPC2Move1 = (String) vNPC2.get("move1");
    aNPC2Move2 = (String) vNPC2.get("move2");
    aNPC2Move3 = (String) vNPC2.get("move3");
    aNPC2AttackDamage1 = (String) vNPC2.get("attackDamage1");
    aNPC2AttackDamage2 = (String) vNPC2.get("attackDamage2");
    aNPC2AttackDamage3 = (String) vNPC2.get("attackDamage3");
    aNPC2AttackAccuracy1 = (String) vNPC2.get("attackAccuracy1");
    aNPC2AttackAccuracy2 = (String) vNPC2.get("attackAccuracy2");
    aNPC2AttackAccuracy3 = (String) vNPC2.get("attackAccuracy3");
    aNPC2AttackType1 = (String) vNPC2.get("attackType1");
    aNPC2AttackType2 = (String) vNPC2.get("attackType2");
    aNPC2AttackType3 = (String) vNPC2.get("attackType3");
  }

  public String getNPC2Name() {
    return aNPC2Name;
  }

  public String getNPC2Description() {
    return aNPC2Description;
  }

  public String getNPC2Location() {
    return aNPC2Location;
  }

  public String getNPC2Image() {
    return aNPC2Image;
  }

  public String getNPC2FullImage() {
    return aNPC2FullImage;
  }

  public Boolean getNPC2Fightable() {
    return aNPC2Fightable;
  }

  public int getNPC2HP() {
    return (int) aNPC2HP;
  }

  public int getNPC2MaxHP() {
    return (int) aNPC2MaxHP;
  }

  public int getNPC2Defense() {
    return (int) aNPC2Defense;
  }

  public int getNPC2SpeDefense() {
    return (int) aNPC2SpeDefense;
  }

  public int getNPC2Attack() {
    return (int) aNPC2Attack;
  }

  public int getNPC2SpeAttack() {
    return (int) aNPC2SpeAttack;
  }

  public String getNPC2Dialogue() {
    return aNPC2Dialogue;
  }

  public String getNPC2Moves() {
    aNPC2Moves = aNPC2Move1 + " " + aNPC2Move2 + " " + aNPC2Move3;
    return aNPC2Moves;
  }

  public String getNPC2AttackDamage() {
    aNPC2AttackDamage =
      aNPC2AttackDamage1 + " " + aNPC2AttackDamage2 + " " + aNPC2AttackDamage3;
    return aNPC2AttackDamage;
  }

  public String getNPC2AttackAccuracy() {
    aNPC2AttackAccuracy =
      aNPC2AttackAccuracy1 +
      " " +
      aNPC2AttackAccuracy2 +
      " " +
      aNPC2AttackAccuracy3;
    return aNPC2AttackAccuracy;
  }

  public String getNPC2AttackType() {
    aNPC2AttackType =
      aNPC2AttackType1 + " " + aNPC2AttackType2 + " " + aNPC2AttackType3;
    return aNPC2AttackType;
  }

  private void setNPC3Information() {
    String vPath = "npc3" + getLanguage();
    Map vNPC3 = ((Map) aJsonObject.get(vPath));

    aNPC3Name = (String) vNPC3.get("name");
    aNPC3Description = (String) vNPC3.get("description");
    aNPC3Location = (String) vNPC3.get("location");
    aNPC3Image = (String) vNPC3.get("image");
    aNPC3FullImage = (String) vNPC3.get("fullImage");
    aNPC3Fightable = (boolean) vNPC3.get("fightable");
    aNPC3HP = (long) vNPC3.get("hp");
    aNPC3MaxHP = (long) vNPC3.get("maxHP");
    aNPC3Defense = (long) vNPC3.get("def");
    aNPC3SpeDefense = (long) vNPC3.get("speDef");
    aNPC3Attack = (long) vNPC3.get("att");
    aNPC3SpeAttack = (long) vNPC3.get("speAtt");
    aNPC3Dialogue = (String) vNPC3.get("dialogue");
    aNPC3Move1 = (String) vNPC3.get("move1");
    aNPC3Move2 = (String) vNPC3.get("move2");
    aNPC3Move3 = (String) vNPC3.get("move3");
    aNPC3AttackDamage1 = (String) vNPC3.get("attackDamage1");
    aNPC3AttackDamage2 = (String) vNPC3.get("attackDamage2");
    aNPC3AttackDamage3 = (String) vNPC3.get("attackDamage3");
    aNPC3AttackAccuracy1 = (String) vNPC3.get("attackAccuracy1");
    aNPC3AttackAccuracy2 = (String) vNPC3.get("attackAccuracy2");
    aNPC3AttackAccuracy3 = (String) vNPC3.get("attackAccuracy3");
    aNPC3AttackType1 = (String) vNPC3.get("attackType1");
    aNPC3AttackType2 = (String) vNPC3.get("attackType2");
    aNPC3AttackType3 = (String) vNPC3.get("attackType3");
  }

  public String getNPC3Name() {
    return aNPC3Name;
  }

  public String getNPC3Description() {
    return aNPC3Description;
  }

  public String getNPC3Location() {
    return aNPC3Location;
  }

  public String getNPC3Image() {
    return aNPC3Image;
  }

  public String getNPC3FullImage() {
    return aNPC3FullImage;
  }

  public Boolean getNPC3Fightable() {
    return aNPC3Fightable;
  }

  public int getNPC3HP() {
    return (int) aNPC3HP;
  }

  public int getNPC3MaxHP() {
    return (int) aNPC3MaxHP;
  }

  public int getNPC3Defense() {
    return (int) aNPC3Defense;
  }

  public int getNPC3SpeDefense() {
    return (int) aNPC3SpeDefense;
  }

  public int getNPC3Attack() {
    return (int) aNPC3Attack;
  }

  public int getNPC3SpeAttack() {
    return (int) aNPC3SpeAttack;
  }

  public String getNPC3Dialogue() {
    return aNPC3Dialogue;
  }

  public String getNPC3Moves() {
    aNPC3Moves = aNPC3Move1 + " " + aNPC3Move2 + " " + aNPC3Move3;
    return aNPC3Moves;
  }

  public String getNPC3AttackDamage() {
    aNPC3AttackDamage =
      aNPC3AttackDamage1 + " " + aNPC3AttackDamage2 + " " + aNPC3AttackDamage3;
    return aNPC3AttackDamage;
  }

  public String getNPC3AttackAccuracy() {
    aNPC3AttackAccuracy =
      aNPC3AttackAccuracy1 +
      " " +
      aNPC3AttackAccuracy2 +
      " " +
      aNPC3AttackAccuracy3;
    return aNPC3AttackAccuracy;
  }

  public String getNPC3AttackType() {
    aNPC3AttackType =
      aNPC3AttackType1 + " " + aNPC3AttackType2 + " " + aNPC3AttackType3;
    return aNPC3AttackType;
  }

  private void setNPC4Information() {
    String vPath = "npc4" + getLanguage();
    Map vNPC4 = ((Map) aJsonObject.get(vPath));

    aNPC4Name = (String) vNPC4.get("name");
    aNPC4Description = (String) vNPC4.get("description");
    aNPC4Location = (String) vNPC4.get("location");
    aNPC4Image = (String) vNPC4.get("image");
    aNPC4FullImage = (String) vNPC4.get("fullImage");
    aNPC4Fightable = (boolean) vNPC4.get("fightable");
    aNPC4HP = (long) vNPC4.get("hp");
    aNPC4MaxHP = (long) vNPC4.get("maxHP");
    aNPC4Defense = (long) vNPC4.get("def");
    aNPC4SpeDefense = (long) vNPC4.get("speDef");
    aNPC4Attack = (long) vNPC4.get("att");
    aNPC4SpeAttack = (long) vNPC4.get("speAtt");
    aNPC4Dialogue = (String) vNPC4.get("dialogue");
    aNPC4Move1 = (String) vNPC4.get("move1");
    aNPC4Move2 = (String) vNPC4.get("move2");
    aNPC4Move3 = (String) vNPC4.get("move3");
    aNPC4AttackDamage1 = (String) vNPC4.get("attackDamage1");
    aNPC4AttackDamage2 = (String) vNPC4.get("attackDamage2");
    aNPC4AttackDamage3 = (String) vNPC4.get("attackDamage3");
    aNPC4AttackAccuracy1 = (String) vNPC4.get("attackAccuracy1");
    aNPC4AttackAccuracy2 = (String) vNPC4.get("attackAccuracy2");
    aNPC4AttackAccuracy3 = (String) vNPC4.get("attackAccuracy3");
    aNPC4AttackType1 = (String) vNPC4.get("attackType1");
    aNPC4AttackType2 = (String) vNPC4.get("attackType2");
    aNPC4AttackType3 = (String) vNPC4.get("attackType3");
  }

  public String getNPC4Name() {
    return aNPC4Name;
  }

  public String getNPC4Description() {
    return aNPC4Description;
  }

  public String getNPC4Location() {
    return aNPC4Location;
  }

  public String getNPC4Image() {
    return aNPC4Image;
  }

  public String getNPC4FullImage() {
    return aNPC4FullImage;
  }

  public Boolean getNPC4Fightable() {
    return aNPC4Fightable;
  }

  public int getNPC4HP() {
    return (int) aNPC4HP;
  }

  public int getNPC4MaxHP() {
    return (int) aNPC4MaxHP;
  }

  public int getNPC4Defense() {
    return (int) aNPC4Defense;
  }

  public int getNPC4SpeDefense() {
    return (int) aNPC4SpeDefense;
  }

  public int getNPC4Attack() {
    return (int) aNPC4Attack;
  }

  public int getNPC4SpeAttack() {
    return (int) aNPC4SpeAttack;
  }

  public String getNPC4Dialogue() {
    return aNPC4Dialogue;
  }

  public String getNPC4Moves() {
    aNPC4Moves = aNPC4Move1 + " " + aNPC4Move2 + " " + aNPC4Move3;
    return aNPC4Moves;
  }

  public String getNPC4AttackDamage() {
    aNPC4AttackDamage =
      aNPC4AttackDamage1 + " " + aNPC4AttackDamage2 + " " + aNPC4AttackDamage3;
    return aNPC4AttackDamage;
  }

  public String getNPC4AttackAccuracy() {
    aNPC4AttackAccuracy =
      aNPC4AttackAccuracy1 +
      " " +
      aNPC4AttackAccuracy2 +
      " " +
      aNPC4AttackAccuracy3;
    return aNPC4AttackAccuracy;
  }

  public String getNPC4AttackType() {
    aNPC4AttackType =
      aNPC4AttackType1 + " " + aNPC4AttackType2 + " " + aNPC4AttackType3;
    return aNPC4AttackType;
  }
}
