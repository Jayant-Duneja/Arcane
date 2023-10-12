package com.arcane.character.adventurer;

import com.arcane.Decorator.Treasure;
import com.arcane.Decorator.Treasure_Bag;
import com.arcane.Decorator.Treasure_Decorator;
import com.arcane.Element;
import com.arcane.Observer.Event;
import com.arcane.Observer.EventType;
import com.arcane.Observer.Subject.ConcreteSubject;
import com.arcane.board.Dice;
import com.arcane.board.GameBoard;
import com.arcane.board.rooms.Room;
import com.arcane.character.Character;
import com.arcane.character.creature.Creature;
import com.arcane.expertise.*;
import com.arcane.util.Constants;
import com.arcane.util.RandomHelper;

import java.util.*;

import static com.arcane.Decorator.Treasure_Factory.createObject;
import static javax.swing.UIManager.put;

// Example of Inheritance, Creature is a subclass of Adventurer
public abstract class Adventurer extends Character {
  private final Element resonanceElement;
  private final Element discordElement;
  private final AdventurerAcronym acronym;
  protected int treasureCount = 0;
  private int dodgeChance;
  private int baseTreasureRoll = 0;
  private int baseCombatRoll = 0;
  private int baseCreatureCombatRoll=0;
  private int health;
  private int combatExpertiseBonus = 0;
  private int searchExpertiseBonus = 0;
  IExpertise expertise;

  private int creatureDamage = 2;
  private Map<String, Integer> treasure_inventory;
  private Treasure treasure_bag;
  protected Adventurer(
      int health,
      int dodgeChance,
      Element resonanceElement,
      Element discordElement,
      AdventurerAcronym acronym) {
    this.health = health;
    this.dodgeChance = dodgeChance;
    this.resonanceElement = resonanceElement;
    this.discordElement = discordElement;
    this.acronym = acronym;
    this.currentRoomId = Constants.STARTING_ROOM_ID;
    expertise = new Novice();
    this.treasure_inventory = new HashMap<>(){
      {
        put("Armor", 0);
        put("Elixir", 0);
        put("Ether", 0);
        put("Gem", 0);
        put("Portal", 0);
        put("Potion", 0);
        put("Sword", 0);
      }};
    this.treasure_bag = new Treasure_Bag();
  }

  @Override
  public int combatRoll() {
      return super.combatRoll() + baseCombatRoll + combatExpertiseBonus;
  }
  public int creatureFinalRoll(Creature creature){
    return this.baseCreatureCombatRoll + creature.combatRoll();
  }

  @Override
  public void performAction(GameBoard gameBoard, ConcreteSubject concreteSubject) {
    // Handle Elemental effects to the adventurer stats
    handleElementalEffects(gameBoard.getRoom(currentRoomId), concreteSubject);
    super.performAction(gameBoard, concreteSubject);
  }

  @Override
  protected void fight(GameBoard gameBoard, ConcreteSubject concreteSubject) {
    fightCreatures(gameBoard, concreteSubject);
  }

  @Override
//   protected void move(GameBoard gameBoard, ConcreteSubject concreteSubject) {
    // Remove adventurer from current room
//     gameBoard.getRoom(currentRoomId).getAdventurers().remove(this);
//     // Move adventurer to a random valid room
//     currentRoomId =
//         RandomHelper.getRandomElementFromList(gameBoard.getRoom(currentRoomId).getConnectedRooms())
//             .getRoomId();
//     concreteSubject.add_event_to_current_turn(EventType.Adventurer_enter_room, this.acronym.acronym, currentRoomId);
//     // Add adventurer to the new room
//     gameBoard.getRoom(currentRoomId).addAdventurer(this);
//     // Handle Elemental effects to the adventurer stats
//     handleElementalEffects(gameBoard.getRoom(currentRoomId), concreteSubject);
//     // Perform post move action
//     postMove(gameBoard, concreteSubject);
  protected void move(GameBoard gameBoard,ConcreteSubject concreteSubject) {

    if(canUsePortal()) {
      usePortal(gameBoard, concreteSubject);
    }
    else {
//       // Remove adventurer from current room
//       gameBoard.getRoom(currentRoomId).getAdventurers().remove(this);
//       // Move adventurer to a random valid room
//       currentRoomId =
//               RandomHelper.getRandomElementFromList(gameBoard.getRoom(currentRoomId).getConnectedRooms())
//                       .getRoomId();
//       // Add adventurer to the new room
//       gameBoard.getRoom(currentRoomId).addAdventurer(this);
//       // Handle Elemental effects to the adventurer stats
//       handleElementalEffects(gameBoard.getRoom(currentRoomId));
//       // Perform post move action
//       postMove(gameBoard);
      gameBoard.getRoom(currentRoomId).getAdventurers().remove(this);
      // Move adventurer to a random valid room
      currentRoomId =
          RandomHelper.getRandomElementFromList(gameBoard.getRoom(currentRoomId).getConnectedRooms())
              .getRoomId();
      concreteSubject.add_event_to_current_turn(EventType.Adventurer_enter_room, this.acronym.acronym, currentRoomId);
      // Add adventurer to the new room
      gameBoard.getRoom(currentRoomId).addAdventurer(this);
      // Handle Elemental effects to the adventurer stats
      handleElementalEffects(gameBoard.getRoom(currentRoomId), concreteSubject);
      // Perform post move action
      postMove(gameBoard, concreteSubject);
    }
  }

  @Override
  protected void postMove(GameBoard board, ConcreteSubject concreteSubject) {
    if (isFightScenario(board)) {
      fightCreatures(board, concreteSubject);
    } else {
      searchTreasure(board, concreteSubject);
    }
  }

  protected void fightCreatures(GameBoard gameBoard, ConcreteSubject concreteSubject) {
    // if adventurer is alive then fight
    if (this.isAlive()) {
      // Get the creatures present in the current room
      List<Creature> creatures =
          new ArrayList<>(gameBoard.getRoom(this.currentRoomId).getCreatures());
      // For all adventurer vs creatures, check dice roll
      for (Creature creature : creatures) {
        // if adventurer is alive then fight
        fightCreature(creature, gameBoard, concreteSubject);
      }
    }
  }

  private void fightCreature(Creature creature, GameBoard gameBoard, ConcreteSubject concreteSubject) {
    if (this.isAlive()) {
        int creatureRoll = this.creatureFinalRoll(creature);
      if (creatureRoll > this.combatRoll()) {
        // if adventurer loses then take damage
        if (!isDodgeSuccessful()) {
          concreteSubject.add_event_to_current_turn(EventType.LOSE_HEALTH, this.acronym.acronym + "-" + this.creatureDamage);
          this.takeDamage();
          if(!this.isAlive()){
            concreteSubject.add_event_to_current_turn(EventType.DEFEAT, this.acronym.acronym);
          }
        }
      } else if (creatureRoll < this.combatRoll()) {
        // if creature loses then remove it from current room
        gameBoard.getRoom(this.currentRoomId).removeCreature(creature);
        concreteSubject.add_event_to_current_turn(EventType.WIN_COMBAT, this.acronym.acronym);
        concreteSubject.add_event_to_current_turn(EventType.LOSE_COMBAT, creature.getAcronym().acronym);
        concreteSubject.add_event_to_current_turn(EventType.DEFEAT, creature.getAcronym().acronym);
        // In case of adventurer victory, increase the combatExperience
//         this.combatExpertiseBonus++;
//         setCombatExpertiseBonus(this.combatExpertiseBonus);
        // Update the adventurer Combat Expertise
        UpdateExpertise(this.expertise.getId());
      }
    }
  }
  private void UpdateExpertise(int previousExpertiseLevel){

    int newExpertiseLevel = previousExpertiseLevel + 1;
    increaseExpertiseLevel(newExpertiseLevel);

    this.combatExpertiseBonus += expertise.combatBonus();
  }

  private void increaseExpertiseLevel(int newExpertiseId) {
    if (newExpertiseId < 3) {
      switch (newExpertiseId) {
        case 1:
          expertise = new Seasoned();
          break;
        case 2:
          expertise = new Veteran();
          break;
        case 3:
          expertise = new Master();
          break;
        default:
          expertise = new Novice();
          break;
      }
    }
  }

  private void handleElementalEffects(Room room, ConcreteSubject concreteSubject) {
    this.elementalReset();
    if (!room.getRoomId().equals(Constants.STARTING_ROOM_ID)) {
      Element element = Element.valueOf(room.getRoomId().split("-")[0]);
      if (element == this.resonanceElement) {
        this.elementalResonance();
        concreteSubject.add_event_to_current_turn(EventType.GAIN_ELEMENTAL_RESONANCE, this.acronym.acronym);
      } else if (element == this.discordElement) {
        this.elementalDiscord();
        concreteSubject.add_event_to_current_turn(EventType.GAIN_ELEMENTAL_DISCORD, this.acronym.acronym);
      }
    }
  }

  public int getHealth() {
    return Math.max(health, 0);
  }
  public void setHealth(int health){ this.health=health;}

  public int getCreatureDamage() {
    return creatureDamage;
  }

  public void setCreatureDamage(int creatureDamage) {
    this.creatureDamage = creatureDamage;
  }

  public void takeDamage() {
    health -= creatureDamage;
  }

  public boolean isAlive() {
    return (health > 0);
  }

  public boolean isDodgeSuccessful() {
    return RandomHelper.getInt(100) < dodgeChance;
  }

  protected void searchTreasure(GameBoard gameBoard, ConcreteSubject concreteSubject) {
//    List<Treasure> treasures_in_current_room=gameBoard.getRoom(currentRoomId).getTreasures();
    List<Treasure> treasures_to_remove_in_room = new ArrayList<>();
    int treasure_count;
    for(Treasure treasure : gameBoard.getRoom(currentRoomId).getTreasures()){
      treasure_count =  this.treasure_inventory.get(treasure.getName());
      if((Objects.equals(treasure.getName(), "Gem")) || ( treasure_count== 0)){
        concreteSubject.add_event_to_current_turn(EventType.FIND_TREASURE, this.acronym.acronym
                + "-" + treasure.getName());
        treasure.update_adventurer_attributes(this);
        treasures_to_remove_in_room.add(treasure);
        this.treasure_inventory.put(treasure.getName(), treasure_count+1);
        // update treasure bag
        this.treasure_bag = createObject(treasure.getName(), this.treasure_bag);
      }
    }
    for(Treasure treasure:treasures_to_remove_in_room){
      gameBoard.getRoom(currentRoomId).removeTreasure(treasure);
    }
  }

  public int getTreasureCount() {
    return treasureCount;
  }

  public void setBaseTreasureRoll(int baseTreasureRoll) {
    this.baseTreasureRoll = baseTreasureRoll;
  }
  public int getBaseTreasureRoll(){ return this.baseTreasureRoll;}

  public void setBaseCombatRoll(int baseCombatRoll) {
    this.baseCombatRoll = baseCombatRoll;
  }
  public int getBaseCombatRoll(){ return this.baseCombatRoll;}

  public void setDodgeChance(int dodgeChance) {
    this.dodgeChance = dodgeChance;
  }
  public int getDodgeChance(){return this.dodgeChance;}
  public void setbaseCreatureCombatRoll(int roll){
    this.baseCreatureCombatRoll = roll;
  }
  public int getBaseCreatureCombatRoll(){
    return this.baseCreatureCombatRoll;
  }

  public AdventurerAcronym getAcronym() {
    return acronym;
  }
  public Treasure getTreasure_bag(){
    return this.treasure_bag;
  }

  protected abstract void elementalResonance();

  protected abstract void elementalDiscord();

  protected abstract void elementalReset();

  private boolean canUsePortal() {

    //Check if adventurer has Portal treasure and Combat Expertise bonus greater than 1
    boolean isPortalPresent = this.treasure_inventory.get("Portal") > 0;
    boolean isCombatExpertiseBonusGreaterThanOne = this.combatExpertiseBonus > 1;
    boolean isHealthGreaterThanZero = this.health > 0;

    if(isPortalPresent && isCombatExpertiseBonusGreaterThanOne && isHealthGreaterThanZero){
      this.treasure_inventory.put("Portal", this.treasure_inventory.get("Portal")-1);
      return true;
    }
    else{
      return false;
    }
  }


  protected void usePortal(GameBoard gameBoard, ConcreteSubject concreteSubject) {

    // Get the current room
    Room currentRoom = gameBoard.getRoom(currentRoomId);

    // Get all the rooms
    List<Room> allRooms = new ArrayList<>();
    for (Element element : Element.values()) {
      for (int row = 0; row < Constants.VERTICAL_ROOMS; row++) {
        for (int column = 0; column < Constants.HORIZONTAL_ROOMS; column++) {
          String roomID = element.name() + "-" + row + "-" + column;
          if (!roomID.equals(Constants.STARTING_ROOM_ID)) {
            allRooms.add(gameBoard.getRoom(roomID));
          }
        }
      }
    }

    // Get a random room from the list of all rooms
    Room randomRoom = RandomHelper.getRandomElementFromList(allRooms);

    // Get the current element
    Element currentElement = Element.valueOf(currentRoom.getRoomId().split("-")[0]);

    // Get the random element
    Element randomElement = getRandomElement(currentElement);

    // If the random element is the same as the current element, then generate a new random element
    while (randomElement == currentElement) {
      randomElement = Element.values()[RandomHelper.getInt(Element.values().length)];
    }

    //Randomly select a new row and column
    int newRow = RandomHelper.getInt(Constants.VERTICAL_ROOMS - 1);
    int newColumn = RandomHelper.getInt(Constants.HORIZONTAL_ROOMS - 1);

    // Create a new room ID with the new floor, row, and column
    String newRoomID = randomElement.name() + "-" + newRow + "-" + newColumn;


    // Remove and move the adventurer to the new room
    currentRoom.getAdventurers().remove(this);
    currentRoomId = newRoomID;
    randomRoom.addAdventurer(this);
    concreteSubject.add_event_to_current_turn(EventType.Adventurer_enter_room, currentRoomId);
    handleElementalEffects(randomRoom, concreteSubject);

    postMove(gameBoard, concreteSubject);
  }

  private Element getRandomElement(Element currentElement) {
    Element randomElement = currentElement;
    while (randomElement == currentElement) {
      randomElement = Element.values()[RandomHelper.getInt(Element.values().length)];
    }
    return randomElement;
  }
  public Map<String, Integer> getTreasureInventory() {
    return this.treasure_inventory;
  }
}
