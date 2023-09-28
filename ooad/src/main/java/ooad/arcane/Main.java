package ooad.arcane;

public class Main {
    public static void main(String[] args) {

        GameDriver current_game = new GameDriver();
        BoardRenderer boardRenderer = new BoardRenderer();
        int turn_number = 1;


        while(turn_number<=30) {
            System.out.println("Run Number :: " + turn_number);

            // Move Adventurers
            current_game.move_adventurers();
            // Move creatures
            current_game.move_creatures();

            current_game.update_adventurer_attributes();

            // Combat or Treasure condition
            current_game.check_adventurer_creature_same_room(current_game.active_adventurers);

            // Game termination condition
            if (current_game.isGameOver())
                break;

            turn_number++;
        }

        // Print the final game result
        if (current_game.isGameOver()) {
            // Determine the reason for game termination and print it
            if (current_game.active_adventurers.isEmpty()) {
                System.out.println("\nALL ADVENTURER ELIMINATED.");
            } else if (current_game.treasure_found_so_far >= 50) {
                System.out.println("\nAll TREASURES FOUND.");
            } else {
                System.out.println("\nALL CREATURES ELIMINATED.");
            }
        }else{
            System.out.println("GAME STILL NOT OVER");
        }





//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ SingleGameRun.txt ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//        while(!current_game.isGameOver()){
//
//            // Move Adventurers
//            current_game.move_adventurers();
//
//            // Move creatures
//            current_game.move_creatures();
//
//            current_game.update_adventurer_attributes();
//
//            // Combat or Treasure condition
//            current_game.check_adventurer_creature_same_room(current_game.active_adventurers);
//
//            boardRenderer.print_game(turn_number, current_game);
//
//            // Game termination condition
//            if(current_game.isGameOver())
//                break;
//
//            turn_number++;
//        }
//
//        // Print the game result (end game state) here
//        if (current_game.isGameOver()) {
//            // Determine the reason for game termination and print it
//            if (current_game.active_adventurers.isEmpty()) {
//                System.out.println("\nALL ADVENTURER ELIMINATED.");
//            } else if (current_game.treasure_found_so_far >= 50) {
//                System.out.println("\nAll TREASURES FOUND.");
//            } else {
//                System.out.println("\nALL CREATURES ELIMINATED.");
//            }
//        } else {
//            System.out.println("\nGAME DID NOT FINISH.");
//        }

    }
}