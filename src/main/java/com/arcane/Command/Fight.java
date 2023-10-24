package com.arcane.Command;

import com.arcane.Observer.Subject.Subject;
import com.arcane.board.GameBoard;
import com.arcane.board.rooms.Room;
import com.arcane.character.adventurer.Adventurer;
import com.arcane.character.creature.Creature;

import java.util.List;

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
        Room currentRoom = gameBoard.getRoom(adventurer.getCurrentRoomId());

        // If there are creatures in the room and the adventurer is alive, fight!
        if(!currentRoom.getCreatures().isEmpty() && adventurer.isAlive()){
            List<Creature> creatures = currentRoom.getCreatures();

            for(Creature creature : creatures){
                fight(creature);
            }
        }
    }

    private void fight(Creature creature){
        int creatureRoll = this.adventurer.creatureFinalRoll(creature);

        if(creatureRoll > this.adventurer.combatRoll()){
            //Adventurer takes the damage
            if(!this.adventurer.isDodgeSuccessful()){
                this.adventurer.takeDamage();

                if(!this.adventurer.isAlive()){
                    //Adventurer dies
                    System.out.println(this.adventurer.getName() + " has died!");

//                  Tracker
//                  concreteSubject.add_event_to_current_turn(EventType.LOSE_HEALTH, this.acronym.acronym + "-" + this.creatureDamage);
                }
            }
        }
        else{
            //Adventurer wins
            this.gameBoard.getRoom(this.adventurer.getCurrentRoomId()).removeCreature(creature);
            this.adventurer.UpdateExpertise(this.adventurer.getCombatExpertiseBonus());

//            Tracker
//            this.currentSubject.concreteSubject.add_event_to_current_turn(EventType.WIN_COMBAT, this.acronym.acronym);
//            this.currentSubject.concreteSubject.add_event_to_current_turn(EventType.LOSE_COMBAT, creature.getAcronym().acronym);
//            this.currentSubject.concreteSubject.add_event_to_current_turn(EventType.DEFEAT, creature.getAcronym().acronym);
        }


    }
}
