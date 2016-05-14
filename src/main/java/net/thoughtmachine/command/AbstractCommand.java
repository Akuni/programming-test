package net.thoughtmachine.command;

import net.thoughtmachine.Application;
import net.thoughtmachine.exception.IllegalCommandException;
import net.thoughtmachine.exception.MalformedCommand;

/**
 * Created by SARROCHE Nicolas on 13/05/16.
 */
public abstract class AbstractCommand {

    protected String initialCommand;

    protected AbstractCommand(String initialCommand){
        this.initialCommand = initialCommand;
    }

    public abstract boolean process(Application application) throws IllegalCommandException, MalformedCommand;
}
