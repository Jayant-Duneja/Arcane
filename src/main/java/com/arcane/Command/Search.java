package com.arcane.Command;

import com.arcane.Decorator.Treasure;
import com.arcane.Observer.EventType;
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
        int sumOfDice = Dice.rollDice() + this.adventurer.getBaseTreasureRoll() + this.adventurer.getCombatExpertiseBonus();

        // Condition for finding treasure (Finding treasure is now easy!)
        if(sumOfDice >= 7){
            search();
        }
    }

    public void search(){
        int treasureCount = 0;
        List<Treasure> treasuresToRemoveInRoom = new ArrayList<>();

        for(Treasure treasure : this.gameBoard.getRoom(this.adventurer.getCurrentRoomId()).getTreasures()){
            treasureCount = this.adventurer.getTreasureInventory().get(treasure.getName());

            if((Objects.equals(treasure.getName(), "Gem")) || (treasureCount == 0)){

//                Tracker
//                concreteSubject.add_event_to_current_turn(EventType.FIND_TREASURE, this.acronym.acronym + "-" + treasure.getName());
                treasure.update_adventurer_attributes(this.adventurer);
                treasuresToRemoveInRoom.add(treasure);
                this.adventurer.updateTreasureCount(treasure.getName());

                this.adventurer.setTreasureBag(createObject(treasure.getName(), this.adventurer.getTreasureBag()));
            }
        }

        for(Treasure treasure:treasuresToRemoveInRoom){
            gameBoard.getRoom(this.adventurer.getCurrentRoomId()).removeTreasure(treasure);
        }
    }
}
