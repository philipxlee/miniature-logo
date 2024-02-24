package slogo.controller;

import slogo.model.api.TurtleModel;
import slogo.model.command.Command;
import slogo.model.parser.Parser;
import slogo.observer.Observer;

/**
 * CommandController handles user input sent from the view
 */
public class CommandController {

  private final TurtleModel turtleModel;
  private final Parser parser;

  /**
   * CommandController constructor initializes new parser
   *
   * @param turtleModel: Turtle Model used for commands
   */
  public CommandController(TurtleModel turtleModel) {
    this.turtleModel = turtleModel;
    this.parser = new Parser(turtleModel);
  }

  /**
   * Executes the command by parsing the command and executing it. Example: "fd 50" to move the
   * turtle forward 50 pixels. The command is parsed and executed using the CommandParser class
   *
   * @param commandString the command to be executed as a string
   */
  public void executeCommand(String commandString) {
    Command command = parser.parseCommand(commandString);
    System.out.println("Executing command: " + commandString);
    command.execute();
  }

  /**
   * Subscribe to updates from the TurtleModel
   *
   * @param observer that wants to subscribe
   */
  public void observeTurtle(Observer observer) {
    turtleModel.addObserver(observer);
  }
}
