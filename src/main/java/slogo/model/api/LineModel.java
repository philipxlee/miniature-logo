package slogo.model.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import slogo.model.line.Line;
import slogo.observer.Observable;
import slogo.observer.Observer;

/**
 * LineModel represents the state of all lines.
 */
public class LineModel implements Observable {

  private final List<Observer> observers;
  private final List<Line> lines;

  /**
   * LineModel constructor. Initializes observers and line storage.
   */
  public LineModel() {
    observers = new ArrayList<>();
    lines = new ArrayList<>();
  }

  /**
   * Add a line to the LineModel
   *
   * @param line to add to LineModel
   */
  public void addLine(Line line) {
    lines.add(line);
    notifyObservers();
  }

  /**
   * Provides an iterator over the Lines
   *
   * @return An Iterator<String> for the lines
   */
  public Iterator<Line> iterator() {
    return lines.iterator();
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
