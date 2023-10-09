package com.arcane.util;

import java.util.Map;

public class Constants {
  public static final int TREASURE_COUNT_FOR_VICTORY = 50;
  public static final int VERTICAL_ROOMS = 3;
  public static final int HORIZONTAL_ROOMS = 3;
  public static final String ALL_CREATURES_KILLED =
      "Game Over: All creatures have been defeated. Adventurers Win!!!";
  public static final String ALL_ADVENTURERS_KILLED =
      "Game Over: All adventurers have been killed. Adventurers Lose :(";
  public static final String ALL_TREASURES_FOUND =
      "Game Over: Adventurers have found "
          + TREASURE_COUNT_FOR_VICTORY
          + " treasures. Adventurers Win!!!";

  public static final String STARTING_ROOM_ID = "SR";

  // FireBorn's possible directions map
  public static final Map<String, String[]> FIRE_BORN_ROOM_DIRECTIONAL_MAP =
      Map.of(
          "0-0", new String[] {"1-0", "0-1"},
          "0-1", new String[] {"0-0", "0-2"},
          "0-2", new String[] {"0-1", "1-2"},
          "1-2", new String[] {"0-2", "2-2"},
          "2-2", new String[] {"1-2", "2-1"},
          "2-1", new String[] {"2-2", "2-0"},
          "2-0", new String[] {"2-1", "1-0"},
          "1-0", new String[] {"2-0", "0-0"});

  private Constants() {}
}
