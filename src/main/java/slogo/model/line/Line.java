package slogo.model.line;

/**
 * Line class represents the points of a line.
 */
public class Line {

  final double startX;
  final double startY;
  final double endX;
  final double endY;

  /**
   * Line constructor.
   *
   * @param startX start x of line
   * @param startY start y of line
   * @param endX   end x of line
   * @param endY   end y of line
   */
  public Line(double startX, double startY, double endX, double endY) {
    this.startX = startX;
    this.startY = startY;
    this.endX = endX;
    this.endY = endY;
  }

  /**
   * Get start X position
   *
   * @return start X position
   */
  public double getStartX() {
    return startX;
  }

  /**
   * Get start Y position
   *
   * @return start Y position
   */
  public double getStartY() {
    return startY;
  }

  /**
   * Get end X position
   *
   * @return end X position
   */
  public double getEndX() {
    return endX;
  }

  /**
   * Get end Y position
   *
   * @return end Y position
   */
  public double getEndY() {
    return endY;
  }
}
