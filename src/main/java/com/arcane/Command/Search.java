package com.arcane.Command;

import com.arcane.Decorator.Treasure;
import com.arcane.Observer.EventType;
import com.arcane.Observer.Subject.ConcreteSubject;
import com.arcane.Observer.Subject.Subject;
import com.arcane.board.Dice;
import com.arcane.board.GameBoard;
import com.arcane.character.adventurer.Adventurer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.arcane.Decorator.Treasure_Factory.createObject;

public class Search implements Command{
    Adventurer adventurer;
    GameBoard gameBoard;
    Subject currentSubject;
    public Search(Adventurer adventurer, GameBoard gameBoard, Subject currentSubject){
        this.adventurer = adventurer;
        this.gameBoard=gameBoard;
        this.currentSubject=currentSubject;
    }
    @Override
    public void execute() {
        this.adventurer.searchTreasure(this.gameBoard, (ConcreteSubject) this.currentSubject);
    }
}
