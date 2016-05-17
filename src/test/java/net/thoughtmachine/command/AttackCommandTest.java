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
public class AttackCommandTest {

    private Application application;
    private AbstractCommand abstractCommand;


    @Before
    public void init(){
        this.application = new Application(new TextInputParser(""));
        // init board to 10x10
        application.setBoard(new Board(10));
        // add a boat at (2,2) facing north
        application.getBoard().getFloatingBoats().add(new Boat(2, 2, Direction.NORTH));
        application.getBoard().getFloatingBoats().add(new Boat(2, 4, Direction.EAST));
        application.getBoard().setBoatInitDone(true);
    }

    @Test
    public void testSuccess() throws MalformedCommand, IllegalCommandException {

        // assert one boat is floating and there is no sunk boat
        Assert.assertEquals(application.getBoard().getFloatingBoats().size(), 2);
        Assert.assertEquals(application.getBoard().getSunkBoats().size(), 0);

        //ATTACK !
        abstractCommand = new AttackBoatCommand("(2, 2)");
        abstractCommand.process(application, application.getBoard());


        // assert one boat is floating and there is no sunk boat
        Assert.assertEquals(application.getBoard().getFloatingBoats().size(), 1);
        Assert.assertEquals(application.getBoard().getSunkBoats().size(), 1);

        //ATTACK ! but miss it ...
        abstractCommand = new AttackBoatCommand("(9, 9)");
        abstractCommand.process(application, application.getBoard());


        // assert one boat is floating and there is no sunk boat
        Assert.assertEquals(application.getBoard().getFloatingBoats().size(), 1);
        Assert.assertEquals(application.getBoard().getSunkBoats().size(), 1);
    }


    @Test(expected = MalformedCommand.class)
    public void testFailMissingCoordinate() throws MalformedCommand, IllegalCommandException {
        //ATTACK ! but .. wait, what ?
        abstractCommand = new AttackBoatCommand("(2, )");
        abstractCommand.process(application, application.getBoard());
    }

    @Test(expected = IllegalCommandException.class)
    public void testFailBoatsNotInitialized() throws MalformedCommand, IllegalCommandException {
        application.getBoard().setBoatInitDone(false);
        //ATTACK !
        abstractCommand = new AttackBoatCommand("(2, 2)");
        abstractCommand.process(application, application.getBoard());
    }

    @Test(expected = IllegalCommandException.class)
    public void testFailWrongValue() throws MalformedCommand, IllegalCommandException {
        application.setBoard(null);
        //ATTACK !
        abstractCommand = new AttackBoatCommand("(2, 2)");
        abstractCommand.process(application, application.getBoard());
    }
}
