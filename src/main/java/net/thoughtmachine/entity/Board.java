package net.thoughtmachine.entity;

import net.thoughtmachine.exception.MalformedCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SARROCHE Nicolas on 14/05/16.
 */
public class Board {

    List<List<Boat>> map;

    public Board(int size){
        this.map = new ArrayList<List<Boat>>();
        for(int i = 0; i < size ; i++){
            List<Boat> ithList = new ArrayList<Boat>();
            for(int j = 0; j < size; j++){
                ithList.add(null);
            }
            map.add(ithList);
        }
    }

    public void placeBoat(String xString, String yString, String dirString) throws MalformedCommand {
        Direction direction = Direction.findDirectionByString(dirString);
        if(direction == null){
            throw new MalformedCommand("Direction should be N, S, E, or W, found : " + dirString);
        }
        int x = Integer.parseInt(xString);
        int y = Integer.parseInt(yString);
        map.get(x).set(y, new Boat(direction));
    }
}
