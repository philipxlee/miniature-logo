package slogo.model.command.turtle;

import slogo.model.api.TurtleModel;
import slogo.model.command.Command;

public class SetLocationCommand implements Command {

  private final TurtleModel model;
  double destinationX;
  double destinationY;

  /**
   * Constructor for the HomeCommand, which moves the turtle to the home position
   *
   * @param model the model to change
   */
  public SetLocationCommand(TurtleModel model, double destinationX, double destinationY) {
    this.destinationX = destinationX;
    this.destinationY = destinationY;
    this.model = model;
  }

  /**
   * Executes the home command Moves the turtle to the home position
   */
  @Override
  public void execute() {
    model.setLocation(destinationX, destinationY);
  }
}
