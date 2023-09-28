package ooad.arcane;

public class Main {
    public static void main(String[] args) {


//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ SingleGameRun.txt ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//        GameDriver current_game = new GameDriver();
//        BoardRenderer boardRenderer = new BoardRenderer();
//        int turn_number = 1;
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



//######################################################################################################################



//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ MultipleGameRun.txt ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//        int totalGames = 30;
//        int treasuresFoundCount = 0;
//        int adventurersEliminatedCount = 0;
//        int creaturesEliminatedCount = 0;
//
//
//        for (int runNumber = 1; runNumber <= totalGames; runNumber++) {
//            GameDriver current_game = new GameDriver();
//
//            while (!current_game.isGameOver()) {
//                // Move Adventurers
//                current_game.move_adventurers();
//                // Move creatures
//                current_game.move_creatures();
//                current_game.update_adventurer_attributes();
//                // Combat or Treasure condition
//                current_game.check_adventurer_creature_same_room(current_game.active_adventurers);
//            }
//
//            // Determine the reason for game termination
//            if (current_game.active_adventurers.isEmpty()) {
//                adventurersEliminatedCount++;
//                System.out.println("Run " + runNumber + ": ALL ADVENTURERS ELIMINATED.");
//            } else if (current_game.treasure_found_so_far >= 50) {
//                treasuresFoundCount++;
//                System.out.println("Run " + runNumber + ": All TREASURES FOUND.");
//            } else {
//                creaturesEliminatedCount++;
//                System.out.println("Run " + runNumber + ": ALL CREATURES ELIMINATED.");
//            }
//        }
//
//        System.out.println("\nFinal Summary:");
//        System.out.println("Total Games: " + totalGames);
//        System.out.println("All Adventurers Eliminated: " + adventurersEliminatedCount + " times");
//        System.out.println("All Treasures Found: " + treasuresFoundCount + " times");
//        System.out.println("All Creatures Eliminated: " + creaturesEliminatedCount + " times");
    }
}