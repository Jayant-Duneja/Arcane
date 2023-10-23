import com.arcane.Element;
import com.arcane.Observer.Subject.ConcreteSubject;
import com.arcane.board.rooms.ElementalRoom;
import com.arcane.board.rooms.Room;
import com.arcane.character.adventurer.EmberKnight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExampleTest {
    @Test
    void showSimpleAssertion(){
        Assertions.assertEquals(1,1);
    }
    // Call Elemental Discord and Resonance for all Adventurers in this Fashion
    @Test
    void testEmberKnightResonance(){
        EmberKnight testEmberKnight = new EmberKnight("");
        Room room= new ElementalRoom(Element.FIRE, 1, 1);
        ConcreteSubject testSubject = new ConcreteSubject();
        testEmberKnight.handleElementalEffects(room, testSubject);
        Assertions.assertEquals(testEmberKnight.getBaseCombatRoll(), 2);
    }
    @Test
    void testEmberKnightExpertise(){
        EmberKnight testEmberKnight = new EmberKnight("");
        testEmberKnight.UpdateExpertise(1);
        Assertions.assertEquals(testEmberKnight.getCombatExpertiseBonus(), 2);
    }

}
