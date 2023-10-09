package com.arcane.board;

import com.arcane.util.RandomHelper;
import java.util.Arrays;
import java.util.List;

// This Class is highly cohesive as it contains functionality related only to rolling dice
public class Dice {

  private static final List<Integer> validDiceRoll = Arrays.asList(1, 2, 3, 4, 5, 6);

  private Dice() {}

  public static int rollDice() {
    return RandomHelper.getRandomElementFromList(validDiceRoll)
        + RandomHelper.getRandomElementFromList(validDiceRoll);
  }
}
