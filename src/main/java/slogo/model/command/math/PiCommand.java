package slogo.model.command.math;

import slogo.model.command.Command;

/**
 * PiCommand returns the value of pi.
 */
public class PiCommand implements Command {

  /**
   * Constructs a PiCommand.
   */
  public PiCommand() {
  }

  /**
   * Executes the pi command. Returns the value of pi.
   *
   * @return the value of pi
   */
  @Override
  public Double execute() { // should be double
    return Math.PI;
  }
}
