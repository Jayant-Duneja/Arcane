package com.arcane.board.rooms;

import com.arcane.util.Constants;

public class StartingRoom extends Room {
  public StartingRoom() {
    super(0, 0);
  }

  @Override
  public String getRoomId() {
    return Constants.STARTING_ROOM_ID;
  }
}
