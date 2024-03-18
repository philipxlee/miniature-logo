package slogo.model.api.command.math;

import slogo.model.api.command.Command;

/**
 * PowerCommand returns the base to the power of the exponent.
 */
public class PowerCommand extends Command {

  private final double base;
  private final double exponent;

  /**
   * Constructor for the PowerCommand, which returns the value of the base raised to the power of
   * the exponent.
   *
   * @param base     the base
   * @param exponent the exponent
   */
  public PowerCommand(double base, double exponent) {
    this.base = base;
    this.exponent = exponent;
  }

  /**
   * Executes the power command Returns the value of the base raised to the power of the exponent.
   *
   * @return the value of the base raised to the power of the exponent
   */
  @Override
  public Double execute() {
    return Math.pow(base, exponent);
  }
}
