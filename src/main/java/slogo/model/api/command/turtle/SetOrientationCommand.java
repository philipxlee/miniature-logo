package slogo.model.api.command.turtle;

import slogo.model.api.command.Command;
import slogo.model.api.data.TurtleModel;

/**
 * SetOrientationCommand sets the orientation of the turtle.
 */
public class SetOrientationCommand implements Command {

  private final TurtleModel model;
  private final Double degree;
  private final Double pointX;
  private final Double pointY;
  private final OrientationAction actionType;

  /**
   * Constructor for setting the turtle's heading to an absolute degree.
   *
   * @param model  the model to change
   * @param degree the degree to set the turtle's heading to
   */
  public SetOrientationCommand(TurtleModel model, double degree) {
    this.model = model;
    this.degree = degree;
    this.pointX = null;
    this.pointY = null;
    this.actionType = OrientationAction.SET_HEADING;
  }

  /**
   * Constructor for turning the turtle towards a point (x, y).
   *
   * @param model the model to change
   * @param x     the x-coordinate of the point to turn towards
   * @param y     the y-coordinate of the point to turn towards
   */
  public SetOrientationCommand(TurtleModel model, double x, double y) {
    this.model = model;
    this.degree = null;
    this.pointX = x;
    this.pointY = y;
    this.actionType = OrientationAction.TOWARDS;
  }

  /**
   * Executes the set orientation command. Sets the turtle's heading to the given degree, or turns
   * the turtle towards the given point.
   *
   * @return the result of the command
   * @throws IllegalStateException if the action type is not recognized
   */
  @Override
  public Double execute() {
    double result = 0;
    switch (actionType) {
      case TOWARDS -> result = model.faceDirection(pointX, pointY);
      case SET_HEADING -> result = model.setHeading(degree);
      default -> throw new IllegalStateException("Unexpected value: " + actionType);
    }
    return result;
  }

  private enum OrientationAction {
    SET_HEADING,
    TOWARDS,
  }
}
