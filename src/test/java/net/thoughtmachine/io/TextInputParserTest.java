package net.thoughtmachine.io;

import net.thoughtmachine.command.*;
import net.thoughtmachine.exception.MalformedCommand;
import  org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by SARROCHE Nicolas on 13/05/16.
 */
public class TextInputParserTest {


    @Test
    public void testSucessBoard() throws MalformedCommand {
        InputParser tip = new TextInputParser("/testBoard");

        // get 123
        AbstractCommand ac = tip.getCommand();
        // check instance type
        Assert.assertTrue(ac instanceof InitBoardCommand);

    }


    @Test(expected =  MalformedCommand.class)
    public void testFailBoardEmptyLine() throws MalformedCommand {
        InputParser tip = new TextInputParser("/testBoard");
        // skip 123
        tip.getCommandLine();

        // get empty line
        tip.getCommand();

    }

    @Test(expected =  MalformedCommand.class)
    public void testFailBoardNonEmptyLine() throws MalformedCommand {
        InputParser tip = new TextInputParser("/testBoard");
        // skip 123 and empty line
        tip.getCommandLine();
        tip.getCommandLine();

        // get non-empty line
        tip.getCommand();

    }


    // ------------------------
    // init boats
    // ------------------------

    @Test
    public void testSucessBoat() throws MalformedCommand {
        InputParser tip = new TextInputParser("/testBoat");

        // get (123, 321, N)
        AbstractCommand ac = tip.getCommand();
        // check instance type
        Assert.assertTrue(ac instanceof InitBoatCommand);

    }

    @Test(expected =  MalformedCommand.class)
    public void testFailBoatEmptyLine() throws MalformedCommand {
        InputParser tip = new TextInputParser("/testBoat");
        // skip (123, 321, N)
        tip.getCommandLine();

        // get empty line
        tip.getCommand();

    }

    @Test(expected =  MalformedCommand.class)
    public void testFailBoatNonEmptyLine() throws MalformedCommand {
        InputParser tip = new TextInputParser("/testBoat");
        // skip (123, 321, N) and empty line
        tip.getCommandLine();
        tip.getCommandLine();

        // get non-empty line
        tip.getCommand();

    }

    // ------------------------
    // move boats
    // ------------------------

    @Test
    public void testSucessMoveBoat() throws MalformedCommand {
        InputParser tip = new TextInputParser("/testMove");

        // get (2, 2) L
        AbstractCommand ac = tip.getCommand();
        // check instance type
        Assert.assertTrue(ac instanceof MoveBoatCommand);

        // get (2, 2) R
        ac = tip.getCommand();
        // check instance type
        Assert.assertTrue(ac instanceof MoveBoatCommand);

        // get (2, 2)   MMRMLM
        ac = tip.getCommand();
        // check instance type
        Assert.assertTrue(ac instanceof MoveBoatCommand);

    }

    @Test(expected =  MalformedCommand.class)
    public void testFailMoveBoatEmptyLine() throws MalformedCommand {
        InputParser tip = new TextInputParser("/testMove");
        // skip 1, 2 and 3
        tip.getCommandLine();
        tip.getCommandLine();
        tip.getCommandLine();

        // get empty line
        tip.getCommand();

    }

    @Test(expected =  MalformedCommand.class)
    public void testFailMoveBoatNonEmptyLine() throws MalformedCommand {
        InputParser tip = new TextInputParser("/testMove");
        // skip 1, 2, 3 and the empty-line
        tip.getCommandLine();
        tip.getCommandLine();
        tip.getCommandLine();
        tip.getCommandLine();

        // get non-empty line
        tip.getCommand();

    }
}
