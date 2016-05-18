package net.thoughtmachine.io;

import net.thoughtmachine.entity.Board;
import net.thoughtmachine.entity.Boat;

import java.io.*;

/**
 * Created by SARROCHE Nicolas on 18/05/16.
 */
public class TextOutputWriter implements OutputWriter {

    private BufferedWriter writer;

    public TextOutputWriter(String input) {
        try {
            writer =  new BufferedWriter( new FileWriter(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void write(Board board) throws IOException {
        for(Boat boat : board.getFloatingBoats()){
            writer.write(boat.toString());
            writer.write("\n");
        }
        for(Boat boat : board.getSunkBoats()){
            writer.write(boat.toString() + " SUNK");
            writer.write("\n");
        }
        writer.close();
    }
}
