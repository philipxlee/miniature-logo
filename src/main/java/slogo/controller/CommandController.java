package slogo.controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import slogo.model.api.TurtleModel;
import slogo.model.commands.Command;
import slogo.model.parser.CommandParser;
import slogo.view.TurtleView;

public class CommandController {

  private final TurtleModel turtleModel;
  private final TurtleView turtleView;
  private final CommandParser parser;
  private final Queue<String> commandHistory;
  private Iterator<String> commandHistoryIterator;

  /**
   * Constructor for MainController Initializes the turtle model, turtle view, and command parser
   * for the program
   */
  public CommandController() {
    this.turtleModel = new TurtleModel();
    this.turtleView = new TurtleView();
    this.parser = new CommandParser(turtleModel);
    this.commandHistory = new LinkedList<>();
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
    commandHistory.add(command);
  }

  /**
   * Initializes or resets the iterator over the command history. Call this method when you want to
   * start retrieving commands from the beginning.
   */
  public void resetCommandHistoryIterator() {
    this.commandHistoryIterator = new LinkedList<>(commandHistory).iterator();
  }

  /**
   * Retrieves the next most recent command without altering the original command history.
   *
   * @return the next command in history if available, or null if there are no more commands.
   */
  public Optional<String> getMostRecentCommand() {
    return (commandHistoryIterator != null && commandHistoryIterator.hasNext()) ?
        Optional.of(commandHistoryIterator.next()) :
        Optional.empty();
  }

  private void setObserverComponents() {
    turtleModel.addObserver(turtleView);
  }
}
