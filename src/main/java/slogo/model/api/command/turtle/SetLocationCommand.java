package slogo.model.api.command.turtle;

import slogo.model.api.data.TurtleModel;
import slogo.model.api.command.Command;

/**
 * SetLocationCommand moves the turtle to a specified location.
 */
public class SetLocationCommand implements Command {

  private final TurtleModel model;
  private final double destinationX;
  private final double destinationY;

  /**
   * Constructor for the SetLocationCommand, which sets the position of the turtle.
   *
   * @param model the model to change
   */
  public SetLocationCommand(TurtleModel model, double destinationX, double destinationY) {
    this.destinationX = destinationX;
    this.destinationY = destinationY;
    this.model = model;
  }

  /**
   * Executes the home command Moves the turtle to the home position.
   */
  @Override
  public Double execute() {
    return model.setLocation(destinationX, destinationY);
  }
}
