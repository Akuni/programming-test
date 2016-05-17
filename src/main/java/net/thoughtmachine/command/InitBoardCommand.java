package net.thoughtmachine.command;

import net.thoughtmachine.Application;
import net.thoughtmachine.entity.Board;
import net.thoughtmachine.exception.IllegalCommandException;
import net.thoughtmachine.exception.MalformedCommand;

import java.util.regex.Pattern;

/**
 * Created by SARROCHE Nicolas on 13/05/16.
 */
public class InitBoardCommand extends AbstractCommand {


    private int boardSize;

    public InitBoardCommand(String size) {
        super(size);

    }

    @Override
    public boolean process(Application application, Board board) throws IllegalCommandException, MalformedCommand {
        if(!Pattern.matches("[0-9]+", initialCommand)){
            throw new MalformedCommand("To init a board, you need to specify its size, got : " + initialCommand);
        }
        this.boardSize = Integer.parseInt(initialCommand);
        if(board == null){
            application.setBoard(new Board(boardSize));
            return true;
        }
        throw new IllegalCommandException("Can not init the board, board already exists !");
    }

    @Override
    public String toString() {
        return "Command INIT_BOARD with " + boardSize + "x" + boardSize + " board";
    }
}
