package com.arcane.character.adventurer;

import com.arcane.Element;

public class MistWalker extends Adventurer {

  private static final int DODGE_CHANCE = 50;

  public MistWalker() {
    super(3, DODGE_CHANCE, Element.WATER, Element.EARTH, AdventurerAcronym.MIST_WALKER);
  }

  @Override
  protected void elementalResonance() {
    this.setDodgeChance(DODGE_CHANCE + 25);
  }

  @Override
  protected void elementalDiscord() {
    this.setDodgeChance(DODGE_CHANCE - 25);
  }

  @Override
  protected void elementalReset() {
    this.setDodgeChance(DODGE_CHANCE);
  }
}
