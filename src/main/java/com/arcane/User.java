package com.arcane;

import com.arcane.engine.GameEngine;
import com.arcane.util.ReturnType;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class User {


    private static <List> void simulateSingleRun(){
        String command;
        GameEngine gameEngine;
        // user will enter the type of adventurer
        String adventurerType;
        // user will enter the custom name of the adventurer
        String adventurerCustomName;
        // user will enter the comma separated string of the creatures he wants in the game
        String creatures;
        gameEngine =  new GameEngine();
        adventurerType = getInput("Please enter the type of Adventurer");
        adventurerCustomName = getInput("Please enter the custom name of the Adventurer");
        creatures = getInput("Enter a comma separated list of creatures you want");
        gameEngine.initialiseGame(adventurerType, adventurerCustomName, creatures);
        java.util.List<ReturnType> graph = new ArrayList<>();
        Boolean prev=Boolean.TRUE;
        ReturnType temp;
        for(int i=0; i< 50; i++){
            if(!prev)
                break;
            command = getInput("Enter the command you want");
            if(command.equals("Move"))
            {
                String room  = getInput("Enter the Room you want to go to");
                command = command + ":" + room;
            }
            temp=gameEngine.simulateTurn(Boolean.TRUE, command);
            graph.add(temp);
            prev = temp.is_game_over;
        }
        makeGraph(graph);
    }

    /// IMPLEMENTATION OF BONUS PART
    // CREATING LINE CHART USING THE STATISTICS OF THE GAME
    private static void makeGraph(List<ReturnType> data){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int time=0;
        for(ReturnType d:data){
            dataset.addValue(d.currHealth, "Health", String.valueOf(time));
            dataset.addValue(d.numCreatures, "Num_Creatures", String.valueOf(time));
            dataset.addValue(d.numTreasures, "Num_Treasures", String.valueOf(time));
            time+=1;
        }
        // Create a chart
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Sample Line Chart", "Category", "Value", dataset, PlotOrientation.VERTICAL, true, true, false);

        // Create a ChartPanel
        ChartPanel chartPanel = new ChartPanel(lineChart);

        // Create a JFrame to display the chart
        JFrame frame = new JFrame("Line Chart Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
    private static String getInput(String prompt){
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt + ": ");
        String ret = scanner.nextLine();
        return ret;
    }
    public static void main(String[] args) {
    simulateSingleRun();
  }

}
