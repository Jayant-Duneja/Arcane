package com.arcane.board;

import com.arcane.Element;
import com.arcane.board.rooms.Room;
import com.arcane.board.rooms.StartingRoom;
import com.arcane.character.adventurer.*;
import com.arcane.character.creature.*;
import com.arcane.util.Constants;
import java.util.*;

public class GameBoard {

  private Map<String, Room> roomMap;

  public GameBoard() {
    initialiseBoard();
    addAdventures();
    addCreatures();
  }

  private void initialiseBoard() {
    roomMap = new LinkedHashMap<>();
    Map<Element, Floor> elementalFloors = createElementalFloors();
    StartingRoom startingRoom = createStartingRoom(elementalFloors);
    populateRoomMap(startingRoom, elementalFloors);
  }

  private void populateRoomMap(StartingRoom startingRoom, Map<Element, Floor> elementalFloors) {
    roomMap.put(startingRoom.getRoomId(), startingRoom);
    for (Floor floor : elementalFloors.values()) {
      for (Room[] row : floor.getRooms()) {
        for (Room room : row) {
          roomMap.put(room.getRoomId(), room);
        }
      }
    }
  }

  private Map<Element, Floor> createElementalFloors() {
    Map<Element, Floor> elementFloorMap = new EnumMap<>(Element.class);
    for (Element element : Element.values()) {
      elementFloorMap.put(element, new Floor(element));
    }
    return elementFloorMap;
  }

  private StartingRoom createStartingRoom(Map<Element, Floor> elementalFloors) {
    StartingRoom startingRoom = new StartingRoom();
    List<Room> connectedRooms = new ArrayList<>();
    for (Floor floor : elementalFloors.values()) {
      connectedRooms.add(
          floor.getRoom(Constants.VERTICAL_ROOMS / 2, Constants.HORIZONTAL_ROOMS / 2));
    }
    startingRoom.setConnectedRooms(connectedRooms);
    return startingRoom;
  }

  private void addAdventures() {
    List<Adventurer> adventurers =
        Arrays.asList(new EmberKnight(), new MistWalker(), new TerraVoyager(), new ZephyrRogue());
    adventurers.forEach(
        adventurer -> this.roomMap.get(adventurer.getCurrentRoomId()).addAdventurer(adventurer));
  }

  private void addCreatures() {
    List<Creature> creatures = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      creatures.add(new FireBorn());
      creatures.add(new Aquarid());
      creatures.add(new TerraVore());
      creatures.add(new Zephyral());
    }
    creatures.forEach(
        creature -> this.roomMap.get(creature.getCurrentRoomId()).addCreature(creature));
  }

  public Room getRoom(String currentRoomId) {
    return roomMap.get(currentRoomId);
  }

  public void renderBoard() {
    int roomWidth = 30;
    renderStartingRoom(roomWidth);
    for (Element element : Element.values()) {
      renderElementalFloors(roomWidth, element);
    }
  }

  private void renderStartingRoom(int roomWidth) {
    System.out.println("Starting Room:");
    Room startingRoom = this.roomMap.get(Constants.STARTING_ROOM_ID);
    String border = "+" + "-".repeat(roomWidth) + "+";
    String characterString = getCharacterString(startingRoom, roomWidth);
    String roomContents = "|" + characterString + "|";
    System.out.println(border);
    System.out.println(roomContents);
    System.out.println(border);
  }

  private void renderElementalFloors(int roomWidth, Element element) {
    System.out.println(element.name() + " Floor:");
    String border =
        "+"
            + "-".repeat(roomWidth)
            + "+"
            + "-".repeat(roomWidth)
            + "+"
            + "-".repeat(roomWidth)
            + "+";
    for (int row = 0; row < Constants.HORIZONTAL_ROOMS; row++) {
      System.out.println(border);
      for (int column = 0; column < Constants.VERTICAL_ROOMS; column++) {
        Room room = this.roomMap.get(element.name() + "-" + row + "-" + column);
        String characterString = getCharacterString(room, roomWidth);
        System.out.print("|" + characterString);
      }
      System.out.println("|");
    }
    System.out.println(border);
  }

  private String getCharacterString(Room room, int roomWidth) {
    String characters =
        getAdventurersInRoom(room.getAdventurers()) + ":" + getCreaturesInRoom(room.getCreatures());
    int lPadding = (roomWidth - characters.length()) / 2;
    int rPadding = roomWidth - characters.length() - lPadding;
    return " ".repeat(lPadding) + characters + " ".repeat(rPadding);
  }

  // the input parameter is a list of adventurers in a particular room
  // return a concatenated string of abbreviations of the adventurers in the room
  public String getAdventurersInRoom(List<Adventurer> adventurers) {
    StringBuilder adventurersPresent = new StringBuilder();
    for (Adventurer adventurer : adventurers) {
      if (adventurer.isAlive()) {
        adventurersPresent.append(adventurer.getAcronym().acronym).append(",");
      }
    }
    return (adventurersPresent.length() == 0)
        ? "-"
        : adventurersPresent.substring(0, adventurersPresent.length() - 1);
  }

  // the input parameter is a list of creatures in a particular room
  // return a concatenated string of abbreviations of the creatures in the room
  public String getCreaturesInRoom(List<Creature> creatures) {
    StringBuilder creaturesPresent = new StringBuilder();
    for (Creature creature : creatures) {
      creaturesPresent.append(creature.getAcronym().acronym).append(",");
    }
    return (creaturesPresent.length() == 0)
        ? "-"
        : creaturesPresent.substring(0, creaturesPresent.length() - 1);
  }

  // iterates through each room and returns a list of creatures still alive
  public List<Creature> getRemainingCreatures() {
    List<Creature> creatures = new ArrayList<>();
    this.roomMap.values().forEach((room -> creatures.addAll(room.getCreatures())));
    return creatures;
  }

  // returns true if even one adventurer is alive
  // returns false if all adventurers are dead
  public boolean areAllAdventuresDead(List<Adventurer> adventurers) {
    for (Adventurer adventurer : adventurers) {
      if (adventurer.isAlive()) {
        return false;
      }
    }
    return true;
  }

  // returns a cumulative count of treasures found by all adventurers
  public int getTotalTreasureCount(List<Adventurer> adventurers) {
    int totalTreasureCollected = 0;
    for (Adventurer adventurer : adventurers) {
      totalTreasureCollected += adventurer.getTreasureCount();
    }
    return totalTreasureCollected;
  }
}
