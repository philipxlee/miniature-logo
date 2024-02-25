package slogo.model.api.command.math;

import slogo.model.api.command.Command;

/**
 * UnaryMathCommand performs a unary math operation.
 */
public class UnaryMathCommand implements Command {

  private final double operand;
  private final UnaryOperationType operationType;

  /**
   * Constructor for the UnaryMathCommand, which performs a unary math operation.
   *
   * @param operand       the operand
   * @param operationType the type of operation to perform
   */
  public UnaryMathCommand(double operand, UnaryOperationType operationType) {
    this.operand = operand;
    this.operationType = operationType;
  }

  /**
   * Executes the unary math command.
   *
   * @return the result of the operation
   */
  @Override
  public Double execute() {
    double result = 0;
    switch (operationType) {
      case MINUS -> result = -operand;
      case SINE -> result = Math.sin(Math.toRadians(operand));
      case COSINE -> result = Math.cos(Math.toRadians(operand));
      case TANGENT -> result = Math.tan(Math.toRadians(operand));
      case ARCTANGENT -> result = Math.toDegrees(Math.atan(operand));
      case SQUAREROOT -> result = Math.sqrt(operand);
      case LOG -> result = Math.log(operand);
      default -> throw new IllegalStateException("Unexpected operation type: " + operationType);
    }
    return result;
  }

  /**
   * Define types for each unary operation.
   */
  public enum UnaryOperationType {
    MINUS, SINE, COSINE, TANGENT, ARCTANGENT, SQUAREROOT, LOG
  }
}
