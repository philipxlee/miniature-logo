package slogo.controller;

import slogo.model.api.TurtleModel;
import slogo.model.command.Command;
import slogo.model.parser.CommandParser;

public class CommandController {

  private final TurtleModel turtleModel;
  private final CommandParser parser;

  /**
   * Constructor for MainController Initializes the turtle model, turtle view, and command parser
   * for the program
   */
  public CommandController() {
    this.turtleModel = new TurtleModel();
    this.parser = new CommandParser(turtleModel);
  }

  /**
   * Executes the command by parsing the command and executing it. Example: "fd 50" to move the
   * turtle forward 50 pixels. The command is parsed and executed using the CommandParser class
   *
   * @param command the command to be executed
   */
  public void executeCommand(String command) {
    System.out.println("Executing command: " + command);
    Command cmd = parser.parseCommand(command);
    cmd.execute();
  }

  /**
   * Returns the turtle model
   *
   * @return the turtle model
   */
  public TurtleModel getTurtleModel() {
    return turtleModel;
  }
}
