package net.thoughtmachine.command;

import net.thoughtmachine.Application;
import net.thoughtmachine.entity.Board;
import net.thoughtmachine.exception.IllegalCommandException;
import net.thoughtmachine.exception.MalformedCommand;

/**
 * Created by SARROCHE Nicolas on 13/05/16.
 */
public class AttackBoatCommand extends AbstractCommand {

    private String boatXCoordinates;
    private String boatYCoordinates;


    public AttackBoatCommand(String command){
        super(command);
    }

    @Override
    public boolean process(Application application, Board board) throws MalformedCommand, IllegalCommandException {
        if(board == null)
            throw new IllegalCommandException("Can not attack a board, the board is not initialized yet !");
        if(!board.isBoatInitDone())
            throw new IllegalCommandException("Can not attack ! Boats are not initialized yet !");
        String[] values = initialCommand.split("\\)");
        // check number of argument
        if(values.length != 1)
            throw new MalformedCommand("To attack a boat one parameter is required : (x , y)");
        // remove unnecessary ( and spaces
        values[0] = values[0].replace("(", "");
        values[0] = values[0].replace(" ", "");
        // split on coma to retrieve X and Y values
        String[] boatCoordinates = values[0].split(",");
        if(boatCoordinates.length != 2)
            throw new MalformedCommand("To attack a boat the parameter needs to be : (x , y)");
        // save values
        boatXCoordinates = boatCoordinates[0];
        boatYCoordinates = boatCoordinates[1];

        board.attack(boatXCoordinates, boatYCoordinates);

        return true;
    }

    @Override
    public String toString() {
        return "Attacking coordinates (" + boatXCoordinates + "," + boatYCoordinates + ") !";
    }
}
