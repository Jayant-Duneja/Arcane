package ooad.arcane;

public class Main {
    public static void main(String[] args) {
        GameDriver current_game = new GameDriver();
        BoardRenderer boardRenderer = new BoardRenderer();
        for(int turn_number=1; turn_number<=5;turn_number++) {
            // Move Adventurers
            current_game.move_adventurers();
            // Move creatures
            current_game.move_creatures();
            current_game.update_adventurer_attributes();
            // Combat or Treasure condition
            current_game.check_adventurer_creature_same_room(current_game.active_adventurers);
            // Game termination condition
            if(current_game.isGameOver())
                break;
            // Printing the board
            boardRenderer.print_game(turn_number, current_game);
        }

    }
}