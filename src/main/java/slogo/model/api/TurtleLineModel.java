package slogo.model.api;

public class TurtleLineModel {

  final double startX;
  final double startY;
  final double endX;
  final double endY;

  TurtleLineModel(double startX, double startY, double endX, double endY) {
    this.startX = startX;
    this.startY = startY;
    this.endX = endX;
    this.endY = endY;
  }

  /**
   * Get start X position
   * @return start X position
   */
  public double getStartX() {
    return startX;
  }

  /**
   * Get start Y position
   * @return start Y position
   */
  public double getStartY() {
    return startY;
  }

  /**
   * Get end X position
   * @return end X position
   */
  public double getEndX() {
    return endX;
  }

  /**
   * Get end Y position
   * @return  end Y position
   */
  public double getEndY() {
    return endY;
  }

}
