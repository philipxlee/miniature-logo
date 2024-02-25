package slogo.model.api.command.math;

import slogo.model.api.command.Command;

/**
 * RandomDoubleCommand returns a random double within a range.
 */
public class RandomDoubleCommand implements Command {

  private final double max;

  /**
   * Constructor for the Random Command, which returns a random double between 0 and the given
   * maximum value.
   *
   * @param max the maximum value to return
   */
  public RandomDoubleCommand(double max) {
    this.max = max;
  }

  /**
   * Executes the random command Returns a random double between 0 and the given maximum value.
   *
   * @return a random double between 0 and the given maximum value
   */
  @Override
  public Double execute() {
    return Math.random() * max;
  }
}
