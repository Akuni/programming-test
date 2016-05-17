package net.thoughtmachine.entity;

import net.thoughtmachine.exception.IllegalCommandException;
import net.thoughtmachine.exception.MalformedCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by SARROCHE Nicolas on 16/05/16.
 */
public class BoardTest {

    private Board board;
    public final int BOARD_SIZE = 10;


    @Before
    public void init(){
        this.board = new Board(BOARD_SIZE);
    }

    @Test
    public void testBoardInit(){
        // check if there is no boat yet
        Assert.assertTrue(!board.isBoatInitDone());

        // check board dimensions
        Assert.assertTrue(BOARD_SIZE == board.getBoardSize());
    }

    @Test
    public void testPlaceBoat() throws MalformedCommand, IllegalCommandException {
        String xCoordinate = "1";
        String yCoordinate = "3";
        String direction = "E";

        // assert there is no boat yet
        Assert.assertTrue(board.getFloatingBoats().size() == 0);
        Assert.assertTrue(board.getSunkBoats().size() == 0);

        // add a new boat at (1,3)
        board.placeBoat(xCoordinate, yCoordinate, direction);

        // assert there is only one boat floating right now
        Assert.assertTrue(board.getFloatingBoats().size() == 1);
        Assert.assertTrue(board.getSunkBoats().size() == 0);

    }

    @Test(expected = MalformedCommand.class)
    public void testFailToPlaceBoatWrongCoordinate() throws MalformedCommand, IllegalCommandException {
        String xCoordinate = "a";
        String yCoordinate = "3";
        String direction = "E";

        // assert there is no boat yet
        Assert.assertTrue(board.getFloatingBoats().size() == 0);
        Assert.assertTrue(board.getSunkBoats().size() == 0);

        // add a new boat at (a,3)
        // should fail and throw exception "MalformedCommand"
        board.placeBoat(xCoordinate, yCoordinate, direction);
    }

    @Test(expected = MalformedCommand.class)
    public void testFailToPlaceBoatWrongDirection() throws MalformedCommand, IllegalCommandException {
        String xCoordinate = "1";
        String yCoordinate = "3";
        String direction = "XXXXX";

        // assert there is no boat yet
        Assert.assertTrue(board.getFloatingBoats().size() == 0);
        Assert.assertTrue(board.getSunkBoats().size() == 0);

        // add a new boat at (1,3)
        // should fail and throw exception "MalformedCommand"
        board.placeBoat(xCoordinate, yCoordinate, direction);
    }

    @Test
    public void testMoveBoat() throws MalformedCommand, IllegalCommandException {
        board.placeBoat("1","2","E");
        // assert there is only one boat
        Assert.assertTrue(board.getFloatingBoats().size() == 1);

        board.moveBoat("1","2");
        // assert there is still only one boat
        Assert.assertTrue(board.getFloatingBoats().size() == 1);

        // find the boat
        Boat movedBoat = board.findBoat(2,2);
        Assert.assertNotNull(movedBoat);
        Assert.assertEquals(movedBoat.getDirection(), Direction.EAST);

        Boat oldPlaceBoat = board.findBoat(1,2);
        Assert.assertNull(oldPlaceBoat);

    }


    @Test(expected = IllegalCommandException.class)
    public void testMoveBoatFailNoBoat() throws MalformedCommand, IllegalCommandException {
        board.placeBoat("1","2","E");
        // assert there is only one boat
        Assert.assertTrue(board.getFloatingBoats().size() == 1);

        // throw illegalCommandException
        // there's no boat at (1,12)
        board.moveBoat("1", "12");
    }

    @Test(expected = MalformedCommand.class)
    public void testMoveBoatFailBadCoordinates() throws MalformedCommand, IllegalCommandException {
        board.placeBoat("1","2","E");
        // assert there is only one boat
        Assert.assertTrue(board.getFloatingBoats().size() == 1);

        // throw MalformedCommand
        // there's no boat at (1,XXXXX) because it's an invalid request
        board.moveBoat("1","XXXXX");
    }

    @Test
    public void testAttackBoat() throws MalformedCommand, IllegalCommandException {
        board.placeBoat("1","2","E");
        board.placeBoat("6","6","S");
        board.placeBoat("4","4","N");

        // assert there are exactly three boats
        Assert.assertTrue(board.getFloatingBoats().size() == 3);
        // assert there are no sunk boat ... yet !
        Assert.assertTrue(board.getSunkBoats().size() == 0);

        // ATTACK !
        board.attack("6","6"); // touched !

        // assert there are exactly two boats left
        Assert.assertTrue(board.getFloatingBoats().size() == 2);
        // assert there are now one sunk boat
        Assert.assertTrue(board.getSunkBoats().size() == 1);

        // ATTACK !
        board.attack("9", "1"); // missed !

        // assert there are exactly one boat left
        Assert.assertTrue(board.getFloatingBoats().size() == 2);
        // assert there are now one sunk boat
        Assert.assertTrue(board.getSunkBoats().size() == 1);
    }

    @Test(expected = MalformedCommand.class)
    public void testAttackBoatFailMalformed() throws MalformedCommand, IllegalCommandException {
        board.placeBoat("1","2","E");
        board.placeBoat("6","6","S");

        // assert there are exactly two boats
        Assert.assertTrue(board.getFloatingBoats().size() == 2);
        // assert there are no sunk boat ... yet !
        Assert.assertTrue(board.getSunkBoats().size() == 0);

        // ATTACK !
        // throw malformedException
        board.attack("6","XXXXX");
    }
}
