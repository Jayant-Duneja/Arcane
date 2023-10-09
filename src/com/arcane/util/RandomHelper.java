package com.arcane.util;

import java.util.List;
import java.util.Random;

// This Class is highly cohesive as it contains all functionality related to generating random data
public class RandomHelper {

  private static Random random;

  private RandomHelper() {}

  // Return random elements from a list
  public static <T> T getRandomElementFromList(List<T> list) {
    random = new Random();
    return list.get(random.nextInt(list.size()));
  }

  public static Boolean getBoolean() {
    random = new Random();
    return random.nextBoolean();
  }

  public static int getInt(int range) {
    random = new Random();
    return random.nextInt(range);
  }
}
