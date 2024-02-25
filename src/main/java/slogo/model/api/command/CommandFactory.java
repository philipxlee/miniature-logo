package slogo.model.api.command;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import slogo.exceptions.InvalidCommandException;
import slogo.model.api.command.turtle.ClearScreenCommand;
import slogo.model.api.command.turtle.ForwardCommand;
import slogo.model.api.command.turtle.PenCommand;
import slogo.model.api.command.turtle.RotateCommand;
import slogo.model.api.command.turtle.SetLocationCommand;
import slogo.model.api.command.turtle.SetOrientationCommand;
import slogo.model.api.command.turtle.TurtleVisibleCommand;
import slogo.model.api.data.LineModel;
import slogo.model.api.data.TurtleModel;

/**
 * CommandFactory will be responsible for creating commands (Factory design pattern.
 */
public class CommandFactory {

  private TurtleModel turtleModel;
  private LineModel lineModel;
  private Properties commandMappings;
  private String language;

  /**
   * CommandFactory constructor. Initialized with turtleModel and lineModel.
   *
   * @param turtleModel is the turtle model.
   * @param lineModel   is the line model.
   */
  public CommandFactory(TurtleModel turtleModel, LineModel lineModel) {
    this.turtleModel = turtleModel;
    this.lineModel = lineModel;
    this.language = "English";
    initializeCommandMappings();
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
      case "seth" -> new SetOrientationCommand(turtleModel, args[0]);
      case "pd" -> new PenCommand(turtleModel, true);
      case "pu" -> new PenCommand(turtleModel, false);
      case "st" -> new TurtleVisibleCommand(turtleModel, true);
      case "ht" -> new TurtleVisibleCommand(turtleModel, false);
      case "towards" -> new SetOrientationCommand(turtleModel, args[0], args[1]);
      case "goto" -> new SetLocationCommand(turtleModel, args[0], args[1]);
      case "home" -> new SetLocationCommand(turtleModel, 0, 0);
      case "cs" -> new ClearScreenCommand(turtleModel, lineModel);
      default -> throw new InvalidCommandException("Invalid Command String: " + commandString);
    };
  }

  /**
   * Initialize properties for command names.
   */
  private void initializeCommandMappings() {
    commandMappings = new Properties();
    try (InputStream inputStream = getClass().getResourceAsStream(
        "/slogo/example/languages/" + language + ".properties")) {
      commandMappings.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

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
}
