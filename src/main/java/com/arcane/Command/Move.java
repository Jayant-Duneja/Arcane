package com.arcane.Command;

import com.arcane.Observer.EventType;
import com.arcane.Observer.Subject.ConcreteSubject;
import com.arcane.Observer.Subject.Subject;
import com.arcane.board.GameBoard;
import com.arcane.character.adventurer.Adventurer;
import com.arcane.util.RandomHelper;

import java.util.Scanner;

public class Move implements Command{
    Adventurer adventurer;
    GameBoard gameBoard;
    Subject currentSubject;
    public Move(Adventurer adventurer, GameBoard gameBoard, Subject currentSubject){
        this.adventurer = adventurer;
        this.gameBoard=gameBoard;
        this.currentSubject=currentSubject;
    }
    @Override
    public void execute() {

        // Check if there are creatures in the room, if so, take damage before moving to the next room.
        String roomId = this.adventurer.getCurrentRoomId();

        if (!this.gameBoard.getRoom(roomId).getCreatures().isEmpty()) {
            this.adventurer.takeDamage();
        }
        this.adventurer.move(this.gameBoard, (ConcreteSubject) this.currentSubject);
    }
}
