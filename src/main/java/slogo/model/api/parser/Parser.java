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

  private final TurtleModel turtleModel;
  private final LineModel lineModel;
//  private Properties commandMappings;
//  private String language;

  /**
   * Parser constructor initializes a TurtleModel.
   */
  public Parser(TurtleModel turtleModel, LineModel lineModel) {
    this.turtleModel = turtleModel;
    this.lineModel = lineModel;
//    this.language = "English";
//    initializeCommandMappings();
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

    String[] parts = commandString.trim().split("\\s+");
    double[] numbers = Arrays.stream(parts).skip(1)
        .mapToDouble(Double::parseDouble)
        .toArray();
    CommandFactory factory = new CommandFactory(turtleModel, lineModel);
    return factory.createCommand(parts[0], numbers);
  }

//  /**
//   * Initialize properties for command names.
//   */
//  private void initializeCommandMappings() {
//    commandMappings = new Properties();
//    try (InputStream inputStream = getClass().getResourceAsStream(
//        "/slogo/example/languages/" + language + ".properties")) {
//      commandMappings.load(inputStream);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
//
//  /**
//   * Convert a command name into the canonical name of command.
//   *
//   * @param commandName is the string representing the command
//   * @return String representing the canonical name of the command
//   */
//  private String findCanonicalName(String commandName) {
//    String canonicalName = "";
//    for (String key : commandMappings.stringPropertyNames()) {
//      String value = commandMappings.getProperty(key);
//      String[] parts = value.split("\\|");
//      for (String part : parts) {
//        if (part.equals(commandName)) {
//          canonicalName = key;
//          break;
//        }
//      }
//      if (!canonicalName.isEmpty()) {
//        break;
//      }
//    }
//    return canonicalName;
//  }
//
//  /**
//   * Convert command string to Command object using reflection.
//   *
//   * @param commandName is the command string
//   * @return Command object representing the command to execute.
//   */
//  private Command reflect(String commandName) {
//    try {
//      String canonicalName = findCanonicalName(commandName);
//      if (!canonicalName.isEmpty()) {
//        Class<?> clazz = Class.forName("slogo.model.command." + commandName + "Command");
//        return (Command) clazz.getDeclaredConstructor(turtleModel.getClass())
//            .newInstance(turtleModel);
//      }
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return null;
//  }
}