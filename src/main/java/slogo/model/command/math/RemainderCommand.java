package slogo.model.command.math;

import slogo.model.command.Command;

public class RemainderCommand implements Command {

  private final double dividend;
  private final double divisor;

  public RemainderCommand(double dividend, double divisor) {
    this.dividend = dividend;
    this.divisor = divisor;
  }

  @Override
  public void execute() {
    if (divisor == 0) {
      throw new ArithmeticException("Division by zero in remainder calculation.");
    }
  }
}
