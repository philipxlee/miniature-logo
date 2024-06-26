package slogo.observer;

/**
 * Observer interface to define modules that act as a subscriber (Observer design pattern).
 */
public interface Observer {

  /**
   * Update function defines behavior when a producer notifies this consumer.
   *
   * @param observable Observable that triggered a notification
   */
  void update(Observable observable);
}
