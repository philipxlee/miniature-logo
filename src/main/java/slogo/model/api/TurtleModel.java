package slogo.model.api;

import java.util.ArrayList;
import java.util.List;
import slogo.observer.Observable;
import slogo.observer.Observer;

/**
 * TurtleModel representing the state of the Turtle
 */
public class TurtleModel implements Observable {

  private final List<Observer> observers;
  private final List<TurtleLineModel> lines;
  private double x;
  private double y;
  private double orientation;
  private boolean penDown;

  /**
   * TurtleModel constructor
   */
  public TurtleModel() {
    this.observers = new ArrayList<>();
    this.lines = new ArrayList<>();
    this.penDown = false;
    this.x = 0;
    this.y = 0;
    this.orientation = 0;
  }

  /**
   * Move Turtle forward or backward based on distance
   *
   * @param distance distance to move Turtle
   */
  public void moveTurtle(double distance) {
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
      lines.add(new TurtleLineModel(oldX, oldY, x, y));
    }

    // notify observers about position change
    notifyObservers();
  }

  /**
   * Rotate Turtle clockwise
   *
   * @param angle angle to rotate turtle by
   */
  public void rotate(double angle) {

    // update angle
    orientation += angle;

    // ensure angle remains within 360 degrees
    orientation = orientation % 360;
    if (orientation < 0) {
      orientation += 360;
    }

    // notify observers about orientation change
    notifyObservers();
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
   * Puts pen down
   */
  public void penDown() {
    penDown = true;
  }

  /**
   * Puts pen up
   */
  public void penUp() {
    penDown = false;
  }

  /**
   * @return list of lines drawn by the turtle
   */
  public List<TurtleLineModel> getLines() {
    return new ArrayList<>(lines);  // Return a copy to avoid external modifications
  }
}
