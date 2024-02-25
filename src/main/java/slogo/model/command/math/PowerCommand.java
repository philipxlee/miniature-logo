package slogo.model.command.math;

import slogo.model.command.Command;

public class PowerCommand implements Command {
  private final double base;
  private final double exponent;

  public PowerCommand(double base, double exponent) {
    this.base = base;
    this.exponent = exponent;
  }

  @Override
  public void execute() {
    System.out.println(Math.pow(base, exponent)); // placeholder
//    return Math.pow(base, exponent);
  }
}
