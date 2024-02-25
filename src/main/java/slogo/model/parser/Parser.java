package slogo.model.parser;

import slogo.exceptions.InvalidCommandException;
import slogo.model.api.LineModel;
import slogo.model.api.TurtleModel;
import slogo.model.command.Command;
import slogo.model.command.turtle.ClearScreenCommand;
import slogo.model.command.turtle.MoveCommand;
import slogo.model.command.turtle.PenCommand;
import slogo.model.command.turtle.RotateCommand;
import slogo.model.command.turtle.SetLocationCommand;
import slogo.model.command.turtle.SetOrientationCommand;
import slogo.model.command.turtle.TurtleVisibleCommand;

/**
 * Parser module converts a string into a Command object, outfitted with the proper configuration
 */
public class Parser {

  private final TurtleModel turtleModel;
  private final LineModel lineModel;

  /**
   * Parser constructor initializes a TurtleModel
   */
  public Parser(TurtleModel turtleModel, LineModel lineModel) {
    this.turtleModel = turtleModel;
    this.lineModel = lineModel;
  }

  /**
   * Parse a string into the respective Command object.
   *
   * @param commandString string representing the command with arguments. Example: "fd 50".
   */
  public Command parseCommand(String commandString) throws InvalidCommandException {
    if (commandString == null || commandString.isEmpty()) {
      throw new InvalidCommandException("Command string is invalid or empty.");
    }

    String[] parts = commandString.trim().split("\\s+"); // Split by whitespace
    Command action = null;
    double number = 0;
    if (parts.length > 1) { // To click enter without a number for some commands
      number = Double.parseDouble(parts[1]);
    }
    switch (parts[0].toLowerCase()) {
      case "fd" -> action = new MoveCommand(turtleModel, number);
      case "bk" -> action = new MoveCommand(turtleModel, -number);
      case "lt" -> action = new RotateCommand(turtleModel, -number);
      case "rt" -> action = new RotateCommand(turtleModel, number);
      case "seth" -> action = new SetOrientationCommand(turtleModel, number);
      case "pd" -> action = new PenCommand(turtleModel, true);
      case "pu" -> action = new PenCommand(turtleModel, false);
      case "st" -> action = new TurtleVisibleCommand(turtleModel, true);
      case "ht" -> action = new TurtleVisibleCommand(turtleModel, false);
      case "towards" ->
          action = new SetOrientationCommand(turtleModel, number, Double.parseDouble(parts[2]));
      case "goto" ->
          action = new SetLocationCommand(turtleModel, number, Double.parseDouble(parts[2]));
      case "home" -> action = new SetLocationCommand(turtleModel, 0, 0);
      case "cs" -> action = new ClearScreenCommand(turtleModel, lineModel);
      default -> throw new InvalidCommandException("Invalid Command String");
    }
    return action;
  }
}