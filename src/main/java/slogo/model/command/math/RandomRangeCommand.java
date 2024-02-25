package slogo.model.command.math;

import slogo.model.command.Command;

/**
 * RandomRangeCommand returns a random double between a defined range.
 */
public class RandomRangeCommand implements Command {

  private final double min;
  private final double max;

  /**
   * Constructor for the RandomRangeCommand, which returns a random double between the given
   * minimum.
   *
   * @param min the minimum value to return
   * @param max the maximum value to return
   */
  public RandomRangeCommand(double min, double max) {
    this.min = min;
    this.max = max;
  }

  /**
   * Executes the random command Returns a random double between the given minimum and maximum
   * value.
   *
   * @return a random double between the given minimum and maximum value
   */
  @Override
  public Double execute() {
    if (min >= max) {
      throw new IllegalArgumentException("Minimum value must be less than maximum value.");
    }
    return min + Math.random() * (max - min);
  }
}
