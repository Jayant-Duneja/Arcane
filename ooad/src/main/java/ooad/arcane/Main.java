package ooad.arcane;

public class Main {
    public static void main(String[] args) {
        GameDriver current_game = new GameDriver();
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
        for(int i=0; i<3;i++) {
            System.out.println("Turn: " + i);
            current_game.move_adventurers();
            current_game.move_creatures();
            System.out.println("###########################################################");
        }

    }
}
