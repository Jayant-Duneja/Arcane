package com.arcane.Command;

import com.arcane.Observer.EventType;
import com.arcane.Observer.Subject.ConcreteSubject;
import com.arcane.Observer.Subject.Subject;
import com.arcane.board.GameBoard;
import com.arcane.character.adventurer.Adventurer;
import com.arcane.util.RandomHelper;

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

        if(this.adventurer.canUsePortal()){
            this.adventurer.usePortal(this.gameBoard, (ConcreteSubject) this.currentSubject);
        }
        else{
            this.gameBoard.getRoom(this.adventurer.getCurrentRoomId()).getAdventurers().remove(this.adventurer);

            // Move the adventurer to the next room
            String currentRoomId = RandomHelper.getRandomElementFromList(gameBoard.getRoom(this.adventurer.getCurrentRoomId()).getConnectedRooms()).getRoomId();

            //  Tracker
            //  concreteSubject.add_event_to_current_turn(EventType.Adventurer_enter_room, this.acronym.acronym, currentRoomId);

            // Add adventurer to new room
            this.gameBoard.getRoom(currentRoomId).addAdventurer(this.adventurer);
            this.adventurer.handleElementalEffects(this.gameBoard.getRoom(currentRoomId), (ConcreteSubject) this.currentSubject);

            // TODO : Do I need postMove() here?

        }
    }
}
