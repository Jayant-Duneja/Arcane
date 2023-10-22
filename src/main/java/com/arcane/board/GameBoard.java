package com.arcane.board;

import com.arcane.Decorator.*;
import com.arcane.Element;
import com.arcane.Factory.AdventurerFactory;
import com.arcane.Factory.CreatureFactory;
import com.arcane.board.rooms.Room;
import com.arcane.board.rooms.StartingRoom;
import com.arcane.character.adventurer.*;
import com.arcane.character.creature.*;
import com.arcane.util.Constants;

import java.util.*;

public class GameBoard {

  private Map<String, Room> roomMap;
  AdventurerFactory adventurerFactory;
  CreatureFactory creatureFactory;

  public GameBoard(String adventurerType, String adventurerCustomName, String creatures) {
    initialiseBoard();
    addAdventures(adventurerType, adventurerCustomName);
    addCreatures(creatures);
    addTreasures();
  }

  private void initialiseBoard() {
    roomMap = new LinkedHashMap<>();
    adventurerFactory =  new AdventurerFactory();
    creatureFactory = new CreatureFactory();
    Map<Element, Floor> elementalFloors = createElementalFloors();
    StartingRoom startingRoom = createStartingRoom(elementalFloors);
    addStartingRoomConnections(elementalFloors, startingRoom);
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

  private void addStartingRoomConnections(
      Map<Element, Floor> elementalFloors, StartingRoom startingRoom) {
    for (Floor floor : elementalFloors.values()) {
      Room room = floor.getRoom(Constants.VERTICAL_ROOMS / 2, Constants.HORIZONTAL_ROOMS / 2);
      List<Room> connectedRooms = room.getConnectedRooms();
      connectedRooms.add(startingRoom);
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

  private void addAdventures(String adventurerType, String adventurerCustomName) {
    List<Adventurer> adventurers =
        Arrays.asList(adventurerFactory.createCharacter(adventurerType, adventurerCustomName));
    adventurers.forEach(
        adventurer -> this.roomMap.get(adventurer.getCurrentRoomId()).addAdventurer(adventurer));
  }

  private void addCreatures(String commaSeperatedCreatures) {
    // Split the string by comma and store the substrings in an array
    String[] stringArray = commaSeperatedCreatures.split(",");
    // Convert the array to a List (if you need a List)
    List<String> tempList = Arrays.asList(stringArray);
    List<Creature> creatures =  new ArrayList<>();
    for(String s:tempList){
      creatures.add(creatureFactory.createCharacter(s, ""));
    }
    creatures.forEach(
        creature -> this.roomMap.get(creature.getCurrentRoomId()).addCreature(creature));
  }
  private void addTreasures(){
    List<Treasure_Decorator> treasures = new ArrayList<>();
    for(int i=0;i<4;i++){
      treasures.add(new Armor(null));
      treasures.add(new Elixir(null));
      treasures.add(new Ether(null));
      treasures.add(new Portal(null));
      treasures.add(new Potion(null));
      treasures.add(new Sword(null));
    }
    for(int i=0; i< 15; i++){
      treasures.add(new Gem(null));
    }
    treasures.forEach(
            treasure -> this.roomMap.get(treasure.getRoom()).addTreasure(treasure));
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

  public boolean areAllTreasureTypeFound(List<Adventurer> adventurers) {
    int totalTreasure = 0;
    for (Adventurer adventurer : adventurers) {

      Map<String, Integer> treasure_inventory = adventurer.getTreasureInventory();
      for (String treasureType : treasure_inventory.keySet()) {

        if (!treasureType.equals("Gem")) {
          totalTreasure += treasure_inventory.get(treasureType);
          if (totalTreasure >= Constants.TREASURE_TYPE_COUNT) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public int treasureValue(List<Adventurer> adventurers){
      // Call Value function here
    int total_treasure_value=0;
    for(Adventurer adventurer:adventurers)
      total_treasure_value+=adventurer.getTreasure_bag().get_value();
    return total_treasure_value;
  }

}
