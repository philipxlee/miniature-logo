package slogo.model.api.command.turtle;

import slogo.model.api.command.Command;
import slogo.model.api.data.TurtleModel;

/**
 * RotateCommand rotates the turtle by a specified amount.
 */
public class RotateCommand extends Command {

  private final TurtleModel model;
  private final double degree;

  /**
   * Constructor for the RotateCommand, which rotates the turtle by a given degree.
   *
   * @param model  the model to change
   * @param degree the degree to rotate the turtle by
   */
  public RotateCommand(TurtleModel model, double degree) {
    this.model = model;
    this.degree = degree;
  }

  /**
   * Executes the rotate command Rotates the turtle by the given degree.
   */
  @Override
  public Double execute() {
    return model.rotateTurtle(degree);
  }
}
