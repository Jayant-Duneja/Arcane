package com.arcane.character.adventurer;

import com.arcane.Element;

public class TerraVoyager extends Adventurer {
  private int bonusHealth = 0;

  public TerraVoyager() {
    super(7, 10, Element.EARTH, Element.FIRE, AdventurerAcronym.TERRA_VOYAGER);
  }

  @Override
  protected void elementalResonance() {
    this.bonusHealth = 3;
  }

  @Override
  protected void elementalDiscord() {
    this.bonusHealth = -3;
  }

  @Override
  protected void elementalReset() {
    this.bonusHealth = 0;
  }

  @Override
  public boolean isAlive() {
    return (bonusHealth + this.getHealth() > 0);
  }

  @Override
  public void takeDamage() {
    if (bonusHealth > 0) {
      bonusHealth--;
    } else {
      super.takeDamage();
    }
  }

  @Override
  public int getHealth() {
    return Math.max(super.getHealth() + bonusHealth, 0);
  }
}
