package net.thoughtmachine.entity;

import net.thoughtmachine.exception.IllegalCommandException;
import net.thoughtmachine.exception.MalformedCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by SARROCHE Nicolas on 14/05/16.
 */
public class Board {

    private List<Boat> floatingBoats;
    private List<Boat> sunkBoats;
    private boolean boatInitDone;
    private int boardSize;


    public Board(int size){
        this.floatingBoats = new ArrayList<Boat>();
        this.sunkBoats = new ArrayList<Boat>();
        this.boardSize = size;
        this.boatInitDone = false;
    }

    public void placeBoat(String xString, String yString, String dirString) throws MalformedCommand, IllegalCommandException {
        if(!Pattern.matches("[0-9]+", xString) || !Pattern.matches("[0-9]+", yString))
            throw new MalformedCommand("To place a boat give its X and Y coordinates ! Got "  + xString + ", " + yString);
        if(!Pattern.matches("[NEWS]", dirString))
            throw new MalformedCommand("To place a boat give its Direction (N, E, S, W) ! Got " + dirString);

        if(floatingBoats == null)
            throw new IllegalCommandException("Can not place boat if the board isn't initialized !");
        Direction direction = Direction.findDirectionByString(dirString);
        if(direction == null){
            throw new MalformedCommand("Direction should be N, S, E, or W, found : " + dirString);
        }
        int x = Integer.parseInt(xString);
        int y = Integer.parseInt(yString);
        floatingBoats.add(new Boat(x, y, direction));
    }

    public String[] moveBoat(String xString, String yString) throws IllegalCommandException, MalformedCommand {
        if(!Pattern.matches("[0-9]+", xString) || !Pattern.matches("[0-9]+", yString))
            throw new MalformedCommand("To move a boat give its X and Y coordinates ! Got "  + xString + ", " + yString);

        int x = Integer.parseInt(xString);
        int y = Integer.parseInt(yString);

        // retrieve boat
        // TODO loop over floatingBoats to retrieve boats
        Boat boat = findBoat(x,y);
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
            Boat nextSquare = findBoat(nextX, nextY);
            if(nextSquare == null){
                boat.setX(nextX);
                boat.setY(nextY);
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

    // test purpose
    protected int getBoardSize(){
        return this.boardSize;
    }

    // test purpose
    protected List<Boat> getFloatingBoats(){
        return this.floatingBoats;
    }

    // test purpose
    protected List<Boat> getSunkBoats(){
        return this.sunkBoats;
    }

    public void turnBoatLeft(String xString, String yString) throws IllegalCommandException, MalformedCommand {
        if(!Pattern.matches("[0-9]+", xString) || !Pattern.matches("[0-9]+", yString))
            throw new MalformedCommand("To turn a boat give its X and Y coordinates ! Got "  + xString + ", " + yString);

        int x = Integer.parseInt(xString);
        int y = Integer.parseInt(yString);

        Boat boat = findBoat(x,y);
        if(boat == null)
            throw new IllegalCommandException("No boat to move at this coordinate : (" + xString + "," + yString +")");

        Direction newDirection = Direction.getLeftDirection(boat.getDirection());
        boat.setDirection(newDirection);
    }

    public void turnBoatRight(String xString, String yString) throws IllegalCommandException, MalformedCommand {
        if(!Pattern.matches("[0-9]+", xString) || !Pattern.matches("[0-9]+", yString))
            throw new MalformedCommand("To turn a boat give its X and Y coordinates ! Got "  + xString + ", " + yString);

        int x = Integer.parseInt(xString);
        int y = Integer.parseInt(yString);

        Boat boat = findBoat(x,y);
        if(boat == null)
            throw new IllegalCommandException("No boat to move at this coordinate : (" + xString + "," + yString +")");

        Direction newDirection = Direction.getRightDirection(boat.getDirection());
        boat.setDirection(newDirection);
    }


    public void attack(String boatXCoordinates, String boatYCoordinates) throws IllegalCommandException, MalformedCommand {
        if(!Pattern.matches("[0-9]+", boatXCoordinates) || !Pattern.matches("[0-9]+", boatYCoordinates))
            throw new MalformedCommand("To attack a boat give its X and Y coordinates ! Got "  + boatXCoordinates + ", " + boatYCoordinates);
        /*if(!boatInitDone)
            throw new IllegalCommandException("Can not attack boat, there are no boats on the board !");
        */
        int x = Integer.valueOf(boatXCoordinates);
        int y = Integer.valueOf(boatYCoordinates);

        Boat boat = findBoat(x,y);
        if(boat == null)
            return;
        sunkBoats.add(boat);
        floatingBoats.remove(boat);
    }



    protected Boat findBoat(int x , int y){
        for(Boat boat : floatingBoats){
            if(boat.getX() == x && boat.getY() == y){
                return boat;
            }
        }
        return null;
    }

    public void printResult(){
        for(Boat b : floatingBoats){
            System.out.println(b);
        }
        for(Boat b : sunkBoats){
            System.out.println(b +" SUNK");
        }
    }

}
