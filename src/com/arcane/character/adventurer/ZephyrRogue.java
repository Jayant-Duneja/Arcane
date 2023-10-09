package com.arcane.character.adventurer;

import com.arcane.Element;

public class ZephyrRogue extends Adventurer {
  public ZephyrRogue() {
    super(3, 25, Element.AIR, Element.EARTH, AdventurerAcronym.ZEPHYR_ROGUE);
  }

  @Override
  protected void elementalResonance() {
    this.setBaseTreasureRoll(2);
  }

  @Override
  protected void elementalDiscord() {
    this.setBaseTreasureRoll(-2);
  }

  @Override
  protected void elementalReset() {
    this.setBaseTreasureRoll(0);
  }
}
