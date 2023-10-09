package com.arcane.character.creature;

import com.arcane.Element;
import com.arcane.board.GameBoard;
import com.arcane.util.Constants;
import com.arcane.util.RandomHelper;
import java.util.ArrayList;

public class FireBorn extends Creature {

  private final boolean clockwise;

  public FireBorn() {
    super(Element.FIRE, CreatureAcronym.FIREBORN);
    this.clockwise = RandomHelper.getBoolean();
  }

  @Override
  protected String getNextRoom(GameBoard gameBoard) {
    String currentPosition = gameBoard.getRoom(this.currentRoomId).getCurrentPosition();
    return this.element.name()
        + "-"
        + Constants.FIRE_BORN_ROOM_DIRECTIONAL_MAP.get(currentPosition)[clockwise ? 1 : 0];
  }

  @Override
  protected String getInitialRoom() {
    // the keys of FIRE_BORN_ROOM_DIRECTIONAL_MAP contains a set of outer rooms
    // with the help of the keys list, we pick a random pair for row and column as the initial
    // position
    String currentPosition =
        RandomHelper.getRandomElementFromList(
            new ArrayList<>(Constants.FIRE_BORN_ROOM_DIRECTIONAL_MAP.keySet()));
    return this.element.name() + "-" + currentPosition;
  }
}
