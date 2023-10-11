package com.arcane.character.adventurer;

import com.arcane.Element;

public class TerraVoyager extends Adventurer {

  private final int normalCreatureDamage;

  public TerraVoyager() {
    super(7, 10, Element.EARTH, Element.FIRE, AdventurerAcronym.TERRA_VOYAGER);
    normalCreatureDamage = this.getCreatureDamage();
  }

  @Override
  protected void elementalResonance() {
    setCreatureDamage(normalCreatureDamage - 1);
  }

  @Override
  protected void elementalDiscord() {
    setCreatureDamage(normalCreatureDamage + 1);
  }

  @Override
  protected void elementalReset() {
    setCreatureDamage(normalCreatureDamage);
  }
}
