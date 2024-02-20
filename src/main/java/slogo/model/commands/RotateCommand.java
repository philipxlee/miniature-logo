package slogo.model.commands;

import slogo.model.api.TurtleModel;

public class RotateCommand implements Command {

  private TurtleModel model;
  private double degree;

  public RotateCommand(TurtleModel model, double degree) {
    this.model = model;
    this.degree = degree;
  }

  @Override
  public void execute() {
    model.rotate(degree);
  }
}
