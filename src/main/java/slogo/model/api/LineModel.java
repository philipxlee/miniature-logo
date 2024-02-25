package slogo.model.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import slogo.model.line.Line;
import slogo.observer.AbstractObservable;

/**
 * LineModel represents the state of all lines.
 */
public class LineModel extends AbstractObservable {

  private final List<Line> lines;

  /**
   * LineModel constructor. Initializes observers and line storage.
   */
  public LineModel() {
    lines = new ArrayList<>();
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
   * @return An Iterator<String> for the lines
   */
  public Iterator<Line> iterator() {
    return lines.iterator();
  }
}
