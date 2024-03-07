package slogo.controller.command;

import slogo.exceptions.InvalidCommandException;
import slogo.model.api.command.Command;
import slogo.model.api.data.CommandHistoryModel;
import slogo.model.api.data.LineModel;
import slogo.model.api.data.TurtleModel;
import slogo.model.api.data.VariablesModel;
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
  private final VariablesModel variablesModel;

  /**
   * CommandController constructor initializes new parser.
   *
   * @param turtleModel         Turtle Model used for commands
   * @param lineModel           LineModel used for lines
   * @param commandHistoryModel Command History Model used for command history
   */
  public CommandController(TurtleModel turtleModel, LineModel lineModel,
      CommandHistoryModel commandHistoryModel, VariablesModel variablesModel) {
    this.turtleModel = turtleModel;
    this.lineModel = lineModel;
    this.commandHistoryModel = commandHistoryModel;
    this.parser = new Parser(turtleModel, lineModel);
    this.variablesModel = variablesModel;
  }

  /**
   * Executes the command by parsing the command and executing it. Example: "fd 50" to move the
   * turtle forward 50 pixels. The command is parsed and executed using the CommandParser class
   *
   * @param commandString the command to be executed as a string
   */
  public void executeCommand(String commandString) throws InvalidCommandException {
    if (commandString.startsWith("make :")) {
      defineVariable(commandString);
    } else {
      Command command = parser.parseCommand(commandString);
      command.execute();
      commandHistoryModel.addCommand(commandString);
    }
  }

  /**
   * Define a new variable.
   *
   * @param commandString the command to define a variable
   * @throws InvalidCommandException if the command to define a variable is invalid
   */
  private void defineVariable(String commandString) throws InvalidCommandException {
    // Split the commandString into parts to extract variable name and value
    String[] parts = commandString.trim().split("\\s+");
    if (parts.length != 3) {
      throw new InvalidCommandException("Invalid 'make' command");
    }

    String variable = parts[1];
    double value;
    try {
      value = Double.parseDouble(parts[2]);
    } catch (NumberFormatException e) {
      throw new InvalidCommandException("Invalid number format");
    }

    variablesModel.setVariable(variable, value);
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

  /**
   * Subscribe to updates from the VariablesModel.
   *
   * @param observer that wants to subscribe
   */
  public void observeVariables(Observer observer) {
    variablesModel.addObserver(observer);
  }
}
