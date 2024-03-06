package slogo.model.api.data;

import slogo.model.api.line.Line;
import slogo.observer.AbstractObservable;

/**
 * TurtleModel representing the state of the Turtle.
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
    super();
    this.lineModel = lineModel;
    this.positionX = 0;
    this.positionY = 0;
    this.orientation = 0;
    this.penDown = false;
    this.visible = true;
  }

  /**
   * Move Turtle forward or backward based on distance.
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
   * Rotate Turtle clockwise.
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
   * Set Location of turtle.
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
   * Set turtle's heading to an absolute angle.
   *
   * @param degrees New heading angle for the turtle.
   * @return The absolute difference between the new and old heading.
   */
  public double setHeading(double degrees) {
    double oldOrientation = orientation;
    orientation = degrees % 360;
    if (orientation < 0) {
      orientation += 360;
    }
    notifyObservers();
    double angleDifference = Math.abs(orientation - oldOrientation);
    angleDifference = angleDifference > 180 ? 360 - angleDifference : angleDifference;
    return angleDifference;
  }


  /**
   * Get X position of turtle.
   *
   * @return X position of Turtle
   */
  public double getPositionX() {
    return positionX;
  }

  /**
   * Get Y position of turtle.
   *
   * @return Y position of Turtle
   */
  public double getPositionY() {
    return positionY;
  }

  /**
   * Get orientation of turtle.
   *
   * @return orientation of Turtle
   */
  public double getOrientation() {
    return orientation;
  }

  /**
   * Sets orientation of turtle.
   *
   * @param angle is the new orientation of the turtle
   */
  public void setOrientation(double angle) {
    this.orientation = angle;
    notifyObservers();
  }

  /**
   * Get the state of the pen.
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
   * Turns turtle to face the point (x, y), where (0, 0) is the center of the screen.This is the
   * towards command.
   *
   * @param targetX X-coordinate of the target point.
   * @param targetY Y-coordinate of the target point.
   * @return The number of degrees the turtle turned.
   */
  public Double faceDirection(double targetX, double targetY) {
    double deltaX = targetX - this.positionX;
    double deltaY = targetY - this.positionY;
    double targetAngle = Math.toDegrees(Math.atan2(deltaY, deltaX));
    targetAngle = targetAngle < 0 ? 360 + targetAngle : targetAngle;

    // Calculate the smallest difference between the current orientation and the target angle.
    double angleDifference = targetAngle - this.orientation;
    if (angleDifference > 180) {
      angleDifference -= 360;
    } else if (angleDifference < -180) {
      angleDifference += 360;
    }

    this.orientation = targetAngle;
    notifyObservers();
    return Math.abs(angleDifference);
  }
}
