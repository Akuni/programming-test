package net.thoughtmachine.command;

import net.thoughtmachine.Application;
import net.thoughtmachine.entity.Board;
import net.thoughtmachine.exception.IllegalCommandException;
import net.thoughtmachine.exception.MalformedCommand;

/**
 * Created by SARROCHE Nicolas on 13/05/16.
 */
public class InitBoatCommand extends AbstractCommand{

    private String[] boats;

    public InitBoatCommand(String boats){
        super(boats);
        this.boats = boats.split("\\)");
    }

    @Override
    public boolean process(Application application, Board board) throws IllegalCommandException, MalformedCommand {
        if(board == null)
            throw new IllegalCommandException("Can not add boat, the board is not initialized yet !");

        if(board.isBoatInitDone()){
            throw new IllegalCommandException("Boats are already on the board, can not add new boat !");
        }

        for(String s : boats){
            s = s.replace("(", "");
            s = s.replace(" ", "");
            String[] values = s.split(",");
            if(values.length != 3)
                throw new MalformedCommand("Boat initialisation requires 3 parameters : X, Y, and Direciton : " + initialCommand);
            board.placeBoat(values[0], values[1], values[2]);
        }

        board.setBoatInitDone(true);
        return true;
    }



    @Override
    public String toString() {
        return "Initializing boats with " + initialCommand;
    }
}
