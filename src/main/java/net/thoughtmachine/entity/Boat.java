package net.thoughtmachine.entity;

/**
 * Created by SARROCHE Nicolas on 14/05/16.
 */
public class Boat {

    private Direction direction;

    public Boat(Direction direction){
        this.direction = direction;
    }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Boat heading to " + direction;
    }
}
