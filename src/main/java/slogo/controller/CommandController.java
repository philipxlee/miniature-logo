package slogo.controller;

import slogo.exceptions.InvalidCommandException;
import slogo.model.api.CommandHistoryModel;
import slogo.model.api.LineModel;
import slogo.model.api.TurtleModel;
import slogo.model.command.Command;
import slogo.model.parser.Parser;
import slogo.observer.Observer;

/**
 * CommandController handles user input sent from the view
 */
public class CommandController {

  private final TurtleModel turtleModel;
  private final CommandHistoryModel commandHistoryModel;
  private final Parser parser;

  /**
   * CommandController constructor initializes new parser
   *
   * @param turtleModel         Turtle Model used for commands
   * @param commandHistoryModel Command History Model used for command history
   * @param lineModel           LineModel used for lines
   */
  public CommandController(TurtleModel turtleModel, CommandHistoryModel commandHistoryModel,
      LineModel lineModel) {
    this.turtleModel = turtleModel;
    this.commandHistoryModel = commandHistoryModel;
    this.parser = new Parser(turtleModel, lineModel);
  }

  /**
   * Executes the command by parsing the command and executing it. Example: "fd 50" to move the
   * turtle forward 50 pixels. The command is parsed and executed using the CommandParser class
   *
   * @param commandString the command to be executed as a string
   */
  public void executeCommand(String commandString) throws InvalidCommandException {
    Command command = parser.parseCommand(commandString);
    command.execute();
    commandHistoryModel.addCommand(commandString);
  }

  /**
   * Subscribe to updates from the TurtleModel
   *
   * @param observer that wants to subscribe
   */
  public void observeTurtle(Observer observer) {
    turtleModel.addObserver(observer);
  }

  /**
   * Subscribe to updates from the CommandHistoryModel
   *
   * @param observer that wants to subscribe
   */
  public void observeHistory(Observer observer) {
    commandHistoryModel.addObserver(observer);
  }
}
