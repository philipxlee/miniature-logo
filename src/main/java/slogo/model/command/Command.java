package slogo.model.command;

/**
 * Command interface creates an abstraction for every command being run (Command design pattern)
 */
public interface Command {

  /**
   * Define behavior to run when command is executed
   */
  Double execute();
}