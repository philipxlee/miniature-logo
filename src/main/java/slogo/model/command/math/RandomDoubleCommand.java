package slogo.model.command.math;

import slogo.model.command.Command;

public class RandomDoubleCommand implements Command {
  private final double max;

  public RandomDoubleCommand(double max) {
    this.max = max;
  }

  @Override
  public void execute() {
    System.out.println(Math.random() * max); // placeholder
//    return Math.random() * max;
  }
}
