package slogo.model.api.command;

import slogo.exceptions.InvalidCommandException;
import slogo.model.api.command.turtle.ForwardCommand;
import slogo.model.api.command.turtle.RotateCommand;
import slogo.model.api.data.TurtleModel;

/**
 * CommandFactory will be responsible for creating commands (Factory design pattern.
 */
public class CommandFactory {

  private TurtleModel turtleModel;
//  private LineModel lineModel;

  /**
   * CommandFactory constructor. Initialized with turtleModel and lineModel.
   *
   * @param turtleModel is the turtle model.
   */
  public CommandFactory(TurtleModel turtleModel) {
    this.turtleModel = turtleModel;
//    this.lineModel = lineModel;
  }

  /**
   * Turns a string into a command.
   *
   * @param commandString is the command String
   * @param args          arguments for command
   * @return Command object representing parsed command.
   */
  public Command createCommand(String commandString, double... args)
      throws InvalidCommandException {
    return switch (commandString.toLowerCase()) {
      case "fd" -> new ForwardCommand(turtleModel, args[0]);
      case "bk" -> new ForwardCommand(turtleModel, -args[0]);
      case "lt" -> new RotateCommand(turtleModel, -args[0]);
      case "rt" -> new RotateCommand(turtleModel, args[0]);
      default -> throw new InvalidCommandException("Invalid Command String: " + commandString);
    };
  }
}
