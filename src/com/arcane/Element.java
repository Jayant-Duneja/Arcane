package com.arcane;

public enum Element {
  FIRE("fire"),
  WATER("water"),
  EARTH("earth"),
  AIR("air");

  private final String type;

  Element(String type) {
    this.type = type;
  }
}
