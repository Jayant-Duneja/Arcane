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

  public void performTurn(GameBoard gameBoard, ConcreteSubject concreteSubject){
    if (!isFightScenario(gameBoard)) {
      move(gameBoard, concreteSubject);
    }
  }

  @Override
  public void move(GameBoard gameBoard, ConcreteSubject concreteSubject) {
    gameBoard.getRoom(currentRoomId).getCreatures().remove(this);
    currentRoomId = getNextRoom(gameBoard);
    gameBoard.getRoom(currentRoomId).addCreature(this);
    concreteSubject.add_event_to_current_turn(EventType.Creature_enter_room, this.acronym.acronym, currentRoomId,gameBoard);
  }
  public String getCurrentRoomId(){return this.currentRoomId;}
}
