package net.thoughtmachine.command;

import net.thoughtmachine.Application;
import net.thoughtmachine.entity.Board;
import net.thoughtmachine.entity.Boat;
import net.thoughtmachine.entity.Direction;
import net.thoughtmachine.exception.IllegalCommandException;
import net.thoughtmachine.exception.MalformedCommand;
import net.thoughtmachine.io.TextInputParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by SARROCHE Nicolas on 17/05/16.
 */
public class MoveBoatCommandTest {

    private Application application;
    private AbstractCommand abstractCommand;


    @Before
    public void init(){
        this.application = new Application(new TextInputParser(""));
        // init board to 10x10
        application.setBoard(new Board(10));
        // add a boat at (2,2) facing north
        application.getBoard().getFloatingBoats().add(new Boat(2, 2, Direction.NORTH));
    }

    @Test
    public void testSuccess() throws MalformedCommand, IllegalCommandException {
        // move twice to NORTH
        abstractCommand = new MoveBoatCommand("(2 , 2) MM");
        abstractCommand.process(application, application.getBoard());

        Assert.assertTrue(application.getBoard().getFloatingBoats().get(0).getX() == 2);
        Assert.assertTrue(application.getBoard().getFloatingBoats().get(0).getY() == 4);

        // turn right and move once to EAST
        abstractCommand = new MoveBoatCommand("(2 , 4) RM");
        abstractCommand.process(application, application.getBoard());

        Assert.assertTrue(application.getBoard().getFloatingBoats().get(0).getX() == 3);
        Assert.assertTrue(application.getBoard().getFloatingBoats().get(0).getY() == 4);

        // turn Left and move once four time and check if the coordinates and direction are right
        abstractCommand = new MoveBoatCommand("(3 , 4) LMLMLMLM");
        abstractCommand.process(application, application.getBoard());

        Assert.assertTrue(application.getBoard().getFloatingBoats().get(0).getX() == 3);
        Assert.assertTrue(application.getBoard().getFloatingBoats().get(0).getY() == 4);
        Assert.assertTrue(application.getBoard().getFloatingBoats().get(0).getDirection() == Direction.EAST);

    }


    @Test(expected = IllegalCommandException.class)
    public void testFailMovingOutNorth() throws MalformedCommand, IllegalCommandException {
        // add boat at the edge of the map
        application.getBoard().getFloatingBoats().add(new Boat(9,9, Direction.NORTH));
        // move twice to NORTH
        abstractCommand = new MoveBoatCommand("(9, 9) MM");

        // should throw IllegalCommandException because the boat is out of the board
        abstractCommand.process(application, application.getBoard());

    }

    @Test(expected = IllegalCommandException.class)
    public void testFailMovingOutEast() throws MalformedCommand, IllegalCommandException {
        // add boat at the edge of the map
        application.getBoard().getFloatingBoats().add(new Boat(9,9, Direction.EAST));
        // move twice to EAST
        abstractCommand = new MoveBoatCommand("(9, 9) MM");

        // should throw IllegalCommandException because the boat is out of the board
        abstractCommand.process(application, application.getBoard());

    }

    @Test(expected = IllegalCommandException.class)
    public void testFailMovingOutSouth() throws MalformedCommand, IllegalCommandException {
        // add boat at the edge of the map
        application.getBoard().getFloatingBoats().add(new Boat(0, 0, Direction.SOUTH));
        // move twice to SOUTH
        abstractCommand = new MoveBoatCommand("(0, 0) MM");

        // should throw IllegalCommandException because the boat is out of the board
        abstractCommand.process(application, application.getBoard());

    }

    @Test(expected = IllegalCommandException.class)
    public void testFailMovingOutWest() throws MalformedCommand, IllegalCommandException {
        // add boat at the edge of the map
        application.getBoard().getFloatingBoats().add(new Boat(0, 0, Direction.WEST));
        // move twice to WEST
        abstractCommand = new MoveBoatCommand("(0, 0) MM");

        // should throw IllegalCommandException because the boat is out of the board
        abstractCommand.process(application, application.getBoard());

    }

    @Test(expected = IllegalCommandException.class)
    public void testFailNoBoat() throws MalformedCommand, IllegalCommandException {
        // move twice boat at (5,5)
        abstractCommand = new MoveBoatCommand("(5, 5) MM");

        // should throw IllegalCommandException because there is no boat
        abstractCommand.process(application, application.getBoard());

    }

    @Test(expected = MalformedCommand.class)
    public void testFailBadCommand() throws MalformedCommand, IllegalCommandException {
        // move twice boat at (5,5)
        abstractCommand = new MoveBoatCommand("(2,2) MK");

        // should throw MalformedCommand because K is not a valid command
        abstractCommand.process(application, application.getBoard());
    }


}
