package slogo.model.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import slogo.observer.Observable;
import slogo.observer.Observer;

/**
 * CommandHistoryMode representing the history of commands
 */
public class CommandHistoryModel implements Observable {

  private final List<Observer> observers;
  private final Queue<String> commandHistory;

  /**
   * Constructor for CommandHistory. It initializes the command history queue and list of observers
   */
  public CommandHistoryModel() {
    this.observers = new ArrayList<>();
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

  /**
   * Add observer to list of observers.
   */
  @Override
  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  /**
   * Remove observer from list of observers.
   */
  @Override
  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  /**
   * Notify all observers of state change.
   */
  @Override
  public void notifyObservers() {
    observers.forEach(observer -> observer.update(this));
  }
}
