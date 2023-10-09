package com.arcane.board.rooms;

import com.arcane.character.adventurer.Adventurer;
import com.arcane.character.creature.Creature;
import java.util.ArrayList;
import java.util.List;

public abstract class Room {
  protected final int row;
  protected final int column;
  protected final List<Adventurer> adventurers;
  protected final List<Creature> creatures;
  protected List<Room> connectedRooms;

  protected Room(int row, int column) {
    this.row = row;
    this.column = column;
    this.adventurers = new ArrayList<>();
    this.creatures = new ArrayList<>();
  }

  public List<Room> getConnectedRooms() {
    return connectedRooms;
  }

  public void setConnectedRooms(List<Room> connectedRooms) {
    this.connectedRooms = connectedRooms;
  }

  public List<Adventurer> getAdventurers() {
    return adventurers;
  }

  public void addAdventurer(Adventurer adventurer) {
    adventurer.setCurrentRoomId(this.getRoomId());
    this.getAdventurers().add(adventurer);
  }

  public List<Creature> getCreatures() {
    return creatures;
  }

  public void addCreature(Creature creature) {
    creature.setCurrentRoomId(this.getRoomId());
    this.getCreatures().add(creature);
  }

  public void removeCreature(Creature creature) {
    this.getCreatures().remove(creature);
  }

  public String getCurrentPosition() {
    return this.row + "-" + this.column;
  }

  public abstract String getRoomId();
}
