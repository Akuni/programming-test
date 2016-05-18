package net.thoughtmachine.io;

import net.thoughtmachine.entity.Board;

import java.io.IOException;

/**
 * Created by SARROCHE Nicolas on 18/05/16.
 */
public interface OutputWriter {

    public void write(Board board) throws IOException;
}
