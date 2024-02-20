package slogo.model.commands;

import slogo.model.api.TurtleModel;

public class SetHeadingCommand implements Command {

  private final TurtleModel model;
  private final double degree;

  /**
   * Constructor for the RotateCommand, which rotates the turtle by a given degree
   *
   * @param model  the model to change
   * @param degree the degree to rotate the turtle by
   */
  public SetHeadingCommand(TurtleModel model, double degree) {
    this.model = model;
    this.degree = degree;
  }

  /**
   * Executes the rotate command Rotates the turtle by the given degree
   */
  @Override
  public void execute() {
//    TODO: implement setHeading
//    model.setAbsoluteHeading(degree);
  }
}
