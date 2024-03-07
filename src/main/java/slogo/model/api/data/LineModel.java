package slogo.model.api.data;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Optional;
import slogo.model.api.line.Line;
import slogo.observer.AbstractObservable;

/**
 * LineModel represents the state of all lines.
 */
public class LineModel extends AbstractObservable {

  private final Deque<Optional<Line>> lines;

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
    lines.add(Optional.ofNullable(line));
    notifyObservers();
  }

  /**
   * Clear lines being stored by adding an empty line to queue.
   */
  public void clearLines() {
    lines.add(Optional.empty());
    notifyObservers();
  }

  /**
   * Returns an iterator on the lines.
   *
   * @return Iterator<Line> object
   */
  public Iterator<Optional<Line>> iterator() {
    return lines.iterator();
  }

  /**
   * Provides an iterator over the Lines.
   *
   * @return iterator over the Lines
   */
  public Optional<Line> getLine() {
    return lines.pop();
  }

  /**
   * Returns the number of lines available.
   *
   * @return number of lines available
   */
  public int getAvailableLines() {
    return lines.size();
  }
}
