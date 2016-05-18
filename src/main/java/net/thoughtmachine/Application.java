package net.thoughtmachine;

import net.thoughtmachine.command.AbstractCommand;
import net.thoughtmachine.entity.Board;
import net.thoughtmachine.exception.IllegalCommandException;
import net.thoughtmachine.exception.MalformedCommand;
import net.thoughtmachine.io.InputParser;
import net.thoughtmachine.io.OutputWriter;
import net.thoughtmachine.io.TextInputParser;
import net.thoughtmachine.io.TextOutputWriter;

import java.io.IOException;


public class Application {

  private InputParser parser;
  private OutputWriter writer;

  private Board board = null;


  public Application(InputParser parser, OutputWriter writer){
    this.parser = parser;
    this.writer = writer;
  }

  public void run(){
    AbstractCommand command;
    // run through the command file
    try {
      while((command = parser.getCommand()) != null){
        command.process(this, this.board);
      }
    } catch (MalformedCommand malformedCommand) {
      malformedCommand.printStackTrace();
    } catch (IllegalCommandException e) {
      e.printStackTrace();
    }

    // save the result in the output file
    try {
      writer.write(board);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public static void main(String... args) {
    Application app = new Application(new TextInputParser("/input.txt"), new TextOutputWriter("./output.txt"));
    app.run();
  }

  public Board getBoard(){
    return board;
  }
}
