package ooad.arcane.Adventurers;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
//-1: Starting Floor
//        0: Earth Floor
//        1: Fire Floor
//        2: Water Floor
//        3: Air Floor

//@Getter
//@Setter
public class Adventurer {

    public Adventurer(int health, int dodge_chance, String affinity, String discord, ArrayList<Integer> current_room, int treasure,
    int damage_delta, int dice_roll_combat_delta, int dice_roll_treasure_delta)
    {
        this.health=health;
        this.dodge_chance=dodge_chance;
        this.affinity=affinity;
        this.discord=discord;
        this.current_room=current_room;
        this.treasure=treasure;
        this.dice_roll_combat_delta=dice_roll_combat_delta;
        this.dice_roll_treasure_delta=dice_roll_treasure_delta;
        this.damage_delta=damage_delta;
    }
    private int health;
    @Getter
    @Setter
    private double dodge_chance;
    private String affinity;
    private String discord;
    @Getter
    @Setter
    private int dice_roll_combat_delta;
    @Getter
    @Setter
    private int dice_roll_treasure_delta;
    @Getter
    @Setter
    private int damage_delta;
    private ArrayList<Integer> current_room;
    private int treasure;
    boolean is_alive()
    {
        return this.health > 0;
    }
    void update_treasure()
    {
        this.treasure=this.treasure+1;
    }
    void update_health()
    {
        if (Math.random() < this.dodge_chance) {
            System.out.println("I have dodged the attack");
        } else {
            this.health=this.health-2+this.damage_delta;
        }
    }
    void update_attributes(int floor_id) {
    }
    ArrayList<Integer> movement(){
        return null;
    }
}
