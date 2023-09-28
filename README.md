[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/0UBO3sfV)
# project-2-arcane

Team Members:
1. Jayant Duneja
2. Tilak Singh

Java Version: - 17

Additional Assumptions:
 - For our code, these are the floor id we are considering:
   - -1: Starting Floor
   - 0: Earth Floor
   - 1: Fire Floor
   - 2: Water Floor
   - 3: Air Floor
 - Adventurers:
   - For each adventurer, it will move randomly to any of the connected room. For the (1,1) room on each floor, 
     we are considering the starting floor as one of the connected room as well.
 - Creatures:
   - We are considering that on all the floors, all the creatures spawn at the four edges of the current floor, 
     i.e, in rooms (0,0), (0,2), (2,0) and (2,2).
   - For Fireborns, they spawn on the four edges and move in a clockwise or an anti-clockwise manner based on a 
     random boolean flag which is generated in the code
   - Zephyryls move randomly to any of the rooms they are connected at any point.
 
Changes in the UML:
 - Adventurer Diagram:
   - We have implemented a single movement() function in the Adventurer super class and we are not overriding 
     this method in each sub-class.
   - We don't have seperate functions for updating discord and resonance attributes. We are doing this in a single function.
 - Creature Diagram:
   - For each of the creatures, we had to implement some more functions inside the sub classes to define the 
     exact movement of each creature.
 - Gamedriver:
   - A few new functions have been introduced but the overall structure is the same.
 - BoardRenderer:
   - We had not included a Board Renderer class in the previous UML but in the code, writing those as functions
     in the Game Driver was a bit difficult so we created a new class just to print the board.
