package slogo.model.commands.turtle;

import slogo.model.api.TurtleModel;
import slogo.model.commands.Command;

public class SetOrientationCommand implements Command {

  private final TurtleModel model;
  private final Double degree;
  private final Double pointX;
  private final Double pointY;
  private final OrientationAction actionType;

  /**
   * Constructor for setting the turtle's heading to an absolute degree.
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
   */
  @Override
  public void execute() {
    Command action = null;
    switch (actionType) {
//      TODO: implement setAbsoluteHeading and setXYComman
//      case SET_HEADING -> model.setAbsoluteHeading(degree);
//      case TOWARDS -> model.setXYCommand(pointX, pointY);
      case SET_HEADING -> System.out.println("Set heading to " + degree);
      case TOWARDS -> System.out.println("Turn towards (" + pointX + ", " + pointY + ")");
      case PLACEHOLDER -> System.out.println("Placeholder >:)");
      default -> throw new IllegalStateException("Unexpected value: " + actionType);
    }
  }

  private enum OrientationAction {
    SET_HEADING,
    TOWARDS,
    PLACEHOLDER // remove in production
  }
}