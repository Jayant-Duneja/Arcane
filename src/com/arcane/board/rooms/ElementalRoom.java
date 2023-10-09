package com.arcane.board.rooms;

import com.arcane.Element;


public class ElementalRoom extends Room {

  private final Element element;

  public ElementalRoom(Element element, int row, int column) {
    super(row, column);
    this.element = element;
  }

  @Override
  public String getRoomId() {
    return element + "-" + row + "-" + column;
  }
}
