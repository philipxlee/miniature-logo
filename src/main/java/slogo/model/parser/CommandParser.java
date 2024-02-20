package slogo.model.parser;

import slogo.model.api.TurtleModel;
import slogo.model.commands.Command;
import slogo.model.commands.MoveCommand;
import slogo.model.commands.RotateCommand;
import slogo.model.commands.SetOrientationCommand;

public class CommandParser {

  private final TurtleModel turtleModel;

  public CommandParser(TurtleModel turtleModel) {
    this.turtleModel = turtleModel;
  }

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
      default -> System.out.println("Unknown command: " + parts[0] + " or number: " + parts[1]);
    }
    System.out.println("Parsed command");
    return action;
  }
}