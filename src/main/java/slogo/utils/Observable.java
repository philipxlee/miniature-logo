package slogo.utils;

/**
 * Observable interface to define modules that act as a producer
 *
 * @author Arnav Nayak
 */
public interface Observable {

  /**
   * Add new observer to state
   *
   * @param observer: observer wanting to subscribe
   */
  void addObserver(Observer observer);

  /**
   * Remove observer from state
   *
   * @param observer: observer wanting to unsubscribe
   */
  void removeObserver(Observer observer);

  /**
   * Notify observers of state change
   */
  void notifyObservers();
}
