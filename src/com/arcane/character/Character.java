package com.arcane.character;

import com.arcane.board.Dice;
import com.arcane.board.GameBoard;

public abstract class Character {

  // Initial room
  protected String currentRoomId;

  public void performAction(GameBoard gameBoard){
    if (isFightScenario(gameBoard)) {
      fight(gameBoard);
    } else {
      move(gameBoard);
    }
  }

  protected abstract void move(GameBoard gameBoard);

  protected abstract void fight(GameBoard gameBoard);

  protected abstract void postMove(GameBoard gameBoard);

  public int combatRoll() {
    return Dice.rollDice();
  }

  protected boolean isFightScenario(GameBoard gameBoard) {
    // It is fight scenario if there is at least one creature and at least one adventurer in the room
    return !gameBoard.getRoom(currentRoomId).getAdventurers().isEmpty()
        && !gameBoard.getRoom(currentRoomId).getCreatures().isEmpty();
  }

  public String getCurrentRoomId() {
    return currentRoomId;
  }


  public void setCurrentRoomId(String currentRoomId) {
    this.currentRoomId = currentRoomId;
  }
}
