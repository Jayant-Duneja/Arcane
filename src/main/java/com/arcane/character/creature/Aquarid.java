package com.arcane.character.creature;

import com.arcane.Element;
import com.arcane.board.GameBoard;
import com.arcane.board.rooms.Room;

public class Aquarid extends Creature {

  public Aquarid() {
    super(Element.WATER, CreatureAcronym.AQUARID);
  }

  @Override
  protected String getNextRoom(GameBoard gameBoard) {
    // First room in the connected rooms is given preference if multiple adjacent rooms have
    // adventures.
    for (Room room : gameBoard.getRoom(currentRoomId).getConnectedRooms()) {
      if (!room.getAdventurers().isEmpty()) {
        return room.getRoomId();
      }
    }
    return currentRoomId;
  }
}
