package ooad.arcane;

public class Main {
    public static void main(String[] args) {
        GameDriver current_game = new GameDriver();
        BoardRenderer boardRenderer = new BoardRenderer();
        // Commands to access all the objects and their features
//        for(Adventurer it: current_game.active_adventurers){
//            System.out.println("health of my Adv is: " + it.getHealth());
//            System.out.println("dodge chance is: " + it.getDodge_chance());
//            System.out.println("Is he alive: " + it.is_alive());
//            System.out.println("Current pos is: " + it.getCurrent_room());
//            System.out.println("-----------------------------------------");
//        }
//        for(Creature it: current_game.active_creature_objects){
//            System.out.println("Health of Adv: " + it.getHealth());
//            System.out.println("Current positions are: " + it.getActive_positions());
//            System.out.println("Is anyone alive: " + it.are_present());
//            System.out.println("-----------------------------------------");
//        for(Floor i: current_game.floors){
//            System.out.println("Can I access Creature object from Floor: " + i.creature.getActive_positions());
//            System.out.println("---------------------------------------");
//        }
        for(int turn_number=1; turn_number<=5;turn_number++) {

            // Game turn
//            System.out.println("Turn: " + (i+1));

            // Adventurers position
//            System.out.println("printing positions of Adventurers");
            current_game.move_adventurers();

//            System.out.println("-----------------------------------------------------------------------------------------------");

            // Creatures position
//            System.out.println("printing positions of Creatures");
            current_game.move_creatures();
//            System.out.println("-----------------------------------------------------------------------------------------------");
            //update adventurer attributes
            current_game.update_adventurer_attributes();

            // Combat or Treasure condition
            current_game.check_adventurer_creature_same_room(current_game.active_adventurers);


            // Game termination condition
            if(current_game.isGameOver())
                break;

//            System.out.println("------------------------------------------------------");
//            System.out.println("Printing indices of Creatures on the same room");
//            for(Adventurer it:current_game.active_adventurers){
//                List<Integer> lst  = current_game.get_creature_in_same_room(it);
//                System.out.println("Name: " + it.getName());
//                System.out.println("Current Floor: " + it.getCurrent_room().get(0));
//                System.out.println("List of Indices: " + lst);
//                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//            }
//            System.out.println("##################################################################################################################");
            boardRenderer.print_game(turn_number, current_game);
        }

    }
}