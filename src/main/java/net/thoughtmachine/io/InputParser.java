package net.thoughtmachine.io;

import net.thoughtmachine.command.AbstractCommand;
import net.thoughtmachine.exception.MalformedCommand;

/**
 * Created by SARROCHE Nicolas on 13/05/16.
 */
public interface InputParser {

    public String getCommandLine();

    public AbstractCommand getCommand() throws MalformedCommand;
}
