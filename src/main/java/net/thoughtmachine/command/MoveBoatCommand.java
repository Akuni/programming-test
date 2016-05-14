package net.thoughtmachine.command;

import net.thoughtmachine.Application;
import net.thoughtmachine.exception.IllegalCommandException;
import net.thoughtmachine.exception.MalformedCommand;

/**
 * Created by SARROCHE Nicolas on 13/05/16.
 */
public class MoveBoatCommand extends AbstractCommand {


    private String boatXCoordinates;
    private String boatYCoordinates;

    public MoveBoatCommand(String movements){
        super(movements);
    }

    @Override
    public boolean process(Application application) throws MalformedCommand, IllegalCommandException {
        String[] values = initialCommand.split("\\)");
        // check number of argument
        if(values.length != 2)
            throw new MalformedCommand("To move a boat two parameters are required : (x , y) MLR");
        // remove unnecessary ( and spaces
        values[0] = values[0].replace("(", "");
        values[0] = values[0].replace(" ", "");
        // split on coma to retrieve X and Y values
        String[] boatCoordinates = values[0].split(",");
        if(boatCoordinates.length != 2)
            throw new MalformedCommand("To move a boat the first parameter needs to be : (x , y)");
        // save values
        boatXCoordinates = boatCoordinates[0];
        boatYCoordinates = boatCoordinates[1];
        // remove unnecessary spaces
        values[1] = values[1].replace(" ", "");

        // run throught MLR commands
        for(char c : values[1].toCharArray()){
            switch (c){
                case 'M':
                    String[] res = application.getBoard().moveBoat(boatCoordinates[0], boatCoordinates[1]);
                    boatCoordinates[0] = res[0];
                    boatCoordinates[1] = res[1];
                    break;
                case 'L':
                    application.getBoard().turnBoatLeft(boatCoordinates[0], boatCoordinates[1]);
                    break;
                case 'R':
                    application.getBoard().turnBoatRight(boatCoordinates[0], boatCoordinates[1]);
                    break;
                default:
                    throw new MalformedCommand("To move a boat only M, L, and R are accepted ");
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + boatXCoordinates + "," + boatYCoordinates + ") is moving";
    }
}
