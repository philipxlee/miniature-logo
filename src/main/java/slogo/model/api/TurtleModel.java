package slogo.model.api;

import java.util.ArrayList;
import java.util.List;
import slogo.model.line.Line;
import slogo.observer.Observable;
import slogo.observer.Observer;

/**
 * TurtleModel representing the state of the Turtle
 */
public class TurtleModel implements Observable {

  private final List<Observer> observers;
  private final LineModel lineModel;
  private double x;
  private double y;
  private double orientation;
  private boolean penDown;
  private boolean visible;

  /**
   * TurtleModel constructor
   */
  public TurtleModel(LineModel lineModel) {
    this.observers = new ArrayList<>();
    this.lineModel = lineModel;
    this.x = 0;
    this.y = 0;
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
    double oldX = x;
    double oldY = y;

    // update X and Y position
    x += deltaX;
    y += deltaY;

    // draw line if pen is down
    if (penDown) {
      lineModel.addLine(new Line(oldX, oldY, x, y));
    }

    // notify observers about position change
    notifyObservers();

    // return distance travelled
    return Math.sqrt(Math.pow(x - oldX, 2) + Math.pow(y - oldY, 2));
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
   * Add observer to list of observers. Subscribing to the turtle will also subscribe you to its
   * lines
   */
  @Override
  public void addObserver(Observer observer) {
    observers.add(observer);
    lineModel.addObserver(observer);
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

  /**
   * Set Location of turtle
   *
   * @param newX is new X position
   * @param newY is new Y position
   */
  public double setLocation(double newX, double newY) {
    double distance = Math.sqrt(Math.pow(newX - x, 2) + Math.pow(newY - y, 2));
    x = newX;
    y = newY;
    notifyObservers();
    return distance;
  }

  /**
   * Sets pen boolean of turtle
   */
  public void setPenDown(boolean penDown) {
    this.penDown = penDown;
  }

  /**
   * @return X position of Turtle
   */
  public double getX() {
    return x;
  }

  /**
   * @return Y position of Turtle
   */
  public double getY() {
    return y;
  }

  /**
   * @return orientation of Turtle
   */
  public double getOrientation() {
    return orientation;
  }

  /**
   * @return true if pen is down, false otherwise
   */
  public boolean getPenDown() {
    return penDown;
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
   * @return true if turtle is shown, false otherwise
   */
  public boolean getVisible() {
    return visible;
  }

  /**
   * Sets visibility of turtle
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
  public void faceXY(double targetX, double targetY) {
    // Calculate the angle needed to rotate the turtle to face the new (x, y) position
    double angleToTarget = Math.toDegrees(Math.atan2(targetY - this.y, targetX - this.x));
    this.orientation = angleToTarget >= 0 ? angleToTarget : 360 + angleToTarget;
    notifyObservers();
  }
}
