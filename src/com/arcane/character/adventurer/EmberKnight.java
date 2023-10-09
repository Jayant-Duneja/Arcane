package com.arcane.character.adventurer;

import com.arcane.Element;

public class EmberKnight extends Adventurer {

  public EmberKnight() {
    super(5, 20, Element.FIRE, Element.WATER, AdventurerAcronym.EMBER_KNIGHT);
  }

  @Override
  protected void elementalResonance() {
    this.setBaseCombatRoll(2);
  }

  @Override
  protected void elementalDiscord() {
    this.setBaseCombatRoll(-2);
  }

  @Override
  protected void elementalReset() {
    this.setBaseCombatRoll(0);
  }
}
