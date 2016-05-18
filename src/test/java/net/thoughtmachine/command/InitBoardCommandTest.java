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
 * Created by SARROCHE Nicolas on 17/05/16.
 */
public class InitBoardCommandTest {

    private Application application;
    private AbstractCommand abstractCommand;

    @Before
    public void init(){
        this.application = new Application(new TextInputParser(""), new TextOutputWriter(""));
    }

    @Test
    public void testInitBoard() throws MalformedCommand, IllegalCommandException {
        abstractCommand = new InitBoardCommand("10");
        abstractCommand.process(application, application.getBoard());

        Assert.assertNotNull(application.getBoard());
        Assert.assertEquals(application.getBoard().getBoardSize() , 10);
    }

    @Test(expected = MalformedCommand.class)
    public void testInitBoardFail() throws MalformedCommand, IllegalCommandException {
        String command = "A";
        abstractCommand = new InitBoardCommand(command);
        // should throw MalformedCommand exception
        abstractCommand.process(application, application.getBoard());
    }

    @Test(expected = IllegalCommandException.class)
    public void testInitBoardFailAlreadyDone() throws MalformedCommand, IllegalCommandException {
        abstractCommand = new InitBoardCommand("10");
        // should throw MalformedCommand exception
        abstractCommand.process(application, application.getBoard());

        // fail when called twice
        abstractCommand.process(application, application.getBoard());
    }
}
