package slogo.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractObservable defines base functionality of Observable interface.
 */
public abstract class AbstractObservable implements Observable {

  private final List<Observer> observers = new ArrayList<>();

  /**
   * Add observer to list.
   *
   * @param observer observer wanting to subscribe
   */
  @Override
  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  /**
   * Remove observer from list.
   *
   * @param observer observer wanting to unsubscribe
   */
  @Override
  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  /**
   * Notify observers of update.
   */
  @Override
  public void notifyObservers() {
    for (Observer observer : observers) {
      observer.update(this);
    }
  }
}
