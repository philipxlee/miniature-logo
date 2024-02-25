package slogo.model.command.math;

import slogo.model.command.Command;

public class RandomRangeCommand implements Command {
  private final double min;
  private final double max;

  public RandomRangeCommand(double min, double max) {
    this.min = min;
    this.max = max;
  }

  @Override
  public void execute() {
    if (min >= max) {
      throw new IllegalArgumentException("Minimum value must be less than maximum value.");
    }
//    return min + Math.random() * (max - min);
  }
}
