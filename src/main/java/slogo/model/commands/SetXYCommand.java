package slogo.model.commands;

import slogo.model.api.TurtleModel;

public class SetXYCommand implements Command {

  private final TurtleModel model;
  private final double pointX;
  private final double pointY;

  /**
   * Constructor for the RotateCommand, which rotates the turtle by a given degree
   *
   * @param model the model to change
   * @param x     the x coordinate to move the turtle to
   * @param y     the y coordinate to move the turtle to
   */
  public SetXYCommand(TurtleModel model, double x, double y) {
    this.model = model;
    this.pointX = x;
    this.pointY = y;
  }

  /**
   * Executes the setXY command, that moves the turtle in te direction of the given point
   */
  @Override
  public void execute() {
//    TODO: implement setHeading
//    model.setXYHeading(pointX, pointY);
  }
}
