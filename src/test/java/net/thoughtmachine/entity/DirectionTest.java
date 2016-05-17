package net.thoughtmachine.entity;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by SARROCHE Nicolas on 17/05/16.
 */
public class DirectionTest {

    @Test
    public void testGetLeftDirection(){
        // check if facing NORTH turning left heads to WEST
        Direction someDirection = Direction.NORTH;
        Direction leftDirection = Direction.getLeftDirection(someDirection);
        Assert.assertTrue(leftDirection == Direction.WEST);

        // check if facing EAST turning left heads to NORTH
        someDirection = Direction.EAST;
        leftDirection = Direction.getLeftDirection(someDirection);
        Assert.assertTrue(leftDirection == Direction.NORTH);

        // check if facing SOUTH turning left heads to EAST
        someDirection = Direction.SOUTH;
        leftDirection = Direction.getLeftDirection(someDirection);
        Assert.assertTrue(leftDirection == Direction.EAST);

        // check if facing WEST turning left heads to SOUTH
        someDirection = Direction.WEST;
        leftDirection = Direction.getLeftDirection(someDirection);
        Assert.assertTrue(leftDirection == Direction.SOUTH);
    }


    @Test
    public void testGetRightDirection(){
        // check if facing WEST turning right heads to NORTH
        Direction someDirection = Direction.WEST;
        Direction rightDirection = Direction.getRightDirection(someDirection);
        Assert.assertTrue(rightDirection == Direction.NORTH);

        // check if facing NORTH turning right heads to EAST
        someDirection = Direction.NORTH;
        rightDirection = Direction.getRightDirection(someDirection);
        Assert.assertTrue(rightDirection == Direction.EAST);

        // check if facing EAST turning right heads to SOUTH
        someDirection = Direction.EAST;
        rightDirection = Direction.getRightDirection(someDirection);
        Assert.assertTrue(rightDirection == Direction.SOUTH);

        // check if facing SOUTH turning right heads to WEST
        someDirection = Direction.SOUTH;
        rightDirection = Direction.getRightDirection(someDirection);
        Assert.assertTrue(rightDirection == Direction.WEST);
    }
}
