package slogo.model.command;

import slogo.exceptions.InvalidCommandException;
import slogo.model.api.LineModel;
import slogo.model.api.TurtleModel;
import slogo.model.command.turtle.ForwardCommand;
import slogo.model.command.turtle.RotateCommand;

public class CommandFactory {

  private TurtleModel turtleModel;
  private LineModel lineModel;

  public CommandFactory(TurtleModel turtleModel, LineModel lineModel) {
    this.turtleModel = turtleModel;
    this.lineModel = lineModel;
  }

  public Command createCommand(String command, double... args) throws InvalidCommandException {
    return switch (command.toLowerCase()) {
      case "fd" -> new ForwardCommand(turtleModel, args[0]);
      case "bk" -> new ForwardCommand(turtleModel, -args[0]);
      case "lt" -> new RotateCommand(turtleModel, -args[0]);
      case "rt" -> new RotateCommand(turtleModel, args[0]);
      default -> throw new InvalidCommandException("Invalid Command String: " + command);
    };
  }
}
