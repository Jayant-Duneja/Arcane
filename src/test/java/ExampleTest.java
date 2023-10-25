import com.arcane.Element;
import com.arcane.Observer.Subject.ConcreteSubject;
import com.arcane.board.GameBoard;
import com.arcane.board.rooms.ElementalRoom;
import com.arcane.board.rooms.Room;
import com.arcane.character.adventurer.Adventurer;
import com.arcane.character.adventurer.EmberKnight;
import com.arcane.character.adventurer.MistWalker;
import com.arcane.character.creature.Creature;
import com.arcane.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ExampleTest {
    @Test
    void testEmberKnightResonance(){
        EmberKnight testEmberKnight = new EmberKnight("");
        Room room= new ElementalRoom(Element.FIRE, 1, 1);
        ConcreteSubject testSubject = new ConcreteSubject();
        testEmberKnight.handleElementalEffects(room, testSubject);
        Assertions.assertEquals(testEmberKnight.getBaseCombatRoll(), 2);
    }
    @Test
    void testEmberKnightDiscord(){
        EmberKnight testEmberKnight = new EmberKnight("");
        Room room= new ElementalRoom(Element.WATER, 1, 1);
        ConcreteSubject testSubject = new ConcreteSubject();
        testEmberKnight.handleElementalEffects(room, testSubject);
        Assertions.assertEquals(testEmberKnight.getBaseCombatRoll(), -2);
    }
    @Test
    void testEmberKnightExpertise(){
        EmberKnight testEmberKnight = new EmberKnight("");
        testEmberKnight.UpdateExpertise(1);
        Assertions.assertEquals(testEmberKnight.getCombatExpertiseBonus(), 2);
    }
    @Test
    void testMistWalkerResonance(){
        MistWalker testMistWalker = new MistWalker("");
        Room room = new ElementalRoom(Element.WATER, 1, 1);
        ConcreteSubject testSubject = new ConcreteSubject();
        float prevDodgeChance = testMistWalker.getDodgeChance();
        testMistWalker.handleElementalEffects(room, testSubject);
        Assertions.assertEquals(testMistWalker.getDodgeChance() - prevDodgeChance, 25);
    }
    @Test
    void testMistWalkerDiscord(){
        MistWalker testMistWalker = new MistWalker("");
        Room room = new ElementalRoom(Element.EARTH, 1, 1);
        ConcreteSubject testSubject = new ConcreteSubject();
        float prevDodgeChance = testMistWalker.getDodgeChance();
        testMistWalker.handleElementalEffects(room, testSubject);
        Assertions.assertEquals(testMistWalker.getDodgeChance() - prevDodgeChance, -25);
    }
    @Test
    void testGameBoardAdventurers(){
        GameBoard gameBoard = new GameBoard("EK", "Test_Adventurer", "");
        List<Adventurer> current_adventurers = gameBoard.getRoom(Constants.STARTING_ROOM_ID).getAdventurers();
        Assertions.assertEquals(current_adventurers.size(), 1);
        Assertions.assertEquals(current_adventurers.get(0).getAcronym().acronym, "EK");
        Assertions.assertEquals(current_adventurers.get(0).getDisplayName(), "Test_Adventurer");
    }
    @Test
    void testGameBoardCreaturesEmpty(){
        GameBoard gameBoard = new GameBoard("EK", "Test_Adventurer", "");
        List<Creature> current_creatures = gameBoard.getRemainingCreatures();
        Assertions.assertEquals(current_creatures.size(), 0);
    }
    @Test
    void testGameBoardCreaturesNonEmpty(){
        GameBoard gameBoard = new GameBoard("EK", "Test_Adventurer", "A,F,T,Z");
        List<Creature> current_creatures = gameBoard.getRemainingCreatures();
        Assertions.assertEquals(current_creatures.size(), 4);
    }
    @Test
    void RoomConnectionsStartingRoom(){
        GameBoard gameBoard = new GameBoard("EK", "Test_Adventurer", "A,F,T,Z");
        List<Room> connectedRooms = gameBoard.getRoom(Constants.STARTING_ROOM_ID).getConnectedRooms();
        Assertions.assertEquals(connectedRooms.size(), 4);
    }
    @Test
    void RoomConnectionsRandomFloor(){
        GameBoard gameBoard = new GameBoard("EK", "Test_Adventurer", "A,F,T,Z");
        Room room = new ElementalRoom(Element.EARTH, 1, 1);
        List<Room> connectedRooms = gameBoard.getRoom(room.getRoomId()).getConnectedRooms();
        Assertions.assertEquals(connectedRooms.size(), 5);
    }






}
