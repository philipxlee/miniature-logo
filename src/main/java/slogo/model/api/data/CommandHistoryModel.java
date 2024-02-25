package slogo.model.api.data;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import slogo.observer.AbstractObservable;

/**
 * CommandHistoryModel representing the history of commands.
 */
public class CommandHistoryModel extends AbstractObservable {

  private final Queue<String> commandHistory;

  /**
   * Constructor for CommandHistory. It initializes the command history queue and list of observers
   */
  public CommandHistoryModel() {
    super();
    this.commandHistory = new LinkedList<>();
  }

  /**
   * Adds a command to the command history queue.
   *
   * @param command the command to be added to the history
   */
  public void addCommand(String command) {
    commandHistory.add(command);
    notifyObservers();
  }

  /**
   * Provides an iterator over the command history.
   *
   * @return An Iterator<String> for the command history.
   */
  public Iterator<String> iterator() {
    return commandHistory.iterator();
  }
}
