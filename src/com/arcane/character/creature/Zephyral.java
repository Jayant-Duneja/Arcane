package com.arcane.character.creature;

import com.arcane.Element;
import com.arcane.board.GameBoard;

public class Zephyral extends Creature {

  public Zephyral() {
    super(Element.AIR, CreatureAcronym.ZEPHYRAL);
  }

  @Override
  protected String getNextRoom(GameBoard gameBoard) {
    return this.getRandomRoom();
  }
}
