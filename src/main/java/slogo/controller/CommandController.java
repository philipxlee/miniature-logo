package slogo.controller;

import java.util.HashMap;
import java.util.Map;
import slogo.exceptions.InvalidCommandException;
import slogo.model.api.command.Command;
import slogo.model.api.data.CommandHistoryModel;
import slogo.model.api.data.LineModel;
import slogo.model.api.data.TurtleModel;
import slogo.model.api.parser.Parser;
import slogo.observer.Observer;

/**
 * CommandController handles user input sent from the view.
 */
public class CommandController {

  private final TurtleModel turtleModel;
  private final LineModel lineModel;
  private final CommandHistoryModel commandHistoryModel;
  private final Parser parser;
  private Map<String, Double> variables;

  /**
   * CommandController constructor initializes new parser.
   *
   * @param turtleModel         Turtle Model used for commands
   * @param lineModel           LineModel used for lines
   * @param commandHistoryModel Command History Model used for command history
   */
  public CommandController(TurtleModel turtleModel, LineModel lineModel,
      CommandHistoryModel commandHistoryModel) {
    this.turtleModel = turtleModel;
    this.lineModel = lineModel;
    this.commandHistoryModel = commandHistoryModel;
    this.parser = new Parser(turtleModel, lineModel);
    this.variables = new HashMap<>();
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
   * Subscribe to updates from the TurtleModel.
   *
   * @param observer that wants to subscribe
   */
  public void observeTurtle(Observer observer) {
    turtleModel.addObserver(observer);
  }

  /**
   * Subscribe to updates from the CommandHistoryModel.
   *
   * @param observer that wants to subscribe
   */
  public void observeHistory(Observer observer) {
    commandHistoryModel.addObserver(observer);
  }

  /**
   * Subscribe to updates from the LineModel.
   *
   * @param observer that wants to subscribe
   */
  public void observeLines(Observer observer) {
    lineModel.addObserver(observer);
  }
}
