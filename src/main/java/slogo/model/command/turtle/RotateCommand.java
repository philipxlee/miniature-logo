package slogo.model.command.turtle;

import slogo.model.api.TurtleModel;
import slogo.model.command.Command;

public class RotateCommand implements Command {

  private final TurtleModel model;
  private final double degree;

  /**
   * Constructor for the RotateCommand, which rotates the turtle by a given degree
   *
   * @param model  the model to change
   * @param degree the degree to rotate the turtle by
   */
  public RotateCommand(TurtleModel model, double degree) {
    this.model = model;
    this.degree = degree;
  }

  /**
   * Executes the rotate command Rotates the turtle by the given degree
   */
  @Override
  public Double execute() {
    return model.rotateTurtle(degree);
  }
}
