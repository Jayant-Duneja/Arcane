package ooad.arcane;

import lombok.Getter;
import lombok.Setter;
import ooad.arcane.Creatures.Creature;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
public class Floor {
    int floor_id;
    @Getter
    @Setter
    private Creature creature;
    @Getter
    Map<List<Integer>, List<List<Integer>>> roomConnections = new HashMap<>();
    public Floor(int floor_id, Creature creature){
        this.floor_id = floor_id;
        this.creature=creature;
        makeConnections();
    }
    private void makeConnections(){
        if(this.floor_id == -1){
            List<Integer> currentRoom = Arrays.asList(-1, 0, 0);
            List<List<Integer>> connections = new ArrayList<>();
            connections.add(Arrays.asList(0,1,1));
            connections.add(Arrays.asList(1,1,1));
            connections.add(Arrays.asList(2,1,1));
            connections.add(Arrays.asList(3,1,1));
            this.roomConnections.put(currentRoom, connections);
        }
        else{
            for (int j = 0; j <= 2; j++) {
                for (int k = 0; k <= 2; k++) {
                    List<Integer> currentRoom = Arrays.asList(this.floor_id, j, k);
                    List<List<Integer>> connections = new ArrayList<>();
                    if (j > 0) {
                        connections.add(Arrays.asList(this.floor_id, j - 1, k));
                    }
                    if (j < 1) {
                        connections.add(Arrays.asList(this.floor_id, j + 1, k));
                    }
                    if (k > 0) {
                        connections.add(Arrays.asList(this.floor_id, j, k - 1));
                    }
                    if (k < 1) {
                        connections.add(Arrays.asList(this.floor_id, j, k + 1));
                    }
                    if(j == 1 && k == 1){
                        connections.add(Arrays.asList(-1, 0, 0));
                    }
                    this.roomConnections.put(currentRoom, connections);
                }
            }
        }
    }

    public void get_creature_details(){

    }
}

