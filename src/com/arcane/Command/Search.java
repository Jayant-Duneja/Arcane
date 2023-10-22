package com.arcane.Command;

import com.arcane.Observer.Subject.Subject;
import com.arcane.board.GameBoard;
import com.arcane.character.adventurer.Adventurer;

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

    }
}
