package pkg_Core;

// Java program to read JSON from a file

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONReader {

  private Object aJsonReader;
  private JSONObject aJsonObject;
  private GameEngine aGameEngine;

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

  //Text
  private String aWelcomeText;
  private String aEndWelcomeText;

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

    getPlayerInformation();

    getAllWelcomeText();

    //get room information
    getRoom1Information();
    getRoom2Information();
    getRoom3Information();
    getRoom4Information();
    getRoom5Information();
    getRoom6Information();
    getRoom7Information();
  }

  private void getPlayerInformation() {
    // getting Player Information
    Map vPlayer = ((Map) aJsonObject.get("player"));

    aPlayerName = (String) vPlayer.get("playerName");
    aMaxMovement = (long) vPlayer.get("maxMovement");
    aMaxWeight = (long) vPlayer.get("maxWeight");
    aPlayerHP = (long) vPlayer.get("playerHP");
    aPlayerMaxHP = (long) vPlayer.get("playerMaxHP");
    aPlayerDef = (long) vPlayer.get("playerDef");
    aPlayerSpeDef = (long) vPlayer.get("playerSpeDef");
    aPlayerAtt = (long) vPlayer.get("playerAtt");
    aPlayerSpeAtt = (long) vPlayer.get("playerSpeAtt");
    aPlayerFightable = (boolean) vPlayer.get("playerFightable");
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

  // TODO : remettre la mise en forme du texte
  private void getAllWelcomeText() {
    Map vText = ((Map) aJsonObject.get("printWelcome"));
    aWelcomeText = (String) vText.get("welcomeText");
    aEndWelcomeText = (String) vText.get("endWelcomeText");
  }

  public String getWelcomeText() {
    return aWelcomeText;
  }

  public String getEndWelcomeText() {
    return aEndWelcomeText;
  }

  private void getRoom1Information() {
    Map vRoom1 = ((Map) aJsonObject.get("room1"));

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

  private void getRoom2Information() {
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

  private void getRoom3Information() {
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

  private void getRoom4Information() {
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

  private void getRoom5Information() {
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

  private void getRoom6Information() {
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

  private void getRoom7Information() {
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
}
