package com.arcane.character.creature;

import com.arcane.Element;
import com.arcane.board.GameBoard;

public class TerraVore extends Creature {

  public TerraVore() {
    super(Element.EARTH, CreatureAcronym.TERRAVORE);
  }

  @Override
  protected String getNextRoom(GameBoard gameBoard) {
    return this.getCurrentRoomId();
  }
}
