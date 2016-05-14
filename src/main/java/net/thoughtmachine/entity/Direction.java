package net.thoughtmachine.entity;

/**
 * Created by SARROCHE Nicolas on 14/05/16.
 */
public enum Direction {
    NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");

    private String direction;

    private Direction(String direction){
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public static Direction findDirectionByString(String ref){
        for(Direction direction : Direction.values()){
            System.out.println(direction.getDirection() + " " + ref.length());
            if(direction.getDirection().equals(ref)){
                return direction;
            }
        }
        return null;
    }
}
