package com.arcane.character.adventurer;

import com.arcane.Element;
import com.arcane.board.Dice;
import com.arcane.board.GameBoard;
import com.arcane.board.rooms.Room;
import com.arcane.character.Character;
import com.arcane.character.creature.Creature;
import com.arcane.util.Constants;
import com.arcane.util.RandomHelper;

import java.util.ArrayList;
import java.util.List;

// Example of Inheritance, Creature is a subclass of Adventurer
public abstract class Adventurer extends Character {
  private final Element resonanceElement;
  private final Element discordElement;
  private final AdventurerAcronym acronym;
  protected int treasureCount = 0;
  private int dodgeChance;
  private int baseTreasureRoll = 0;
  private int baseCombatRoll = 0;
  private int health;
  private int creatureDamage = 2;

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
  }

  @Override
  public int combatRoll() {
    return super.combatRoll() + baseCombatRoll;
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
      searchTreasure();
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
      if (creature.combatRoll() > this.combatRoll()) {
        // if adventurer loses then take damage
        if (!isDodgeSuccessful()) {
          this.takeDamage();
        }
      } else if (creature.combatRoll() < this.combatRoll()) {
        // if creature loses then remove it from current room
        gameBoard.getRoom(this.currentRoomId).removeCreature(creature);
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

  protected void searchTreasure() {
    if (baseTreasureRoll + Dice.rollDice() >= 11) {
      treasureCount += 1;
    }
  }

  public int getTreasureCount() {
    return treasureCount;
  }

  public void setBaseTreasureRoll(int baseTreasureRoll) {
    this.baseTreasureRoll = baseTreasureRoll;
  }

  public void setBaseCombatRoll(int baseCombatRoll) {
    this.baseCombatRoll = baseCombatRoll;
  }

  public void setDodgeChance(int dodgeChance) {
    this.dodgeChance = dodgeChance;
  }

  public AdventurerAcronym getAcronym() {
    return acronym;
  }

  protected abstract void elementalResonance();

  protected abstract void elementalDiscord();

  protected abstract void elementalReset();
}
