package slogo.model.api.command.math;

import slogo.model.api.command.Command;

/**
 * PiCommand returns the value of pi.
 */
public class PiCommand extends Command {

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
