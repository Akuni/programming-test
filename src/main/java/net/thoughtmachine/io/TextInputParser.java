package net.thoughtmachine.io;

import net.thoughtmachine.command.*;
import net.thoughtmachine.exception.MalformedCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * Created by SARROCHE Nicolas on 13/05/16.
 */
public class TextInputParser implements InputParser {


    private BufferedReader reader;

    public TextInputParser(String input){
        InputStream is = getClass().getResourceAsStream(input);
        this.reader = new BufferedReader(new InputStreamReader(is));
    }


    @Override
    public String getCommandLine() {

        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AbstractCommand getCommand() throws MalformedCommand {
        try {
            // read command
            String line = reader.readLine();
            if(line == null)
                return null;

            // maybe an init command
            if(Pattern.matches("[0-9]+", line))
                return new InitBoardCommand(line);
            if(Pattern.matches("\\([0-9]+, [0-9]+\\)", line))
                return new AttackBoatCommand(line);
            if(Pattern.matches("[\\([0-9]+, [0-9]+, [NSEW]{1}\\) ]+", line))
                return new InitBoatCommand(line);
            if(Pattern.matches("\\([0-9]+, [0-9]+\\) [MLR]+", line))
                return new MoveBoatCommand(line);

            throw new MalformedCommand("Invalid command " + line);

            // maybe
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
