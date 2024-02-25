package slogo.model.api;

import slogo.model.line.Line;
import slogo.observer.AbstractObservable;

/**
 * TurtleModel representing the state of the Turtle
 */
public class TurtleModel extends AbstractObservable {

  private final LineModel lineModel;
  private double positionX;
  private double positionY;
  private double orientation;
  private boolean penDown;
  private boolean visible;

  /**
   * TurtleModel constructor.
   */
  public TurtleModel(LineModel lineModel) {
    this.lineModel = lineModel;
    this.positionX = 0;
    this.positionY = 0;
    this.orientation = 0;
    this.penDown = false;
    this.visible = true;
  }

  /**
   * Move Turtle forward or backward based on distance
   *
   * @param distance distance to move Turtle
   */
  public double moveTurtle(double distance) {
    // calculate change in X and Y based on orientation angle and distance
    double orientationRadians = Math.toRadians(orientation);
    double deltaX = distance * Math.cos(orientationRadians);
    double deltaY = distance * Math.sin(orientationRadians);
    double oldX = positionX;
    double oldY = positionY;

    // update X and Y position
    positionX += deltaX;
    positionY += deltaY;

    // draw line if pen is down
    if (penDown) {
      lineModel.addLine(new Line(oldX, oldY, positionX, positionY));
    }

    // notify observers about position change
    notifyObservers();

    // return distance travelled
    return Math.sqrt(Math.pow(positionX - oldX, 2) + Math.pow(positionY - oldY, 2));
  }

  /**
   * Rotate Turtle clockwise
   *
   * @param angle angle to rotate turtle by
   */
  public double rotateTurtle(double angle) {
    // update angle
    orientation += angle;

    // ensure angle remains within 360 degrees
    orientation = orientation % 360;
    if (orientation < 0) {
      orientation += 360;
    }

    // notify observers about orientation change
    notifyObservers();

    // angle travelled
    return Math.abs(angle);
  }

  /**
   * Set Location of turtle
   *
   * @param newX is new X position
   * @param newY is new Y position
   */
  public double setLocation(double newX, double newY) {
    double distance = Math.sqrt(Math.pow(newX - positionX, 2) + Math.pow(newY - positionY, 2));
    positionX = newX;
    positionY = newY;
    notifyObservers();
    return distance;
  }

  /**
   * @return X position of Turtle
   */
  public double getPositionX() {
    return positionX;
  }

  /**
   * @return Y position of Turtle
   */
  public double getPositionY() {
    return positionY;
  }

  /**
   * @return orientation of Turtle
   */
  public double getOrientation() {
    return orientation;
  }

  /**
   * Sets orientation of turtle
   *
   * @param angle is the new orientation of the turtle
   */
  public void setOrientation(double angle) {
    this.orientation = angle;
    notifyObservers();
  }

  /**
   * Get the state of the pen
   *
   * @return true if pen is down, false otherwise
   */
  public boolean getPenDown() {
    return penDown;
  }

  /**
   * Sets pen boolean of turtle.
   *
   * @param penDown is the new state of the pen
   */
  public void setPenDown(boolean penDown) {
    this.penDown = penDown;
  }

  /**
   * Get visibility of turtle.
   *
   * @return true if turtle is shown, false otherwise
   */
  public boolean getVisible() {
    return visible;
  }

  /**
   * Sets visibility of turtle.
   *
   * @param visible is the boolean to set visibility to
   */
  public void setVisible(boolean visible) {
    this.visible = visible;
    notifyObservers();
  }

  /**
   * Set the turtle's position to the given (x, y) coordinates.
   */
  public void faceDirection(double targetX, double targetY) {
    // Calculate the angle needed to rotate the turtle to face the new (x, y) position
    double angleToTarget = Math.toDegrees(
        Math.atan2(targetY - this.positionY, targetX - this.positionX));
    this.orientation = angleToTarget >= 0 ? angleToTarget : 360 + angleToTarget;
    notifyObservers();
  }
}
