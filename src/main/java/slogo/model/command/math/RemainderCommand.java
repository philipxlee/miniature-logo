package slogo.model.command.math;

import slogo.model.command.Command;

/**
 * RemainderCommand returns the remainder of a dividend divdend by a divisor.
 */
public class RemainderCommand implements Command {

  private final double dividend;
  private final double divisor;

  /**
   * Constructor for the RemainderCommand, which returns the remainder of the dividend divided by
   * the divisor.
   *
   * @param dividend the dividend
   * @param divisor  the divisor
   */
  public RemainderCommand(double dividend, double divisor) {
    this.dividend = dividend;
    this.divisor = divisor;
  }

  /**
   * Executes the remainder command Returns the remainder of the dividend divided by the divisor.
   *
   * @return the remainder of the dividend divided by the divisor
   */
  @Override
  public Double execute() {
    if (divisor == 0) {
      throw new ArithmeticException("Division by zero in remainder calculation.");
    }
    return dividend % divisor;
  }
}
