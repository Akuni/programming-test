package net.thoughtmachine.exception;

/**
 * Created by SARROCHE Nicolas on 13/05/16.
 */
public class MalformedCommand extends Throwable {

    public MalformedCommand(String message){
        super(message);
    }
}
