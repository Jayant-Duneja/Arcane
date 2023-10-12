package com.arcane.character.creature;

import com.arcane.Element;
import com.arcane.Observer.EventType;
import com.arcane.Observer.Subject.ConcreteSubject;
import com.arcane.board.GameBoard;
import com.arcane.character.Character;
import com.arcane.character.adventurer.Adventurer;
import com.arcane.util.Constants;
import com.arcane.util.RandomHelper;

// Example of Inheritance, Creature is a subclass of Character
public abstract class Creature extends Character {

  protected final Element element;

  private final CreatureAcronym acronym;

  protected Creature(Element element, CreatureAcronym acronym) {
    this.element = element;
    this.acronym = acronym;
    this.currentRoomId = getInitialRoom();
  }

  protected String getInitialRoom() {
    return this.getRandomRoom();
  }

  protected String getRandomRoom() {
    int row = RandomHelper.getInt(Constants.VERTICAL_ROOMS - 1);
    int column = RandomHelper.getInt(Constants.HORIZONTAL_ROOMS - 1);
    return this.element.name() + "-" + row + "-" + column;
  }

  protected abstract String getNextRoom(GameBoard gameBoard);

  public CreatureAcronym getAcronym() {
    return acronym;
  }

  @Override
  protected void fight(GameBoard gameBoard, ConcreteSubject concreteSubject) {
    fightAdventures(gameBoard, concreteSubject);
  }

  @Override
  protected void move(GameBoard gameBoard, ConcreteSubject concreteSubject) {
    gameBoard.getRoom(currentRoomId).getCreatures().remove(this);
    currentRoomId = getNextRoom(gameBoard);
    concreteSubject.add_event_to_current_turn(EventType.Creature_enter_room, this.acronym.acronym, currentRoomId);
    gameBoard.getRoom(currentRoomId).addCreature(this);
    postMove(gameBoard, concreteSubject);
  }

  @Override
  protected void postMove(GameBoard gameBoard, ConcreteSubject concreteSubject) {
    if (isFightScenario(gameBoard)) {
      fightAdventures(gameBoard, concreteSubject);
    }
  }

  protected void fightAdventures(GameBoard gameBoard, ConcreteSubject concreteSubject) {
    for (Adventurer adventurer : gameBoard.getRoom(this.currentRoomId).getAdventurers()) {
      // Fight if adventurer is alive
      if (adventurer.isAlive()) {
        if (this.combatRoll() > adventurer.combatRoll()) {
          if (!adventurer.isDodgeSuccessful()) {
            concreteSubject.add_event_to_current_turn(EventType.LOSE_HEALTH, adventurer.getAcronym().acronym + "-" + adventurer.getCreatureDamage());
            adventurer.takeDamage();
            if(!adventurer.isAlive()){
              concreteSubject.add_event_to_current_turn(EventType.DEFEAT, adventurer.getAcronym().acronym);
            }
          }
        } else if (adventurer.combatRoll() > this.combatRoll()) {
          // if creature loses then remove from the current room and end fighting
          gameBoard.getRoom(this.currentRoomId).removeCreature(this);
          concreteSubject.add_event_to_current_turn(EventType.WIN_COMBAT, adventurer.getAcronym().acronym);
          concreteSubject.add_event_to_current_turn(EventType.LOSE_COMBAT, this.getAcronym().acronym);
          concreteSubject.add_event_to_current_turn(EventType.DEFEAT, this.getAcronym().acronym);
          break;
        }
      }
    }
  }
}
