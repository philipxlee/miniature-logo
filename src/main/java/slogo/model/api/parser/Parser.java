package slogo.model.api.parser;

import java.util.Arrays;
import slogo.exceptions.InvalidCommandException;
import slogo.model.api.command.Command;
import slogo.model.api.command.CommandFactory;
import slogo.model.api.data.LineModel;
import slogo.model.api.data.TurtleModel;

/**
 * Parser module converts a string into a Command object, outfitted with the proper configuration.
 */
public class Parser {

  private final CommandFactory commandFactory;


  /**
   * Parser constructor initializes a TurtleModel.
   */
  public Parser(TurtleModel turtleModel, LineModel lineModel) {
    commandFactory = new CommandFactory(turtleModel, lineModel);
  }

  /**
   * Parse a string into the respective Command object.
   *
   * @param commandString string representing the command with arguments. Example: "fd 50".
   * @return Command representing the parsed command.
   */
  public Command parseCommand(String commandString) throws InvalidCommandException {
    if (commandString == null || commandString.isEmpty()) {
      throw new InvalidCommandException("Command string is invalid or empty.");
    }

    String[] parts = commandString.trim().split("\\s+");
    double[] numbers = Arrays.stream(parts).skip(1)
        .mapToDouble(Double::parseDouble)
        .toArray();

    return commandFactory.createCommand(parts[0], numbers);
  }
}