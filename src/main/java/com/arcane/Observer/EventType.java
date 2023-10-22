package com.arcane.Observer;

public enum EventType {
    Adventurer_enter_room("Adventurer entered room: "),
    Creature_enter_room("Creature entered room: "),
    WIN_COMBAT("Fight Occurred. Winner was: "),
    LOSE_COMBAT("Fight Occurred. Loser was: "),
    GAIN_EXPERTISE("Expertise Gained by Adventurer: "),
    GAIN_ELEMENTAL_DISCORD("Adventurer gained Discord: "),
    GAIN_ELEMENTAL_RESONANCE("Adventurer gained Resonance: "),
    LOSE_HEALTH("Adventurer Lost Health: "),
    DEFEAT("Entity was removed: "),
    FIND_TREASURE("Adventurer found Treasure: ");

    EventType(String event_string) {

    }
}