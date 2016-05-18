# Battleship Project 
---

* Introduction
* Explanation of the solution
* Team

---
## Introduction 
Consider a square board of size N. Each cell can either be empty or be occupied by a ship. The position of a ship is defined by three parameters: an x-coordinate, a y-coordinate and an orientation (north, east, south, west).
Once the initial state of the board is set up, any number of operations can occur. There are two types of operations:

- Move a ship :
Specified by an initial coordinate and a series of movements which can be move forward (in the direction that ship is facing), rotate left, rotate right. A ship can navigate through an occupied cell. However, two ships cannot occupy the same cell at the end of a move operation.

- Shoot down a ship : 
Specified by an x- and y-coordinate. If the cell is occupied, that ship is sunk and the cell can be occupied by another ship. If there is no ship, nothing happens.

---

## Explanation of the solution
### entitiy
To develop a solution to this project, I started by wondering what were the elements I needed to manipulate. There were only three entities : 
* `Board` : The easiest way to store floating boats and sunk boats informations is to do it in two separated data structures. I first thought of a matrix (Array of Array for instance, or Map of Map, ...) because it was really efficient to retrieve elements (O(1)) and to check if a given coordinate is occupied by a Boat or not.

![alt text](https://github.com/Akuni/programming-test/blob/master/.readme_images/board.png?raw=true "Board")
* `Boat` : Since we use a List of Boat and not a Map of Map (for instance) the coordinates of a give boat is not implied by its position is the datastructure. Therefore the Boat has to know its coordinates. The Boat also carry the information of its direction.

![alt text](https://github.com/Akuni/programming-test/blob/master/.readme_images/boat.png?raw=true "Boat")
* `Direction` :
To simplify things and because there are only four possibilities Direction is an Enum  with containing NORTH, EAST, SOUTH, and WEST. 

![alt text](https://github.com/Akuni/programming-test/blob/master/.readme_images/direction.png?raw=true "Direction")
### io
To easily manage the input and output operation I created two interfaces `InputParser` and `OutputWriter` and only contain the methods getCommand or write to implement to read or write new datas.

![alt text](https://github.com/Akuni/programming-test/blob/master/.readme_images/reader_writer.png?raw=true "Reader & Writer")

### command
I implemented the Command Pattern to have a uniform way to process them. That's why I made an `AbstractCommand` that only contains the initial command as a String and the method process which is implemented in all sub-classes.
![alt text](https://github.com/Akuni/programming-test/blob/master/.readme_images/command.png?raw=true "Commands")

---
## Team
This project was made by Akuni (https://github.com/Akuni/).
