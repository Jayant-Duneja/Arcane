package ooad.arcane;

import ooad.arcane.Adventurers.Adventurer;
import ooad.arcane.Creatures.Creature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardRenderer {
    private Map<String, String> shortened_names_characters;
    private Map<Integer, String> floor_names;
    public BoardRenderer() {
        this.shortened_names_characters = new HashMap<>(){{
            put("EmberKnight", "EK");
            put("MistWalker", "MW");
            put("TerraVoyager", "TV");
            put("ZephyrRogue", "ZR");
            put("TerraVores", "T");
            put("FireBorns", "F");
            put("Aquarids", "A");
            put("Zephyrals", "Z");
        }};
        this.floor_names = new HashMap<>(){{
            put(-1, "Starting Room");
            put(0, "EARTH Floor");
            put(1, "FIRE Floor");
            put(2, "WATER Floor");
            put(3, "AIR Floor");
        }};

    }
    private void print_turn(int turn_number){
        System.out.println("----------------------------------" + "Turn-" + turn_number + "----------------------------------");
        System.out.println("\n");
    }
    private void print_floor(Floor floor, List<Adventurer> adventurers){
        System.out.println(this.floor_names.get(floor.floor_id) + "\n");
        String adv_string="";
        String creature_string="";
        if(floor.floor_id == -1){
            System.out.println("+------------------------------+------------------------------+------------------------------+");
            adv_string="";
            List<Integer> curr_position;
            for(Adventurer adv: adventurers){
                curr_position = adv.getCurrent_room();
                if(curr_position.get(0) == floor.floor_id && curr_position.get(1) == 0 && curr_position.get(2) == 0){
                    adv_string += this.shortened_names_characters.get(adv.getName());
                    adv_string += ",";
                }
            }
            System.out.print("|    "  + adv_string + ":" + creature_string + "     |");
            System.out.println("+------------------------------+------------------------------+------------------------------+\\n");

        }
        else{
            for(int i=0;i<=2;i++){
                System.out.println("+------------------------------+------------------------------+------------------------------+");
                for(int j=0;j<=2;j++){
                    adv_string="";
                    creature_string="";
                    List<Integer> curr_position;
                    for(Adventurer adv: adventurers){
                        curr_position = adv.getCurrent_room();
                        if(curr_position.get(0) == floor.floor_id && curr_position.get(1) == i && curr_position.get(2) == j){
                            adv_string += this.shortened_names_characters.get(adv.getName());
                            adv_string += ",";
                        }
                    }
                    for(List<Integer> it: floor.getCreature().getActive_positions()){
                        if(it.get(0) == i && it.get(1) == j){
                            creature_string += this.shortened_names_characters.get(floor.getCreature().getName());
                            creature_string += ",";
                        }
                    }
                    System.out.print("|           "  + adv_string + ":" + creature_string + "        ");
                }
                System.out.println("|");
            }
            System.out.println("+------------------------------+------------------------------+------------------------------+\\n");

        }


    }
    private void print_adventurer_detail(List<Adventurer> current_adventurers){
        for(Adventurer adv:current_adventurers){
            System.out.println(adv.getName() + " - " + adv.getTreasure() + " Treasure(s)" + " - " + adv.getHealth() + " Health Remaining");
        }
    }
    private void print_creature_details(List<Creature> current_creature_objects){
        for(Creature creature:current_creature_objects){
            System.out.println(creature.getName() + " - " + creature.getActive_positions().size() + " Remaining");
        }
    }
    private void print_all_floors(GameDriver current_game, List<Adventurer> current_adventurers){
        for(Floor floor:current_game.floors){
            print_floor(floor, current_adventurers);
        }
    }
    public void print_game(int turn_number, GameDriver current_game){
        print_turn(turn_number);
        print_all_floors(current_game, current_game.active_adventurers);
        print_adventurer_detail(current_game.active_adventurers);
        print_creature_details(current_game.active_creature_objects);
    }
}
