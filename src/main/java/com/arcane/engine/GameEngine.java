package com.arcane.engine;

import com.arcane.Command.*;
import com.arcane.Observer.Observer.Logger;
import com.arcane.Observer.Observer.Tracker;
import com.arcane.Observer.Subject.ConcreteSubject;
import com.arcane.board.GameBoard;
import com.arcane.character.adventurer.Adventurer;
import com.arcane.character.creature.Creature;
import com.arcane.util.Constants;
import java.util.ArrayList;
import java.util.List;

public class GameEngine {
  private List<Adventurer> adventurers;
  private GameBoard gameBoard;
  private int turn;
  private ConcreteSubject currentSubject;
  private Logger logger;
  private Tracker tracker;

  private List<Creature> creatures;
  Invoker invoker;

  // Game initialization - create new board, set turn to 0, populate adventures and creatures
  public void initialiseGame(String adventurerType, String adventurerCustomName, String creatures) {
    gameBoard = new GameBoard(adventurerType, adventurerCustomName, creatures);
    adventurers = new ArrayList<>();
    this.turn = 0;
    this.adventurers.addAll(gameBoard.getRoom(Constants.STARTING_ROOM_ID).getAdventurers());
    this.creatures = gameBoard.getRemainingCreatures();
    currentSubject = new ConcreteSubject();
    logger = Logger.getInstance();
    tracker = Tracker.getInstance();
    tracker.initialize(this.adventurers, this.creatures);
    currentSubject.register_observer(tracker);
    invoker = new Invoker();
  }

  public String simulateTurn(Boolean shouldPrint, String commandName){
    printGame(shouldPrint);
    // Run the game until game over condition is achieved
    currentSubject.clear_previous_turn_events();
    currentSubject.remove_observer(logger);
    logger.instantiate(turn);
    currentSubject.register_observer(logger);
    performCommand(commandName);
    performCreatureTurn();
    printGame(shouldPrint);
    currentSubject.notify_all_observers();
    // If game ends print results
    return printGameResults();
  }

//  public String simulateGame(Boolean shouldPrint, String commandName) {
//    printGame(shouldPrint);
//    // Run the game until game over condition is achieved
//    while (!isGameOver()) {
//      currentSubject.clear_previous_turn_events();
//      currentSubject.remove_observer(logger);
//      logger.instantiate(turn);
//      currentSubject.register_observer(logger);
//      // perform a turn and then print board
////      performTurn();
//      performCommand(commandName);
//      printGame(shouldPrint);
//      currentSubject.notify_all_observers();
//    }
//    // If game ends print results
//    return printGameResults();
//  }

  private void printGame(boolean shouldPrint) {
    if (shouldPrint) {
      System.out.println("------------------------------------------------------Turn-" + turn + "----------------------------------------------------------------");
      gameBoard.renderBoard();
      printAdventurersStatus();
      printCreaturesStatus();
      turn++;
      System.out.println();
    }
  }

  // Helper method to print creature status
  private void printCreaturesStatus() {
    int fireBornCount = 0;
    int aquaridCount = 0;
    int terraVoreCount = 0;
    int zephyralCount = 0;
    this.creatures = gameBoard.getRemainingCreatures();
    for (Creature creature : creatures) {
      switch (creature.getAcronym()) {
        case FIREBORN -> fireBornCount++;
        case TERRAVORE -> terraVoreCount++;
        case AQUARID -> aquaridCount++;
        case ZEPHYRAL -> zephyralCount++;
      }
    }
    System.out.printf("FireBorns - %d Remaining%n", fireBornCount);
    System.out.printf("TerraVores - %d Remaining%n", terraVoreCount);
    System.out.printf("Aquarids - %d Remaining%n", aquaridCount);
    System.out.printf("Zephyrals - %d Remaining%n%n", zephyralCount);
  }

  // Helper method to print adventurers status
  private void printAdventurersStatus() {
    for (Adventurer adventurer : adventurers) {
      System.out.println(
              adventurer.getClass().getSimpleName()
                      + " ||||||||"
                      + " Treasure(s) - "
                      + adventurer.getTreasure_bag().get_treasures()
                      + " ||||||||"
                      + " Treasure Value - "
                      + adventurer.getTreasure_bag().get_value()
                      + " ||||||||"
                      + " Health Remaining- "
                      + (adventurer.getHealth())
                      );
    }
    System.out.println();
  }

  // Step1: Perform action for all adventurers while checking if game over is achieved
  // Step2: Perform action for all creatures while checking if game over is achieved
//  public void performTurn() {
//    for (Adventurer adventurer : adventurers) {
//      adventurer.performAction(gameBoard, currentSubject);
//      creatures = gameBoard.getRemainingCreatures();
//      if (isGameOver()) {
//        break;
//      }
//    }
//
//    for (Creature creature : creatures) {
//      creature.performAction(gameBoard, currentSubject);
//      if (isGameOver()) {
//        break;
//      }
//    }
//  }
  public void performCommand(String CommandName) {
    if (CommandName.contains("Move")) {
      invoker.setCommand(new Move(this.adventurers.get(0), gameBoard, currentSubject, CommandName.split(":")[1]));
    } else if (CommandName.contains("Search")) {
      invoker.setCommand(new Search(this.adventurers.get(0), gameBoard, currentSubject));
    } else if (CommandName.contains("Fight")) {
      invoker.setCommand(new Fight(this.adventurers.get(0), gameBoard, currentSubject));
    } else if (CommandName.contains("Exit")) {
      invoker.setCommand(new Exit(this.adventurers.get(0), gameBoard, currentSubject));
    } else {
      // Handle the case where none of the substrings are found
      // You may want to provide a default action or an error message.
      System.out.println("Please provide a valid command.");
    }

    invoker.performCommand();
  }
    public void performCreatureTurn() {
    for (Creature creature : creatures) {
//      creature.performAction(gameBoard, currentSubject);

//      creature.move(gameBoard, currentSubject);
      creature.performTurn(gameBoard, currentSubject);
      if (isGameOver()) {
        break;
      }
    }
  }

  public boolean isGameOver() {
    return creatures.isEmpty()
            || gameBoard.areAllAdventuresDead(adventurers)
            || gameBoard.getTotalTreasureCount(adventurers) >= Constants.TREASURE_COUNT_FOR_VICTORY
            || gameBoard.areAllTreasureTypeFound(adventurers)
            || gameBoard.treasureValue(this.adventurers) >= Constants.TREASURE_VALUE_FOR_VICTORY;
  }

  public String printGameResults() {
    // Game ends if all creatures die
    if (creatures.isEmpty()) {
      System.out.println(Constants.ALL_CREATURES_KILLED);
      return Constants.ALL_CREATURES_KILLED;
      // Game ends if all adventurers are dead
    } else if (gameBoard.areAllAdventuresDead(adventurers)) {
      System.out.println(Constants.ALL_ADVENTURERS_KILLED);
      return Constants.ALL_ADVENTURERS_KILLED;
      // Game ends If 10 treasures are found
    } else if (gameBoard.getTotalTreasureCount(adventurers) >= Constants.TREASURE_COUNT_FOR_VICTORY) {
      System.out.println(Constants.ALL_TREASURES_FOUND);
      return Constants.ALL_TREASURES_FOUND;
    }
    return "";
  }
}
