package net.thoughtmachine.command;

import net.thoughtmachine.Application;
import net.thoughtmachine.entity.Board;
import net.thoughtmachine.exception.IllegalCommandException;

/**
 * Created by SARROCHE Nicolas on 13/05/16.
 */
public class InitBoardCommand extends AbstractCommand {


    private int boardSize;

    public InitBoardCommand(String size){
        this.boardSize = Integer.parseInt(size);
    }

    @Override
    public boolean process(Application application) throws IllegalCommandException {
        if(application.getBoard() == null){
            application.setBoard(new Board(boardSize));
            return true;
        }
        throw new IllegalCommandException("Can not init the board, board already exists !");
    }
}
