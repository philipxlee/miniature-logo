package slogo.model;

import java.util.ArrayList;
import java.util.List;
import slogo.utils.Observable;
import slogo.utils.Observer;

/**
 * TurtleModel representing the state of the Turtle
 *
 * @author Arnav Nayak
 */
public class TurtleModel implements Observable {

  private List<Observer> observers;
  private double xPosition;
  private double yPosition;
  private double orientation;


  /**
   * TurtleModel constructor
   */
  public TurtleModel() {
    observers = new ArrayList<>();
    xPosition = 0;
    yPosition = 0;
    orientation = 0;
  }

  /**
   * Move Turtle forward by some distance
   *
   * @param distance: distance to move Turtle
   */
  public void moveForward(double distance) {
    // calculate change in X and Y based on orientation angle and distance
    double orientationRadians = Math.toRadians(orientation);
    double deltaX = distance * Math.cos(orientationRadians);
    double deltaY = distance * Math.sin(orientationRadians);

    // update X and Y position
    xPosition += deltaX;
    yPosition += deltaY;

    // notify observers about position change
    notifyObservers();
  }

  /**
   * Rotate Turtle clockwise
   *
   * @param angle: angle to rotate turtle by
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
   * Add observer to list of observers
   */
  @Override
  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  /**
   * Remove observer from list of observers
   */
  @Override
  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  /**
   * Notify all observers of state change
   */
  @Override
  public void notifyObservers() {
    observers.forEach(observer -> observer.update(this));
  }

  /**
   * @return X position of Turtle
   */
  public double getX() {
    return xPosition;
  }

  /**
   * @return Y position of Turtle
   */
  public double getY() {
    return yPosition;
  }
}
