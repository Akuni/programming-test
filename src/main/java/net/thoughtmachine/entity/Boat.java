package net.thoughtmachine.entity;

/**
 * Created by SARROCHE Nicolas on 14/05/16.
 */
public class Boat {

    private Direction direction;
    private int x, y;

    public Boat(int x, int y, Direction direction){
        this.direction = direction;
        this.x = x;
        this.y = y;
    }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y +", " + direction.getDirection() +")";
    }


    @Override
    public int hashCode() {
        return 51 * x + 51 * y + 51 * direction.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Boat) && ((Boat) obj).getX() == x &&  ((Boat) obj).getY() == y;
    }
}
