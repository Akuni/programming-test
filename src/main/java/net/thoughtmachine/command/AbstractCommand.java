package net.thoughtmachine.command;

import net.thoughtmachine.Application;
import net.thoughtmachine.exception.IllegalCommandException;

/**
 * Created by SARROCHE Nicolas on 13/05/16.
 */
public abstract class AbstractCommand {

    public abstract boolean process(Application application) throws IllegalCommandException;
}
