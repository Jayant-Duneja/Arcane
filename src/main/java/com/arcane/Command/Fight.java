package com.arcane.Command;

import com.arcane.Observer.Subject.ConcreteSubject;
import com.arcane.Observer.Subject.Subject;
import com.arcane.board.GameBoard;
import com.arcane.character.adventurer.Adventurer;

public class Fight implements Command{
    Adventurer adventurer;
    GameBoard gameBoard;
    Subject currentSubject;
    public Fight(Adventurer adventurer, GameBoard gameBoard, Subject currentSubject){
        this.adventurer = adventurer;
        this.gameBoard=gameBoard;
        this.currentSubject=currentSubject;
    }
    @Override
    public void execute() {
        this.adventurer.fightCreatures(this.gameBoard, (ConcreteSubject) this.currentSubject);
    }
}
