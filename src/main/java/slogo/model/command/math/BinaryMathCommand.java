package slogo.model.command.math;

import slogo.model.command.Command;

/**
 * BinaryMathCommand represents each of the 4 operations.
 */
public class BinaryMathCommand implements Command {

  private final double operand1;
  private final double operand2;
  private final OperationType operationType;

  /**
   * Constructor for the BinaryMathCommand, which performs a binary math operation
   *
   * @param operand1      the first operand
   * @param operand2      the second operand
   * @param operationType the type of operation to perform
   */
  public BinaryMathCommand(double operand1, double operand2, OperationType operationType) {
    this.operand1 = operand1;
    this.operand2 = operand2;
    this.operationType = operationType;
  }

  /**
   * Executes the binary math command.
   *
   * @return the result of the operation
   */
  @Override
  public Double execute() {
    double result = 0;
    switch (operationType) {
      case SUM -> result = operand1 + operand2;
      case DIFFERENCE -> result = operand1 - operand2;
      case PRODUCT -> result = operand1 * operand2;
      case QUOTIENT -> result = operand1 / operand2;
      default -> throw new IllegalStateException("Unexpected operation type: " + operationType);
    }
    return result;
  }

  public enum OperationType {
    SUM, DIFFERENCE, PRODUCT, QUOTIENT
  }
}
