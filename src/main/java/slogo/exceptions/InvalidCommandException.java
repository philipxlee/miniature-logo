package slogo.exceptions;

/**
 * InvalidCommandException is thrown when an invalid command is used in the IDE.
 */
public class InvalidCommandException extends Exception {

  /**
   * InvalidCommandException constructor.
   *
   * @param message is the description of the error
   */
  public InvalidCommandException(String message) {
    super(message);
  }
}
