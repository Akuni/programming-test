package net.thoughtmachine.command;

import net.thoughtmachine.Application;
import net.thoughtmachine.entity.Board;
import net.thoughtmachine.exception.IllegalCommandException;
import net.thoughtmachine.exception.MalformedCommand;
import net.thoughtmachine.io.TextInputParser;
import net.thoughtmachine.io.TextOutputWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by SARROCHE Nicolas on 16/05/16.
 */
public class InitBoatCommandTest {

    private Application application;
    private AbstractCommand abstractCommand;


    @Before
    public void init(){
        this.application = new Application(new TextInputParser(""), new TextOutputWriter(""));
    }

    @Test
    public void testInitBoats() throws MalformedCommand, IllegalCommandException {
        this.abstractCommand = new InitBoatCommand("(1,1,E) (2,5,N)");
        application.setBoard(new Board(10));

        // assert there are no boat yet
        Assert.assertTrue(!application.getBoard().isBoatInitDone());

        // add boats
        abstractCommand.process(application, application.getBoard());

        // assert there are boats now
        Assert.assertTrue(application.getBoard().isBoatInitDone());
        Assert.assertTrue(application.getBoard().getFloatingBoats().size() == 2);

    }

    @Test(expected = MalformedCommand.class)
    public void testInitBoatsFailWrongCoordinates() throws MalformedCommand, IllegalCommandException {
        this.abstractCommand = new InitBoatCommand("(1,XX,E) (2,5,N)");
        application.setBoard(new Board(10));

        // assert there are no boat yet
        Assert.assertTrue(!application.getBoard().isBoatInitDone());

        // add boats
        // should throw MalformedException
        abstractCommand.process(application, application.getBoard());
    }

    @Test(expected = MalformedCommand.class)
    public void testInitBoatsFailWrongDirection() throws MalformedCommand, IllegalCommandException {
        this.abstractCommand = new InitBoatCommand("(1,2,EUR) (2,5,N)");
        application.setBoard(new Board(10));

        // assert there are no boat yet
        Assert.assertTrue(!application.getBoard().isBoatInitDone());

        // add boats
        // should throw MalformedException
        abstractCommand.process(application, application.getBoard());
    }
    @Test(expected = MalformedCommand.class)
    public void testInitBoatsFailWrongNumberOfArgument() throws MalformedCommand, IllegalCommandException {
        this.abstractCommand = new InitBoatCommand("(1,2,E,TOO_MUCH)");
        application.setBoard(new Board(10));

        // assert there are no boat yet
        Assert.assertTrue(!application.getBoard().isBoatInitDone());

        // add boats
        // should throw MalformedException
        abstractCommand.process(application, application.getBoard());
    }

    @Test(expected = IllegalCommandException.class)
    public void testInitBoatsFailAlreadyInitialized() throws MalformedCommand, IllegalCommandException {
        this.abstractCommand = new InitBoatCommand("(1,2,E)");
        application.setBoard(new Board(10));

        // assert there are no boat yet
        Assert.assertTrue(!application.getBoard().isBoatInitDone());

        // add boats
        // should throw MalformedException
        abstractCommand.process(application, application.getBoard());

        // add boats ... again !
        // should throw IllegalCommandException
        abstractCommand.process(application, application.getBoard());
    }
}
