package net.thoughtmachine;

import net.thoughtmachine.command.AbstractCommand;
import net.thoughtmachine.entity.Board;
import net.thoughtmachine.exception.IllegalCommandException;
import net.thoughtmachine.exception.MalformedCommand;
import net.thoughtmachine.io.InputParser;
import net.thoughtmachine.io.TextInputParser;

import java.io.*;

public class Application {

  private static final String input = "/input.txt";
  private InputParser parser;

  private Board board = null;
  private boolean boatInitDone = false;



  public Application(InputParser parser){
    this.parser = parser;
  }

  public void loadInput() {
    InputStream is = getClass().getResourceAsStream(input);
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));

    try {
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void run(){
    String commandLine;
    /*while((commandLine = parser.getCommandLine()) != null){
      System.out.println(commandLine);
    }*/
    AbstractCommand command;
    try {
      while((command = parser.getCommand()) != null){
        System.out.println(command);
        command.process(this);
      }
    } catch (MalformedCommand malformedCommand) {
      malformedCommand.printStackTrace();
    } catch (IllegalCommandException e) {
      e.printStackTrace();
    }
  }

  public static void main(String... args) {
    Application app = new Application(new TextInputParser("/input.txt"));
    app.run();
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public boolean isBoatInitDone() {
    return boatInitDone;
  }

  public void setBoatInitDone(boolean boatInitDone) {
    this.boatInitDone = boatInitDone;
  }
}
