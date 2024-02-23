package slogo.model.api;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class CommandHistory {

  private final Queue<String> commandHistory;
  private Iterator<String> commandHistoryIterator;

  /**
   * Constructor for CommandHistory. It initializes the command history queue.
   */
  public CommandHistory() {
    this.commandHistory = new LinkedList<>();
  }

  /**
   * Adds a command to the command history queue.
   *
   * @param command the command to be added to the history
   */
  public void addCommand(String command) {
    commandHistory.add(command);
    resetIterator(); // Reset iterator each time a new command is added to ensure it's in sync
  }

  /**
   * Retrieves the next most recent command without altering the original command history.
   *
   * @return the next command in history if available, or optional empty if not
   */
  public Optional<String> getNextCommand() {
    return (commandHistoryIterator != null && commandHistoryIterator.hasNext()) ?
        Optional.of(commandHistoryIterator.next()) :
        Optional.empty();
  }

  /**
   * Initializes or resets the iterator over the command history. Call this method when you want to
   * start retrieving commands from the beginning.
   */
  private void resetIterator() {
    this.commandHistoryIterator = commandHistory.iterator();
  }
}
