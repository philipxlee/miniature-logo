package slogo.model.commands;

import slogo.model.api.TurtleModel;

public class TowardsCommand implements Command {

  private final TurtleModel model;
  private final double pointX;
  private final double pointY;

  /**
   * Constructor for the Towards Command, which moves the turtle to a given point
   *
   * @param model the model to change
   * @param x     the x coordinate to move the turtle to
   * @param y     the y coordinate to move the turtle to
   */
  public TowardsCommand(TurtleModel model, double x, double y) {
    this.model = model;
    this.pointX = x;
    this.pointY = y;
  }

  /**
   * Executes the move turtle command. Moves the turtle to the given point.
   */
  @Override
  public void execute() {
//    TODO: implement towards
//    model.moveToPosition(pointX, pointY);
  }


}
