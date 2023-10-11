package com.arcane.character.adventurer;

import com.arcane.Decorator.Treasure;
import com.arcane.Decorator.Treasure_Bag;
import com.arcane.Decorator.Treasure_Decorator;
import com.arcane.Element;
import com.arcane.board.Dice;
import com.arcane.board.GameBoard;
import com.arcane.board.rooms.Room;
import com.arcane.character.Character;
import com.arcane.character.creature.Creature;
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
  private int combatExpertiseBonus;
  private int searchExpertiseBonus;

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
    this.combatExpertiseBonus = 0; // start from novice
    this.searchExpertiseBonus = 0; // start from novice
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
  public void performAction(GameBoard gameBoard) {
    // Handle Elemental effects to the adventurer stats
    handleElementalEffects(gameBoard.getRoom(currentRoomId));
    super.performAction(gameBoard);
  }

  @Override
  protected void fight(GameBoard gameBoard) {
    fightCreatures(gameBoard);
  }

  @Override
  protected void move(GameBoard gameBoard) {
    // Remove adventurer from current room
    gameBoard.getRoom(currentRoomId).getAdventurers().remove(this);
    // Move adventurer to a random valid room
    currentRoomId =
        RandomHelper.getRandomElementFromList(gameBoard.getRoom(currentRoomId).getConnectedRooms())
            .getRoomId();
    // Add adventurer to the new room
    gameBoard.getRoom(currentRoomId).addAdventurer(this);
    // Handle Elemental effects to the adventurer stats
    handleElementalEffects(gameBoard.getRoom(currentRoomId));
    // Perform post move action
    postMove(gameBoard);
  }

  @Override
  protected void postMove(GameBoard board) {
    if (isFightScenario(board)) {
      fightCreatures(board);
    } else {
      searchTreasure(board);
    }
  }

  protected void fightCreatures(GameBoard gameBoard) {
    // if adventurer is alive then fight
    if (this.isAlive()) {
      // Get the creatures present in the current room
      List<Creature> creatures =
          new ArrayList<>(gameBoard.getRoom(this.currentRoomId).getCreatures());
      // For all adventurer vs creatures, check dice roll
      for (Creature creature : creatures) {
        // if adventurer is alive then fight
        fightCreature(creature, gameBoard);
      }
    }
  }

  private void fightCreature(Creature creature, GameBoard gameBoard) {
    if (this.isAlive()) {
        int creatureRoll = this.creatureFinalRoll(creature);
      if (creatureRoll > this.combatRoll()) {
        // if adventurer loses then take damage
        if (!isDodgeSuccessful()) {
          this.takeDamage();
        }
      } else if (creatureRoll < this.combatRoll()) {
        // if creature loses then remove it from current room
        gameBoard.getRoom(this.currentRoomId).removeCreature(creature);

        // In case of adventurer victory, increase the combatExperience
        this.combatExpertiseBonus++;
        setCombatExpertiseBonus(this.combatExpertiseBonus);
      }
    }
  }

  private void handleElementalEffects(Room room) {
    this.elementalReset();
    if (!room.getRoomId().equals(Constants.STARTING_ROOM_ID)) {
      Element element = Element.valueOf(room.getRoomId().split("-")[0]);
      if (element == this.resonanceElement) {
        this.elementalResonance();
      } else if (element == this.discordElement) {
        this.elementalDiscord();
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

  protected void searchTreasure(GameBoard gameBoard) {
//    List<Treasure> treasures_in_current_room=gameBoard.getRoom(currentRoomId).getTreasures();
    List<Treasure> treasures_to_remove_in_room = new ArrayList<>();
    int treasure_count;
    for(Treasure treasure : gameBoard.getRoom(currentRoomId).getTreasures()){
      treasure_count =  this.treasure_inventory.get(treasure.getName());
      if((Objects.equals(treasure.getName(), "Gem")) || ( treasure_count== 0)){
        treasure.update_adventurer_attributes(this);
        treasures_to_remove_in_room.add(treasure);
        this.treasure_inventory.put(treasure.getName(), treasure_count+1);
        // update treasure bag
        this.treasure_bag = createObject(treasure.getName(), this.treasure_bag);
      }
    }
    System.out.println("List before: " + gameBoard.getRoom(currentRoomId).getTreasures());
    for(Treasure treasure:treasures_to_remove_in_room){
      gameBoard.getRoom(currentRoomId).removeTreasure(treasure);
    }
    System.out.println("List after: " + gameBoard.getRoom(currentRoomId).getTreasures());
    System.out.println("------------------------------------------------------------------------");
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

  public void setCombatExpertiseBonus(int bonus){
    this.combatExpertiseBonus = bonus;
  }

  public void setSearchExpertiseBonus(int bonus){
    this.searchExpertiseBonus = bonus;
  }

}
