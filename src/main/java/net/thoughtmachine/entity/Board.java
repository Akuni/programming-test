package net.thoughtmachine.entity;

import net.thoughtmachine.exception.IllegalCommandException;
import net.thoughtmachine.exception.MalformedCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SARROCHE Nicolas on 14/05/16.
 */
public class Board {

    private List<List<Boat>> map;
    private boolean boatInitDone;
    private int boardSize;


    public Board(int size){
        this.map = new ArrayList<List<Boat>>();
        for(int i = 0; i < size ; i++){
            List<Boat> ithList = new ArrayList<Boat>();
            for(int j = 0; j < size; j++){
                ithList.add(null);
            }
            map.add(ithList);
        }
        this.boardSize = size;
        this.boatInitDone = false;
    }

    public void placeBoat(String xString, String yString, String dirString) throws MalformedCommand {
        Direction direction = Direction.findDirectionByString(dirString);
        if(direction == null){
            throw new MalformedCommand("Direction should be N, S, E, or W, found : " + dirString);
        }
        int x = Integer.parseInt(xString);
        int y = Integer.parseInt(yString);
        map.get(x).set(y, new Boat(direction));
        System.out.println(map.get(x).get(y));
    }

    public String[] moveBoat(String xString, String yString) throws IllegalCommandException {
        int x = Integer.parseInt(xString);
        int y = Integer.parseInt(yString);

        // retrieve boat
        Boat boat = map.get(x).get(y);
        if(boat == null)
            throw new IllegalCommandException("No boat to move at (" + xString + "," + yString + ")");

        // compute next coordinates
        int nextX = x, nextY = y;
        switch (boat.getDirection()){
            case NORTH:
                nextY++;
                break;
            case EAST:
                nextX++;
                break;
            case SOUTH:
                nextY--;
                break;
            case WEST:
                nextX--;
                break;
            default:
                break;

        }

        if((0 <= nextX && nextX < boardSize) && (0 <= nextY && nextY < boardSize)){
            Boat nextSquare = map.get(nextX).get(nextY);
            if(nextSquare == null){
                map.get(x).set(y, null);
                map.get(nextX).set(nextY, boat);
            } else {
                throw new IllegalCommandException("Can not go to (" + nextX + "," + nextY + ") there is already a boat there");
            }

        }else {
            throw new IllegalCommandException("Can not move to " + boat.getDirection() + ", the boat will get out of the board");
        }
        return new String[]{String.valueOf(nextX), String.valueOf(nextY)};
    }

    public boolean isBoatInitDone() {
        return boatInitDone;
    }

    public void setBoatInitDone(boolean boatInitDone) {
        this.boatInitDone = boatInitDone;
    }

    public void turnBoatLeft(String xString, String yString) throws IllegalCommandException {
        int x = Integer.parseInt(xString);
        int y = Integer.parseInt(yString);

        Boat boat = map.get(x).get(y);
        if(boat == null)
            throw new IllegalCommandException("No boat to move at this coordinate : (" + xString + "," + yString +")");

        Direction newDirection = Direction.getLeftDirection(boat.getDirection());
        boat.setDirection(newDirection);
    }

    public void turnBoatRight(String xString, String yString) throws IllegalCommandException {
        int x = Integer.parseInt(xString);
        int y = Integer.parseInt(yString);

        Boat boat = map.get(x).get(y);
        if(boat == null)
            throw new IllegalCommandException("No boat to move at this coordinate : (" + xString + "," + yString +")");

        Direction newDirection = Direction.getRightDirection(boat.getDirection());
        boat.setDirection(newDirection);
    }

    public void printboard(){
        for(List<Boat> l : map){
            String res = "";
            for(Boat b : l){
                res += b + " ";
            }
            System.out.println(res + "\n");
        }
    }
}
