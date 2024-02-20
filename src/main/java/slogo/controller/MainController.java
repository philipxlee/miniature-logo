package slogo.controller;

import slogo.model.api.TurtleModel;
import slogo.model.commands.Command;
import slogo.model.parser.CommandParser;
import slogo.view.TurtleView;


public class MainController {

  private final TurtleModel turtleModel;
  private final TurtleView turtleView;
  private final CommandParser parser;

  /**
   * Constructor for MainController Initializes the turtle model, turtle view, and command parser
   * for the program
   */
  public MainController() {
    this.turtleModel = new TurtleModel();
    this.turtleView = new TurtleView();
    this.parser = new CommandParser(turtleModel);
  }

  /**
   * Starts the program by initializing the components and setting the observer components
   */
  public void startProgram() {
    setObserverComponents();
  }

  /**
   * Executes the command by parsing the command and executing it. Example: "fd 50" to move the
   * turtle forward 50 pixels. The command is parsed and executed using the CommandParser class
   *
   * @param command the command to be executed
   */
  public void executeCommand(String command) {
    Command cmd = parser.parseCommand(command);
    cmd.execute();
  }

  private void setObserverComponents() {
    turtleModel.addObserver(turtleView);
  }
}
