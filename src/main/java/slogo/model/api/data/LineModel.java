package slogo.model.api.data;

import java.util.ArrayDeque;
import java.util.Deque;
import slogo.model.line.Line;
import slogo.observer.AbstractObservable;

/**
 * LineModel represents the state of all lines.
 */
public class LineModel extends AbstractObservable {

  private final Deque<Line> lines;

  /**
   * LineModel constructor. Initializes observers and line storage.
   */
  public LineModel() {
    super();
    lines = new ArrayDeque<>();
  }

  /**
   * Add a line to the LineModel.
   *
   * @param line to add to LineModel
   */
  public void addLine(Line line) {
    lines.add(line);
    notifyObservers();
  }

  /**
   * Clear lines being stored.
   */
  public void clearLines() {
    lines.clear();
    notifyObservers();
  }

  /**
   * Provides an iterator over the Lines
   *
   * @return iterator over the Lines
   */
  public Line getLine() {
    return lines.pop();
  }

  /**
   * Returns the number of lines available
   *
   * @return number of lines available
   */
  public int getAvailableLines() {
    return lines.size();
  }
}
