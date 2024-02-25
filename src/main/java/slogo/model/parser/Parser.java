package slogo.model.parser;

import slogo.model.api.TurtleModel;
import slogo.model.command.Command;
import slogo.model.command.turtle.MoveCommand;
import slogo.model.command.turtle.PenCommand;
import slogo.model.command.turtle.RotateCommand;
import slogo.model.command.turtle.SetOrientationCommand;
import slogo.model.command.turtle.TurtleVisibleCommand;

/**
 * Parser module converts a string into a Command object, outfitted with the proper configuration
 */
public class Parser {

  private final TurtleModel turtleModel;

  /**
   * Parser constructor initializes a TurtleModel
   */
  public Parser(TurtleModel turtleModel) {
    this.turtleModel = turtleModel;
  }

  /**
   * Parse a string into the respective Command object
   *
   * @param command string representing the command with arguments. Example: "fd 50".
   */
  public Command parseCommand(String command) {
    String[] parts = command.trim().split("\\s+"); // Split by whitespace
    Command action = null;
    double number = Double.parseDouble(parts[1]);
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
      default -> System.out.println("Unknown command: " + parts[0] + " or number: " + parts[1]);
    }
    return action;
  }
}