package slogo.model.command.bool;

import slogo.model.command.Command;

/**
 * UnaryBoolCommand represents a unary operation.
 */
public class UnaryBoolCommand implements Command {

  private final double operand;
  private final UnaryOperationType operationType;

  /**
   * Constructs a UnaryBoolCommand, which performs a unary boolean operation.
   *
   * @param operand       the operand
   * @param operationType the type of operation to perform
   */
  public UnaryBoolCommand(double operand, UnaryOperationType operationType) {
    this.operand = operand;
    this.operationType = operationType;
  }

  /**
   * Executes the unary boolean command.
   *
   * @return the result of the operation
   */
  @Override
  public Double execute() { // change to double
    if (operationType == UnaryOperationType.NOT) {
      return operand == 0 ? 1.0 : 0.0;
    }
    throw new IllegalStateException("Unexpected operation type: " + operationType);
  }

  /**
   * Define types for all unary operations.
   */
  public enum UnaryOperationType {
    NOT
  }
}
