package net.thoughtmachine;

import net.thoughtmachine.command.AbstractCommand;
import net.thoughtmachine.entity.Board;
import net.thoughtmachine.exception.IllegalCommandException;
import net.thoughtmachine.exception.MalformedCommand;
import net.thoughtmachine.io.InputParser;
import net.thoughtmachine.io.TextInputParser;


public class Application {

  private InputParser parser;

  private Board board = null;


  public Application(InputParser parser){
    this.parser = parser;
  }

  public void run(){
    AbstractCommand command;
    try {
      while((command = parser.getCommand()) != null){
        command.process(this, this.board);
      }
    } catch (MalformedCommand malformedCommand) {
      malformedCommand.printStackTrace();
    } catch (IllegalCommandException e) {
      e.printStackTrace();
    }
    board.printResult();
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public static void main(String... args) {
    Application app = new Application(new TextInputParser("/input.txt"));
    app.run();
  }

  public Board getBoard(){
    return board;
  }
}
